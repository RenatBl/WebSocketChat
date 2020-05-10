package ru.itis.chat.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.forms.SignUpForm;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UsersRepository;
import ru.itis.chat.services.SignUpService;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Optional<UserDto> signUp(SignUpForm form) {
        UserDto userDto;
        Optional<User> optionalUser = repository.findByUsername(form.getUsername());
        if (optionalUser.isPresent()) {
            return Optional.empty();
        } else {
            User user = User.builder()
                    .username(form.getUsername())
                    .password(encoder.encode(form.getPassword()))
                    .build();
            repository.save(user);
            userDto = UserDto.get(user, "name");
            return Optional.ofNullable(userDto);
        }
    }
}
