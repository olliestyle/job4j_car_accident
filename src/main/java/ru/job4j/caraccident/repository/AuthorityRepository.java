package ru.job4j.caraccident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.caraccident.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
