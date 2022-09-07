package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.slepukhin.domain.Comment;

import java.util.List;

@ShellComponent
@ShellCommandGroup(CommentShellService.groupPrefix)
@AllArgsConstructor
public class CommentShellService {
    public final static String groupPrefix = "comment";
    private final CommentService commentService;

    @ShellMethod(value = "Get comment's count", key = groupPrefix + " getCount")
    public long getCount() {
        return commentService.getCount();
    }

    @ShellMethod(value = "Get comment by id", key = groupPrefix + " getById")
    public Comment getById(long id) {
        return commentService.getById(id);
    }

    @ShellMethod(value = "Get comment by book id", key = groupPrefix + " getByBookId")
    public List<Comment> getByBookId(long commentId) {
        return commentService.getByBookId(commentId);
    }

    @ShellMethod(value = "Get all comments", key = groupPrefix + " getAll")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Insert comment", key = groupPrefix + " insert")
    public long insert(String text, long bookId) {
        return commentService.insert(new Comment(0, text, bookId));
    }

    @ShellMethod(value = "Update comment", key = groupPrefix + " update")
    public void update(long id, String text, long bookId) {
        commentService.update(new Comment(id, text, bookId));
    }

    @ShellMethod(value = "Delete comment", key = groupPrefix + " deleteById")
    public void deleteById(long id) {
        commentService.deleteById(id);
    }
}