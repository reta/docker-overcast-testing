package com.example.overcast;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppStarter {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppConfiguration.class)
            .web(false)
            .run(args);
    }
}
