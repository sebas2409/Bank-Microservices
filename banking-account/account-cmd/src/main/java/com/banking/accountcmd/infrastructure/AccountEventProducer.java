package com.banking.accountcmd.infrastructure;

import com.banking.cqrs.core.events.BaseEvent;
import com.banking.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String,Object> template;

    @Override
    public void produce(String topic, BaseEvent event) {
            this.template.send(topic,event);
    }
}
