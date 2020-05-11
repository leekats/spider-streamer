package leek.spider.streamer.bl;

import leek.spider.streamer.dal.HostsDAL;
import leek.spider.streamer.modules.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HostsBL {
    public final HostsDAL dal;

    @Autowired
    public HostsBL(HostsDAL dal) {
        this.dal = dal;
    }

    public Mono<Host> addHost(Host host) {
        return dal.addHost(host);
    }
}
