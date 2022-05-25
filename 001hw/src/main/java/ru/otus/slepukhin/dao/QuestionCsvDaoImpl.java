package ru.otus.slepukhin.dao;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import ru.otus.slepukhin.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class QuestionCsvDaoImpl implements Dao<Question> {
    private final static String DELIMITER = ",";
    private final List<Question> questions;

    public QuestionCsvDaoImpl(Resource resource) {
        this.questions = this.getQuestionsFromResource(resource);
    }

    @Override
    public List<Question> getAll() {
        return this.questions;
    }

    @SneakyThrows
    private List<Question> getQuestionsFromResource(Resource resource) {
        InputStream resourceAsStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader.lines().map((line) -> {
            String[] csvRecord = line.split(DELIMITER);
            String question = csvRecord[0].strip();
            String answer = csvRecord[1].strip();
            return new Question(question, answer);
        }).collect(toCollection(ArrayList::new));
    }
}

