package ru.otus.slepukhin.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.slepukhin.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
