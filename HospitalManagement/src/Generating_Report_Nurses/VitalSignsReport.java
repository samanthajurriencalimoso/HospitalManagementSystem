package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class VitalSignsReport extends JPanel {
    
    public VitalSignsReport() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlVitals = new JPanel();
        pnlVitals.setLayout(null);
        pnlVitals.setPreferredSize(new Dimension(1040, 900));
        pnlVitals.setBackground(Color.WHITE);
        pnlVitals.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrVitals = new JScrollPane(pnlVitals);
        scrVitals.setBounds(0, 0, 1060, 650);
        scrVitals.setBorder(BorderFactory.createEmptyBorder());
        scrVitals.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrVitals);

       
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlVitals.add(lblLogo);

       
        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlVitals.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlVitals.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlVitals.add(lblContact);

        
        JLabel lblTitle = new JLabel("VITAL SIGNS REPORT");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(680, 35, 400, 30);
        pnlVitals.add(lblTitle);

        JLabel lblRefNo = new JLabel("Ref No.: VS-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlVitals.add(lblRefNo);

        JLabel lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlVitals.add(lblDate);

        
        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlVitals.add(lineTop);

       
        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 980, 80);
        pnlPatient.setBackground(Color.WHITE);
        pnlPatient.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(neutralGray),
                "Patient Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Calibri", Font.BOLD, 14)
        ));
        pnlVitals.add(pnlPatient);

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
        pnlTable.setBounds(30, 250, 980, 250);
        pnlTable.setBackground(Color.WHITE);
        pnlTable.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(neutralGray),
                "Vital Signs Record",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Calibri", Font.BOLD, 14)
        ));
        pnlVitals.add(pnlTable);

        String[] clmVitals = {"Time", "BP (mmHg)", "Heart Rate", "Temperature", "Oxygen Level", "Remarks"};
        String[][] rwVitals = {
            {"08:00 AM", "120/80", "78", "36.7°C", "98%", "Normal"},
            {"12:00 PM", "118/79", "82", "36.8°C", "97%", "Normal"},
            {"04:00 PM", "125/85", "90", "37.2°C", "96%", "Slightly elevated"},
            {"08:00 PM", "122/81", "85", "37.0°C", "97%", "Stable"}
        };

        JTable tblVitals = new JTable(rwVitals, clmVitals);
        tblVitals.setRowHeight(35);
        tblVitals.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblVitals.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        JScrollPane scrTable = new JScrollPane(tblVitals);
        scrTable.setBounds(20, 30, 940, 180);
        pnlTable.add(scrTable);

       
        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 530, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlVitals.add(lineFooter);

        JLabel lblFooter = new JLabel("Vital signs monitored by: Nurse Angela Cruz");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(60, 550, 400, 20);
        pnlVitals.add(lblFooter);
    }
}