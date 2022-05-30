package ru.otus.slepukhin.dao;

import java.io.IOException;

public class NotFoundQuestionsException extends IOException {

    NotFoundQuestionsException(String resourceName) {

        super("Could not find questions in " + resourceName);
    }
}