package dev.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import dev.hotel.entite.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,UUID>{

    Optional <Hotel> findByNom(String nom);
    
}