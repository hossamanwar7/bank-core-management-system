package gui;

import bank.Bank;
import bank.Customer;
import bank.Transaction;
import bank.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerTransactionsFrame extends JFrame {

    private Bank bank;
    private Customer customer;
    private TransactionService transactionService;

    private JTable transactionTable;
    private DefaultTableModel tableModel;

    public CustomerTransactionsFrame(
            Bank bank,
            Customer customer,
            CustomerDashboard dashboard) {

        this.bank = bank;
        this.customer = customer;

        this.transactionService =
                new TransactionService(bank);

        setTitle("My Transactions");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =========================
        // Main Panel
        // =========================

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        // =========================
        // Header
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "MY TRANSACTIONS",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Customer Name
        // =========================

        JLabel customerLabel =
                new JLabel(
                        "Customer: "
                                + customer.getFullName(),
                        SwingConstants.CENTER
                );

        customerLabel.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        // =========================
        // Table
        // =========================

        String[] columns = {
                "Transaction ID",
                "Account Number",
                "Amount",
                "Transaction Type",
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

        transactionTable.setRowHeight(30);

        transactionTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        transactionTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(transactionTable);

        // =========================
        // Center Panel
        // =========================

        JPanel centerPanel =
                new JPanel(new BorderLayout(10, 10));

        centerPanel.add(
                customerLabel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Buttons
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        JButton refreshButton =
                new JButton("Refresh");

        JButton backButton =
                new JButton("Back to Dashboard");

        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Refresh
        // =========================

        refreshButton.addActionListener(e ->
                refreshTable()
        );

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

        for (Transaction transaction :
                bank.getTransactions()) {

            if (transaction.getAccount()
                    .getCustomer() == customer) {

                tableModel.addRow(
                        new Object[]{
                                transaction.getTransactionId(),
                                transaction.getAccount()
                                        .getAccountNumber(),
                                transaction.getAmount(),
                                transaction
                                        .getTransActionType(),
                                transaction
                                        .getTransactionDate()
                        }
                );
            }
        }

        if (tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "You don't have any transactions yet.",
                    "My Transactions",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}