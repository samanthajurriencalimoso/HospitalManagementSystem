package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PrescriptionReport extends JPanel {
    
    public PrescriptionReport() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlPrescription = new JPanel();
        pnlPrescription.setLayout(null);
        pnlPrescription.setPreferredSize(new Dimension(1040, 900));
        pnlPrescription.setBackground(Color.WHITE);
        pnlPrescription.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrPrescription = new JScrollPane(pnlPrescription);
        scrPrescription.setBounds(0, 0, 1060, 650);
        scrPrescription.setBorder(BorderFactory.createEmptyBorder());
        scrPrescription.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrPrescription.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrPrescription);

     
        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlPrescription.add(lblLogo);

        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlPrescription.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlPrescription.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlPrescription.add(lblContact);

        JLabel lblTitle = new JLabel("PRESCRIPTION REPORT");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(740, 35, 300, 30);
        pnlPrescription.add(lblTitle);

        JLabel lblRxNo = new JLabel("RX No.: RX-2026-001");
        lblRxNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRxNo.setBounds(740, 70, 200, 20);
        pnlPrescription.add(lblRxNo);

        JLabel lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlPrescription.add(lblDate);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlPrescription.add(lineTop);

       
        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 980, 100);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlPrescription.add(pnlPatient);

        JLabel lblPName = new JLabel("Patient Name: Joshua Garcia");
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        JLabel lblAge = new JLabel("Age: 29");
        lblAge.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAge.setBounds(400, 30, 100, 20);
        pnlPatient.add(lblAge);

        JLabel lblGender = new JLabel("Gender: Male");
        lblGender.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblGender.setBounds(550, 30, 150, 20);
        pnlPatient.add(lblGender);

        JLabel lblDoctor = new JLabel("Doctor: Dr. Isabella Ramos");
        lblDoctor.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDoctor.setBounds(25, 60, 300, 20);
        pnlPatient.add(lblDoctor);

        
        JPanel pnlMeds = new JPanel();
        pnlMeds.setLayout(null);
        pnlMeds.setBounds(30, 270, 980, 250);
        pnlMeds.setBorder(BorderFactory.createTitledBorder("Prescribed Medications"));
        pnlPrescription.add(pnlMeds);

        String[] clmMeds = {"Medicine", "Dosage", "Frequency", "Duration", "Instructions"};
        String[][] rwMeds = {
            {"Paracetamol 500mg", "1 tablet", "Every 6 hours", "5 days", "Take after meals"},
            {"Amoxicillin 250mg", "1 capsule", "Every 8 hours", "7 days", "Complete full course"},
            {"Vitamin C", "500mg", "Once daily", "30 days", "Take with food"}
        };

        JTable tblMeds = new JTable(rwMeds, clmMeds);
        tblMeds.setRowHeight(35);
        tblMeds.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblMeds.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        JScrollPane scrMeds = new JScrollPane(tblMeds);
        scrMeds.setBounds(20, 30, 940, 180);
        pnlMeds.add(scrMeds);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 650, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlPrescription.add(lineFooter);

        JLabel lblPrepared = new JLabel("Prescribed by: Dr. Isabella Ramos");
        lblPrepared.setFont(new Font("Calibri", Font.ITALIC, 14));
        lblPrepared.setBounds(60, 670, 300, 20);
        pnlPrescription.add(lblPrepared);

        JLabel lblFooter = new JLabel("This prescription is valid for 30 days from the date issued.", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 11));
        lblFooter.setBounds(280, 700, 500, 20);
        pnlPrescription.add(lblFooter);
    }
}