package com.ecommerce.common.exception;

/**
 * Exception thrown when a downstream service returns a server-side error.
 */
public class UpstreamServerException extends RuntimeException {

    public UpstreamServerException(String message) {
        super(message);
    }
}