package ru.otus.slepukhin.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    @Override
    public Comment save(Comment book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("SELECT s FROM Comment s WHERE s.book_id = :book_id", Comment.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("SELECT s FROM Comment s", Comment.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Comment s", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("DELETE " +
                "FROM Comment s " +
                "WHERE s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
