package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.chat.models.Chat;
import ru.itis.chat.services.ChatsService;
import ru.itis.chat.services.MessagesService;
import ru.itis.chat.services.UsersService;
import ru.itis.chat.utils.MessageResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/chat")
    public String getChat(HttpServletRequest req,
                          @RequestParam("id") Long id,
                          Model model) {
        Chat chat = chatsService.getChatById(id);
        model.addAttribute("currentUser", usersService.getUserByCookie(req).getUsername());
        model.addAttribute("chat", chat);
        model.addAttribute("messages", MessageResolver.resolveMessages(
                messagesService.getAllMessagesByChat(chat)));
        return "chat";
    }
}
