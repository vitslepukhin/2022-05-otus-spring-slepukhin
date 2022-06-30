package ru.otus.slepukhin.dao;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.slepukhin.config.FileNameProvider;
import ru.otus.slepukhin.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Repository
@AllArgsConstructor
public class QuestionCsvDaoImpl implements QuestionDao {
    private static final String DELIMITER = ",";
    private final FileNameProvider fileNameProvider;

    @Override
    public List<Question> getAll() throws
            QuestionsLoadingException {
        return this.getQuestionsFromResource(getResource());
    }

    private List<Question> getQuestionsFromResource(Resource resource) throws
            QuestionsLoadingException {
        try (
                InputStream resourceAsStream = resource.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
                BufferedReader reader = new BufferedReader(inputStreamReader)
        ) {
            return reader.lines()
                         .map(line -> {
                             String[] csvRecord = line.split(DELIMITER);
                             String question = csvRecord[0].strip();
                             String answer = csvRecord[1].strip();
                             return new Question(question, answer);
                         })
                         .collect(toCollection(ArrayList::new));

        } catch (Exception e) {
            throw new QuestionsLoadingException(resource.getFilename(), e);
        }
    }

    private Resource getResource() {
        String fileName = fileNameProvider.getFileName();
        return new ClassPathResource(fileName);
    }
}

