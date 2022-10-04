package ru.otus.slepukhin.routers;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.slepukhin.handlers.BookHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@AllArgsConstructor
public class BooksRouter {

    private final BookHandler handler;

    @Bean
    RouterFunction<ServerResponse> booksRoutes() {
        return route()
                .GET("/books/{id}", accept(APPLICATION_JSON), handler::findById)
                .PUT("/books/{id}", handler::update)
                .DELETE("/books/{id}", handler::deleteById)
                .GET("/books", accept(APPLICATION_JSON), handler::findAll)
                .POST("/books", handler::create)
                .build();
    }
}
