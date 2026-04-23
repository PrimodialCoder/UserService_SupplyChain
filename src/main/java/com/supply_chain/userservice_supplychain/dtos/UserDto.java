package com.supply_chain.userservice_supplychain.dtos;

import com.supply_chain.userservice_supplychain.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private String password;


    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;    
    }
}
//acts as signup response dto, and user dto