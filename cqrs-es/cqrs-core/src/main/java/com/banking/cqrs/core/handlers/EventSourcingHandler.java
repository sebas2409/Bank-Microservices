package com.banking.cqrs.core.handlers;

import com.banking.cqrs.core.domain.AggregateRoute;

public interface EventSourcingHandler<T>{
    void save(AggregateRoute aggregate);
    T getById(String id);
}
