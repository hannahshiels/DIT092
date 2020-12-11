package interfaces;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import users.User;

public class UserTasksInterface extends Interface {

    public UserTasksInterface(){
        super();
        super.setTitle("Miss Management | Current Tasks");
    }


    public AnchorPane showGUI(){
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | Current Tasks");
        GridPane userTasksPane = getUserTasksPane();
        root.getChildren().add(titlePane);
        root.getChildren().add(userTasksPane);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(userTasksPane,120.0);

        return root;
    }


    private GridPane getUserTasksPane(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);

        GridPane.setConstraints(backToManageProject, 0, 0);
        grid.getChildren().add(backToManageProject);

        grid.getStyleClass().add("grid");

        return grid;
    }




}
