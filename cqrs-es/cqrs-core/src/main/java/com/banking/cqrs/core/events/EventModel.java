package com.banking.cqrs.core.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private String id;
    private Date timeStamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int aggregateVersion;
    private String eventType;
    private BaseEvent eventData;
}
