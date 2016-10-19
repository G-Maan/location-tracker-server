package com.mielniczuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Pawel Mielniczuk on 2016-10-03.
 */
@Controller
@SpringBootApplication
public class SpringBootAppliaction {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    public static void main(String [] args){
        SpringApplication.run(SpringBootAppliaction.class, args);
    }
}
