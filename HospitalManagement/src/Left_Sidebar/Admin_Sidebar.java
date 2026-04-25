package Left_Sidebar;

import Appointments.Admin_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Dashboard.Admin_Dashboard;
import Login_Startup.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Admin_Sidebar extends JPanel implements ActionListener{

    private JButton btnDashboard, btnLogout, btnAppointment;
    private Admin_SideBarFrame navPage;
    
    public Admin_Sidebar(Admin_SideBarFrame navPage) {
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
        lblLogoTitle.setBounds(105, 200, 120, 30);
        lblLogoTitle.setForeground(Color.WHITE);
        lblLogoTitle.setFont(new Font("Calibri", Font.ITALIC, 28));
        leftSidebar.add(lblLogoTitle);
        
        btnDashboard = new JButton("Dashboard");
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
        
        JButton btnUManagement = new JButton("User Management");
        btnUManagement.setLayout(null);
        btnUManagement.setBounds(20, 320, 260, 45);
        btnUManagement.setFont(new Font("Calibri", Font.BOLD, 20));
        btnUManagement.setForeground(Color.WHITE);
        btnUManagement.setBackground(darkBlue);
        btnUManagement.setBorderPainted(false);
        btnUManagement.setFocusPainted(false);
        btnUManagement.setContentAreaFilled(true);
        btnUManagement.setOpaque(true);
        btnUManagement.setHorizontalAlignment(SwingConstants.LEFT);
        btnUManagement.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnUManagement);
        
        JButton btnPInfo = new JButton("Patient Central");
        btnPInfo.setBounds(20, 380, 260, 45);
        btnPInfo.setFont(new Font("Calibri", Font.BOLD, 20));
        btnPInfo.setForeground(Color.WHITE);
        btnPInfo.setBackground(darkBlue);
        btnPInfo.setBorderPainted(false);
        btnPInfo.setFocusPainted(false);
        btnPInfo.setContentAreaFilled(false);
        btnPInfo.setOpaque(true);
        btnPInfo.setHorizontalAlignment(SwingConstants.LEFT);
        btnPInfo.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnPInfo);
        
        btnAppointment = new JButton("Appointment");
        btnAppointment.setBounds(20, 440, 260, 45);
        btnAppointment.setFont(new Font("Calibri", Font.BOLD, 20));
        btnAppointment.setForeground(Color.WHITE);
        btnAppointment.setBackground(darkBlue);
        btnAppointment.setBorderPainted(false);
        btnAppointment.setFocusPainted(false);
        btnAppointment.setContentAreaFilled(false);
        btnAppointment.setOpaque(true);
        btnAppointment.setHorizontalAlignment(SwingConstants.LEFT);
        btnAppointment.setMargin(new Insets(0, 10, 0, 0));
        leftSidebar.add(btnAppointment);
        
        JButton btnInventory = new JButton("Inventory  +");
        btnInventory.setBounds(20, 500, 260, 45);
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
        pnlInventory.setBounds(40, 545, 240, 200);
        pnlInventory.setBackground(darkBlue);
        pnlInventory.setVisible(false);
        leftSidebar.add(pnlInventory);
        
        String[] InventoryOptions = {"Stock Orders", "Logistics", "Supplies", "Pharmacy Inventory", "Equipment"};
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
            InveOption += 40;
        }
        
        JButton btnReport = new JButton("Generating Report");
        btnReport.setLayout(null);
        btnReport.setBounds(20, 560, 260, 45);
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
        
        btnInventory.addActionListener(e -> {
            boolean expandIOptions = !pnlInventory.isVisible();
            pnlInventory.setVisible(expandIOptions);
            
                if (expandIOptions) {
                    btnInventory.setText("Inventory  -");
                    pnlInventory.setBounds(40, 545, 240, 200);
                    btnReport.setBounds(20, 760, 260, 45);
                } else {
                    btnInventory.setText("Inventory  +");
                    pnlInventory.setVisible(false);
                    btnReport.setBounds(20, 560, 260, 45);
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
        
        btnDashboard.addActionListener(this);
        btnAppointment.addActionListener(this);
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
        } else if (ae.getSource() == btnDashboard) {
            navPage.turnPage(new Admin_Dashboard());
        } else if (ae.getSource() == btnAppointment) {
        navPage.turnPage(new Admin_SchedAppointment());
    } 
    } 

}
    
