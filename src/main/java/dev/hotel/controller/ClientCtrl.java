package dev.hotel.controller;

import dev.hotel.entite.*;
import dev.hotel.repository.ClientRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(ClientCtrl.class);

    private ClientRepository clientRepository;

    public ClientCtrl(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;

    }



    /*
     * @RequestMapping(method = RequestMethod.GET, path = "clients") public
     * List<Client> list() {
     * 
     * List<Client> list = new ArrayList<>(); 
     * try { list =
     * this.clientRepository.findAll(); }
     *  catch (EntityNotFoundException e) {
     * LOG.error("Problème d'accès à une donnée en base : " + e.getMessage()); }
     * LOG.info("Affichage liste Clients"); return list; }
     * 
     */
    
    @RequestMapping(method = RequestMethod.GET, path = "allclients")
    public List<Client> listAllClient() {

        LOG.info("Affichage liste Clients");
        
        List<Client> liste =  this.clientRepository.findAll();

        return liste;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, path = "clients")
    public Client ClientNom(@RequestParam("nom") String nom) {

        LOG.info("Affichage liste Clients");
        Client c = null;
        
        		if (this.clientRepository.findByNom(nom).isPresent()){
        			c  = this.clientRepository.findByNom(nom).get();       			
        		}

        return c;
    }
  

    @RequestMapping(method = RequestMethod.POST, path = "clients")
    public ResponseEntity<String> postClient(@RequestBody Client client) {

        

        if (clientRepository.findByNomAndPrenoms(client.getNom(),client.getPrenoms()).isPresent()) {

            LOG.info("Client Enregistré");
            clientRepository.save(new Client(client.getNom(),client.getPrenoms()));
            return ResponseEntity.status(HttpStatus.CREATED).body("Client enregistré");

          

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client possedant deja le meme nom prenoms");

        }

    }

}