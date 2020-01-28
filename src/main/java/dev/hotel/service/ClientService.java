package dev.hotel.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	public List<Client> listerClient() {

		return clientRepository.findAll();
	}

	public List<Client> clientByNom(String nom) {

		return this.clientRepository.findByNom(nom);
		
	}
	
	public ResponseEntity<String> creerClient(Client client){

		if (clientRepository.existsByNomAndPrenoms(client.getNom(), client.getPrenoms())) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client possedant deja le meme nom prenoms");

		} else {

			clientRepository.save(new Client(client.getNom(), client.getPrenoms()));
			return ResponseEntity.status(HttpStatus.CREATED).body("Client enregistré");

		}
	}

}
