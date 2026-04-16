package Appointments;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

public class Admin_SchedAppointment extends JPanel{
    
    private JPanel pnlMain;
    
    public Admin_SchedAppointment() {
        setLayout(null);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JLabel lblMonth = new JLabel("April 2026", SwingConstants.CENTER);
        lblMonth.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblMonth.setBounds(1190, 100, 400, 40);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(new Color(0x39, 0x5A, 0x7F));
        lblMonth.setForeground(Color.WHITE);
        pnlMain.add(lblMonth);
        
        String [] days = {"Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[][] DateNum = {{"", "", "", "1", "2", "3", "4"},
                              {"5", "6", "7", "8", "9", "10", "11"},
                              {"12", "13", "14", "15", "16", "17", "18"},
                              {"19", "20", "21", "22", "23", "24", "25"},
                              {"26", "27", "28", "29", "30", "", ""}};
        
        JTable TblCalendar = new JTable(DateNum, days);
        TblCalendar.setRowHeight(60);
        TblCalendar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        TblCalendar.setGridColor(Color.LIGHT_GRAY);
        TblCalendar.setBackground(Color.WHITE);
        TblCalendar.setSelectionBackground(new Color(0x5A, 0x8F, 0xC9));
        TblCalendar.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrCalendar = new JScrollPane(TblCalendar);
        scrCalendar.setBounds(1190, 140, 400, 320);
        pnlMain.add(scrCalendar);
        
        JPanel pnlPatients = new JPanel();
        pnlPatients.setBounds(30, 30, 250, 40);
        pnlPatients.setBackground(new Color(0x39, 0x5A, 0x7F));
        
        JLabel lblPatients = new JLabel("150 Total Patients", SwingConstants.CENTER);
        lblPatients.setBounds(0, 0, 250, 70);
        lblPatients.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        pnlMain.add(pnlPatients);
        
        JPanel pnlConsul = new JPanel();
        pnlConsul.setBounds(290, 30, 250, 40);
        pnlConsul.setBackground(new Color(0x39, 0x5A, 0x7F));
        pnlMain.add(pnlConsul);
        
        JLabel lblConsul = new JLabel("290 Consultation", SwingConstants.CENTER);
        lblConsul.setBounds(0, 0, 250, 70);
        lblConsul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblConsul.setForeground(Color.WHITE);
        pnlConsul.add(lblConsul);
        
        JPanel pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBounds(30, 100, 1130, 360);
        pnlSelection.setBackground(Color.WHITE);
        pnlMain.add(pnlSelection);
        
        JLabel pnlSelectTitle = new JLabel("Appointment Details Panel");
        pnlSelectTitle.setBounds(20, 10, 300, 40);
        pnlSelectTitle.setFont(new Font("Calibri", Font.BOLD, 26));
        pnlSelectTitle.setForeground(Color.BLACK);
        pnlSelection.add(pnlSelectTitle);
        
        JLabel pnlSelectText= new JLabel("Choose an option to see appointment information.");
        pnlSelectText.setBounds(20, 50, 450, 40);
        pnlSelectText.setFont(new Font("Calibri", Font.PLAIN, 20));
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
        
        JPanel pnlShortcut = new JPanel();
        pnlShortcut.setLayout(null);
        pnlShortcut.setBounds(130, 220, 300, 120);
        pnlShortcut.setBackground(Color.WHITE);
        pnlShortcut.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlShortcut);
        
        JLabel lblShortcutTitle = new JLabel("Action Shortcut");
        lblShortcutTitle.setBounds(20, 20, 250, 20);
        lblShortcutTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblShortcutTitle.setForeground(Color.BLACK);
        pnlShortcut.add(lblShortcutTitle);
        
        JLabel lblShortcutSubtitle = new JLabel("Access common tools fast.");
        lblShortcutSubtitle.setBounds(20, 50, 250, 20);
        lblShortcutSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblShortcutSubtitle.setForeground(Color.DARK_GRAY);
        pnlShortcut.add(lblShortcutSubtitle);
        
        JButton btnGenerate = new JButton("Generate Report");
        btnGenerate.setBounds(20, 80, 130, 25);
        btnGenerate.setFont(new Font("Calibri", Font.PLAIN, 12));
        pnlShortcut.add(btnGenerate);

        JButton btnExport = new JButton("Export Schedule");
        btnExport.setBounds(160, 80, 130, 25);
        btnExport.setFont(new Font("Calibri", Font.PLAIN, 12));
        pnlShortcut.add(btnExport);
        
        JPanel pnlTimeline = new JPanel();
        pnlTimeline.setLayout(null);
        pnlTimeline.setBounds(440, 220, 300, 120);
        pnlTimeline.setBackground(Color.WHITE);
        pnlTimeline.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlTimeline);
        
        JLabel lblTimelineTitle = new JLabel("Timeline View");
        lblTimelineTitle.setBounds(20, 20, 250, 20);
        lblTimelineTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTimelineTitle.setForeground(Color.BLACK);
        pnlTimeline.add(lblTimelineTitle);
        
        JLabel lblTimelineSubtitle = new JLabel("Visualize today’s schedule.");
        lblTimelineSubtitle.setBounds(20, 50, 300, 16);
        lblTimelineSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblTimelineSubtitle.setForeground(Color.DARK_GRAY);
        pnlTimeline.add(lblTimelineSubtitle);
        
        JPanel pnlAddSpace = new JPanel();
        pnlAddSpace.setLayout(null);
        pnlAddSpace.setBounds(750, 220, 300, 120);
        pnlAddSpace.setBackground(Color.WHITE);
        pnlAddSpace.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));
        pnlSelection.add(pnlAddSpace);
        
        JLabel lblAddSpaceTitle = new JLabel("Extra");
        lblAddSpaceTitle.setBounds(20, 20, 250, 20);
        lblAddSpaceTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblAddSpaceTitle.setForeground(Color.BLACK);
        pnlAddSpace.add(lblAddSpaceTitle);
        
        JLabel lblAddSpaceSubtitle = new JLabel("Additional features or notes.");
        lblAddSpaceSubtitle.setBounds(20, 50, 300, 16);
        lblAddSpaceSubtitle.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblAddSpaceSubtitle.setForeground(Color.DARK_GRAY);
        pnlAddSpace.add(lblAddSpaceSubtitle);
        
        // Appointment Controls and Table
        JPanel pnlAppointments = new JPanel();
        pnlAppointments.setBounds(30, 530, 1560, 340);
        pnlAppointments.setLayout(null);
        pnlAppointments.setBackground(Color.WHITE);
        pnlMain.add(pnlAppointments);
        
        JLabel lblDoctor = new JLabel("Doctor: ");
        lblDoctor.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDoctor.setBounds(20, 15, 80, 30);
        pnlAppointments.add(lblDoctor);
        
        String[] doctors = {"All", "Dr. Reyes", "Dr. Villanueva", "Dr. Delgado", "Dr.Santos", 
                            "Dr. Mendoza", "Dr. Santiago", "Dr. Navarro", "Dr. Ramos", "Dr. Bautista", "Dr. Ramirez"
                            };
        JComboBox<String> cmbDoctor = new JComboBox<>(doctors);
        cmbDoctor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cmbDoctor.setBounds(100, 15, 200, 30);
        pnlAppointments.add(cmbDoctor);
        
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setBounds(320, 15, 80, 30);
        pnlAppointments.add(lblStatus);

        String[] statuses = {"All", "Pending", "Complete", "Urgent"};
        JComboBox<String> cmbStatus = new JComboBox<>(statuses);
        cmbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cmbStatus.setBounds(400, 15, 200, 30);
        pnlAppointments.add(cmbStatus);
        
        JButton btnAdd = new JButton("Add Appointment");
        btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnAdd.setBounds(960, 12, 180, 35);
        pnlAppointments.add(btnAdd);
        
        JButton btnEdit = new JButton("Edit Appointment");
        btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnEdit.setBounds(1160, 12, 180, 35);
        pnlAppointments.add(btnEdit);

        JButton btnCancel = new JButton("Cancel Appointment");
        btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnCancel.setBounds(1360, 12, 180, 35);
        pnlAppointments.add(btnCancel);
        
        pnlMain.add(pnlAppointments);
        
        String[] ClmAppoint = {"Patient ID", "Patient Name", "Doctor Consulting", "Treatment Under", "Status", "Action"};
        
        String[][] RwAppointdt = {
            {"10021", "Maria Santos", "Dr. Reyes", "Cardiac Evaluation", "Pending", "Waiting for Confirmation"}, 
            {"10022", "Daniel Cruz", "Dr. Villanueva", "Orthopedia Checkup", "Pending", "Available"},
            {"10023","Angela Ramirez", "Dr. Delgado", "Diabetes Management", "Complete", "Waiting for Confirmation"},
            {"10024", "Roberto Garcia", "Dr. Mendoza", "Dental Cleaning", "Complete", "Available"},
            {"10025", "Elena Cruz", "Dr. Santiago", "General Checkup", "Urgent", "Available"},
            {"10026", "Sophia Cruz", "Dr. Navarro", "Eye Examination", "Pending", "Rescheduled"},
            {"10027", "Miguel Torres", "Dr. Santos", "Physical Therapy", "Complete", "Waiting for Confirmation"},
            {"10028", "Isabella Flores", "Dr. Ramos", "Prenatal Checkup", "Pending", "Available"},
            {"10029", "Carlos Hernandez", "Dr. Bautista", "Skin Allergy Treatment", "Urgent", "Rescheduled"},
            {"10030", "Javier Morales", "Dr. Ramirez", "Blood Pressure Monitoring", "Complete", "Waiting for Confirmation"},
        };
        
        JTable tblAppointments = new JTable(RwAppointdt, ClmAppoint);
        tblAppointments.setRowHeight(30);
        tblAppointments.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tblAppointments.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tblAppointments.setGridColor(Color.LIGHT_GRAY);
        tblAppointments.setBackground(Color.WHITE);
        
        JTableHeader TblHApp = tblAppointments.getTableHeader();
        TblHApp.setBackground(new Color(0xA3, 0xCA, 0xE9));
        TblHApp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        TblHApp.setForeground(Color.BLACK);
        
        JScrollPane scrAppointments = new JScrollPane(tblAppointments);
        scrAppointments.setBounds(20, 70, 1520, 250);
        pnlAppointments.add(scrAppointments);
        
        tblAppointments.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
        JLabel lblStatusAct = new JLabel(value.toString(), SwingConstants.CENTER);
        lblStatusAct.setOpaque(true);
        lblStatusAct.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        if ("Pending".equals(value)) {
            lblStatusAct.setBackground(new Color(255, 245, 204));
        } else if ("Complete".equals(value)) {
            lblStatusAct.setBackground(new Color(204, 255, 229));
        } else if ("Urgent".equals(value)) {
            lblStatusAct.setBackground(new Color(255, 204, 204));
            lblStatusAct.setFont(new Font("Segoe UI", Font.BOLD, 16));
        } else {
            lblStatusAct.setBackground(Color.WHITE);
        }

        if (isSelected) {
        lblStatusAct.setBackground(new Color(173, 216, 230));
        lblStatusAct.setForeground(Color.BLACK);
        }
        
        return lblStatusAct;
        });
        
        tblAppointments.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
        JLabel lblAction = new JLabel(value.toString(), SwingConstants.CENTER);
        lblAction.setOpaque(true);
        lblAction.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAction.setForeground(Color.BLACK);

        if ("Waiting for Confirmation".equals(value)) {
            lblAction.setBackground(new Color(255, 245, 204));
            lblAction.setForeground(Color.BLACK);
        } else if ("Available".equals(value)) {
            lblAction.setBackground(new Color(204, 255, 229)); 
        } else if ("Rescheduled".equals(value)) {
            lblAction.setBackground(new Color(186, 225, 255));
        } else {
            lblAction.setBackground(Color.WHITE);
        }

        if (isSelected) {
        lblAction.setBackground(new Color(216, 191, 216));
        lblAction.setForeground(Color.BLACK);
        }
        
        return lblAction;
        });
        
    }
}
