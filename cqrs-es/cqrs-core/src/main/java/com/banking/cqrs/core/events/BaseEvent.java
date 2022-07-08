package com.banking.cqrs.core.events;

import com.banking.cqrs.core.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder //construccion fluida de objetos que hereden
public abstract class BaseEvent extends Message {
    private int version;
}
