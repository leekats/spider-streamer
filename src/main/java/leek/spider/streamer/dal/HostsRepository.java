package leek.spider.streamer.dal;

import leek.spider.streamer.modules.Host;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface HostsRepository extends ReactiveMongoRepository<Host, String> {
    Mono<Host> findFirstByIp(String ip);
}
