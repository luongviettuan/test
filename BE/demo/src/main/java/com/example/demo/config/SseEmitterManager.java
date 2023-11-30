package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SseEmitterManager {
    private static final Logger logger = LoggerFactory.getLogger(SseEmitterManager.class.getName());
    private static final Map<String, SseEmitter> emitters = new HashMap<>();

    public static void addEmitter(String subscriberId, SseEmitter emitter) {
        emitters.put(subscriberId, emitter);
    }

    public static void removeEmitter(String subscriberId) {
        emitters.remove(subscriberId);
    }

    public static void sendSseEventToClients(String subscriberId, String data) {
        var emitter = emitters.get(subscriberId);
        if (emitter == null) {
            logger.debug("No client with subscriber Id " + subscriberId + " found!");
            return;
        }
        try {
            emitter.send(data);
        } catch (IOException e) {
            logger.debug("Error sending event to client: " + e.getMessage());
        }
    }
}
