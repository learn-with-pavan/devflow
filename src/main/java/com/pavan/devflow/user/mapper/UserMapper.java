package com.pavan.devflow.user.mapper;

import org.springframework.stereotype.Component;

import com.pavan.devflow.user.dto.CreateUserRequest;
import com.pavan.devflow.user.dto.UserResponse;
import com.pavan.devflow.user.entity.User;

@Component
public class UserMapper {

	public User toEntity(CreateUserRequest request) {
		return User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).build();
	}

	public UserResponse toResponse(User user) {
		return UserResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).createdAt(user.getCreatedAt()).build();
	}
}
