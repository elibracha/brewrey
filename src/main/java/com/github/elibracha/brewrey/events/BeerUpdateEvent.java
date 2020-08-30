package com.github.elibracha.brewrey.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerUpdateEvent implements Serializable {
    private UUID id;
    private EventType type;
    private String content;
}
