package Prescriptions;

import static Color_Palette.ColorPalette.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class Prescription_Doctor extends JPanel implements ActionListener {

    private JPanel pnlMain, tabPrescription, tabPatients, tabMedicine, tabUpdate, pnlForm, pnlBottom;
    private JLabel lblTitle, lblDate, lblTPrescription, lblTPatients, lblTMedicine, lblPatient, lblDrug, lblDose, lblFreq, lblDur, lblTitle2, 
                   lblValue;
    private JTextField txtPat, txtDrug, txtFreq, txtDur;
    private JComboBox<String> cbDose;
    private JButton btnAdd, btnDelete, btnUpdate, btnSave;
    private JTable tblPres;
    private JTableHeader hdrtbl;
    private JScrollPane sp;
    private DefaultTableModel tblModel;
    private ArrayList<String[]> prescriptions = new ArrayList<>();
    private boolean isEditing = false;

    public Prescription_Doctor() {

        setLayout(null);
        setBounds(0, 0, 1620, 930);

        pnlMain = new JPanel(null);
        pnlMain.setBounds(0, 0, 1620, 930);
        pnlMain.setBackground(veryLightBlue);
        add(pnlMain);

        lblTitle = new JLabel("Prescription Dashboard");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lblTitle);

        lblDate = new JLabel("May 21, 2026 | 10:00 AM");
        lblDate.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDate.setForeground(Color.darkGray);
        lblDate.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDate);

        tabPatients = createTab("Total Patients", "0", mediumBlue);
        tabPatients.setBounds(30, 80, 500, 110);
        pnlMain.add(tabPatients);

        lblTPatients = (JLabel) tabPatients.getComponent(1);
        
        tabPrescription = createTab("Total Prescriptions", "0", darkBlue);
        tabPrescription.setBounds(550, 80, 500, 110);
        pnlMain.add(tabPrescription);

        lblTPrescription = (JLabel) tabPrescription.getComponent(1);

        pnlForm = new JPanel(null);
        pnlForm.setBounds(30, 220, 1560, 110);
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlForm);

        lblPatient = new JLabel("Patient:");
        lblPatient.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPatient.setBounds(20, 38, 100, 30);
        pnlForm.add(lblPatient);
        txtPat = new JTextField();
        txtPat.setBounds(100, 38, 180, 30);
        pnlForm.add(txtPat);

        lblDrug = new JLabel("Drug:");
        lblDrug.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDrug.setBounds(310, 38, 80, 30);
        pnlForm.add(lblDrug);
        txtDrug = new JTextField();
        txtDrug.setBounds(370, 38, 180, 30);
        pnlForm.add(txtDrug);

        lblDose = new JLabel("Dosage:");
        lblDose.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDose.setBounds(580, 38, 100, 30);
        pnlForm.add(lblDose);

        cbDose = new JComboBox<>(
                new String[]{"Select", "5mg", "10mg", "50mg","100mg","400mg","500mg"});
        cbDose.setBounds(660, 38, 140, 30);
        pnlForm.add(cbDose);
        
        lblFreq = new JLabel("Frequency:");
        lblFreq.setFont(new Font("Calibri", Font.BOLD, 18));
        lblFreq.setBounds(830, 38, 100, 30);
        pnlForm.add(lblFreq);
        txtFreq = new JTextField();
        txtFreq.setBounds(930, 38, 150, 30);
        pnlForm.add(txtFreq);

        lblDur = new JLabel("Duration:");
        lblDur.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDur.setBounds(1110, 38, 100, 30);
        pnlForm.add(lblDur);
        txtDur = new JTextField();
        txtDur.setBounds(1200, 38, 120, 30);
        pnlForm.add(txtDur);

        btnAdd = new JButton("Add Prescription");
        btnAdd.setBounds(1360, 32, 170, 40);
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);
        pnlForm.add(btnAdd);

        String[] cols = {"Patient", "Drug", "Dosage", "Frequency", "Duration"};

        tblModel = new DefaultTableModel(cols, 0);
        tblPres = new JTable(tblModel);
        tblPres.setRowHeight(35);
        tblPres.setFont(new Font("Calibri", Font.PLAIN, 16));
        hdrtbl = tblPres.getTableHeader();
        hdrtbl.setFont(new Font("Calibri", Font.BOLD, 18));
        hdrtbl.setBackground(lightBlue);
        sp = new JScrollPane(tblPres);
        sp.setBounds(30, 360, 1560, 470);
        sp.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(sp);
        
        tblPres.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && isEditing) {
                int data = tblPres.getSelectedRow();
                if (data >= 0) {
                    txtPat.setText(tblModel.getValueAt(data, 0).toString());
                    txtDrug.setText(tblModel.getValueAt(data, 1).toString());
                    cbDose.setSelectedItem(tblModel.getValueAt(data, 2).toString());
                    txtFreq.setText(tblModel.getValueAt(data, 3).toString().replace("x/day", "").trim());
                    txtDur.setText(tblModel.getValueAt(data, 4).toString().replace("days", "").trim());
                }
            }
        });

        pnlBottom = new JPanel(null);
        pnlBottom.setBounds(30, 850, 1560, 50);
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlBottom);

        btnSave = new JButton("Save");
        btnSave.setBounds(20, 10, 120, 30);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Calibri", Font.BOLD, 14));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> saveEdit());
        pnlBottom.add(btnSave);
        
        btnUpdate = new JButton("Edit");
        btnUpdate.setBounds(160, 10, 120, 30);
        btnUpdate.setBackground(mediumBlue);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Calibri", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(e -> { 
            int row = tblPres.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select a prescription row first!","No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            isEditing = true;
            txtPat.setText(tblModel.getValueAt(row, 0).toString());
            txtDrug.setText(tblModel.getValueAt(row, 1).toString());
            cbDose.setSelectedItem(tblModel.getValueAt(row, 2).toString());
            txtFreq.setText(tblModel.getValueAt(row, 3).toString().replace("x/day", "").trim());
            txtDur.setText(tblModel.getValueAt(row, 4).toString().replace("days", "").trim());
            JOptionPane.showMessageDialog(this, "Edit the fields then click Save.","Edit Mode", JOptionPane.INFORMATION_MESSAGE);
        });
        pnlBottom.add(btnUpdate);
        
        btnUpdate.addActionListener(e -> {
            int row = tblPres.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select a prescription row first!",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            isEditing = true;
            txtPat.setText(tblModel.getValueAt(row, 0).toString());
            txtDrug.setText(tblModel.getValueAt(row, 1).toString());
            cbDose.setSelectedItem(tblModel.getValueAt(row, 2).toString());
            txtFreq.setText(tblModel.getValueAt(row, 3).toString().replace("x/day", "").trim());
            txtDur.setText(tblModel.getValueAt(row, 4).toString().replace("days", "").trim());
                JOptionPane.showMessageDialog(this, "You can now edit the fields. Click Save to confirm.","Edit Mode", JOptionPane.INFORMATION_MESSAGE);
        });

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(300, 10, 120, 30);
        btnDelete.setBackground(LightRed);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(this);
        pnlBottom.add(btnDelete);
   
    }
    
    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel(null);
        tabUpdate.setBackground(color);
        
        lblTitle2 = new JLabel(title);
        lblTitle2.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle2.setForeground(Color.WHITE);
        lblTitle2.setBounds(20, 20, 300, 30);
        tabUpdate.add(lblTitle2);
        
        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 32));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 55, 200, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addPrescription();
        } else if (e.getSource() == btnDelete) {
            deletePrescription();
        } 
    }
    
    private void addPrescription() {
        String patient = txtPat.getText().trim();
        String drug = txtDrug.getText().trim();
        String freq = txtFreq.getText().trim();
        String duration = txtDur.getText().trim();
        
        if (patient.isEmpty() || drug.isEmpty() || freq.isEmpty() || duration.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled up!","Incomplete Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!patient.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Patient name must contain letters only!", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!drug.matches("[a-zA-Z\\- ]+")) {
            JOptionPane.showMessageDialog(this, "Drug name must contain letters only!","Invalid Drug", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (cbDose.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a dosage!","No Dosage Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int freqVal;
        try {
            freqVal = Integer.parseInt(freq);
            if (freqVal <= 0) {
                JOptionPane.showMessageDialog(this, "Frequency must be a positive number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Frequency must be a valid number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int durVal;
        try {
            durVal = Integer.parseInt(duration);
            if (durVal <= 0) {
                JOptionPane.showMessageDialog(this, "Duration must be a positive number!", "Invalid Duration", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duration must be a valid number!", "Invalid Duration", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            if (tblModel.getValueAt(i, 0).toString().equalsIgnoreCase(patient) &&
                tblModel.getValueAt(i, 1).toString().equalsIgnoreCase(drug)) {
                JOptionPane.showMessageDialog(this,
                    patient + " already has a prescription for " + drug + "!",
                    "Duplicate Prescription", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String[] row = {patient, drug, cbDose.getSelectedItem().toString(), freqVal + "x/day", durVal + " days"};
        prescriptions.add(row);
        tblModel.addRow(row);


        updateSummary();
        clearFields();
        JOptionPane.showMessageDialog(this, "Prescription added!");
    }

    private void deletePrescription() {
        int row = tblPres.getSelectedRow();
        
        if (row >= 0) {
            tblModel.removeRow(row);
            prescriptions.remove(row);
            updateSummary();

            JOptionPane.showMessageDialog(this, "Prescription deleted!");
        } else {
            JOptionPane.showMessageDialog(this,"Select prescription first!");
        } 
        
        clearFields();
    }
    
    private void saveEdit() {
        if (!isEditing) {
            JOptionPane.showMessageDialog(this, "Click Edit on a row first!","Not in Edit Mode", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = tblPres.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "No row selected!","No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String patient  = txtPat.getText().trim();
        String drug     = txtDrug.getText().trim();
        String freq     = txtFreq.getText().trim();
        String duration = txtDur.getText().trim();

        if (patient.isEmpty() || drug.isEmpty() || freq.isEmpty() || duration.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled up!", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!patient.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Patient name must contain letters only!", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!drug.matches("[a-zA-Z\\- ]+")) {
            JOptionPane.showMessageDialog(this, "Drug name must contain letters only!", "Invalid Drug", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cbDose.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a dosage!", "No Dosage Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int freqVal;
        try {
            freqVal = Integer.parseInt(freq);
            if (freqVal <= 0) { 
                JOptionPane.showMessageDialog(this, "Frequency must be a positive number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE); return; }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Frequency must be a valid number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE); return;
        }

        int durVal;
        try {
            durVal = Integer.parseInt(duration);
            if (durVal <= 0) { 
                JOptionPane.showMessageDialog(this, "Duration must be a positive number!", "Invalid Duration", JOptionPane.WARNING_MESSAGE); return; }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duration must be a valid number!", "Invalid Duration", JOptionPane.WARNING_MESSAGE); return;
        }

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            if (i == row) continue;
            if (tblModel.getValueAt(i, 0).toString().equalsIgnoreCase(patient) &&
                tblModel.getValueAt(i, 1).toString().equalsIgnoreCase(drug)) {
                JOptionPane.showMessageDialog(this, patient + " already has a prescription for " + drug + "!","Duplicate Prescription", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        tblModel.setValueAt(patient, row, 0);
        tblModel.setValueAt(drug, row, 1);
        tblModel.setValueAt(cbDose.getSelectedItem(),row, 2);
        tblModel.setValueAt(freqVal + "x/day", row, 3);
        tblModel.setValueAt(durVal  + " days", row, 4);

        isEditing = false;
        updateSummary();
        clearFields();
        JOptionPane.showMessageDialog(this, "Prescription updated!");
    }

    private void updateSummary() {
        int totalPrescription = tblModel.getRowCount();

        java.util.HashSet<String> uniquePatients = new java.util.HashSet<>();
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            uniquePatients.add(tblModel.getValueAt(i, 0).toString());
        }
            lblTPrescription.setText(String.valueOf(totalPrescription));
            lblTPatients.setText(String.valueOf(uniquePatients.size()));
    }

    private void clearFields() {
        txtPat.setText("");
        txtDrug.setText("");
        txtFreq.setText("");
        txtDur.setText("");
        cbDose.setSelectedIndex(0);
    }
}