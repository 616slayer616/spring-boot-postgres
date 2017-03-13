package de.adler.springboot_postgres.controller;

import de.adler.springboot_postgres.database.entity.Customer;
import de.adler.springboot_postgres.database.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequestMapping("customer")
@CrossOrigin()
public class CustomerController {

    @SuppressWarnings("CanBeFinal")
    @Autowired
    private CustomerRepository customers;

    @ResponseBody
    @RequestMapping(value = "{lastName}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@PathVariable String lastName) {
        List<Customer> result = customers.findByLastName(lastName);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS))
                .body(result);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer newCustomer) {

        try {
            Customer result = customers.save(newCustomer);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newCustomer.getLastName()).toUri();
            return ResponseEntity.created(location)
                    .cacheControl(CacheControl.noCache())
                    .body(result);
        } catch (DataIntegrityViolationException e) { // TODO: JpaSystemException ???
            //e.printStackTrace();
        }

        return ResponseEntity.badRequest()
                .cacheControl(CacheControl.noCache()).body(null);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer customer) {
        customers.delete(customer);

        return ResponseEntity.accepted()
                .cacheControl(CacheControl.noCache()).body(null);
    }

}