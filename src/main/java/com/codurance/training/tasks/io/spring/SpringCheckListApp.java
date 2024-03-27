package com.codurance.training.tasks.io.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

@ComponentScan(basePackages = {"com.codurance.training.tasks"})
@EntityScan(basePackages={"com.codurance.training.tasks"})
@SpringBootApplication
public class SpringCheckListApp extends SpringBootServletInitializer implements CommandLineRunner {
    public static final String CHECK_LIST_ID = "1";

    public SpringCheckListApp() {
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringCheckListApp.class);
    }

    public static void main(String[] args){
        SpringApplication.run(SpringCheckListApp.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("CheckListApp runs");
    }
}
