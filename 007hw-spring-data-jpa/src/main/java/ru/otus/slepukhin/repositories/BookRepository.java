package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.slepukhin.domain.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
}
