package bank;

public class TransactionService {

    private Bank bank;

    public TransactionService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Add Bank.Transaction


    public void addTransaction(Transaction transaction) {

        if (transaction == null) {
            throw new IllegalArgumentException(
                    "Bank.Transaction cannot be null."
            );
        }

        if (searchTransactionById(
                transaction.getTransactionId()) != null) {

            throw new IllegalArgumentException(
                    "Bank.Transaction ID already exists."
            );
        }

        bank.addTransaction(transaction);

        System.out.println(
                "Bank.Transaction "
                        + transaction.getTransactionId()
                        + " has been added successfully."
        );
    }

    // =========================
    // Search Bank.Transaction By ID


    public Transaction searchTransactionById(int transactionId) {

        for (Transaction transaction : bank.getTransactions()) {

            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }

        return null;
    }

    // =========================
    // Find Bank.Transaction By ID


    public Transaction findTransactionById(int transactionId) {

        Transaction transaction =
                searchTransactionById(transactionId);

        if (transaction == null) {
            throw new IllegalArgumentException(
                    "Bank.Transaction not found."
            );
        }

        return transaction;
    }

    // =========================
    // Remove Bank.Transaction


    public void removeTransaction(int transactionId) {

        Transaction transaction =
                findTransactionById(transactionId);

        bank.getTransactions().remove(transaction);

        System.out.println(
                "Bank.Transaction "
                        + transactionId
                        + " has been removed successfully."
        );
    }

    // =========================
    // Get Transactions By Bank.Account


    public void viewTransactionsByAccount(
            String accountNumber) {

        if (accountNumber == null
                || accountNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Bank.Account number cannot be empty."
            );
        }

        boolean found = false;

        for (Transaction transaction
                : bank.getTransactions()) {

            if (transaction.getAccount()
                    .getAccountNumber()
                    .equals(accountNumber)) {

                System.out.println(transaction);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No transactions found for account "
                            + accountNumber
            );
        }
    }

    // =========================
    // View All Transactions


    public void viewTransactions() {

        if (bank.getTransactions().isEmpty()) {

            System.out.println(
                    "Bank.Transaction list is empty."
            );

            return;
        }

        for (Transaction transaction
                : bank.getTransactions()) {

            System.out.println(transaction);
        }
    }

    // =========================
    // Get Transactions Count


    public int getTransactionsCount() {

        return bank.getTransactions().size();
    }
}

