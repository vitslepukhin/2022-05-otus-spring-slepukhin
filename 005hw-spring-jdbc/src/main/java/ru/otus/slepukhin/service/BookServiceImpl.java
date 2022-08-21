package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.dao.BookDao;
import ru.otus.slepukhin.domain.Book;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public int getCount() {
        return bookDao.count();
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public long insert(Book bookForInsert) {
        return bookDao.insert(bookForInsert);
    }

    @Override
    public void update(Book bookForUpdate) {
        bookDao.update(bookForUpdate);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
