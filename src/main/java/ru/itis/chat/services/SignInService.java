package ru.itis.chat.services;

import ru.itis.chat.forms.SignInForm;
import ru.itis.chat.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(SignInForm form, HttpServletRequest req, HttpServletResponse resp);
    boolean isAuthenticated(HttpServletRequest req);
}
