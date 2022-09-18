package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.slepukhin.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    @EntityGraph(value = "commentEntityGraph")
    List<Comment> findAll();

    @Override
    @EntityGraph(value = "commentEntityGraph")
    Optional<Comment> findById(Long id);

    @EntityGraph(value = "commentEntityGraph")
    List<Comment> findByBookId(Long bookId);

    void deleteByBookId(Long bookId);
}
