package dev.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.service.ChambreService;


@RestController
@RequestMapping("chambres")
public class ChambreCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(ChambreCtrl.class);

    private ChambreService chambreService;


	public ChambreCtrl(ChambreService chambreService) {
		super();
		this.chambreService = chambreService;
	}



	@GetMapping
    public List<Chambre> listeChambre() {

        LOG.info("Liste Chambre");
        return chambreService.listerChambres();

    }

}