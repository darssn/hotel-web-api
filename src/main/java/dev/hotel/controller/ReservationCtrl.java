package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationRequete;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;
import dev.hotel.service.ReservationService;

@RestController
@RequestMapping("reservations")
public class ReservationCtrl {

	private static final Logger LOG = LoggerFactory.getLogger(ReservationCtrl.class);

	private ReservationService reservationService;


	public ReservationCtrl(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}



	@GetMapping
	public List<Reservation> listeReservations() {
		LOG.info("Affichage liste Reservations");
		return reservationService.listerReservation();
	}

	@PostMapping
	public ResponseEntity<String> creerReservations(@RequestBody @Valid ReservationRequete reservation) {

		return reservationService.creerReservation(reservation);

	}

}