package com.banking.cqrs.core.commands;

import com.banking.cqrs.core.messages.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseCommand extends Message {

    public BaseCommand(String id){
        super(id);
    }
}
