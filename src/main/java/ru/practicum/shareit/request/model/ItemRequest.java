package ru.practicum.shareit.request.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@Entity
@Table(name = "requests")
@AllArgsConstructor
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private final String description;

    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private final User requestor;

    @Column(name = "create_date")
    public final LocalDateTime created;
}
