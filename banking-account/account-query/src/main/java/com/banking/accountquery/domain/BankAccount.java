package com.banking.accountquery.domain;

import com.banking.accountcommon.dto.AccountType;
import com.banking.cqrs.core.domain.BaseEntity;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankAccount extends BaseEntity {

    @Id
    private String Id;
    private String accountHolder;
    private Date creationDate;
    private AccountType accountType;
    private double balance;
}
