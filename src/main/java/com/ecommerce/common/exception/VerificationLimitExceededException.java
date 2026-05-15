package com.ecommerce.common.exception;

public class VerificationLimitExceededException extends RuntimeException{
    public VerificationLimitExceededException(String message)
    {
        super(message);
    }
}
