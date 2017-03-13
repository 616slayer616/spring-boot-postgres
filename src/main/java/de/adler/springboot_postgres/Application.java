package de.adler.springboot_postgres;

import de.adler.springboot_postgres.database.entity.User;
import de.adler.springboot_postgres.database.entity.UserRole;
import de.adler.springboot_postgres.database.repository.UserRepository;
import de.adler.springboot_postgres.database.repository.UserRoleRepository;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SuppressWarnings("WeakerAccess")
@SpringBootApplication
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Application {

    private static final Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("setup")
    public CommandLineRunner demo(UserRepository userRepo, UserRoleRepository roleRepo) {
        return (args) -> {
            User savedUser = userRepo.save(new User("user", "password", "email1", true));
            roleRepo.save(new UserRole(savedUser.getUserid(), "USER"));
            User savedAdmin = userRepo.save(new User("admin", "admin", "email2", true));
            roleRepo.save(new UserRole(savedAdmin.getUserid(), "ADMIN"));
        };
    }
}