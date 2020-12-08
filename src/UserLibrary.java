import java.util.ArrayList;

public class UserLibrary {
    private ArrayList<User> userList = new ArrayList<>();

    public void addUser(User newUser) {
        userList.add(newUser);
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public User getUser(String email){
        for(User user : userList){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    //We'll probably need to add more methods here
    //maybe some getters?

}
