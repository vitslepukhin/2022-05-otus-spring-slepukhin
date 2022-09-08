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
    public Book getById(long id) throws Exception {
        return bookRepository.findById(id).orElseThrow(() -> new Exception(String.valueOf(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public long insert(Book bookForInsert) {
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
