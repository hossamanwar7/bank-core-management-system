
        package gui;

import bank.Bank;
import bank.Customer;
import bank.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerDashboard extends JFrame {

    private Bank bank;
    private Customer customer;

    public CustomerDashboard(
            Bank bank,
            Customer customer) {

        this.bank = bank;
        this.customer = customer;

        setTitle("Customer Dashboard");
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
                        40,
                        100,
                        40,
                        100
                )
        );

        // =========================
        // Title
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "CUSTOMER DASHBOARD",
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
        // Welcome Message
        // =========================

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + customer.getFullName(),
                        SwingConstants.CENTER
                );

        welcomeLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        22
                )
        );

        welcomeLabel.setForeground(
                new Color(203, 213, 225)
        );

        // =========================
        // Buttons Panel
        // =========================

        JPanel buttonsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                30,
                                30
                        )
                );

        buttonsPanel.setOpaque(false);

        // =========================
        // Buttons
        // =========================

        RoundedButton accountButton =
                new RoundedButton("My Account");

        RoundedButton cardsButton =
                new RoundedButton("My Cards");

        RoundedButton loansButton =
                new RoundedButton("My Loans");

        RoundedButton transactionsButton =
                new RoundedButton(
                        "My Transactions"
                );

        // =========================
        // Add Buttons
        // =========================

        buttonsPanel.add(accountButton);
        buttonsPanel.add(cardsButton);
        buttonsPanel.add(loansButton);
        buttonsPanel.add(transactionsButton);

        // =========================
        // Center Panel
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                40
                        )
                );

        centerPanel.setOpaque(false);

        centerPanel.add(
                welcomeLabel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                buttonsPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Logout Button
        // =========================

        RoundedButton logoutButton =
                new RoundedButton("Logout");

        logoutButton.setPreferredSize(
                new Dimension(
                        180,
                        55
                )
        );

        logoutButton.setNormalColor(
                new Color(185, 55, 55)
        );

        logoutButton.setHoverColor(
                new Color(220, 70, 70)
        );

        JPanel logoutPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        logoutPanel.setOpaque(false);

        logoutPanel.add(logoutButton);

        mainPanel.add(
                logoutPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // My Account
        // =========================

        accountButton.addActionListener(e -> {

            CustomerAccountFrame frame =
                    new CustomerAccountFrame(
                            bank,
                            customer,
                            this
                    );

            frame.setVisible(true);
            setVisible(false);
        });

        // =========================
        // My Cards
        // =========================

        cardsButton.addActionListener(e -> {

            CustomerCardsFrame frame =
                    new CustomerCardsFrame(
                            bank,
                            customer,
                            this
                    );

            frame.setVisible(true);
            setVisible(false);
        });

        // =========================
        // My Loans
        // =========================

        loansButton.addActionListener(e -> {

            CustomerLoansFrame frame =
                    new CustomerLoansFrame(
                            bank,
                            customer,
                            this
                    );

            frame.setVisible(true);
            setVisible(false);
        });

        // =========================
        // My Transactions
        // =========================

        transactionsButton.addActionListener(e -> {

            CustomerTransactionsFrame frame =
                    new CustomerTransactionsFrame(
                            bank,
                            customer,
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
        // Set Content
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
                            20
                    )
            );

            setForeground(
                    Color.WHITE
            );

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
                            110
                    )
            );

            // =========================
            // Hover Effect
            // =========================

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

        // =========================
        // Normal Color
        // =========================

        public void setNormalColor(
                Color color) {

            this.normalColor = color;

            repaint();
        }

        // =========================
        // Hover Color
        // =========================

        public void setHoverColor(
                Color color) {

            this.hoverColor = color;

            repaint();
        }

        // =========================
        // Paint Button
        // =========================

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // =========================
            // Shadow
            // =========================

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

            // =========================
            // Button
            // =========================

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

