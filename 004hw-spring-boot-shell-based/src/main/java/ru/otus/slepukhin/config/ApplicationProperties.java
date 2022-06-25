package ru.otus.slepukhin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Data
@ConfigurationProperties
@Component
public class ApplicationProperties implements LanguageProvider, QuizConfigProvider, FileNameProvider {
    private String language = "en";
    private Map<String, String> localizedFileNames = Collections.singletonMap(language, "questions_" + language + ".csv");
    private Integer rightAnswersToPassQuiz = 5;


    @Override
    public String getFileName() {
        return localizedFileNames.get(language);
    }
}
