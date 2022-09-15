package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.slepukhin.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBookId(long bookId);
}
