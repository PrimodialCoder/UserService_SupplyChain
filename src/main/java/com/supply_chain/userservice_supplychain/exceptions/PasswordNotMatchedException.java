package com.supply_chain.userservice_supplychain.exceptions;

public class PasswordNotMatchedException extends RuntimeException {
  public PasswordNotMatchedException(String message) {
    super(message);
  }
}
