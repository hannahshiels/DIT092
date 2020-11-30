import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginInterface {
    private GridPane gui;
    private String title;
    private Stage stage;
    private Scene scene;

    LoginInterface(Stage stage, Scene scene){
        this.gui = this.showGUI();
        this.title = "Miss Management | Login";
        this.stage = stage;
        this.scene = scene;
    }

    public GridPane getGUI(){
        return this.gui;
    }

    public String getTitle(){
        return this.title;
    }

    public Stage getStage(){
        return this.stage;
    }
    public Scene getScene(){
        return this.scene;
    }


    private GridPane showGUI(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 10, 0));
        grid.setVgap(10);
        grid.setHgap(5);

        Hyperlink menuLink = new Hyperlink("Back to Main Menu");
        GridPane.setConstraints(menuLink, 0, 0);
        grid.getChildren().add(menuLink);

        menuLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToMainMenu(getStage(), getScene());
            }
        });

        Label loginLabel = new Label("Login");
        GridPane.setConstraints(loginLabel, 0, 1);
        grid.getChildren().add(loginLabel);




        final TextField userEmail = new TextField();
        userEmail.setPromptText("Enter email");
        GridPane.setConstraints(userEmail, 0, 2);
        grid.getChildren().add(userEmail);

        //User password
        final PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Enter password");
        GridPane.setConstraints(userPassword, 0, 3);
        grid.getChildren().add(userPassword);

        // debug label
        Label debug = new Label();
        grid.getChildren().add(debug);
        GridPane.setConstraints(debug, 0, 5);
         debug.getStyleClass().add("debug");


        //submission button
        Button submit = new Button ("Login");
        GridPane.setConstraints(submit, 0, 4);
        grid.getChildren().add(submit);
        submit.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                debug.setText("");
                String email = userEmail.getText();
                String password = userPassword.getText();
                if(email.length() == 0 || password.length() == 0){
                    debug.setText("Enter all fields");
                } else if(User.isRegistered(email,password)){
                    User currentUser = User.getUser(email);
                    InterfaceController.switchToUserMenu(currentUser,getStage(), getScene());
                } else{
                    debug.setText("Account not found.");
                }

            }
        });




        Hyperlink createLink = new Hyperlink("Create an account instead");
        GridPane.setConstraints(createLink, 0, 6);
        grid.getChildren().add(createLink);
        GridPane.setHalignment(createLink, HPos.RIGHT);


        createLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToRegister(getStage(), getScene());
            }
        });


        return grid;
    }




}

