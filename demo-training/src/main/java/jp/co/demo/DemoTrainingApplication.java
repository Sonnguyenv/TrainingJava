package jp.co.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * DemoTrainingApplication
 */
@SpringBootApplication
@EnableScheduling
public class DemoTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTrainingApplication.class, args);
    }
}
