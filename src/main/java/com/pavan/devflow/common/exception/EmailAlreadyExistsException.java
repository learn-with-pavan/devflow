package com.pavan.devflow.common.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException {

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
