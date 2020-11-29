import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateProjectInterface {
    private GridPane gui;
    private String title;
    private Stage stage;
    private Scene scene;

    CreateProjectInterface(Stage stage, Scene scene){
            this.gui = this.showGUI();
            this.title = "Miss Management | Create a project";
            this.stage = stage;
            this.scene = scene;
        }


        public GridPane getGUI(){
            return this.gui;
        }

        public String getTitle(){
            return this.title;
        }

        public  Stage getStage(){
            return this.stage;
        }
        public Scene getScene(){
            return this.scene;
        }


        private GridPane showGUI() {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(20, 20, 20, 20));
            grid.setVgap(5);
            grid.setHgap(5);


            Hyperlink menuLink = new Hyperlink("Back to User Menu");
            GridPane.setConstraints(menuLink, 0, 0);
            grid.getChildren().add(menuLink);

            menuLink.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    InterfaceController.switchToUserMenu(getStage(), getScene());
                }
            });


            final TextField projectName = new TextField();
            projectName.setPromptText("Enter project name");
            projectName.setPrefColumnCount(10);
            GridPane.setConstraints(projectName, 0, 1);
            grid.getChildren().add(projectName);

            final TextArea projectDescription = new TextArea();
            projectDescription.setPromptText("Enter project description");
            projectDescription.setPrefColumnCount(10);
            GridPane.setConstraints(projectDescription, 0, 2);
            grid.getChildren().add(projectDescription);

            Button createProjectBtn = new Button ("Create project");
            GridPane.setConstraints(createProjectBtn, 0, 3);
            grid.getChildren().add(createProjectBtn);

            createProjectBtn.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    InterfaceController.switchToUserMenu(getStage(), getScene());
                }
            });





            return grid;
        }

}
