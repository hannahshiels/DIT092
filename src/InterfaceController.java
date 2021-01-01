import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
    private AddUserInterface addUserInterface;
    private CreateTaskInterface createTaskInterface;
    private UserTasksInterface userTasksInterface;
    private ManageTaskInterface manageTaskInterface;
    private AllProjectTasksInterface allProjectTasksInterface;
    private AdminInterface adminInterface;
    private ProjectLibrary projectLibrary;
    private UserLibrary userLibrary;
    private SysAdminLibrary sysAdminLibrary;
    private TaskLibrary taskLibrary;
    private RoleLibrary roleLibrary;


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
        this.addUserInterface = new AddUserInterface();
        this.createTaskInterface = new CreateTaskInterface();
        this.userTasksInterface = new UserTasksInterface();
        this.manageTaskInterface = new ManageTaskInterface();
        this.allProjectTasksInterface = new AllProjectTasksInterface();

        this.adminInterface = new AdminInterface();

        this.taskLibrary = new TaskLibrary();
        this.projectLibrary = new ProjectLibrary();
        this.userLibrary = new UserLibrary();
        this.roleLibrary = new RoleLibrary();
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

        Button submitLoginBtn = loginInterface.getSubmitLoginBtn();
        submitLoginBtn.setOnAction(event -> {
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
        mainMenuLink.setOnAction(event -> showMainMenu());
        Hyperlink loginLink = registerInterface.getLoginLink();
        loginLink.setOnAction(event -> showLogin());

        GridPane grid = (GridPane) gui.getChildren().get(2);
        Button createAccBtn = registerInterface.getCreateAccBtn();
        Label debug = registerInterface.getDebug();

        createAccBtn.setOnAction(event -> {
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
                link.setOnAction(event1 -> showLogin());
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

        Button createATask = manageProjectInterface.getCreateTaskBtn();
        createATask.setOnAction(event -> showCreateTaskMenu(project.getProjectID()));

        Button currentTasksBtn = manageProjectInterface.getCurrentTasksBtn();
        currentTasksBtn.setOnAction(event -> showUserTasksMenu(project.getProjectID(), getUser()));

        Button allTasksBtn = manageProjectInterface.getAllTasksBtn();
        allTasksBtn.setOnAction(event -> showAllProjectTasks(project.getProjectID()));

        Role role = roleLibrary.getUserRoleInProject(project.getProjectID(), getUser());
        GridPane grid = (GridPane) gui.getChildren().get(1);

         if(!roleLibrary.doesScrumMasterExist(project.getProjectID()) && role.getRole().equals("Project Creator") || role.getRole().equals("Scrum Master")) {
             if (!grid.getChildren().contains(addUserBtn)) {
                 grid.getChildren().add(addUserBtn);
             }
             if(!grid.getChildren().contains(allTasksBtn)){
                 grid.getChildren().add(allTasksBtn);
             }
         } else{
             grid.getChildren().remove(addUserBtn);
             grid.getChildren().remove(allTasksBtn);
         }

        Hyperlink link = manageProjectInterface.getBackToCurrentProjects();
        link.setOnAction(event -> showUserProjectsMenu());


        changeScene(gui, title);
    }


    private void showAddUserInterface(String ID){
        AnchorPane gui = addUserInterface.getGUI();
        String title = addUserInterface.getTitle();

        Label debug = addUserInterface.getDebug();

        ChoiceBox<String> userRolesCb = new ChoiceBox<>();
        userRolesCb.getItems().addAll( "Scrum Master", "Product Owner", "Developer");
        userRolesCb.setValue("Scrum Master");
        GridPane grid = (GridPane) gui.getChildren().get(2);
        GridPane.setConstraints(userRolesCb, 1, 0);
        grid.getChildren().add(userRolesCb);
        debug.setText("");
        Button addUserBtn = addUserInterface.getAddUserConfirmBtn();
        TextField email = addUserInterface.getUserEmail();
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

        Hyperlink backToManageProject = createTaskInterface.getBackToManageProject();
        Button createTask = createTaskInterface.getCreateATaskBtn();
        TextField taskName = createTaskInterface.getTaskName();
        TextArea taskDesc = createTaskInterface.getTaskDesc();
        Label debug = createTaskInterface.getDebug();

        debug.setText("");

        backToManageProject.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                showManageProjectInterface(projectLibrary.getProject(ID));
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
                GridPane.setConstraints(assignUserCB, 0, 4);
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
                    showManageProjectInterface(projectLibrary.getProject(ID));
                }

            }
        });
        changeScene(gui, title);

    }

    //User is not used at the moment, remove if necessary
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

            taskBtn.setOnAction(event -> showManageTask(userTasks.get(currentTask)));
            GridPane.setConstraints(taskBtn, 0, startNum);
            grid.getChildren().add(taskBtn);
            GridPane.setConstraints(taskProgressCb, 1, startNum);
            grid.getChildren().add(taskProgressCb);
            startNum++;
        }

        changeScene(gui, title);
    }


    private void showAllProjectTasks(String ID){
        AnchorPane gui = allProjectTasksInterface.getGUI();
        String title = allProjectTasksInterface.getTitle();

        GridPane grid = (GridPane) gui.getChildren().get(2);
        grid.getChildren().remove(0,grid.getChildren().size());

        Hyperlink backToManageProject = allProjectTasksInterface.getBackToManageProject();
        backToManageProject.setOnAction(event -> showManageProjectInterface(projectLibrary.getProject(ID)));

        ArrayList<Task> allProjectTasks = taskLibrary.getAllProjectTasks(ID);
        ArrayList<User> allProjectUsers = roleLibrary.getAllProjectUsers(ID);

        for(int i = 0; i < allProjectTasks.size(); i++){
            Task currentTask = allProjectTasks.get(i);
            Button taskBtn = new Button(currentTask.getTaskName());
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
                    Button btn = new Button();
                    for(int i = 0; i < grid.getChildren().size(); i++){
                        Node child = grid.getChildren().get(i);
                        if(GridPane.getColumnIndex(child) == 0 && GridPane.getRowIndex(child) == row){
                            btn = (Button) child;
                        }
                    }
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
                    showUserTasksMenu(task.getProjectID(), getUser());
                }
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
