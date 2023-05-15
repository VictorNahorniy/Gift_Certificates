package com.epam.esm.errorHandling.customException.Ceritficate;

/**
 * This class is a custom exception that is thrown when a certificate is not found.
 */
public class CertificateNotFoundException extends RuntimeException {
    private final int errorCode;

    public CertificateNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
