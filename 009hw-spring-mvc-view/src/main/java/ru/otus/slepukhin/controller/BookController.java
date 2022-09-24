package ru.otus.slepukhin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;
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

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.getAll();
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

        return "edit";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);

        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);

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
