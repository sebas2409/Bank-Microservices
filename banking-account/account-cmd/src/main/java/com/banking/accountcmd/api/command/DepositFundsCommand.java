package com.banking.accountcmd.api.command;

import com.banking.cqrs.core.commands.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
