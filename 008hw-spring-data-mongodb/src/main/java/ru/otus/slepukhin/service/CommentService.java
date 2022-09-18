package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Comment;

import java.util.List;

public interface CommentService {
    long getCount();

    Comment getById(String id) throws Exception;

    List<Comment> getByBookId(String bookId);

    List<Comment> getAll();

    String insert(Comment commentForInsert);

    void update(Comment commentForUpdate);

    void deleteById(String id);
}
