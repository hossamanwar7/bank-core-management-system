package bank;

public class Account {

    private String accountNumber;
    private double balance;
    private AccountType accountType;

    private Customer customer;

    public Account(String accountNumber,
                   double balance,
                   AccountType accountType,
                   Customer customer) {

        setAccountNumber(accountNumber);
        setBalance(balance);
        setAccountType(accountType);
        setCustomer(customer);
    }

    // Getters

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Setters

    public void setAccountNumber(String accountNumber) {

        if (accountNumber == null ||
                accountNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Bank.Account number cannot be null or empty."
            );
        }

        if (accountNumber.trim().length() < 14) {

            throw new IllegalArgumentException(
                    "Bank.Account number must contain at least 14 characters."
            );
        }

        this.accountNumber = accountNumber.trim();
    }

    public void setBalance(double balance) {

        if (balance < 100) {

            throw new IllegalArgumentException(
                    "The balance cannot be less than 100."
            );
        }

        this.balance = balance;
    }

    public void setAccountType(AccountType accountType) {

        if (accountType == null) {

            throw new IllegalArgumentException(
                    "Bank.Account type cannot be null."
            );
        }

        this.accountType = accountType;
    }

    public void setCustomer(Customer customer) {

        if (customer == null) {

            throw new IllegalArgumentException(
                    "Bank.Customer cannot be null."
            );
        }

        this.customer = customer;
    }

    @Override
    public String toString() {

        return "=========================\n" +
                "Bank.Account Number : " + accountNumber +
                "\nBank.Customer : " + customer.getFullName() +
                "\nBank.Account Type : " + accountType +
                "\nBalance : " + balance +
                "\n=========================";
    }
}