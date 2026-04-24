package com.supply_chain.userservice_supplychain.services;

import com.supply_chain.userservice_supplychain.exceptions.PasswordNotMatchedException;
import com.supply_chain.userservice_supplychain.exceptions.TokenDoesNotExistException;
import com.supply_chain.userservice_supplychain.exceptions.UserAlreadyExistException;
import com.supply_chain.userservice_supplychain.exceptions.UserNotFoundException;
import com.supply_chain.userservice_supplychain.models.Token;
import com.supply_chain.userservice_supplychain.models.User;
import com.supply_chain.userservice_supplychain.repositories.TokenRepository;
import com.supply_chain.userservice_supplychain.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    TokenRepository tokenRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository,  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signup(String name, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("User with email already exists");
        }
        User user =  new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email : " +email+ "not found");
        }
        User  user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchedException("Password does not match");
        }
        Token token = new Token();
        token.setUser(user);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date date = calendar.getTime();

        token.setExpiryAt(date);
        return tokenRepository.save(token);
    }

    @Override
    public void logout(String token) {
        Token tokenFromDb = tokenRepository.findByTokenValue(token).orElseThrow(() -> new UserNotFoundException("User with Token not found"));
        tokenFromDb.setDeleted(true);
        tokenRepository.save(tokenFromDb);
    }

    @Override
    public User validateToken(String tokenValue) {
        /*
        * 1. Exists in DB
        * 2. Not deleted
        * 3. Not expired
        * */
        Optional<Token> tokenOptional = tokenRepository.findByTokenValueAndDeletedAndExpiryAtGreaterThan(tokenValue, false, new Date());
        if(tokenOptional.isEmpty()) {
            throw new TokenDoesNotExistException("Token does not exist");
        }
        Token token = tokenOptional.get();
        return token.getUser();
    }
}
