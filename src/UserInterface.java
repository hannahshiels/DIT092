import javafx.geometry.HPos;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;


public class UserInterface extends Interface {
        private User currentUser;


    public UserInterface(User currentUser){
        super();
        super.setTitle("Miss Management | User Menu");
        this.currentUser = currentUser;
    }

    public AnchorPane showGUI() {
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | User Menu");
        GridPane projectsPane = getProjectsPane();
        root.getChildren().add(titlePane);
        root.getChildren().add(projectsPane);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(projectsPane,120.0);

        return root;

    }


    private GridPane getProjectsPane(){
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);
        GridPane.setConstraints(createAProjectBtn, 0, 0);
        grid.getChildren().add(createAProjectBtn);

        GridPane.setConstraints(projectsBtn, 0, 1);
        grid.getChildren().add(projectsBtn);

        grid.getChildren().add(logoutLink);
        GridPane.setConstraints(logoutLink, 0, 2);

        GridPane.setHalignment(logoutLink, HPos.RIGHT);
        grid.getStyleClass().add("grid");

        return grid;

    }



}
