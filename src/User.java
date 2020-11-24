public class User {

    private String username;
    private String name;
    private String password;

    User(String username, String name, String password){
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public boolean checkUsername(String inputUsername) {
        return this.username.equals(inputUsername);
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }


    @Override
    public String toString() {
        return "username: " + this.username;
    }
}
