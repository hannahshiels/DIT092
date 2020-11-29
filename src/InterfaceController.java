import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfaceController {

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


    public static void showLogin(Stage stage, Scene scene){
        LoginInterface login = new LoginInterface(stage,scene) ;
        GridPane gui = login.getGUI();
        String title = login.getTitle();
        changeScene(stage,scene, gui, title);
    }



    public static void showMainMenu(Stage stage, Scene scene){
        MainMenuInterface mainMenu = new MainMenuInterface(stage,scene);
        GridPane gui = mainMenu.getGUI();
        String title = mainMenu.getTitle();
        changeScene(stage,scene, gui, title);
    }

    public static void showRegister(Stage stage, Scene scene){
        RegisterInterface register = new RegisterInterface(stage,scene);
        GridPane gui = register.getGUI();
        String title = register.getTitle();
        changeScene(stage,scene, gui, title);
    }


}
