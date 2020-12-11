import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ManageProjectInterface extends Interface {

    User user;

    public ManageProjectInterface(User currentUser){
        super();
        super.setTitle("Miss Management | Manage Project");
        this.user = currentUser;
    }


    public AnchorPane showGUI(){
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | Manage Project");
        GridPane manageProjectsMenuPane = getManageProjectsMenu();
        GridPane manageProjectsNav = getManageProjectsNav();

        root.getChildren().add(titlePane);
        root.getChildren().add(manageProjectsMenuPane);
        root.getChildren().add(manageProjectsNav);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(manageProjectsNav,120.0);
        AnchorPane.setTopAnchor(manageProjectsMenuPane,240.0);


        return root;
    }

    public GridPane getManageProjectsNav(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);


        GridPane.setConstraints(backToCurrentProjects, 0, 0);
        grid.getChildren().add(backToCurrentProjects);

        GridPane.setConstraints(projectNameLabel, 0, 1);
        grid.getChildren().add(projectNameLabel);

        GridPane.setConstraints(projectDescLabel, 0, 2);
        grid.getChildren().add(projectDescLabel);


        grid.getStyleClass().add("grid");

        return grid;
    }

    public GridPane getManageProjectsMenu(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);

        GridPane.setConstraints(createTaskBtn, 0, 0);
        grid.getChildren().add(createTaskBtn);

        GridPane.setConstraints(currentTasksBtn, 0, 1);
        grid.getChildren().add(currentTasksBtn);

        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("center");

        return grid;
    }


}
