package ru.otus.slepukhin.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.slepukhin.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
