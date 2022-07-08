package com.banking.accountquery.infrastructure.consumers;

import com.banking.accountcommon.events.AccountClosedEvent;
import com.banking.accountcommon.events.AccountOpenedEvent;
import com.banking.accountcommon.events.FundsDepositedEvent;
import com.banking.accountcommon.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);

}
