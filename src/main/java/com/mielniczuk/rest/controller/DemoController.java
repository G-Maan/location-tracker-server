package com.mielniczuk.rest.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Pawel Mielniczuk on 2016-10-03.
 */
@RestController
public class DemoController {
    @CrossOrigin("*")
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") final String name){
        System.out.println("\n\n\n\n\nHello " + name);
        return "Hello: " + name;
    }

}
