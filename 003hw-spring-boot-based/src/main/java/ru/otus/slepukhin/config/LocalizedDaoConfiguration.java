package ru.otus.slepukhin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Configuration
@RequiredArgsConstructor
public class LocalizedDaoConfiguration implements DaoConfiguration {
    private final SourceProperties sourceProperties;
    private final LanguageProperties languageProperties;

    @Bean
    public Resource getResource() {
        var language = languageProperties.getLanguage();
        var resources = sourceProperties.getSource();
        var res = resources.get(language);
        return res;
    }
}
