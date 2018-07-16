package cn.enncloud.iot.iotusecim.rabbitmq.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

//@Component
public class RabbitAccept {
    private static final Logger logger = LogManager.getLogger(RabbitAccept.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "dynamic_data_queue", durable = "true"),
            exchange = @Exchange(value = "data_exchange"),
            key = "dynamic_data"))
//    key = "dynamic_data.#"))
    public  void processMessage(Message<Stream> message){
        message.getPayload();
        logger.info(message.toString());

    }
}
