package dev.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import dev.hotel.entite.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,UUID>{

    List<Hotel> findByNom(String nom);
    
}