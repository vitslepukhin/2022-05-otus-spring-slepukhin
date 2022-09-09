package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
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
    public Comment getById(long id) throws Exception {
        return commentService.getById(id);
    }

    @ShellMethod(value = "Get comment by book id", key = "comment getByBookId")
    public List<Comment> getByBookId(long commentId) {
        return commentService.getByBookId(commentId);
    }

    @ShellMethod(value = "Get all comments", key = "comment getAll")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Insert comment", key = "comment insert")
    public long insert(String text, long bookId) {
        return commentService.insert(new Comment(0, text, new Book(bookId)));
    }

    @ShellMethod(value = "Update comment", key = "comment update")
    public void update(long id, String text, long bookId) {
        commentService.update(new Comment(id, text, new Book(bookId)));
    }

    @ShellMethod(value = "Delete comment", key = "comment deleteById")
    public void deleteById(long id) {
        commentService.deleteById(id);
    }
}