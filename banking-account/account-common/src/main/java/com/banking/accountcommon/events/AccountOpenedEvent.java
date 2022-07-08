package com.banking.accountcommon.events;

import com.banking.accountcommon.dto.AccountType;
import com.banking.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AccountType type;
    private Date createdDate;
    private double openingBalance;
}
