package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.User;
import ru.otus.slepukhin.repositories.AuthorRepository;
import ru.otus.slepukhin.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User findByLogin(String login) {
        System.out.println(login);
        return userRepository.findByLogin(login);
    }
}
