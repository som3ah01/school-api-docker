package com.metadata.school.config;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.metadata.school.dto.ExceptionDTO;
import com.metadata.school.exceptions.OverSizeException;
import com.metadata.school.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerConfig {
	Logger logger = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ExceptionDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ExceptionDTO e = new ExceptionDTO(ex.getMessageKey(),ex.getMessage());
		logger.error(e.toString());
		return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
		logger.error(ex.toString());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(value = { OverSizeException.class })
	public ResponseEntity<ExceptionDTO> handleOverSizeException(OverSizeException ex) {
		ExceptionDTO e = new ExceptionDTO(ex.getMessageKey(),ex.getMessage());
		logger.error(e.toString());
		return new ResponseEntity<>(e, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		logger.error(ex.toString());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
