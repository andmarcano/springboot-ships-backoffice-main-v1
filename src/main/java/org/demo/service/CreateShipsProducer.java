package org.demo.service;

import org.demo.dto.MovieSpaceShipsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CreateShipsProducer {

    private static final Logger log = LoggerFactory.getLogger(CreateShipsProducer.class);

    private final KafkaTemplate<String, MovieSpaceShipsDto> createOrderKafkaTemplate;

    private final String createOrderTopic;

    public CreateShipsProducer(KafkaTemplate<String, MovieSpaceShipsDto> createOrderKafkaTemplate,
                               @Value("${spring.kafka.order.topic.create-order}") String createOrderTopic) {
        this.createOrderKafkaTemplate = createOrderKafkaTemplate;
        this.createOrderTopic = createOrderTopic;
    }

    public boolean sendCreateOrderEvent(MovieSpaceShipsDto order) throws ExecutionException, InterruptedException {
        SendResult<String, MovieSpaceShipsDto> sendResult = createOrderKafkaTemplate.send(createOrderTopic, order).get();
        log.info("Create order {} event sent via Kafka", order);
        log.info(sendResult.toString());
        return true;
    }
}
