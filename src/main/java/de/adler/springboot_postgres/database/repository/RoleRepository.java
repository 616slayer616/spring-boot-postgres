package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
}