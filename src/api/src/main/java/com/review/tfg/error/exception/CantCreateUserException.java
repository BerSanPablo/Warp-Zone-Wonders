package com.review.tfg.error.exception;

public class CantCreateUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public CantCreateUserException(String message) {
        super(message);
    }
}
