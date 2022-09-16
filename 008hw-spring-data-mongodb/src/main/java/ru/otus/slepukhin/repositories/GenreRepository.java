package ru.otus.slepukhin.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.slepukhin.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {
}
