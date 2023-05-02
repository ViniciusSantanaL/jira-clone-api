package br.com.iesb.jira.infrastructure.errorhandling;

import br.com.iesb.jira.infrastructure.exception.BusinessException;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.NotHaveAccessException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class DefaultErrorHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultErrorHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleException(Exception ex, HttpServletRequest request) {
        LOGGER.error("Internal Error ",ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<MessageResponse> handleBusiness(BusinessException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorValidationResponse>> handleBeanValidation(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(FieldErrorValidationResponse::new).toList());
    }

    @ExceptionHandler(EntityDuplicatedException.class)
    public ResponseEntity<MessageResponse> handleDuplicatedEntity(EntityDuplicatedException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(NotHaveAccessException.class)
    public ResponseEntity<MessageResponse> handleNotHaveAccess(NotHaveAccessException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    private ResponseEntity<MessageResponse> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new MessageResponse(message, status.value()));
    }

    private record FieldErrorValidationResponse(String field, String message){
        public FieldErrorValidationResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
