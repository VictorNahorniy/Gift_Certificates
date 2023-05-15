package com.epam.esm.errorHandling.customException.Ceritficate;

/**
 * This class is a custom exception that is thrown when wrong
 * parameter is given for sorting.
 */
public class WrongSortParamException extends RuntimeException {
    private final int errorCode;

    public WrongSortParamException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
