package ru.otus.slepukhin.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Comment;
import ru.otus.slepukhin.repositories.CommentRepository;

import java.util.List;

@ChangeLog(order = "004")
public class CommentChangeLog {

    @ChangeSet(order = "002", id = "addCommentsCollection", author = "slepukhin", runAlways = true)
    public void addComments(CommentRepository commentRepository) {
        List<Comment> comments = List.of(
                createNewComment("1", "Physics is cool! 1", "1"),
                createNewComment("2", "Physics comment 2", "1"),
                createNewComment("3", "Physics comment 3", "1"),
                createNewComment("4", "Physics comment 4", "1"),
                createNewComment("5", "Physics comment 5", "1"),
                createNewComment("6", "Physics comment 6", "1"),
                createNewComment("7", "Physics is bad 7", "1"),
                createNewComment("8", "Folk comment", "2")
        );

        commentRepository
                .saveAll(comments)
                .subscribe();
    }

    private Comment createNewComment(String id, String text, String bookId) {
        return new Comment(id, text, new Book(bookId));

    }
}
