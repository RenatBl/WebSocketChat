package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.chat.forms.SignInForm;
import ru.itis.chat.models.User;
import ru.itis.chat.services.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/signIn")
    public String getPage(HttpServletRequest req) {
        return signInService.isAuthenticated(req) ? "redirect:/profile" : "signIn";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("form") SignInForm form,
                         HttpServletRequest req,
                         HttpServletResponse resp) {
        Optional<User> user = signInService.signIn(form, req, resp);
        return user.isPresent() ? "redirect:/profile" : "redirect:/signIn";
    }
}
