package ru.otus.slepukhin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
