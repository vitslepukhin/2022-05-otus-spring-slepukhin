package ru.otus.slepukhin.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Question {

    @Getter
    private final String question;
    @Getter
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean isRightAnswer(String givenAnswer) {
        return givenAnswer.equals(this.answer);
    }
}
