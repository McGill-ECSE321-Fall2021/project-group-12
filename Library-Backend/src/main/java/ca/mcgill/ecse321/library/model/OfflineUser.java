package ca.mcgill.ecse321.library.model;


import javax.persistence.Entity;

@Entity
public class OfflineUser extends User {
	
  public void delete()
  {
    super.delete();
  }

}