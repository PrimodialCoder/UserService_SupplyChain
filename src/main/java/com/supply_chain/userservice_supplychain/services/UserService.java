package com.supply_chain.userservice_supplychain.services;

import com.supply_chain.userservice_supplychain.models.Token;
import com.supply_chain.userservice_supplychain.models.User;
import org.hibernate.dialect.BooleanDecoder;
import org.springframework.stereotype.Service;

public interface UserService {
    User signup(String name, String email, String password);
    Token login(String email, String password);
    void logout(String token);
    User validateToken(String tokenValue);
}
