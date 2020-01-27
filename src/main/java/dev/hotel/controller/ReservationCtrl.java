package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        this.chambreRepository = chambreRepository;
    }
    
    /*
{
"dateDebut" : "2019-10-01",
"dateFin" : "2019-10-10",
"clientId" : "0ba57425-0904-4fdb-a128-7fe62ff0574c",
"chambres" : [
"73a970b6-0d25-46cc-9d03-5e1da5a29ca8",
"ad4aa45b-3dfb-4d26-80bd-cf170542c02f"
]
}



     * 
     * */

    @RequestMapping(method = RequestMethod.GET, path = "reservations")
    public List<Reservation> listeReservations() {

        LOG.info("Affichage liste Reservations");
        return reservationRepository.findAll();

    }

    

    @RequestMapping(method = RequestMethod.POST, path = "reservations")
    public ResponseEntity<String> creerReservations(@RequestBody ReservationRequete reservation) {

        if (!clientRepository.findById(reservation.getClientId()).isPresent()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client non trouvé");

        }
     

        for(UUID uuid : reservation.getChambres()){
        	      	
          if (!chambreRepository.findById(uuid).isPresent()) {
            		
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                      .body("La chambre" + uuid + " n'existe pas");
            }

        }
       
        Reservation res = new Reservation();
        res.setDateDebut(reservation.getDateDebut());
        res.setDateFin(reservation.getDateFin());
        List<Chambre> lC = new ArrayList<>();
        
        for(UUID uuid : reservation.getChambres()){
        	lC.add(new Chambre(uuid));       	
        }
        res.setChambres(lC);
        res.setClient(clientRepository.findById(reservation.getClientId()).get());
        reservationRepository.save(res);

        LOG.info("Reservation crée");
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation Enregistrée");

    }

}