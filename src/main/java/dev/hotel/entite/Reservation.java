package dev.hotel.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends BaseEntite {


    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
	@JoinColumn(name = "uuid_client")
    private Client client;

    @ManyToMany
	@JoinTable(name = "reservation_chambres", 
	joinColumns = @JoinColumn(name = "uuid_reservation", referencedColumnName = "uuid"), 
    inverseJoinColumns = @JoinColumn(name = "uuid_chambre", referencedColumnName = "uuid"))
    private List<Chambre> chambres = new ArrayList<>();

    public Reservation() {
    }

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Client client, List<Chambre> chambres) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
        this.chambres = chambres;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }
}
