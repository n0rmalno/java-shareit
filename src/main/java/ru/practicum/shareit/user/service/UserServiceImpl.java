package ru.practicum.shareit.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exeption.EmailAlreadyExistsException;
import ru.practicum.shareit.exeption.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        if (isUserHaveEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Пользователь с email " + userDto.getEmail() + " уже существует.");
        }

        User user = UserMapper.toUser(userDto);

        user = userRepository.save(user);
        userDto.setId(user.getId());
        log.info("Добавлен новый пользователь с ID = {}", user.getId());
        return userDto;
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        if (isUserHaveEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Пользователь с email " + userDto.getEmail() + " уже существует.");
        }
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        isUserPresent(optionalUser, userDto.getId());
        User oldUser = optionalUser.orElseThrow();
        String newEmail = userDto.getEmail();
        if (newEmail != null) {
            oldUser.setEmail(newEmail);
        }
        String newName = userDto.getName();
        if (newName != null) {
            oldUser.setName(newName);
        }
        User updatedUser = userRepository.save(oldUser);
        log.info("Пользователь с ID {} обновлён.", updatedUser.getId());
        return UserMapper.toUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        isUserPresent(optionalUser, id);
        UserDto userDto = UserMapper.toUserDto(optionalUser.orElseThrow());
        log.info("Пользователь с ID {} возвращён.", id);
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
        log.info("Пользователь с ID {} удалён.", id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Текущее количество пользователей: {}. Список возвращён.", users.size());
        return users.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    private void isUserPresent(Optional<User> optionalUser, long userId) {
        if (optionalUser.isEmpty()) {
            log.error("Пользователь с ИД {} отсутствует в БД.", userId);
            throw new NotFoundException(String.format("Пользователь с ИД %d отсутствует в БД.", userId));
        }
    }

    private boolean isUserHaveEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
