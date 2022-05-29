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

public class QuestionCsvDaoImpl implements QuestionDao {
    private static final String DELIMITER = ",";
    private final Resource resource;

    public QuestionCsvDaoImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> getAll() {
        return this.getQuestionsFromResource(this.resource);
    }

    @SneakyThrows
    private List<Question> getQuestionsFromResource(Resource resource) {
        try (
                InputStream resourceAsStream = resource.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
                BufferedReader reader = new BufferedReader(inputStreamReader
        )
        ){

            return reader.lines()
                         .map(line -> {
                             String[] csvRecord = line.split(DELIMITER);
                             String question = csvRecord[0].strip();
                             String answer = csvRecord[1].strip();
                             return new Question(question, answer);
                         })
                         .collect(toCollection(ArrayList::new));
        }

    }
}

