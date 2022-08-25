package com.ldt.BusService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
@SpringBootApplication
public class BusServiceApplication{
    public static void main (String[] args) throws IOException{
        SpringApplication.run(BusServiceApplication.class,args);
        System.out.println("hello");
    }
}
