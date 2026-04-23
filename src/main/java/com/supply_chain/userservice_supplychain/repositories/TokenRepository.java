package com.supply_chain.userservice_supplychain.repositories;

import com.supply_chain.userservice_supplychain.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Token findByTokenValue(String tokenValue);
}
