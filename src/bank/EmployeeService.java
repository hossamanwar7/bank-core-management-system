package bank;

public class EmployeeService {

    private Bank bank;

    public EmployeeService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Add Bank.Employee
     

    public void addEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee cannot be null."
            );
        }

        // Check Bank.Employee ID
        if (searchEmployeeById(employee.getEmpId()) != null) {
            throw new IllegalArgumentException(
                    "Bank.Employee ID already exists."
            );
        }

        // Check Username
        if (searchEmployeeByUsername(employee.getUsername()) != null) {
            throw new IllegalArgumentException(
                    "Username already exists."
            );
        }

        bank.addEmployee(employee);

        System.out.println(
                "Bank.Employee " + employee.getEmpId()
                        + " has been added successfully."
        );
    }

    // =========================
    // Search Bank.Employee By ID


    public Employee searchEmployeeById(int empId) {

        for (Employee employee : bank.getEmployees()) {

            if (employee.getEmpId() == empId) {
                return employee;
            }
        }

        return null;
    }

    // =========================
    // Search Bank.Employee By Username


    public Employee searchEmployeeByUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        username = username.trim();

        for (Employee employee : bank.getEmployees()) {

            if (employee.getUsername().equals(username)) {
                return employee;
            }
        }

        return null;
    }

    // =========================
    // Find Bank.Employee By ID


    public Employee findEmployeeById(int empId) {

        Employee employee = searchEmployeeById(empId);

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee not found."
            );
        }

        return employee;
    }

    // =========================
    // Remove Bank.Employee


    public void removeEmployee(int empId) {

        Employee employee = findEmployeeById(empId);

        bank.getEmployees().remove(employee);

        System.out.println(
                "Bank.Employee with ID: " + empId
                        + " has been removed successfully."
        );
    }

    // =========================
    // Update Bank.Employee


    public void updateEmployee(
            int empId,
            String fullname,
            String empPosition,
            double salary) {

        Employee employee = findEmployeeById(empId);

        employee.setFullname(fullname);
        employee.setEmpPosition(empPosition);
        employee.setSalary(salary);

        System.out.println(
                "Bank.Employee with ID: " + empId
                        + " has been updated successfully."
        );
    }

    // =========================
    // View All Employees


    public void viewEmployees() {

        if (bank.getEmployees().isEmpty()) {

            System.out.println(
                    "Bank.Employee list is empty."
            );

            return;
        }

        for (Employee employee : bank.getEmployees()) {

            System.out.println(employee);
        }
    }
}

