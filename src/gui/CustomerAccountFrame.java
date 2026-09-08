package gui;

import bank.Account;
import bank.Bank;
import bank.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerAccountFrame extends JFrame {

    private Bank bank;
    private Customer customer;
    private CustomerDashboard dashboard;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    private JLabel accountNumberValue;
    private JLabel balanceValue;
    private JLabel accountTypeValue;

    public CustomerAccountFrame(
            Bank bank,
            Customer customer,
            CustomerDashboard dashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null."
            );
        }

        this.bank = bank;
        this.customer = customer;
        this.dashboard = dashboard;

        setTitle(
                "My Account - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadCustomerAccount();
    }

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );

        // =========================
        // Header
        // =========================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                PRIMARY_COLOR
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        JLabel titleLabel =
                new JLabel("MY ACCOUNT");

        titleLabel.setForeground(
                Color.WHITE
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        JLabel customerLabel =
                new JLabel(
                        "Customer: " +
                                customer.getFullName()
                );

        customerLabel.setForeground(
                Color.WHITE
        );

        customerLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        headerPanel.add(
                customerLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // Account Information Panel
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setBackground(
                BACKGROUND_COLOR
        );

        JPanel accountPanel =
                new JPanel();

        accountPanel.setLayout(
                new BoxLayout(
                        accountPanel,
                        BoxLayout.Y_AXIS
                )
        );

        accountPanel.setPreferredSize(
                new Dimension(
                        550,
                        430
                )
        );

        accountPanel.setBackground(
                Color.WHITE
        );

        accountPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                30,
                                40,
                                30,
                                40
                        )
                )
        );

        JLabel infoTitle =
                new JLabel(
                        "Account Information"
                );

        infoTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        infoTitle.setForeground(
                PRIMARY_COLOR
        );

        infoTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        accountPanel.add(infoTitle);

        accountPanel.add(
                Box.createVerticalStrut(30)
        );

        // Customer Name

        accountPanel.add(
                createInfoRow(
                        "Full Name:",
                        customer.getFullName()
                )
        );

        accountPanel.add(
                Box.createVerticalStrut(15)
        );

        // Customer ID

        accountPanel.add(
                createInfoRow(
                        "Customer ID:",
                        String.valueOf(
                                customer.getCustomerId()
                        )
                )
        );

        accountPanel.add(
                Box.createVerticalStrut(15)
        );

        // Username

        accountPanel.add(
                createInfoRow(
                        "Username:",
                        customer.getUsername()
                )
        );

        accountPanel.add(
                Box.createVerticalStrut(15)
        );

        // Email

        accountPanel.add(
                createInfoRow(
                        "Email:",
                        customer.getEmail()
                )
        );

        accountPanel.add(
                Box.createVerticalStrut(15)
        );

        // Account Number

        accountNumberValue =
                new JLabel("-");

        accountPanel.add(
                createDynamicInfoRow(
                        "Account Number:",
                        accountNumberValue
                )
        );

        accountPanel.add(
                Box.createVerticalStrut(15)
        );

        // Account Type

        accountTypeValue =
                new JLabel("-");

        accountPanel.add(
                createDynamicInfoRow(
                        "Account Type:",
                        accountTypeValue
                )
        );

        accountPanel.add(
                Box.createVerticalStrut(15)
        );

        // Balance

        balanceValue =
                new JLabel("-");

        accountPanel.add(
                createDynamicInfoRow(
                        "Balance:",
                        balanceValue
                )
        );

        centerPanel.add(accountPanel);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Bottom Panel
        // =========================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                15
                        )
                );

        bottomPanel.setBackground(
                BACKGROUND_COLOR
        );

        JButton refreshButton =
                new JButton("Refresh");

        JButton backButton =
                new JButton(
                        "Back to Dashboard"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        130,
                        40
                )
        );

        backButton.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );

        refreshButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        backButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        bottomPanel.add(refreshButton);
        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Button Actions
        // =========================

        refreshButton.addActionListener(
                e -> loadCustomerAccount()
        );

        backButton.addActionListener(e -> {

            dispose();

            dashboard.setVisible(true);
        });

        setContentPane(mainPanel);
    }

    // =========================
    // Load Customer Account
    // =========================

    private void loadCustomerAccount() {

        Account customerAccount = null;

        for (Account account :
                bank.getAccounts()) {

            if (account.getCustomer() == customer) {

                customerAccount = account;

                break;
            }
        }

        if (customerAccount == null) {

            accountNumberValue.setText(
                    "No account"
            );

            accountTypeValue.setText(
                    "No account"
            );

            balanceValue.setText(
                    "No account"
            );

            return;
        }

        accountNumberValue.setText(
                customerAccount.getAccountNumber()
        );

        accountTypeValue.setText(
                String.valueOf(
                        customerAccount.getAccountType()
                )
        );

        balanceValue.setText(
                String.format(
                        "%.2f",
                        customerAccount.getBalance()
                )
        );
    }

    // =========================
    // Static Info Row
    // =========================

    private JPanel createInfoRow(
            String label,
            String value) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        JLabel labelComponent =
                new JLabel(label);

        labelComponent.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JLabel valueComponent =
                new JLabel(value);

        valueComponent.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        panel.add(
                labelComponent,
                BorderLayout.WEST
        );

        panel.add(
                valueComponent,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================
    // Dynamic Info Row
    // =========================

    private JPanel createDynamicInfoRow(
            String label,
            JLabel valueComponent) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        JLabel labelComponent =
                new JLabel(label);

        labelComponent.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        valueComponent.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        panel.add(
                labelComponent,
                BorderLayout.WEST
        );

        panel.add(
                valueComponent,
                BorderLayout.CENTER
        );

        return panel;
    }
}