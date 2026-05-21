package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;

public class DiagnosisSummary extends JPanel {

    private JPanel pnlMain;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblTitle, lblRefNo, lblDate,
            lblPName, lblAge, lblGender, lblDiagnosis, lblSymptoms, lblTreatment, lblFollowUp, lblFooter;
    private JPanel lineTop, lineFooter, pnlPatient, pnlDiagnosisDetails;
    private Image imgLG;
    private ImageIcon imgLogo;
    private JScrollPane scrDiagnosis;

    public DiagnosisSummary() {
        setLayout(null);
        setBackground(Color.WHITE);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1000, 570));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrDiagnosis = new JScrollPane(pnlMain);
        scrDiagnosis.setBounds(0, 0, 1020, 620);
        scrDiagnosis.setBorder(null);
        scrDiagnosis.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrDiagnosis.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrDiagnosis.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrDiagnosis);

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

        lblTitle = new JLabel("PATIENT DIAGNOSIS SUMMARY");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(620, 35, 400, 30);
        pnlMain.add(lblTitle);

        lblRefNo = new JLabel("Ref No.: DX-2026-001");
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

        lblAge = new JLabel("Age: 29");
        lblAge.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAge.setBounds(400, 30, 100, 20);
        pnlPatient.add(lblAge);

        lblGender = new JLabel("Gender: Male");
        lblGender.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblGender.setBounds(550, 30, 150, 20);
        pnlPatient.add(lblGender);

        pnlDiagnosisDetails = new JPanel();
        pnlDiagnosisDetails.setLayout(null);
        pnlDiagnosisDetails.setBounds(30, 270, 940, 200);
        pnlDiagnosisDetails.setBorder(BorderFactory.createTitledBorder("Diagnosis Details"));
        pnlMain.add(pnlDiagnosisDetails);

        lblDiagnosis = new JLabel("Primary Diagnosis: Acute Bronchitis");
        lblDiagnosis.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDiagnosis.setBounds(25, 30, 400, 20);
        pnlDiagnosisDetails.add(lblDiagnosis);

        lblSymptoms = new JLabel("Symptoms: Cough, fever, chest congestion, fatigue");
        lblSymptoms.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblSymptoms.setBounds(25, 60, 500, 20);
        pnlDiagnosisDetails.add(lblSymptoms);

        lblTreatment = new JLabel("Treatment Plan: Antibiotics, rest, hydration, follow-up in 1 week");
        lblTreatment.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblTreatment.setBounds(25, 90, 600, 20);
        pnlDiagnosisDetails.add(lblTreatment);

        lblFollowUp = new JLabel("Follow-up Date: April 22, 2026");
        lblFollowUp.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblFollowUp.setBounds(25, 120, 300, 20);
        pnlDiagnosisDetails.add(lblFollowUp);

        lineFooter = new JPanel();
        lineFooter.setBounds(30, 490, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblFooter = new JLabel("Diagnosed by: Dr. Isabella Ramos");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 14));
        lblFooter.setBounds(60, 510, 300, 20);
        pnlMain.add(lblFooter);
    }
}