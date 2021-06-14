package com.which.forensics.service;

import com.which.forensics.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDto getUser(String userId);
}
