package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.control.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentMem.findAllTypes();
    }

    public List<Rule> findAllRules() {
        return accidentMem.findAllRules();
    }

    public void setRules(Accident accident, String[] rIds) {
        Integer[] ids = new Integer[rIds.length];
        for (int i = 0; i < rIds.length; i++) {
            ids[i] = Integer.parseInt(rIds[i]);
        }
        accidentMem.setRules(accident, ids);
    }
}
