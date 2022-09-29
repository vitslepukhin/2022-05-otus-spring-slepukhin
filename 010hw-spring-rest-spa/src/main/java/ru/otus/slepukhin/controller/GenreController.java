package ru.otus.slepukhin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.service.GenreService;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class GenreController {
    private GenreService authorService;

    @GetMapping("/genres/suggest")
    List<Genre> suggestGenres() {
        return authorService.getAll();
    }
}
