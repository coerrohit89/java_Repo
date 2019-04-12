package com.rohit.exception;

public class SORuntimeException extends RuntimeException {

	private final String message;

	public SORuntimeException(String message) {

		super(message);
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

}
