package ru.job4j.caraccident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.caraccident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @EntityGraph(value = "rulesAndAccidentType")
    List<Accident> findAll();

}
