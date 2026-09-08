package bank;

public class LoanService {

    private Bank bank;

    public LoanService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Add Bank.Loan
    // =========================

    public void addLoan(Loan loan) {

        if (loan == null) {
            throw new IllegalArgumentException(
                    "Bank.Loan cannot be null."
            );
        }

        if (loan.getAmount() < 5000) {
            throw new IllegalArgumentException(
                    "Bank.Loan amount cannot be less than 5000."
            );
        }

        if (searchLoanById(loan.getLoanId()) != null) {
            throw new IllegalArgumentException(
                    "Bank.Loan ID already exists."
            );
        }

        bank.addLoan(loan);

        System.out.println(
                "Bank.Loan " + loan.getLoanId()
                        + " has been added successfully."
        );
    }

    // =========================
    // Search Bank.Loan By ID
    // =========================

    public Loan searchLoanById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Bank.Loan ID must be greater than zero."
            );
        }

        for (Loan loan : bank.getLoans()) {

            if (loan.getLoanId() == id) {
                return loan;
            }
        }

        return null;
    }

    // =========================
    // Find Bank.Loan By ID
    // =========================

    public Loan findLoanById(int id) {

        Loan loan = searchLoanById(id);

        if (loan == null) {
            throw new IllegalArgumentException(
                    "Bank.Loan not found."
            );
        }

        return loan;
    }

    // =========================
    // Remove Bank.Loan
    // =========================

    public void removeLoan(int id) {

        Loan loan = findLoanById(id);

        bank.getLoans().remove(loan);

        System.out.println(
                "Bank.Loan " + id
                        + " has been removed successfully."
        );
    }

    // =========================
    // Update Bank.Loan
    // =========================

    public void updateLoan(
            int loanId,
            double amount,
            int duration,
            double interestRate,
            LoanType loanType,
            LoanStatus loanStatus,
            Customer customer) {

        Loan loan = findLoanById(loanId);

        loan.setAmount(amount);
        loan.setDuration(duration);
        loan. setInterestRate(interestRate);
        loan.setLoanType(loanType);
        loan.setLoanStatus(loanStatus);
        loan.setCustomer(customer);

        System.out.println(
                "Bank.Loan " + loanId
                        + " has been updated successfully."
        );
    }

    // =========================
    // View All Loans
    // =========================

    public void viewLoans() {

        if (bank.getLoans().isEmpty()) {

            System.out.println(
                    "No loans found."
            );

            return;
        }

        for (Loan loan : bank.getLoans()) {

            System.out.println(loan);
        }
    }

    // =========================
    // Approve Bank.Loan
    // =========================

    public void approveLoan(int loanId) {

        Loan loan = findLoanById(loanId);

        loan.setLoanStatus(LoanStatus.APPROVED);

        System.out.println(
                "Bank.Loan " + loanId
                        + " has been approved."
        );
    }

    // =========================
    // Reject Bank.Loan
    // =========================

    public void rejectLoan(int loanId) {

        Loan loan = findLoanById(loanId);

        loan.setLoanStatus(LoanStatus.REJECTED);

        System.out.println(
                "Bank.Loan " + loanId
                        + " has been rejected."
        );
    }

    // =========================
    // Get Loans By Bank.Customer
    // =========================

    public void viewLoansByCustomer(int customerId) {

        boolean found = false;

        for (Loan loan : bank.getLoans()) {

            if (loan.getCustomer().getCustomerId()
                    == customerId) {

                System.out.println(loan);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No loans found for customer "
                            + customerId
            );
        }
    }
}

