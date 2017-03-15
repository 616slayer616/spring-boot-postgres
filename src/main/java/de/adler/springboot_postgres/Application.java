package de.adler.springboot_postgres;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SuppressWarnings("WeakerAccess")
@EnableAsync
@SpringBootApplication
public class Application {

    private static final Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}