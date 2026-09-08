package bank;

public class AccountService {

    private Bank bank;

    public AccountService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Add Bank.Account


    public void addAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException(
                    "Bank.Account cannot be null."
            );
        }

        if (searchAccountByNumber(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException(
                    "Bank.Account number already exists."
            );
        }

        bank.addAccount(account);

        System.out.println(
                "Bank.Account " + account.getAccountNumber()
                        + " has been added successfully."
        );
    }

    // =========================
    // Search Bank.Account By Number


    public Account searchAccountByNumber(String accountNumber) {

        if (accountNumber == null
                || accountNumber.trim().isEmpty()) {

            return null;
        }

        accountNumber = accountNumber.trim();

        for (Account account : bank.getAccounts()) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    // =========================
    // Find Bank.Account By Number


    public Account findAccountByNumber(String accountNumber) {

        Account account = searchAccountByNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                    "Bank.Account not found."
            );
        }

        return account;
    }

    // =========================
    // Remove Bank.Account


    public void removeAccount(String accountNumber) {

        Account account = findAccountByNumber(accountNumber);

        bank.getAccounts().remove(account);

        System.out.println(
                "Bank.Account " + accountNumber
                        + " has been removed successfully."
        );
    }

    // =========================
    // Update Bank.Account


    public void updateAccount(
            String accountNumber,
            double balance,
            AccountType accountType,
            Customer customer) {

        Account account = findAccountByNumber(accountNumber);

        account.setBalance(balance);
        account.setAccountType(accountType);
        account.setCustomer(customer);

        System.out.println(
                "Bank.Account " + accountNumber
                        + " has been updated successfully."
        );
    }

    // =========================
    // View All Accounts


    public void viewAccounts() {

        if (bank.getAccounts().isEmpty()) {

            System.out.println(
                    "No accounts in the list."
            );

            return;
        }

        for (Account account : bank.getAccounts()) {

            System.out.println(account);
        }
    }

    // =========================
    // Deposit


    public void deposit(
            String accountNumber,
            double amount) {

        Account account = findAccountByNumber(accountNumber);

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than 0."
            );
        }

        double newBalance =
                account.getBalance() + amount;

        account.setBalance(newBalance);

        System.out.println(
                "Deposited " + amount
                        + " to account "
                        + accountNumber
        );

        System.out.println(
                "New Balance: " + account.getBalance()
        );
    }

    // =========================
    // Withdraw


    public void withdraw(
            String accountNumber,
            double amount) {

        Account account = findAccountByNumber(accountNumber);

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than 0."
            );
        }

        if (amount > account.getBalance()) {
            throw new IllegalArgumentException(
                    "Insufficient balance."
            );
        }

        double newBalance =
                account.getBalance() - amount;

        account.setBalance(newBalance);

        System.out.println(
                "Withdrawn " + amount
                        + " from account "
                        + accountNumber
        );

        System.out.println(
                "New Balance: " + account.getBalance()
        );
    }

    // =========================
    // Transfer


    public void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            double amount) {

        if (fromAccountNumber == null
                || toAccountNumber == null) {

            throw new IllegalArgumentException(
                    "Bank.Account numbers cannot be null."
            );
        }

        if (fromAccountNumber.equals(toAccountNumber)) {

            throw new IllegalArgumentException(
                    "Cannot transfer to the same account."
            );
        }

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Transfer amount must be greater than 0."
            );
        }

        Account fromAccount =
                findAccountByNumber(fromAccountNumber);

        Account toAccount =
                findAccountByNumber(toAccountNumber);

        if (amount > fromAccount.getBalance()) {

            throw new IllegalArgumentException(
                    "Insufficient balance."
            );
        }

        fromAccount.setBalance(
                fromAccount.getBalance() - amount
        );

        toAccount.setBalance(
                toAccount.getBalance() + amount
        );

        System.out.println(
                "Transferred " + amount
                        + " from "
                        + fromAccountNumber
                        + " to "
                        + toAccountNumber
        );
    }
}

