
package gui;

import bank.Account;
import bank.Bank;
import bank.Transaction;
import bank.TransactionService;
import bank.TransactionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class TransactionManagementFrame extends JFrame {

    private Bank bank;
    private TransactionService transactionService;
    private EmployeeDashboard dashboard;

    private JTextField transactionIdField;
    private JComboBox<TransactionType> transactionTypeComboBox;
    private JTextField amountField;
    private JTextField accountNumberField;
    private JTextField transactionDateField;

    private JTable transactionTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public TransactionManagementFrame(
            Bank bank,
            EmployeeDashboard dashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
        this.dashboard = dashboard;

        transactionService =
                new TransactionService(bank);

        setTitle(
                "Transaction Management - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadTransactions();
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
                        "TRANSACTION MANAGEMENT"
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
        // Transaction ID
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Transaction ID"),
                gbc
        );

        transactionIdField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                transactionIdField,
                gbc
        );

        // =========================
        // Transaction Type
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Transaction Type"),
                gbc
        );

        transactionTypeComboBox =
                new JComboBox<>(
                        TransactionType.values()
                );

        gbc.gridx = 3;

        formPanel.add(
                transactionTypeComboBox,
                gbc
        );

        // =========================
        // Amount
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Amount"),
                gbc
        );

        amountField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                amountField,
                gbc
        );

        // =========================
        // Account Number
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Account Number"),
                gbc
        );

        accountNumberField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                accountNumberField,
                gbc
        );

        // =========================
        // Transaction Date
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Transaction Date"),
                gbc
        );

        transactionDateField =
                new JTextField();

        transactionDateField.setText(
                LocalDate.now().toString()
        );

        gbc.gridx = 1;

        formPanel.add(
                transactionDateField,
                gbc
        );

        // =========================
        // Add / Delete / Clear
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
                new JButton("Add Transaction");

        JButton deleteButton =
                new JButton("Delete Transaction");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
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
                "Transaction ID",
                "Transaction Type",
                "Amount",
                "Account Number",
                "Customer Name",
                "Transaction Date"
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

        transactionTable =
                new JTable(tableModel);

        transactionTable.setRowHeight(35);

        transactionTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        transactionTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        transactionTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        transactionTable
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

        JButton viewByAccountButton =
                new JButton(
                        "View By Account"
                );

        JButton backButton =
                new JButton(
                        "Back to Dashboard"
                );

        bottomPanel.add(refreshButton);
        bottomPanel.add(viewByAccountButton);
        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Actions
        // =========================

        addButton.addActionListener(
                e -> addTransaction()
        );

        deleteButton.addActionListener(
                e -> deleteTransaction()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadTransactions()
        );

        viewByAccountButton.addActionListener(
                e -> viewTransactionsByAccount()
        );

        backButton.addActionListener(e -> {

            dispose();

            if (dashboard != null) {
                dashboard.setVisible(true);
            }
        });

        transactionTable.getSelectionModel()
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
    // Add Transaction
    // =========================

    private void addTransaction() {

        try {

            int transactionId =
                    Integer.parseInt(
                            transactionIdField
                                    .getText()
                                    .trim()
                    );

            TransactionType transactionType =
                    (TransactionType)
                            transactionTypeComboBox
                                    .getSelectedItem();

            double amount =
                    Double.parseDouble(
                            amountField
                                    .getText()
                                    .trim()
                    );

            String accountNumber =
                    accountNumberField
                            .getText()
                            .trim();

            LocalDate transactionDate =
                    LocalDate.parse(
                            transactionDateField
                                    .getText()
                                    .trim()
                    );

            Account account =
                    findAccountByNumber(
                            accountNumber
                    );

            if (account == null) {

                throw new IllegalArgumentException(
                        "Account not found."
                );
            }

            Transaction transaction =
                    new Transaction(
                            transactionId,
                            transactionType,
                            amount,
                            account,
                            transactionDate
                    );

            transactionService.addTransaction(
                    transaction
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Transaction added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTransactions();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Transaction ID and Amount must be valid numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (java.time.format.DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Date must be in this format: yyyy-MM-dd",
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
    // Delete Transaction
    // =========================

    private void deleteTransaction() {

        try {

            int transactionId =
                    Integer.parseInt(
                            transactionIdField
                                    .getText()
                                    .trim()
                    );

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this transaction?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            transactionService.removeTransaction(
                    transactionId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Transaction deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTransactions();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Transaction ID must be a valid number.",
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
    // View By Account
    // =========================

    private void viewTransactionsByAccount() {

        String accountNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Account Number:"
                );

        if (accountNumber == null) {
            return;
        }

        accountNumber =
                accountNumber.trim();

        if (accountNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account Number cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Account account =
                findAccountByNumber(
                        accountNumber
                );

        if (account == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        tableModel.setRowCount(0);

        boolean found = false;

        for (Transaction transaction :
                bank.getTransactions()) {

            if (transaction.getAccount()
                    .getAccountNumber()
                    .equals(accountNumber)) {

                addTransactionToTable(
                        transaction
                );

                found = true;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "No transactions found for this account.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================
    // Load Transactions
    // =========================

    private void loadTransactions() {

        tableModel.setRowCount(0);

        for (Transaction transaction :
                bank.getTransactions()) {

            addTransactionToTable(
                    transaction
            );
        }
    }

    // =========================
    // Add Transaction To Table
    // =========================

    private void addTransactionToTable(
            Transaction transaction) {

        Account account =
                transaction.getAccount();

        tableModel.addRow(
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransActionType(),
                        String.format(
                                "%.2f",
                                transaction.getAmount()
                        ),
                        account.getAccountNumber(),
                        account.getCustomer()
                                .getFullName(),
                        transaction.getTransactionDate()
                }
        );
    }

    // =========================
    // Find Account
    // =========================

    private Account findAccountByNumber(
            String accountNumber) {

        if (accountNumber == null ||
                accountNumber.trim().isEmpty()) {

            return null;
        }

        for (Account account :
                bank.getAccounts()) {

            if (account.getAccountNumber()
                    .equals(accountNumber.trim())) {

                return account;
            }
        }

        return null;
    }

    // =========================
    // Fill Fields
    // =========================

    private void fillFieldsFromTable() {

        int selectedRow =
                transactionTable
                        .getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        transactionIdField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        0
                ).toString()
        );

        transactionTypeComboBox.setSelectedItem(
                TransactionType.valueOf(
                        tableModel.getValueAt(
                                selectedRow,
                                1
                        ).toString()
                )
        );

        amountField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        2
                ).toString()
        );

        accountNumberField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        3
                ).toString()
        );

        transactionDateField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        5
                ).toString()
        );
    }

    // =========================
    // Clear Fields
    // =========================

    private void clearFields() {

        transactionIdField.setText("");
        amountField.setText("");
        accountNumberField.setText("");

        transactionTypeComboBox.setSelectedIndex(0);

        transactionDateField.setText(
                LocalDate.now().toString()
        );

        transactionTable.clearSelection();
    }
}