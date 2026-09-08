package bank;

public class Employee extends User {

    private int empId;
    private String fullname;
    private String empPosition;
    private double salary;

    public Employee(String username,
                    String password,
                    int userId,
                    int empId,
                    String fullname,
                    String empPosition,
                    double salary) {

        super(username, password, userId);

        setEmpId(empId);
        setFullname(fullname);
        setEmpPosition(empPosition);
        setSalary(salary);
    }



    // =========================
    // Getters

    public int getEmpId() {
        return empId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public double getSalary() {
        return salary;
    }

    // =========================
    // Setters

    public void setEmpId(int empId) {

        if (empId <= 0) {
            throw new IllegalArgumentException(
                    "Bank.Employee ID must be greater than 0."
            );
        }

        this.empId = empId;
    }

    public void setFullname(String fullname) {

        if (fullname == null || fullname.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Employee name cannot be empty."
            );
        }

        this.fullname = fullname.trim();
    }

    public void setEmpPosition(String empPosition) {

        if (empPosition == null || empPosition.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Position cannot be empty."
            );
        }

        this.empPosition = empPosition.trim();
    }

    public void setSalary(double salary) {

        if (salary <= 3000) {
            throw new IllegalArgumentException(
                    "Salary must be greater than 3000."
            );
        }

        this.salary = salary;
    }

    // =========================
    // toString

    @Override
    public String toString() {

        return "=============================\n" +
                "Bank.User ID : " + getUserId() +
                "\nUsername : " + getUsername() +
                "\nBank.Employee ID : " + empId +
                "\nFull Name : " + fullname +
                "\nPosition : " + empPosition +
                "\nSalary : " + salary +
                "\n=============================";
    }
}