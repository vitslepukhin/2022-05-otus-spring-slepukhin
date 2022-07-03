package ru.otus.slepukhin.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.slepukhin.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.queryForObject("SELECT count(*) FROM books",
                                                                    Map.of(),
                                                                    Integer.class
        );
        return count == null ? 0 : count;
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor_id());
        params.addValue("genre_id", book.getGenre_id());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "INSERT INTO books (title, author_id, genre_id) VALUES (:title, :author_id, :genre_id)",
                params,
                keyHolder
        );

        return keyHolder.getKey()
                        .longValue();
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("UPDATE books SET title = :title, author_id = :author_id, genre_id = :genre_id WHERE id = :id",
                                            Map.of("id",
                                                   book.getId(),
                                                   "title",
                                                   book.getTitle(),
                                                   "author_id",
                                                   book.getAuthor_id(),
                                                   "genre_id",
                                                   book.getGenre_id()
                                            )
        );
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("SELECT id, title, author_id, genre_id FROM BOOKS where id = :id ",
                                                           params,
                                                           new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("SELECT id, title, author_id, genre_id FROM books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws
                SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("title");
            Long author_id = resultSet.getLong("author_id");
            Long genre_id = resultSet.getLong("genre_id");
            return new Book(id, name, author_id, genre_id);
        }
    }
}
