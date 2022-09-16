package ru.otus.slepukhin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = Author.COLLECTION)
public class Author {
    public static final String COLLECTION = "authors";

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    public Author(String id) {
        this.id = id;
    }
}
