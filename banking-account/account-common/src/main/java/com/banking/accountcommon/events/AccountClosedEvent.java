package com.banking.accountcommon.events;

import com.banking.cqrs.core.events.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountClosedEvent extends BaseEvent {
}
