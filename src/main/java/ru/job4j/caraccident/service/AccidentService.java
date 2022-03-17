package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.repository.AccidentRepository;
import ru.job4j.caraccident.repository.AccidentTypeRepository;
import ru.job4j.caraccident.repository.RuleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Transactional
    public Collection<Accident> findAllAccidents() {
        return accidentRepository.findAll();
    }

    @Transactional
    public void save(Accident accident) {
        accident.setAccidentType(accidentTypeRepository.findById(accident.getAccidentType().getId()).get());
        Set<Rule> rules = new HashSet<>();
        accident.getRules().forEach(rule -> rules.add(ruleRepository.findById(rule.getId()).get()));
        accident.setRules(rules);
        accidentRepository.save(accident);
    }

    @Transactional
    public void edit(Accident accident) {
        Accident toUpdate = accidentRepository.findById(accident.getId()).get();
        toUpdate.setName(accident.getName());
        toUpdate.setText(accident.getText());
        toUpdate.setAddress(accident.getAddress());
        toUpdate.setAccidentType(accidentTypeRepository.findById(accident.getAccidentType().getId()).get());
        accidentRepository.save(toUpdate);
    }

    @Transactional
    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id).get();
    }

    @Transactional
    public List<AccidentType> findAllTypes() {
        return (List<AccidentType>) accidentTypeRepository.findAll();
    }

    @Transactional
    public List<Rule> findAllRules() {
        return (List<Rule>) ruleRepository.findAll();
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

    @Transactional
    public void update(Accident accident) {
        Accident toUpdate = accidentRepository.findById(accident.getId()).get();
        toUpdate.setName(accident.getName());
        accidentRepository.save(toUpdate);
    }
}
