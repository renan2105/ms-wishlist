package com.renan.wish.services.exception;

public class BusinessErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessErrorException(String msg) {
		super(msg);
	}
}
