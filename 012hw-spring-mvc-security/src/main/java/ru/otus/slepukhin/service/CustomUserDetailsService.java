package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final ru.otus.slepukhin.domain.User user = userService.findByLogin(username);
        if (user == null) throw new UsernameNotFoundException(username);
        UserDetails userDetails = User.withUsername(user.getLogin()).password(user.getPassword()).authorities(List.of())
                                      .build();
        return userDetails;
    }
}
