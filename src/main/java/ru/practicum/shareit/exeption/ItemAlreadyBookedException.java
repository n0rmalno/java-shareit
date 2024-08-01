package ru.practicum.shareit.exeption;

public class ItemAlreadyBookedException extends RuntimeException {
    public ItemAlreadyBookedException(String message) {
        super(message);
    }
}
