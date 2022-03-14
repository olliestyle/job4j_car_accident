package ru.job4j.caraccident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.caraccident.repository.AccidentJdbcTemplate;
import ru.job4j.caraccident.service.AccidentService;

@Controller
public class IndexControl {

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public IndexControl(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentJdbcTemplate.findAll());
        return "index";
    }
}
