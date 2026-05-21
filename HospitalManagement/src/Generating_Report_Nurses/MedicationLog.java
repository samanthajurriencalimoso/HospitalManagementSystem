package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class MedicationLog extends JPanel {
    
    private JPanel pnlMain,lineTop, lineFooter, pnlPatient, pnlTable;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact,lblTitle, lblRefNo, lblDate, lblPName, lblPID, lblRoom, lblNurse, lblFooter;
    private Image imgLG;
    private JTable tblMed;
    private JScrollPane scrTable,scrMedLog;
    private ImageIcon imgLogo;
    
    public MedicationLog() {
        setLayout(null);
        setBackground(Color.WHITE);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(11000, 570));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrMedLog = new JScrollPane(pnlMain);
        scrMedLog.setBounds(0, 0, 1020, 620);
        scrMedLog.setBorder(null);
        scrMedLog.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrMedLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrMedLog);

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

        lblTitle = new JLabel("MEDICATION ADMINISTRATION LOG");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setBounds(620, 35, 400, 30);
        pnlMain.add(lblTitle);

        lblRefNo = new JLabel("Log No.: MED-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlMain.add(lblRefNo);

        lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlMain.add(lblDate);

        lineTop = new JPanel();
        lineTop.setBounds(30, 130, 940, 2);
        lineTop.setBackground(neutralGray);
        pnlMain.add(lineTop);

        pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 940, 100);
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

        lblRoom = new JLabel("Room: 101");
        lblRoom.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRoom.setBounds(700, 30, 150, 20);
        pnlPatient.add(lblRoom);

        pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 270, 940, 200);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Medication Records"));
        pnlMain.add(pnlTable);

        String[] clmMed = {"Time", "Medicine", "Dosage", "Route", "Given By", "Status"};
        String[][] rwMed = {
            {"08:00 AM", "Paracetamol 500mg", "1 tablet", "Oral", "Nurse Angela", "Given"},
            {"12:00 PM", "Amoxicillin 250mg", "1 capsule", "Oral", "Nurse Angela", "Given"},
            {"04:00 PM", "Paracetamol 500mg", "1 tablet", "Oral", "Nurse Angela", "Pending"},
            {"08:00 PM", "Vitamin C", "500mg", "Oral", "Nurse Angela", "Scheduled"}
        };

        tblMed = new JTable(rwMed, clmMed);
        tblMed.setRowHeight(35);
        tblMed.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblMed.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        scrTable = new JScrollPane(tblMed);
        scrTable.setBounds(20, 30, 940, 240);
        pnlTable.add(scrTable);

        lineFooter = new JPanel();
        lineFooter.setBounds(30, 490, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblFooter = new JLabel("Total Medications: 4 | Given: 2 | Pending: 1 | Scheduled: 1");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(300, 600, 500, 20);
        pnlMain.add(lblFooter);

        lblNurse = new JLabel("Nurse Signature: ____________________");
        lblNurse.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNurse.setBounds(60, 510, 300, 20);
        pnlMain.add(lblNurse);
    }
}