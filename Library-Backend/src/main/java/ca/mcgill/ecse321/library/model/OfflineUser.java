package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

@Entity
public class OfflineUser extends User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long offlineUserId;


  public void delete()
  {
    super.delete();
  }

}
