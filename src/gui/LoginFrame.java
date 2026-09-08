
        package gui;

import bank.Bank;
import bank.Customer;
import bank.Employee;
import bank.User;
import bank.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private Bank bank;
    private UserService userService;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JRadioButton employeeRadio;
    private JRadioButton customerRadio;

    // =========================
    // Colors
    // =========================

    private final Color BACKGROUND_COLOR =
            new Color(15, 23, 42);

    private final Color CARD_COLOR =
            new Color(30, 41, 59);

    private final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private final Color HOVER_COLOR =
            new Color(59, 130, 246);

    private final Color TEXT_COLOR =
            new Color(248, 250, 252);

    private final Color SECONDARY_TEXT =
            new Color(148, 163, 184);

    private final Color INPUT_COLOR =
            new Color(51, 65, 85);

    public LoginFrame(
            Bank bank,
            UserService userService) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        if (userService == null) {
            throw new IllegalArgumentException(
                    "UserService cannot be null."
            );
        }

        this.bank = bank;
        this.userService = userService;

        setTitle(
                "Bank Management System - Login"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();

        setVisible(true);
    }

    // =====================================================
    // CREATE GUI
    // =====================================================

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new GridBagLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );

        // =================================================
        // Login Card
        // =================================================

        JPanel loginPanel =
                new JPanel();

        loginPanel.setPreferredSize(
                new Dimension(
                        480,
                        560
                )
        );

        loginPanel.setBackground(
                CARD_COLOR
        );

        loginPanel.setLayout(
                new BoxLayout(
                        loginPanel,
                        BoxLayout.Y_AXIS
                )
        );

        loginPanel.setBorder(
                new EmptyBorder(
                        45,
                        50,
                        45,
                        50
                )
        );

        // =================================================
        // Bank Logo / Icon
        // =================================================

        JLabel bankIcon =
                new JLabel("BANK");

        bankIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        bankIcon.setForeground(
                HOVER_COLOR
        );

        bankIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(bankIcon);

        loginPanel.add(
                Box.createVerticalStrut(10)
        );

        // =================================================
        // Title
        // =================================================

        JLabel titleLabel =
                new JLabel(
                        "MY BANK"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        titleLabel.setForeground(
                TEXT_COLOR
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(titleLabel);

        loginPanel.add(
                Box.createVerticalStrut(5)
        );

        // =================================================
        // Subtitle
        // =================================================

        JLabel subtitleLabel =
                new JLabel(
                        "Bank Management System"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitleLabel.setForeground(
                SECONDARY_TEXT
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(subtitleLabel);

        loginPanel.add(
                Box.createVerticalStrut(40)
        );

        // =================================================
        // Username Label
        // =================================================

        JLabel usernameLabel =
                createLabel(
                        "Username"
                );

        loginPanel.add(usernameLabel);

        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        // =================================================
        // Username Field
        // =================================================

        usernameField =
                new JTextField();

        styleTextField(
                usernameField
        );

        loginPanel.add(usernameField);

        loginPanel.add(
                Box.createVerticalStrut(20)
        );

        // =================================================
        // Password Label
        // =================================================

        JLabel passwordLabel =
                createLabel(
                        "Password"
                );

        loginPanel.add(passwordLabel);

        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        // =================================================
        // Password Field
        // =================================================

        passwordField =
                new JPasswordField();

        styleTextField(
                passwordField
        );

        loginPanel.add(passwordField);

        loginPanel.add(
                Box.createVerticalStrut(25)
        );

        // =================================================
        // Login As
        // =================================================

        JLabel loginAsLabel =
                createLabel(
                        "Login As"
                );

        loginPanel.add(loginAsLabel);

        loginPanel.add(
                Box.createVerticalStrut(10)
        );

        // =================================================
        // Radio Buttons
        // =================================================

        employeeRadio =
                createRadioButton(
                        "Employee"
                );

        customerRadio =
                createRadioButton(
                        "Customer"
                );

        employeeRadio.setSelected(true);

        ButtonGroup group =
                new ButtonGroup();

        group.add(employeeRadio);
        group.add(customerRadio);

        JPanel radioPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                0
                        )
                );

        radioPanel.setOpaque(false);

        radioPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        radioPanel.add(
                employeeRadio
        );

        radioPanel.add(
                customerRadio
        );

        loginPanel.add(radioPanel);

        loginPanel.add(
                Box.createVerticalStrut(35)
        );

        // =================================================
        // Login Button
        // =================================================

        RoundedButton loginButton =
                new RoundedButton(
                        "LOGIN"
                );

        loginButton.setPreferredSize(
                new Dimension(
                        380,
                        55
                )
        );

        loginButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(loginButton);

        // =================================================
        // Login Action
        // =================================================

        loginButton.addActionListener(
                e -> login()
        );

        passwordField.addActionListener(
                e -> login()
        );

        // =================================================
        // Add Card
        // =================================================

        mainPanel.add(
                loginPanel
        );

        setContentPane(
                mainPanel
        );
    }

    // =====================================================
    // CREATE LABEL
    // =====================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    // =====================================================
    // STYLE TEXT FIELD
    // =====================================================

    private void styleTextField(
            JTextField field) {

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        field.setForeground(
                TEXT_COLOR
        );

        field.setBackground(
                INPUT_COLOR
        );

        field.setCaretColor(
                Color.WHITE
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        71,
                                        85,
                                        105
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        field.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );
    }

    // =====================================================
    // CREATE RADIO BUTTON
    // =====================================================

    private JRadioButton createRadioButton(
            String text) {

        JRadioButton radio =
                new JRadioButton(
                        text
                );

        radio.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        radio.setForeground(
                SECONDARY_TEXT
        );

        radio.setOpaque(false);

        radio.setFocusPainted(false);

        return radio;
    }

    // =====================================================
    // LOGIN
    // =====================================================

    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            User user =
                    userService.login(
                            username,
                            password
                    );

            // =============================================
            // EMPLOYEE LOGIN
            // =============================================

            if (employeeRadio.isSelected()) {

                if (user instanceof Employee) {

                    Employee employee =
                            (Employee) user;

                    EmployeeDashboard dashboard =
                            new EmployeeDashboard(
                                    bank
                            );

                    dashboard.setVisible(
                            true
                    );

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "This account is not an employee account.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

            // =============================================
            // CUSTOMER LOGIN
            // =============================================

            else if (customerRadio.isSelected()) {

                if (user instanceof Customer) {

                    Customer customer =
                            (Customer) user;

                    CustomerDashboard dashboard =
                            new CustomerDashboard(
                                    bank,
                                    customer
                            );

                    dashboard.setVisible(
                            true
                    );

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "This account is not a customer account.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (
                IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ROUNDED BUTTON
    // =====================================================

    private class RoundedButton
            extends JButton {

        private Color normalColor =
                PRIMARY_COLOR;

        private Color hoverColor =
                HOVER_COLOR;

        private boolean hovered =
                false;

        public RoundedButton(
                String text) {

            super(text);

            setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            17
                    )
            );

            setForeground(
                    Color.WHITE
            );

            setFocusPainted(
                    false
            );

            setBorderPainted(
                    false
            );

            setContentAreaFilled(
                    false
            );

            setOpaque(
                    false
            );

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
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

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

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
                            90
                    )
            );

            g2.fillRoundRect(
                    3,
                    5,
                    getWidth() - 6,
                    getHeight() - 7,
                    18,
                    18
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
                    getWidth() - 4,
                    getHeight() - 4,
                    18,
                    18
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}

