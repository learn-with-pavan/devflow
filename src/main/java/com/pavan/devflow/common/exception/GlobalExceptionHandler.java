package com.pavan.devflow.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pavan.devflow.common.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult()
		.getFieldErrors()
		.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		ApiResponse<Object> response = ApiResponse.builder()
				.success(false)
				.message("Validation failed")
				.data(null)
				.errors(errors)
				.build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	// Handle Duplicate Email
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmailExists(EmailAlreadyExistsException ex){
		ApiResponse<Object> response = ApiResponse.builder()
				.success(false)
				.message(ex.getMessage())
				.data(null)
				.errors(null)
				.build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		
	}
	
	// Handle Resource not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmailExists(ResourceNotFoundException ex){
		ApiResponse<Object> response = ApiResponse.builder()
				.success(false)
				.message(ex.getMessage())
				.data(null)
				.errors(null)
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		
	}
	
	// Unexpected exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleEmailExists(Exception ex){
		ApiResponse<Object> response = ApiResponse.builder()
				.success(false)
				.message("Something went wrong")
				.data(null)
				.errors(null)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		
	}
}
