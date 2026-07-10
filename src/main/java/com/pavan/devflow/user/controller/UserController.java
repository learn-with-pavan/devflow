package com.pavan.devflow.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.devflow.common.constants.AppConstants;
import com.pavan.devflow.common.response.ApiResponse;
import com.pavan.devflow.common.response.PageResponse;
import com.pavan.devflow.common.response.ResponseBuilder;
import com.pavan.devflow.user.dto.CreateUserRequest;
import com.pavan.devflow.user.dto.UpdateUserRequest;
import com.pavan.devflow.user.dto.UserResponse;
import com.pavan.devflow.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) {
		UserResponse response = userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success(AppConstants.USER_CREATED, response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {
		UserResponse response = userService.getUserById(id);
		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.USER_FETCHED, response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<PageResponse<UserResponse>>> getAllUsers(

			@RequestParam(defaultValue = "0") int page,

			@RequestParam(defaultValue = "10") int size,

			@RequestParam(defaultValue = "id") String sortBy,

			@RequestParam(defaultValue = "asc") String direction) {

		PageResponse<UserResponse> response = userService.getAllUsers(page, size, sortBy, direction);

		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.USERS_FETCHED, response));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> updateUser(@Valid @RequestBody UpdateUserRequest request,
			@PathVariable Long id) {
		UserResponse response = userService.updateUser(request, id);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success(AppConstants.USER_UPDATED, response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {

		userService.deleteUser(id);

		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.USER_DELETED, null));
	}
}
