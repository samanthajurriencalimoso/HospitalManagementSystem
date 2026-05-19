package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class AppointmentHistory extends JPanel {
    
    public AppointmentHistory() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlAppHistory = new JPanel();
        pnlAppHistory.setLayout(null);
        pnlAppHistory.setPreferredSize(new Dimension(1040, 900));
        pnlAppHistory.setBackground(Color.WHITE);
        pnlAppHistory.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrAppHistory = new JScrollPane(pnlAppHistory);
        scrAppHistory.setBounds(0, 0, 1060, 650);
        scrAppHistory.setBorder(BorderFactory.createEmptyBorder());
        scrAppHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrAppHistory);

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlAppHistory.add(lblLogo);

        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlAppHistory.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlAppHistory.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlAppHistory.add(lblContact);

        JLabel lblTitle = new JLabel("APPOINTMENT HISTORY");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(680, 35, 400, 30);
        pnlAppHistory.add(lblTitle);

        JLabel lblRefNo = new JLabel("Ref No.: AP-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlAppHistory.add(lblRefNo);

        JLabel lblDate = new JLabel("Generated: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlAppHistory.add(lblDate);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlAppHistory.add(lineTop);

        
        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 980, 80);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlAppHistory.add(pnlPatient);

        JLabel lblPName = new JLabel("Patient Name: Joshua Garcia");
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        JLabel lblPID = new JLabel("Patient ID: P-10234");
        lblPID.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPID.setBounds(400, 30, 200, 20);
        pnlPatient.add(lblPID);

        
        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 250, 980, 280);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Appointment Records"));
        pnlAppHistory.add(pnlTable);

        String[] clmApp = {"Date", "Doctor", "Department", "Status", "Remarks"};
        String[][] rwApp = {
            {"04/15/2026", "Dr. Isabella Ramos", "General Medicine", "Completed", "Routine checkup"},
            {"03/28/2026", "Dr. Michael Santos", "Cardiology", "Completed", "BP monitoring"},
            {"03/10/2026", "Dr. Sarah Lopez", "Pediatrics", "Cancelled", "Patient rescheduled"},
            {"02/20/2026", "Dr. Isabella Ramos", "General Medicine", "Completed", "Follow-up"},
            {"01/25/2026", "Dr. John Cruz", "Emergency", "Completed", "Acute Bronchitis"}
        };

        JTable tblApp = new JTable(rwApp, clmApp);
        tblApp.setRowHeight(35);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblApp.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        JScrollPane scrTable = new JScrollPane(tblApp);
        scrTable.setBounds(20, 30, 940, 220);
        pnlTable.add(scrTable);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 560, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlAppHistory.add(lineFooter);

        JLabel lblFooter = new JLabel("Total Appointments: 5 | Completed: 4 | Cancelled: 1");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(350, 580, 400, 20);
        pnlAppHistory.add(lblFooter);
    }
}