package com.supply_chain.userservice_supplychain.controllers;

import com.supply_chain.userservice_supplychain.dtos.*;
import com.supply_chain.userservice_supplychain.models.Token;
import com.supply_chain.userservice_supplychain.models.User;
import com.supply_chain.userservice_supplychain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto  signUpRequestDto) {
        User newSavedUser = userService.signup(
                signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.from(newSavedUser)) ;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Token generatedToken = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        LoginResponseDto responseDto =  new LoginResponseDto();
        responseDto.setTokenValue(generatedToken.getTokenValue());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        return null;
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String tokenValue) {
        return null;
    }
}
