package dev.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationRequete;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;


@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	/**
	 * @param reservationRepository
	 * @param clientRepository
	 * @param chambreRepository
	 */
	public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	public List<Reservation> listerReservation() {

		return reservationRepository.findAll();
	}

	public ResponseEntity<String> creerReservation(ReservationRequete reservation) {

		if (!clientRepository.existsById(reservation.getClientId())) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client non trouvé");

		}

		for (UUID uuid : reservation.getChambres()) {

			if (!chambreRepository.existsById(uuid)) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La chambre" + uuid + " n'existe pas");
			}

		}

		Reservation res = new Reservation();
		res.setDateDebut(reservation.getDateDebut());
		res.setDateFin(reservation.getDateFin());
		List<Chambre> lC = new ArrayList<>();

		for (UUID uuid : reservation.getChambres()) {
			lC.add(new Chambre(uuid));
		}
		res.setChambres(lC);
		res.setClient(clientRepository.findById(reservation.getClientId()).get());
		reservationRepository.save(res);
		return ResponseEntity.status(HttpStatus.CREATED).body("Reservation Enregistrée");

	}

}
