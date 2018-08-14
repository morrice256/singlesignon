package com.morrice.SingleSignOn.foundation.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void handleConflict() {
		logger.error("Data integrity violation");
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entitie not found")
	@ExceptionHandler(NotFoundException.class)
	public void handleNotfound() {
		logger.error("Entitie not found");
	}
}
