
        package gui;

import bank.Bank;
import bank.Card;
import bank.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerCardsFrame extends JFrame {

    private Bank bank;
    private Customer customer;
    private CustomerDashboard dashboard;

    private JTable cardsTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public CustomerCardsFrame(
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
                "My Cards - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();
        loadCustomerCards();
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
                new JLabel("MY CARDS");

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
                "Card Number",
                "Card Type",
                "Expiry Date",
                "Account Number"
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

        cardsTable =
                new JTable(tableModel);

        cardsTable.setRowHeight(35);

        cardsTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        cardsTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        cardsTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(cardsTable);

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
        // Actions
        // =========================

        refreshButton.addActionListener(
                e -> loadCustomerCards()
        );

        backButton.addActionListener(e -> {

            dispose();

            dashboard.setVisible(true);
        });

        setContentPane(mainPanel);
    }

    // =========================
    // Load Customer Cards
    // =========================

    private void loadCustomerCards() {

        tableModel.setRowCount(0);

        for (Card card :
                bank.getCards()) {

            if (card.getAccount() != null &&
                    card.getAccount()
                            .getCustomer() == customer) {

                String cardNumber =
                        card.getCardNumber();

                // Hide most of the card number
                String maskedCardNumber =
                        "**** **** **** " +
                                cardNumber.substring(
                                        12
                                );

                tableModel.addRow(
                        new Object[]{
                                maskedCardNumber,
                                card.getCardType(),
                                card.getExpiryDate(),
                                card.getAccount()
                                        .getAccountNumber()
                        }
                );
            }
        }

        if (tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "You don't have any cards.",
                    "My Cards",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}

