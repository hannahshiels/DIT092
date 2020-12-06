import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class UserProjectsInterface extends Interface {

    private User currentUser;
    static  ArrayList<Project> userProjects = new ArrayList<>();

    UserProjectsInterface(User currentUser,Stage stage, Scene scene){
        super(stage, scene);
        super.setTitle("Miss Management | Current Projects");
        this.currentUser = currentUser;
        this.userProjects = Project.getAllUserProjects(currentUser);
    }


    public User getCurrentUser() {
        return currentUser;
    }


    public GridPane showGUI(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);

        Hyperlink backToUserMenu = new Hyperlink("Back to User Menu");
        backToUserMenu.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                InterfaceController.switchToUserMenu(getCurrentUser(),getStage(), getScene());
            }
        });
        GridPane.setConstraints(backToUserMenu, 0, 0);
        grid.getChildren().add(backToUserMenu);
       int count = 2;

       for(int i = 0; i < userProjects.size(); i++){
            Button projectBtn = new Button(userProjects.get(i).getProjectName());
            GridPane.setConstraints(projectBtn, 0, count);
            grid.getChildren().add(projectBtn);
            projectBtn.setOnAction(new EventHandler() {
            	
            public void handle(Event event) {
            	InterfaceController.switchToManageProjectInterface(getCurrentUser(), getStage(), getScene());
            }
            });
            count++;
        }




        return grid;
    }

}
