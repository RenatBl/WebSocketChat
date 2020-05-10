package ru.itis.chat.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.CookieDto;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.CookiesRepository;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private CookiesRepository cookiesRepository;

    @Override
    public User getUserByCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Optional<Cookie> cookieCandidate = Arrays.stream(cookies)
                .filter(cookie ->
                        cookie.getName().equals("chat_cookie")
                )
                .findAny();
        if (cookieCandidate.isPresent()) {
            Cookie cookie = cookieCandidate.get();
            Optional<CookieDto> cookieDtoCandidate = cookiesRepository.findByParameter(cookie.getValue());
            return cookieDtoCandidate.map(CookieDto::getUser).orElse(null);
        } else return null;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> userCandidate = repository.find(id);
        if (userCandidate.isPresent()) {
            return userCandidate.get();
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public UserDto getUserDto(Long id) {
        User user = getUser(id);
        return UserDto.get(user, "all");
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDtoProcessor.process(repository.findAll());
    }

    private static class UserDtoProcessor {
        static List<UserDto> process(List<User> users) {
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user: users) {
                userDtoList.add(UserDto.get(user,"chat"));
            }
            return userDtoList;
        }
    }
}
