public class Project {

    private String projectName;
    private String projectDescription;
    private User user;
    private String projectOwner;
    private String ID;

    public Project(String projectName, String projectDescription, User user){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.user = user;
        this.projectOwner = user.getEmail();
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

    public String getProjectOwner(){
        return this.projectOwner;
    }


    @Override
    public String toString() {
        return "Project: " +  "\n id: " + this.ID +  "\n project owner: " + this.projectOwner +  "\n project name: " + this.projectName + "\n project description: " + this.projectDescription;
    }

}
