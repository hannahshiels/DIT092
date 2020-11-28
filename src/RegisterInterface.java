import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


    private GridPane showGUI(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(5);
        grid.setHgap(5);

        Label label = new Label("Create an account");
        GridPane.setConstraints(label,0,0);
        grid.getChildren().add(label);

        TextField userEmail = new TextField();
        userEmail.setPromptText("Enter user email");

        GridPane.setConstraints(userEmail, 0, 1);
        grid.getChildren().add(userEmail);

        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Enter your password");

        GridPane.setConstraints(userPassword, 0, 2);
        grid.getChildren().add(userPassword);

        Button createBtn = new Button ("Create account");
        GridPane.setConstraints(createBtn, 0, 3);
        grid.getChildren().add(createBtn);
         return grid;

    }

}
