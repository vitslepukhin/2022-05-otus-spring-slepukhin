package ru.otus.slepukhin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.service.AuthenticationInfoService;
import ru.otus.slepukhin.service.AuthorService;
import ru.otus.slepukhin.service.BookService;
import ru.otus.slepukhin.service.GenreService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final AuthenticationInfoService authenticationInfoService;


    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.getAll();
        boolean isAuthenticated = authenticationInfoService.isAuthenticated();
        String getUsername = authenticationInfoService.getUsername();
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("userName", getUsername);
        model.addAttribute("books", books);

        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") int id, Model model) throws NotFoundException {
        Book book = bookService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);

        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);

        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);

        boolean isAuthenticated = authenticationInfoService.isAuthenticated();
        String getUsername = authenticationInfoService.getUsername();
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("userName", getUsername);

        return "edit";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);

        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);

        boolean isAuthenticated = authenticationInfoService.isAuthenticated();
        String getUsername = authenticationInfoService.getUsername();
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("userName", getUsername);

        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
