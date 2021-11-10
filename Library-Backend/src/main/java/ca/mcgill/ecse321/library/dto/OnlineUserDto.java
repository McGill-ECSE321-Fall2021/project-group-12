package ca.mcgill.ecse321.library.dto;

public class OnlineUserDto extends UserDto{

    private String username;
    private String password;
    private String email;
    
    public OnlineUserDto() {}

    public OnlineUserDto(String firstName, String lastName, String address, boolean isLocal, String username, String password, String email, Long userId) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setIsLocal(isLocal);
        this.username = username;
        this.password = password;
        this.email = email;
        this.setUserId(userId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
}
