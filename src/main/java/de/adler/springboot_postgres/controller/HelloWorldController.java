package de.adler.springboot_postgres.controller;


import de.adler.springboot_postgres.database.entity.Customer;
import de.adler.springboot_postgres.database.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequestMapping("/")
@CrossOrigin(/*origins = "http://localhost:4200"*/)
public class HelloWorldController {

    private static final Logger log = Logger.getLogger(HelloWorldController.class);

    @SuppressWarnings("CanBeFinal")
    @Autowired
    private CustomerRepository customers;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity hello() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body("Hallol");
    }

    @ResponseBody
    @RequestMapping(value = "customer/{lastName}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@PathVariable String lastName) {
        List<Customer> result = customers.findByLastName(lastName);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS))
                .body(result);
    }

    @ResponseBody
    @RequestMapping(value = "customer", method = RequestMethod.PUT)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer newCustomer) {

        try {
            Customer result = customers.save(newCustomer);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newCustomer.getLastName()).toUri();
            return ResponseEntity.created(location)
                    .cacheControl(CacheControl.noCache())
                    .body(result);
        } catch (JpaSystemException e) {
            //e.printStackTrace();
        }

        return ResponseEntity.badRequest()
                .cacheControl(CacheControl.noCache()).body(null);
    }

    @ResponseBody
    @RequestMapping(value = "customer", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer newCustomer) {
        customers.delete(newCustomer);

        return ResponseEntity.accepted()
                .cacheControl(CacheControl.noCache()).body(null);
    }

}