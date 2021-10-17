package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

@Entity
public class OfflineUser extends User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long offlineUserId;

  public OfflineUser(String aFirstName, String aLastName, String aAddress, boolean aIsLocal, LibraryApplicationSystem aLibraryApplicationSystem)
  {
    super(aFirstName, aLastName, aAddress, aIsLocal, aLibraryApplicationSystem);
  }

  public OfflineUser() {
      super();
  }

  public void delete()
  {
    super.delete();
  }

}
