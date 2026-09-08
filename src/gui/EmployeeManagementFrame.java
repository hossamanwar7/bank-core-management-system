package gui;

import bank.Bank;
import bank.Employee;
import bank.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeManagementFrame extends JFrame {

    private Bank bank;
    private EmployeeService employeeService;
    private EmployeeDashboard dashboard;

    private JTextField empIdField;
    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullnameField;
    private JTextField positionField;
    private JTextField salaryField;

    private JTable employeeTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public EmployeeManagementFrame(
            Bank bank,
            EmployeeDashboard dashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
        this.dashboard = dashboard;

        employeeService =
                new EmployeeService(bank);

        setTitle(
                "Employee Management - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadEmployees();
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
                        "EMPLOYEE MANAGEMENT"
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
        // Form Panel
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

        // Employee ID

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Employee ID"),
                gbc
        );

        empIdField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                empIdField,
                gbc
        );

        // User ID

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("User ID"),
                gbc
        );

        userIdField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                userIdField,
                gbc
        );

        // Username

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Username"),
                gbc
        );

        usernameField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                usernameField,
                gbc
        );

        // Password

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Password"),
                gbc
        );

        passwordField =
                new JPasswordField();

        gbc.gridx = 3;

        formPanel.add(
                passwordField,
                gbc
        );

        // Full Name

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Full Name"),
                gbc
        );

        fullnameField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                fullnameField,
                gbc
        );

        // Position

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Position"),
                gbc
        );

        positionField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                positionField,
                gbc
        );

        // Salary

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Salary"),
                gbc
        );

        salaryField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                salaryField,
                gbc
        );

        // =========================
        // Form Button Panel
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
                new JButton("Add Employee");

        JButton updateButton =
                new JButton("Update Employee");

        JButton deleteButton =
                new JButton("Delete Employee");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
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
                "Employee ID",
                "User ID",
                "Username",
                "Full Name",
                "Position",
                "Salary"
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

        employeeTable =
                new JTable(tableModel);

        employeeTable.setRowHeight(35);

        employeeTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        employeeTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        employeeTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        employeeTable
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

        bottomPanel.add(refreshButton);
        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Actions
        // =========================

        addButton.addActionListener(
                e -> addEmployee()
        );

        updateButton.addActionListener(
                e -> updateEmployee()
        );

        deleteButton.addActionListener(
                e -> deleteEmployee()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadEmployees()
        );

        backButton.addActionListener(e -> {

            dispose();

            if (dashboard != null) {
                dashboard.setVisible(true);
            }
        });

        employeeTable.getSelectionModel()
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
    // Add Employee
    // =========================

    private void addEmployee() {

        try {

            int empId =
                    Integer.parseInt(
                            empIdField.getText().trim()
                    );

            int userId =
                    Integer.parseInt(
                            userIdField.getText().trim()
                    );

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            String fullname =
                    fullnameField.getText().trim();

            String position =
                    positionField.getText().trim();

            double salary =
                    Double.parseDouble(
                            salaryField.getText().trim()
                    );

            Employee employee =
                    new Employee(
                            username,
                            password,
                            userId,
                            empId,
                            fullname,
                            position,
                            salary
                    );

            employeeService.addEmployee(
                    employee
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Employee added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadEmployees();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID, User ID and Salary must be valid numbers.",
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
    // Update Employee
    // =========================

    private void updateEmployee() {

        try {

            int empId =
                    Integer.parseInt(
                            empIdField.getText().trim()
                    );

            String fullname =
                    fullnameField.getText().trim();

            String position =
                    positionField.getText().trim();

            double salary =
                    Double.parseDouble(
                            salaryField.getText().trim()
                    );

            employeeService.updateEmployee(
                    empId,
                    fullname,
                    position,
                    salary
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Employee updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadEmployees();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID and Salary must be valid numbers.",
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
    // Delete Employee
    // =========================

    private void deleteEmployee() {

        try {

            int empId =
                    Integer.parseInt(
                            empIdField.getText().trim()
                    );

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete Employee "
                                    + empId
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            employeeService.removeEmployee(
                    empId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Employee deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadEmployees();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Employee ID.",
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
    // Load Employees
    // =========================

    private void loadEmployees() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        for (Employee employee :
                bank.getEmployees()) {

            tableModel.addRow(
                    new Object[]{
                            employee.getEmpId(),
                            employee.getUserId(),
                            employee.getUsername(),
                            employee.getFullname(),
                            employee.getEmpPosition(),
                            String.format(
                                    "%.2f",
                                    employee.getSalary()
                            )
                    }
            );
        }
    }

    // =========================
    // Fill Fields
    // =========================

    private void fillFieldsFromTable() {

        int selectedRow =
                employeeTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        empIdField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        0
                ).toString()
        );

        userIdField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString()
        );

        usernameField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        2
                ).toString()
        );

        fullnameField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        3
                ).toString()
        );

        positionField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        4
                ).toString()
        );

        salaryField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        5
                ).toString()
        );

        // Password is intentionally not loaded
        passwordField.setText("");
    }

    // =========================
    // Clear Fields
    // =========================

    private void clearFields() {

        empIdField.setText("");
        userIdField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        fullnameField.setText("");
        positionField.setText("");
        salaryField.setText("");

        employeeTable.clearSelection();
    }
}