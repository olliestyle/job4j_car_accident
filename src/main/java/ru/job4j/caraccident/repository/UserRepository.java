package ru.job4j.caraccident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.caraccident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsUserByUsername(String username);
}
