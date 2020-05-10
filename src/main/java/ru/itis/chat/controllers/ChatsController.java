package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.services.ChatsService;
import ru.itis.chat.services.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatsController {

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/chats")
    public String getAllChats(HttpServletRequest req,
                              Model model) {
        model.addAttribute("chats", chatsService.getAllChats(req));
        model.addAttribute("user", UserDto.get(usersService.getUserByCookie(req), "all"));
        return "chats";
    }

    @GetMapping("/createChat")
    public String getCreatingChatPage(Model model,
                                      HttpServletRequest req) {
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("owner", usersService.getUserByCookie(req));
        return "createChat";
    }
}
