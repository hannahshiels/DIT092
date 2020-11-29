import java.util.ArrayList;
import java.time.LocalDate;

public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate creationDate;
    static ArrayList<User> users = new ArrayList<>();

    User(String email, String firstName, String lastName, String password){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.creationDate = CurrentDate.getCurrentDate();
    }

    public void addUser(User newUser){
        users.add(newUser);
    }


    public String getEmail(){
        return this.email;
    }

    public String getFirstName(){
        return this.email;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

        public String getLastName(){
        return this.password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public void listUsers(){
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i).toString());
        }
    }

    public static boolean isEmailRegistered(String email){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public static boolean isRegistered(String email, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "User: \n " + "email: " + this.email + " \n first name: " + this.firstName + "\n last name: " + this.lastName + " \n account created: " + this.creationDate;
    }
}
