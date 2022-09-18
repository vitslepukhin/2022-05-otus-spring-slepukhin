package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Comment;

import java.util.List;

@ShellComponent
@ShellCommandGroup("comment")
@AllArgsConstructor
public class CommentShellService {
    private final CommentService commentService;

    @ShellMethod(value = "Get comment's count", key = "comment getCount")
    public long getCount() {
        return commentService.getCount();
    }

    @ShellMethod(value = "Get comment by id", key = "comment getById")
    public Comment getById(String id) throws Exception {
        return commentService.getById(id);
    }

    @ShellMethod(value = "Get comment by book id", key = "comment getByBookId")
    public List<Comment> getByBookId(String commentId) {
        return commentService.getByBookId(commentId);
    }

    @ShellMethod(value = "Get all comments", key = "comment getAll")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Insert comment", key = "comment insert")
    public String insert(String text, String bookId) {
        return commentService.insert(new Comment(text, new Book(bookId)));
    }

    @ShellMethod(value = "Update comment", key = "comment update")
    public void update(String id, String text, String bookId) {
        commentService.update(new Comment(id, text, new Book(bookId)));
    }

    @ShellMethod(value = "Delete comment", key = "comment deleteById")
    public void deleteById(String id) {
        commentService.deleteById(id);
    }
}