package ru.otus.slepukhin.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT s FROM Book s JOIN FETCH s.genre", Book.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        Query query = em.createQuery("SELECT count(*) FROM Book s");
        return (long) query.getSingleResult();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("DELETE " +
                "FROM Book s " +
                "WHERE s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
