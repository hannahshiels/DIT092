import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterInterface{

    private GridPane gui;
    private String title;
    private Stage stage;
    private Scene scene;

    RegisterInterface(Stage stage, Scene scene){
        this.gui = this.showGUI();
        this.title = "Miss Management | Register";
        this.stage = stage;
        this.scene = scene;
    }

    public GridPane getGUI(){
        return this.gui;
    }

    public String getTitle(){
        return this.title;
    }

    public  Stage getStage(){
        return this.stage;
    }
    public Scene getScene(){
        return this.scene;
    }



    private GridPane showGUI(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(5);
        grid.setHgap(5);


        Hyperlink menuLink = new Hyperlink("Back to Main Menu");

        menuLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToMainMenu(getStage(), getScene());
            }
        });

        Label label = new Label("Create an account");

        final TextField userFirstName = new TextField();
        userFirstName.setPromptText("Enter first name");


        TextField userEmail = new TextField();
        userEmail.setPromptText("Enter email");

        final TextField userLastName = new TextField();
        userLastName.setPromptText("Enter last name");



        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Enter password (8 chars or more)");


        final PasswordField userPasswordConfirm = new PasswordField();
        userPasswordConfirm.setPromptText("Confirm password");



        Button createBtn = new Button ("Create account");



       Label debug = new Label();


        createBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                debug.setText("");
                String email = userEmail.getText();
                String firstName = userFirstName.getText();
                String lastName = userLastName.getText();
                String password = userPassword.getText();
                String passwordConfirm = userPasswordConfirm.getText();
                if(email.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || password.length() == 0 || passwordConfirm.length() == 0){
                    debug.setText("Enter all fields");
                } else if(User.isEmailRegistered(email)){
                    debug.setText("Email is already in use. Log in instead.");
                } else if(!EmailValidation.isEmailValid(email)){
                    debug.setText("Email is not valid.");
                }else if(password.length() < 8){
                    debug.setText("Password must be 8 or more characters");
                } else if(!password.equals(passwordConfirm)){
                    debug.setText("Passwords do not match.");
                }else{
                   //debug.setText("Account created.");
                    User newUser = new User(email,firstName,lastName,password);
                    User.users.add(newUser);
                    Hyperlink link = new Hyperlink("Account created. Log in.");
                    link.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            InterfaceController.switchToLogin(getStage(), getScene());
                        }
                    });
                    GridPane.setConstraints(link, 0, 8);
                    grid.getChildren().add(link);
                }
            }
        });

        Hyperlink loginLink = new Hyperlink("Log In instead");

        loginLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToLogin(getStage(), getScene());
            }
        });


        GridPane.setConstraints(menuLink, 0, 0);
        grid.getChildren().add(menuLink);
        GridPane.setConstraints(label,0,1);
        grid.getChildren().add(label);

        GridPane.setConstraints(userFirstName, 0, 2);
        grid.getChildren().add(userFirstName);
        GridPane.setConstraints(userLastName, 0, 3);
        grid.getChildren().add(userLastName);
        GridPane.setConstraints(userEmail, 0, 4);
        grid.getChildren().add(userEmail);
        GridPane.setConstraints(userPassword, 0, 5);
        grid.getChildren().add(userPassword);
        GridPane.setConstraints(userPasswordConfirm, 0, 6);
        grid.getChildren().add(userPasswordConfirm);

        GridPane.setConstraints(createBtn, 0, 7);
        grid.getChildren().add(createBtn);
        grid.getChildren().add(debug);
        GridPane.setConstraints(debug, 0, 8);
        grid.getChildren().add(loginLink);
        GridPane.setConstraints(loginLink, 0, 9);




         return grid;

    }

}
