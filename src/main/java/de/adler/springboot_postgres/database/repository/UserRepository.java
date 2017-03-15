package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User findByEmail(String email);

    User save(User entity);
}