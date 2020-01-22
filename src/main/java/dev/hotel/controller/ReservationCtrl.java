package dev.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
public class ReservationCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationCtrl.class);

    private ReservationRepository reservationRepository;
    private ClientRepository clientRepository;
    private ChambreRepository chambreRepository;

    public ReservationCtrl(ReservationRepository reservationRepository, ClientRepository clientRepository,
            ChambreRepository chambreRepository) {

        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "reservations")
    public List<Reservation> listeReservations() {

        LOG.info("Affichage liste Reservations");
        return reservationRepository.findAll();

    }

    @RequestMapping(method = RequestMethod.POST, path = "reservations")
    public ResponseEntity<String> creerReservations(@RequestBody Reservation reservation) {

        if (!clientRepository.findById(reservation.getUuid()).isPresent()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client non trouvé");

        }

        for (Chambre ch : reservation.getChambres()) {

            if (!chambreRepository.findById(ch.getUuid()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La chambre" + ch.getUuid() + " n'existe pas");
            }

        }

        reservationRepository.save(reservation);

        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation Enregistrée");

    }

}