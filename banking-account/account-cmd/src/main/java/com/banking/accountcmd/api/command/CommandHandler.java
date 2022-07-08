package com.banking.accountcmd.api.command;

public interface CommandHandler {

    void handle(OpenAccountCommand command);
    void handle(DepositFundsCommand command);
    void handle(WithdrawFundsCommand command);
    void handle(CloseAccountCommnand commnand);
}
