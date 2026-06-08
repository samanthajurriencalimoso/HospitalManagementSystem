package Generating_Reports_Admin;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;

public class DiagnosisSummaryAdmin extends JPanel implements ActionListener {

    private JPanel pnlMain;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblRefNo, lblDate,
            lblPName, lblAge, lblGender, lblDiagnosis, lblSymptoms, lblTreatment, lblFollowUp, 
            lblFooter, lblStatus, lblDiagnosisValue, lblSymptomsValue, lblTreatmentValue, lblFollowUpValue;
    private JPanel lineTop, lineFooter, pnlPatient, pnlDiagnosisDetails;
    private Image imgLG;
    private ImageIcon imgLogo;
    private JButton btnSave, btnCopy, btnEdit;
    private String patientName = "Joshua Garcia";
    private String age = "29";
    private String gender = "Male";
    private String diagnosis = "Acute Bronchitis";
    private String symptoms = "Cough, fever, chest congestion, fatigue";
    private String treatment = "Antibiotics, rest, hydration, follow-up in 1 week";
    private String followUp = "April 22, 2026";
    private String status = "Pending";

    public DiagnosisSummaryAdmin() {
        setLayout(null);
        setBounds(0, 0, 1060, 750);
        setBackground(Color.WHITE);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBounds(0, 0, 1020, 700);
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(pnlMain);

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

        lblRefNo = new JLabel("Ref No.: DX-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlMain.add(lblRefNo);

        lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlMain.add(lblDate);
        
        lblStatus = new JLabel("Status: " + status);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        lblStatus.setForeground(orange);
        lblStatus.setBounds(740, 110, 200, 20);
        pnlMain.add(lblStatus);

        btnSave = new JButton("Save");
        btnSave.setBounds(830, 25, 80, 30);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(this);
        pnlMain.add(btnSave);
        
        btnCopy = new JButton("Copy");
        btnCopy.setBounds(920, 25, 80, 30);
        btnCopy.setBackground(mediumBlue);
        btnCopy.setForeground(Color.WHITE);
        btnCopy.addActionListener(this);
        pnlMain.add(btnCopy);

        lineTop = new JPanel();
        lineTop.setBounds(30, 130, 940, 2);
        lineTop.setBackground(neutralGray);
        pnlMain.add(lineTop);

        pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 940, 100);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlMain.add(pnlPatient);

        lblPName = new JLabel("Patient Name: " + patientName);
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        lblAge = new JLabel("Age: " + age);
        lblAge.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAge.setBounds(400, 30, 100, 20);
        pnlPatient.add(lblAge);

        lblGender = new JLabel("Gender: " + gender);
        lblGender.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblGender.setBounds(550, 30, 150, 20);
        pnlPatient.add(lblGender);
        
        btnEdit = new JButton("Edit");
        btnEdit.setBounds(800, 30, 100, 35);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(this);
        pnlPatient.add(btnEdit);

        pnlDiagnosisDetails = new JPanel();
        pnlDiagnosisDetails.setLayout(null);
        pnlDiagnosisDetails.setBounds(30, 270, 940, 220);
        pnlDiagnosisDetails.setBorder(BorderFactory.createTitledBorder("Diagnosis Details"));
        pnlMain.add(pnlDiagnosisDetails);

        lblDiagnosis = new JLabel("Primary Diagnosis:");
        lblDiagnosis.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDiagnosis.setBounds(25, 30, 150, 20);
        pnlDiagnosisDetails.add(lblDiagnosis);
        
        lblDiagnosisValue = new JLabel(diagnosis);
        lblDiagnosisValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDiagnosisValue.setBounds(180, 30, 400, 20);
        pnlDiagnosisDetails.add(lblDiagnosisValue);

        lblSymptoms = new JLabel("Symptoms:");
        lblSymptoms.setFont(new Font("Calibri", Font.BOLD, 14));
        lblSymptoms.setBounds(25, 60, 100, 20);
        pnlDiagnosisDetails.add(lblSymptoms);
        
        lblSymptomsValue = new JLabel(symptoms);
        lblSymptomsValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblSymptomsValue.setBounds(180, 60, 500, 20);
        pnlDiagnosisDetails.add(lblSymptomsValue);

        lblTreatment = new JLabel("Treatment Plan:");
        lblTreatment.setFont(new Font("Calibri", Font.BOLD, 14));
        lblTreatment.setBounds(25, 90, 120, 20);
        pnlDiagnosisDetails.add(lblTreatment);
        
        lblTreatmentValue = new JLabel(treatment);
        lblTreatmentValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblTreatmentValue.setBounds(180, 90, 600, 20);
        pnlDiagnosisDetails.add(lblTreatmentValue);

        lblFollowUp = new JLabel("Follow-up Date:");
        lblFollowUp.setFont(new Font("Calibri", Font.BOLD, 14));
        lblFollowUp.setBounds(25, 120, 120, 20);
        pnlDiagnosisDetails.add(lblFollowUp);
        
        lblFollowUpValue = new JLabel(followUp);
        lblFollowUpValue.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblFollowUpValue.setBounds(180, 120, 200, 20);
        pnlDiagnosisDetails.add(lblFollowUpValue);

        lineFooter = new JPanel();
        lineFooter.setBounds(30, 520, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblFooter = new JLabel("Diagnosed by: Dr. Isabella Ramos");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 14));
        lblFooter.setBounds(60, 540, 300, 20);
        pnlMain.add(lblFooter);
    }
    
    private void saveReport() {
        status = "Saved";
        lblStatus.setText("Status: Saved");
        lblStatus.setForeground(Green);
        JOptionPane.showMessageDialog(this, "Diagnosis Summary saved successfully!", "Save Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void copyReport() {
        String data = "=== ETRIAGE HOSPITAL DIAGNOSIS SUMMARY ===\n\n" +
            "Patient Name: " + patientName + "\nAge: " + age + "\nGender: " + gender + "\n\n" +
            "Primary Diagnosis: " + diagnosis + "\n" +
            "Symptoms: " + symptoms + "\n" +
            "Treatment Plan: " + treatment + "\n" +
            "Follow-up Date: " + followUp + "\n\n" +
            "Diagnosed by: Dr. Isabella Ramos";
        
        StringSelection ss = new StringSelection(data);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        JOptionPane.showMessageDialog(this, "Report copied to clipboard!", "Copy Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void editReport() {
        JTextField txtPatientName = new JTextField(patientName, 20);
        JTextField txtAge = new JTextField(age, 10);
        JComboBox<String> cmbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        cmbGender.setSelectedItem(gender);
        JTextField txtDiagnosis = new JTextField(diagnosis, 30);
        JTextField txtSymptoms = new JTextField(symptoms, 40);
        JTextField txtTreatment = new JTextField(treatment, 50);
        JTextField txtFollowUp = new JTextField(followUp, 20);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("Patient Name:")); panel.add(txtPatientName);
        panel.add(new JLabel("Age:")); panel.add(txtAge);
        panel.add(new JLabel("Gender:")); panel.add(cmbGender);
        panel.add(new JLabel("Diagnosis:")); panel.add(txtDiagnosis);
        panel.add(new JLabel("Symptoms:")); panel.add(txtSymptoms);
        panel.add(new JLabel("Treatment Plan:")); panel.add(txtTreatment);
        panel.add(new JLabel("Follow-up Date:")); panel.add(txtFollowUp);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Diagnosis Summary", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            patientName = txtPatientName.getText();
            age = txtAge.getText();
            gender = (String) cmbGender.getSelectedItem();
            diagnosis = txtDiagnosis.getText();
            symptoms = txtSymptoms.getText();
            treatment = txtTreatment.getText();
            followUp = txtFollowUp.getText();
            
            lblPName.setText("Patient Name: " + patientName);
            lblAge.setText("Age: " + age);
            lblGender.setText("Gender: " + gender);
            lblDiagnosisValue.setText(diagnosis);
            lblSymptomsValue.setText(symptoms);
            lblTreatmentValue.setText(treatment);
            lblFollowUpValue.setText(followUp);
            
            status = "Pending";
            lblStatus.setText("Status: Pending");
            lblStatus.setForeground(orange);
            
            JOptionPane.showMessageDialog(this, "Diagnosis Summary updated!");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) saveReport();
        else if (e.getSource() == btnCopy) copyReport();
        else if (e.getSource() == btnEdit) editReport();
    }
}