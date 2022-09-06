package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.repositories.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public long insert(Book bookForInsert) {
        return bookRepository.save(bookForInsert).getId();
    }

    @Override
    public void update(Book bookForUpdate) {
        bookRepository.save(bookForUpdate);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
