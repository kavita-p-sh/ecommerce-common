package com.ecommerce.common.exception;

public class IpBlockedException extends RuntimeException{

    public IpBlockedException(String message)
    {
        super(message);
    }

}
