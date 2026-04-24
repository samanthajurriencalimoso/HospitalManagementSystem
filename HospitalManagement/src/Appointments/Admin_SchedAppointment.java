/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Appointments;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jm's Laptop
 */
public class Admin_SchedAppointment extends JPanel implements ActionListener{

    private JPanel pnlMain;
    
    public Admin_SchedAppointment() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(new Color(0xE9, 0xEC, 0xEE));
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JPanel pnlPatients = new JPanel();
        pnlPatients.setBounds(30, 30, 250, 40);
        pnlPatients.setBackground(new Color(0x39, 0x5A, 0x7F));
        pnlMain.add(pnlPatients);
        
        JLabel lblPatients = new JLabel("150 Total Patients", SwingConstants.CENTER);
        lblPatients.setBounds(0, 0, 250, 70);
        lblPatients.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        
        JPanel pnlConsul = new JPanel();
        pnlConsul.setBounds(290, 30, 250, 40);
        pnlConsul.setBackground(new Color(0x39, 0x5A, 0x7F));
        pnlMain.add(pnlConsul);
        
        JLabel lblConsul = new JLabel("290 Consultation", SwingConstants.CENTER);
        lblConsul.setBounds(0, 0, 250, 70);
        lblConsul.setFont(new Font("Calibri", Font.BOLD, 18));
        lblConsul.setForeground(Color.WHITE);
        pnlConsul.add(lblConsul);
        
        JPanel pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBounds(70, 100, 1500, 230);
        pnlSelection.setBackground(Color.WHITE);
        pnlMain.add(pnlSelection);
        
        JLabel pnlSelectTitle = new JLabel("Appointment Details Panel");
        pnlSelectTitle.setBounds(20, 10, 300, 40);
        pnlSelectTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        pnlSelectTitle.setForeground(Color.BLACK);
        pnlSelection.add(pnlSelectTitle);
        
        JLabel pnlSelectText= new JLabel("Choose an option to see appointment information.");
        pnlSelectText.setBounds(20, 40, 450, 40);
        pnlSelectText.setFont(new Font("Calibri", Font.PLAIN, 18));
        pnlSelectText.setForeground(Color.BLACK);
        pnlSelection.add(pnlSelectText);
        
        JPanel pnlQuickStats = new JPanel();
        pnlQuickStats.setLayout(null);
        pnlQuickStats.setBounds(130, 90, 300, 120);
        pnlQuickStats.setBackground(Color.WHITE);
        pnlQuickStats.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlQuickStats);
        
        JLabel lblQSTitle = new JLabel("Quick Stats");
        lblQSTitle.setBounds(20, 20, 200, 20);
        lblQSTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblQSTitle.setForeground(Color.BLACK);
        pnlQuickStats.add(lblQSTitle);
        
        JLabel lblQSSubtitle = new JLabel("View patient and appointment summary.");
        lblQSSubtitle.setBounds(20, 50, 300, 25);
        lblQSSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblQSSubtitle.setForeground(Color.DARK_GRAY);
        pnlQuickStats.add(lblQSSubtitle);
        
        JPanel pnlUpcoming = new JPanel();
        pnlUpcoming.setLayout(null);
        pnlUpcoming.setBounds(440, 90, 300, 120);
        pnlUpcoming.setBackground(Color.WHITE);
        pnlUpcoming.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlUpcoming);
        
        JLabel lblUpTitle = new JLabel("Upcoming Appointments");
        lblUpTitle.setBounds(20, 20, 250, 20);
        lblUpTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblUpTitle.setForeground(Color.BLACK);
        pnlUpcoming.add(lblUpTitle);
        
        JLabel lblUpSubtitle = new JLabel("See upcoming visits today.");
        lblUpSubtitle.setBounds(20, 50, 250, 25);
        lblUpSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblUpSubtitle.setForeground(Color.DARK_GRAY);
        pnlUpcoming.add(lblUpSubtitle);
        
        JPanel pnlAlerts = new JPanel();
        pnlAlerts.setLayout(null);
        pnlAlerts.setBounds(750, 90, 300, 120);
        pnlAlerts.setBackground(Color.WHITE);
        pnlAlerts.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlAlerts);
        
        JLabel lblAlertsTitle = new JLabel("Alerts & Notifications");
        lblAlertsTitle.setBounds(20, 20, 250, 20);
        lblAlertsTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblAlertsTitle.setForeground(Color.BLACK);
        pnlAlerts.add(lblAlertsTitle);
        
        JLabel lblAlertsSubtitle = new JLabel("Check urgent reminders.");
        lblAlertsSubtitle.setBounds(20, 50, 300, 16);
        lblAlertsSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblAlertsSubtitle.setForeground(Color.DARK_GRAY);
        pnlAlerts.add(lblAlertsSubtitle);
        
        JPanel pnlAvail = new JPanel();
        pnlAvail.setLayout(null);
        pnlAvail.setBounds(1070, 90, 300, 120);
        pnlAvail.setBackground(Color.WHITE);
        pnlAvail.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlAvail);
        
        JLabel lblAvailTitle = new JLabel("Doctor Availability");
        lblAvailTitle.setBounds(20, 20, 250, 20);
        lblAvailTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblAvailTitle.setForeground(Color.BLACK);
        pnlAvail.add(lblAvailTitle);
        
        JLabel lblAvailSubtitle = new JLabel("Check doctor schedules.");
        lblAvailSubtitle.setBounds(20, 50, 250, 20);
        lblAvailSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblAvailSubtitle.setForeground(Color.DARK_GRAY);
        pnlAvail.add(lblAvailSubtitle);
        
        JPanel pnlDepart = new JPanel(); 
        pnlDepart.setLayout(null); 
        pnlDepart.setBounds(70, 350, 1500, 50); 
        pnlDepart.setBackground(Color.WHITE); 
        pnlMain.add(pnlDepart); 
        
        JLabel lblDepart = new JLabel("Doctor Department:"); 
        lblDepart.setBounds(40, 13, 200, 30); 
        lblDepart.setFont(new Font("Calibri", Font.BOLD, 22)); 
        pnlDepart.add(lblDepart); 
        String[] departments = { "Department: All", "General Medicine", "Pediatrics", "Internal Medicine", "Cardiology", 
                                 "Endocrinology", "Gastroenterology", "Neurology", "Pulmonology", "Nephrology", "Orthopedics", 
                                 "Surgery", "Physical Therapy", "Rehabilitation", "Ophthalmology", "ENT", "Dermatology", "OB-GYN", 
                                 "Family Medicine", "Psychiatry", "Emergency", "Dentistry", "Radiology", "Anesthesiology"}; 
        
        JComboBox<String> cmbDepart = new JComboBox<>(departments); 
        cmbDepart.setFont(new Font("Calibri", Font.PLAIN, 22)); 
        cmbDepart.setBounds(240, 10, 300, 30); 
        pnlDepart.add(cmbDepart); 
        
        String[] status = { "Status: All", "Available", "In Consultation", 
            "Waiting for Patient", "Fully Booked", "On Break", "On Leave", 
            "Off Duty", "Emergency Handling"}; 
        
        JComboBox<String> cmbstatus = new JComboBox<>(status); 
        cmbstatus.setFont(new Font("Calibri", Font.PLAIN, 22)); 
        cmbstatus.setBounds(560, 10, 300, 30); pnlDepart.add(cmbstatus); 
        
        String[] dateOptions = { "Date: All", "Today", "Tomorrow", "This Week", "Next Week"}; 
        
        JComboBox<String> cmbDate = new JComboBox<>(dateOptions); 
        cmbDate.setFont(new Font("Calibri", Font.PLAIN, 16)); 
        cmbDate.setBounds(880, 10, 300, 30); 
        pnlDepart.add(cmbDate);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(1200, 10, 130, 30);
        btnClear.setFont(new Font("Calibri", Font.BOLD, 20));
        btnClear.setBackground(lightBlue);
        btnClear.setFocusPainted(false);
        pnlDepart.add(btnClear);
        
        JButton btnRef = new JButton("Refresh");
        btnRef.setBounds(1350, 10, 130, 30);
        btnRef.setFont(new Font("Calibri", Font.BOLD, 20));
        btnRef.setBackground(lightBlue);
        btnRef.setFocusPainted(false);
        pnlDepart.add(btnRef);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
