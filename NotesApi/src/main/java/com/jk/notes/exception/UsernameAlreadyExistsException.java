package com.jk.notes.exception;


import com.jk.notes.payload.response.error.ErrorMessages;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format(ErrorMessages.USERNAME_TAKEN, username));
    }
}
