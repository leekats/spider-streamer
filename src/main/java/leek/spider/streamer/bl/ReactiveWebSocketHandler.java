package leek.spider.streamer.bl;

import leek.spider.streamer.modules.SpiderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private final ConsumerBL bl;

    @Autowired
    public ReactiveWebSocketHandler(ConsumerBL bl) {
        this.bl = bl;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(getSessionInfo(session)
                .flatMap(bl::getPermissions)
                .flatMapMany(bl::getEvents)
                .map(SpiderEvent::toString)
                .map(session::textMessage));
    }

    private Mono<String> getSessionInfo(WebSocketSession session) {
        return Mono.just("test");
    }
}
