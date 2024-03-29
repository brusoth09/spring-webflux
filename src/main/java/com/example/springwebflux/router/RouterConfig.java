package com.example.springwebflux.router;

import com.example.springwebflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> router(UserHandler userHandler){
        return RouterFunctions
                .route(GET("/functional/users")
                        .and(accept(MediaType.APPLICATION_JSON)), userHandler::flux)
                .andRoute(GET("/functional/user")
                        .and(accept(MediaType.APPLICATION_JSON)), userHandler::mono);
    }
}
