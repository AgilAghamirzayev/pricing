package com.hackathon.pricing.exception;

import com.hackathon.pricing.exception.classes.RecordNotFoundException;
import com.hackathon.pricing.model.shared.ErrorResponse;
import lombok.CustomLog;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@CustomLog
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String VALIDATION_FAILED_MSG = "Validation failed!";
    public static final String UNSUPPORTED_METHOD_MSG = "This http method is not allowed here!";
    public static final String INTERNAL_ERROR_MSG = "Request cannot be processed by the server!";
    public static final String CAMUNDA_FAILED_MSG = "Failed to get successful response from camunda!";
    public static final String RECORD_NOT_FOUND_MSG = "Record not found!";
    public static final String PARAM_TYPE_MISMATCH_MSG = "Parameter type is incorrect!";
    public static final String ACTIVE_APPLICATION_MSG = "Active applications exist!";
    public static final String EXTERNAL_TASK_NOT_FOUND_MSG = "External task not found!";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, ex.getMostSpecificCause().getMessage(), ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getError(BAD_REQUEST, PARAM_TYPE_MISMATCH_MSG, ex.getMostSpecificCause().getMessage(), ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorDetail = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, errorDetail, ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getError(METHOD_NOT_ALLOWED, UNSUPPORTED_METHOD_MSG, ex.getMessage(), ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, ex.getLocalizedMessage(), ex, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        String errorDetail = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, errorDetail, ex, request);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> handleRecordNotFoundException(
            RecordNotFoundException ex, WebRequest request) {
        String errorDetail = ex.getLocalizedMessage();
        return getError(NOT_FOUND, RECORD_NOT_FOUND_MSG, errorDetail, ex, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String errorDetail = ex.getMessage();
        return getError(INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MSG, errorDetail, ex, request);
    }

    private ResponseEntity<Object> getError(
            HttpStatus status, String message, String errorDetail, Exception ex, WebRequest request) {
        log.error("{} : {}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(status)
                .body(ErrorResponse.builder()
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(message)
                        .errorDetail(errorDetail)
                        .path(request.getDescription(false))
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
