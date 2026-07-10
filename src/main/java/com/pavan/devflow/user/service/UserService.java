package com.pavan.devflow.user.service;

import com.pavan.devflow.common.response.PageResponse;
import com.pavan.devflow.user.dto.CreateUserRequest;
import com.pavan.devflow.user.dto.UpdateUserRequest;
import com.pavan.devflow.user.dto.UserResponse;

import jakarta.validation.Valid;

public interface UserService {

	// Create User method
	UserResponse createUser(CreateUserRequest request);
	UserResponse getUserById(Long id);
	PageResponse<UserResponse>  getAllUsers(int page, int size, String sortBy, String direction);
	UserResponse updateUser(UpdateUserRequest request, Long id);
	void deleteUser(Long id);
}
