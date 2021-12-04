package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

@Entity
public class OfflineUser extends User {

  public void delete()
  {
    super.delete();
  }

}
