import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInterface extends Interface {
        private User currentUser;


    UserInterface(User currentUser,Stage stage, Scene scene){
        super(stage, scene);
        super.setTitle("Miss Management | User Menu");
        this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public GridPane showGUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);

        Button createAProjectBtn = new Button("Create a project");
        GridPane.setConstraints(createAProjectBtn, 0, 0);
        grid.getChildren().add(createAProjectBtn);

        createAProjectBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToProjectMenu(getCurrentUser(),getStage(), getScene());
            }
        });

        Button projectsBtn = new Button("Current projects");
        GridPane.setConstraints(projectsBtn, 0, 1);
        grid.getChildren().add(projectsBtn);

        projectsBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              //  Project.listAllUserProjects(getCurrentUser());
                InterfaceController.switchToUserProjectsMenu(getCurrentUser(),getStage(), getScene());
            }
        });


        Hyperlink logoutLink = new Hyperlink("Log out");
        grid.getChildren().add(logoutLink);
        GridPane.setConstraints(logoutLink, 0, 2);

        logoutLink.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToMainMenu(getStage(), getScene());
            }
        });


        return grid;
    }

}
