package dev.hotel.controller;

import dev.hotel.entite.*;
import dev.hotel.service.ClientService;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
public class ClientCtrl {

	private static final Logger LOG = LoggerFactory.getLogger(ClientCtrl.class);

	private ClientService clientService;

	public ClientCtrl(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	public List<Client> listAllClient() {

		return clientService.listerClient();
	}

	@GetMapping(params = "nom")
	public List<Client> ClientNom(@RequestParam("nom") @Valid String nom) {

		LOG.info("Recherche du client : "+nom);
		
		return clientService.clientByNom(nom);
		

	}

	@PostMapping
	public ResponseEntity<String> createClient(@RequestBody @Valid Client client) {

		return clientService.creerClient(client);

	}

}