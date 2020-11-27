import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class registrationInterface extends Application {
	public void start(Stage primaryStage) throws Exception { 
	      /* 
	      Code for JavaFX application. 
	      (Stage, scene, scene graph) 
	      */     
		//DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	   // Date date = new Date();  

		// Window name
		primaryStage.setTitle("Miss Management - Register"); 
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		
		//User login-form
		
		final TextField userFirstName = new TextField();
		userFirstName.setPromptText("Enter first name");
		userFirstName.setPrefColumnCount(10);
		userFirstName.getText();
		GridPane.setConstraints(userFirstName, 0, 0);
		grid.getChildren().add(userFirstName);
		
		
		final TextField userLastName = new TextField();
		userLastName.setPromptText("Enter last name");
		userLastName.setPrefColumnCount(10);
		userLastName.getText();
		GridPane.setConstraints(userLastName, 0, 1);
		grid.getChildren().add(userLastName);
		//User email
		final TextField userEmail = new TextField();
		userEmail.setPromptText("Enter email");
		userEmail.setPrefColumnCount(10);
		userEmail.getText();
		GridPane.setConstraints(userEmail, 0, 2);
		grid.getChildren().add(userEmail);
		
		//User password
		final TextField userPassword = new TextField();
		userPassword.setPromptText("Enter user password");
		userPassword.setPrefColumnCount(10);
		userPassword.getText();
		GridPane.setConstraints(userPassword, 0, 3);
		grid.getChildren().add(userPassword);

		//User password
		final TextField userPasswordConfirm = new TextField();
		userPasswordConfirm.setPromptText("Confirm password");
		userPasswordConfirm.setPrefColumnCount(10);
		userPasswordConfirm.getText();
		GridPane.setConstraints(userPasswordConfirm, 0, 4);
		grid.getChildren().add(userPasswordConfirm);	
		
		//submission button
		Button submit = new Button ("Login");
		GridPane.setConstraints(submit, 0, 5);
		grid.getChildren().add(submit);

        Label debugLabel = new Label("User info:"); 
		grid.getChildren().add(debugLabel);	
		grid.setConstraints(debugLabel, 0, 6);
		//Setting the scene to Stage 
		Scene scene = new Scene(grid, 900, 700);
		primaryStage.setScene(scene); 
		

		
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	User account = new User(userEmail.getText(), userFirstName.getText(), userLastName.getText(), userPassword.getText());
                debugLabel.setText(account.toString()); 
            } 
        };
            submit.setOnAction(event);
		//Displaying the stage 
		primaryStage.show();

	   }         
	public static void main (String args[]) {
	      launch(args);      

	}
}
