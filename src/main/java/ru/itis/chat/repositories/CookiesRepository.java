package ru.itis.chat.repositories;

import ru.itis.chat.models.CookieDto;
import ru.itis.chat.models.User;

import java.util.Optional;

public interface CookiesRepository extends CrudRepository<CookieDto, Long> {
    Optional<CookieDto> findByParameter(String parameter);
    void deleteByUser(User user);
}
