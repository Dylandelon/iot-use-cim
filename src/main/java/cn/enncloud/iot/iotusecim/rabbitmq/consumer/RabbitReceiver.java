package cn.enncloud.iot.iotusecim.rabbitmq.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@EnableBinding(Sink.class)
public class RabbitReceiver {

    private static final Logger logger = LogManager.getLogger(RabbitReceiver.class);

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){


        message.getPayload().replace("P0\\","").replace("\\","_");

        Resource resource = new ClassPathResource("xcm(1).csv");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long count = bufferedReader.lines().count();



        logger.info(message.getPayload());

    }
}
