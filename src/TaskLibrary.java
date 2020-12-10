package projects;

import users.User;

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


    /*
    public  ArrayList<Task> getAllUserTasks(User user){
        ArrayList<Task> userProjects = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getTaskDescription().equals(user.getEmail())){
                taskList.add(count,taskList.get(i));
                count++;
            }
        }
        return tasks;
    }*/



}
