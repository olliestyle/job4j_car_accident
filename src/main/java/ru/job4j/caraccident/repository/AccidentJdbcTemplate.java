package ru.job4j.caraccident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
@Repository
*/
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public AccidentJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into accident (name, text, address, accident_type_id) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getAccidentType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) keyHolder.getKeys().get("id"));
        return accident;
    }

    public List<Accident> findAllAccidents() {
        return jdbcTemplate.query("select a.id aid, a.name aname, text, address, a.accident_type_id aatid,"
                        + " at.id atid, at.name atname"
                        + " from accident a join accident_type at "
                        + "on a.accident_type_id = at.id;",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("aid"));
                    accident.setName(rs.getString("aname"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setAccidentType(AccidentType.of(rs.getInt("aatid"),
                            rs.getString("atname")));
                    accident.setRules(findRulesByAccidentId(rs.getInt("aid")));
                    return accident;
                });
    }

    public Set<Rule> findRulesByAccidentId(int id) {
        return new HashSet<>(jdbcTemplate.query("select rule.id as rid, rule.name as rname from rule"
                        + " join accident_rule ar on rule.id = ar.rule_id where accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("rid"));
                    rule.setName(rs.getString("rname"));
                    return rule;
                }, id));
    }

    public AccidentType findAccidentTypeById(int id) {
        return jdbcTemplate.queryForObject("select * from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id);
    }

    public List<Rule> findAllRules() {
        return jdbcTemplate.query("select * from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<AccidentType> findAllTypes() {
        return jdbcTemplate.query("select * from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Accident findAccidentById(int id) {
        return jdbcTemplate.queryForObject("select * from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = findAccidentTypeById(rs.getInt("accident_type_id"));
                    accident.setAccidentType(accidentType);
                    return accident;
                }, id);
    }

    public void edit(Accident accident) {
        jdbcTemplate.update("update accident set name = ?, text = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getAccidentType().getId(),
                accident.getId());
    }

    public void setRules(Accident accident, Integer[] ids) {
        for (Integer id: ids) {
            jdbcTemplate.update("insert into accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    id);
        }
    }

    public void update(Accident accident) {
        jdbcTemplate.update("update accident set name = ? where id = ?",
                accident.getName(),
                accident.getId());
    }
}
