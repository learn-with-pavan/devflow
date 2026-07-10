package com.pavan.devflow.common.response;

public final class ResponseBuilder {

	private ResponseBuilder() {

	}

	public static <T> ApiResponse<T> success(String message, T data) {
		return ApiResponse.<T>builder().success(true).message(message).data(data).errors(null).build();
	}

	public static ApiResponse<Object> error(String message, Object errors) {
		return ApiResponse.builder().success(false).message(message).data(null).errors(errors).build();
	}
}
