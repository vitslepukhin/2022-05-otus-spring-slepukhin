package ru.otus.slepukhin.routers;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.slepukhin.handlers.AuthorHandler;
import ru.otus.slepukhin.handlers.BookHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@AllArgsConstructor
public class AuthorsRouter {
    private final AuthorHandler handler;

    @Bean
    RouterFunction<ServerResponse> authorsRoutes() {
        return route()
                .GET("/authors", accept(APPLICATION_JSON), handler::findAll)
                .build();
    }
}
