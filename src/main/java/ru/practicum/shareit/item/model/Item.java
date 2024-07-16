package ru.practicum.shareit.item.model;

import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class Item {
    private Long id;
    private final String name;
    private final String description;
    private final Boolean available;
    private final User owner;
    private final ItemRequest request;
}
