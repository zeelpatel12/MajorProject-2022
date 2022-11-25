package com.amazon.backend.exceptions;

public class CartItemAlreadyExistsException extends RuntimeException {
    public CartItemAlreadyExistsException(String message) {
        super(message);
    }
}
