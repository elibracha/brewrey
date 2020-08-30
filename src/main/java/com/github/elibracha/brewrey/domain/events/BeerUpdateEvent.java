package com.github.elibracha.brewrey.domain.events;

import com.github.elibracha.brewrey.domain.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerUpdateEvent extends BeerEvent {
    private UUID messageId;
    private UUID beerId;
    private EventType type;
    private Beer content;
}
