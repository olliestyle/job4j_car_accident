package ru.job4j.caraccident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.List;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public AccidentJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Accident save(Accident accident) {
        jdbcTemplate.update("insert into accident (name) values (?)", accident.getName());
        return accident;
    }

    public List<Accident> findAll() {
        return jdbcTemplate.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }
}
