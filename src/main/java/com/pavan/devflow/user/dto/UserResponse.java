package com.pavan.devflow.user.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private LocalDateTime createdAt;
}
