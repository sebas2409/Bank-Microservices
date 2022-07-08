package com.banking.accountquery.infrastructure.handlers;


import com.banking.accountcommon.events.AccountClosedEvent;
import com.banking.accountcommon.events.AccountOpenedEvent;
import com.banking.accountcommon.events.FundsDepositedEvent;
import com.banking.accountcommon.events.FundsWithdrawnEvent;

public interface EventHandler {

    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
