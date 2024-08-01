package ru.practicum.shareit.booking.controller;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;



import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDto createBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                    @RequestBody BookingDto bookingDto) {
        return bookingService.addBooking(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                     @PathVariable @Positive long bookingId,
                                     @RequestParam boolean approved) {
        return bookingService.approveBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@RequestHeader("X-Sharer-User-Id") long userId,
                                     @PathVariable @Positive long bookingId) {
        return bookingService.getBookingById(userId, bookingId);
    }

    @GetMapping
    public List<BookingDto> getAllBookingByBookerId(@RequestHeader("X-Sharer-User-Id") long bookerId,
                                                    @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getAllBookingByBookerId(bookerId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getAllBookingByOwnerId(@RequestHeader("X-Sharer-User-Id") long ownerId,
                                                   @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getAllBookingByOwnerId(ownerId, state);
    }
}
