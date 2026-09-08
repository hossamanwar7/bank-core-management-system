package bank;

import java.time.LocalDate;

public class Transaction {

    private int transactionId;
    private TransactionType transActionType;
    private double amount;
    private Account account;
    private LocalDate transactionDate;

    public Transaction(int transactionId, TransactionType transActionType,
                       double amount, Account account, LocalDate transactionDate) {

        setTransactionId(transactionId);
        setAmount(amount);
        setAccount(account);
        setTransactionDate(transactionDate);
        setTransActionType(transActionType);
    }

    public int getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransActionType() {
        return transActionType;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionId(int transactionId) {
        if (transactionId <= 0) {
            throw new IllegalArgumentException(
                    "Bank.Transaction Id cannot be less than or equal to 0."
            );
        }

        this.transactionId = transactionId;
    }

    public void setTransActionType(TransactionType transActionType) {

        if (transActionType == null) {
            throw new IllegalArgumentException(
                    "Bank.Transaction Type cannot be null."
            );
        }

        this.transActionType = transActionType;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "The Bank.Transaction amount cannot be less than or equal to 0."
            );
        }

        this.amount = amount;
    }

    public void setTransactionDate(LocalDate transactionDate) {

        if (transactionDate == null) {
            throw new IllegalArgumentException(
                    "Bank.Transaction Date cannot be null."
            );
        }

        this.transactionDate = transactionDate;
    }

    public void setAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException(
                    "Bank.Account cannot be null!"
            );
        }

        this.account = account;
    }

    @Override
    public String toString() {
        return "============================\n" +
                "\nBank.Transaction ID : " + transactionId +
                "\nBank.Account : " + account +
                "\nAmount : " + amount +
                "\nBank.Transaction Type : " + transActionType +
                "\nBank.Transaction Date : " + transactionDate +
                "\n============================";
    }
}

