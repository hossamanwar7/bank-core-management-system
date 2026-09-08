package gui;

import bank.Bank;
import bank.Customer;
import bank.Loan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerLoansFrame extends JFrame {

    private Bank bank;
    private Customer customer;
    private CustomerDashboard dashboard;

    private JTable loansTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public CustomerLoansFrame(
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
                "My Loans - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();
        loadCustomerLoans();
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
                new JLabel("MY LOANS");

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
        // Table
        // =========================

        String[] columns = {
                "Loan ID",
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

        loansTable =
                new JTable(tableModel);

        loansTable.setRowHeight(35);

        loansTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        loansTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        loansTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(loansTable);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        20,
                        50
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // Bottom Buttons
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
                e -> loadCustomerLoans()
        );

        backButton.addActionListener(e -> {

            dispose();

            dashboard.setVisible(true);
        });

        setContentPane(mainPanel);
    }

    // =========================
    // Load Customer Loans
    // =========================

    private void loadCustomerLoans() {

        tableModel.setRowCount(0);

        for (Loan loan :
                bank.getLoans()) {

            if (loan.getCustomer() == customer) {

                tableModel.addRow(
                        new Object[]{
                                loan.getLoanId(),
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
        }

        if (tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "You don't have any loans.",
                    "My Loans",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}