package com.reservation.service.controller;

import com.reservation.service.entity.Reservation;
import com.reservation.service.external.EventClient;
import com.reservation.service.external.UserClient;
import com.reservation.service.model.Event;
import com.reservation.service.model.ReservationDetails;
import com.reservation.service.model.User;
import com.reservation.service.repository.ReservationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private EventClient eventClient;

    @Operation(summary = "Créer une nouvelle réservation")
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Operation(summary = "Récupérer toutes les réservations")
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Operation(summary = "Récupérer une réservation avec les détails de l'utilisateur et de l'événement")
    @ApiResponse(responseCode = "200", description = "Réservation trouvée")
    @ApiResponse(responseCode = "404", description = "Réservation non trouvée")
    @GetMapping("/{id}/detail")
    public ReservationDetails getReservationWithDetails(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));

        // Appels Feign avec gestion d’erreur claire
        User user = null;
        Event event = null;

        try {
            user = userClient.getUserById(reservation.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + reservation.getUserId());
        }

        try {
            event = eventClient.getEventById(reservation.getEventId());
        } catch (Exception e) {
            throw new RuntimeException("Événement non trouvé avec l'ID : " + reservation.getEventId());
        }

        return new ReservationDetails(reservation, user, event);
    }
}
