import java.util.ArrayList;

public class TaskLibrary {

    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task){
        taskList.add(task);
    }

    public void listAllTasks(){
        for(int i = 0; i < taskList.size(); i++){
            System.out.println(taskList.get(i).toString());
        }
    }


    public ArrayList<Task> getAllUserTasks(User user, String projectID){
        ArrayList<Task> userTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getUserAssigned().getEmail().equals(user.getEmail()) && taskList.get(i).getProjectID().equals(projectID)){
                userTasks.add(taskList.get(i));
            }
        }
        return userTasks;
    }



}
