package com.renan.wish.services.exception;

public class ExceededLimitWishesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceededLimitWishesException(String msg) {
		super(msg);
	}
}
