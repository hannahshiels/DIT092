import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class AdminInterface extends Interface {
    public AdminInterface(){
        super();
        super.setTitle("Miss Management | Admin");
    }

    public AnchorPane showGUI() {
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | Admin");
        GridPane adminMenu = getAdminOptions();
        root.getChildren().add(titlePane);
        root.getChildren().add(adminMenu);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(adminMenu,120.0);

        return root;
    }

    private GridPane getAdminOptions(){
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(5);


        GridPane.setConstraints(exportBtn, 0, 0);
        grid.getChildren().add(exportBtn);
        exportBtn.getStyleClass().add("btn");


        GridPane.setConstraints(logoutLink, 0, 1);
        grid.getChildren().add(logoutLink);
        GridPane.setHalignment(logoutLink, HPos.RIGHT);

        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("center");


        return grid;

    }

}
