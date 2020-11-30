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
    private User currentUser;

    CreateProjectInterface(Stage stage, Scene scene, User currentUser){
            this.gui = this.showGUI();
            this.title = "Miss Management | Create a project";
            this.stage = stage;
            this.scene = scene;
            this.currentUser = currentUser;
        }


        public GridPane getGUI(){
            return this.gui;
        }
    public User getCurrentUser(){
        return this.currentUser;
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
                    InterfaceController.switchToUserMenu(getCurrentUser(),getStage(), getScene());
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
            Label debug = new Label();
            grid.getChildren().add(debug);
            GridPane.setConstraints(debug, 0, 4);

            createProjectBtn.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    debug.setText("");
                    String projectNameText = projectName.getText();
                    String projectDescriptionText = projectDescription.getText();
                    if(projectNameText.length() == 0){
                        debug.setText("Please enter a project name");
                    } else if(projectNameText.length() > 100){
                        debug.setText("Project name must be 100 characters or less");
                    } else if(projectDescriptionText.length() > 250){
                        debug.setText("Project description must be 250 characters or less");
                    } else{
                       Project newProject = new Project(projectNameText, projectDescriptionText,getCurrentUser());
                       Project.addProject(newProject);
                       InterfaceController.switchToUserMenu(getCurrentUser(),getStage(), getScene());
                    }

                }
            });





            return grid;
        }

}
