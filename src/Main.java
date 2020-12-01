import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        testingInit();

        GridPane gui = new GridPane();
        Scene scene = new Scene(gui, 900, 700);
        scene.getStylesheets().add("css/styles.css");
        InterfaceController.showMainMenu(primaryStage,scene);
    }



    public void testingInit(){

        User user1 = new User("email@email.com", "firstName", "lastName", "password");
        User.addUser(user1);
        Project project1 = new Project("Project name 1", "Project Description 1", user1);
        Project.addProject(project1);
        Project project2 = new Project("Project name 2", "Project Description 2", user1);
        Project.addProject(project2);
        Project project3 = new Project("Project name 3", "Project Description 3", user1);
        Project.addProject(project3);
        User.listUsers();

    }


}
