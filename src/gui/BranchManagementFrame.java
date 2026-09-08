package gui;

import bank.Bank;
import bank.Branch;
import bank.BranchService;
import bank.Employee;
import bank.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BranchManagementFrame extends JFrame {

    private Bank bank;
    private BranchService branchService;
    private EmployeeService employeeService;

    private JTextField branchIdField;
    private JTextField branchNameField;
    private JTextField branchAddressField;
    private JTextField openField;
    private JTextField closeField;
    private JTextField employeeIdField;

    private JTable branchTable;
    private DefaultTableModel tableModel;

    public BranchManagementFrame(Bank bank, EmployeeDashboard dashboard) {

        this.bank = bank;
        this.branchService = new BranchService(bank);
        this.employeeService = new EmployeeService(bank);

        setTitle("Branch Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // =========================
        // Header
        // =========================

        JLabel titleLabel = new JLabel(
                "BRANCH MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // =========================
        // Form Panel
        // =========================

        JPanel formPanel = new JPanel(
                new GridLayout(3, 4, 10, 10)
        );

        branchIdField = new JTextField();
        branchNameField = new JTextField();
        branchAddressField = new JTextField();
        openField = new JTextField();
        closeField = new JTextField();
        employeeIdField = new JTextField();

        formPanel.add(new JLabel("Branch ID"));
        formPanel.add(branchIdField);

        formPanel.add(new JLabel("Branch Name"));
        formPanel.add(branchNameField);

        formPanel.add(new JLabel("Branch Address"));
        formPanel.add(branchAddressField);

        formPanel.add(new JLabel("Opening Time"));
        formPanel.add(openField);

        formPanel.add(new JLabel("Closing Time"));
        formPanel.add(closeField);

        formPanel.add(new JLabel("Employee ID"));
        formPanel.add(employeeIdField);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // =========================
        // Table
        // =========================

        String[] columns = {
                "Branch ID",
                "Branch Name",
                "Address",
                "Open",
                "Close",
                "Employees"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        branchTable = new JTable(tableModel);

        branchTable.setRowHeight(30);
        branchTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        branchTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JScrollPane scrollPane =
                new JScrollPane(branchTable);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // Buttons
        // =========================

        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 5, 10, 10)
        );

        JButton addButton =
                new JButton("Add Branch");

        JButton updateButton =
                new JButton("Update Branch");

        JButton deleteButton =
                new JButton("Delete Branch");

        JButton clearButton =
                new JButton("Clear");

        JButton refreshButton =
                new JButton("Refresh");

        JButton addEmployeeButton =
                new JButton("Add Employee");

        JButton removeEmployeeButton =
                new JButton("Remove Employee");

        JButton viewEmployeesButton =
                new JButton("View Employees");

        JButton backButton =
                new JButton("Back to Dashboard");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(removeEmployeeButton);
        buttonPanel.add(viewEmployeesButton);
        buttonPanel.add(backButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Add Branch
        // =========================

        addButton.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(
                                branchIdField.getText().trim()
                        );

                String name =
                        branchNameField.getText().trim();

                String address =
                        branchAddressField.getText().trim();

                String open =
                        openField.getText().trim();

                String close =
                        closeField.getText().trim();

                Branch branch =
                        new Branch(
                                id,
                                name,
                                address,
                                open,
                                close
                        );

                branchService.addBranch(branch);

                JOptionPane.showMessageDialog(
                        this,
                        "Branch added successfully."
                );

                clearFields();
                refreshTable();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // Update Branch
        // =========================

        updateButton.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(
                                branchIdField.getText().trim()
                        );

                String name =
                        branchNameField.getText().trim();

                String address =
                        branchAddressField.getText().trim();

                String open =
                        openField.getText().trim();

                String close =
                        closeField.getText().trim();

                branchService.updateBranch(
                        id,
                        name,
                        address,
                        open,
                        close
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Branch updated successfully."
                );

                clearFields();
                refreshTable();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // Delete Branch
        // =========================

        deleteButton.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(
                                branchIdField.getText().trim()
                        );

                int result =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this branch?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (result == JOptionPane.YES_OPTION) {

                    branchService.removeBranchById(id);

                    JOptionPane.showMessageDialog(
                            this,
                            "Branch deleted successfully."
                    );

                    clearFields();
                    refreshTable();
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // Add Employee To Branch
        // =========================

        addEmployeeButton.addActionListener(e -> {

            try {

                int branchId =
                        Integer.parseInt(
                                branchIdField.getText().trim()
                        );

                int employeeId =
                        Integer.parseInt(
                                employeeIdField.getText().trim()
                        );

                Employee employee =
                        employeeService.searchEmployeeById(
                                employeeId
                        );

                if (employee == null) {

                    throw new IllegalArgumentException(
                            "Employee not found."
                    );
                }

                branchService.addEmployeeToBranch(
                        branchId,
                        employee
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Employee added to branch successfully."
                );

                clearFields();
                refreshTable();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // Remove Employee From Branch
        // =========================

        removeEmployeeButton.addActionListener(e -> {

            try {

                int branchId =
                        Integer.parseInt(
                                branchIdField.getText().trim()
                        );

                int employeeId =
                        Integer.parseInt(
                                employeeIdField.getText().trim()
                        );

                branchService.removeEmployeeFromBranch(
                        branchId,
                        employeeId
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Employee removed from branch successfully."
                );

                clearFields();
                refreshTable();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // View Employees
        // =========================

        viewEmployeesButton.addActionListener(e -> {

            try {

                int branchId =
                        Integer.parseInt(
                                branchIdField.getText().trim()
                        );

                Branch branch =
                        branchService.searchBranchById(
                                branchId
                        );

                if (branch == null) {

                    throw new IllegalArgumentException(
                            "Branch not found."
                    );
                }

                if (branch.getEmployees().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No employees in this branch."
                    );

                    return;
                }

                StringBuilder employeesText =
                        new StringBuilder();

                employeesText.append(
                        "Employees in "
                ).append(
                        branch.getBranchName()
                ).append(
                        ":\n\n"
                );

                for (Employee employee :
                        branch.getEmployees()) {

                    employeesText
                            .append("Employee ID: ")
                            .append(employee.getEmpId())
                            .append("\n");

                    employeesText
                            .append("Name: ")
                            .append(employee.getFullname())
                            .append("\n");

                    employeesText
                            .append("Position: ")
                            .append(employee.getEmpPosition())
                            .append("\n");

                    employeesText.append(
                            "-------------------------\n"
                    );
                }

                JTextArea textArea =
                        new JTextArea(
                                employeesText.toString()
                        );

                textArea.setEditable(false);
                textArea.setFont(
                        new Font("Arial", Font.PLAIN, 14)
                );

                JScrollPane employeeScrollPane =
                        new JScrollPane(textArea);

                employeeScrollPane.setPreferredSize(
                        new Dimension(500, 350)
                );

                JOptionPane.showMessageDialog(
                        this,
                        employeeScrollPane,
                        "Branch Employees",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // Clear
        // =========================

        clearButton.addActionListener(
                e -> clearFields()
        );

        // =========================
        // Refresh
        // =========================

        refreshButton.addActionListener(
                e -> refreshTable()
        );

        // =========================
        // Table Selection
        // =========================

        branchTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                branchTable.getSelectedRow();

                        if (row >= 0) {

                            branchIdField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            branchNameField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            branchAddressField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            openField.setText(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );

                            closeField.setText(
                                    tableModel
                                            .getValueAt(row, 4)
                                            .toString()
                            );

                            employeeIdField.setText("");
                        }
                    }
                });

        // =========================
        // Back To Dashboard
        // =========================

        backButton.addActionListener(e -> {

            dispose();

            if (dashboard != null) {
                dashboard.setVisible(true);
            }
        });

        // =========================
        // Initial Data
        // =========================

        refreshTable();

        setContentPane(mainPanel);
        setVisible(true);
    }

    // =========================
    // Refresh Table
    // =========================

    private void refreshTable() {

        tableModel.setRowCount(0);

        for (Branch branch :
                bank.getBranches()) {

            tableModel.addRow(
                    new Object[]{
                            branch.getBranchId(),
                            branch.getBranchName(),
                            branch.getBranchAddress(),
                            branch.getOpen(),
                            branch.getClose(),
                            branch.getEmployees().size()
                    }
            );
        }
    }

    // =========================
    // Clear Fields
    // =========================

    private void clearFields() {

        branchIdField.setText("");
        branchNameField.setText("");
        branchAddressField.setText("");
        openField.setText("");
        closeField.setText("");
        employeeIdField.setText("");

        branchTable.clearSelection();
    }
}