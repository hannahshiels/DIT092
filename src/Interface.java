import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


abstract public class Interface {
    private AnchorPane gui;
    private String title;

    public Interface(){
        this.gui = this.showGUI();
        this.title = "Miss Management";
    }

    public GridPane getTitlePane(String titleText){
        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(5);
        Label title = new Label(titleText);

        GridPane.setConstraints(title, 0, 0);
        grid.getChildren().add(title);

        title.getStyleClass().add("title");
        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("bg");

        return grid;
    }

    abstract public AnchorPane showGUI();

    public String getTitle(){
        return this.title;
    }


    public AnchorPane getGUI(){
        return this.gui;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    // register + login
    Button registerBtn = new Button("Register");
    Button loginBtn = new Button("Login");
    Button submitLoginBtn = new Button("Login");

    Hyperlink mainMenuLink = new Hyperlink("Back to Main Menu");
    Hyperlink loginLink = new Hyperlink("Login instead");
    Hyperlink registerLink = new Hyperlink("Create an account instead");

    TextField userFirstName  = new TextField();
    TextField userLastName = new TextField();
    TextField userEmail = new TextField();


    PasswordField userPassword = new PasswordField();
    PasswordField userPasswordConfirm = new PasswordField();
    Label debug = new Label();

    Button createAccBtn = new Button ("Create account");

    // user projects

    Hyperlink backToUserMenu = new Hyperlink("Back to User Menu");
    Hyperlink backToCurrentProjects = new Hyperlink("Back to Current Projects");
    Hyperlink backToManageProject = new Hyperlink("Back to Manage Projects");

    Hyperlink logoutLink = new Hyperlink("Log out");

    Button createAProjectBtn = new Button("Create a project");
    Button projectsBtn = new Button("Current projects");
    Button createProjectBtn = new Button ("Create project");

    TextField projectName = new TextField();
    TextArea projectDescription = new TextArea();
    TextField taskName = new TextField();
    TextArea taskDesc = new TextArea();

    Label projectNameLabel = new Label();
    Label projectDescLabel = new Label();

    Button createTaskBtn = new Button("Create a task");
    Button createATaskBtn = new Button("Create task");




    // admin

    Button exportBtn = new Button("Create export");



    // login + register


    public Button getRegisterBtn(){
        return this.registerBtn;
    }

    public TextField getUserEmail(){
        return this.userEmail;
    }


    public TextField getUserFirstName(){
        return this.userFirstName;
    }

    public TextField getUserLastName(){
        return this.userLastName;
    }

    public PasswordField getUserPassword(){
        return this.userPassword;
    }

    public PasswordField getUserPasswordConfirm(){
        return this.userPasswordConfirm;
    }

    public Label getDebug(){
        return this.debug;
    }


    public Button getCreateAccBtn(){
        return this.createAccBtn;
    }


    public Button getLoginBtn(){
        return this.loginBtn;
    }

    public Button getSubmitLoginBtn(){
        return this.submitLoginBtn;
    }

    public Hyperlink getMainMenuLink(){
        return this.mainMenuLink;
    }

    public Hyperlink getRegisterLink(){
        return this.registerLink;
    }


    public Hyperlink getLoginLink(){
        return this.loginLink;
    }


    // user projects

    public Button getCreateAProjectBtn(){
        return this.createAProjectBtn;
    }

    public Button getProjectsBtn(){
        return this.projectsBtn;
    }

    public Hyperlink getLogoutLink(){
        return this.logoutLink;
    }

    public Hyperlink getBackToUserMenuLink(){
        return  this.backToUserMenu;
    }

    public Button getCreateProjectBtn(){
        return this.createProjectBtn;
    }

    public TextField getProjectName(){
        return this.projectName;
    }

    public TextArea getProjectDescription(){
        return this.projectDescription;
    }

    public Button getCreateTaskBtn(){
        return this.createTaskBtn;
    }

    public Hyperlink getBackToCurrentProjects(){
        return this.backToCurrentProjects;
    }


    public Label getProjectNameLabel(){
        return this.projectNameLabel;
    }

    public Label getProjectDescLabel(){
        return this.projectDescLabel;
    }



    public Button getCreateATaskBtn(){
        return this.createATaskBtn;
    }

    public Hyperlink getBackToManageProject(){
        return this.backToManageProject;
    }


    public TextField getTaskName(){
        return this.taskName;
    }

    public TextArea getTaskDesc(){
        return this.taskDesc;
    }


    // admin

    public Button getExportBtn(){
        return this.exportBtn;
    }




}
