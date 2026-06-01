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
    
    private JPanel pnlLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblbackground, lblLogo, lblTitle, lblUser, lblPass, lblForgot, lblFooter;
    private ImageIcon imgBg, imgDlogo;
    private Image BgImage, imgbg;
    
    
    public Login() {
        setTitle("Hospital Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // For screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 1000));
        setLayout(null);
        setLocationRelativeTo(null);
        
        imgBg = new ImageIcon(getClass().getResource("/resources/Login_BG.png"));
        BgImage = imgBg.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        lblbackground = new JLabel(new ImageIcon(BgImage));
        lblbackground.setBounds(0, 0, 1920, 1080);
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

        lblbackground.add(pnlLogin);
        
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

        lblForgot = new JLabel("Forgot Password?");
        lblForgot.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblForgot.setForeground(Color.BLUE);
        lblForgot.setBounds(370, 580, 200, 30);
        lblForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlLogin.add(lblForgot);

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
