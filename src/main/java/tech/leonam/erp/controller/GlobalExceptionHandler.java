package tech.leonam.erp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.leonam.erp.model.DTO.ErroDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErroDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                String message = error.getDefaultMessage();
                if (message != null) {
                    errorMessage.append(message).append(" ");
                }
            }
        });

        return ResponseEntity.badRequest().body(new ErroDTO(errorMessage.toString(), HttpStatus.BAD_REQUEST.value()));
    }
}
