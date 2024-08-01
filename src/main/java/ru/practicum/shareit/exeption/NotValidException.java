package ru.practicum.shareit.exeption;

public class NotValidException extends RuntimeException {
    public NotValidException(String message) {
        super(message);
    }
}
