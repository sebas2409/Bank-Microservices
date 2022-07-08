package com.banking.accountquery.infrastructure.consumers;

import com.banking.accountcommon.events.AccountClosedEvent;
import com.banking.accountcommon.events.AccountOpenedEvent;
import com.banking.accountcommon.events.FundsDepositedEvent;
import com.banking.accountcommon.events.FundsWithdrawnEvent;
import com.banking.accountquery.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer{

    @Autowired
    private EventHandler eventHandler;


    @KafkaListener(topics = "AccountOpenedEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountOpenedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundsDepositedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsWithdrawnEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundsWithdrawnEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountClosedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
