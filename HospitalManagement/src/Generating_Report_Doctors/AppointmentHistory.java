package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;

public class AppointmentHistory extends JPanel {

    private JPanel pnlMain, lineTop, lineFooter, pnlPatient, pnlTable;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblTitle, lblRefNo, lblDate,
            lblPName, lblPID, lblFooter;
    private Image imgLG;
    private JTable tblApp;
    private JScrollPane scrTable, scrAppHistory;
    private ImageIcon imgLogo;

    public AppointmentHistory() {
        setLayout(null);
        setBackground(Color.WHITE);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1000, 630));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrAppHistory = new JScrollPane(pnlMain);
        scrAppHistory.setBounds(0, 0, 1020, 680);
        scrAppHistory.setBorder(null);
        scrAppHistory.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrAppHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrAppHistory.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrAppHistory);

        imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        imgLG = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(imgLG));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlMain.add(lblLogo);

        lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlMain.add(lblHospital);

        lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlMain.add(lblAddress);

        lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlMain.add(lblContact);

        lblTitle = new JLabel("APPOINTMENT HISTORY");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(680, 35, 400, 30);
        pnlMain.add(lblTitle);

        lblRefNo = new JLabel("Ref No.: AP-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlMain.add(lblRefNo);

        lblDate = new JLabel("Generated: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlMain.add(lblDate);

        lineTop = new JPanel();
        lineTop.setBounds(30, 130, 940, 2);
        lineTop.setBackground(neutralGray);
        pnlMain.add(lineTop);

        pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 940, 80);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlMain.add(pnlPatient);

        lblPName = new JLabel("Patient Name: Joshua Garcia");
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        lblPID = new JLabel("Patient ID: P-10234");
        lblPID.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPID.setBounds(400, 30, 200, 20);
        pnlPatient.add(lblPID);

        pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 250, 940, 280);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Appointment Records"));
        pnlMain.add(pnlTable);

        String[] clmApp = {"Date", "Doctor", "Department", "Status", "Remarks"};
        String[][] rwApp = {
            {"04/15/2026", "Dr. Isabella Ramos", "General Medicine", "Completed", "Routine checkup"},
            {"03/28/2026", "Dr. Michael Santos", "Cardiology", "Completed", "BP monitoring"},
            {"03/10/2026", "Dr. Sarah Lopez", "Pediatrics", "Cancelled", "Patient rescheduled"},
            {"02/20/2026", "Dr. Isabella Ramos", "General Medicine", "Completed", "Follow-up"},
            {"01/25/2026", "Dr. John Cruz", "Emergency", "Completed", "Acute Bronchitis"}
        };

        tblApp = new JTable(rwApp, clmApp);
        tblApp.setRowHeight(35);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblApp.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        scrTable = new JScrollPane(tblApp);
        scrTable.setBounds(20, 30, 900, 220);
        pnlTable.add(scrTable);

        lineFooter = new JPanel();
        lineFooter.setBounds(30, 550, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblFooter = new JLabel("Total Appointments: 5 | Completed: 4 | Cancelled: 1");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(300, 570, 400, 20);
        pnlMain.add(lblFooter);
    }
}