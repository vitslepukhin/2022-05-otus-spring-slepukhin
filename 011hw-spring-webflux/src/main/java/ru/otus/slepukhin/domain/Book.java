package ru.otus.slepukhin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = Book.COLLECTION)
public class Book {
    public static final String COLLECTION = "books";

    @Id
    private String id;

    @Field(name = "title")
    private String title;

    @DBRef
    @Field(name = "authors")
    private List<Author> authors = new ArrayList<>();

    @DBRef
    @Field(name = "genre")
    private Genre genre;

    public Book(String title, List<Author> authors, Genre genre) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }

    public Book(String id) {
        this.id = id;
    }
}
