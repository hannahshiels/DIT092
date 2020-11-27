import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class loginInterface extends Application {
	public void start(Stage primaryStage) throws Exception { 
	      /* 
	      Code for JavaFX application. 
	      (Stage, scene, scene graph) 
	      */     
		
		// Window name
		primaryStage.setTitle("Miss Management"); 
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		
		//User login-form
		//User email
		final TextField userEmail = new TextField();
		userEmail.setPromptText("Enter user email");
		userEmail.setPrefColumnCount(10);
		userEmail.getText();
		GridPane.setConstraints(userEmail, 0, 0);
		grid.getChildren().add(userEmail);
		
		//User password
		final TextField userPassword = new TextField();
		userPassword.setPromptText("Enter user password");
		userPassword.setPrefColumnCount(10);
		userPassword.getText();
		GridPane.setConstraints(userPassword, 0, 1);
		grid.getChildren().add(userPassword);
		
		//submission button
		Button submit = new Button ("Login");
		GridPane.setConstraints(submit, 0, 3);
		grid.getChildren().add(submit);

		//Setting the scene to Stage 
		Scene scene = new Scene(grid, 900, 700);
		primaryStage.setScene(scene); 
		
 
		//Displaying the stage 
		primaryStage.show();

	   }         
	public static void main (String args[]) {
	      launch(args);      

	}

}
