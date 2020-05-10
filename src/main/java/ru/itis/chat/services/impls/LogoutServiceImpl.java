package ru.itis.chat.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.CookiesRepository;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.services.LogoutService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CookiesRepository cookiesRepository;

    @Override
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("user");
        if (id != null) {
            Optional<User> user = usersRepository.find(id);
            if (user.isPresent()) {
                cookiesRepository.deleteByUser(user.get());
            } else {
                throw new IllegalStateException("User not found");
            }
        } else {
            throw new IllegalStateException("User not authenticated");
        }

        session.removeAttribute("user");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("chat_cookie".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }
}
