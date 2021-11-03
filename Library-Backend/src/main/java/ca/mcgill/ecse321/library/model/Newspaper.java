package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Newspaper extends Item {
	
	public boolean setReservation(Reservation reservation) {
		return false;
	}

	public void delete() {
		super.delete();
	}

}
