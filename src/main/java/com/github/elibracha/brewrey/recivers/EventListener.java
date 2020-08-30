package com.github.elibracha.brewrey.recivers;

import com.github.elibracha.brewrey.config.JmsConfig;
import com.github.elibracha.brewrey.domain.events.BeerEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @JmsListener(destination = JmsConfig.UPDATE_TOPIC)
    public void receiveUpdateMessage(BeerEvent event) {
        System.out.println("Received Update:" + event);
    }

    @JmsListener(destination = JmsConfig.CREATE_TOPIC)
    public void receiveCreateMessage(BeerEvent event) {
        System.out.println("Received Create:" + event);
    }

}
