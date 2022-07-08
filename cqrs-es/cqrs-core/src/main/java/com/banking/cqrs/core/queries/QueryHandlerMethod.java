package com.banking.cqrs.core.queries;

import com.banking.cqrs.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery>{
    List<BaseEntity> handle (T query);
}
