package ca.mcgill.ecse321.library.dto;

public class LibrarianDto extends OnlineUserDto{
	
	private boolean isHead;
	
	public LibrarianDto() {}
	
	public LibrarianDto(String firstname, String lastname, String address, boolean isLocal, String username, String password, String email, boolean isHead, Long id) {
		super(firstname, lastname, address, isLocal, username, password, email, id);
		this.isHead = isHead;
	}
	
	public boolean getIsHead() {
		return isHead;
	}
	public boolean setIsHead(boolean isHead) {
		this.isHead = isHead;
		return true;
	}
}