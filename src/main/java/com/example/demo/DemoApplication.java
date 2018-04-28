package com.example.demo;

import com.example.demo.utils.Receiver;
import com.example.demo.utils.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sender.run();
        receiver.run();
    }
}
