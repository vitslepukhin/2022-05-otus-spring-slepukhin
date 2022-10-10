package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByLogin(String login);
}
