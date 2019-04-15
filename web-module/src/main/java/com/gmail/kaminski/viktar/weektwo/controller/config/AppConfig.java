package com.gmail.kaminski.viktar.weektwo.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.gmail.kaminski.viktar.weektwo.controller",
        "com.gmail.kaminski.viktar.weektwo.service",
        "com.gmail.kaminski.viktar.weektwo.repository"
})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
public class AppConfig {
}
