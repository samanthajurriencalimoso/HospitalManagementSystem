package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Login_Startup.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Nurse_SideBar extends JPanel implements ActionListener{

    private JButton btnLogout;
    private Nurse_SideBarFrame navPage;
    
    public Nurse_SideBar(Nurse_SideBarFrame navPage) {
        this.navPage = navPage;
        setLayout(null);
        //Left Sidebar
        JPanel leftSidebar = new JPanel();
        leftSidebar.setLayout(null);
        leftSidebar.setBounds(0, 0, 300, 1000);
        leftSidebar.setBackground(darkBlue);
        add(leftSidebar);
        
        ImageIcon imgDlogo = new ImageIcon(getClass().getResource("/resources/eTriage.Logo.png"));
        Image imgbg = imgDlogo.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imgbg));
        lblLogo.setBounds(60, 40, 180, 180);
        leftSidebar.add(lblLogo);
        
        JLabel lblLogoTitle = new JLabel("eTriage");
        lblLogoTitle.setBounds(110, 210, 120, 30);
        lblLogoTitle.setForeground(Color.WHITE);
        lblLogoTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        leftSidebar.add(lblLogoTitle);
        
        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setLayout(null);
        btnDashboard.setBounds(20, 260, 260, 45);
        btnDashboard.setFont(new Font("Calibri", Font.BOLD, 20));
        btnDashboard.setForeground(Color.WHITE);
        btnDashboard.setBackground(darkBlue);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setContentAreaFilled(true);
        btnDashboard.setOpaque(true);
        btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
        btnDashboard.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnDashboard);
        
        JButton btnPInfo = new JButton("Patient Information  +");
        btnPInfo.setLayout(null);
        btnPInfo.setBounds(20, 320, 260, 45);
        btnPInfo.setFont(new Font("Calibri", Font.BOLD, 20));
        btnPInfo.setForeground(Color.WHITE);
        btnPInfo.setBackground(darkBlue);
        btnPInfo.setBorderPainted(false);
        btnPInfo.setFocusPainted(false);
        btnPInfo.setContentAreaFilled(true);
        btnPInfo.setOpaque(true);
        btnPInfo.setHorizontalAlignment(SwingConstants.LEFT);
        btnPInfo.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnPInfo);
        
        JPanel pnlPInfo = new JPanel();
        pnlPInfo.setLayout(null);
        pnlPInfo.setBounds(40, 365, 240, 80);
        pnlPInfo.setBackground(darkBlue);
        pnlPInfo.setVisible(false);
        leftSidebar.add(pnlPInfo);
        
        String[] PatientInfoOptions = {"Medical History", "Prescriptions"};
        int PInfoOption = 0;
        for (String PInfOption : PatientInfoOptions) {
            JButton btnPInfoOptions = new JButton(PInfOption);
            btnPInfoOptions.setBounds(0, PInfoOption, 240, 35);
            btnPInfoOptions.setForeground(Color.WHITE);
            btnPInfoOptions.setBackground(darkBlue);
            btnPInfoOptions.setFont(new Font("Calibri", Font.BOLD, 18));
            btnPInfoOptions.setBorderPainted(false);
            btnPInfoOptions.setFocusPainted(false);
            btnPInfoOptions.setContentAreaFilled(false);
            btnPInfoOptions.setOpaque(true);
            btnPInfoOptions.setHorizontalAlignment(SwingConstants.LEFT);
            btnPInfoOptions.setMargin(new Insets(0, 10, 0, 0));
            pnlPInfo.add(btnPInfoOptions);
            PInfoOption += 40;
        }
        
        JButton btAppointment = new JButton("Appointment");
        btAppointment.setBounds(20, 380, 260, 45);
        btAppointment.setFont(new Font("Calibri", Font.BOLD, 20));
        btAppointment.setForeground(Color.WHITE);
        btAppointment.setBackground(darkBlue);
        btAppointment.setBorderPainted(false);
        btAppointment.setFocusPainted(false);
        btAppointment.setContentAreaFilled(false);
        btAppointment.setOpaque(true);
        btAppointment.setHorizontalAlignment(SwingConstants.LEFT);
        btAppointment.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btAppointment);
        
        JButton btnInventory = new JButton("Inventory  +");
        btnInventory.setBounds(20, 440, 260, 45);
        btnInventory.setFont(new Font("Calibri", Font.BOLD, 20));
        btnInventory.setForeground(Color.WHITE);
        btnInventory.setBackground(darkBlue);
        btnInventory.setBorderPainted(false);
        btnInventory.setFocusPainted(false);
        btnInventory.setContentAreaFilled(false);
        btnInventory.setOpaque(true);
        btnInventory.setHorizontalAlignment(SwingConstants.LEFT);
        btnInventory.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnInventory);
        
        JPanel pnlInventory = new JPanel();
        pnlInventory.setLayout(null);
        pnlInventory.setBounds(40, 485, 240, 35);
        pnlInventory.setBackground(darkBlue);
        pnlInventory.setVisible(false);
        leftSidebar.add(pnlInventory);
        
        String[] InventoryOptions = {"Request Items"};
        int InveOption = 0;
        for (String InventoryOp : InventoryOptions) {
            JButton btnPOptions = new JButton(InventoryOp);
            btnPOptions.setBounds(0, InveOption, 240, 35);
            btnPOptions.setForeground(Color.WHITE);
            btnPOptions.setBackground(darkBlue);
            btnPOptions.setFont(new Font("Calibri", Font.BOLD, 18));
            btnPOptions.setBorderPainted(false);
            btnPOptions.setFocusPainted(false);
            btnPOptions.setContentAreaFilled(false);
            btnPOptions.setOpaque(true);
            btnPOptions.setHorizontalAlignment(SwingConstants.LEFT);
            btnPOptions.setMargin(new Insets(0, 10, 0, 0));
            pnlInventory.add(btnPOptions);
            InveOption += 35;
        }
        
        JButton btnReport = new JButton("Generating Report");
        btnReport.setLayout(null);
        btnReport.setBounds(20, 500, 260, 45);
        btnReport.setFont(new Font("Calibri", Font.BOLD, 20));
        btnReport.setForeground(Color.WHITE);
        btnReport.setBackground(darkBlue);
        btnReport.setBorderPainted(false);
        btnReport.setFocusPainted(false);
        btnReport.setContentAreaFilled(false);
        btnReport.setOpaque(true);
        btnReport.setHorizontalAlignment(SwingConstants.LEFT);
        btnReport.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnReport);
        
        btnPInfo.addActionListener(e -> {
            boolean expandIOptions = !pnlPInfo.isVisible();
            pnlPInfo.setVisible(expandIOptions);
            
            if (expandIOptions) {
                btnPInfo.setText("Patient Information  -");
                btAppointment.setBounds(20, 460, 260, 45);
                btnInventory.setBounds(20, 520, 260, 45);
                
                if (pnlInventory.isVisible()) {
                    pnlInventory.setBounds(40, 565, 240, 35);
                    btnReport.setBounds(20, 610, 260, 45);
                } else {
                    btnReport.setBounds(20, 565, 260, 45);
                }
                
            } else {
                btnPInfo.setText("Patient Information  +");
                btAppointment.setBounds(20, 380, 260, 45);
                btnInventory.setBounds(20, 440, 260, 45);

                if (pnlInventory.isVisible()) {
                    pnlInventory.setBounds(40, 485, 240, 35);
                    btnReport.setBounds(20, 530, 260, 45);
                } else {
                    btnReport.setBounds(20, 500, 260, 45);
                }
                
            }
            leftSidebar.repaint();
            leftSidebar.revalidate();
        });
        
        btnInventory.addActionListener(e -> {
            boolean expandIOptions = !pnlInventory.isVisible();
            pnlInventory.setVisible(expandIOptions);
            
                if (expandIOptions) {
                    if (pnlPInfo.isVisible()) {
                    pnlInventory.setBounds(40, 565, 240, 35);
                    btnReport.setBounds(20, 610, 260, 45);
                } else {
                    pnlInventory.setBounds(40, 485, 240, 35);
                    btnReport.setBounds(20, 530, 260, 45);
                }
            } else {
                btnInventory.setText("Inventory  -");
                pnlInventory.setVisible(false);
                if (pnlPInfo.isVisible()) {
                    btnReport.setBounds(20, 565, 260, 45);
                } else {
                btnReport.setBounds(20, 500, 260, 45);
                }
            }
            leftSidebar.repaint();
            leftSidebar.revalidate();
        });

        JButton btnAppearance = new JButton("Appearance");
        btnAppearance.setLayout(null);
        btnAppearance.setBounds(20, 830, 260, 45);
        btnAppearance.setFont(new Font("Calibri", Font.BOLD, 20));
        btnAppearance.setForeground(Color.WHITE);
        btnAppearance.setBackground(darkBlue);
        btnAppearance.setBorderPainted(false);
        btnAppearance.setFocusPainted(false);
        btnAppearance.setContentAreaFilled(false);
        btnAppearance.setOpaque(true);
        btnAppearance.setHorizontalAlignment(SwingConstants.LEFT);
        btnAppearance.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnAppearance);
        
        JRadioButton rbtnDarkMode = new JRadioButton("Dark Mode");
        rbtnDarkMode.setBounds(60, 880, 200, 30);
        rbtnDarkMode.setForeground(Color.WHITE);
        rbtnDarkMode.setFont(new Font("Calibri", Font.PLAIN, 18));
        rbtnDarkMode.setBackground(darkBlue);
        rbtnDarkMode.setOpaque(true);
        leftSidebar.add(rbtnDarkMode);
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(20, 930, 260, 45);
        btnLogout.setFont(new Font("Calibri", Font.BOLD, 18));
        btnLogout.setForeground(Color.BLACK);
        btnLogout.setBackground(veryLightBlue);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setContentAreaFilled(true);
        btnLogout.setOpaque(true);
        btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
        leftSidebar.add(btnLogout);
        
        btnLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btnLogout) {

        int confirm = JOptionPane.showConfirmDialog(
                navPage,
                "Are you sure you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION
        );

            if (confirm == JOptionPane.YES_OPTION) {
                Login lg = new Login();
                lg.setVisible(true);
                navPage.dispose();
            }
        }
    }
    
}
