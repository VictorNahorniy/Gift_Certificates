package com.epam.esm.errorHandling.customException.Tag;

/**
 * This class is a custom exception for the case when a tag is not found in the database.
 */
public class TagNotFoundException extends RuntimeException {
    private final int errorCode;

    public TagNotFoundException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
