package projects;

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

    public boolean doesUserHaveSalaryInProject(String email, String projectID){
        for (int i = 0; i < salaryList.size(); i++){
            if(salaryList.get(i).getProjectID().equals(projectID) &&  salaryList.get(i).getProjectID().equals(email)){
                return true;
            }
        }
        return false;
    }


}



