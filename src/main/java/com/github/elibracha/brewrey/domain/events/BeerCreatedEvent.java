package com.github.elibracha.brewrey.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerCreatedEvent extends BeerEvent {
    private UUID id;
    private EventType type;
    private String content;
}
