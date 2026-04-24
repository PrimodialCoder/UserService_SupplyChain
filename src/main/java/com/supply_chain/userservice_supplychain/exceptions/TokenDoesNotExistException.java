package com.supply_chain.userservice_supplychain.exceptions;

public class TokenDoesNotExistException extends RuntimeException {
    public TokenDoesNotExistException(String message) {
        super(message);
    }
}
