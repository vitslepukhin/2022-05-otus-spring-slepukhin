package ru.otus.slepukhin.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.repositories.AuthorRepository;

import java.util.List;

@ChangeLog(order = "001")
public class AuthorChangeLog {
    private final List<Author> authors = List.of(
            new Author("1", "Pushkin Alexander"),
            new Author("2", "Smith John"),
            new Author("3", "Family Name"),
            new Author("4", "Author 4"),
            new Author("5", "Author 5"),
            new Author("6", "Author 6")
    );

    @ChangeSet(order = "001", id = "dropAuthorsCollection", author = "slepukhin", runAlways = true)
    public void dropAuthors(MongoDatabase db) {
        db.getCollection(Author.COLLECTION).drop();
    }

    @ChangeSet(order = "002", id = "addAuthorsCollection", author = "slepukhin", runAlways = true)
    public void addAuthors(AuthorRepository authorRepository) {
        authorRepository.saveAll(authors);
    }
}
