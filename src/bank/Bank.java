package bank;

import java.util.ArrayList;

public class Bank {

    private String bankName;
    private String bankCode;
    private String address;

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Branch> branches = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(String bankName, String bankCode, String address) {

        setBankName(bankName);
        setBankCode(bankCode);
        setAddress(address);
    }

    // =========================
    // Getters

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // =========================
    // Setters


    public void setBankName(String bankName) {

        if (bankName == null || bankName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Bank name cannot be empty."
            );
        }

        this.bankName = bankName.trim();
    }

    public void setBankCode(String bankCode) {

        if (bankCode == null || bankCode.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Bank code cannot be empty."
            );
        }

        if (bankCode.length() != 6) {
            throw new IllegalArgumentException(
                    "Bank.Bank code must contain exactly 6 characters."
            );
        }

        this.bankCode = bankCode.trim();
    }

    public void setAddress(String address) {

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Bank address cannot be empty."
            );
        }

        this.address = address.trim();
    }

    // =========================
    // Add Methods


    public void addCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Bank.Customer cannot be null."
            );
        }

        customers.add(customer);
    }

    public void addEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Bank.Employee cannot be null."
            );
        }

        employees.add(employee);
    }

    public void addBranch(Branch branch) {

        if (branch == null) {
            throw new IllegalArgumentException(
                    "Bank.Branch cannot be null."
            );
        }

        branches.add(branch);
    }

    public void addCard(Card card) {

        if (card == null) {
            throw new IllegalArgumentException(
                    "Bank.Card cannot be null."
            );
        }

        cards.add(card);
    }

    public void addAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException(
                    "Bank.Account cannot be null."
            );
        }

        accounts.add(account);
    }

    public void addLoan(Loan loan) {

        if (loan == null) {
            throw new IllegalArgumentException(
                    "Bank.Loan cannot be null."
            );
        }

        loans.add(loan);
    }

    public void addTransaction(Transaction transaction) {

        if (transaction == null) {
            throw new IllegalArgumentException(
                    "Bank.Transaction cannot be null."
            );
        }

        transactions.add(transaction);
    }

    // =========================
    // toString


    @Override
    public String toString() {

        return "============================\n" +
                "Bank.Bank Name : " + bankName +
                "\nBank.Bank Code : " + bankCode +
                "\nAddress : " + address +
                "\nCustomers : " + customers.size() +
                "\nEmployees : " + employees.size() +
                "\nBranches : " + branches.size() +
                "\nAccounts : " + accounts.size() +
                "\nCards : " + cards.size() +
                "\nLoans : " + loans.size() +
                "\nTransactions : " + transactions.size() +
                "\n============================";
    }
}