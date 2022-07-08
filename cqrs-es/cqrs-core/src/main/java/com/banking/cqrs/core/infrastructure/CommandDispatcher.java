package com.banking.cqrs.core.infrastructure;

import com.banking.cqrs.core.commands.BaseCommand;
import com.banking.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
