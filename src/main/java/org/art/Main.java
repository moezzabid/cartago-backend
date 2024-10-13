package org.art;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages = "")
@Configuration
@ComponentScan(basePackages = {"org.art.*"})
public class Main {
    public static void main(String[] args) {
      SpringApplication.run(Main.class, args);
    }
}
