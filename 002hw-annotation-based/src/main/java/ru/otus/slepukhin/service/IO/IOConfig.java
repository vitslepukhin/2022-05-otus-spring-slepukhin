package ru.otus.slepukhin.service.IO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
public class IOConfig {

    @Bean
    public InputStream input() {
        return System.in;
    }

    @Bean
    public PrintStream output() {
        return System.out;
    }
}