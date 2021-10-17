package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

@Entity
public class OfflineUser extends User {

  @Id
  public Long getUserId() {
	  return super.getUserId();
  }


  public void delete()
  {
    super.delete();
  }

}
