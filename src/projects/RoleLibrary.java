package projects;

import users.User;

import java.util.ArrayList;

public class RoleLibrary {

    private ArrayList<Role> roleList = new ArrayList<>();

    public void addRole(Role role) {
        roleList.add(role);
    }

    public void listAllRoles() {
        for (int i = 0; i < roleList.size(); i++) {
            System.out.println(roleList.get(i).toString());
        }
    }

    public ArrayList<String> getAllUserEmails(String projectID) {
        ArrayList<String> userEmailList = new ArrayList<>();
        for (int i = 0; i < roleList.size(); i++) {
            if(roleList.get(i).getProjectID().equals(projectID)){
                userEmailList.add(roleList.get(i).getUser().getEmail());
            }
        }
        return userEmailList;
    }


    public boolean isUserInProject(String projectID, String email) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getUser().getEmail().equals(email) && roleList.get(i).getProjectID().equals(projectID)) {
                return true;
            }
        }
        return false;

    }

    public ArrayList<String> getAllUserProjects(User user){
        ArrayList<String> userProjects = new ArrayList<>();
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getUser().equals(user)) {
                userProjects.add(roleList.get(i).getProjectID());
            }
        }
        return userProjects;
    }


    public int getNumberOfUsersInProject(String projectID){
        int totalUsers = 0;
        for(int i = 0; i < roleList.size(); i++){
            Role role = roleList.get(i);
            if(role.getProjectID().equals(projectID)){
                totalUsers++;
            }
        }
        return totalUsers;
    }



    public ArrayList<User> getAllProjectUsers(String ID){
        ArrayList<User> allProjectUsers = new ArrayList<>();
        for(Role role: roleList){
            if(role.getProjectID().equals(ID)){
                allProjectUsers.add(role.getUser());
            }
        } 
        return allProjectUsers;
    }

    public ArrayList<Role> getRoleListInProject(String projectID) {
        ArrayList<Role> rolesInProject = new ArrayList<>();
        for (Role role : roleList) {
            if (role.getProjectID().equals(projectID)) {
                rolesInProject.add(role);
            }
        }
        return rolesInProject;
    }

    public boolean doesScrumMasterExist(String projectID) {
        for (Role currentRole : roleList) {
            if (currentRole.getProjectID().equals(projectID)) {
                if(currentRole.getRole().equalsIgnoreCase("Scrum Master")){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doesProductOwnerExist(String projectID) {
        for (Role currentRole : roleList) {
            if (currentRole.getProjectID().equals(projectID)) {
                if(currentRole.getRole().equalsIgnoreCase("Product Owner"))
                return true;
            }
        }
        return false;

    }

    public boolean doesProductOwnerOrScrumMasterExist(String projectID){
        for (Role currentRole : roleList) {
            if (currentRole.getProjectID().equals(projectID) ) {
                if(currentRole.getRole().equalsIgnoreCase("Product Owner"))
                    return true;
                if(currentRole.getRole().equalsIgnoreCase("Scrum Master")){
                    return true;
                }
            }
        }
        return false;

    }

    public Role getUserRoleInProject(String projectID, User user){
        for(int i = 0; i < roleList.size(); i++){
            Role currentRole = roleList.get(i);
            if(currentRole.getProjectID().equals(projectID) && currentRole.getUser() == user){
                return currentRole;
            }
        }
        return null;
    }
}

