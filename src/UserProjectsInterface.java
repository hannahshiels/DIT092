import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class UserProjectsInterface extends Interface {

    private User currentUser;
    private ProjectLibrary projectLibrary;
    ArrayList<Project> userProjects = new ArrayList<>();

    public UserProjectsInterface(User currentUser){
        super();
        super.setTitle("Miss Management | Current Projects");
        this.currentUser = currentUser;
        this.projectLibrary = new ProjectLibrary();
        this.userProjects = projectLibrary.getAllUserProjects(currentUser);
    }


    public AnchorPane showGUI(){
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | Current Projects");
        GridPane userProjectsPane = getUserProjectsPane();
        root.getChildren().add(titlePane);
        root.getChildren().add(userProjectsPane);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(userProjectsPane,120.0);

        return root;
    }


    private GridPane getUserProjectsPane(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);


        GridPane.setConstraints(backToUserMenu, 0, 0);
        grid.getChildren().add(backToUserMenu);

        showAllProjects(1, grid);
        grid.getStyleClass().add("grid");

        return grid;
    }


    private void showAllProjects(int startNum, GridPane grid){
        // always returns null
        // need to fix
        if(userProjects == null){
            System.out.println("no projects yet");
        } else {
            for(int i = 0; i < userProjects.size(); i++){
                Button project = new Button(userProjects.get(i).getProjectName());
                GridPane.setConstraints(project, 0, startNum);
                grid.getChildren().add(project);
                startNum++;
            }
        }

    }

}
