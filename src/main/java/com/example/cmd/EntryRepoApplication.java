package com.example.cmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
public class EntryRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntryRepoApplication.class, args);
    }
}