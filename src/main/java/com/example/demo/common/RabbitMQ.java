package com.example.demo.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {

private static Logger log = LogManager.getLogger(RabbitMQ.class);


    public static Channel channel = null;
    private static ConnectionFactory factory = null;
    private static Connection connection = null;

    private static final String QUEUE_NAME = "demo";
    private static final String QUEUE_HOST = "127.0.0.1";
    private static final int QUEUE_PORT = 5672;
    private static final String QUEUE_USER = "admin";
    private static final String QUEUE_PWD = "admin";

    private RabbitMQ() { }

    public static Channel getChannel() throws IOException, TimeoutException {
        if (channel == null) {
            log.info("init a channel.");
            initChannel();
        }
        return channel;
    }

    public static void basicConsume(Consumer consumer) throws IOException {
        channel.basicConsume(QUEUE_NAME, true, consumer);
    };

    public static void basicPublish (Channel channel, String message) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    }

    public static void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    private static ConnectionFactory initFactory() {
        factory = new ConnectionFactory();
        factory.setHost(QUEUE_HOST);
        factory.setPort(QUEUE_PORT);
        factory.setUsername(QUEUE_USER);
        factory.setPassword(QUEUE_PWD);
        return factory;
    }

    private static Channel initChannel() throws IOException, TimeoutException {
        if (factory == null) {
            initFactory();
        }
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        return channel;
    }

















}
