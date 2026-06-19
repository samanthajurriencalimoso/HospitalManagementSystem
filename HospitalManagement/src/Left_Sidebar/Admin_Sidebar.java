package Left_Sidebar;

import Appointments.Admin_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Dashboard.Admin_Dashboard;
import Generating_Reports_Admin.Admin_Reports;
import Inventory.InventoryPanel;
import Inventory.LogisticsPanel;
import Inventory.PatientCentralPanel;
import Inventory.UserManagementPanel;
import Inventory.RequestPanel_Admin;
import Login_Startup.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Admin_Sidebar extends JPanel implements ActionListener{

    private JPanel leftSidebar, pnlInventory;
    private JLabel lblLogo, lblLogoTitle;
    private JButton btnDashboard, btnUManagement, btnPInfo, btnAppointment, btnInventory, btnPOptions, btnIManagement, btnLogistics, btnReport, btnLogout, btnDesign, btnLout, btnRequestManagement;
    private ImageIcon imgDlogo;
    private Image imgbg;
    private Admin_SideBarFrame navPage;
    
    public Admin_Sidebar(Admin_SideBarFrame navPage) {
        this.navPage = navPage;
        setLayout(null);
        
        //Left Sidebar
        leftSidebar = new JPanel();
        leftSidebar.setLayout(null);
        leftSidebar.setBounds(0, 0, 300, 1000);
        leftSidebar.setBackground(darkBlue);
        add(leftSidebar);
        
        imgDlogo = new ImageIcon(getClass().getResource("/resources/eTriage.Logo.png"));
        imgbg = imgDlogo.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(imgbg));
        lblLogo.setBounds(60, 40, 180, 180);
        leftSidebar.add(lblLogo);
        
        lblLogoTitle = new JLabel("eTriage");
        lblLogoTitle.setBounds(105, 200, 120, 30);
        lblLogoTitle.setForeground(Color.WHITE);
        lblLogoTitle.setFont(new Font("Calibri", Font.ITALIC, 28));
        leftSidebar.add(lblLogoTitle);
        
        btnDashboard = btnSideBar("Dashboard", 20, 260, 260, 45, darkBlue);
        leftSidebar.add(btnDashboard);
        
        btnUManagement = btnSideBar("User Management", 20, 320, 260, 45, darkBlue);
        leftSidebar.add(btnUManagement);
        
        btnPInfo = btnSideBar("Patient Central", 20, 380, 260, 45, darkBlue);
        leftSidebar.add(btnPInfo);
        
        btnAppointment = btnSideBar("Appointment", 20, 440, 260, 45, darkBlue);
        leftSidebar.add(btnAppointment);
        
        btnInventory = btnSideBar("Inventory", 20, 500, 260, 45, darkBlue);
        leftSidebar.add(btnInventory);
        
        pnlInventory = new JPanel();
        pnlInventory.setLayout(null);
        pnlInventory.setBounds(40, 545, 240, 130);
        pnlInventory.setBackground(darkBlue);
        pnlInventory.setVisible(false);
        leftSidebar.add(pnlInventory);
        
        String[] InventoryOptions = {"Inventory Management", "Logistics", "Request Management"};
        int InveOption = 0;
        for (String InventoryOp : InventoryOptions) {
            btnPOptions = btnInve(InventoryOp, 0, InveOption, 240, 35);
            pnlInventory.add(btnPOptions);
            
            if(InventoryOp.equals("Inventory Management")) {
                btnIManagement = btnPOptions;
            } else if (InventoryOp.equals("Logistics")) {
                btnLogistics = btnPOptions;
            } else if (InventoryOp.equals("Request Management")) {
                btnRequestManagement = btnPOptions;
            }
            
            InveOption += 40;
        }
        
        btnReport = btnSideBar("Report & Documents", 20, 560, 260, 45, darkBlue);
        leftSidebar.add(btnReport);
        
        btnInventory.addActionListener(e -> {
            boolean expandIOptions = !pnlInventory.isVisible();
            pnlInventory.setVisible(expandIOptions);
            
            if (expandIOptions) {
                btnInventory.setText("Inventory -");
                pnlInventory.setBounds(40, 545, 240, 130);
                btnReport.setBounds(20, 690, 260, 45);
            } else {
                btnInventory.setText("Inventory +");
                pnlInventory.setVisible(false);
                btnReport.setBounds(20, 560, 260, 45);
            }
            leftSidebar.repaint();
            leftSidebar.revalidate();
        });
        
        btnLogout = btnLog("Logout", 20, 930, 260, 45, veryLightBlue);
        leftSidebar.add(btnLogout);
        
        btnDashboard.addActionListener(this);
        btnPInfo.addActionListener(this);
        btnUManagement.addActionListener(this);
        btnIManagement.addActionListener(this);
        btnLogistics.addActionListener(this);
        btnAppointment.addActionListener(this);
        btnReport.addActionListener(this);
        btnRequestManagement.addActionListener(this);
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
        } else if (ae.getSource() == btnUManagement) {
            navPage.turnPage(new UserManagementPanel());
        } else if (ae.getSource() == btnPInfo) {
            navPage.turnPage(new PatientCentralPanel());
        } else if (ae.getSource() == btnAppointment) {
            navPage.turnPage(new Admin_SchedAppointment());
        } else if (ae.getSource() == btnIManagement) {
            navPage.turnPage(new InventoryPanel());
        } else if (ae.getSource() == btnLogistics) {
            navPage.turnPage(new LogisticsPanel());
        } else if (ae.getSource() == btnReport) {
            navPage.turnPage(new Admin_Reports());
        } else if (ae.getSource() == btnRequestManagement) {
            navPage.turnPage(new RequestPanel_Admin());
        }
    } 

    private JButton btnSideBar(String text, int x, int y, int wid, int hei, Color darkblue) {
        btnDesign = new JButton(text);
        btnDesign.setBounds(x, y, wid, hei);
        btnDesign.setFont(new Font("Calibri", Font.BOLD, 20));
        btnDesign.setForeground(Color.WHITE);
        btnDesign.setBackground(darkblue);
        btnDesign.setBorderPainted(false);
        btnDesign.setFocusPainted(false);
        btnDesign.setContentAreaFilled(false);
        btnDesign.setOpaque(true);
        btnDesign.setHorizontalAlignment(SwingConstants.LEFT);
        btnDesign.setMargin(new Insets(0, 10, 0, 0));
        return btnDesign;
    }
    
    private JButton btnInve(String text, int x, int y, int wid, int hei) {
        btnPOptions = new JButton(text);
        btnPOptions.setBounds(x, y, wid, hei);
        btnPOptions.setForeground(Color.WHITE);
        btnPOptions.setBackground(darkBlue);
        btnPOptions.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPOptions.setBorderPainted(false);
        btnPOptions.setFocusPainted(false);
        btnPOptions.setContentAreaFilled(false);
        btnPOptions.setOpaque(true);
        btnPOptions.setHorizontalAlignment(SwingConstants.LEFT);
        btnPOptions.setMargin(new Insets(0, 10, 0, 0));
        return btnPOptions;
    }
    
    private JButton btnLog(String text, int x, int y, int wid, int hei, Color veryLightBlue) {
        btnLout = new JButton(text);
        btnLout.setBounds(x, y, wid, hei);
        btnLout.setFont(new Font("Calibri", Font.BOLD, 20));
        btnLout.setForeground(Color.BLACK);
        btnLout.setBackground(veryLightBlue);
        btnLout.setBorderPainted(false);
        btnLout.setFocusPainted(false);
        btnLout.setContentAreaFilled(false);
        btnLout.setOpaque(true);
        btnLout.setHorizontalAlignment(SwingConstants.CENTER);
        btnLout.setMargin(new Insets(0, 10, 0, 0));
        return btnLout;
    }
}
