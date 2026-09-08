package bank;

public class UserService {

    private Bank bank;

    public UserService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Login


    public User login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty."
            );
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty."
            );
        }

        username = username.trim();

        // Search Employees
        for (Employee employee : bank.getEmployees()) {

            if (employee.getUsername().equals(username)
                    && employee.getPassword().equals(password)) {

                return employee;
            }
        }

        // Search Customers
        for (Customer customer : bank.getCustomers()) {

            if (customer.getUsername().equals(username)
                    && customer.getPassword().equals(password)) {

                return customer;
            }
        }

        throw new IllegalArgumentException(
                "Invalid username or password."
        );
    }

    // =========================
    // Find Bank.User


    public User findUserByUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty."
            );
        }

        username = username.trim();

        // Search Employees
        for (Employee employee : bank.getEmployees()) {

            if (employee.getUsername().equals(username)) {
                return employee;
            }
        }

        // Search Customers
        for (Customer customer : bank.getCustomers()) {

            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }

        return null;
    }

    // =========================
    // Check Username


    public boolean usernameExists(String username) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        return findUserByUsername(username.trim()) != null;
    }

    // =========================
    // Add Bank.User


    public void addUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "Bank.User cannot be null."
            );
        }

        if (usernameExists(user.getUsername())) {
            throw new IllegalArgumentException(
                    "Username already exists."
            );
        }

        if (user instanceof Employee) {

            bank.addEmployee((Employee) user);

        } else if (user instanceof Customer) {

            bank.addCustomer((Customer) user);

        } else {

            throw new IllegalArgumentException(
                    "Unsupported user type."
            );
        }
    }

    // =========================
    // Remove Bank.User


    public void removeUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "Bank.User cannot be null."
            );
        }

        if (user instanceof Employee) {

            bank.getEmployees().remove(user);

        } else if (user instanceof Customer) {

            bank.getCustomers().remove(user);

        } else {

            throw new IllegalArgumentException(
                    "Unsupported user type."
            );
        }
    }



    // =========================
    // Change Password


    public void changePassword(User user, String newPassword) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "Bank.User cannot be null."
            );
        }

        if (newPassword == null
                || newPassword.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Password cannot be empty."
            );
        }

        user.setPassword(newPassword);
    }

    public Bank getBank() {
        return bank;
    }
}

