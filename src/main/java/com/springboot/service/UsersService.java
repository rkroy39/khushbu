package com.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.model.UsersModel;


public interface UsersService {
	List<UsersModel> getAllUsers();
	void saveUser(UsersModel user);
	UsersModel getUserById(long id);
	void deleteUserById(long id);
	Page<UsersModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
