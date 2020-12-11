import java.time.LocalDate;
import java.util.Date;

public class Task {

    User userAssigned;
    String taskTitle;
    String taskDescription;
    Date dueDate;
    LocalDate dateCreated;
    String projectID;

    public Task(User user, String projectID, String taskTitle, String taskDescription){
        this.userAssigned  = user;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.projectID = projectID;
    }

    public String getTask() {
        return this.taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }



}
