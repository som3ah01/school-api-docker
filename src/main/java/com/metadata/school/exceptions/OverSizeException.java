package com.metadata.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OverSizeException extends RuntimeException {
	
	private static final long serialVersionUID = 8271137279842298118L;
	private final String messageKey;

	public OverSizeException(String message, String messageKey) {
		super(message);

		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}

}
