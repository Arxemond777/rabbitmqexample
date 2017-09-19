package com.upsilon_tech.java.sample.springrabbitmq.service;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ProducerServiceImpl implements ProducerService {

    /**
     *  The name of the Exchange
     */
    //private static final String EXCHANGE_NAME = "messages1";

    /**
     *  This method publishes a message
     * @param message
     */
    @Override
    public void produceMessage(String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(ConsumerServiceImpl.getExchangeName(), "fanout");

            
            channel.basicPublish(ConsumerServiceImpl.getExchangeName(), "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message  + "'");
            
            channel.close();
            //connection.close();
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } catch (TimeoutException toe) {
            System.out.println("TimeoutException : " + toe.getMessage());
            toe.printStackTrace();
        }
    }
}
