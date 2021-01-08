package projects;

import users.User;

import java.util.ArrayList;
import java.util.Collections;

public class NotificationLibrary {
    private ArrayList<Notification> notificationList = new ArrayList<>();

    /*public Notification sendNotification(String projectID, String receiverEmail, String notificationTitle, String notificationText, RoleLibrary roleLibrary){
        if(roleLibrary.isUserInProject(projectID, receiverEmail)){
            Notification newNotification = new Notification(receiverEmail, notificationTitle, notificationText);
            notificationList.add(newNotification);
            return newNotification;
        } else {
            return null;
        }
    }*/

    public void sendNewUserNotification(ArrayList<User> receivers, User newUser, Project project){
        String title = project.getProjectName() + " - New User";
        String text = "A new team member has been added to " + project.getProjectName() + "! Please, welcome " + newUser.getNames() + "!";
        Notification newNotification = new Notification(receivers, title, text);
        notificationList.add(newNotification);
    }

    public void sendNewTaskNotification(ArrayList<User> receivers, Task newTask, Project project){
        String title = project.getProjectName() + " - New Task";
        String text = "A new task " + newTask.getTaskName() + " has been created in " + project.getProjectName() + "!";
        Notification newNotification = new Notification(receivers, title, text);
        notificationList.add(newNotification);
    }

    public void sendMeetingNotification(ArrayList<User> receivers, Project project){
        String title = project.getProjectName() + " - New Meeting";
        String text = "New Team Meeting has been scheduled for " + project.getProjectName() + ". Check your e-mail for more information.";
        Notification newNotification = new Notification(receivers, title, text);
        notificationList.add(newNotification);
    }

    public void sendNewProgressNotification(ArrayList<User> receivers, Task task, Project project){
        String title = project.getProjectName() + " - Task Progress Changed";
        String text = "Task " + task.getTaskName() + " in " + project.getProjectName() + " has been moved to " + task.getTaskProgress() + "!";
        Notification newNotification = new Notification(receivers, title, text);
        notificationList.add(newNotification);
    }

    public void sendTaskAssignedNotification(User receiver, Task task, Project project){
        String title = project.getProjectName() + " - New Task Assigned";
        String text = "You have been assigned a new task (" + task.getTaskName() + " ) in " + project.getProjectName() + "!";
        Notification newNotification = new Notification(receiver, title, text);
        notificationList.add(newNotification);
    }

    //--------------------------------------------------------------
                       // Notification Titles
    public String newUserNotTitle(Project project){
        return project.getProjectName() + " - New User";
    }
    public String newTaskNotTitle(Project project){
        return project.getProjectName() + " - New Task";
    }
    public String newMeetingNotTitle(Project project){
        return project.getProjectName() + " - New Meeting";
    }
    public String newProgressNotTitle(Project project){
        return project.getProjectName() + " - Task Progress Changed";
    }

    public String taskAssignedNotTitle(Project project){
        return project.getProjectName() + " - New Task Assigned";
    }
    //--------------------------------------------------------------
                     // Notification Texts
   /* public String newUserText(User newUser, Project project){
        return "A new team member has been added to " + project.getProjectName() + "! Please, welcome " + newUser.getNames() + "!";
    }
    public String newTaskText(Task newTask, Project project){
        return "A new task " + newTask.getTaskName() + " has been created in " + project.getProjectName() + "!";
    }
    //I don't have the meeting updates yet, come back to it later!!!
    public String newMeetingText(Task newTask, Project project){
        String text = String.format("A new task %s", newTask.getTaskName(), " has been created in %s", project.getProjectName());
        return text;
    }
    public String newTaskProgress(Task task, Project project){
        return "Task " + task.getTaskName() + "in " + project.getProjectName() + " has been moved to %s" + task.getTaskProgress() + "!";
    }
    public String taskAssignedText(Task task, Project project){
        return "You have been assigned a new task (" + task.getTaskName() + " ) in " + project.getProjectName() + "!";
    }*/
    //-------------------------------------------------------------------


    public void deleteNotification(Notification notification) {
        notificationList.remove(notification);
    }

    public ArrayList<Notification> getAllNotificationsOfUser(User user){
        ArrayList<Notification> notificationsOfUser = new ArrayList<>();
        for (Notification notification : notificationList) {
            if (notification.getIndividualReceiver(user) == user) {
                notificationsOfUser.add(notification);
            }
        }

        //bubble sort
        //sorts the notifications of the user to display the most recent ones first
        for(int i=0; i<notificationsOfUser.size()-1; i++){
            for(int j=0; j<notificationsOfUser.size()-i-1; j++){
                if(!notificationsOfUser.get(j).isAfter(notificationsOfUser.get(j+1))){
                    Collections.swap(notificationsOfUser, j,j+1);
                }
            }
        }
        return notificationsOfUser;
    }

    public Notification getNotificationFromID(String notificationID) {
        for(Notification notification : notificationList){
            if(notification.getNotificationID().equals(notificationID)){
                return notification;
            }
        }
        return null;
    }
}
