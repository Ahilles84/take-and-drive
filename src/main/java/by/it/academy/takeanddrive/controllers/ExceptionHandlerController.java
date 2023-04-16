package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.ErrorResponse;
import by.it.academy.takeanddrive.exceptions.CarIsBusyException;
import by.it.academy.takeanddrive.exceptions.LoginNotUniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CarIsBusyException.class)
    public ResponseEntity<ErrorResponse> handleCarIsBusyException(CarIsBusyException exception){
        log.warn("EXCEPTION: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception){
        log.warn("EXCEPTION: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }
    @ExceptionHandler(LoginNotUniqueException.class)
    public ResponseEntity<ErrorResponse> handleLoginNotUniqueException(LoginNotUniqueException exception){
        log.warn("EXCEPTION: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }
}
