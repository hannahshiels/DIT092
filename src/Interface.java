import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class Interface {
    private GridPane gui;
    private String title;
    private Stage stage;
    private Scene scene;

   Interface(Stage stage, Scene scene){
        this.gui = this.showGUI();
        this.title = "Miss Management";
        this.stage = stage;
        this.scene = scene;
    }

    abstract public GridPane showGUI();

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Stage getStage(){
        return this.stage;
    }
    public Scene getScene(){
        return this.scene;
    }


    public GridPane getGUI(){
        return this.gui;
    }


}
