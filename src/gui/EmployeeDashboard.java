
        package gui;

import bank.Bank;
import bank.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeDashboard extends JFrame {

    private Bank bank;

    public EmployeeDashboard(Bank bank) {

        this.bank = bank;

        setTitle("Employee Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // =========================
        // Main Panel
        // =========================

        JPanel mainPanel =
                new JPanel(new BorderLayout(30, 30));

        mainPanel.setBackground(
                new Color(15, 23, 42)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        70,
                        35,
                        70
                )
        );

        // =========================
        // Header
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "EMPLOYEE DASHBOARD",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        38
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Buttons Panel
        // =========================

        JPanel buttonsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                25,
                                25
                        )
                );

        buttonsPanel.setOpaque(false);

        // =========================
        // Buttons
        // =========================

        RoundedButton customersButton =
                new RoundedButton("Customers");

        RoundedButton employeesButton =
                new RoundedButton("Employees");

        RoundedButton accountsButton =
                new RoundedButton("Accounts");

        RoundedButton cardsButton =
                new RoundedButton("Cards");

        RoundedButton loansButton =
                new RoundedButton("Loans");

        RoundedButton transactionsButton =
                new RoundedButton("Transactions");

        RoundedButton branchesButton =
                new RoundedButton("Branches");

        RoundedButton logoutButton =
                new RoundedButton("Logout");

        // =========================
        // Logout Color
        // =========================

        logoutButton.setNormalColor(
                new Color(185, 55, 55)
        );

        logoutButton.setHoverColor(
                new Color(220, 70, 70)
        );

        // =========================
        // Add Buttons
        // =========================

        buttonsPanel.add(customersButton);
        buttonsPanel.add(employeesButton);

        buttonsPanel.add(accountsButton);
        buttonsPanel.add(cardsButton);

        buttonsPanel.add(loansButton);
        buttonsPanel.add(transactionsButton);

        buttonsPanel.add(branchesButton);
        buttonsPanel.add(logoutButton);

        mainPanel.add(
                buttonsPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Customers
        // =========================

        customersButton.addActionListener(e -> {

            CustomerManagementFrame frame =
                    new CustomerManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Employees
        // =========================

        employeesButton.addActionListener(e -> {

            EmployeeManagementFrame frame =
                    new EmployeeManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Accounts
        // =========================

        accountsButton.addActionListener(e -> {

            AccountManagementFrame frame =
                    new AccountManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Cards
        // =========================

        cardsButton.addActionListener(e -> {

            CardManagementFrame frame =
                    new CardManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Loans
        // =========================

        loansButton.addActionListener(e -> {

            LoanManagementFrame frame =
                    new LoanManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Transactions
        // =========================

        transactionsButton.addActionListener(e -> {

            TransactionManagementFrame frame =
                    new TransactionManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Branches
        // =========================

        branchesButton.addActionListener(e -> {

            BranchManagementFrame frame =
                    new BranchManagementFrame(
                            bank,
                            this
                    );

            frame.setVisible(true);

            setVisible(false);
        });

        // =========================
        // Logout
        // =========================

        logoutButton.addActionListener(e -> {

            dispose();

            UserService userService =
                    new UserService(bank);

            new LoginFrame(
                    bank,
                    userService
            );
        });

        // =========================
        // Show
        // =========================

        setContentPane(mainPanel);

        setVisible(true);
    }

    // =====================================================
    // Rounded Button
    // =====================================================

    private static class RoundedButton
            extends JButton {

        private Color normalColor =
                new Color(37, 99, 235);

        private Color hoverColor =
                new Color(59, 130, 246);

        private boolean hovered = false;

        public RoundedButton(String text) {

            super(text);

            setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            19
                    )
            );

            setForeground(Color.WHITE);

            setFocusPainted(false);

            setBorderPainted(false);

            setContentAreaFilled(false);

            setOpaque(false);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            setPreferredSize(
                    new Dimension(
                            250,
                            95
                    )
            );

            addMouseListener(
                    new MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                MouseEvent e) {

                            hovered = true;

                            repaint();
                        }

                        @Override
                        public void mouseExited(
                                MouseEvent e) {

                            hovered = false;

                            repaint();
                        }
                    }
            );
        }

        public void setNormalColor(
                Color color) {

            normalColor = color;

            repaint();
        }

        public void setHoverColor(
                Color color) {

            hoverColor = color;

            repaint();
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // Shadow

            g2.setColor(
                    new Color(
                            0,
                            0,
                            0,
                            80
                    )
            );

            g2.fillRoundRect(
                    5,
                    7,
                    getWidth() - 10,
                    getHeight() - 12,
                    30,
                    30
            );

            // Button

            if (hovered) {

                g2.setColor(
                        hoverColor
                );

            } else {

                g2.setColor(
                        normalColor
                );
            }

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth() - 10,
                    getHeight() - 10,
                    30,
                    30
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}

