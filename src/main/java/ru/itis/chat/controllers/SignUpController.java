package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.forms.SignUpForm;
import ru.itis.chat.services.SignUpService;

import java.util.Optional;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm form,
                         Model model) {
        Optional<UserDto> userDto = signUpService.signUp(form);
        if (userDto.isPresent()) {
            return "redirect:/signIn";
        } else {
            model.addAttribute("message", "User already exist");
            return "signUp";
        }
    }
}
