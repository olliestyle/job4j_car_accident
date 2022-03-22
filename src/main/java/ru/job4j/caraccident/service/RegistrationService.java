package ru.job4j.caraccident.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Authority;
import ru.job4j.caraccident.model.User;
import ru.job4j.caraccident.repository.AuthorityRepository;
import ru.job4j.caraccident.repository.UserRepository;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public RegistrationService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    public Authority findUserByAuthority(String role) {
        return authorityRepository.findByAuthority(role);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
