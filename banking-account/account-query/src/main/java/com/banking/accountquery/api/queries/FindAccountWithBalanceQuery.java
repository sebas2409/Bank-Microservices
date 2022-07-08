package com.banking.accountquery.api.queries;

import com.banking.accountquery.api.dto.EqualityType;
import com.banking.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FindAccountWithBalanceQuery extends BaseQuery {
    private double balance;
    private EqualityType equalityType;

}
