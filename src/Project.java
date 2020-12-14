import java.util.ArrayList;


public class Project {

    private String projectName;
    private String projectDescription;
    private String ID;


    public Project(String projectName, String projectDescription, User user){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.ID = RandomID.getRandomID();
    }

    public String getProjectID(){
        return this.ID;
    }

    public String getProjectName(){
        return this.projectName;
    }
    public String getProjectDescription(){
        return this.projectDescription;
    }



    @Override
    public String toString() {
        return "Project: " +  "\n id: " + this.ID +  "\n project name: " + this.projectName + "\n project description: " + this.projectDescription;
    }

}
