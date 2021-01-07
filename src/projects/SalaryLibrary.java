package projects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.User;

import java.util.ArrayList;

public class SalaryLibrary {

    private ArrayList<Salary> salaryList = new ArrayList<>();

    public void addSalary(Salary salary){
        salaryList.add(salary);
    }

    public void listAllSalaries(){
        for(int i = 0; i < salaryList.size(); i++){
            System.out.println(salaryList.get(i).toString());
        }
    }

    public ArrayList<Salary> getAllProjectSalaries(String projectID){
        ArrayList<Salary> projectSalaries = new ArrayList<>();
        for (int i = 0; i < salaryList.size(); i++){
            if(salaryList.get(i).getProjectID().equals(projectID)){
                projectSalaries.add(salaryList.get(i));
            }
        }
        return projectSalaries;
    }


    public double getProjectTotalCost(String projectID){
        double totalCost = 0.0;
        for (int i = 0; i < salaryList.size(); i++){
            if(salaryList.get(i).getProjectID().equals(projectID)){
               totalCost = totalCost + salaryList.get(i).getTotalCosts();
            }
        }
        return totalCost;
    }

    public ObservableList<Salary> getTableData(String projectID){
        ObservableList<Salary> projectSalaries =  FXCollections.observableArrayList();
        for (int i = 0; i < salaryList.size(); i++){
           if(salaryList.get(i).getProjectID().equals(projectID)){
                   projectSalaries.add(salaryList.get(i));
               }
        }
        return projectSalaries;
    }

    public boolean doesUserHaveASalary(User user){
        for (int i = 0; i < salaryList.size(); i++){
            if(salaryList.get(i).getEmail().equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }

    public Salary getUserSalaryInProject(String projectID, User user){
        for (int i = 0; i < salaryList.size(); i++){
            if(salaryList.get(i).getProjectID().equals(projectID) && salaryList.get(i).getEmail().equals(user.getEmail())){
                return salaryList.get(i);
            }
        }
        return null;
    }


    public boolean doesUserHaveSalaryInProject(String email, String projectID){
        for (int i = 0; i < salaryList.size(); i++){
            if(salaryList.get(i).getProjectID().equals(projectID) &&  salaryList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }


}



