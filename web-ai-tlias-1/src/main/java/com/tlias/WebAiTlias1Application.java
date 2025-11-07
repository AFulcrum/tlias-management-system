package com.tlias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebAiTlias1Application {

    public static void main(String[] args) {
        SpringApplication.run(WebAiTlias1Application.class, args);
    }

}
