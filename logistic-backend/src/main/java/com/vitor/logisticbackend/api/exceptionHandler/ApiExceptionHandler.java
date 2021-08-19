package com.vitor.logisticbackend.api.exceptionHandler;

import com.vitor.logisticbackend.domain.exception.BusinessException;
import com.vitor.logisticbackend.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
          HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problem.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            fields.add(new Problem.Field(name, message));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle("One or more fields are invalid");
        problem.setFields(fields);

        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleCustomerAlreadyRegistered(BusinessException ex, WebRequest req) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problem problem = createErrorInstance(ex, status);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, req);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest req) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createErrorInstance(ex, status);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, req);
    }

    private Problem createErrorInstance(RuntimeException ex, HttpStatus status) {
        return Problem.builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title(ex.getMessage())
                .build();
    }
}
