package ru.otus.slepukhin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.slepukhin.service.QuizEngine;

@ComponentScan(basePackages = "ru.otus.slepukhin")
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(Main.class);
        ctx.refresh();
        QuizEngine quizEngine = ctx.getBean(QuizEngine.class);

        quizEngine.process();
    }
}
