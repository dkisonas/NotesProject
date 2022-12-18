package com.jk.notes.payload.response.error;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;

    private List<FieldErrorDto> fieldErrors;

    public ErrorDto(String message) {
        this.message = message;
    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDto(objectName, field, message));
    }

}