import java.util.ArrayList;

public class Project {

    private String projectName;
    private String projectDescription;
    static ArrayList<Project> projects = new ArrayList<>();

    Project(String projectName, String projectDescription){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }


    public static void addProject(Project newProject){
        projects.add(newProject);
    }

    public void listProjects(){
        for(int i = 0; i < projects.size(); i++){
            System.out.println(projects.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "project name: " + this.projectName + "\n project description: " + this.projectDescription;
    }

}
