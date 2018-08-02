package cn.enncloud.iot.iotusecim.rabbitmq.consumer;

import cn.enncloud.iot.iotusecim.service.ICimService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@EnableBinding(Sink.class)
public class RabbitReceiver {

    private static final Logger logger = LogManager.getLogger(RabbitReceiver.class);

    @Autowired
    private ICimService cimService;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        if(logger.isInfoEnabled()){
            logger.info(message);
        }

        Optional<String> payLoad = Optional.ofNullable(message.getPayload());
        if(payLoad.isPresent()){
            cimService.doPoceess(payLoad.get());

        }

    }
}
