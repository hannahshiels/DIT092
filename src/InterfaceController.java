import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
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
    private UserMeetingInterface userMeetingInterface;
    private CreateMeetingInterface createMeetingInterface;
    private AddUserInterface addUserInterface;
    private TaskMenuInterface taskMenuInterface;
    private CreateTaskInterface createTaskInterface;
    private UserTasksInterface userTasksInterface;
    private ManageTaskInterface manageTaskInterface;
    private AllProjectTasksInterface allProjectTasksInterface;
    private UserSalaryInterface userSalaryInterface;
    private AddSalaryInterface addSalaryInterface;
    private AdminInterface adminInterface;

    private ProjectLibrary projectLibrary;
    private UserLibrary userLibrary;
    private SysAdminLibrary sysAdminLibrary;
    private TaskLibrary taskLibrary;
    private RoleLibrary roleLibrary;
    private SalaryLibrary salaryLibrary;

    private Email emailFunction;


    public InterfaceController(Stage stage){
        this.stage = stage;
        this.gui = new AnchorPane();
        this.scene = new Scene(gui,900,700);
        this.mainMenuInterface = new MainMenuInterface();
        this.registerInterface = new RegisterInterface();
        this.loginInterface = new LoginInterface();

      //  this.currentUser = new User("email@email.com", "firstName", "lastName", "password");

        this.userInterface = new UserInterface();
        this.createProjectInterface = new CreateProjectInterface();
        this.userProjectsInterface = new UserProjectsInterface();
        this.createProjectInterface = new CreateProjectInterface();
        this.manageProjectInterface = new ManageProjectInterface();
        this.userMeetingInterface = new UserMeetingInterface();
        this.createMeetingInterface = new CreateMeetingInterface();
        this.addUserInterface = new AddUserInterface();
        this.taskMenuInterface = new TaskMenuInterface();
        this.createTaskInterface = new CreateTaskInterface();
        this.userTasksInterface = new UserTasksInterface();
        this.manageTaskInterface = new ManageTaskInterface();
        this.allProjectTasksInterface = new AllProjectTasksInterface();
        this.addSalaryInterface = new AddSalaryInterface();
        this.userSalaryInterface = new UserSalaryInterface();

        this.adminInterface = new AdminInterface();

        this.taskLibrary = new TaskLibrary();
        this.projectLibrary = new ProjectLibrary();
        this.userLibrary = new UserLibrary();
        this.roleLibrary = new RoleLibrary();
        this.sysAdminLibrary = new SysAdminLibrary();
        this.salaryLibrary = new SalaryLibrary();

        this.emailFunction = new Email();
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
        registerBtn.setOnAction(event -> showRegister());
        Button loginBtn = mainMenuInterface.getLoginBtn();
        loginBtn.setOnAction(event -> showLogin());
    }

    private void showLogin(){
        AnchorPane gui = loginInterface.getGUI();
        String title = loginInterface.getTitle();
        Hyperlink mainMenuLink = loginInterface.getMainMenuLink();
        mainMenuLink.setOnAction(event -> showMainMenu());
        Hyperlink registerLink = loginInterface.getRegisterLink();
        registerLink.setOnAction(event -> showRegister());

        Label debug = loginInterface.getDebug();
        debug.setText("");

        Button submitLoginBtn = loginInterface.getSubmitLoginBtn();
        submitLoginBtn.setOnAction(event -> {
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
        mainMenuLink.setOnAction(event -> showMainMenu());
        Hyperlink loginLink = registerInterface.getLoginLink();
        loginLink.setOnAction(event -> showLogin());

        Button createAccBtn = registerInterface.getCreateAccBtn();
        Label debug = registerInterface.getDebug();
        TextField emailField = registerInterface.getUserEmail();
        TextField firstNameField = registerInterface.getUserFirstName();
        TextField lastNameField = registerInterface.getUserLastName();
        PasswordField passwordField = registerInterface.getUserPassword();
        PasswordField passwordConfirmField = registerInterface.getUserPasswordConfirm();

        debug.setText("");
        emailField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        passwordConfirmField.setText("");
        createAccBtn.setOnAction(event -> {
            debug.setText("");
            String email = emailField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            String passwordConfirm = passwordConfirmField.getText();
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
                User newUser = new User(email,firstName,lastName,password);
                userLibrary.addUser(newUser);
                Alert accCreatedAlert = new Alert(Alert.AlertType.CONFIRMATION);
                accCreatedAlert.headerTextProperty().set("Account successfully created!");
                accCreatedAlert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        showLogin();
                    }
                });
            }
        });
        String title = registerInterface.getTitle();
        changeScene(gui, title);
    }

    private void showUserMenu(){
        AnchorPane gui = userInterface.getGUI();
        String title = userInterface.getTitle();

        Button createAProjectBtn = userInterface.getCreateAProjectBtn();
        createAProjectBtn.setOnAction(event -> showCreateProjectMenu());

        Button projectsBtn = userInterface.getProjectsBtn();
        projectsBtn.setOnAction(event -> showUserProjectsMenu());
        Hyperlink logoutLink = userInterface.getLogoutLink();
        logoutLink.setOnAction(event -> {
            setUser(null);
            showMainMenu();
        });
        changeScene(gui, title);
    }

    private void showCreateProjectMenu(){
        AnchorPane gui = createProjectInterface.getGUI();
        String title = createProjectInterface.getTitle();
        Hyperlink backToUserMenu = createProjectInterface.getBackToUserMenuLink();
        backToUserMenu.setOnAction(event -> showUserMenu());

        Button createProjectBtn = createProjectInterface.getCreateProjectBtn();
        Label debug = createProjectInterface.getDebug();

        TextField projectNameField = createProjectInterface.getProjectName();
        TextArea projectDescriptionField = createProjectInterface.getProjectDescription();

        createProjectBtn.setOnAction(event -> {
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
                Role newRole = new Role(getUser(), newProject.getProjectID());
                newRole.setRoleProjectCreator();
                roleLibrary.addRole(newRole);
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

        backToUserMenu.setOnAction(event -> showUserMenu());

        GridPane grid = (GridPane) gui.getChildren().get(2);
        grid.getChildren().remove(0,grid.getChildren().size());
        ArrayList<String> allUserProjects = roleLibrary.getAllUserProjects(getUser());
        ArrayList<Project> userProjects = projectLibrary.getProjects(allUserProjects);
        int startNum = 0;

        for(int i = 0; i < userProjects.size(); i++){
            Button project = new Button(userProjects.get(i).getProjectName());
            int currentProject = i;
            project.setOnAction(event -> showManageProjectInterface(userProjects.get(currentProject)));
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

        Button addUserBtn = manageProjectInterface.getAddUserBtn();
        addUserBtn.setOnAction(event -> showAddUserInterface(project.getProjectID()));

        Button tasksBtn = manageProjectInterface.getTasksBtn();
        tasksBtn.setOnAction(event -> showTaskMenu(project.getProjectID()));

        Button createMeetingBtn = manageProjectInterface.getShowMeetingMenuBtn();
        createMeetingBtn.setOnAction((EventHandler) event -> {
            showUserMeetingInterface(project.getProjectID());
        });

        Button addSalaryBtn = manageProjectInterface.getAddSalaryBtn();
        addSalaryBtn.setOnAction(event -> showAddSalaryInterface(project.getProjectID()));


        Button currentSalariesBtn = manageProjectInterface.getCurrentSalariesBtn();
        currentSalariesBtn.setOnAction(event -> showUserSalaryInterface(project.getProjectID()));

        Role role = roleLibrary.getUserRoleInProject(project.getProjectID(), getUser());
        GridPane grid = (GridPane) gui.getChildren().get(1);

         if(!roleLibrary.doesScrumMasterExist(project.getProjectID()) && role.getRole().equals("Project Creator") || role.getRole().equals("Scrum Master")) {
             if (!grid.getChildren().contains(addUserBtn)) {
                 grid.getChildren().add(addUserBtn);
             }
         } else{
             grid.getChildren().remove(addUserBtn);
         }

         if(!roleLibrary.doesProductOwnerExist(project.getProjectID()) && role.getRole().equals("Project Creator") || role.getRole().equals("Product Owner")){
             if (!grid.getChildren().contains(addSalaryBtn)) {
                 grid.getChildren().add(addSalaryBtn);
             }
             if(!grid.getChildren().contains(currentSalariesBtn)){
                 grid.getChildren().add(currentSalariesBtn);
             }
         } else{
             grid.getChildren().remove(addSalaryBtn);
             grid.getChildren().remove(currentSalariesBtn);
         }

        Hyperlink link = manageProjectInterface.getBackToCurrentProjects();
        link.setOnAction(event -> showUserProjectsMenu());


        changeScene(gui, title);
    }

    private void showTaskMenu(String projectID){
        AnchorPane gui = taskMenuInterface.getGUI();
        String title = taskMenuInterface.getTitle();
        GridPane grid = (GridPane) gui.getChildren().get(2);

        Hyperlink link = taskMenuInterface.getBackToManageProject();
        link.setOnAction(event -> showManageProjectInterface(projectLibrary.getProject(projectID)));

        Button createATask = taskMenuInterface.getCreateTaskBtn();
        createATask.setOnAction(event -> showCreateTaskMenu(projectID));

        Button currentTasksBtn = taskMenuInterface.getCurrentTasksBtn();
        currentTasksBtn.setOnAction(event -> showUserTasksMenu(projectID));

        Button allTasksBtn = taskMenuInterface.getAllTasksBtn();
        allTasksBtn.setOnAction(event -> showAllProjectTasks(projectID));

        Role role = roleLibrary.getUserRoleInProject(projectID, getUser());

        if(!roleLibrary.doesScrumMasterExist(projectID) && role.getRole().equals("Project Creator") || role.getRole().equals("Scrum Master")) {
            if(!grid.getChildren().contains(allTasksBtn)){
                grid.getChildren().add(allTasksBtn);
            }
        } else{
            grid.getChildren().remove(allTasksBtn);
        }
        changeScene(gui, title);

    }

    private void showUserMeetingInterface(String projectID) {
        AnchorPane gui = userMeetingInterface.getGUI();
        String title = userMeetingInterface.getTitle();

        Hyperlink backToManagerProject = userMeetingInterface.getBackToManageProject();
        backToManagerProject.setOnAction((EventHandler) event -> {
            showManageProjectInterface(projectLibrary.getProject(projectID));
        });
        Button createNewMeetingBtn = userMeetingInterface.getCreateNewMeetingBtn();
        createNewMeetingBtn.setOnAction((EventHandler) event -> {
            showCreateMeetingInterface(projectID);
        });
        changeScene(gui, title);
    }


    private void showCreateMeetingInterface(String projectID) {
        AnchorPane gui = createMeetingInterface.getGUI();
        String title = createMeetingInterface.getTitle();

        Hyperlink backToMeetingMenu = createMeetingInterface.getBackToMeetingMenu();
        backToMeetingMenu.setOnAction((EventHandler) event -> {
            showUserMeetingInterface(projectID);
        });

        Label debugLabel = createMeetingInterface.getDebug();
        TextField locationField = createMeetingInterface.getMeetingLocation();
        locationField.setText("");
        DatePicker dateField = createMeetingInterface.getMeetingDate();
        dateField.setValue(null);
        debugLabel.setText("");

        Button createNewMeetingBtn = createMeetingInterface.getCreateNewMeetingInviteBtn();

        createNewMeetingBtn.setOnAction((EventHandler) event -> {
            debugLabel.setText("");
            String location = locationField.getText();
            LocalDate date = dateField.getValue();
            if(location.equals("")){
                debugLabel.setText("Enter a location");
            } else if(date == null){
                debugLabel.setText("Enter a date");
            }else{
                showUserMeetingInterface(projectID);
                String emailBody = "Team meeting was scheduled for project (" + projectLibrary.getProject(projectID).getProjectName() + "). \r Meeting location: " + location + " \n \n Sent by: " + getUser().getFirstName() + " " + getUser().getLastName() + ". Using Miss Management." ;
                emailFunction.sendFromGMail(emailFunction.getEmailLogin(), emailFunction.getEmailPassword(), roleLibrary.getAllUserEmails(projectID), "Team meeting scheduled - " + createMeetingInterface.getMeetingDate().getValue(), emailBody);
            }

        });

        ///// IMPORTANT!! WE WILL NEED TO CHANGE THIS, THIS IS COPIED FROM ORACLE DOCS AND STACKOVERFLOW COULDNT UNDERSTAND SHIT
        createMeetingInterface.getMeetingDate().setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }


        });
        changeScene(gui, title);
    }



    private void showAddUserInterface(String ID){
        AnchorPane gui = addUserInterface.getGUI();
        String title = addUserInterface.getTitle();
        Label debug = addUserInterface.getDebug();
        ChoiceBox<String> userRolesCb = addUserInterface.getUserRolesCb();
        Button addUserBtn = addUserInterface.getAddUserConfirmBtn();
        TextField email = addUserInterface.getUserEmail();

        debug.setText("");
        email.setText("");


        addUserBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String emailText = email.getText();
                String role = userRolesCb.getValue();

                if(emailText.equals("")){
                    debug.setText("Enter email address!");
                } else if(!EmailValidation.isEmailValid(emailText)){
                    debug.setText("Enter a valid email!");
                } else if(!userLibrary.isEmailRegistered(emailText)){
                    debug.setText("Account not found.");
                } else if(roleLibrary.isUserInProject(ID, emailText)){
                    debug.setText("User is already in project.");
                } else if(role.equals("Scrum Master")){
                    if(roleLibrary.doesScrumMasterExist(ID)){
                        debug.setText("Role is already filled.");
                    } else{
                        User user = userLibrary.getUser(emailText);
                        Role newRole = new Role(user,ID);
                        roleLibrary.addRole(newRole);
                        newRole.setRoleScrumMaster();
                        showManageProjectInterface(projectLibrary.getProject(ID));
                    }
                } else if(role.equals("Product Owner")){
                    if(roleLibrary.doesProductOwnerExist(ID)){
                        debug.setText("Role is already filled.");
                    } else{
                        User user = userLibrary.getUser(emailText);
                        Role newRole = new Role(user,ID);
                        roleLibrary.addRole(newRole);
                        newRole.setRoleProductOwner();
                        showManageProjectInterface(projectLibrary.getProject(ID));
                    }
                }else{
                    User user = userLibrary.getUser(emailText);
                    Role newRole = new Role(user,ID);
                    roleLibrary.addRole(newRole);
                    newRole.setRoleDeveloper();
                    showManageProjectInterface(projectLibrary.getProject(ID));
                }
            }
        });


        Hyperlink backToManageProject = addUserInterface.getBackToManageProject();
        backToManageProject.setOnAction(event -> showManageProjectInterface(projectLibrary.getProject(ID)));
        changeScene(gui, title);
    }


    private void showCreateTaskMenu(String ID){
        AnchorPane gui = createTaskInterface.getGUI();
        String title = createTaskInterface.getTitle();

        Hyperlink backToTaskMenu = createTaskInterface.getBackToTaskMenu();
        Button createTask = createTaskInterface.getCreateATaskBtn();
        TextField taskName = createTaskInterface.getTaskName();
        TextArea taskDesc = createTaskInterface.getTaskDesc();
        Label debug = createTaskInterface.getDebug();

        debug.setText("");

        backToTaskMenu.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showTaskMenu(ID);
            }
        });

        ArrayList<User> allProjectUsers = roleLibrary.getAllProjectUsers(ID);

        GridPane grid = (GridPane) gui.getChildren().get(1);

        ChoiceBox<String> assignUserCB = new ChoiceBox<>();
        assignUserCB.setTooltip(new Tooltip("Assign task"));
        for(int j = 0; j < allProjectUsers.size(); j++){
            User user = allProjectUsers.get(j);
            assignUserCB.getItems().add(user.getEmail());
            if(user.equals(getUser())){
                assignUserCB.setValue(user.getEmail());
            }
        }

        Role role = roleLibrary.getUserRoleInProject(ID, getUser());
        if(!roleLibrary.doesScrumMasterExist(ID) && role.getRole().equals("Project Creator") || role.getRole().equals("Scrum Master")) {
            if (!grid.getChildren().contains(assignUserCB) && roleLibrary.getNumberOfUsersInProject(ID) > 1) {
                grid.getChildren().add(assignUserCB);
                GridPane.setConstraints(assignUserCB, 1, 0);
            }
        } else{
            grid.getChildren().remove(assignUserCB);
        }




        createTask.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                debug.setText("");
                String taskNameText = taskName.getText();
                String taskDescriptionText = taskDesc.getText();
                if(taskNameText.length() == 0){
                    debug.setText("Please enter a task name");
                } else if(taskLibrary.doesTaskNameExistInProject(ID,taskNameText)){
                    debug.setText("Please set a unique task name");
                }else if(taskNameText.length() > 100){
                    debug.setText("Task name must be 100 characters or less");
                } else if(taskDescriptionText.length() > 200){
                    debug.setText("Task description must be 200 characters or less");
                } else{
                    Task newTask = new Task(getUser(),ID, taskNameText, taskDescriptionText);
                    taskLibrary.addTask(newTask);
                    taskName.setText("");
                    taskDesc.setText("");
                    if(grid.getChildren().contains(assignUserCB)){
                        User assignUser = userLibrary.getUser(assignUserCB.getValue().toString());
                        newTask.setUserAssigned(assignUser);
                    }
                    showTaskMenu(ID);
                }

            }
        });
        changeScene(gui, title);

    }

    //User is not used at the moment, remove if necessary
    private void showUserTasksMenu(String projectID){
        AnchorPane gui = userTasksInterface.getGUI();
        String title = userTasksInterface.getTitle();

        Hyperlink backToTaskMenu = userTasksInterface.getBackToTaskMenu();

        backToTaskMenu.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showTaskMenu(projectID);
            }
        });

        GridPane grid = (GridPane) gui.getChildren().get(2);
        grid.getChildren().remove(0,grid.getChildren().size());

        ArrayList<Task> userTasks = taskLibrary.getAllUserTasks(getUser(),projectID);
        int startNum = 0;
        for(int i = 0; i < userTasks.size(); i++){
            Button taskBtn = new Button(userTasks.get(i).getTaskName());
            int currentTask = i;

            ChoiceBox<String> taskProgressCb = new ChoiceBox<>();
            taskProgressCb.getItems().addAll("Not started", "In progress", "Done");
            taskProgressCb.setValue(userTasks.get(currentTask).getTaskProgress());
            Button delBtn = new Button("x");
            delBtn.getStyleClass().add("del-btn");


            taskProgressCb.setOnAction(new EventHandler(){
                @Override
                public void handle(Event event) {
                    String progress = taskProgressCb.getValue();
                    if(progress.equalsIgnoreCase("Not started")){
                        userTasks.get(currentTask).setTaskNotStarted();
                    } else if(progress.equalsIgnoreCase("In progress")){
                        userTasks.get(currentTask).setTaskInProgress();
                    } else if(progress.equalsIgnoreCase("Done")){
                        userTasks.get(currentTask).setTaskDone();
                    }
                }
            });

            delBtn.setOnAction(new EventHandler(){
                @Override
                public void handle(Event event) {
                    int row = GridPane.getRowIndex(delBtn);
                    Button btn = GridTools.getButtonAtRow(row, grid);
                    ChoiceBox<String> cb = GridTools.getCBAtRow(row,grid);
                    Task task = taskLibrary.getTaskFromName(btn.getText());
                    taskLibrary.removeTask(task);
                    grid.getChildren().removeAll(btn,cb, delBtn);
                }
            });


            taskBtn.setOnAction(event -> showManageTask(userTasks.get(currentTask)));
            GridPane.setConstraints(taskBtn, 0, startNum);
            grid.getChildren().add(taskBtn);
            GridPane.setConstraints(taskProgressCb, 1, startNum);
            grid.getChildren().add(taskProgressCb);

            GridPane.setConstraints(delBtn, 2, startNum);
            grid.getChildren().add(delBtn);
            startNum++;
        }

        changeScene(gui, title);
    }


    private void showAllProjectTasks(String ID){
        AnchorPane gui = allProjectTasksInterface.getGUI();
        String title = allProjectTasksInterface.getTitle();

        GridPane grid = (GridPane) gui.getChildren().get(2);
        grid.getChildren().remove(0,grid.getChildren().size());

        Hyperlink backToTaskMenu = allProjectTasksInterface.getBackToTaskMenu();
        backToTaskMenu.setOnAction(event -> showTaskMenu(ID));

        ArrayList<Task> allProjectTasks = taskLibrary.getAllProjectTasks(ID);
        ArrayList<User> allProjectUsers = roleLibrary.getAllProjectUsers(ID);

        for(int i = 0; i < allProjectTasks.size(); i++){
            Task currentTask = allProjectTasks.get(i);
            Button taskBtn = new Button(currentTask.getTaskName());
            Button delBtn = new Button("x");
            delBtn.getStyleClass().add("del-btn");

            delBtn.setOnAction(new EventHandler(){
                @Override
                public void handle(Event event) {
                    int row = GridPane.getRowIndex(delBtn);
                    Button btn = GridTools.getButtonAtRow(row, grid);
                    ChoiceBox<String> cb = GridTools.getCBAtRow(row,grid);
                    Task task = taskLibrary.getTaskFromName(btn.getText());
                    taskLibrary.removeTask(task);
                    grid.getChildren().removeAll(btn,cb, delBtn);
                    }
            });

            taskBtn.setOnAction(event -> showManageTask(currentTask));
            User userAssigned = currentTask.getUserAssigned();

            ChoiceBox<String> userAssignedCb = new ChoiceBox<>();
            userAssignedCb.setTooltip(new Tooltip("Reassign task"));
            for(int j = 0; j < allProjectUsers.size(); j++){
                User user = allProjectUsers.get(j);
                userAssignedCb.getItems().add(user.getEmail());
                if(user.equals(userAssigned)){
                    userAssignedCb.setValue(user.getEmail());
                }
            }

            userAssignedCb.setOnAction(new EventHandler(){
                @Override
                public void handle(Event event) {
                    int row = GridPane.getRowIndex(userAssignedCb);
                    Button btn = GridTools.getButtonAtRow(row, grid);

                    Task task = taskLibrary.getTaskFromName(btn.getText());
                    String userEmail = userAssignedCb.getValue().toString();
                    User user = userLibrary.getUser(userEmail);
                    task.setUserAssigned(user);
                }
            });

            GridPane.setConstraints(taskBtn, 0, i);
            grid.getChildren().add(taskBtn);

            GridPane.setConstraints(userAssignedCb, 1, i);
            grid.getChildren().add(userAssignedCb);

            GridPane.setConstraints(delBtn, 2, i);
            grid.getChildren().add(delBtn);
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

        if(!task.getUserAssigned().equals(getUser())){
            backToCurrentTasks.setText("Back to View All Project Tasks");
        } else {
            backToCurrentTasks.setText("Back to Current Tasks");
        }

        backToCurrentTasks.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String linkText = backToCurrentTasks.getText();
                if(linkText.equals("Back to View All Project Tasks")){
                    showAllProjectTasks(task.getProjectID());
                } else{
                    showUserTasksMenu(task.getProjectID());
                }
            }
        });
        changeScene(gui, title);
    }


    private void showAddSalaryInterface(String ID){
        AnchorPane gui = addSalaryInterface.getGUI();
        String title = addSalaryInterface.getTitle();

        Hyperlink backToManageProject = addSalaryInterface.getBackToManageProject();
        Button addSalary = addSalaryInterface.getAddSalaryBtn();
        TextField hourSalaryField = addSalaryInterface.getHourSalary();
        Label debug = addSalaryInterface.getDebug();

        ChoiceBox<String> allUsersCB = addSalaryInterface.getAllUsersCB();
        allUsersCB.getItems().clear();
        ArrayList<User> allProjectUsers = roleLibrary.getAllProjectUsers(ID);
        allUsersCB.setTooltip(new Tooltip("Select user"));
        for(int j = 0; j < allProjectUsers.size(); j++){
            User user = allProjectUsers.get(j);
            allUsersCB.getItems().add(user.getEmail());
            if(user.equals(getUser())){
                allUsersCB.setValue(user.getEmail());
            }
        }
        debug.setText("");

        backToManageProject.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showManageProjectInterface(projectLibrary.getProject(ID));
            }
        });

        addSalary.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                debug.setText("");
                String emailText = allUsersCB.getValue().toString();
                Double hourSalary = 0.0;
                if(!hourSalaryField.getText().equals("")){
                    hourSalary = Double.parseDouble(hourSalaryField.getText());
                    if(salaryLibrary.doesUserHaveSalaryInProject(emailText,ID)) {
                        debug.setText("User already has a salary.");
                    }else if (hourSalary <= 0) {
                        debug.setText("Salary can not be 0 or less than zero");
                    } else {
                        Salary salary = new Salary(emailText, hourSalary, ID);
                        salaryLibrary.addSalary(salary);
                        hourSalaryField.setText("");
                        showManageProjectInterface(projectLibrary.getProject(ID));
                    }
                } else{
                    debug.setText("Enter a salary");
                }
            }
        });
        changeScene(gui, title);

    }
    private void showUserSalaryInterface(String projectID) {
        AnchorPane gui = userSalaryInterface.getGUI();
        String title = userSalaryInterface.getTitle();


        GridPane grid = (GridPane) gui.getChildren().get(2);
        grid.getChildren().remove(0,grid.getChildren().size());


        Hyperlink backToManageProjects = userSalaryInterface.getBackToManageProject();

        ObservableList<Salary> userSalaries = salaryLibrary.getTableData(projectID);
        TableView table = new TableView();
        TableColumn emailCol = new TableColumn("Email");
        TableColumn salaryCol = new TableColumn("Salary(SEK)");
        table.getColumns().addAll(emailCol, salaryCol);
        table.setItems(userSalaries);
        table.setMinWidth(500);
        salaryCol.setPrefWidth(250);
        emailCol.setPrefWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        salaryCol.setCellValueFactory(new PropertyValueFactory("salary"));

        table.getColumns().setAll(emailCol, salaryCol);
        GridPane.setConstraints(table, 0, 0);
        grid.getChildren().add(table);

        backToManageProjects.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showManageProjectInterface(projectLibrary.getProject(projectID));
            }
        });
        changeScene(gui, title);
    }



    private void showAdmin(){
        AnchorPane gui = adminInterface.getGUI();
        String title = adminInterface.getTitle();

        Button userExportBtn = adminInterface.getUserExportBtn();
        Hyperlink logoutLink = adminInterface.getLogoutLink();

        userExportBtn.setOnAction(event -> Export.createUserDataExport(userLibrary.exportUserData()));

        logoutLink.setOnAction(event -> {
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

        User user3 = new User("email3@email.com", "firstName", "lastName", "password");
        userLibrary.addUser(user3);

        User user4 = new User("email4@email.com", "firstName", "lastName", "password");
        userLibrary.addUser(user4);

        Project project1 = new Project("Project name 1", "Project Description 1", user1);
        projectLibrary.addProject(project1);

        Role role1 =  new Role(user1, project1.getProjectID());
        role1.setRoleProjectCreator();
        roleLibrary.addRole(role1);

        Project project2 = new Project("Project name 2", "Project Description 2", user1);
        projectLibrary.addProject(project2);

        Role role2 =  new Role(user1, project2.getProjectID());
        role2.setRoleProjectCreator();
        roleLibrary.addRole(role2);

        Project project3 = new Project("Project name 3", "Project Description 3", user1);
        projectLibrary.addProject(project3);
        Role role3 =  new Role(user1, project3.getProjectID());
        role3.setRoleProjectCreator();
        roleLibrary.addRole(role3);

        Project project4 = new Project("Project name 4", "Project Description 4", user2);
        projectLibrary.addProject(project4);

        Role role4 =  new Role(user2, project4.getProjectID());
        role4.setRoleProjectCreator();
        roleLibrary.addRole(role4);

        Project project5 = new Project("Project name 5", "Project Description 5", user2);
        projectLibrary.addProject(project5);

        Role role5 =  new Role(user2, project5.getProjectID());
        role5.setRoleProjectCreator();
        roleLibrary.addRole(role5);

        Project project6 = new Project("Project name 6", "Project Description 6", user2);
        projectLibrary.addProject(project6);

        Role role6 =  new Role(user2, project6.getProjectID());
        role6.setRoleProjectCreator();
        roleLibrary.addRole(role6);

        userLibrary.listUsers();
     //   projectLibrary.listAllProjects();
        sysAdminLibrary.addSysAdmin("admin@email.com", "hannah", "shiels", "password");
    }



}
