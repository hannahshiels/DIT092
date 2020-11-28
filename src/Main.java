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
        GridPane gui = new GridPane();
        Scene scene = new Scene(gui, 900, 700);
        InterfaceController.showMainMenu(primaryStage,scene);
    }

}
