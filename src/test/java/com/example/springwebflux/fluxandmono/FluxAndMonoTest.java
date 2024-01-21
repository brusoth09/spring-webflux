package com.example.springwebflux.fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class FluxAndMonoTest {
    @Test
    void fluxTest() {
        Flux<String> flux = Flux.just("Hello", "Hi", "Hola")
                .concatWith(Flux.error(new RuntimeException("Error while processing")))
                .concatWith(Flux.just("After error"))
                .map(s -> s.concat(" Test"))
                .log();
        flux.repeat(2).subscribe(System.out::println,
                System.err::println,
                () -> System.out.println("Completed"));
    }

    @Test
    void monoTest() {
        Mono.just("Hello")
                .map(s -> s.concat("Example"))
                .subscribe(System.out::println);
    }
}
