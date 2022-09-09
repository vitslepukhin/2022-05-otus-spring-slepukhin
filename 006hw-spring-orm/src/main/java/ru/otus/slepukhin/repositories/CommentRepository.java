package ru.otus.slepukhin.repositories;

import ru.otus.slepukhin.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    List<Comment> findByBookId(long bookId);

    List<Comment> findAll();

    void deleteById(long id);

    long count();
}
