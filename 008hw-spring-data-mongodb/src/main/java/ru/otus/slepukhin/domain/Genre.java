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
@Document(collection = Genre.COLLECTION)
public class Genre {
    public static final String COLLECTION = "genres";
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    public Genre(String id) {
        this.id = id;
    }
}
