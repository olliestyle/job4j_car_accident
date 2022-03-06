package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(4);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "one", "oneText", "oneAddress"));
        accidents.put(2, new Accident(2, "two", "twoText", "twoAddress"));
        accidents.put(3, new Accident(3, "three", "threeText", "threeAddress"));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accident.setId(idCounter.get());
        accidents.put(idCounter.getAndIncrement(), accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }
}
