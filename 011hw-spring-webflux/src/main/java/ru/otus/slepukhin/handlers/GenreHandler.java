package ru.otus.slepukhin.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.repositories.GenreRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
@AllArgsConstructor
public class GenreHandler {

    private final GenreRepository repository;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Genre> bookFlux = repository.findAll();
        return ok()
                .contentType(APPLICATION_JSON)
                .body(bookFlux, Genre.class);
    }
}
