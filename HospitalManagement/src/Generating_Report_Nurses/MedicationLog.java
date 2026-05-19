package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class MedicationLog extends JPanel {
    
    public MedicationLog() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlMedLog = new JPanel();
        pnlMedLog.setLayout(null);
        pnlMedLog.setPreferredSize(new Dimension(1040, 900));
        pnlMedLog.setBackground(Color.WHITE);
        pnlMedLog.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrMedLog = new JScrollPane(pnlMedLog);
        scrMedLog.setBounds(0, 0, 1060, 650);
        scrMedLog.setBorder(BorderFactory.createEmptyBorder());
        scrMedLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrMedLog);

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlMedLog.add(lblLogo);

        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlMedLog.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlMedLog.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlMedLog.add(lblContact);

        JLabel lblTitle = new JLabel("MEDICATION ADMINISTRATION LOG");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setBounds(660, 35, 420, 30);
        pnlMedLog.add(lblTitle);

        JLabel lblRefNo = new JLabel("Log No.: MED-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlMedLog.add(lblRefNo);

        JLabel lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlMedLog.add(lblDate);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlMedLog.add(lineTop);

        
        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 980, 80);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlMedLog.add(pnlPatient);

        JLabel lblPName = new JLabel("Patient Name: Joshua Garcia");
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        JLabel lblPID = new JLabel("Patient ID: P-10234");
        lblPID.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPID.setBounds(400, 30, 200, 20);
        pnlPatient.add(lblPID);

        JLabel lblRoom = new JLabel("Room: 101");
        lblRoom.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRoom.setBounds(700, 30, 150, 20);
        pnlPatient.add(lblRoom);

        
        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 250, 980, 300);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Medication Records"));
        pnlMedLog.add(pnlTable);

        String[] clmMed = {"Time", "Medicine", "Dosage", "Route", "Given By", "Status"};
        String[][] rwMed = {
            {"08:00 AM", "Paracetamol 500mg", "1 tablet", "Oral", "Nurse Angela", "Given"},
            {"12:00 PM", "Amoxicillin 250mg", "1 capsule", "Oral", "Nurse Angela", "Given"},
            {"04:00 PM", "Paracetamol 500mg", "1 tablet", "Oral", "Nurse Angela", "Pending"},
            {"08:00 PM", "Vitamin C", "500mg", "Oral", "Nurse Angela", "Scheduled"}
        };

        JTable tblMed = new JTable(rwMed, clmMed);
        tblMed.setRowHeight(35);
        tblMed.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblMed.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        JScrollPane scrTable = new JScrollPane(tblMed);
        scrTable.setBounds(20, 30, 940, 240);
        pnlTable.add(scrTable);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 580, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlMedLog.add(lineFooter);

        JLabel lblFooter = new JLabel("Total Medications: 4 | Given: 2 | Pending: 1 | Scheduled: 1");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(300, 600, 500, 20);
        pnlMedLog.add(lblFooter);

        JLabel lblNurse = new JLabel("Nurse Signature: ____________________");
        lblNurse.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNurse.setBounds(60, 630, 300, 20);
        pnlMedLog.add(lblNurse);
    }
}