package com.training.institutemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.training.institutemanagementsystem.exception.UserExistException;

@RestControllerAdvice
public class CenteralizedExceptionHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserExistException.class)
	public String handleInvalidId(UserExistException e) {
		return e.getMessage();
	

}
}
