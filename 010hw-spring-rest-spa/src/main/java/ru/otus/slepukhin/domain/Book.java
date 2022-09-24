package ru.otus.slepukhin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedEntityGraph(name = "bookEntityGraph", attributeNodes = {
        @NamedAttributeNode("authors"),
        @NamedAttributeNode("genre")
})
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 4)
    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book(long id) {
        this.id = id;
    }
}