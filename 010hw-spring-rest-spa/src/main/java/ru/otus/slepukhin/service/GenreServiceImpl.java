package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.repositories.GenreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
