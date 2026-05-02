package Login_Startup;

import static Color_Palette.ColorPalette.*;
import Left_Sidebar.Admin_SideBarFrame;
import Left_Sidebar.Doctor_SideBarFrame;
import Left_Sidebar.Nurse_SideBarFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.*;

public class Login extends JFrame implements ActionListener{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblForgot;
    
    public Login() {
        setTitle("Hospital Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // For screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 1000));
        setLayout(null);
        setLocationRelativeTo(null);
        
        ImageIcon imgBg = new ImageIcon(getClass().getResource("/resources/OOP - LOGIN BG.jpg"));
        Image BgImage = imgBg.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        JLabel lblbackground = new JLabel(new ImageIcon(BgImage));
        lblbackground.setBounds(0, 0, 1920, 1080);
        add(lblbackground);
        
        JPanel pnlLogin = new JPanel();
        pnlLogin.setLayout(null);
        pnlLogin.setBounds(700, 340, 520, 400);
        pnlLogin.setBackground(new Color(245, 245, 245, 235));
        pnlLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderLBLUE, 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        pnlLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderLBLUE, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        lblbackground.add(pnlLogin);
        
        JLabel lblTitle = new JLabel("eTriage | Hospital Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        lblTitle.setBounds(20, 30, 480, 40);
        lblTitle.setForeground(new Color(57, 90, 127));
        pnlLogin.add(lblTitle);
        
        JLabel lblUser = new JLabel("Username: ");
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUser.setBounds(80, 130, 120, 30);
        pnlLogin.add(lblUser);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(200, 130, 240, 35);
        txtUsername.setBackground(LightGray);
        txtUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
        txtUsername.setBorder(createInputBorder());
        pnlLogin.add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPass.setBounds(80, 190, 120, 30);
        pnlLogin.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 190, 240, 35);
        txtPassword.setBackground(LightGray);
        txtPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
        txtPassword.setBorder(createInputBorder());
        pnlLogin.add(txtPassword);

        lblForgot = new JLabel("Forgot Password?");
        lblForgot.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblForgot.setForeground(Color.BLUE);
        lblForgot.setBounds(260, 240, 200, 30);
        lblForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlLogin.add(lblForgot);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(240, 290, 150, 45);
        btnLogin.setBackground(darkBlue);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlLogin.add(btnLogin);
        
        getRootPane().setDefaultButton(btnLogin);
        btnLogin.addActionListener(this);
        
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(mediumBlue);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(darkBlue);
            }
        });
        
        setVisible(true);
    }
    
    private Border createInputBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(darkBlue, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        if (ae.getSource() == btnLogin) {
            if (user.equals("admin") && pass.equals("admin123")) {
                Admin_SideBarFrame ad = new Admin_SideBarFrame();
                ad.setVisible(true);
                dispose();
            } else if (user.equals("doctor") && pass.equals("doctor123")) {
                Doctor_SideBarFrame dc = new Doctor_SideBarFrame();
                dc.setVisible(true);
                dispose();
            } else if (user.equals("nurse") && pass.equals("nurse123")) {
                Nurse_SideBarFrame nr = new Nurse_SideBarFrame();
                nr.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     
}
