package com.which.forensics.security;


import com.which.forensics.SpringApplicationContext;

public class SecurityConstant {
    public static final String SIGN_UP_URL = "/users";
    public static final long EXPIRATION_DURATION = 1 * 24 * 60 * 60 * 1000;
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer: ";

    public static String getTokenSecret() {
        AppProperties properties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return properties.getTokenSecret();
    }
}
