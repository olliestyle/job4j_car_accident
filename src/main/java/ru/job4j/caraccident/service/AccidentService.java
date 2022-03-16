package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.repository.AccidentHibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {

    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public Collection<Accident> findAllAccidents() {
        return accidentHibernate.findAllAccidents();
    }

    public void save(Accident accident) {
        accidentHibernate.save(accident);
    }

    public void edit(Accident accident) {
        accidentHibernate.edit(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentHibernate.findAccidentById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentHibernate.findAllTypes();
    }

    public List<Rule> findAllRules() {
        return accidentHibernate.findAllRules();
    }

    public void setRules(Accident accident, String[] rIds) {
        Set<Rule> set = new HashSet<>();
        for (String id: rIds) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(id));
            set.add(rule);
        }
        accident.setRules(set);
    }

    public void update(Accident accident) {
        accidentHibernate.update(accident);
    }
}
