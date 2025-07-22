package com.reservation.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;  // Qui a réservé
    private Long eventId; // L'événement réservé
    private String dateReservation; // Date de réservation

    // Constructeurs
    public Reservation() {}

    public Reservation(Long id, Long userId, Long eventId, String dateReservation) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.dateReservation = dateReservation;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getDateReservation() { return dateReservation; }
    public void setDateReservation(String dateReservation) { this.dateReservation = dateReservation; }
}
