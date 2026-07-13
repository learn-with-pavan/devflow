package com.pavan.devflow.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.devflow.auth.dto.LoginRequest;
import com.pavan.devflow.auth.dto.LoginResponse;
import com.pavan.devflow.auth.dto.RegisterRequest;
import com.pavan.devflow.auth.service.AuthService;
import com.pavan.devflow.common.response.ApiResponse;
import com.pavan.devflow.common.response.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) {
		authService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success("User registered successfully", null));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
		LoginResponse response = authService.login(request);
		return ResponseEntity.ok(ResponseBuilder.success("Login successful", response));
	}
}
