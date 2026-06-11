package Left_Sidebar;

import Prescriptions.Prescription_Nurse;
import Appointments.Nurse_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Dashboard.Nurse_Dashboard;
import Database.PatientManagementSQL;
import Generating_Report_Nurses.Nurses_Report;
import Inventory.RequestPanel_Nurse;
import Login_Startup.Login;
import MedicalHistory.MedicalHistory_Nurse;
import Patient_Information.PatientInfo_Nurse;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Nurse_SideBar extends JPanel implements ActionListener{

    private JPanel leftSidebar, pnlPInfo;
    private JLabel lblLogo, lblLogoTitle;
    private JButton btnDashboard, btnPInfo, btnProfile, btnMHis, btnPres, btnPOptions, btnAppointment, btnInventory, btnReport, btnLogout, btnDesign, btnLout;
    private ImageIcon imgDlogo;
    private Image imgbg;
    private Nurse_SideBarFrame navPage;
    
    public Nurse_SideBar(Nurse_SideBarFrame navPage) {
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
        
        btnPInfo = btnSideBar("Patient Information  +", 20, 320, 260,45, darkBlue);
        leftSidebar.add(btnPInfo);
        
        pnlPInfo = new JPanel();
        pnlPInfo.setLayout(null);
        pnlPInfo.setBounds(40, 365, 240, 120);
        pnlPInfo.setBackground(darkBlue);
        pnlPInfo.setVisible(false);
        leftSidebar.add(pnlPInfo);
        
        btnProfile = btnSideBar("Patient Profile", 0, 0, 240, 35, darkBlue);
        pnlPInfo.add(btnProfile);
        
        btnMHis = btnSideBar("Medical History", 0, 40, 240,35, darkBlue);
        pnlPInfo.add(btnMHis);
        
        btnPres = btnSideBar("Prescriptions", 0, 80, 240,35, darkBlue);
        pnlPInfo.add(btnPres);
        
        btnAppointment = btnSideBar("Appointment", 20, 380, 260, 45, darkBlue);
        leftSidebar.add(btnAppointment);
        
        btnInventory = btnSideBar("Request Items", 20, 440, 260, 45, darkBlue);
        leftSidebar.add(btnInventory);
        
        btnReport = btnSideBar("Report & Documents", 20, 500, 260, 45, darkBlue);
        leftSidebar.add(btnReport);
        
        btnPInfo.addActionListener(e -> {
            boolean expandIOptions = !pnlPInfo.isVisible();
            pnlPInfo.setVisible(expandIOptions);
            
            if (expandIOptions) {
                btnPInfo.setText("Patient Information  -");
                btnAppointment.setBounds(20, 500, 260, 45);
                btnInventory.setBounds(20, 560, 260, 45);
                btnReport.setBounds(20, 620, 260, 45);
            } else {
                btnPInfo.setText("Patient Information  +");
                btnAppointment.setBounds(20, 380, 260, 45);
                btnInventory.setBounds(20, 440, 260, 45);
                btnReport.setBounds(20, 500, 260, 45);
                }
                
            leftSidebar.repaint();
            leftSidebar.revalidate();
        });
        
        btnLogout = btnLog("Logout", 20, 930, 260, 45, veryLightBlue);
        leftSidebar.add(btnLogout);
        
        btnDashboard.addActionListener(this);
        btnProfile.addActionListener(this);
        btnMHis.addActionListener(this);
        btnPres.addActionListener(this);
        btnAppointment.addActionListener(this);
        btnInventory.addActionListener(this);
        btnReport.addActionListener(this);
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
            navPage.turnPage(new Nurse_Dashboard());
        } else if (ae.getSource() == btnProfile) {
    String patientName = PatientManagementSQL.getCurrentPatientName();
    if (patientName == null || patientName.isEmpty()) {
        JOptionPane.showMessageDialog(navPage, "No patient selected. Please select a patient from the dashboard or appointments first.");
        navPage.turnPage(new PatientInfo_Nurse());
    } else {
        navPage.turnPage(new PatientInfo_Nurse(patientName));
    }
} else if (ae.getSource() == btnMHis) {
    String patientName = PatientManagementSQL.getCurrentPatientName();
    if (patientName == null || patientName.isEmpty()) {
        JOptionPane.showMessageDialog(navPage, "No patient selected. Please select a patient from the dashboard or appointments first.");
        navPage.turnPage(new MedicalHistory_Nurse());
    } else {
        navPage.turnPage(new MedicalHistory_Nurse(patientName));
    }
} else if (ae.getSource() == btnPres) {
            navPage.turnPage(new Prescription_Nurse());
        } else if (ae.getSource() == btnAppointment) {
            navPage.turnPage(new  Nurse_SchedAppointment());
        } else if (ae.getSource() == btnInventory) {
            navPage.turnPage(new RequestPanel_Nurse());
        } else if (ae.getSource() == btnReport) {
            navPage.turnPage(new Nurses_Report());
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
    
    private JButton btnPI(String text, int x, int y, int wid, int hei) {
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