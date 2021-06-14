package com.which.forensics.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Autowired
    Environment env;

    public final String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }
}
