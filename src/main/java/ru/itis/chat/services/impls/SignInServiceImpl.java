package ru.itis.chat.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.CookieDto;
import ru.itis.chat.forms.SignInForm;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.CookiesRepository;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.services.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CookiesRepository cookiesRepository;

    @Override
    public Optional<User> signIn(SignInForm form, HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Optional<User> userCandidate = usersRepository.findByUsername(form.getUsername());
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (encoder.matches(form.getPassword(), user.getPassword())) {
                String cookieValue = encoder.encode(form.getUsername() + form.getPassword());
                saveCookie(user, cookieValue);
                session.setAttribute("user", user.getId());
                resp.addCookie(getCookie(cookieValue));
                return userCandidate;
            } else throw new IllegalArgumentException("Wrong password");
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Optional<Cookie> cookieCandidate = Arrays.stream(cookies)
                .filter(cookie ->
                        cookie.getName().equals("chat_cookie")
                )
                .findAny();
        if (cookieCandidate.isPresent()) {
            Cookie cookie = cookieCandidate.get();
            Optional<CookieDto> cookieDtoCandidate = cookiesRepository.findByParameter(cookie.getValue());
            return cookieDtoCandidate.isPresent();
        } else return false;
    }

    private Cookie getCookie(String value) {
        Cookie cookie = new Cookie("chat_cookie", value);
        cookie.setMaxAge(-1);
        return cookie;
    }

    private void saveCookie(User user, String param) {
        cookiesRepository.save(CookieDto.builder()
                .parameter(param)
                .user(user)
                .build());
    }
}
