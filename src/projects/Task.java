package projects;

import tools.CurrentDate;
import users.User;

import java.time.LocalDate;
import java.util.Date;

public class Task {

    User userAssigned;
    String taskTitle;
    String taskDescription;
    String projectID;
    String taskProgress;

    public Task(User user, String projectID, String taskTitle, String taskDescription){
        this.userAssigned  = user;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.projectID = projectID;
        this.taskProgress = "Not started";
    }

    public void setUserAssigned(User user){
        this.userAssigned = user;
    }

    public void setTaskNotStarted(){
        this.taskProgress = "Not started";
    }

    public String getTaskProgress(){
        return this.taskProgress;
    }


    public void setTaskInProgress(){
        this.taskProgress = "In progress";
    }

    public void setTaskDone(){
        this.taskProgress = "Done";
    }


    public User getUserAssigned() {
        return this.userAssigned;
    }

    public String getProjectID() {
        return this.projectID;
    }


    public String getTaskName() {
        return this.taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return "Task: " +  "\n project id: " + this.getProjectID() +  "\n user assigned: " + this.getUserAssigned().getEmail() +  "\n task name: " + this.taskTitle + "\n task description: " + getTaskDescription() + "\n task progress: " + this.taskProgress;
    }



}
