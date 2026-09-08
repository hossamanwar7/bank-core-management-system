package bank;

import java.util.ArrayList;

public class Branch {

    private int branchId;
    private String branchName;
    private String branchAddress;
    private String open;
    private String close;
    private ArrayList<Employee> employees;

    public Branch(int branchId,
                  String branchName,
                  String branchAddress,
                  String open,
                  String close) {

        setBranchId(branchId);
        setBranchName(branchName);
        setBranchAddress(branchAddress);
        setOpen(open);
        setClose(close);

        employees = new ArrayList<>();
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setBranchId(int branchId) {

        if (branchId <= 0) {
            throw new IllegalArgumentException(
                    "Bank.Branch ID must be greater than 0."
            );
        }

        this.branchId = branchId;
    }

    public void setBranchName(String branchName) {

        if (branchName == null || branchName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Branch name cannot be empty."
            );
        }

        this.branchName = branchName.trim();
    }

    public void setBranchAddress(String branchAddress) {

        if (branchAddress == null || branchAddress.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Branch address cannot be empty."
            );
        }

        this.branchAddress = branchAddress.trim();
    }

    public void setOpen(String open) {

        if (open == null || open.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Opening time cannot be empty."
            );
        }

        this.open = open.trim();
    }

    public void setClose(String close) {

        if (close == null || close.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Closing time cannot be empty."
            );
        }

        this.close = close.trim();
    }

    public void addEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee cannot be null."
            );
        }

        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee cannot be null."
            );
        }

        employees.remove(employee);
    }

    @Override
    public String toString() {

        return "====================\n" +
                "Bank.Branch ID : " + branchId +
                "\nBank.Branch Name : " + branchName +
                "\nBank.Branch Address : " + branchAddress +
                "\nOpen : " + open +
                "\nClose : " + close +
                "\nEmployees : " + employees +
                "\n====================";
    }
}