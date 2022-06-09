package ru.otus.slepukhin.dao;

import java.io.IOException;

public class QuestionsLoadingException extends IOException {

    QuestionsLoadingException(String resourceName) {
        super("Could not load questions from " + resourceName);
    }
}