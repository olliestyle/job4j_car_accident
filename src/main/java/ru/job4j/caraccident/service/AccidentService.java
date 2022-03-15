package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.control.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Collection<Accident> findAllAccidents() {
        return accidentJdbcTemplate.findAllAccidents();
    }

    public void save(Accident accident) {
        accidentJdbcTemplate.save(accident);
    }

    public void edit(Accident accident) {
        accidentJdbcTemplate.edit(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentJdbcTemplate.findAccidentById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentJdbcTemplate.findAllTypes();
    }

    public List<Rule> findAllRules() {
        return accidentJdbcTemplate.findAllRules();
    }

    public void setRules(Accident accident, String[] rIds) {
        Integer[] ids = new Integer[rIds.length];
        for (int i = 0; i < rIds.length; i++) {
            ids[i] = Integer.parseInt(rIds[i]);
        }
        accidentJdbcTemplate.setRules(accident, ids);
    }

    public void update(Accident accident) {
        accidentJdbcTemplate.update(accident);
    }
}
