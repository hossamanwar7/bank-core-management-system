package gui;

import bank.Account;
import bank.AccountService;
import bank.AccountType;
import bank.Bank;
import bank.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountManagementFrame extends JFrame {

    private Bank bank;
    private AccountService accountService;
    private EmployeeDashboard dashboard;

    private JTextField accountNumberField;
    private JTextField balanceField;
    private JComboBox<AccountType> accountTypeComboBox;
    private JTextField customerIdField;

    private JTable accountTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public AccountManagementFrame(
            Bank bank,
            EmployeeDashboard dashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
        this.dashboard = dashboard;

        accountService =
                new AccountService(bank);

        setTitle(
                "Account Management - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadAccounts();
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
                new JLabel(
                        "ACCOUNT MANAGEMENT"
                );

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

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // Form
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                Color.WHITE
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        7,
                        7,
                        7
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // =========================
        // Account Number
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Account Number"),
                gbc
        );

        accountNumberField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                accountNumberField,
                gbc
        );

        // =========================
        // Balance
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Balance"),
                gbc
        );

        balanceField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                balanceField,
                gbc
        );

        // =========================
        // Account Type
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Account Type"),
                gbc
        );

        accountTypeComboBox =
                new JComboBox<>(
                        AccountType.values()
                );

        gbc.gridx = 1;

        formPanel.add(
                accountTypeComboBox,
                gbc
        );

        // =========================
        // Customer ID
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Customer ID"),
                gbc
        );

        customerIdField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                customerIdField,
                gbc
        );

        // =========================
        // CRUD Buttons
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        JButton addButton =
                new JButton("Add Account");

        JButton updateButton =
                new JButton("Update Account");

        JButton deleteButton =
                new JButton("Delete Account");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;

        formPanel.add(
                buttonPanel,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        // =========================
        // Table
        // =========================

        String[] columns = {
                "Account Number",
                "Balance",
                "Account Type",
                "Customer ID",
                "Customer Name"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        accountTable =
                new JTable(tableModel);

        accountTable.setRowHeight(35);

        accountTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        accountTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        accountTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        accountTable
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        10,
                        30
                )
        );

        mainPanel.add(
                scrollPane,
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

        JButton depositButton =
                new JButton("Deposit");

        JButton withdrawButton =
                new JButton("Withdraw");

        JButton transferButton =
                new JButton("Transfer");

        JButton backButton =
                new JButton(
                        "Back to Dashboard"
                );

        bottomPanel.add(refreshButton);
        bottomPanel.add(depositButton);
        bottomPanel.add(withdrawButton);
        bottomPanel.add(transferButton);
        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Actions
        // =========================

        addButton.addActionListener(
                e -> addAccount()
        );

        updateButton.addActionListener(
                e -> updateAccount()
        );

        deleteButton.addActionListener(
                e -> deleteAccount()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadAccounts()
        );

        depositButton.addActionListener(
                e -> deposit()
        );

        withdrawButton.addActionListener(
                e -> withdraw()
        );

        transferButton.addActionListener(
                e -> transfer()
        );

        backButton.addActionListener(e -> {

            dispose();

            if (dashboard != null) {
                dashboard.setVisible(true);
            }
        });

        accountTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {
                                fillFieldsFromTable();
                            }
                        }
                );

        setContentPane(mainPanel);
    }

    // =========================
    // Add Account
    // =========================

    private void addAccount() {

        try {

            String accountNumber =
                    accountNumberField
                            .getText()
                            .trim();

            double balance =
                    Double.parseDouble(
                            balanceField
                                    .getText()
                                    .trim()
                    );

            AccountType accountType =
                    (AccountType)
                            accountTypeComboBox
                                    .getSelectedItem();

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            Customer customer =
                    findCustomerById(customerId);

            if (customer == null) {

                throw new IllegalArgumentException(
                        "Customer not found."
                );
            }

            Account account =
                    new Account(
                            accountNumber,
                            balance,
                            accountType,
                            customer
                    );

            accountService.addAccount(account);

            JOptionPane.showMessageDialog(
                    this,
                    "Account added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAccounts();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Balance and Customer ID must be valid numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Update Account
    // =========================

    private void updateAccount() {

        try {

            String accountNumber =
                    accountNumberField
                            .getText()
                            .trim();

            double balance =
                    Double.parseDouble(
                            balanceField
                                    .getText()
                                    .trim()
                    );

            AccountType accountType =
                    (AccountType)
                            accountTypeComboBox
                                    .getSelectedItem();

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            Customer customer =
                    findCustomerById(customerId);

            if (customer == null) {

                throw new IllegalArgumentException(
                        "Customer not found."
                );
            }

            accountService.updateAccount(
                    accountNumber,
                    balance,
                    accountType,
                    customer
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Account updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAccounts();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Balance and Customer ID must be valid numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Delete Account
    // =========================

    private void deleteAccount() {

        String accountNumber =
                accountNumberField
                        .getText()
                        .trim();

        if (accountNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Account Number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this account?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            accountService.removeAccount(
                    accountNumber
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Account deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAccounts();

            clearFields();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Deposit
    // =========================

    private void deposit() {

        String accountNumber =
                accountNumberField
                        .getText()
                        .trim();

        if (accountNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Account Number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String amountText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter deposit amount:"
                );

        if (amountText == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText.trim()
                    );

            accountService.deposit(
                    accountNumber,
                    amount
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Deposit completed successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAccounts();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Withdraw
    // =========================

    private void withdraw() {

        String accountNumber =
                accountNumberField
                        .getText()
                        .trim();

        if (accountNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Account Number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String amountText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter withdrawal amount:"
                );

        if (amountText == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText.trim()
                    );

            accountService.withdraw(
                    accountNumber,
                    amount
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Withdrawal completed successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAccounts();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Transfer
    // =========================

    private void transfer() {

        String fromAccount =
                JOptionPane.showInputDialog(
                        this,
                        "Enter source account number:"
                );

        if (fromAccount == null) {
            return;
        }

        String toAccount =
                JOptionPane.showInputDialog(
                        this,
                        "Enter destination account number:"
                );

        if (toAccount == null) {
            return;
        }

        String amountText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter transfer amount:"
                );

        if (amountText == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText.trim()
                    );

            accountService.transfer(
                    fromAccount.trim(),
                    toAccount.trim(),
                    amount
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Transfer completed successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAccounts();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Load Accounts
    // =========================

    private void loadAccounts() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        for (Account account :
                bank.getAccounts()) {

            Customer customer =
                    account.getCustomer();

            tableModel.addRow(
                    new Object[]{
                            account.getAccountNumber(),
                            String.format(
                                    "%.2f",
                                    account.getBalance()
                            ),
                            account.getAccountType(),
                            customer.getCustomerId(),
                            customer.getFullName()
                    }
            );
        }
    }

    // =========================
    // Find Customer
    // =========================

    private Customer findCustomerById(
            int customerId) {

        for (Customer customer :
                bank.getCustomers()) {

            if (customer.getCustomerId()
                    == customerId) {

                return customer;
            }
        }

        return null;
    }

    // =========================
    // Fill Fields
    // =========================

    private void fillFieldsFromTable() {

        int selectedRow =
                accountTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        accountNumberField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        0
                ).toString()
        );

        balanceField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString()
        );

        accountTypeComboBox.setSelectedItem(
                AccountType.valueOf(
                        tableModel.getValueAt(
                                selectedRow,
                                2
                        ).toString()
                )
        );

        customerIdField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        3
                ).toString()
        );
    }

    // =========================
    // Clear
    // =========================

    private void clearFields() {

        accountNumberField.setText("");
        balanceField.setText("");
        customerIdField.setText("");

        accountTypeComboBox.setSelectedIndex(0);

        accountTable.clearSelection();
    }
}