package com.example.chapter1;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Provider {

    private static Logger log = LoggerFactory.getLogger(Provider.class);

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

        Map<String, Object> exchangeArguments = new HashMap<>();
        AMQP.Exchange.DeclareOk exchangeDeclareOk = channel.exchangeDeclare("EXCHANGE_1", BuiltinExchangeType.DIRECT, false, false, true, exchangeArguments);
        log.info("Exchange.DeclareOk:{}", exchangeDeclareOk);

        Map<String, Object> exchangeArguments2 = new HashMap<>();
        AMQP.Exchange.DeclareOk exchangeDeclareOk2 = channel.exchangeDeclare("EXCHANGE_2", BuiltinExchangeType.DIRECT, false, false, true, exchangeArguments2);
        log.info("Exchange.DeclareOk2:{}", exchangeDeclareOk2);

        Map<String, Object> exchangeArguments3 = new HashMap<>();
        AMQP.Exchange.DeclareOk exchangeDeclareOk3 = channel.exchangeDeclare("EXCHANGE_3", BuiltinExchangeType.DIRECT, false, false, false, exchangeArguments3);
        log.info("Exchange.DeclareOk3:{}", exchangeDeclareOk3);

        Map<String, Object> bindExchangeArguments = new HashMap<>();
        channel.exchangeBind("EXCHANGE_2", "EXCHANGE_1", "ROUTING_KEY_1", bindExchangeArguments);

        channel.exchangeBind("EXCHANGE_3", "EXCHANGE_1", "ROUTING_KEY_1");

//        Map<String, Object> queueArguments = new HashMap<>();
//        AMQP.Queue.DeclareOk queueDeclareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, queueArguments);
//        log.info("Queue.DeclareOk:{}", queueDeclareOk);
//
//        Map<String, Object> bindArguments = new HashMap<>();
//        AMQP.Queue.BindOk bindOk = channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, bindArguments);
//        log.info("BindOk.bindOk:{}", bindOk);

//        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
//        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, false , false, basicProperties, "Hello World".getBytes());

        channel.close();
        connection.close();
    }
}
