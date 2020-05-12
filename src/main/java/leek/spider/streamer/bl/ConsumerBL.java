package leek.spider.streamer.bl;

import leek.spider.streamer.dal.FSLogger;
import leek.spider.streamer.dal.HostsRepository;
import leek.spider.streamer.modules.Permissions;
import leek.spider.streamer.modules.SpiderEvent;
import org.reactivestreams.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;

import java.util.Objects;
import java.util.function.Predicate;

import static leek.spider.streamer.modules.consts.Checkpoints.GET_EVENTS;

@Service
public class ConsumerBL {
    private final Processor<SpiderEvent, SpiderEvent> updateUnicastProcessor = UnicastProcessor.create();
    private final Flux<SpiderEvent> updates = Flux.from(updateUnicastProcessor)
            .replay(0)
            .autoConnect();
    private final HostsRepository db;
    private final FSLogger logger;

    @Autowired
    public ConsumerBL(HostsRepository db, FSLogger logger) {
        this.db = db;
        this.logger = logger;
    }

    public Mono<Predicate<SpiderEvent>> getPermissions(String ip) {
        if (Objects.isNull(ip)) {
            ip = "test";
        }
        return db.findFirstByIp(ip).map(host -> createPredicate(host.getPermissions())).switchIfEmpty(Mono.empty());
    }

    public Flux<SpiderEvent> getEvents(Predicate<SpiderEvent> p) {
        return updates
                .filter(p)
                .doOnError(logger::error)
                .checkpoint(GET_EVENTS);
    }

    private Predicate<SpiderEvent> createPredicate(Permissions permissions) {
        // TODO: Refactor
        return spiderEvent -> spiderEvent.getIndication() == permissions.getIndication() && spiderEvent.getType() == permissions.getType();
    }

    public void addEvent(SpiderEvent event) {
        updateUnicastProcessor.onNext(event);
    }
}