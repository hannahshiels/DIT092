import javafx.scene.layout.AnchorPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LoginInterface extends Interface {

    private UserLibrary userLibrary;


    public LoginInterface(){
        super();
        super.setTitle("Miss Management | Login");
        this.userLibrary = new UserLibrary();
    }




    public AnchorPane showGUI(){
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | Login");
        GridPane loginPane = getLoginPane();

        root.getChildren().add(titlePane);
        root.getChildren().add(loginPane);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(loginPane,120.0);

        return root;
    }



    private GridPane getLoginPane(){
        GridPane grid = new GridPane();

        Label loginLabel = new Label("Login");

        grid.setPadding(new Insets(10, 0, 10, 0));
        grid.setVgap(10);
        grid.setHgap(5);

        GridPane.setConstraints(mainMenuLink, 0, 0);
        grid.getChildren().add(mainMenuLink);

        GridPane.setConstraints(loginLabel, 0, 1);
        grid.getChildren().add(loginLabel);

        userEmail.setPromptText("Enter email");
        GridPane.setConstraints(userEmail, 0, 2);

        grid.getChildren().add(userEmail);

        userPassword.setPromptText("Enter password");
        GridPane.setConstraints(userPassword, 0, 3);
        grid.getChildren().add(userPassword);

        GridPane.setConstraints(submitLoginBtn, 0, 4);
        grid.getChildren().add(submitLoginBtn);


        grid.getChildren().add(debug);
        GridPane.setConstraints(debug, 0, 5);
        debug.getStyleClass().add("debug");


        GridPane.setConstraints(registerLink, 0, 6);
        grid.getChildren().add(registerLink);
        GridPane.setHalignment(registerLink, HPos.RIGHT);

        grid.getStyleClass().add("grid");

        return grid;
    }




}
