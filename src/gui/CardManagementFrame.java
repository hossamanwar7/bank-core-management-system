package gui;

import bank.Account;
import bank.Bank;
import bank.Card;
import bank.CardService;
import bank.CardType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CardManagementFrame extends JFrame {

    private Bank bank;
    private CardService cardService;
    private EmployeeDashboard dashboard;

    private JTextField cardNumberField;
    private JComboBox<CardType> cardTypeComboBox;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JTextField accountNumberField;

    private JTable cardTable;
    private DefaultTableModel tableModel;

    private final Color PRIMARY_COLOR =
            new Color(25, 55, 109);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public CardManagementFrame(
            Bank bank,
            EmployeeDashboard dashboard) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
        this.dashboard = dashboard;

        cardService =
                new CardService(bank);

        setTitle(
                "Card Management - Bank Management System"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        loadCards();
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
                        "CARD MANAGEMENT"
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
        // Card Number
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Card Number"),
                gbc
        );

        cardNumberField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                cardNumberField,
                gbc
        );

        // =========================
        // Card Type
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Card Type"),
                gbc
        );

        cardTypeComboBox =
                new JComboBox<>(
                        CardType.values()
                );

        gbc.gridx = 3;

        formPanel.add(
                cardTypeComboBox,
                gbc
        );

        // =========================
        // Expiry Date
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Expiry Date"),
                gbc
        );

        expiryDateField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                expiryDateField,
                gbc
        );

        // =========================
        // CVV
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("CVV"),
                gbc
        );

        cvvField =
                new JPasswordField();

        gbc.gridx = 3;

        formPanel.add(
                cvvField,
                gbc
        );

        // =========================
        // Account Number
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 2;

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
                new JButton("Add Card");

        JButton updateButton =
                new JButton("Update Card");

        JButton deleteButton =
                new JButton("Delete Card");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
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
                "Card Number",
                "Card Type",
                "Expiry Date",
                "CVV",
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

        cardTable =
                new JTable(tableModel);

        cardTable.setRowHeight(35);

        cardTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        cardTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        cardTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        cardTable
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
                new JButton("View By Account");

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
                e -> addCard()
        );

        updateButton.addActionListener(
                e -> updateCard()
        );

        deleteButton.addActionListener(
                e -> deleteCard()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadCards()
        );

        viewByAccountButton.addActionListener(
                e -> viewCardsByAccount()
        );

        backButton.addActionListener(e -> {

            dispose();

            if (dashboard != null) {
                dashboard.setVisible(true);
            }
        });

        cardTable.getSelectionModel()
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
    // Add Card
    // =========================

    private void addCard() {

        try {

            String cardNumber =
                    cardNumberField
                            .getText()
                            .trim();

            CardType cardType =
                    (CardType)
                            cardTypeComboBox
                                    .getSelectedItem();

            String expiryDate =
                    expiryDateField
                            .getText()
                            .trim();

            String cvv =
                    cvvField
                            .getText()
                            .trim();

            String accountNumber =
                    accountNumberField
                            .getText()
                            .trim();

            Account account =
                    findAccountByNumber(
                            accountNumber
                    );

            if (account == null) {

                throw new IllegalArgumentException(
                        "Account not found."
                );
            }

            Card card =
                    new Card(
                            cardNumber,
                            cardType,
                            expiryDate,
                            cvv,
                            account
                    );

            cardService.addCard(card);

            JOptionPane.showMessageDialog(
                    this,
                    "Card added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCards();

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
    // Update Card
    // =========================

    private void updateCard() {

        try {

            String cardNumber =
                    cardNumberField
                            .getText()
                            .trim();

            CardType cardType =
                    (CardType)
                            cardTypeComboBox
                                    .getSelectedItem();

            String expiryDate =
                    expiryDateField
                            .getText()
                            .trim();

            String cvv =
                    cvvField
                            .getText()
                            .trim();

            String accountNumber =
                    accountNumberField
                            .getText()
                            .trim();

            Account account =
                    findAccountByNumber(
                            accountNumber
                    );

            if (account == null) {

                throw new IllegalArgumentException(
                        "Account not found."
                );
            }

            cardService.updateCard(
                    cardNumber,
                    cardType,
                    expiryDate,
                    cvv
            );

            Card card =
                    cardService.findCardByNumber(
                            cardNumber
                    );

            if (card != null) {
                card.setAccount(account);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Card updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCards();

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
    // Delete Card
    // =========================

    private void deleteCard() {

        String cardNumber =
                cardNumberField
                        .getText()
                        .trim();

        if (cardNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Card Number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this card?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            cardService.removeCard(
                    cardNumber
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Card deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCards();

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
    // View Cards By Account
    // =========================

    private void viewCardsByAccount() {

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

        boolean found = false;

        tableModel.setRowCount(0);

        for (Card card :
                bank.getCards()) {

            if (card.getAccount()
                    .getAccountNumber()
                    .equals(accountNumber)) {

                addCardToTable(card);

                found = true;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "No cards found for this account.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================
    // Load Cards
    // =========================

    private void loadCards() {

        tableModel.setRowCount(0);

        for (Card card :
                bank.getCards()) {

            addCardToTable(card);
        }
    }

    // =========================
    // Add Card To Table
    // =========================

    private void addCardToTable(Card card) {

        String maskedCardNumber =
                "**** **** **** "
                        + card.getCardNumber()
                        .substring(12);

        tableModel.addRow(
                new Object[]{
                        maskedCardNumber,
                        card.getCardType(),
                        card.getExpiryDate(),
                        "***",
                        card.getAccount()
                                .getAccountNumber()
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
                cardTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        String displayedCardNumber =
                tableModel.getValueAt(
                        selectedRow,
                        0
                ).toString();

        String actualCardNumber =
                findActualCardNumber(
                        displayedCardNumber
                );

        cardNumberField.setText(
                actualCardNumber
        );

        cardTypeComboBox.setSelectedItem(
                CardType.valueOf(
                        tableModel.getValueAt(
                                selectedRow,
                                1
                        ).toString()
                )
        );

        expiryDateField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        2
                ).toString()
        );

        cvvField.setText("");

        accountNumberField.setText(
                tableModel.getValueAt(
                        selectedRow,
                        4
                ).toString()
        );
    }

    // =========================
    // Find Actual Card Number
    // =========================

    private String findActualCardNumber(
            String maskedNumber) {

        String lastFourDigits =
                maskedNumber.substring(
                        maskedNumber.length() - 4
                );

        for (Card card :
                bank.getCards()) {

            if (card.getCardNumber()
                    .endsWith(lastFourDigits)) {

                return card.getCardNumber();
            }
        }

        return "";
    }

    // =========================
    // Clear Fields
    // =========================

    private void clearFields() {

        cardNumberField.setText("");
        expiryDateField.setText("");
        cvvField.setText("");
        accountNumberField.setText("");

        cardTypeComboBox.setSelectedIndex(0);

        cardTable.clearSelection();
    }
}