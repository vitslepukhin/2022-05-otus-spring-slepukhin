package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.slepukhin.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "bookEntityGraph")
    @Override
    List<Book> findAll();

    @Override
    @EntityGraph(value = "bookEntityGraph")
    Optional<Book> findById(Long id);
}
