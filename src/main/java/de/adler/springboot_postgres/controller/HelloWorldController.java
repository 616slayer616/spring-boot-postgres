package de.adler.springboot_postgres.controller;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Service
@RequestMapping("/")
@CrossOrigin()
class HelloWorldController {

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity hello() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body("Hallol");
    }
}