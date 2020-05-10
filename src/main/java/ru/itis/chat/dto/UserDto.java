package ru.itis.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.chat.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private int chats;

    public static UserDto get(User user, String form) {
        switch (form) {
            case "all": {
                return UserDto.builder()
                        .id(user.getId())
                        .name(user.getUsername())
                        .build();
            }
            case "name": {
                return UserDto.builder()
                        .name(user.getUsername())
                        .build();
            }
            case "chat": {
                return UserDto.builder()
                        .id(user.getId())
                        .name(user.getUsername())
                        .chats(user.getChats().size())
                        .build();
            }
            default: return null;
        }
    }
}
