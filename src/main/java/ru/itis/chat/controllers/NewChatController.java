package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.services.ChatsService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

@RestController
public class NewChatController {

    @Autowired
    private ChatsService chatsService;

    @Consumes({MediaType.APPLICATION_JSON_VALUE, "application/json"})
    @RequestMapping(value = "/newChat", method = RequestMethod.POST)
    public ResponseEntity newChat(@RequestBody ChatDto chatDto,
                                  HttpServletRequest req) {
        chatsService.newChat(req, chatDto);
        return ResponseEntity.ok().build();
    }
}
