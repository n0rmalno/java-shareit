package ru.practicum.shareit.booking.model;

import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class Booking {
    private Long id;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Item item;
    private final User booker;
    private String status;
}
