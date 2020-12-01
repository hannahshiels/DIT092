import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuInterface {

    private GridPane gui;
    private String title;
    private Stage stage;
    private Scene scene;

    MainMenuInterface(Stage stage, Scene scene){
        this.gui = this.showGUI();
        this.title = "Miss Management | Main Menu";
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


    private GridPane showGUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 0, 20, 0));
        grid.setVgap(10);
        grid.setHgap(5);

        Label title = new Label("Miss Management");
        Label description = new Label("the program where project management isn't mismanaged");
        GridPane.setConstraints(title, 0, 0);
        grid.getChildren().add(title);
        GridPane.setConstraints(description, 0, 1);
        grid.getChildren().add(description);
        title.getStyleClass().add("title");
        description.getStyleClass().add("subtitle");

        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(description, HPos.CENTER);

        Button loginBtn = new Button("Log In");
        GridPane.setConstraints(loginBtn, 0, 2);
        grid.getChildren().add(loginBtn);

        loginBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToLogin(getStage(), getScene());
            }
        });

        Button registerBtn = new Button("Register");
       GridPane.setConstraints(registerBtn, 0, 3);
        grid.getChildren().add(registerBtn);

        registerBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToRegister(getStage(), getScene());
            }
        });


        Button infoBtn = new Button("Further Info");
       GridPane.setConstraints(infoBtn, 0, 4);
       grid.getChildren().add(infoBtn);



        return grid;
    }



}
