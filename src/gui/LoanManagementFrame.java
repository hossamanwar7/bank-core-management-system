package gui;

import bank.Bank;
import bank.Customer;
import bank.Loan;
import bank.LoanService;
import bank.LoanStatus;
import bank.LoanType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoanManagementFrame extends JFrame {

    private Bank bank;
    private LoanService loanService;
    private EmployeeDashboard dashboard;

    private JTextField loanIdField;
    private JTextField amountField;
    private JTextField durationField;
    private JTextField interestRateField;
    private JComboBox<LoanType> loanTypeComboBox;
    private JComboBox<LoanStatus> loanStatusComboBox;
    private JTextField customerIdField;

    private JTable loanTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public LoanManagementFrame(
            Bank bank,
            EmployeeDashboard dashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
        this.dashboard = dashboard;

        loanService =
                new LoanService(bank);

        setTitle(
                "Loan Management - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadLoans();
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
                        "LOAN MANAGEMENT"
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
        // Loan ID
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Loan ID"),
                gbc
        );

        loanIdField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                loanIdField,
                gbc
        );

        // =========================
        // Amount
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Amount"),
                gbc
        );

        amountField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                amountField,
                gbc
        );

        // =========================
        // Duration
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Duration (Months)"),
                gbc
        );

        durationField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                durationField,
                gbc
        );

        // =========================
        // Interest Rate
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Interest Rate (%)"),
                gbc
        );

        interestRateField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                interestRateField,
                gbc
        );

        // =========================
        // Loan Type
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Loan Type"),
                gbc
        );

        loanTypeComboBox =
                new JComboBox<>(
                        LoanType.values()
                );

        gbc.gridx = 1;

        formPanel.add(
                loanTypeComboBox,
                gbc
        );

        // =========================
        // Loan Status
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Loan Status"),
                gbc
        );

        loanStatusComboBox =
                new JComboBox<>(
                        LoanStatus.values()
                );

        loanStatusComboBox.setSelectedItem(
                LoanStatus.PENDING
        );

        gbc.gridx = 3;

        formPanel.add(
                loanStatusComboBox,
                gbc
        );

        // =========================
        // Customer ID
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Customer ID"),
                gbc
        );

        customerIdField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                customerIdField,
                gbc
        );

        // =========================
        // Buttons
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
                new JButton("Add Loan");

        JButton updateButton =
                new JButton("Update Loan");

        JButton deleteButton =
                new JButton("Delete Loan");

        JButton approveButton =
                new JButton("Approve");

        JButton rejectButton =
                new JButton("Reject");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
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
                "Loan ID",
                "Customer ID",
                "Customer Name",
                "Amount",
                "Duration",
                "Interest Rate",
                "Loan Type",
                "Status"
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

        loanTable =
                new JTable(tableModel);

        loanTable.setRowHeight(35);

        loanTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        loanTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        loanTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        loanTable
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

        JButton viewByCustomerButton =
                new JButton(
                        "View By Customer"
                );

        JButton backButton =
                new JButton(
                        "Back to Dashboard"
                );

        bottomPanel.add(refreshButton);
        bottomPanel.add(viewByCustomerButton);
        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Actions
        // =========================

        addButton.addActionListener(
                e -> addLoan()
        );

        updateButton.addActionListener(
                e -> updateLoan()
        );

        deleteButton.addActionListener(
                e -> deleteLoan()
        );

        approveButton.addActionListener(
                e -> approveLoan()
        );

        rejectButton.addActionListener(
                e -> rejectLoan()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadLoans()
        );

        viewByCustomerButton.addActionListener(
                e -> viewLoansByCustomer()
        );

        backButton.addActionListener(e -> {

            dispose();

            if (dashboard != null) {
                dashboard.setVisible(true);
            }
        });

        loanTable.getSelectionModel()
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
    // Add Loan
    // =========================

    private void addLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            loanIdField
                                    .getText()
                                    .trim()
                    );

            double amount =
                    Double.parseDouble(
                            amountField
                                    .getText()
                                    .trim()
                    );

            int duration =
                    Integer.parseInt(
                            durationField
                                    .getText()
                                    .trim()
                    );

            double interestRate =
                    Double.parseDouble(
                            interestRateField
                                    .getText()
                                    .trim()
                    );

            LoanType loanType =
                    (LoanType)
                            loanTypeComboBox
                                    .getSelectedItem();

            LoanStatus loanStatus =
                    (LoanStatus)
                            loanStatusComboBox
                                    .getSelectedItem();

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            Customer customer =
                    findCustomerById(
                            customerId
                    );

            if (customer == null) {

                throw new IllegalArgumentException(
                        "Customer not found."
                );
            }

            Loan loan =
                    new Loan(
                            loanId,
                            amount,
                            duration,
                            interestRate,
                            loanType,
                            loanStatus,
                            customer
                    );

            loanService.addLoan(loan);

            JOptionPane.showMessageDialog(
                    this,
                    "Loan added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadLoans();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
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
    // Update Loan
    // =========================

    private void updateLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            loanIdField
                                    .getText()
                                    .trim()
                    );

            double amount =
                    Double.parseDouble(
                            amountField
                                    .getText()
                                    .trim()
                    );

            int duration =
                    Integer.parseInt(
                            durationField
                                    .getText()
                                    .trim()
                    );

            double interestRate =
                    Double.parseDouble(
                            interestRateField
                                    .getText()
                                    .trim()
                    );

            LoanType loanType =
                    (LoanType)
                            loanTypeComboBox
                                    .getSelectedItem();

            LoanStatus loanStatus =
                    (LoanStatus)
                            loanStatusComboBox
                                    .getSelectedItem();

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            Customer customer =
                    findCustomerById(
                            customerId
                    );

            if (customer == null) {

                throw new IllegalArgumentException(
                        "Customer not found."
                );
            }

            loanService.updateLoan(
                    loanId,
                    amount,
                    duration,
                    interestRate,
                    loanType,
                    loanStatus,
                    customer
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Loan updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadLoans();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
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
    // Delete Loan
    // =========================

    private void deleteLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            loanIdField
                                    .getText()
                                    .trim()
                    );

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this loan?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            loanService.removeLoan(
                    loanId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Loan deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadLoans();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Loan ID must be a valid number.",
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
    // Approve Loan
    // =========================

    private void approveLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            loanIdField
                                    .getText()
                                    .trim()
                    );

            loanService.approveLoan(
                    loanId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Loan approved successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadLoans();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Loan ID must be a valid number.",
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
    // Reject Loan
    // =========================

    private void rejectLoan() {

        try {

            int loanId =
                    Integer.parseInt(
                            loanIdField
                                    .getText()
                                    .trim()
                    );

            loanService.rejectLoan(
                    loanId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Loan rejected successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadLoans();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Loan ID must be a valid number.",
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
    // View By Customer
    // =========================

    private void viewLoansByCustomer() {

        String customerIdText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Customer ID:"
                );

        if (customerIdText == null) {
            return;
        }

        try {

            int customerId =
                    Integer.parseInt(
                            customerIdText.trim()
                    );

            Customer customer =
                    findCustomerById(
                            customerId
                    );

            if (customer == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            tableModel.setRowCount(0);

            boolean found = false;

            for (Loan loan :
                    bank.getLoans()) {

                if (loan.getCustomer()
                        .getCustomerId()
                        == customerId) {

                    addLoanToTable(loan);

                    found = true;
                }
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        this,
                        "No loans found for this customer.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Load Loans
    // =========================

    private void loadLoans() {

        tableModel.setRowCount(0);

        for (Loan loan :
                bank.getLoans()) {

            addLoanToTable(loan);
        }
    }

    // =========================
    // Add Loan To Table
    // =========================

    private void addLoanToTable(Loan loan) {

        Customer customer =
                loan.getCustomer();

        tableModel.addRow(
                new Object[]{
                        loan.getLoanId(),
                        customer.getCustomerId(),
                        customer.getFullName(),
                        String.format(
                                "%.2f",
                                loan.getAmount()
                        ),
                        loan.getDuration()
                                + " months",
                        String.format(
                                "%.2f%%",
                                loan.getInterestRate()
                        ),
                        loan.getLoanType(),
                        loan.getLoanStatus()
                }
        );
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
                loanTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        loanIdField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        0
                ).toString()
        );

        customerIdField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString()
        );

        amountField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        3
                ).toString()
        );

        String duration =
                tableModel.getValueAt(
                                selectedRow,
                                4
                        ).toString()
                        .replace(
                                " months",
                                ""
                        );

        durationField.setText(
                duration
        );

        String interestRate =
                tableModel.getValueAt(
                                selectedRow,
                                5
                        ).toString()
                        .replace(
                                "%",
                                ""
                        );

        interestRateField.setText(
                interestRate
        );

        loanTypeComboBox.setSelectedItem(
                LoanType.valueOf(
                        tableModel.getValueAt(
                                selectedRow,
                                6
                        ).toString()
                )
        );

        loanStatusComboBox.setSelectedItem(
                LoanStatus.valueOf(
                        tableModel.getValueAt(
                                selectedRow,
                                7
                        ).toString()
                )
        );
    }

    // =========================
    // Clear Fields
    // =========================

    private void clearFields() {

        loanIdField.setText("");
        amountField.setText("");
        durationField.setText("");
        interestRateField.setText("");
        customerIdField.setText("");

        loanTypeComboBox.setSelectedIndex(0);

        loanStatusComboBox.setSelectedItem(
                LoanStatus.PENDING
        );

        loanTable.clearSelection();
    }
}