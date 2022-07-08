package com.banking.accountcmd.infrastructure;

import com.banking.accountcmd.domain.AccountAggregate;
import com.banking.cqrs.core.domain.AggregateRoute;
import com.banking.cqrs.core.events.BaseEvent;
import com.banking.cqrs.core.handlers.EventSourcingHandler;
import com.banking.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoute aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommited();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvent(id);
        if (events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }
}
