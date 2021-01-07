package projects;

import java.util.ArrayList;

public class ProjectLibrary {
    private ArrayList<Project> projectList = new ArrayList<>();

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

}
