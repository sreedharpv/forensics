package com.which.forensics.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -297420978611106616L;

    private String emailId;
    private String password;
    private String encryptedPassword;
}
