package leek.spider.streamer.controllers;

import leek.spider.streamer.bl.ConsumerBL;
import leek.spider.streamer.dal.FSLogger;
import leek.spider.streamer.modules.SpiderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private final ConsumerBL bl;
    private final FSLogger logger;

    @Autowired
    public ReactiveWebSocketHandler(ConsumerBL bl, FSLogger logger) {
        this.bl = bl;
        this.logger = logger;
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
        logger.connectedClient(session.getId());
        return Mono.just("test");
    }
}
