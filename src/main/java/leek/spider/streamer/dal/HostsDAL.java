package leek.spider.streamer.dal;

import leek.spider.streamer.modules.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HostsDAL {
    private final HostsRepository db;

    @Autowired
    public HostsDAL(HostsRepository db) {
        this.db = db;
    }

    public Mono<Host> addHost(Host host) {
        return db.save(host);
    }
}