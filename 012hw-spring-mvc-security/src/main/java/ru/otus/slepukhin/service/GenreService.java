package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAll();
}
