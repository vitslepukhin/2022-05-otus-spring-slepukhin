package ru.otus.slepukhin.config;

import org.springframework.core.io.Resource;

import java.util.Map;

public interface SourceProperties {
    Map<String, Resource> getSource();
}
