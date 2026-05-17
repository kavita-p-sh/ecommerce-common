package com.ecommerce.common.exception;

/**
 * Exception thrown when a downstream service is unreachable or connection fails.
 */
public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message) {
        super(message);
    }
}