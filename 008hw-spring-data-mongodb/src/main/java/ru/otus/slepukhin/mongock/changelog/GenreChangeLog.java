package ru.otus.slepukhin.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.repositories.GenreRepository;

import java.util.List;

@ChangeLog(order = "002")
public class GenreChangeLog {

    private final List<Genre> genres = List.of(
            new Genre("1", "science"),
            new Genre("2", "popular"),
            new Genre("3", "folk")
    );

    @ChangeSet(order = "001", id = "dropGenresCollection", author = "slepukhin", runAlways = true)
    public void dropGenres(MongoDatabase db) {
        db.getCollection(Genre.COLLECTION).drop();
    }

    @ChangeSet(order = "002", id = "addGenresCollection", author = "slepukhin", runAlways = true)
    public void addGenres(GenreRepository genreRepository) {
        genreRepository.saveAll(genres);
    }
}
