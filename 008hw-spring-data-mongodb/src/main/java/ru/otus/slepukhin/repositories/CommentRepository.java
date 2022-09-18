package ru.otus.slepukhin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.slepukhin.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAll();

    List<Comment> findByBookId(String bookId);
    void deleteByBookId(String bookId);
}
