package com.example.chapter1;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Provider {

    private static Logger log = LoggerFactory.getLogger(Provider.class);

    private static final String USER_NAME = "root";

    private static final String PASS_WORD = "root";

    private static final String EXCHANGE_NAME = "ERIC_EXCHANGE";

    private static final String QUEUE_NAME = "ERIC_QUEUE";

    private static final String ROUTING_KEY = "ERIC_ROUTING_KEY";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASS_WORD);
        connectionFactory.setHost("192.168.1.11");
        connectionFactory.setPort(ConnectionFactory.DEFAULT_AMQP_PORT);
        connectionFactory.setVirtualHost("vhost");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        Map<String, Object> exchangeArguments = new HashMap<>();
        AMQP.Exchange.DeclareOk exchangeDeclareOk = channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, false, false, false, exchangeArguments);
        log.info("Exchange.DeclareOk:{}", exchangeDeclareOk);

        Map<String, Object> queueArguments = new HashMap<>();
        AMQP.Queue.DeclareOk queueDeclareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, queueArguments);
        log.info("Queue.DeclareOk:{}", queueDeclareOk);

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, false , false, basicProperties, "Hello World".getBytes());

        channel.close();
        connection.close();
    }
}
