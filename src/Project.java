public class Project {

    private String projectName;
    private String projectDescription;

    Project(String projectName, String projectDescription){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    @Override
    public String toString() {
        return "project name: " + this.projectName + "\n project description: " + this.projectDescription ;
    }

}
