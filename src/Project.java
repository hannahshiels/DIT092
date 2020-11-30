import java.util.ArrayList;

public class Project {

    private String projectName;
    private String projectDescription;
    private User user;
    private String projectOwner;
    static ArrayList<Project> projects = new ArrayList<>();

    Project(String projectName, String projectDescription, User user){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
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




    public static void addProject(Project newProject){
        projects.add(newProject);
    }

    public void listAllProjects(){
        for(int i = 0; i < projects.size(); i++){
            System.out.println(projects.get(i).toString());
        }
    }

    public static void listAllUserProjects(User user){
        for(int i = 0; i < projects.size(); i++){
            if(projects.get(i).getProjectOwner().equals(user.getEmail())){
                System.out.println("Project name: " + projects.get(i).getProjectName());
                System.out.println("Project description: " + projects.get(i).getProjectDescription());
            }
        }
    }



    @Override
    public String toString() {
        return "project owner: " + this.projectOwner +  "\nproject name: " + this.projectName + "\nproject description: " + this.projectDescription;
    }

}
