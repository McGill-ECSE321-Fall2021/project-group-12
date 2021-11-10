package ca.mcgill.ecse321.library.dto;

public class OfflineUserDto extends UserDto{

    public OfflineUserDto() {}
    
    public OfflineUserDto(String firstName, String lastName, String address, boolean isLocal, Long userId) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setIsLocal(isLocal);
        this.setUserId(userId);
    }
}
