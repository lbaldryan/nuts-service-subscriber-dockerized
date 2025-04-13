package org.example.nutssubscriber;

import org.example.nutssubscriber.api.MessageSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NutsSubscriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutsSubscriberApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MessageSender sender) {
        return args -> {
            sender.send("Automate - message sending from RabbitMQ");
        };
    }
}
