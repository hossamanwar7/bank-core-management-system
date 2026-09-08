
import bank.Bank;
import bank.Customer;
import bank.Employee;
import bank.UserService;
import gui.LoginFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // =========================
            // Look and Feel
            // =========================

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            // =========================
            // Create Bank
            // =========================

            Bank bank = new Bank(
                    "MY BANK",
                    "BANK01",
                    "Cairo, Egypt"
            );

            // =========================
            // Create User Service
            // =========================

            UserService userService =
                    new UserService(bank);

            // =========================
            // Test Employee
            // =========================

            Employee employee =
                    new Employee(
                            "admin",
                            "Admin@12345",
                            1,                  // userId
                            1,                  // empId
                            "System Admin",     // fullname
                            "Manager",          // position
                            10000               // salary
                    );

            userService.addUser(employee);

            // =========================
            // Test Customer
            // =========================

            Customer customer =
                    new Customer(
                            "customer1",
                            "Customer@12345",
                            2,                  // userId
                            1,                  // customerId
                            "Ahmed Mohamed",    // fullName
                            "ahmed@gmail.com",   // email
                            "12345678901234",    // nationalId
                            "Cairo, Egypt"      // address
                    );

            userService.addUser(customer);

            // =========================
            // Open Login
            // =========================

            LoginFrame loginFrame =
                    new LoginFrame(
                            bank,
                            userService
                    );

            loginFrame.setVisible(true);
        });
    }
}

