package tech.leonam.erp.controller.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tech.leonam.erp.exceptions.IdentificadorInvalidoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdentificadorInvalidoException.class)
    public ApiError handleIdentificadorInvalidoException(IdentificadorInvalidoException ex) {
        return ApiError.builder()
                .descricao("Error: " + HttpStatus.NOT_FOUND)
                .error(Arrays.asList(ex.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ApiError.builder()
                .descricao("Error: " + HttpStatus.BAD_REQUEST)
                .error(Arrays.asList(ex.getMessage()))
                .build();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handlerHttpMessageNotReadableException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ApiError.builder()
                .descricao("Error: " + HttpStatus.BAD_REQUEST)
                .error(errors)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleIdentificadorInvalidoException(Exception ex) {

        return ApiError.builder()
                .descricao("Error: " + HttpStatus.NOT_FOUND)
                .error(Arrays.asList(ex.getMessage()))
                .build();
    }
}
