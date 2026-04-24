package com.supply_chain.userservice_supplychain.repositories;

import com.supply_chain.userservice_supplychain.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Optional<Token> findByTokenValue(String tokenValue);
    //this will return the token if all three conditions are satisfied: the token value matches, the token is not marked as deleted, and the token has not expired.
    Optional<Token> findByTokenValueAndDeletedAndExpiryAtGreaterThan(String tokenValue, Boolean deleted, Date expiryAt);


}
