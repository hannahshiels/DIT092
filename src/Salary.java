package projects;

public class Salary {

    private String email;
    private double salary;
    private String projectID;

    public Salary(String email, Double salary, String projectID) {
        this.email = email;
        this.salary = salary;
        this.projectID = projectID;

    }

    public String getProjectID() {
        return this.projectID;
    }
    public String getEmail() {
        return this.email;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User Salary:" +
                " email='" + email + '\'' +
                ", salary=$" + salary + " per hour" +
                '}';
    }
}
