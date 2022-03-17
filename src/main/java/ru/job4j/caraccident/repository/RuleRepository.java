package ru.job4j.caraccident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.caraccident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
