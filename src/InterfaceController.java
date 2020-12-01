import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfaceController {

    public static void start(Stage stage){
        GridPane gui = new GridPane();
        Scene scene = new Scene(gui, 900, 700);
        scene.getStylesheets().add("css/styles.css");
        showMainMenu(stage, scene);
    }

    public static void changeScene(Stage stage, Scene scene, GridPane gui, String title){
        scene.setRoot(gui);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToLogin(Stage stage, Scene scene){
        showLogin(stage,scene);
    }
    public static void switchToRegister(Stage stage, Scene scene){
        showRegister(stage,scene);
    }

    public static void switchToMainMenu(Stage stage, Scene scene){
        showMainMenu(stage,scene);
    }

    public static void switchToUserMenu(User user, Stage stage, Scene scene){
        showUserMenu(user,stage,scene);
    }

    public static void switchToProjectMenu(User user,Stage stage, Scene scene){
        showProjectMenu(user,stage,scene);
    }



    public static void showMainMenu(Stage stage, Scene scene){
        MainMenuInterface mainMenu = new MainMenuInterface(stage,scene);
        GridPane gui = mainMenu.getGUI();
        String title = mainMenu.getTitle();
        changeScene(stage,scene, gui, title);
    }


    public static void showLogin(Stage stage, Scene scene){
        LoginInterface login = new LoginInterface(stage,scene) ;
        GridPane gui = login.getGUI();
        String title = login.getTitle();
        changeScene(stage,scene, gui, title);
    }


    public static void showRegister(Stage stage, Scene scene){
        RegisterInterface register = new RegisterInterface(stage,scene);
        GridPane gui = register.getGUI();
        String title = register.getTitle();
        changeScene(stage,scene, gui, title);
    }

    public static void showUserMenu(User user, Stage stage, Scene scene){
        UserInterface userMenu = new UserInterface(user,stage,scene);
        GridPane gui = userMenu.getGUI();
        String title = userMenu.getTitle();
        changeScene(stage,scene, gui, title);
    }

    public static void showProjectMenu(User user,Stage stage, Scene scene){
        CreateProjectInterface createProjectMenu = new CreateProjectInterface(stage,scene, user);
        GridPane gui = createProjectMenu.getGUI();
        String title = createProjectMenu.getTitle();
        changeScene(stage,scene, gui, title);
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
