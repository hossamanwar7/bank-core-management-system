package bank;

public class Loan {

    private int loanId;
    private double amount;
    private int duration;
    private double interestRate;
    private LoanType loanType;
    private LoanStatus loanStatus;
    private Customer customer;

    public Loan(int loanId,
                double amount,
                int duration,
                double interestRate,
                LoanType loanType,
                LoanStatus loanStatus,
                Customer customer) {

        setLoanId(loanId);
        setAmount(amount);
        setDuration(duration);
        setInterestRate(interestRate);
        setLoanType(loanType);
        setLoanStatus(loanStatus);
        setCustomer(customer);
    }

    public int getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public int getDuration() {
        return duration;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setLoanId(int loanId) {

        if (loanId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid loan ID, must be greater than 0."
            );
        }

        this.loanId = loanId;
    }

    public void setAmount(double amount) {

        if (amount < 5000) {
            throw new IllegalArgumentException(
                    "Bank.Loan amount cannot be less than 5000."
            );
        }

        this.amount = amount;
    }

    public void setDuration(int duration) {

        if (duration < 12) {
            throw new IllegalArgumentException(
                    "Bank.Loan duration cannot be less than 12 months."
            );
        }

        this.duration = duration;
    }

    public void setInterestRate(double interestRate) {

        if (interestRate < 10) {
            throw new IllegalArgumentException(
                    "Interest rate cannot be less than 10%."
            );
        }

        this.interestRate = interestRate;
    }

    public void setLoanType(LoanType loanType) {

        if (loanType == null) {
            throw new IllegalArgumentException(
                    "Bank.Loan type cannot be null."
            );
        }

        this.loanType = loanType;
    }

    public void setLoanStatus(LoanStatus loanStatus) {

        if (loanStatus == null) {
            throw new IllegalArgumentException(
                    "Bank.Loan status cannot be null."
            );
        }

        this.loanStatus = loanStatus;
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

        return "===============\n" +
                "Bank.Loan ID : " + loanId +
                "\nBank.Customer : " + customer +
                "\nAmount : " + amount +
                "\nInterest Rate : " + interestRate + "%" +
                "\nDuration : " + duration + " months" +
                "\nBank.Loan Type : " + loanType +
                "\nBank.Loan Status : " + loanStatus +
                "\n===============";
    }
}