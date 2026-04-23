package com.supply_chain.userservice_supplychain.exceptions;

import com.supply_chain.userservice_supplychain.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ErrorDto HandlePasswordNotMatchedException(PasswordNotMatchedException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus("FAILURE");
        return errorDto;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDto HandleUserNotFoundException(UserNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus("FAILURE");
        return errorDto;
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ErrorDto HandleUserAlreadyExistException(UserAlreadyExistException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus("ALREADY_EXIST");
        return errorDto;
    }

}
