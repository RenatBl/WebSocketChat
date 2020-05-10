package ru.itis.chat.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogoutService {
    void logout(HttpServletRequest req, HttpServletResponse resp);
}
