package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
@Repository
*/
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final List<AccidentType> types = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(4);

    public AccidentMem() {
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        accidents.put(1, new Accident(1, "one", "oneText", "oneAddress", types.get(0), Set.of(rules.get(0))));
        accidents.put(2, new Accident(2, "two", "twoText", "twoAddress", types.get(1), Set.of(rules.get(1))));
        accidents.put(3, new Accident(3, "three", "threeText", "threeAddress", types.get(2), Set.of(rules.get(2))));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        int id = accident.getId();
        if (id != 0) {
            accidents.get(id).setName(accident.getName());
        } else {
            accident.setId(idCounter.get());
            accident.setAccidentType(findTypeById(accident.getAccidentType().getId()));
            accidents.put(idCounter.getAndIncrement(), accident);
        }
    }

    public void update(Accident accident) {
        accident.setAccidentType(findTypeById(accident.getAccidentType().getId()));
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> findAllTypes() {
        return types;
    }

    public AccidentType findTypeById(int id) {
        return types.get(id - 1);
    }

    public List<Rule> findAllRules() {
        return rules;
    }

    public void setRules(Accident accident, Integer[] ids) {
        Set<Rule> toSet = new HashSet<>();
        for (Integer id: ids) {
            toSet.add(rules.get(id - 1));
        }
        accident.setRules(toSet);
    }
}
