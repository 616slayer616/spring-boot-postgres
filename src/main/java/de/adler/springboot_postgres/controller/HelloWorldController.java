package de.adler.springboot_postgres.controller;


import de.adler.springboot_postgres.database.entity.Customer;
import de.adler.springboot_postgres.database.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequestMapping("/")
@CrossOrigin(/*origins = "http://localhost:4200"*/)
class HelloWorldController {

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
    public ResponseEntity<List<Customer>> customer(@PathVariable String lastName) {
        List<Customer> result = customers.findByLastName(lastName);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS))
                .body(result);
    }

}