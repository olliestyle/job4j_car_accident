package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.Map;

@Service
public class AccidentService {

    private AccidentMem accidentMem = new AccidentMem();

    public Map<Integer, Accident> findAll() {
        return accidentMem.findAll();
    }

}
