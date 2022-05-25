package ru.otus.slepukhin.service;

import lombok.Getter;
import ru.otus.slepukhin.dao.Dao;
import ru.otus.slepukhin.domain.Question;

import java.util.*;

public class QuizEngine {
    @Getter
    private final List<Question> questions;
    private final Map<Question, String> result = new HashMap<>();
    private Scanner sc;

    public QuizEngine(Dao<Question> questionDao) {
        this.questions = questionDao.getAll();
    }

    public String ask(Question question) {
        System.out.print(question.getQuestion() + " ");
        return sc.nextLine();
    }

    public void process() {
        sc = new Scanner(System.in);
        this.questions.forEach(question -> result.put(question, this.ask(question)));
        System.out.println();
        sc.close();
    }

    public void showResult() {
        System.out.println("Result of quiz:");
        questions.forEach((question) -> {
            String yourAnswer = result.get(question);
            String answer = question.isRightAnswer(yourAnswer) ? "Right." : "Wrong. Right answer is " + question.getAnswer();
            System.out.println(question.getQuestion() + " " + answer);
        });
    }
}
