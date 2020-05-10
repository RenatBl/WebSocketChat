package ru.itis.chat.services;

import ru.itis.chat.dto.UserDto;
import ru.itis.chat.forms.SignUpForm;

import java.util.Optional;

public interface SignUpService {
    Optional<UserDto> signUp(SignUpForm form);
}
