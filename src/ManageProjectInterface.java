import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManageProjectInterface extends Interface {
    private User currentUser;

	ManageProjectInterface(User currentUser, Stage stage, Scene scene) {
		super(stage, scene);
        super.setTitle("Miss Management | User Menu");
        this.currentUser = currentUser;	
        }   
public User getCurrentUser(){
    return this.currentUser;
	}
public GridPane showGUI() {
	GridPane grid = new GridPane();
	grid.setPadding(new Insets(20, 20, 20, 20));
	grid.setVgap(10);
	grid.setHgap(5);
	
	Button CreateTaskBtn = new Button("Create a task");
	GridPane.setConstraints(CreateTaskBtn, 0, 0);
	grid.getChildren().add(CreateTaskBtn);
	
    return grid;
}
}
