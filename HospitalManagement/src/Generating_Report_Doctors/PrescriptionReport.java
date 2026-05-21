package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;

public class PrescriptionReport extends JPanel {
    
    private JPanel pnlMain;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact,
            lblPatientName, lblPatientNameValue, lblPatientAddress, lblPatientAddressValue,
            lblAge, lblAgeValue, lblGender, lblGenderValue, lblDate, lblDateValue, 
            lblDoctor, lblDoctorValue, lblDiagnosis, lblDiagnosisValue, 
            lblMed1, lblForm1, lblQty1, lblSig1, lblRefill1,
            lblMed2, lblForm2, lblQty2, lblSig2, lblRefill2,
            lblMed3, lblForm3, lblQty3, lblSig3, lblRefill3,
            lblPrescriberName, lblPrescriberLicense, lblPrescriberPTR,
            lblSignature, lblValidity;
    private JPanel lineTop, lineMeds, lineDA, lineFooter, lineSignature;
    private JScrollPane scrPrescription;
    private ImageIcon imgLogo;
    private Image imgLG;
    
    public PrescriptionReport() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1010, 780));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrPrescription = new JScrollPane(pnlMain);
        scrPrescription.setBounds(0, 0, 1060, 650);
        scrPrescription.setBorder(BorderFactory.createEmptyBorder());
        scrPrescription.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrPrescription.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrPrescription.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrPrescription);

        imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        imgLG = imgLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(imgLG));
        lblLogo.setBounds(30, 25, 80, 80);
        pnlMain.add(lblLogo);

        lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(130, 30, 350, 25);
        pnlMain.add(lblHospital);

        lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblAddress.setBounds(130, 55, 400, 18);
        pnlMain.add(lblAddress);

        lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblContact.setBounds(130, 73, 300, 18);
        pnlMain.add(lblContact);

        lineTop = new JPanel();
        lineTop.setBounds(30, 120, 960, 2);
        lineTop.setBackground(neutralGray);
        pnlMain.add(lineTop);

        lblPatientName = new JLabel("Patient Name:");
        lblPatientName.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPatientName.setBounds(30, 145, 110, 25);
        pnlMain.add(lblPatientName);

        lblPatientNameValue = new JLabel("GARCIA, JOSHUA S.");
        lblPatientNameValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPatientNameValue.setBounds(145, 145, 250, 25);
        pnlMain.add(lblPatientNameValue);

        lblPatientAddress = new JLabel("Address:");
        lblPatientAddress.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPatientAddress.setBounds(30, 175, 70, 25);
        pnlMain.add(lblPatientAddress);

        lblPatientAddressValue = new JLabel("45 San Pedro St., Barangay 3, Wellness City");
        lblPatientAddressValue.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblPatientAddressValue.setBounds(105, 175, 400, 25);
        pnlMain.add(lblPatientAddressValue);

        lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Calibri", Font.BOLD, 14));
        lblAge.setBounds(550, 145, 40, 25);
        pnlMain.add(lblAge);

        lblAgeValue = new JLabel("29");
        lblAgeValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAgeValue.setBounds(595, 145, 50, 25);
        pnlMain.add(lblAgeValue);

        lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Calibri", Font.BOLD, 14));
        lblGender.setBounds(660, 145, 60, 25);
        pnlMain.add(lblGender);

        lblGenderValue = new JLabel("Male");
        lblGenderValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblGenderValue.setBounds(725, 145, 80, 25);
        pnlMain.add(lblGenderValue);

        lblDate = new JLabel("Date:");
        lblDate.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDate.setBounds(820, 145, 45, 25);
        pnlMain.add(lblDate);

        lblDateValue = new JLabel("April 15, 2026");
        lblDateValue.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblDateValue.setBounds(870, 145, 120, 25);
        pnlMain.add(lblDateValue);

        lblDoctor = new JLabel("Prescribing Physician:");
        lblDoctor.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDoctor.setBounds(30, 210, 155, 25);
        pnlMain.add(lblDoctor);

        lblDoctorValue = new JLabel("Dr. Isabella Ramos, MD");
        lblDoctorValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDoctorValue.setBounds(190, 210, 250, 25);
        pnlMain.add(lblDoctorValue);

        lblDiagnosis = new JLabel("Clinical Diagnosis:");
        lblDiagnosis.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDiagnosis.setBounds(30, 245, 125, 25);
        pnlMain.add(lblDiagnosis);

        lblDiagnosisValue = new JLabel("Acute Bronchitis (J20.9)");
        lblDiagnosisValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDiagnosisValue.setBounds(160, 245, 300, 25);
        pnlMain.add(lblDiagnosisValue);

        lineMeds = new JPanel();
        lineMeds.setBounds(30, 285, 960, 2);
        lineMeds.setBackground(neutralGray);
        pnlMain.add(lineMeds);

        lblMed1 = new JLabel("1. Paracetamol");
        lblMed1.setFont(new Font("Calibri", Font.BOLD, 13));
        lblMed1.setBounds(30, 305, 200, 25);
        pnlMain.add(lblMed1);

        lblForm1 = new JLabel("(Acetaminophen) 500mg tablet");
        lblForm1.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblForm1.setBounds(50, 325, 220, 20);
        pnlMain.add(lblForm1);

        lblQty1 = new JLabel("Disp: 10 tablets");
        lblQty1.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblQty1.setBounds(300, 305, 120, 20);
        pnlMain.add(lblQty1);

        lblSig1 = new JLabel("Sig: 1 tablet every 6 hours as needed for fever or pain");
        lblSig1.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblSig1.setBounds(50, 345, 500, 20);
        pnlMain.add(lblSig1);

        lblRefill1 = new JLabel("Refill: 0");
        lblRefill1.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblRefill1.setBounds(300, 325, 100, 20);
        pnlMain.add(lblRefill1);

        lblMed2 = new JLabel("2. Amoxicillin");
        lblMed2.setFont(new Font("Calibri", Font.BOLD, 13));
        lblMed2.setBounds(30, 385, 200, 25);
        pnlMain.add(lblMed2);

        lblForm2 = new JLabel("250mg capsule");
        lblForm2.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblForm2.setBounds(50, 405, 150, 20);
        pnlMain.add(lblForm2);

        lblQty2 = new JLabel("Disp: 21 capsules");
        lblQty2.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblQty2.setBounds(300, 385, 120, 20);
        pnlMain.add(lblQty2);

        lblSig2 = new JLabel("Sig: 1 capsule every 8 hours for 7 days");
        lblSig2.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblSig2.setBounds(50, 425, 400, 20);
        pnlMain.add(lblSig2);

        lblRefill2 = new JLabel("Refill: 0");
        lblRefill2.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblRefill2.setBounds(300, 405, 100, 20);
        pnlMain.add(lblRefill2);

        lblMed3 = new JLabel("3. Ascorbic Acid");
        lblMed3.setFont(new Font("Calibri", Font.BOLD, 13));
        lblMed3.setBounds(30, 465, 200, 25);
        pnlMain.add(lblMed3);

        lblForm3 = new JLabel("(Vitamin C) 500mg tablet");
        lblForm3.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblForm3.setBounds(50, 485, 200, 20);
        pnlMain.add(lblForm3);

        lblQty3 = new JLabel("Disp: 30 tablets");
        lblQty3.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblQty3.setBounds(300, 465, 120, 20);
        pnlMain.add(lblQty3);

        lblSig3 = new JLabel("Sig: 1 tablet once daily");
        lblSig3.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblSig3.setBounds(50, 505, 300, 20);
        pnlMain.add(lblSig3);

        lblRefill3 = new JLabel("Refill: 2");
        lblRefill3.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblRefill3.setBounds(300, 485, 100, 20);
        pnlMain.add(lblRefill3);

        lineDA = new JPanel();
        lineDA.setBounds(30, 545, 960, 2);
        lineDA.setBackground(neutralGray);
        pnlMain.add(lineDA);
    }
}