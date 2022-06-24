package ru.otus.slepukhin.service.IO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOConfiguration {
    @Bean
    public IO ioImpl() {
        return new IOImpl(System.out, System.in);
    }
}
