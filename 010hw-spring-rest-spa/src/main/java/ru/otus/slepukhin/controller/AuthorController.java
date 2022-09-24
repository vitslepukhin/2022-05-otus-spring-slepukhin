package ru.otus.slepukhin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @GetMapping("/authors/suggest")
    List<Author> suggestAuthors() {
        return authorService.getAll();
    }
}
