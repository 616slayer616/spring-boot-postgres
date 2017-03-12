package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends Repository<Customer, Long> {

    Customer save(Customer entity);

    void delete(Customer entity);

    List<Customer> findByLastName(String lastName);

    @Query("SELECT p FROM Customer p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    List<Customer> findByLastNameCaseInsensitive(@Param("lastName") String lastName);
}