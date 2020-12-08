import java.util.ArrayList;

public class ProjectLibrary {

    private ArrayList<Project> projectList = new ArrayList<>();

    public ArrayList<Project> getProjectList = new ArrayList<>();

    public void addProjectToList(Project newProject){
        projectList.add(newProject);
    }

    public Project createProject(String projectName, String projectDescription, User user) {
        Project newProject = new Project(projectName, projectDescription, user);
        addProjectToList(newProject);
        return newProject;
    }
}
