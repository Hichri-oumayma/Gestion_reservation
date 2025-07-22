package com.reservation.service.model;

import com.reservation.service.entity.Reservation;

public class ReservationDetails {

    private Reservation reservation;
    private User user;
    private Event event;

    public ReservationDetails() {
    }

    public ReservationDetails(Reservation reservation, User user, Event event) {
        this.reservation = reservation;
        this.user = user;
        this.event = event;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
