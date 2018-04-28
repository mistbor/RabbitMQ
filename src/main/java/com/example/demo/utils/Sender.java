package com.example.demo.utils;

import com.example.demo.common.RabbitMQ;
import com.rabbitmq.client.Channel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Sender {
    Logger log = LogManager.getLogger(Sender.class);

    public void run() throws IOException, TimeoutException {
        String message = "hello world~";
        RabbitMQ.basicPublish(RabbitMQ.getChannel(), message);
        log.info("send : " + message);
        // RabbitMQ.close();
    }
}
