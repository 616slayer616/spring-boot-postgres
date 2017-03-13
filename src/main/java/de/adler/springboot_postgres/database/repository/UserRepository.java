package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}