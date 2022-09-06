package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.domain.Comment;
import ru.otus.slepukhin.repositories.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public long getCount() {
        return commentRepository.count();
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> getByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public long insert(Comment commentForInsert) {
        return commentRepository.save(commentForInsert).getId();
    }

    @Override
    public void update(Comment commentForUpdate) {
        commentRepository.save(commentForUpdate);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
