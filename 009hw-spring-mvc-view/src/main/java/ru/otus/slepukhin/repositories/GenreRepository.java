package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.slepukhin.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
