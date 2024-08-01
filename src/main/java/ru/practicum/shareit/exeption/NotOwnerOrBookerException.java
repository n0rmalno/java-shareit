package ru.practicum.shareit.exeption;

public class NotOwnerOrBookerException extends RuntimeException {
    public NotOwnerOrBookerException(String message) {
        super(message);
    }
}
