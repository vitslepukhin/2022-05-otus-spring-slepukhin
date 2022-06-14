package ru.otus.slepukhin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Data
@ConfigurationProperties
@Component
public class ApplicationProperties implements LanguageProperties, QuizProperties, SourceProperties {
    private String language = "en";
    private Map<String, Resource> source = Collections.singletonMap(
            language,
            new ClassPathResource("questions_" + language + ".csv")
                                                                         );
    private Integer rightAnswersToPassQuiz = 5;
}
