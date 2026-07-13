package com.pavan.devflow.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
	
	private String accessToken;
	private String tokenType;
}
