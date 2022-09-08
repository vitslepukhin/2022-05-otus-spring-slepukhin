package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Comment;
import ru.otus.slepukhin.repositories.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return commentRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public long insert(Comment commentForInsert) {
        return commentRepository.save(commentForInsert).getId();
    }

    @Transactional
    @Override
    public void update(Comment commentForUpdate) {
        commentRepository.save(commentForUpdate);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
