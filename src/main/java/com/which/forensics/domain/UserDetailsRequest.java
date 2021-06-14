package com.which.forensics.domain;

import lombok.Data;

@Data
public class UserDetailsRequest {
    private String emailId;
    private String password;
}
