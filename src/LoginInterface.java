import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //User login-form
        //User email

        Hyperlink menuLink = new Hyperlink("Back to Main Menu");
        GridPane.setConstraints(menuLink, 0, 0);
        grid.getChildren().add(menuLink);

        menuLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToMainMenu(getStage(), getScene());
            }
        });



        final TextField userEmail = new TextField();
        userEmail.setPromptText("Enter user email");
        userEmail.setPrefColumnCount(10);
        GridPane.setConstraints(userEmail, 0, 1);
        grid.getChildren().add(userEmail);

        //User password
        final PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Enter user password");
        userPassword.setPrefColumnCount(10);
        GridPane.setConstraints(userPassword, 0, 2);
        grid.getChildren().add(userPassword);

        //submission button
        Button submit = new Button ("Login");
        GridPane.setConstraints(submit, 0, 3);
        grid.getChildren().add(submit);


        Hyperlink createLink = new Hyperlink("Create an account instead");
        GridPane.setConstraints(createLink, 0, 4);
        grid.getChildren().add(createLink);

        createLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToRegister(getStage(), getScene());
            }
        });


        return grid;
    }


}

