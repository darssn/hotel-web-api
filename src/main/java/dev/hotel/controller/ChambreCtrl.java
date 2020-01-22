package dev.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.HotelRepository;

@RestController
public class ChambreCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(ChambreCtrl.class);

    private ChambreRepository chambreRepository;
    private HotelRepository hotelRepository;

    public ChambreCtrl(ChambreRepository chambreRepository, HotelRepository hotelRepository) {
        this.chambreRepository = chambreRepository;
        this.hotelRepository = hotelRepository;
    }



    @RequestMapping(method = RequestMethod.GET, path = "chambres")
    public List<Chambre> listeChambre() {

        LOG.info("Liste Chambre");
        return chambreRepository.findAll();

    }

}