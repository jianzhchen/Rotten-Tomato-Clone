package com.redwood.rottenpotato.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/movie", method = RequestMethod.POST)
public class MovieController
{
    /*@RequestMapping(value="/addMovie", method = RequestMethod.POST)
    public ResponseEntity<?> insertCustomers
    (
        @RequestParam(value="id") String CustomerId,
        @RequestParam(value="password") String CPassword,
        @RequestParam(value="lname") String LastName,
        @RequestParam(value="fname") String FirstName,
        @RequestParam(value="phone") String PhoneNumber,
        @RequestParam(value="address") String Address, @RequestParam(value="email") String Email
    )
    {

        return insertCustomers(new Customers(CustomerId,CPassword,LastName,FirstName,PhoneNumber,Address,Email));


    }*/
}
