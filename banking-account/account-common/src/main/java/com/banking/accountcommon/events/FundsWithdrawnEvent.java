package com.banking.accountcommon.events;

import com.banking.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FundsWithdrawnEvent extends BaseEvent {
    private double amount;
}
