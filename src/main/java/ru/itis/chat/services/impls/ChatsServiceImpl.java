package ru.itis.chat.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.ChatsRepository;
import ru.itis.chat.services.ChatsService;
import ru.itis.chat.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatsServiceImpl implements ChatsService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ChatsRepository chatsRepository;

    @Override
    public List<Chat> getAllChats(HttpServletRequest req) {
        User user = getUser(req);
        List<Chat> chats = chatsRepository.findAllByUser(user);
        chats.addAll(user.getChats());
        return chats;
    }

    @Override
    public Chat getChatById(Long id) {
        Optional<Chat> chatCandidate = chatsRepository.find(id);
        if (chatCandidate.isPresent()) {
            return chatCandidate.get();
        } else throw new IllegalArgumentException("Chat not exist");
    }

    @Override
    public void newChat(HttpServletRequest req, ChatDto dto) {
        chatsRepository.save(Chat.builder()
                .owner(getUser(req))
                .title(dto.getTitle())
                .users(getUsers(dto.getUsers()))
                .build());
    }

    private User getUser(HttpServletRequest req) {
        Optional<User> user = Optional.ofNullable(usersService.getUserByCookie(req));
        if (user.isPresent()) {
            return user.get();
        } else throw new IllegalArgumentException("User not authorized");
    }

    private List<User> getUsers(Long[] id) {
        List<User> users = new ArrayList<>();
        for (Long l : id) {
            users.add(usersService.getUser(l));
        }
        return users;
    }
}
