package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.dao.BookDao;
import ru.otus.slepukhin.domain.Book;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookDao bookDao;

    public int getCount() {
        return bookDao.count();
    }

    public Book getById(long id) {
        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public long insert(Book bookForInsert) {
        return bookDao.insert(bookForInsert);
    }

    public void update(Book bookForUpdate) {
        bookDao.update(bookForUpdate);
    }

    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
