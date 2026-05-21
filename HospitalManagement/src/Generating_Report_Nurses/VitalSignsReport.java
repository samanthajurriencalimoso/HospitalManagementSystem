package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class VitalSignsReport extends JPanel {
    
    private JPanel pnlMain,lineTop, lineFooter, pnlPatient, pnlTable;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblTitle, lblRefNo, lblDate,lblPName, lblPID, lblRoom, lblFooter;
    private Image imgLG;
    private JTable tblVitals;
    private JScrollPane scrTable,scrVitals;
    private ImageIcon imgLogo;
    
    public VitalSignsReport() {
        setLayout(null);
        setBackground(Color.WHITE);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1000, 570));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrVitals = new JScrollPane(pnlMain);
        scrVitals.setBounds(0, 0, 1020, 620);
        scrVitals.setBorder(null);
        scrVitals.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrVitals.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrVitals);

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

        lblTitle = new JLabel("VITAL SIGNS REPORT");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(620, 35, 400, 30);
        pnlMain.add(lblTitle);

        lblRefNo = new JLabel("Ref No.: VS-2026-001");
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
        pnlTable.setBorder(BorderFactory.createTitledBorder("Vital Signs Record"));
        pnlMain.add(pnlTable);

        String[] clmVitals = {"Time", "BP (mmHg)", "Heart Rate", "Temperature", "Oxygen Level", "Remarks"};
        String[][] rwVitals = {
            {"08:00 AM", "120/80", "78", "36.7째C", "98%", "Normal"},
            {"12:00 PM", "118/79", "82", "36.8째C", "97%", "Normal"},
            {"04:00 PM", "125/85", "90", "37.2째C", "96%", "Slightly elevated"},
            {"08:00 PM", "122/81", "85", "37.0째C", "97%", "Stable"}
        };

        tblVitals = new JTable(rwVitals, clmVitals);
        tblVitals.setRowHeight(35);
        tblVitals.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblVitals.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        scrTable = new JScrollPane(tblVitals);
        scrTable.setBounds(20, 30, 940, 180);
        pnlTable.add(scrTable);

        lineFooter = new JPanel();
        lineFooter.setBounds(30, 490, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblFooter = new JLabel("Vital signs monitored by: Nurse Angela Cruz");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(60, 510, 300, 20);
        pnlMain.add(lblFooter);
    }
}