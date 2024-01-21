package com.example.springwebflux.fluxandmono;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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
    void fluxTestElementsWithoutError(){
        Flux<String> flux = Flux.just("Hello", "Hi", "Hola")
                .log();
        StepVerifier.create(flux)
                .expectNext("Hello")
                .expectNext("Hi")
                .expectNext("Hola")
                .verifyComplete();
    }

    @Test
    void fluxTestElementsWithError(){
        Flux<String> flux = Flux.just("Hello", "Hi", "Hola")
                .concatWith(Flux.error(new RuntimeException("Error")))
                .log();
        StepVerifier.create(flux)
                .expectNext("Hello")
                .expectNext("Hi")
                .expectNext("Hola")
                .expectErrorMessage("Error")
                .verify();
    }

    @Test
    void fluxTestElementCount(){
        Flux<String> flux = Flux.just("Hello", "Hi", "Hola")
                .log();
        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void monoTest() {
        Mono.just("Hello")
                .map(s -> s.concat("Example"))
                .subscribe(System.out::println);
    }
}
