import java.util.ArrayList;

public class UserLibrary {
    ArrayList<User> userList = new ArrayList<>();

    public void addUser(User newUser){
        userList.add(newUser);
    }

    public User getUser(String email){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getEmail().equals(email)){
                return userList.get(i);
            }
        }
        return null;
    }
    public  void listUsers(){
        for(int i = 0; i < userList.size(); i++){
            System.out.println(userList.get(i).toString());
        }
    }


    public boolean isEmailRegistered(String email){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean isRegistered(String email, String password){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getEmail().equals(email) && userList.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


}
