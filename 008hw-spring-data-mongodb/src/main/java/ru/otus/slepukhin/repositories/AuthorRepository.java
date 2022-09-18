package ru.otus.slepukhin.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.slepukhin.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, String> {
}
