import java.util.ArrayList;

public class User {

    private String email;
    private String password;
    static ArrayList<User> users = new ArrayList<>();

    User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void addUser(User newUser){
        users.add(newUser);
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
    
    public void listUsers(){
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i).toString());
        }
    }

    public boolean isRegistered(String username, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equalsIgnoreCase(username) && users.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "email: " + this.email;
    }
}
