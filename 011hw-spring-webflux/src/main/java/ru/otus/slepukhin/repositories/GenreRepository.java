package ru.otus.slepukhin.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.slepukhin.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
