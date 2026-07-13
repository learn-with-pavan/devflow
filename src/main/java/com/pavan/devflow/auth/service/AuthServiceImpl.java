package com.pavan.devflow.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pavan.devflow.auth.dto.LoginRequest;
import com.pavan.devflow.auth.dto.LoginResponse;
import com.pavan.devflow.auth.dto.RegisterRequest;
import com.pavan.devflow.common.exception.EmailAlreadyExistsException;
import com.pavan.devflow.security.jwt.JwtService;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.user.enums.Role;
import com.pavan.devflow.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Override
	public void register(RegisterRequest request) {

		if (userRepo.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists");
		}

		User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
				.build();
		userRepo.save(user);

	}

	@Override
	public LoginResponse login(LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPasswor()));

		User user = (User) authentication.getPrincipal();
		String token = jwtService.generateToken(user);
		return LoginResponse.builder().accessToken(token).tokenType("Bearer").expiresIn(86400L).build();
	}

}
