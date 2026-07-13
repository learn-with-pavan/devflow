package com.pavan.devflow.auth.service;

import com.pavan.devflow.auth.dto.LoginRequest;
import com.pavan.devflow.auth.dto.LoginResponse;
import com.pavan.devflow.auth.dto.RegisterRequest;

public interface AuthService {
	void register(RegisterRequest request);
	LoginResponse login(LoginRequest request);
}
