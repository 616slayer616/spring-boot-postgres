package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends Repository<Customer, Long> {

    Customer save(Customer entity);

    void delete(Customer entity);

    Customer findByLastName(String lastName);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.lastName) = LOWER(:lastName)")
    Customer findByLastNameCaseInsensitive(@Param("lastName") String lastName);
}