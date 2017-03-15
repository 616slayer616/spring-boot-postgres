package de.adler.springboot_postgres;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SuppressWarnings("WeakerAccess")
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Application {

    private static final Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}