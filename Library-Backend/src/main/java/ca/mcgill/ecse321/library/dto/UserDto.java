package ca.mcgill.ecse321.library.dto;

public abstract class UserDto {
	
	private String firstname;
	private String lastName;
	private String address;
	private boolean isLocal;
	
    public String getFirstName() {
        return firstname;
    }

    public boolean setFirstName(String firstName) {
        this.firstname = firstName;
        return true;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setLastName(String lastname) {
        this.lastName = lastname;
        return true;
    }

    public String getAddress() {
        return address;
    }

    public boolean setAddress(String address) {
        this.address = address;
        return true;
    }

    public boolean getIsLocal() {
        return isLocal;
    }

    public boolean setIsLocal(boolean local) {
        isLocal = local;
        return true;
    }
}
