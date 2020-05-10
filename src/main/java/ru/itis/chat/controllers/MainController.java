package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.chat.services.LogoutService;
import ru.itis.chat.services.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private LogoutService logoutService;

    @GetMapping("/")
    public String getRoot(HttpServletRequest req) {
        return signInService.isAuthenticated(req) ? "redirect:/profile" : "redirect:/signIn";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req,
                         HttpServletResponse resp) {
        logoutService.logout(req, resp);
        return "redirect:/signIn";
    }
}
