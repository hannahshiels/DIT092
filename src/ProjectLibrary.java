import java.util.ArrayList;

public class ProjectLibrary {
    ArrayList<Project> projectList = new ArrayList<>();

    public void addProject(Project newProject){
        projectList.add(newProject);
    }

    public void listAllProjects(){
        for(int i = 0; i < projectList.size(); i++){
            System.out.println(projectList.get(i).toString());
        }
    }

    public boolean checkIfProjectExists(String ID){
        for (int i = 0; i < projectList.size(); i++){
            if(projectList.get(i).getProjectID().equals(ID)){
                return true;
            }
        }
        return false;
    }

    public Project getProject(String ID){
        for (int i = 0; i < projectList.size(); i++){
            if(projectList.get(i).getProjectID().equals(ID)){
                return projectList.get(i);
            }
        }
        return null;
    }

    public ArrayList<Project> getProjects(ArrayList<String> projectIDS){
        ArrayList<Project> userProjects = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++){
            for(int j = 0; j < projectIDS.size(); j++){
                if(projectList.get(i).getProjectID().equals(projectIDS.get(j))){
                    userProjects.add(projectList.get(i));
                }
            }
        }
        return userProjects;
    }

/*
    public ArrayList<Project> getAllUserProjects(User user){
        ArrayList<Project> userProjects = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++){
            if(projectList.get(i).getProjectOwner().equals(user.getEmail())){
                userProjects.add(projectList.get(i));
            }
        }
        return userProjects;
    }

    public void listAllUserProjects(User user){
        for(int i = 0; i < projectList.size(); i++){
            if(projectList.get(i).getProjectOwner().equals(user.getEmail())){
                System.out.println("Project name: " + projectList.get(i).getProjectName());
                System.out.println("Project description: " + projectList.get(i).getProjectDescription());
            }
        }
    }
*/



}
