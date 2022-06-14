package ru.otus.slepukhin.service.translation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.config.LanguageProperties;

import java.util.Locale;

@Service
public class TranslationService implements Translation {
    private final MessageSource messageSource;
    private Locale locale;
    TranslationService(MessageSource messageSource, LanguageProperties languageProperties) {
        this.messageSource = messageSource;
        this.locale = new Locale(languageProperties.getLanguage());
    }

    public String translate(String key) {
        return messageSource.getMessage(key, null, locale);
    }
}
