package ru.job4j.caraccident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/edit")
    public String edit(@ModelAttribute Accident accident, Model model, HttpServletRequest req) {
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", req.getParameterValues("rIds"));
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident) {
        accidentService.edit(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findAccidentById(id));
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        if (rIds != null) {
            accidentService.setRules(accident, rIds);
        }
        accidentService.save(accident);
        return "redirect:/";
    }
}
