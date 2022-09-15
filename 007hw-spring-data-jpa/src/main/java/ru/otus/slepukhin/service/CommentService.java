package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Comment;

import java.util.List;

public interface CommentService {
    long getCount();

    Comment getById(long id) throws Exception;

    List<Comment> getByBookId(long bookId);

    List<Comment> getAll();

    long insert(Comment commentForInsert);

    void update(Comment commentForUpdate);

    void deleteById(long id);
}
