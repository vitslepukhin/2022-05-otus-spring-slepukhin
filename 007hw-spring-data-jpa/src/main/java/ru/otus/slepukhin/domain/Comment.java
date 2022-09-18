package ru.otus.slepukhin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedEntityGraph(name = "commentEntityGraph", attributeNodes = {
                @NamedAttributeNode(value = "book", subgraph = "bookSubGraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "bookSubGraph",
                        attributeNodes = {
                                @NamedAttributeNode("authors"),
                                @NamedAttributeNode("genre")
                        }
                )
        }
)
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
