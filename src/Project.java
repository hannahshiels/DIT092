import java.util.ArrayList;

public class Project {

    private String projectName;
    private String projectDescription;
    private User user;
    private String projectOwner;

    public Project(String projectName, String projectDescription, User user){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.user = user;
        this.projectOwner = user.getEmail();
    }

    public String getProjectName(){
        return this.projectName;
    }
    public String getProjectDescription(){
        return this.projectDescription;
    }

    public String getProjectOwner(){
        return this.projectOwner;
    }


    @Override
    public String toString() {
        return "project owner: " + this.projectOwner +  "\nproject name: " + this.projectName + "\nproject description: " + this.projectDescription;
    }

}
