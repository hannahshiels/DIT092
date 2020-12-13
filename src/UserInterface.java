package interfaces;

import javafx.geometry.HPos;
import javafx.scene.layout.AnchorPane;
import users.User;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;


public class UserInterface extends Interface {


    public UserInterface(){
        super();
        super.setTitle("Miss Management | User Menu");
    }

    public AnchorPane showGUI() {
        AnchorPane root = new AnchorPane();

        GridPane titlePane = getTitlePane("Miss Management | User Menu");
        GridPane projectsPane = getProjectsPane();
        GridPane nav = getBottomNav();
        root.getChildren().add(titlePane);
        root.getChildren().add(projectsPane);
        root.getChildren().add(nav);

        AnchorPane.setTopAnchor(titlePane,0.0);
        AnchorPane.setTopAnchor(projectsPane,240.0);
        AnchorPane.setBottomAnchor(nav,0.0);



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


        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("center");


        return grid;

    }


    private GridPane getBottomNav(){
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setPadding(new Insets(20, 20, 20, 20));

        grid.getChildren().add(logoutLink);
        GridPane.setConstraints(logoutLink, 0, 2);
        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("right-align");


        return grid;
    }






}
