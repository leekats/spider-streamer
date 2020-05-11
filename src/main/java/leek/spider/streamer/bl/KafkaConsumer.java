package leek.spider.streamer.bl;

import leek.spider.streamer.modules.SpiderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ConsumerBL bl;

    @Autowired
    public KafkaConsumer(ConsumerBL bl) {
        this.bl = bl;
    }

    @KafkaListener(topics = "streamer")
    public void consume(SpiderEvent event) {
        bl.addEvent(event);
    }
}
