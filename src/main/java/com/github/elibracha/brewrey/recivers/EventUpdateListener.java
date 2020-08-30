package com.github.elibracha.brewrey.recivers;

import com.github.elibracha.brewrey.config.JmsConfig;
import com.github.elibracha.brewrey.events.BeerUpdateEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventUpdateListener {

    @JmsListener(destination = JmsConfig.QUEUE)
    public void receiveMessage(BeerUpdateEvent event) {
        System.out.println("Received :" + event);
    }

}
