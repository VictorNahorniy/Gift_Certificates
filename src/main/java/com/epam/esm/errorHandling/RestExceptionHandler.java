package com.epam.esm.errorHandling;

import com.epam.esm.errorHandling.customException.Ceritficate.CertificateNotFoundException;
import com.epam.esm.errorHandling.customException.Ceritficate.DuplicateCertificateException;
import com.epam.esm.errorHandling.customException.Ceritficate.WrongSortParamException;
import com.epam.esm.errorHandling.customException.Tag.DuplicateTagException;
import com.epam.esm.errorHandling.customException.Tag.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a handler for exceptions that are thrown in the application.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DuplicateTagException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateTagException(DuplicateTagException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTagNotFoundException(TagNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CertificateNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCertificateNotFoundException(CertificateNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCertificateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCertificateException(DuplicateCertificateException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongSortParamException.class)
    public ResponseEntity<ErrorResponse> handleWrongSortParamException(WrongSortParamException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
