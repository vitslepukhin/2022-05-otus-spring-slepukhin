package ru.otus.slepukhin.service.translator;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.config.LanguageProvider;

import java.util.Locale;

@Service
@AllArgsConstructor
public class TranslatorService implements Translator {
    private final MessageSource messageSource;
    private final LanguageProvider languageProvider;

    public String translate(String key) {
        return messageSource.getMessage(key, null, new Locale(languageProvider.getLanguage()));
    }
}
