package com.epam.esm.errorHandling.customException.Ceritficate;

/**
 * This class is a custom exception that duplicates the functionality of the
 * DuplicateCertificateException class.
 */
public class DuplicateCertificateException extends RuntimeException {
    private final int errorCode;

    public DuplicateCertificateException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
