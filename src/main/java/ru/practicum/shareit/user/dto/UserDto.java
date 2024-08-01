package ru.practicum.shareit.user.dto;

import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private final String name;

    @Email
    private final String email;
}
