package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

public class SseEmitterManager {
    private static final Logger logger = LoggerFactory.getLogger(SseEmitterManager.class.getName());
    private static final Map<String, List<SseEmitter>> emitters = new HashMap<>();

    public static void addEmitter(String subscriberId, SseEmitter emitter) {
//        UUID uuid = UUID.randomUUID();
////        emitters.put(subscriberId, emitter);
        List<SseEmitter> emitterList = emitters.get(subscriberId);
        if(emitterList == null) {
            emitterList = new ArrayList<SseEmitter>();
        }
        emitterList.add(emitter);
        emitters.put(subscriberId, emitterList);
    }

    public static void removeEmitter(String subscriberId) {
        emitters.remove(subscriberId);
    }

    public static void sendSseEventToClients(String subscriberId, String data) {
        var emitterList = emitters.get(subscriberId);

        if (emitterList == null || emitterList.size() == 0) {
            logger.debug("No client with subscriber Id " + subscriberId + " found!");
            return;
        }
        try {
//            emitter.send(data);
            for (SseEmitter emitter: emitterList) {
                emitter.send(data);
            }
        } catch (IOException e) {
            logger.debug("Error sending event to client: " + e.getMessage());
        }
    }
}
