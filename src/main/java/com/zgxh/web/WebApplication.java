package com.zgxh.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test.Test;

// @SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        Test test = new Test();
        test.getTest();
        // SpringApplication.run(WebApplication.class, args);
    }
}
