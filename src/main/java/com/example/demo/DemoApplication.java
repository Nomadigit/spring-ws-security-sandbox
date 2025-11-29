package com.example.demo;

import com.example.demo.service.WSCallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        WSCallService wsCallService = context.getBean(WSCallService.class);
        while (true) {
            try {
                wsCallService.call();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            } finally {
                System.exit(0);
            }
        }
    }
}
