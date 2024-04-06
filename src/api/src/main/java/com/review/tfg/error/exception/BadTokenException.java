package com.review.tfg.error.exception;

public class BadTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public BadTokenException(String message) {
        super(message);
    }
}
