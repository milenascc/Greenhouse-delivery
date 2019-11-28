package demo.rest;

import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketApi {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(WebSocketApi.class);

    @MessageMapping("/sendMessage")
    @SendTo("/topic/orders")
    public String sendMessage(String message) throws Exception {
        log.info("Message received: " + message);
        return message;
    }
}
