package com.banking.accountquery.api.queries;

import com.banking.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
    private String id;
}
