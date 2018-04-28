package com.example.demo.utils;

import com.example.demo.common.RabbitMQ;
import com.rabbitmq.client.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Receiver {
    Logger log = LogManager.getLogger(Receiver.class);

    public void run() throws IOException, TimeoutException {

        Consumer consumer = new DefaultConsumer(RabbitMQ.getChannel()) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                log.info("received : " + message);
            }
        };
        RabbitMQ.basicConsume(consumer);
    }



















}
