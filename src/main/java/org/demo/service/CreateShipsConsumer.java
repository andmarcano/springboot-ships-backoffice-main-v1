package org.demo.service;
import org.demo.dto.MovieSpaceShipsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service("NotificationService")
public class CreateShipsConsumer {

    private static final Logger log = LoggerFactory.getLogger(CreateShipsConsumer.class);

    @KafkaListener(topics = "${spring.kafka.order.topic.create-order}", containerFactory="NotificationContainerFactory")
    public void createOrderListener(@Payload MovieSpaceShipsDto order, Acknowledgment ack) {
        log.info("Notification service received order {} ", order);
        ack.acknowledge();
    }
}
