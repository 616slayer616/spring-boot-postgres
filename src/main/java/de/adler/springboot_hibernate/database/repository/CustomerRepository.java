package de.adler.springboot_hibernate.database.repository;

import de.adler.springboot_hibernate.database.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author adler
 * @version 01.03.2017
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}