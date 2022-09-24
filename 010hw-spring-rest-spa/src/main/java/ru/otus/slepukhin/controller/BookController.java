package ru.otus.slepukhin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.service.BookService;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) throws NotFoundException {
        return new ResponseEntity<>(bookService
                .getById(id)
                .orElseThrow(NotFoundException::new), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book);
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> putBook(@PathVariable("id") int id, @RequestBody Book bookToPut) {
        return bookService
                .getById(id)
                .map((book) -> {
                    book.setTitle(bookToPut.getTitle());
                    book.setAuthors(bookToPut.getAuthors());
                    book.setGenre(bookToPut.getGenre());
                    return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    bookToPut.setId(id);
                    return new ResponseEntity<>(bookService.save(bookToPut), HttpStatus.CREATED);
                });
    }

    @DeleteMapping("books/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        bookService.deleteById(id);
    }
}
