package ru.otus.slepukhin.dao;

import java.io.IOException;

public class QuestionsLoadingException extends IOException {

    QuestionsLoadingException(String resourceName, Exception e) {
        super("Could not load questions from " + resourceName + " ---> " + e.getMessage());
    }
}