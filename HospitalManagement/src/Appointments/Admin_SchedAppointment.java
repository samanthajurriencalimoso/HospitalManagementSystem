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
import javax.swing.table.JTableHeader;

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
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JPanel pnlPatients = new JPanel();
        pnlPatients.setLayout(null);
        pnlPatients.setBounds(30, 30, 250, 40);
        pnlPatients.setBackground(Blue);
        pnlMain.add(pnlPatients);
        
        JLabel lblPatients = new JLabel("150 Today's Patients", SwingConstants.CENTER);
        lblPatients.setBounds(0, 0, 250, 40);
        lblPatients.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        
        JPanel pnlAvail = new JPanel();
        pnlAvail.setLayout(null);
        pnlAvail.setBounds(290, 30, 250, 40);
        pnlAvail.setBackground(Green);
        pnlMain.add(pnlAvail);
        
        JLabel lblAvail = new JLabel("4 Doctor's Available", SwingConstants.CENTER);
        lblAvail.setBounds(0, 0, 250, 40);
        lblAvail.setFont(new Font("Calibri", Font.BOLD, 18));
        lblAvail.setForeground(Color.WHITE);
        pnlAvail.add(lblAvail);
        
        JPanel pnlUrgent = new JPanel();
        pnlUrgent.setLayout(null);
        pnlUrgent.setBounds(560, 30, 250, 40);
        pnlUrgent.setBackground(LightRed);
        pnlMain.add(pnlUrgent);
        
        JLabel lblUrgent = new JLabel("6 Urgent Cases", SwingConstants.CENTER);
        lblUrgent.setBounds(0, 0, 250, 40);
        lblUrgent.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUrgent.setForeground(Color.WHITE);
        pnlUrgent.add(lblUrgent);
        
        JPanel pnlApp = new JPanel();
        pnlApp.setLayout(null);
        pnlApp.setBounds(830, 30, 250, 40);
        pnlApp.setBackground(Yellow);
        pnlMain.add(pnlApp);
        
        JLabel lblApp = new JLabel("18 Pending Appointments", SwingConstants.CENTER);
        lblApp.setBounds(0, 0, 250, 40);
        lblApp.setFont(new Font("Calibri", Font.BOLD, 18));
        lblApp.setForeground(Color.WHITE);
        pnlApp.add(lblApp);
        
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
        
        JPanel pnlAvail2 = new JPanel();
        pnlAvail2.setLayout(null);
        pnlAvail2.setBounds(1070, 90, 300, 120);
        pnlAvail2.setBackground(Color.WHITE);
        pnlAvail2.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlAvail2);
        
        JLabel lblAvailTitle = new JLabel("Doctor Availability");
        lblAvailTitle.setBounds(20, 20, 250, 20);
        lblAvailTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblAvailTitle.setForeground(Color.BLACK);
        pnlAvail2.add(lblAvailTitle);
        
        JLabel lblAvailSubtitle = new JLabel("Check doctor schedules.");
        lblAvailSubtitle.setBounds(20, 50, 250, 20);
        lblAvailSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblAvailSubtitle.setForeground(Color.DARK_GRAY);
        pnlAvail2.add(lblAvailSubtitle);
        
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
        cmbDepart.setFont(new Font("Calibri", Font.PLAIN, 16)); 
        cmbDepart.setBounds(240, 10, 300, 30); 
        pnlDepart.add(cmbDepart); 
        
        String[] status = { "Status: All", "Available", "In Consultation", 
            "Waiting for Patient", "Fully Booked", "On Break", "On Leave", 
            "Off Duty", "Emergency Handling"}; 
        
        JComboBox<String> cmbstatus = new JComboBox<>(status); 
        cmbstatus.setFont(new Font("Calibri", Font.PLAIN, 16)); 
        cmbstatus.setBounds(560, 10, 300, 30); pnlDepart.add(cmbstatus); 
        
        String[] dateOptions = { "Date: All", "Today", "Tomorrow", "This Week", "Next Week"}; 
        
        JComboBox<String> cmbDate = new JComboBox<>(dateOptions); 
        cmbDate.setFont(new Font("Calibri", Font.PLAIN, 16)); 
        cmbDate.setBounds(880, 10, 300, 30); 
        pnlDepart.add(cmbDate);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(1200, 10, 130, 30);
        btnClear.setFont(new Font("Calibri", Font.BOLD, 18));
        btnClear.setBackground(lightBlue);
        btnClear.setFocusPainted(false);
        pnlDepart.add(btnClear);
        
        JButton btnRef = new JButton("Refresh");
        btnRef.setBounds(1350, 10, 130, 30);
        btnRef.setFont(new Font("Calibri", Font.BOLD, 18));
        btnRef.setBackground(lightBlue);
        btnRef.setFocusPainted(false);
        pnlDepart.add(btnRef);
        
        JPanel pnlDoctor = new JPanel();
        pnlDoctor.setLayout(null);
        pnlDoctor.setBounds(70, 420, 1500, 480);
        pnlDoctor.setBackground(Color.WHITE);
        pnlMain.add(pnlDoctor);
        
        JButton btnPatientApp = new JButton("Patient Appointments");
        btnPatientApp.setBounds(20, 10, 220, 40);
        btnPatientApp.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPatientApp.setBackground(darkBlue);
        btnPatientApp.setForeground(Color.WHITE);
        pnlDoctor.add(btnPatientApp);

        JButton btnDoctorAvail = new JButton("Doctor Availability");
        btnDoctorAvail.setBounds(250, 10, 220, 40);
        btnDoctorAvail.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlDoctor.add(btnDoctorAvail);
        
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(1360, 10, 120, 40);
        btnSave.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSave.setBackground(darkBlue);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        pnlDoctor.add(btnSave);
        
        JPanel pnlTableContainer = new JPanel();
        pnlTableContainer.setLayout(null);
        pnlTableContainer.setBounds(20, 60, 1460, 400);
        pnlDoctor.add(pnlTableContainer);

                Runnable showPatientTable = () -> {
            pnlTableContainer.removeAll();

            String[] clmPatient = {
                "Patient ID", "Patient Name", "Doctor",
                "Treatment", "Status", "Action"
            };

            String[][] rwPatient = {
                {"10021", "Maria Santos", "Dr. Reyes", "Cardiac", "Pending", "Waiting"},
                {"10022", "Daniel Cruz", "Dr. Villanueva", "Orthopedia", "Pending", "Available"},
                {"10023", "Angela Ramirez", "Dr. Delgado", "Diabetes", "Complete", "Done"},
                {"10024", "Roberto Garcia", "Dr. Mendoza", "Dental", "Complete", "Done"},
                {"10025", "Elena Cruz", "Dr. Santiago", "General", "Urgent", "Priority"}
            };

            JTable tblPatient = new JTable(rwPatient, clmPatient);
            tblPatient.setRowHeight(30);
            tblPatient.setFont(new Font("Calibri", Font.PLAIN, 16));

            String[] patientStatuses = {"Select Status: ", "Pending", "Complete", "Urgent", "Cancelled", "Rescheduled"};
            
            JComboBox<String> cmbPatientStatus = new JComboBox<>(patientStatuses);
            tblPatient.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cmbPatientStatus));
            
            tblPatient.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {

            JLabel lblStatus = new JLabel(value.toString(), SwingConstants.CENTER);
            lblStatus.setOpaque(true);
            lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));

            switch (value.toString()) {
                case "Pending":
                    lblStatus.setBackground(PaleYellow);
                    break;
                case "Complete":
                    lblStatus.setBackground(MintGreen);
                    break;
                case "Urgent":
                    lblStatus.setBackground(SoftPink);
                    break;
                case "Cancelled":
                    lblStatus.setBackground(Teal);
                    break;
                case "Rescheduled":
                    lblStatus.setBackground(Lavender);
                    break;
                default:
                    lblStatus.setBackground(Color.WHITE);
            }

                    if (isSelected) {
                        lblStatus.setBackground(veryLightBlue);
                    }

                    return lblStatus;
                });
            
            JScrollPane scrPatient = new JScrollPane(tblPatient);
            scrPatient.setBounds(0, 0, 1460, 400);
            
            pnlTableContainer.add(scrPatient);
            pnlTableContainer.revalidate();
            pnlTableContainer.repaint();
        };

        Runnable showDoctorTable = () -> {
            pnlTableContainer.removeAll();

            String[] clmDoctor = {
                "Doctor Name", "Department", "Status",
                "Shift", "Appointments", "Next Available", "Room"
            };

            String[][] rwDoctor = {
                {"Dr. Reyes", "Cardiology", "Available", "8AM-4PM", "3", "11:15 AM", "201"},
                {"Dr. Villanueva", "Orthopedics", "Busy", "9AM-5PM", "6", "2:30 PM", "305"},
                {"Dr. Delgado", "Endocrinology", "Fully Booked", "8AM-2PM", "8", "-", "210"},
                {"Dr. Santos", "General", "On Leave", "-", "0", "-", "-"},
                {"Dr. Mendoza", "Dentistry", "Available", "10AM-6PM", "2", "4:45 PM", "110"}
            };

            JTable tblDoctor = new JTable(rwDoctor, clmDoctor);
            tblDoctor.setRowHeight(30);
            tblDoctor.setFont(new Font("Calibri", Font.PLAIN, 16));

            String[] doctorStatuses = {"Select Status: ", "Available", "Busy", "Fully Booked", "On Leave", "Off Duty"};
            JComboBox<String> cmbDoctorStatus = new JComboBox<>(doctorStatuses);
            tblDoctor.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cmbDoctorStatus));
            
            tblDoctor.getColumnModel().getColumn(2).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {

            JLabel lblStatus = new JLabel(value.toString(), SwingConstants.CENTER);
            lblStatus.setOpaque(true);
            lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));

            switch (value.toString()) {
                case "On Leave":
                    lblStatus.setBackground(Lavender);
                    break;
                case "Available":
                    lblStatus.setBackground(MintGreen);
                    break;
                case "Fully Booked":
                    lblStatus.setBackground(SoftPink);
                    break;
                case "Off Duty":
                    lblStatus.setBackground(LightGray);
                    break;
                case "Busy":
                    lblStatus.setBackground(Teal);
                    break;
                default:
                    lblStatus.setBackground(Color.WHITE);
            }

                    if (isSelected) {
                        lblStatus.setBackground(veryLightBlue);
                    }

                    return lblStatus;
                });
            
            JScrollPane scroll = new JScrollPane(tblDoctor);
            scroll.setBounds(0, 0, 1460, 400);

            pnlTableContainer.add(scroll);
            pnlTableContainer.revalidate();
            pnlTableContainer.repaint();
        };

        btnPatientApp.addActionListener(e -> {
            showPatientTable.run();

            btnPatientApp.setBackground(darkBlue);
            btnPatientApp.setForeground(Color.WHITE);

            btnDoctorAvail.setBackground(Color.WHITE);
            btnDoctorAvail.setForeground(Color.BLACK);
        });

        btnDoctorAvail.addActionListener(e -> {
            showDoctorTable.run();

            btnDoctorAvail.setBackground(darkBlue);
            btnDoctorAvail.setForeground(Color.WHITE);

            btnPatientApp.setBackground(Color.WHITE);
            btnPatientApp.setForeground(Color.BLACK);
        });
        showPatientTable.run();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
