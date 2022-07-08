package com.banking.accountcmd.api.command;

import com.banking.cqrs.core.commands.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloseAccountCommnand extends BaseCommand {
    public CloseAccountCommnand(String id){
        super(id);
    }
}
