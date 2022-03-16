package ru.job4j.caraccident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.service.AccidentService;

import java.util.List;

@Controller
public class IndexControl {

    private final AccidentService accidentService;

    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> list = (List<Accident>) accidentService.findAllAccidents();
        model.addAttribute("accidents", list);
        return "index";
    }
}
