package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.Collection;

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

}
