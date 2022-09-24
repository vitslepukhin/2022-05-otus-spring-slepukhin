package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.slepukhin.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
