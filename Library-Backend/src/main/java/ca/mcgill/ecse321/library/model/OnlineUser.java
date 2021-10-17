package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

@Entity
public class OnlineUser extends User
{
  private String username;
  private String password;
  private String email;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long onlineUserId;

  public OnlineUser(String aFirstName, String aLastName, String aAddress, boolean aIsLocal, LibraryApplicationSystem aLibraryApplicationSystem, String aUsername, String aPassword, String aEmail)
  {
    super(aFirstName, aLastName, aAddress, aIsLocal, aLibraryApplicationSystem);
    username = aUsername;
    password = aPassword;
    email = aEmail;
  }

  public OnlineUser() {}

  public void setUsername(String aUsername) {
    username = aUsername;
  }

  public void setPassword(String aPassword) {
    password = aPassword;
  }

  public void setEmail(String aEmail) {
    email = aEmail;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public void delete()
  {
    super.delete();
  }
}
