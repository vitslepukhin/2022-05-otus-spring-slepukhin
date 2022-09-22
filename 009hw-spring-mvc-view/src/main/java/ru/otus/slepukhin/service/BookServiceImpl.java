package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return bookRepository.count();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public long save(Book bookForInsert) {
        return bookRepository.save(bookForInsert).getId();
    }

    @Transactional
    @Override
    public void update(Book bookForUpdate) {
        bookRepository.save(bookForUpdate);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
