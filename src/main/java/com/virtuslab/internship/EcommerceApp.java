package com.virtuslab.internship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
