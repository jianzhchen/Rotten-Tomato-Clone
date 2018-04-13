package com.redwood.rottenpotato.controllers;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class HelloWorldController
{

    @RequestMapping("/hello")
    String home() {
        return "Hello World! haha";
    }

}