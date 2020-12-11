import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
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
    private ManageProjectInterface manageProjectInterface;
    private CreateTaskInterface createTaskInterface;
    private UserTasksInterface userTasksInterface;
    private ManageTaskInterface manageTaskInterface;
    private AdminInterface adminInterface;
    private ProjectLibrary projectLibrary;
    private UserLibrary userLibrary;
    private SysAdminLibrary sysAdminLibrary;
    private TaskLibrary taskLibrary;



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
        this.manageProjectInterface = new ManageProjectInterface(currentUser);
        this.createTaskInterface = new CreateTaskInterface();
        this.userTasksInterface = new UserTasksInterface();
        this.manageTaskInterface = new ManageTaskInterface();

        this.adminInterface = new AdminInterface();

        this.taskLibrary = new TaskLibrary();
        this.projectLibrary = new ProjectLibrary();
        this.userLibrary = new UserLibrary();
        this.sysAdminLibrary = new SysAdminLibrary();



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
            } else if(sysAdminLibrary.isRegistered(email, password)){
                SysAdmin admin = (SysAdmin) sysAdminLibrary.getUser(email);
                setUser(admin);
                showAdmin();

            }else{
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
        Label debug = registerInterface.getDebug();


        createAccBtn.setOnAction((EventHandler) event -> {
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
              } else{
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
          // projectLibrary.listAllUserProjects(getUser());
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

        TextField projectNameField = createProjectInterface.getProjectName();
        TextArea projectDescriptionField = createProjectInterface.getProjectDescription();



        createProjectBtn.setOnAction((EventHandler) event -> {
            debug.setText("");
            String projectNameText = projectNameField.getText();
            String projectDescriptionText = projectDescriptionField.getText();
            if(projectNameText.length() == 0){
                debug.setText("Please enter a project name");
            } else if(projectNameText.length() > 100){
                debug.setText("Project name must be 100 characters or less");
            } else if(projectDescriptionText.length() > 250){
                debug.setText("Project description must be 250 characters or less");
            } else{
                Project newProject = new Project(projectNameText, projectDescriptionText,getUser());
                projectLibrary.addProject(newProject);
                projectNameField.setText("");
                projectDescriptionField.setText("");
                showUserMenu();
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

        GridPane grid = (GridPane) gui.getChildren().get(1);
        grid.getChildren().remove(1,grid.getChildren().size());
        ArrayList<Project> userProjects = projectLibrary.getAllUserProjects(getUser());
        int startNum = 1;

            for(int i = 0; i < userProjects.size(); i++){
                Button project = new Button(userProjects.get(i).getProjectName());
                int currentProject = i;
                project.setOnAction((EventHandler) event -> {
                    showManageProjectInterface(userProjects.get(currentProject));
                });
                GridPane.setConstraints(project, 0, startNum);
                grid.getChildren().add(project);
                startNum++;
            }

        changeScene(gui, title);
    }


    private void showManageProjectInterface(Project project){
        AnchorPane gui = manageProjectInterface.getGUI();
        String title = manageProjectInterface.getTitle();

        Label projectName = manageProjectInterface.getProjectNameLabel();
        Label projectDesc = manageProjectInterface.getProjectDescLabel();

        projectName.setText(project.getProjectName());
        projectDesc.setText(project.getProjectDescription());

        Button createATask = manageProjectInterface.getCreateTaskBtn();
        createATask.setOnAction((EventHandler) event -> {
            showCreateTaskMenu(project.getProjectID());
        });

        Button currentTasksBtn = manageProjectInterface.getCurrentTasksBtn();
        currentTasksBtn.setOnAction((EventHandler) event -> {
            showUserTasksMenu(project.getProjectID(), getUser());
        });



        Hyperlink link = manageProjectInterface.getBackToCurrentProjects();
        link.setOnAction((EventHandler) event -> {
            showUserProjectsMenu();
        });



        changeScene(gui, title);
    }

    private void showCreateTaskMenu(String ID){
        AnchorPane gui = createTaskInterface.getGUI();
        String title = createTaskInterface.getTitle();

        Hyperlink backToManageProject = createTaskInterface.getBackToManageProject();
        Button createTask = createTaskInterface.getCreateATaskBtn();
        TextField taskName = createTaskInterface.getTaskName();
        TextArea taskDesc = createTaskInterface.getTaskDesc();
        Label debug = createTaskInterface.getDebug();

        backToManageProject.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              showManageProjectInterface(projectLibrary.getProject(ID));
            }
        });

        createTask.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                debug.setText("");
                String taskText = taskName.getText();
                String taskDescriptionText = taskDesc.getText();
                if(taskText.length() == 0){
                    debug.setText("Please enter a task name");
                } else if(taskText.length() > 100){
                    debug.setText("Task name must be 100 characters or less");
                } else if(taskDescriptionText.length() > 200){
                    debug.setText("Task description must be 200 characters or less");
                } else{
                    Task newTask = new Task(getUser(),ID, taskText, taskDescriptionText);
                    taskLibrary.addTask(newTask);
                    showManageProjectInterface(projectLibrary.getProject(ID));
                }

            }
        });
        changeScene(gui, title);

    }

    private void showUserTasksMenu(String projectID, User user ){
        AnchorPane gui = userTasksInterface.getGUI();
        String title = userTasksInterface.getTitle();

        Hyperlink backToManageProjects = userTasksInterface.getBackToManageProject();


        backToManageProjects.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showManageProjectInterface(projectLibrary.getProject(projectID));
            }
        });



        GridPane grid = (GridPane) gui.getChildren().get(1);
        grid.getChildren().remove(1,grid.getChildren().size());

        ArrayList<Task> userTasks = taskLibrary.getAllUserTasks(getUser(),projectID);
        int startNum = 1;
        for(int i = 0; i < userTasks.size(); i++){
            Button taskBtn = new Button(userTasks.get(i).getTaskName());
            int currentTask = i;

            ChoiceBox taskProgressCb = new ChoiceBox();
            taskProgressCb.getItems().addAll("Not started", "In progress", "Done");
            taskProgressCb.setValue(userTasks.get(currentTask).getTaskProgress());


            taskProgressCb.setOnAction(new EventHandler(){
                @Override
                public void handle(Event event) {
                    String progress = taskProgressCb.getValue().toString();
                    if(progress.equalsIgnoreCase("Not started")){
                        userTasks.get(currentTask).setTaskNotStarted();
                    } else if(progress.equalsIgnoreCase("In progress")){
                        userTasks.get(currentTask).setTaskInProgress();
                    } else if(progress.equalsIgnoreCase("Done")){
                        userTasks.get(currentTask).setTaskDone();
                    }
                }
            });


            taskBtn.setOnAction((EventHandler) event -> {
               showManageTask(userTasks.get(currentTask));
            });
            GridPane.setConstraints(taskBtn, 0, startNum);
            grid.getChildren().add(taskBtn);
            GridPane.setConstraints(taskProgressCb, 1, startNum);
            grid.getChildren().add(taskProgressCb);
            startNum++;
        }



        changeScene(gui, title);

    }

    private void showManageTask(Task task){
        AnchorPane gui = manageTaskInterface.getGUI();
        String title = manageTaskInterface.getTitle();
        Hyperlink backToCurrentTasks = manageTaskInterface.getBackToCurrentTasks();

        Label taskNameLabel = manageTaskInterface.getTaskNameLabel();
        Label taskDescLabel = manageTaskInterface.getTaskDescLabel();
        Label taskProgressLabel = manageTaskInterface.getTaskProgressLabel();
        taskNameLabel.setText("Task name:" + task.getTaskName());
        taskDescLabel.setText("Task description: " + task.getTaskDescription());
        taskProgressLabel.setText("Progress: " + task.getTaskProgress());


        backToCurrentTasks.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showUserTasksMenu(task.getProjectID(), getUser());
            }
        });


        changeScene(gui, title);


    }


    private void showAdmin(){
        AnchorPane gui = adminInterface.getGUI();
        String title = adminInterface.getTitle();

        Button createExport = adminInterface.getExportBtn();
        Hyperlink logoutLink = adminInterface.getLogoutLink();

        createExport.setOnAction((EventHandler) event ->{
            Export.createUserDataExport(userLibrary.exportUserData());
        });

        logoutLink.setOnAction((EventHandler) event -> {
            setUser(null);
            showMainMenu();
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
     //   projectLibrary.listAllProjects();
        sysAdminLibrary.addSysAdmin("admin@email.com", "hannah", "shiels", "password");
    }



}
