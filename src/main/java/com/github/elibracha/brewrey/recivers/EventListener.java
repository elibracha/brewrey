package com.github.elibracha.brewrey.recivers;

import com.github.elibracha.brewrey.config.JmsConfig;
import com.github.elibracha.brewrey.domain.events.BeerCreatedEvent;
import com.github.elibracha.brewrey.domain.events.BeerUpdateEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @JmsListener(destination = JmsConfig.UPDATE_TOPIC)
    public void receiveUpdateMessage(BeerUpdateEvent event) {
        System.out.println("Received Update:" + event);
    }

    @JmsListener(destination = JmsConfig.CREATE_TOPIC)
    public void receiveCreateMessage(BeerCreatedEvent event) {
        System.out.println("Received Create:" + event);
    }

}
