package ru.otus.slepukhin.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.repositories.AuthorRepository;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.*;


@Component
@AllArgsConstructor
public class AuthorHandler {

    private final AuthorRepository repository;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Author> bookFlux = repository.findAll();
        return ok()
                .contentType(APPLICATION_JSON)
                .body(bookFlux, Author.class);
    }
}
