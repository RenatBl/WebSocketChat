package ru.itis.chat.repositories;

import ru.itis.chat.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
