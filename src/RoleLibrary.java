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

    public boolean isUserInProject(String projectID, String email) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getUser().getEmail().equals(email) && roleList.get(i).getProjectID().equals(projectID)) {
                return true;
            }
        }
        return false;

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
}

