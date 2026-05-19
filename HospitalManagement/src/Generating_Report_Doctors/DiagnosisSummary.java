package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DiagnosisSummary extends JPanel {
    
    public DiagnosisSummary() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlDiagnosis = new JPanel();
        pnlDiagnosis.setLayout(null);
        pnlDiagnosis.setPreferredSize(new Dimension(1040, 900));
        pnlDiagnosis.setBackground(Color.WHITE);
        pnlDiagnosis.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrDiagnosis = new JScrollPane(pnlDiagnosis);
        scrDiagnosis.setBounds(0, 0, 1060, 650);
        scrDiagnosis.setBorder(BorderFactory.createEmptyBorder());
        scrDiagnosis.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrDiagnosis);

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlDiagnosis.add(lblLogo);

        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlDiagnosis.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlDiagnosis.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlDiagnosis.add(lblContact);

        JLabel lblTitle = new JLabel("PATIENT DIAGNOSIS SUMMARY");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(680, 35, 400, 30);
        pnlDiagnosis.add(lblTitle);

        JLabel lblRefNo = new JLabel("Ref No.: DX-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlDiagnosis.add(lblRefNo);

        JLabel lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlDiagnosis.add(lblDate);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlDiagnosis.add(lineTop);

       
        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 980, 100);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlDiagnosis.add(pnlPatient);

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

       
        JPanel pnlDiagnosisDetails = new JPanel();
        pnlDiagnosisDetails.setLayout(null);
        pnlDiagnosisDetails.setBounds(30, 270, 980, 200);
        pnlDiagnosisDetails.setBorder(BorderFactory.createTitledBorder("Diagnosis Details"));
        pnlDiagnosis.add(pnlDiagnosisDetails);

        JLabel lblDiagnosis = new JLabel("Primary Diagnosis: Acute Bronchitis");
        lblDiagnosis.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDiagnosis.setBounds(25, 30, 400, 20);
        pnlDiagnosisDetails.add(lblDiagnosis);

        JLabel lblSymptoms = new JLabel("Symptoms: Cough, fever, chest congestion, fatigue");
        lblSymptoms.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblSymptoms.setBounds(25, 60, 500, 20);
        pnlDiagnosisDetails.add(lblSymptoms);

        JLabel lblTreatment = new JLabel("Treatment Plan: Antibiotics, rest, hydration, follow-up in 1 week");
        lblTreatment.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblTreatment.setBounds(25, 90, 600, 20);
        pnlDiagnosisDetails.add(lblTreatment);

        JLabel lblFollowUp = new JLabel("Follow-up Date: April 22, 2026");
        lblFollowUp.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblFollowUp.setBounds(25, 120, 300, 20);
        pnlDiagnosisDetails.add(lblFollowUp);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 500, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlDiagnosis.add(lineFooter);

        JLabel lblFooter = new JLabel("Diagnosed by: Dr. Isabella Ramos");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 14));
        lblFooter.setBounds(60, 520, 300, 20);
        pnlDiagnosis.add(lblFooter);
    }
}