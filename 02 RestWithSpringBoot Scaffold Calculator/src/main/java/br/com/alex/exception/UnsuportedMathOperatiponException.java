package br.com.alex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsuportedMathOperatiponException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnsuportedMathOperatiponException(String exception) {
		super(exception);
	}
}
