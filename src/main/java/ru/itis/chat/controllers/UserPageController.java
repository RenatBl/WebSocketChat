package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;
import ru.itis.chat.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserPageController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile")
    public String getPage(Model model,
                          HttpServletRequest req) {
        Optional<Long> id = Optional.ofNullable((Long) req.getAttribute("user"));
        if (id.isPresent()) {
            model.addAttribute("user", UserDto.get(usersService.getUser(id.get()), "name"));
            return "user";
        } else {
            Optional<User> user = Optional.ofNullable(usersService.getUserByCookie(req));
            if (user.isPresent()) {
                model.addAttribute("user", UserDto.get(user.get(), "name"));
                return "user";
            } else return "redirect:/signIn";
        }
    }

    @GetMapping("/user")
    public String getUserPage(@RequestParam("id") Long id,
                              Model model) {
        model.addAttribute("user", UserDto.get(usersService.getUser(id), "name"));
        return "user";
    }
}
