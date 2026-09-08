package bank;

public class BranchService {

    private Bank bank;

    // =========================
    // Constructor
    // =========================

    public BranchService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Add Bank.Branch
    // =========================

    public void addBranch(Branch branch) {

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch cannot be null."
            );
        }

        // Check duplicate Bank.Branch ID
        if (searchBranchById(branch.getBranchId()) != null) {
            throw new IllegalArgumentException(
                    "Bank.Branch with this ID already exists."
            );
        }

        // Check duplicate Bank.Branch Name
        if (searchBranchByName(branch.getBranchName()) != null) {
            throw new IllegalArgumentException(
                    "Bank.Branch with this name already exists."
            );
        }

        bank.addBranch(branch);

        System.out.println(
                "Bank.Branch added successfully."
        );
    }

    // =========================
    // Search Bank.Branch By ID
    // =========================

    public Branch searchBranchById(int id) {

        for (Branch branch : bank.getBranches()) {

            if (branch.getBranchId() == id) {
                return branch;
            }
        }

        return null;
    }

    // =========================
    // Find Bank.Branch By ID
    // =========================

    public Branch findBranchById(int id) {

        Branch branch = searchBranchById(id);

        if (branch == null) {
            System.out.println("Bank.Branch not found.");
            return null;
        }

        System.out.println("Bank.Branch found.");

        return branch;
    }

    // =========================
    // Search Bank.Branch By Name
    // =========================

    public Branch searchBranchByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        for (Branch branch : bank.getBranches()) {

            if (branch.getBranchName()
                    .equalsIgnoreCase(name.trim())) {

                return branch;
            }
        }

        return null;
    }

    // =========================
    // Remove Bank.Branch By ID
    // =========================

    public void removeBranchById(int id) {

        Branch branch = searchBranchById(id);

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch not found."
            );
        }

        bank.getBranches().remove(branch);

        System.out.println(
                "Bank.Branch removed successfully."
        );
    }

    // =========================
    // Remove Bank.Branch By Name
    // =========================

    public void removeBranch(String name) {

        Branch branch = searchBranchByName(name);

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch not found."
            );
        }

        bank.getBranches().remove(branch);

        System.out.println(
                "Bank.Branch removed successfully."
        );
    }

    // =========================
    // Update Bank.Branch
    // =========================

    public void updateBranch(
            int id,
            String name,
            String address,
            String open,
            String close) {

        Branch branch = searchBranchById(id);

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch not found."
            );
        }

        branch.setBranchName(name);
        branch.setBranchAddress(address);
        branch.setOpen(open);
        branch.setClose(close);

        System.out.println(
                "Bank.Branch updated successfully."
        );
    }

    // =========================
    // Add Bank.Employee To Bank.Branch
    // =========================

    public void addEmployeeToBranch(
            int branchId,
            Employee employee) {

        Branch branch = searchBranchById(branchId);

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch not found."
            );
        }

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee cannot be null."
            );
        }

        // Check if employee already exists in this branch
        for (Employee emp : branch.getEmployees()) {

            if (emp.getEmpId() == employee.getEmpId()) {
                throw new IllegalArgumentException(
                        "Bank.Employee already exists in this branch."
                );
            }
        }

        branch.addEmployee(employee);

        System.out.println(
                "Bank.Employee added to branch successfully."
        );
    }

    // =========================
    // Remove Bank.Employee From Bank.Branch
    // =========================

    public void removeEmployeeFromBranch(
            int branchId,
            int employeeId) {

        Branch branch = searchBranchById(branchId);

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch not found."
            );
        }

        Employee employeeToRemove = null;

        for (Employee employee : branch.getEmployees()) {

            if (employee.getEmpId() == employeeId) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee not found in this branch."
            );
        }

        branch.removeEmployee(employeeToRemove);

        System.out.println(
                "Bank.Employee removed from branch successfully."
        );
    }

    // =========================
    // Find Bank.Employee In Bank.Branch
    // =========================

    public Employee findEmployeeInBranch(
            int branchId,
            int employeeId) {

        Branch branch = searchBranchById(branchId);

        if (branch == null) {
            return null;
        }

        for (Employee employee : branch.getEmployees()) {

            if (employee.getEmpId() == employeeId) {
                return employee;
            }
        }

        return null;
    }

    // =========================
    // View Employees In Bank.Branch
    // =========================

    public void viewBranchEmployees(int branchId) {

        Branch branch = searchBranchById(branchId);

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch not found."
            );
        }

        if (branch.getEmployees().isEmpty()) {

            System.out.println(
                    "No employees in this branch."
            );

            return;
        }

        System.out.println(
                "Employees in branch: "
                        + branch.getBranchName()
        );

        for (Employee employee : branch.getEmployees()) {
            System.out.println(employee);
        }
    }

    // =========================
    // View All Branches
    // =========================

    public void viewBranches() {

        if (bank.getBranches().isEmpty()) {

            System.out.println(
                    "No branches available."
            );

            return;
        }

        for (Branch branch : bank.getBranches()) {
            System.out.println(branch);
        }
    }
}

