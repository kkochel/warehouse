package pl.recruitmenttask.error.handling;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> buildResponseEntity(ErrorDto apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleEntityNotFound(NullPointerException ex) {
        ErrorDto apiError = new ErrorDto(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(RuntimeException ex) {
        ErrorDto apiError = new ErrorDto(UNPROCESSABLE_ENTITY);
        apiError.setMessage(ex.getCause().getCause().getMessage());
        return buildResponseEntity(apiError);
    }

}
