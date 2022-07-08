package com.banking.accountcmd.api.command;

import com.banking.accountcommon.dto.AccountType;
import com.banking.cqrs.core.commands.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType type;
    private double openingBalance;
}
