package gui;

import bank.Bank;
import bank.Customer;
import bank.CustomerService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerManagementFrame extends JFrame {

    private Bank bank;
    private CustomerService customerService;
    private EmployeeDashboard employeeDashboard;

    private JTable customerTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;

    private JTextField userIdField;
    private JTextField customerIdField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField nationalIdField;
    private JTextField addressField;

    public CustomerManagementFrame(
            Bank bank,
            EmployeeDashboard employeeDashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
        this.employeeDashboard = employeeDashboard;
        this.customerService =
                new CustomerService(bank);

        setTitle("Customer Management");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadCustomers();
    }

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                new EmptyBorder(
                        20, 20, 20, 20
                )
        );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        // =================================================
        // TOP PANEL
        // =================================================

        JPanel topPanel =
                new JPanel(new BorderLayout());

        topPanel.setBackground(
                new Color(245, 247, 250)
        );

        JLabel titleLabel =
                new JLabel("CUSTOMER MANAGEMENT");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                new Color(25, 55, 109)
        );

        topPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        // =================================================
        // SEARCH PANEL
        // =================================================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        searchPanel.setBackground(
                new Color(245, 247, 250)
        );

        JLabel searchLabel =
                new JLabel("Customer ID:");

        searchField =
                new JTextField(10);

        JButton searchButton =
                new JButton("Search");

        JButton refreshButton =
                new JButton("Refresh");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        topPanel.add(
                searchPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {
                "Customer ID",
                "User ID",
                "Username",
                "Full Name",
                "Email",
                "National ID",
                "Address"
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

        customerTable =
                new JTable(tableModel);

        customerTable.setRowHeight(30);

        customerTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        customerTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        customerTable
                );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =================================================
        // FORM
        // =================================================

        JPanel formContainer =
                new JPanel(new BorderLayout(10, 10));

        formContainer.setBackground(
                Color.WHITE
        );

        formContainer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                15,
                                10
                        )
                );

        formPanel.setBackground(
                Color.WHITE
        );

        userIdField =
                new JTextField();

        customerIdField =
                new JTextField();

        usernameField =
                new JTextField();

        passwordField =
                new JTextField();

        fullNameField =
                new JTextField();

        emailField =
                new JTextField();

        nationalIdField =
                new JTextField();

        addressField =
                new JTextField();

        formPanel.add(
                createFieldPanel(
                        "User ID",
                        userIdField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "Customer ID",
                        customerIdField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "Username",
                        usernameField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "Password",
                        passwordField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "Full Name",
                        fullNameField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "Email",
                        emailField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "National ID",
                        nationalIdField
                )
        );

        formPanel.add(
                createFieldPanel(
                        "Address",
                        addressField
                )
        );

        formContainer.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        JButton addButton =
                new JButton("Add Customer");

        JButton updateButton =
                new JButton("Update Customer");

        JButton deleteButton =
                new JButton("Delete Customer");

        JButton clearButton =
                new JButton("Clear");

        JButton backButton =
                new JButton("Back to Dashboard");

        addButton.setPreferredSize(
                new Dimension(160, 40)
        );

        updateButton.setPreferredSize(
                new Dimension(160, 40)
        );

        deleteButton.setPreferredSize(
                new Dimension(160, 40)
        );

        clearButton.setPreferredSize(
                new Dimension(120, 40)
        );

        backButton.setPreferredSize(
                new Dimension(180, 40)
        );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        formContainer.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                formContainer,
                BorderLayout.SOUTH
        );

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        searchButton.addActionListener(
                e -> searchCustomer()
        );

        refreshButton.addActionListener(
                e -> {
                    loadCustomers();
                    clearFields();
                }
        );

        addButton.addActionListener(
                e -> addCustomer()
        );

        updateButton.addActionListener(
                e -> updateCustomer()
        );

        deleteButton.addActionListener(
                e -> deleteCustomer()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        backButton.addActionListener(
                e -> {

                    dispose();

                    if (employeeDashboard != null) {
                        employeeDashboard.setVisible(true);
                    }
                }
        );

        // =================================================
        // TABLE SELECTION
        // =================================================

        customerTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        fillFormFromTable();
                    }
                });

        setContentPane(mainPanel);
    }

    // =====================================================
    // CREATE FIELD PANEL
    // =====================================================

    private JPanel createFieldPanel(
            String label,
            JTextField field) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(5, 5)
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
                        12
                )
        );

        panel.add(
                labelComponent,
                BorderLayout.NORTH
        );

        field.setPreferredSize(
                new Dimension(150, 30)
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =====================================================
    // LOAD CUSTOMERS
    // =====================================================

    private void loadCustomers() {

        tableModel.setRowCount(0);

        for (Customer customer :
                bank.getCustomers()) {

            tableModel.addRow(
                    new Object[]{
                            customer.getCustomerId(),
                            customer.getUserId(),
                            customer.getUsername(),
                            customer.getFullName(),
                            customer.getEmail(),
                            customer.getNationalId(),
                            customer.getAddress()
                    }
            );
        }
    }

    // =====================================================
    // SEARCH CUSTOMER
    // =====================================================

    private void searchCustomer() {

        String text =
                searchField.getText().trim();

        if (text.isEmpty()) {

            loadCustomers();

            return;
        }

        try {

            int id =
                    Integer.parseInt(text);

            Customer customer =
                    customerService
                            .searchCustomerById(id);

            tableModel.setRowCount(0);

            if (customer == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer not found.",
                        "Search",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            tableModel.addRow(
                    new Object[]{
                            customer.getCustomerId(),
                            customer.getUserId(),
                            customer.getUsername(),
                            customer.getFullName(),
                            customer.getEmail(),
                            customer.getNationalId(),
                            customer.getAddress()
                    }
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADD CUSTOMER
    // =====================================================

    private void addCustomer() {

        try {

            int userId =
                    Integer.parseInt(
                            userIdField
                                    .getText()
                                    .trim()
                    );

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            String username =
                    usernameField
                            .getText()
                            .trim();

            String password =
                    passwordField
                            .getText()
                            .trim();

            String fullName =
                    fullNameField
                            .getText()
                            .trim();

            String email =
                    emailField
                            .getText()
                            .trim();

            String nationalId =
                    nationalIdField
                            .getText()
                            .trim();

            String address =
                    addressField
                            .getText()
                            .trim();

            // =============================================
            // VALIDATION
            // =============================================

            if (username.isEmpty()) {

                throw new IllegalArgumentException(
                        "Username cannot be empty."
                );
            }

            if (password.isEmpty()) {

                throw new IllegalArgumentException(
                        "Password cannot be empty."
                );
            }

            if (fullName.isEmpty()) {

                throw new IllegalArgumentException(
                        "Full Name cannot be empty."
                );
            }

            if (email.isEmpty()) {

                throw new IllegalArgumentException(
                        "Email cannot be empty."
                );
            }

            if (nationalId.isEmpty()) {

                throw new IllegalArgumentException(
                        "National ID cannot be empty."
                );
            }

            if (address.isEmpty()) {

                throw new IllegalArgumentException(
                        "Address cannot be empty."
                );
            }

            // =============================================
            // CREATE CUSTOMER
            // =============================================

            Customer customer =
                    new Customer(
                            username,
                            password,
                            userId,
                            customerId,
                            fullName,
                            email,
                            nationalId,
                            address
                    );

            // =============================================
            // ADD TO BANK
            // =============================================

            customerService.addCustomer(
                    customer
            );

            // =============================================
            // REFRESH TABLE
            // =============================================

            loadCustomers();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Customer added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID and Customer ID must be numbers.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE CUSTOMER
    // =====================================================

    private void updateCustomer() {

        try {

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            String fullName =
                    fullNameField
                            .getText()
                            .trim();

            String email =
                    emailField
                            .getText()
                            .trim();

            String nationalId =
                    nationalIdField
                            .getText()
                            .trim();

            String address =
                    addressField
                            .getText()
                            .trim();

            customerService.updateCustomer(
                    customerId,
                    fullName,
                    email,
                    nationalId,
                    address
            );

            loadCustomers();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Customer updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE CUSTOMER
    // =====================================================

    private void deleteCustomer() {

        try {

            int customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim()
                    );

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete customer "
                                    + customerId
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result != JOptionPane.YES_OPTION) {

                return;
            }

            customerService.removeCustomer(
                    customerId
            );

            loadCustomers();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Customer deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // FILL FORM
    // =====================================================

    private void fillFormFromTable() {

        int selectedRow =
                customerTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        userIdField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                1
                        )
                        .toString()
        );

        customerIdField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString()
        );

        usernameField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                2
                        )
                        .toString()
        );

        fullNameField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                3
                        )
                        .toString()
        );

        emailField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                4
                        )
                        .toString()
        );

        nationalIdField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                5
                        )
                        .toString()
        );

        addressField.setText(
                tableModel
                        .getValueAt(
                                selectedRow,
                                6
                        )
                        .toString()
        );

        // Password is not loaded from the table.
        passwordField.setText("");
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        userIdField.setText("");
        customerIdField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        fullNameField.setText("");
        emailField.setText("");
        nationalIdField.setText("");
        addressField.setText("");

        customerTable.clearSelection();
    }
}