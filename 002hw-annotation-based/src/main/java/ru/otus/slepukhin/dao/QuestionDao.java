package ru.otus.slepukhin.dao;

import ru.otus.slepukhin.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getAll() throws
            NotFoundQuestionsException;
}
