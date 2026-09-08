package bank;

public class Customer extends User {

    private int customerId;
    private String fullName;
    private String email;
    private String nationalId;
    private String address;

    public Customer(String username,
                    String password,
                    int userId,
                    int customerId,
                    String fullName,
                    String email,
                    String nationalId,
                    String address) {

        super(username, password, userId);

        setCustomerId(customerId);
        setFullName(fullName);
        setEmail(email);
        setNationalId(nationalId);
        setAddress(address);
    }

    // =========================
    // Getters

    public int getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getAddress() {
        return address;
    }

    // =========================
    // Setters

    public void setCustomerId(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Bank.Customer ID must be greater than 0."
            );
        }

        this.customerId = customerId;
    }

    public void setFullName(String fullName) {

        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Customer name cannot be empty."
            );
        }

        this.fullName = fullName.trim();
    }

    public void setEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Customer email cannot be empty."
            );
        }

        this.email = email.trim();
    }

    public void setNationalId(String nationalId) {

        if (nationalId == null || nationalId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Customer national ID cannot be empty."
            );
        }

        this.nationalId = nationalId.trim();
    }

    public void setAddress(String address) {

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Bank.Customer address cannot be empty."
            );
        }

        this.address = address.trim();
    }

    // =========================
    // toString

    @Override
    public String toString() {

        return "=============================\n" +
                "Bank.User ID : " + getUserId() +
                "\nUsername : " + getUsername() +
                "\nBank.Customer ID : " + customerId +
                "\nFull Name : " + fullName +
                "\nEmail : " + email +
                "\nNational ID : " + nationalId +
                "\nAddress : " + address +
                "\n=============================";
    }
}