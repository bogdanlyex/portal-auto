package ro.esolutions.nemetschek.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.esolutions.nemetschek.bean.ValidationFailureResponse;

/**
 * Created with IntelliJ IDEA.
 * User: marius
 * Date: 5/4/15
 * Time: 4:32 PM
 */

@ControllerAdvice
public class BrokerErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationFailureResponse processValidationFailure(MethodArgumentNotValidException ex) {
		return new ValidationFailureResponse.Builder().messageSource(messageSource)
				.fieldErrors(ex.getBindingResult().getFieldErrors())
				.errors(ex.getBindingResult().getGlobalErrors()).build();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ExceptionHandler(UniqueViolationException.class)
	public ValidationFailureResponse processValidationFailure(UniqueViolationException ex) {
		return new ValidationFailureResponse.Builder().messageSource(messageSource)
				.fieldError(ex.getField(), ex.getMessage()).build();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleAnyException(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
}
