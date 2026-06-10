package Login_Startup;

import static Color_Palette.ColorPalette.*;
import Database.UserManagementSQL;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgotPasswordFrame extends JFrame {

    private JPanel pnlContainer;
    private JLabel lblHeader, lblDesc, lblUser, lblEmail, lblNewPass, lblConfirmPass;
    private JTextField txtUsername, txtEmail;
    private JPasswordField txtNewPass, txtConfirmPass;
    private JButton btnReset, btnCancel;

    public ForgotPasswordFrame() {
        setTitle("Account Security Recovery");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 620);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null); // Center window on user desktop

        pnlContainer = new JPanel();
        pnlContainer.setLayout(null);
        pnlContainer.setBackground(Color.WHITE);
        pnlContainer.setBounds(0, 0, 550, 620);
        add(pnlContainer);

        lblHeader = new JLabel("Reset Your Password");
        lblHeader.setFont(new Font("Calibri", Font.BOLD, 28));
        lblHeader.setForeground(darkBlue);
        lblHeader.setBounds(40, 25, 450, 35);
        pnlContainer.add(lblHeader);

        lblDesc = new JLabel("Verify your matching corporate email to provision updates.");
        lblDesc.setFont(new Font("Calibri", Font.PLAIN, 15));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setBounds(40, 65, 450, 20);
        pnlContainer.add(lblDesc);

        // --- Username Form ---
        lblUser = new JLabel("Account Username:");
        lblUser.setFont(new Font("Calibri", Font.BOLD, 16));
        lblUser.setBounds(40, 110, 200, 25);
        pnlContainer.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(40, 140, 450, 38);
        txtUsername.setBackground(LightGray);
        txtUsername.setBorder(createInputBorder());
        pnlContainer.add(txtUsername);

        // --- Email Form ---
        lblEmail = new JLabel("Registered Corporate Email:");
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 16));
        lblEmail.setBounds(40, 200, 250, 25);
        pnlContainer.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(40, 230, 450, 38);
        txtEmail.setBackground(LightGray);
        txtEmail.setBorder(createInputBorder());
        pnlContainer.add(txtEmail);

        // --- New Password ---
        lblNewPass = new JLabel("New Security Password:");
        lblNewPass.setFont(new Font("Calibri", Font.BOLD, 16));
        lblNewPass.setBounds(40, 290, 250, 25);
        pnlContainer.add(lblNewPass);

        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(40, 320, 450, 38);
        txtNewPass.setBackground(LightGray);
        txtNewPass.setBorder(createInputBorder());
        pnlContainer.add(txtNewPass);

        // --- Confirm Password ---
        lblConfirmPass = new JLabel("Confirm New Password:");
        lblConfirmPass.setFont(new Font("Calibri", Font.BOLD, 16));
        lblConfirmPass.setBounds(40, 380, 250, 25);
        pnlContainer.add(lblConfirmPass);

        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(40, 410, 450, 38);
        txtConfirmPass.setBackground(LightGray);
        txtConfirmPass.setBorder(createInputBorder());
        pnlContainer.add(txtConfirmPass);

        // --- Buttons Section ---
        btnReset = new JButton("RESET PASSWORD");
        btnReset.setBounds(40, 485, 450, 45);
        btnReset.setBackground(darkBlue);
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Calibri", Font.BOLD, 16));
        btnReset.setFocusPainted(false);
        pnlContainer.add(btnReset);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(40, 540, 450, 30);
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnCancel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnCancel.setFocusPainted(false);
        pnlContainer.add(btnCancel);

        // Button Action Events
        btnReset.addActionListener(e -> executePasswordReset());
        btnCancel.addActionListener(e -> dispose());

        // Hover UI Effects
        btnReset.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btnReset.setBackground(mediumBlue); }
            @Override public void mouseExited(MouseEvent e) { btnReset.setBackground(darkBlue); }
        });
    }

    private Border createInputBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(darkBlue, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
    }

    private void executePasswordReset() {
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String newPass = new String(txtNewPass.getPassword()).trim();
        String confirmPass = new String(txtConfirmPass.getPassword()).trim();

        // 1. Check for empty fields
        if (username.isEmpty() || email.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All input parameters are required to authenticate updates!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Validate password match strings
        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match! Please verify structural entries.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Process backend sequence check
        if (UserManagementSQL.resetPassword(username, email, newPass)) {
            JOptionPane.showMessageDialog(this, "Password security updated successfully! Use new credentials to log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Authentication failed! Username and Email do not match or account is inactive.", "Reset Denied", JOptionPane.ERROR_MESSAGE);
        }
    }
}