import javafx.scene.layout.AnchorPane;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class UserProjectsInterface extends Interface {

    public UserProjectsInterface(User currentUser){
        super();
        super.setTitle("Miss Management | Current Projects");
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

        grid.getStyleClass().add("grid");

        return grid;
    }




}
