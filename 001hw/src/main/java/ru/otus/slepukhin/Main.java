package ru.otus.slepukhin;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.slepukhin.service.QuizEngine;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuizEngine quizEngine = context.getBean(QuizEngine.class);

        final boolean SHOW_RESULT = true;
        quizEngine.process(SHOW_RESULT);
    }
}
