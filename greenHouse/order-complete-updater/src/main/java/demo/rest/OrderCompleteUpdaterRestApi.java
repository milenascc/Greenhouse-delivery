package demo.rest;

import demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderCompleteUpdaterRestApi {
    
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void orderComplete(@RequestBody Order order) {
        template.convertAndSend("/topic/orders", order);
    }

    @RequestMapping(value = "/errors", method = RequestMethod.POST)
    public void orderComplete(@RequestBody String errorMessage) {
        template.convertAndSend("/topic/orders", errorMessage);
    }
}
