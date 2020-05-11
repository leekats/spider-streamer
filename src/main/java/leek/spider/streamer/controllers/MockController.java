package leek.spider.streamer.controllers;

import leek.spider.streamer.modules.SpiderEvent;
import leek.spider.streamer.modules.enums.Indication;
import leek.spider.streamer.modules.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/mock")
@RestController
public class MockController {

    @Autowired
    private KafkaTemplate<String, SpiderEvent> kafkaTemplate;

    @GetMapping
    public String shmocker() {
        final SpiderEvent event = new SpiderEvent("1", "eventname", Type.FACE, Indication.Green);
        kafkaTemplate.send("streamer", event);
        return "good";
    }
}
