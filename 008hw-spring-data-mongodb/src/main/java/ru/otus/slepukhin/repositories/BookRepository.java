package ru.otus.slepukhin.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.slepukhin.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAll();
}
