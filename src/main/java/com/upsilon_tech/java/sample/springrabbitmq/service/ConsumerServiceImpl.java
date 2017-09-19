package com.upsilon_tech.java.sample.springrabbitmq.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    /**
     * The name of the exchange.
     */
    private static final String EXCHANGE_NAME = "message1";

    public static String getExchangeName() {
        return EXCHANGE_NAME;
    }

    /**
     * The function that consumes messages from the broker(RabbitMQ)
     *
     * @param data
     */
    @Override
    @RabbitListener(bindings =
        @QueueBinding(value = @Queue(), exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.FANOUT))
    )
    public void consumerMessage(byte[] data) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String consumedMessage = new String(data);
        System.out.println(" [x] Consumed  '" + consumedMessage + "'" + new Date() + System.getProperty("line.separator"));
    }
}
