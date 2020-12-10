package interfaces;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CreateTaskInterface extends Interface{

    public CreateTaskInterface() {
        super();
        super.setTitle("Miss Management | User Menu");
    }

    public AnchorPane showGUI() {
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | Create a task");
        GridPane adminMenu = getCreateATask();
        root.getChildren().add(titlePane);
        root.getChildren().add(adminMenu);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(adminMenu,120.0);

        return root;
    }

    private GridPane getCreateATask(){
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);


        GridPane.setConstraints(backToManageProject, 0, 0);
        grid.getChildren().add(backToManageProject);

        taskName.setPromptText("Enter task name");
        taskName.setPrefColumnCount(10);
        GridPane.setConstraints(taskName, 0, 1);
        grid.getChildren().add(taskName);

        taskDesc.setPromptText("Enter task description");
        taskDesc.setPrefColumnCount(10);
        GridPane.setConstraints(taskDesc, 0, 2);
        grid.getChildren().add(taskDesc);

        GridPane.setConstraints(createATaskBtn, 0, 3);
        grid.getChildren().add(createATaskBtn);

        debug.getStyleClass().add("debug");
        grid.getChildren().add(debug);
        GridPane.setConstraints(debug, 0, 4);






        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("center");


        return grid;

    }




}
