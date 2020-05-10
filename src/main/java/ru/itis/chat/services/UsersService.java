package ru.itis.chat.services;

import ru.itis.chat.dto.UserDto;
import ru.itis.chat.forms.SignUpForm;
import ru.itis.chat.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UsersService {
    User getUser(Long id);
    UserDto getUserDto(Long id);
    List<UserDto> getAllUsers();
    User getUserByCookie(HttpServletRequest req);
}
