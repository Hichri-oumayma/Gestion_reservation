package com.reservation.service.service;

import com.reservation.service.entity.Reservation;
import com.reservation.service.external.UserClient;
import com.reservation.service.external.EventClient;
import com.reservation.service.model.User;
import com.reservation.service.model.Event;
import com.reservation.service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private EventClient eventClient;

    public Reservation createReservation(Reservation reservation) {
        // Vérifie que l'utilisateur existe
        User user = userClient.getUserById(reservation.getUserId());

        // Vérifie que l'événement existe
        Event event = eventClient.getEventById(reservation.getEventId());

        // Sauvegarde la réservation seulement si tout est ok
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
