package tools;
import exceptions.NameException;
import exceptions.PasswordException;
import projects.*;
import users.SysAdminLibrary;
import users.User;
import users.UserLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Import {
    // Sample format for the txt file to be imported

    //User;firstname;lastname;email;Password
    //Project;title;description;email
    //Task;
    //Meeting;projectID;meetingCreator(email);location;meetingDate(int day, int month, int year)

    public static String importData(String filePath, UserLibrary userLibrary, SysAdminLibrary sysAdminLibrary, RoleLibrary roleLibrary, TaskLibrary taskLibrary, ProjectLibrary projectLibrary){
        try {
            File myFile = new File(filePath);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder();
            String line = null;
            int lineCounter=0;
            int userCount=0;
            int projectCount=0;
            int sysAdminCounter=0;
            int taskCounter=0;

            while ((line = reader.readLine()) != null) {
                lineCounter++;
                String[] partsOfCurrentLine = line.split(";");
                if(partsOfCurrentLine[0].equalsIgnoreCase("User")){
                    try{
                        userLibrary.registerUser(partsOfCurrentLine[3],partsOfCurrentLine[1],partsOfCurrentLine[2],partsOfCurrentLine[4]);
                        userCount++;
                    }catch (NameException | PasswordException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                        builder.append(ex.getMessage());
                        builder.append("\n");
                    } catch (ArrayIndexOutOfBoundsException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                    }

                }else if(partsOfCurrentLine[0].equalsIgnoreCase("System Admin")){
                    try{
                        sysAdminLibrary.addSysAdmin(partsOfCurrentLine[1],partsOfCurrentLine[2],(partsOfCurrentLine[3]),partsOfCurrentLine[4]);
                        sysAdminCounter++;
                    }catch ( NameException | PasswordException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                        builder.append(ex.getMessage());
                        builder.append("\n");
                    } catch (ArrayIndexOutOfBoundsException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                    }
                }else if(partsOfCurrentLine[0].equalsIgnoreCase("Project")){
                    try{
                        User user = userLibrary.getUser(partsOfCurrentLine[3]);
                        Project project = new Project( partsOfCurrentLine[1], partsOfCurrentLine[2],user);
                        projectLibrary.addProject(project);
                        Role role = new Role(user, project.getProjectID());
                        role.setRoleProjectCreator();
                        roleLibrary.addRole(role);
                        projectCount++;
                    }catch (NameException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                        builder.append(ex.getMessage());
                        builder.append("\n");
                    } catch (ArrayIndexOutOfBoundsException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                    }

                }else if(partsOfCurrentLine[0].equalsIgnoreCase("Role")){
                    try{
                        User user = userLibrary.getUser(partsOfCurrentLine[1]);
                        Project project = projectLibrary.getNumProject(Integer.parseInt(partsOfCurrentLine[2]));
                        roleLibrary.registerRole(user,project.getProjectID(),partsOfCurrentLine[3]);
                    }catch (NameException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                        builder.append(ex.getMessage());
                        builder.append("\n");
                    } catch (ArrayIndexOutOfBoundsException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                    }
                }else if(partsOfCurrentLine[0].equalsIgnoreCase("Task")){
                    try{
                        User user = userLibrary.getUser(partsOfCurrentLine[1]);
                        Project project = projectLibrary.getNumProject(Integer.parseInt(partsOfCurrentLine[2]));
                        taskLibrary.registerTask(user, project.getProjectID(),partsOfCurrentLine[3],partsOfCurrentLine[4]);
                        taskCounter++;
                    }catch (NameException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                        builder.append(ex.getMessage());
                        builder.append("\n");
                    } catch (ArrayIndexOutOfBoundsException ex){
                        builder.append("Invalid input at line "+lineCounter+"\n");
                    }
                }else{
                    builder.append("Invalid input at line "+lineCounter+"\n");
                }
            }
            if(userCount > 0){
                builder.append(userCount + " users.");
            }
            if (projectCount > 0){
                builder.append(projectCount + " projects.");
            }
            if (sysAdminCounter > 0){
                builder.append(sysAdminCounter + " system admins.");
            }
            if (taskCounter > 0){
                builder.append(taskCounter + " tasks.");
            }else {
                builder.append("No data to import!");
            }
            reader.close();
            return builder.toString();
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }
}