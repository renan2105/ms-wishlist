package com.renan.wish.resources.exception;

import javax.servlet.http.HttpServletRequest;

import com.renan.wish.services.exception.BusinessErrorException;
import com.renan.wish.services.exception.ExceededLimitWishesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.renan.wish.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Não encontrado.",
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ExceededLimitWishesException.class)
	public ResponseEntity<StandardError> exceededLimitWishes(ExceededLimitWishesException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Só se adicionar 20 desejos.",
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(BusinessErrorException.class)
	public ResponseEntity<StandardError> businessError(BusinessErrorException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Erro de regra de negocio.",
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
