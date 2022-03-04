package ru.job4j.caraccident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = List.of("Murat", "Egor", "Pavel", "Oleg", "Anna", "Olga");
        model.addAttribute("users", list);
        return "index";
    }
}
