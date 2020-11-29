import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable {


	private String userEmail;
	private String firstName;
	private String lastName;
	private String userPwd;
	private Date creationDate;
	public static ArrayList<User> userList = new ArrayList<User>();
	
	User(String userEmail, String firstName, String lastName, String password) {
		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userPwd = password;
	}
	
	  public String toString() {
		return "Full name: " + this.firstName + " " + lastName + ". User email: " + this.userEmail + ". User password:" + " *hidden*" + ". Created: " + this.creationDate;
		
	}
	  
		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
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

		public String getUserPwd() {
			return userPwd;
		}

		public void setUserPwd(String userPwd) {
			this.userPwd = userPwd;
		}





}
