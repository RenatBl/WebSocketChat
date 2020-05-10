package ru.itis.chat.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.ChatsRepository;
import ru.itis.chat.repositories.MessagesRepository;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.services.MessagesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ChatsRepository chatsRepository;

    @Override
    public List<Message> getAllMessagesByChat(Chat chat) {
        return messagesRepository.getAllByChat(chat);
    }

    @Override
    public void saveMessage(MessageDto messageDto) {
        User user = usersRepository.findByUsername(messageDto.getOwner())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not fond")
                );
        Chat chat = chatsRepository.find(messageDto.getChatId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Chat not found")
                );

        messagesRepository.save(Message.builder()
                .owner(user)
                .content(messageDto.getContent())
                .chat(chat)
                .creationTime(LocalDateTime.parse(messageDto.getDate()))
                .build());
    }
}
