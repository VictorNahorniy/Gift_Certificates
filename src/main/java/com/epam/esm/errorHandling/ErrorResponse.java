package com.epam.esm.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class {@code ErrorResponse} is used to represent error response.
 */
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private int errorCode;
}
