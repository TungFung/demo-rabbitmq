package com.example.chapter1;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Shen on 2018/9/3.
 */
public class PreBuilder {

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
//
//        Map<String, Object> exchangeArguments = new HashMap<>();
//        AMQP.Exchange.DeclareOk exchangeDeclareOk = channel.exchangeDeclare("EXCHANGE_1", BuiltinExchangeType.DIRECT, false, false, true, exchangeArguments);
//        log.info("Exchange.DeclareOk:{}", exchangeDeclareOk);
//
//        Map<String, Object> exchangeArguments2 = new HashMap<>();
//        AMQP.Exchange.DeclareOk exchangeDeclareOk2 = channel.exchangeDeclare("EXCHANGE_2", BuiltinExchangeType.DIRECT, false, false, true, exchangeArguments2);
//        log.info("Exchange.DeclareOk2:{}", exchangeDeclareOk2);
//
//        Map<String, Object> exchangeArguments3 = new HashMap<>();
//        AMQP.Exchange.DeclareOk exchangeDeclareOk3 = channel.exchangeDeclare("EXCHANGE_3", BuiltinExchangeType.DIRECT, false, false, false, exchangeArguments3);
//        log.info("Exchange.DeclareOk3:{}", exchangeDeclareOk3);
//
//        Map<String, Object> bindExchangeArguments = new HashMap<>();
//        channel.exchangeBind("EXCHANGE_2", "EXCHANGE_1", "ROUTING_KEY_1", bindExchangeArguments);
//
//        channel.exchangeBind("EXCHANGE_3", "EXCHANGE_1", "ROUTING_KEY_1");

        Map<String, Object> queueArguments = new HashMap<>();
        AMQP.Queue.DeclareOk queueDeclareOk = channel.queueDeclare("QUEUE_2", false, false, false, queueArguments);
        log.info("Queue.DeclareOk:{}", queueDeclareOk);

        Map<String, Object> queueArguments2 = new HashMap<>();
        AMQP.Queue.DeclareOk queueDeclareOk2 = channel.queueDeclare("QUEUE_3", false, false, false, queueArguments2);
        log.info("Queue.DeclareOk2:{}", queueDeclareOk2);

        AMQP.Queue.BindOk bindOk = channel.queueBind("QUEUE_2", "EXCHANGE_2", "ROUTING_KEY_1");
        log.info("BindOk.bindOk:{}", bindOk);

        AMQP.Queue.BindOk bindOk2 = channel.queueBind("QUEUE_3", "EXCHANGE_3", "ROUTING_KEY_1");
        log.info("BindOk.bindOk2:{}", bindOk2);

        channel.close();
        connection.close();
    }
}
