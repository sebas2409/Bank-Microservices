package com.banking.accountquery.api.queries;

import com.banking.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
    private String accounHolder;

}
