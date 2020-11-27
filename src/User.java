import java.util.Date;

public class User {
	private String userEmail;
	private String firstName;
	private String lastName;
	private String userPwd;
	private Date creationDate;
	
	User(String userEmail, String firstName, String lastName, String password) {
		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userPwd = password;
	}
	
	  public String toString() {
		return "Full name: " + this.firstName + " " + lastName + "User email: " + this.userEmail + "User password:" + "*hidden*" + "Created: " + this.creationDate;
		
	}

}
