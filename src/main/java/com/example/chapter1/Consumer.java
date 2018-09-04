package com.example.chapter1;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Consumer {

    private static Logger log = LoggerFactory.getLogger(Consumer.class);

    private static final String EXCHANGE_NAME = "ERIC_EXCHANGE";

    private static final String QUEUE_NAME = "ERIC_QUEUE";

    private static final String ROUTING_KEY = "ERIC_ROUTING_KEY";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        connectionFactory.setHost("10.16.18.141");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/vhost");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.basicConsume(QUEUE_NAME, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("consumerTag:{}", consumerTag);
                log.info("envelope:{}", envelope);
                log.info("properties:{}", properties);
                log.info(new String(body, "UTF-8"));
            }
        });

        channel.close();
        connection.close();
    }
}
