package leek.spider.streamer.controllers;

import leek.spider.streamer.bl.HostsBL;
import leek.spider.streamer.modules.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/hosts")
@RestController
public class HostsController {

    private final HostsBL bl;

    @Autowired
    public HostsController(HostsBL bl) {
        this.bl = bl;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Host> addHost(@Valid @RequestBody Host host) {
        return bl.addHost(host);
    }
}
