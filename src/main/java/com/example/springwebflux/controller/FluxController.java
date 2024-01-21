package com.example.springwebflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {
    @GetMapping("/flux")
    public Flux<Integer> getFlux(){
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/fluxStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFluxStream(){
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
}
