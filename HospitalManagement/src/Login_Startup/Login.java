package Login_Startup;

import static Color_Palette.ColorPalette.*;
import Left_Sidebar.Admin_SideBarFrame;
import Left_Sidebar.Doctor_SideBarFrame;
import Left_Sidebar.Nurse_SideBarFrame;
import Database.UserManagementSQL; 
import Models.Employee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.*;

public class Login extends JFrame implements ActionListener {
    
    private JPanel pnlLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnForgot;
    private JLabel lblbackground, lblLogo, lblTitle, lblUser, lblPass, lblFooter;
    private ImageIcon imgBg, imgDlogo;
    private Image BgImage, imgbg;
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public Login() {
        setTitle("Hospital Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 1000));
        setLayout(null);
        setLocationRelativeTo(null);
        
        imgBg = new ImageIcon(getClass().getResource("/resources/Login_BG.png"));
        BgImage = imgBg.getImage().getScaledInstance(1020, 1080 ,Image.SCALE_SMOOTH);
        lblbackground = new JLabel(new ImageIcon(BgImage));
        lblbackground.setBounds(900, 0, 1020, 1080);
        add(lblbackground);
        
        pnlLogin = new JPanel();
        pnlLogin.setLayout(null);
        pnlLogin.setBounds(0, 0, 900, 1080);
        pnlLogin.setBackground(Color.WHITE);
        pnlLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderLBLUE, 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        pnlLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderLBLUE, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        add(pnlLogin);
        add(lblbackground);
        
        imgDlogo = new ImageIcon(getClass().getResource("/resources/eTriage.Logo.png"));
        imgbg = imgDlogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(imgbg));
        lblLogo.setBounds(40, 40, 100, 100);
        pnlLogin.add(lblLogo);
        
        lblTitle = new JLabel("eTriage");
        lblTitle.setFont(new Font("Calibri", Font.ITALIC, 22));
        lblTitle.setBounds(150, 60, 300, 40);
        lblTitle.setForeground(Color.BLACK);
        pnlLogin.add(lblTitle);
        
        lblTitle = new JLabel("Welcome Back!");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 40));
        lblTitle.setBounds(80, 220, 600, 60);
        lblTitle.setForeground(Color.BLACK);
        pnlLogin.add(lblTitle);
        
        lblTitle = new JLabel("Please Log in to your Account.");
        lblTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblTitle.setBounds(80, 280, 600, 40);
        lblTitle.setForeground(Color.BLACK);
        pnlLogin.add(lblTitle);
        
        lblUser = new JLabel("Username: ");
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUser.setBounds(80, 370, 200, 30);
        pnlLogin.add(lblUser);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(80, 410, 700, 40);
        txtUsername.setBackground(LightGray);
        txtUsername.setBorder(createInputBorder());
        pnlLogin.add(txtUsername);

        lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPass.setBounds(80, 470, 200, 30);
        pnlLogin.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(80, 510, 700, 40);
        txtPassword.setBackground(LightGray);
        txtPassword.setBorder(createInputBorder());
        pnlLogin.add(txtPassword);

        btnForgot = new JButton("Forgot Password?");
        btnForgot.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnForgot.setForeground(Color.BLUE);
        btnForgot.setBounds(350, 580, 200, 30);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setBorderPainted(false);
        btnForgot.setFocusPainted(false);
        btnForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlLogin.add(btnForgot);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(80, 630, 700, 50);
        btnLogin.setBackground(darkBlue);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlLogin.add(btnLogin);
        
        lblFooter = new JLabel("By sign up you agree to our terms and that you have read our data policy.");
        lblFooter.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblFooter.setForeground(Color.LIGHT_GRAY);
        lblFooter.setBounds(80, 920, 800, 30);
        pnlLogin.add(lblFooter);

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
        
        btnForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnForgot.setForeground(mediumBlue); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnForgot.setForeground(Color.BLUE);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ForgotPasswordFrame recoverFrame = new ForgotPasswordFrame();
                recoverFrame.setVisible(true);
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
        if (ae.getSource() == btnLogin) {
            String user = txtUsername.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password fields!", "Input Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
            
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                
                pst.setString(1, user);
                pst.setString(2, pass);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        
                        UserManagementSQL.currentEmployee = new Employee(
                            rs.getString("name"),
                            rs.getString("id"),
                            rs.getInt("age"),
                            rs.getString("role"),
                            rs.getString("status"),
                            rs.getString("department"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("profile_image")
                        );

                        String role = UserManagementSQL.currentEmployee.getRole();

                        if (role.equalsIgnoreCase("Admin")) {
                            Admin_SideBarFrame sf = new Admin_SideBarFrame();
                            sf.setVisible(true);
                            dispose();
                        } else if (role.equalsIgnoreCase("Doctor")) {
                            Doctor_SideBarFrame sf = new Doctor_SideBarFrame();
                            sf.setVisible(true);
                            dispose();
                        } else if (role.equalsIgnoreCase("Nurse")) {
                            Nurse_SideBarFrame sf = new Nurse_SideBarFrame();
                            sf.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Unrecognized employee role type configured.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        txtPassword.setText("");
                        JOptionPane.showMessageDialog(this, "Incorrect Credentials. Try Again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Connection Failed!\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } 
}