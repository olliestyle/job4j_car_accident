package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "one", "oneText", "oneAddress"));
        accidents.put(2, new Accident(2, "two", "twoText", "twoAddress"));
        accidents.put(3, new Accident(3, "three", "threeText", "threeAddress"));
    }

    public Map<Integer, Accident> findAll() {
        return accidents;
    }
}
