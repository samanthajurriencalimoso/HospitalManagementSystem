package Prescriptions;

import static Color_Palette.ColorPalette.*;
import Database.PrescriptionSQL;
import Database.PatientManagementSQL;
import Models.Prescription;
import Models.Patient;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class Prescription_Doctor extends JPanel implements ActionListener {

    private JPanel pnlMain, tabPrescription, tabPatients, tabUpdate, pnlForm, pnlBottom;
    private JLabel lblTitle, lblDT, lblTPrescription, lblTPatients, lblPatient, lblDrug, lblDose, lblFreq, lblDur, lblTitle2, lblValue;
    private JTextField txtDrug, txtFreq, txtDur;
    private JComboBox<String> cbPat, cbDose; 
    private JButton btnAdd, btnDelete, btnUpdate, btnSave;
    private JTable tblPres;
    private JTableHeader hdrtbl;
    private JScrollPane sp;
    private DefaultTableModel tblModel;
    private ArrayList<Integer> dbIds = new ArrayList<>();
    private ArrayList<String> patientIds = new ArrayList<>(); 
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

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

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
        
        cbPat = new JComboBox<>();
        cbPat.setBounds(100, 38, 200, 30);
        pnlForm.add(cbPat);

        lblDrug = new JLabel("Drug:");
        lblDrug.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDrug.setBounds(320, 38, 80, 30);
        pnlForm.add(lblDrug);
        txtDrug = new JTextField();
        txtDrug.setBounds(380, 38, 180, 30);
        pnlForm.add(txtDrug);

        lblDose = new JLabel("Dosage:");
        lblDose.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDose.setBounds(580, 38, 100, 30);
        pnlForm.add(lblDose);

        cbDose = new JComboBox<>(new String[]{"Select", "5mg", "10mg", "50mg","100mg","400mg","500mg"});
        cbDose.setBounds(660, 38, 140, 30);
        pnlForm.add(cbDose);
        
        lblFreq = new JLabel("Frequency:");
        lblFreq.setFont(new Font("Calibri", Font.BOLD, 18));
        lblFreq.setBounds(820, 38, 100, 30);
        pnlForm.add(lblFreq);
        txtFreq = new JTextField();
        txtFreq.setBounds(920, 38, 150, 30);
        pnlForm.add(txtFreq);

        lblDur = new JLabel("Duration:");
        lblDur.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDur.setBounds(1100, 38, 100, 30);
        pnlForm.add(lblDur);
        txtDur = new JTextField();
        txtDur.setBounds(1190, 38, 120, 30);
        pnlForm.add(txtDur);

        btnAdd = new JButton("Add Prescription");
        btnAdd.setBounds(1360, 32, 170, 40);
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);
        pnlForm.add(btnAdd);

        String[] cols = {"Patient ID", "Patient Name", "Drug", "Dosage", "Frequency", "Duration"};
        tblModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        
        tblPres = new JTable(tblModel);
        tblPres.setRowHeight(35);
        tblPres.setFont(new Font("Calibri", Font.PLAIN, 16)); 
        tblPres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        hdrtbl = tblPres.getTableHeader();
        hdrtbl.setFont(new Font("Calibri", Font.BOLD, 18));
        hdrtbl.setBackground(lightBlue);
        
        sp = new JScrollPane(tblPres);
        sp.setBounds(30, 360, 1560, 470);
        sp.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(sp);
        
        tblPres.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int data = tblPres.getSelectedRow();
                if (data >= 0) {
                    String rowPid = patientIds.get(data);
                    String rowPname = tblModel.getValueAt(data, 1).toString();
                    setSelectedPatientInComboBox(rowPid, rowPname);
                    
                    txtDrug.setText(tblModel.getValueAt(data, 2).toString());
                    cbDose.setSelectedItem(tblModel.getValueAt(data, 3).toString());
                    txtFreq.setText(tblModel.getValueAt(data, 4).toString().replace("x/day", "").trim());
                    txtDur.setText(tblModel.getValueAt(data, 5).toString().replace("days", "").trim());
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
                JOptionPane.showMessageDialog(this, "Select a prescription row first!", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            isEditing = true;
            String rowPid = patientIds.get(row);
            String rowPname = tblModel.getValueAt(row, 1).toString();
            setSelectedPatientInComboBox(rowPid, rowPname);

            txtDrug.setText(tblModel.getValueAt(row, 2).toString());
            cbDose.setSelectedItem(tblModel.getValueAt(row, 3).toString());
            txtFreq.setText(tblModel.getValueAt(row, 4).toString().replace("x/day", "").trim());
            txtDur.setText(tblModel.getValueAt(row, 5).toString().replace("days", "").trim());
            JOptionPane.showMessageDialog(this, "Edit the fields then click Save.", "Edit Mode", JOptionPane.INFORMATION_MESSAGE);
        });
        pnlBottom.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(300, 10, 120, 30);
        btnDelete.setBackground(LightRed);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(this);
        pnlBottom.add(btnDelete);
        
        populatePatientComboBox();
        loadDataFromDatabase();
    }
    
    private void populatePatientComboBox() {
        cbPat.removeAllItems();
        cbPat.addItem("Select Patient");
        List<Patient> list = PatientManagementSQL.getAllPatients();
        for (Patient p : list) {
            cbPat.addItem(p.getId() + " - " + p.getName());
        }
    }
    
    private void setSelectedPatientInComboBox(String id, String name) {
        String matchTarget = id + " - " + name;
        for (int i = 0; i < cbPat.getItemCount(); i++) {
            if (cbPat.getItemAt(i).equalsIgnoreCase(matchTarget)) {
                cbPat.setSelectedIndex(i);
                return;
            }
        }
    }

    private void loadDataFromDatabase() {
        tblModel.setRowCount(0);
        dbIds.clear();
        patientIds.clear();
        List<Prescription> items = PrescriptionSQL.getAllPrescriptions();
        for (Prescription p : items) {
            dbIds.add(p.getPrescriptionId());
            patientIds.add(p.getPatientId());
            tblModel.addRow(new Object[]{p.getPatientId(), p.getPatientName(), p.getDrugName(), p.getDosage(), p.getFrequency() + "x/day", p.getDuration() + " days"});
        }
        updateSummary();
    }

    private JPanel createTab(String title, String value, Color color) {
        JPanel tab = new JPanel(null);
        tab.setBackground(color);
        
        JLabel lblTitle2 = new JLabel(title);
        lblTitle2.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle2.setForeground(Color.WHITE);
        lblTitle2.setBounds(20, 20, 300, 30);
        tab.add(lblTitle2);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 32));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 55, 200, 40);
        tab.add(lblValue);
        
        return tab;
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
        String drug = txtDrug.getText().trim();
        String freq = txtFreq.getText().trim();
        String duration = txtDur.getText().trim();
        
        if (cbPat.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Please select an applicable patient target profile!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selectedItem = cbPat.getSelectedItem().toString();
        String[] parts = selectedItem.split(" - ", 2);
        String patientId = parts[0];
        String patientName = parts[1];

        if (validateInputs(patientId, drug, freq, duration, -1)) {
            int freqVal = Integer.parseInt(freq);
            int durVal = Integer.parseInt(duration);
            String dosage = cbDose.getSelectedItem().toString();

            Prescription target = new Prescription(0, patientId, patientName, drug, dosage, freqVal, durVal, "Pending");
            if (PrescriptionSQL.addPrescription(target)) {
                loadDataFromDatabase();
                clearFields();
                JOptionPane.showMessageDialog(this, "Prescription added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Database entry write operation failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deletePrescription() {
        int row = tblPres.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Completely remove selected prescription entry?", "Confirm Action", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int dbId = dbIds.get(row);
                if (PrescriptionSQL.deletePrescription(dbId)) {
                    loadDataFromDatabase();
                    clearFields();
                    isEditing = false;
                    JOptionPane.showMessageDialog(this, "Prescription dropped from base records system.");
                } else {
                    JOptionPane.showMessageDialog(this, "Database rejection encountered on delete query statement execution.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select target row context from elements map space matrix layout first.", "No Selection", JOptionPane.WARNING_MESSAGE);
        } 
    }
    
    private void saveEdit() {
        if (!isEditing) {
            JOptionPane.showMessageDialog(this, "Activate layout selection modification by clicking the Edit action button first.", "Not Editing", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = tblPres.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Target element structural lookup indexing track broken.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cbPat.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Please select a valid patient context target.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selectedItem = cbPat.getSelectedItem().toString();
        String[] parts = selectedItem.split(" - ", 2);
        String patientId = parts[0];
        String patientName = parts[1];

        String drug     = txtDrug.getText().trim();
        String freq     = txtFreq.getText().trim();
        String duration = txtDur.getText().trim();

        if (validateInputs(patientId, drug, freq, duration, row)) {
            int targetId = dbIds.get(row);
            int freqVal = Integer.parseInt(freq);
            int durVal = Integer.parseInt(duration);
            String dosage = cbDose.getSelectedItem().toString();

            Prescription updatePackage = new Prescription(targetId, patientId, patientName, drug, dosage, freqVal, durVal, "Pending");
            if (PrescriptionSQL.updatePrescription(updatePackage)) {
                isEditing = false;
                loadDataFromDatabase();
                clearFields();
                JOptionPane.showMessageDialog(this, "Changes committed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Database update operation rejected tracking adjustments packages.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateInputs(String patientId, String drug, String freq, String duration, int ignoringRowIndex) {
        if (drug.isEmpty() || freq.isEmpty() || duration.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All informational tracking properties require entry values!", "Incomplete Fields", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!drug.matches("[a-zA-Z\\- ]+")) {
            JOptionPane.showMessageDialog(this, "Drug target text values should map straight alphabetic strings.", "Invalid Drug", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (cbDose.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Assign an active item volume string inside selection choice map options box.", "No Dosage Selected", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (Integer.parseInt(freq) <= 0) {
                JOptionPane.showMessageDialog(this, "Frequency numbers must represent metrics higher than structural base level zeros.", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Frequency tracking requirements must contain simple integer structures.", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (Integer.parseInt(duration) <= 0) {
                JOptionPane.showMessageDialog(this, "Lifecycle durations must encompass active intervals.", "Invalid Duration", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Timelines demand plain digital numerical figures for bounds declarations.", "Invalid Duration", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            if (i == ignoringRowIndex) continue;
            if (patientIds.get(i).equalsIgnoreCase(patientId) &&
                tblModel.getValueAt(i, 2).toString().equalsIgnoreCase(drug)) {
                JOptionPane.showMessageDialog(this, "This patient (" + patientId + ") already has an active prescription record for " + drug + "!", "Duplicate Prescription Entry", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void updateSummary() {
        int totalPrescription = tblModel.getRowCount();
        HashSet<String> uniquePatients = new HashSet<>(patientIds);
        
        lblTPrescription.setText(String.valueOf(totalPrescription));
        lblTPatients.setText(String.valueOf(uniquePatients.size()));
    }

    private void clearFields() {
        cbPat.setSelectedIndex(0);
        txtDrug.setText("");
        txtFreq.setText("");
        txtDur.setText("");
        cbDose.setSelectedIndex(0);
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}