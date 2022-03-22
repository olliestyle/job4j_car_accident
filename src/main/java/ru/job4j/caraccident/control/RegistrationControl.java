package ru.job4j.caraccident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.caraccident.model.User;
import ru.job4j.caraccident.service.RegistrationService;

@Controller
public class RegistrationControl {

    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;

    public RegistrationControl(PasswordEncoder passwordEncoder, RegistrationService registrationService) {
        this.passwordEncoder = passwordEncoder;
        this.registrationService = registrationService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(registrationService.findUserByAuthority("ROLE_USER"));
        registrationService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
