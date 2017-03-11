package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author adler
 * @version 01.03.2017
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    @Query("SELECT p FROM Customer p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    List<Customer> findByLastNameCaseInsensitive(@Param("lastName") String lastName);
}