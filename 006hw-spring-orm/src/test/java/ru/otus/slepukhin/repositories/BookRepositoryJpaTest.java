package ru.otus.slepukhin.repositories;


import lombok.val;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.engine.jdbc.spi.SqlStatementLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.slepukhin.domain.Book;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA based repository for Books ")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 7;
    private static final long FIRST_BOOK_ID = 1L;

    private static final int EXPECTED_QUERIES_COUNT = 3;

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен загружать информацию о нужном книге по его id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook.get())
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }


    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = repositoryJpa.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
                .allMatch(s -> s.getGenre() != null);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("должен загружать ожидаемый список книг по номеру страницы")
    @Test
    void shouldReturnCorrectBookListByPage() {
        AtomicInteger booksSelectionsCount = new AtomicInteger(0);
        applyCustomSqlStatementLogger(new SqlStatementLogger(true, false, false, 0) {
            @Override
            public void logStatement(String statement) {
                if (!statement.contains("count") && statement.contains("from books")) {
                    booksSelectionsCount.incrementAndGet();
                    assertThat(statement).contains("offset").contains("limit");
                }
                super.logStatement(statement);
            }
        });

        var booksCount = em.getEntityManager()
                .createQuery("SELECT count(s) FROM Book s", Long.class).getSingleResult();
        var pageNum = 1;
        var pageSize = 3;
        var pagesCount = (long) Math.ceil(booksCount * 1d / pageSize);

        var query = em.getEntityManager().createQuery("SELECT s FROM Book s ", Book.class);
        var books = query.setFirstResult(pageNum * pageSize).setMaxResults(pageSize).getResultList();

        assertThat(pagesCount).isEqualTo(3);
        assertThat(books).isNotNull().hasSize(pageSize);
        assertThat(booksSelectionsCount.get()).isEqualTo(1);
    }

    private void applyCustomSqlStatementLogger(SqlStatementLogger customSqlStatementLogger) {
        StandardServiceRegistry serviceRegistry = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class).getSessionFactoryOptions().getServiceRegistry();
        var jdbcServices = serviceRegistry.getService(JdbcServices.class);
        try {
            Field field = jdbcServices.getClass().getDeclaredField("sqlStatementLogger");
            field.setAccessible(true);
            field.set(jdbcServices, customSqlStatementLogger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}