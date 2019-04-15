package com.gmail.kaminski.viktar.weektwo.repository.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfiguration {
    @Value("${database.url}")
    private String url;
    @Value("${database.username:}")
    private String username;
    @Value("${database.password:}")
    private String password;
    @Value("${database.driver}")
    private String driver;
    @Value("${database.unicode}")
    private String unicode;
    @Value("${database.character.encoding}")
    private String encoding;
    @Value("${database.server.timezone}")
    private String timezone;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public String getUnicode() {
        return unicode;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getTimezone() {
        return timezone;
    }
}
