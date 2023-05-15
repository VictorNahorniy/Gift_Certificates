package com.epam.esm.errorHandling.customException.Tag;

/**
 * This class is a custom exception for the case when tag is duplicating while insert into database.
 */
public class DuplicateTagException extends RuntimeException {
    private final int errorCode;

    public DuplicateTagException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
