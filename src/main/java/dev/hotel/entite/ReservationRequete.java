package dev.hotel.entite;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class ReservationRequete {

	@NotNull
	private LocalDate dateDebut;
	@NotNull
	private LocalDate dateFin;
	@NotNull
	private UUID clientId;
	@NotNull
	private UUID[] chambres ;

	public ReservationRequete(LocalDate dateDebut, LocalDate dateFin, UUID clientUUID, UUID[] chambres) {
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientId = clientUUID;
		this.chambres = chambres;
	
	}



	/**Getter
	 * @return the chambres
	 */
	public UUID[] getChambres() {
		return chambres;
	}



	/**Setter
	 * @param chambres the chambres to set
	 */
	public void setChambres(UUID[] chambres) {
		this.chambres = chambres;
	}



	/**Getter
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}



	/**Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**Getter
	 * @return the clientUUID
	 */
	public UUID getClientId() {
		return clientId;
	}

	/**Setter
	 * @param clientUUID the clientUUID to set
	 */
	public void setClientId(UUID clientUUID) {
		this.clientId = clientUUID;
	}



}
