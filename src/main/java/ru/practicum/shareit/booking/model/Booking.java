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
    public Long id;
    public final LocalDateTime start;
    public final LocalDateTime end;
    public final Item item;
    public final User booker;
    public String status;
}
