package com.jk.notes.exception.handler;


import com.jk.notes.payload.response.error.ErrorDto;
import com.jk.notes.payload.response.error.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class ExceptionMessageProvider {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto processValidationError(MethodArgumentTypeMismatchException ex) {
        ErrorDto dto = new ErrorDto(ErrorMessages.METHOD_ARGUMENT_TYPE_MISMATCH_ERROR);
        dto.add(ex.getName(), ex.getParameter().getParameterName(), ex.getMessage());
        return dto;
    }

    private ErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        ErrorDto dto = new ErrorDto(ErrorMessages.VALIDATION_ERROR);

        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }

        return dto;
    }

}