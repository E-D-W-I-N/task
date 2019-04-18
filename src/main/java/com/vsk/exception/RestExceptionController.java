package com.vsk.exception;

import com.vsk.util.ControllerUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatus status,
																  WebRequest request) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ControllerUtils.getErrors(ex.getBindingResult()));
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrorCode() +
				" exception, please, check param with value " + "' " + ex.getValue() + " '"
				+ " it must by typeOf " + ex.getRequiredType());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(DateTimeParseException.class)
	protected ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage().replaceFirst("Text", "Date and Time")
						+ " Please, use right date-time format: yyyy-MM-dd HH:mm:ss");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

		List<String> errors = new ArrayList();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
