package com.which.forensics.service.impl;

import com.which.forensics.dto.UserDto;
import com.which.forensics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto getUser(String userId) {
        return getUserDto();
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setEmailId("my email Id");
        userDto.setPassword("my password");
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode("my password"));
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //get user details from db
        UserDto userDto = getUserDto();
        if (userDto == null) throw new UsernameNotFoundException(email);

        User user = new User(userDto.getEmailId(), userDto.getEncryptedPassword(), new ArrayList<>());
        return user;
    }
}
