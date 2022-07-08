package com.banking.accountquery.infrastructure;

import com.banking.cqrs.core.domain.BaseEntity;
import com.banking.cqrs.core.infrastructure.QueryDispatcher;
import com.banking.cqrs.core.queries.BaseQuery;
import com.banking.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type,c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers =routes.get(query.getClass());
        if (handlers == null || handlers.size() <= 0){
            throw new RuntimeException("Ningun query handler fue registrado");
        }
        if (handlers.size() > 1){
            throw new RuntimeException("No puedes registrar dos handlers a un query ");
        }
        return handlers.get(0).handle(query);
    }
}
