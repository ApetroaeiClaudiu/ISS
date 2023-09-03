package com.claudiu.proiect;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.claudiu.proiect.domain")
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class,args);

    }
}
