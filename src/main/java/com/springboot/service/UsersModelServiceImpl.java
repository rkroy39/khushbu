package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.model.UsersModel;
import com.springboot.repository.UsersRepository;

@Service
public class UsersModelServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<UsersModel> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public void saveUser(UsersModel user) {
		this.usersRepository.save(user);
	}

	@Override
	public UsersModel getUserById(long id) {
		Optional<UsersModel> optional = usersRepository.findById(id);
		UsersModel UsersModel = null;
		if (optional.isPresent()) {
			UsersModel = optional.get();
		} else {
			throw new RuntimeException(" UsersModel not found for id :: " + id);
		}
		return UsersModel;
	}

	@Override
	public void deleteUserById(long id) {
		this.usersRepository.deleteById(id);
	}

	@Override
	public Page<UsersModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.usersRepository.findAll(pageable);
	}
}
