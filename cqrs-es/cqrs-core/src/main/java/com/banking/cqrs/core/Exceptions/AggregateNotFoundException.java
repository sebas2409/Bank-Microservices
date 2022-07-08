package com.banking.cqrs.core.Exceptions;

public class AggregateNotFoundException extends RuntimeException{
    public AggregateNotFoundException(String mensaje){
        super(mensaje);
    }
}
