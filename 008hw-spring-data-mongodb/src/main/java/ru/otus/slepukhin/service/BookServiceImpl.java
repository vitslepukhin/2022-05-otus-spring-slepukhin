package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.repositories.BookRepository;
import ru.otus.slepukhin.repositories.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return bookRepository.count();
    }


    @Transactional(readOnly = true)
    @Override
    public Book getById(String id) throws Exception {
        return bookRepository.findById(id).orElseThrow(() -> new Exception(String.valueOf(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public String insert(Book bookForInsert) {
        return bookRepository.save(bookForInsert).getId();
    }

    @Transactional
    @Override
    public void update(Book bookForUpdate) {
        bookRepository.save(bookForUpdate);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
        commentRepository.deleteByBookId(id);
    }
}
