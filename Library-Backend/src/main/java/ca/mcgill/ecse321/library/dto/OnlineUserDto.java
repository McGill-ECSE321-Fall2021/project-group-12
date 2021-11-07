package ca.mcgill.ecse321.library.dto;

public class OnlineUserDto {
    private String firstName;
    private String lastName;
    private String address;
    private boolean isLocal;
    private String username;
    private String password;
    private String email;
    private Long userId;
    
    public OnlineUserDto() {}

    public OnlineUserDto(String firstName, String lastName, String address, boolean isLocal, String username, String password, String email, Long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.isLocal = isLocal;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

   
}
