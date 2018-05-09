package com.springboot.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception handler
 *
 */
@ControllerAdvice
public class RestExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {

		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("NOT FOUND");
		response.setErrorMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.name());
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidInput(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("VALIDATION_ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleOtherExceptions(Exception ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("INTERNAL ERROR");
		response.setErrorMessage(ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
