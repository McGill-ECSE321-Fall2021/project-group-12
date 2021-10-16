package ca.mcgill.ecse321.library.model;


import javax.persistence.Entity;

@Entity
public class OfflineUser extends User {

  public OfflineUser(String aFirstName, String aLastName, String aAddress, boolean aIsLocal, LibraryApplicationSystem aLibraryApplicationSystem)
  {
    super(aFirstName, aLastName, aAddress, aIsLocal, aLibraryApplicationSystem);
  }
  public void delete()
  {
    super.delete();
  }

}