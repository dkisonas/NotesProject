package com.jk.notes.exception;


import com.jk.notes.payload.response.error.ErrorMessages;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role) {
        super(String.format(ErrorMessages.ROLE_NOT_FOUND, role));
    }
}
