import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;


public class InterfaceController  {

    private Stage stage;
    private Scene scene;
    private User currentUser;
    private AnchorPane gui;
    private MainMenuInterface mainMenuInterface;
    private RegisterInterface registerInterface;
    private LoginInterface loginInterface;
    private UserInterface userInterface;
    private CreateProjectInterface createProjectInterface;
    private UserProjectsInterface userProjectsInterface;
    private ProjectLibrary projectLibrary;
    private UserLibrary userLibrary;


    public InterfaceController(Stage stage){
        this.stage = stage;
        this.gui = new AnchorPane();
        this.scene = new Scene(gui,900,700);
        this.mainMenuInterface = new MainMenuInterface();
        this.registerInterface = new RegisterInterface();
        this.loginInterface = new LoginInterface();

      //  this.currentUser = new User("email@email.com", "firstName", "lastName", "password");

        this.userInterface = new UserInterface(currentUser);
        this.createProjectInterface = new CreateProjectInterface(currentUser);
        this.userProjectsInterface = new UserProjectsInterface(currentUser);
        this.createProjectInterface = new CreateProjectInterface(currentUser);
        this.projectLibrary = new ProjectLibrary();
        this.userLibrary = new UserLibrary();


    }

    public void setUser(User user){
        this.currentUser = user;
    }

    public User getUser(){
        return this.currentUser;
    }


    public void start(){
        this.scene.getStylesheets().add("css/styles.css");
        testingInit();
        showMainMenu();
    }



    private void changeScene(AnchorPane gui, String title){
        this.scene.setRoot(gui);
        this.stage.setTitle(title);
        this.stage.setScene(this.scene);
        this.stage.show();
    }



    private void showMainMenu(){
        AnchorPane gui = mainMenuInterface.getGUI();
        String title = mainMenuInterface.getTitle();
        changeScene(gui, title);


        Button registerBtn = mainMenuInterface.getRegisterBtn();
        registerBtn.setOnAction((EventHandler) event -> showRegister());
        Button loginBtn = mainMenuInterface.getLoginBtn();
        loginBtn.setOnAction((EventHandler) event -> showLogin());
    }


    private void showLogin(){
        AnchorPane gui = loginInterface.getGUI();
        String title = loginInterface.getTitle();
        Hyperlink mainMenuLink = loginInterface.getMainMenuLink();
        mainMenuLink.setOnAction((EventHandler) event -> showMainMenu());
        Hyperlink registerLink = loginInterface.getRegisterLink();
        registerLink.setOnAction((EventHandler) event -> showRegister());

        Button submitLoginBtn = loginInterface.getSubmitLoginBtn();
        submitLoginBtn.setOnAction((EventHandler) event -> {
            Label debug = loginInterface.getDebug();
            debug.setText("");
            String email = loginInterface.getUserEmail().getText();
            String password = loginInterface.getUserPassword().getText();
            if(email.length() == 0 || password.length() == 0){
                debug.setText("Enter all fields");
            } else if(userLibrary.isRegistered(email,password)){
                User currentUser = userLibrary.getUser(email);
                setUser(currentUser);
                showUserMenu();
            } else{
                debug.setText("Account not found.");
            }

        });

        changeScene(gui, title);
    }


    private void showRegister(){
        AnchorPane gui = registerInterface.getGUI();
        Hyperlink mainMenuLink = registerInterface.getMainMenuLink();
        mainMenuLink.setOnAction((EventHandler) event -> showMainMenu());
        Hyperlink loginLink = registerInterface.getLoginLink();
        loginLink.setOnAction((EventHandler) event -> showLogin());

          GridPane grid = (GridPane) gui.getChildren().get(1);
          Button createAccBtn = registerInterface.getCreateAccBtn();
          createAccBtn.setOnAction((EventHandler) event -> {
              Label debug = registerInterface.getDebug();
              debug.setText("");
              String email = registerInterface.getUserEmail().getText();
              String firstName = registerInterface.getUserFirstName().getText();
              String lastName = registerInterface.getUserLastName().getText();
              String password = registerInterface.getUserPassword().getText();
              String passwordConfirm = registerInterface.getUserPasswordConfirm().getText();
              if(email.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || password.length() == 0 || passwordConfirm.length() == 0){
                  debug.setText("Enter all fields");
              } else if(userLibrary.isEmailRegistered(email)){
                  debug.setText("Email is already in use. Log in instead.");
              } else if(!EmailValidation.isEmailValid(email)){
                  debug.setText("Email is not valid");
              }else if(password.length() < 8){
                  debug.setText("Password must be 8 or more characters");
              } else if(!password.equals(passwordConfirm)){
                  debug.setText("Passwords do not match.");
              }else{
                 grid.getChildren().remove(debug);
                  User newUser = new User(email,firstName,lastName,password);
                  userLibrary.addUser(newUser);
                  Hyperlink link = new Hyperlink("Account created. Log in.");
                  link.setOnAction((EventHandler) event1 -> showLogin());
                  GridPane.setConstraints(link, 0, 8);
                  grid.getChildren().add(link);
              }
          });
        String title = registerInterface.getTitle();
        changeScene(gui, title);
    }

    private void showUserMenu(){
        AnchorPane gui = userInterface.getGUI();
        String title = userInterface.getTitle();

        Button createAProjectBtn = userInterface.getCreateAProjectBtn();
        createAProjectBtn.setOnAction((EventHandler) event -> showCreateProjectMenu());

        Button projectsBtn = userInterface.getProjectsBtn();
        projectsBtn.setOnAction((EventHandler) event -> {
           projectLibrary.listAllUserProjects(getUser());
            showUserProjectsMenu();
        });
        Hyperlink logoutLink = userInterface.getLogoutLink();
        logoutLink.setOnAction((EventHandler) event -> {
            setUser(null);
            showMainMenu();
        });
        changeScene(gui, title);
    }

    private void showCreateProjectMenu(){
        AnchorPane gui = createProjectInterface.getGUI();
        String title = createProjectInterface.getTitle();
        Hyperlink backToUserMenu = createProjectInterface.getBackToUserMenuLink();
        backToUserMenu.setOnAction((EventHandler) event -> showUserMenu());

        Button createProjectBtn = createProjectInterface.getCreateProjectBtn();
        Label debug = createProjectInterface.getDebug();

        createProjectBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                debug.setText("");
                String projectNameText = createProjectInterface.getProjectName().getText();
                String projectDescriptionText = createProjectInterface.getProjectDescription().getText();
                if(projectNameText.length() == 0){
                    debug.setText("Please enter a project name");
                } else if(projectNameText.length() > 100){
                    debug.setText("Project name must be 100 characters or less");
                } else if(projectDescriptionText.length() > 250){
                    debug.setText("Project description must be 250 characters or less");
                } else{
                    Project newProject = new Project(projectNameText, projectDescriptionText,getUser());
                    projectLibrary.addProject(newProject);
                    showUserMenu();
                }

            }
        });



        changeScene(gui, title);
    }

    private void showUserProjectsMenu(){
        AnchorPane gui = userProjectsInterface.getGUI();
        String title = userProjectsInterface.getTitle();
        Hyperlink backToUserMenu = userProjectsInterface.getBackToUserMenuLink();

        backToUserMenu.setOnAction((EventHandler) event -> {
           showUserMenu();
        });

        changeScene(gui, title);
    }


    public void testingInit(){
        User user1 = new User("email@email.com", "firstName", "lastName", "password");
        userLibrary.addUser(user1);
        User user2 = new User("email2@email.com", "firstName", "lastName", "password");
        userLibrary.addUser(user2);
        Project project1 = new Project("Project name 1", "Project Description 1", user1);
        projectLibrary.addProject(project1);
        Project project2 = new Project("Project name 2", "Project Description 2", user1);
        projectLibrary.addProject(project2);
        Project project3 = new Project("Project name 3", "Project Description 3", user1);
        projectLibrary.addProject(project3);
        Project project4 = new Project("Project name 4", "Project Description 4", user2);
        projectLibrary.addProject(project4);
        Project project5 = new Project("Project name 5", "Project Description 5", user2);
        projectLibrary.addProject(project5);
        Project project6 = new Project("Project name 6", "Project Description 6", user2);
        projectLibrary.addProject(project6);
        userLibrary.listUsers();
    }



}
