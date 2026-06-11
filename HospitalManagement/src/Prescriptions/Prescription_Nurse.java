package Prescriptions;

import static Color_Palette.ColorPalette.*;
import Database.PrescriptionSQL;
import Database.PatientManagementSQL;
import Database.UserManagementSQL;
import Models.Prescription;
import Models.Patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class Prescription_Nurse extends JPanel implements ActionListener {

    private JPanel pnlMain, tabPrescription, tabPatients, tabUpdate, pnlForm, pnlBottom;
    private JLabel lblMainTitle, lblDT, lblTitle, lblValue, lblFreq, lblDose, lblTPrescription, lblTPatients;
    private JTextField txtFreq;
    private JComboBox<String> cbDose;
    private JButton btnEdit, btnSave;
    private JTable tblPres;
    private JTableHeader hdrtbl;
    private JScrollPane sp;
    private DefaultTableModel tblModel;

    private ArrayList<Integer> dbIds = new ArrayList<>();
    private ArrayList<String> patientIds = new ArrayList<>();
    private boolean isEditing = false;
    private int editingRow = -1;
    private int editingPrescriptionId = -1;

    public Prescription_Nurse() {

        setLayout(null);
        setBounds(0, 0, 1620, 930);
        setBackground(veryLightBlue);

        pnlMain = new JPanel(null);
        pnlMain.setBounds(0, 0, 1620, 930);
        pnlMain.setBackground(veryLightBlue);
        add(pnlMain);

        lblMainTitle = new JLabel("Nurse Prescription Dashboard");
        lblMainTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lblMainTitle.setForeground(Color.BLACK);
        lblMainTitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lblMainTitle);

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

        tabPrescription = createTab("Total Patients", "0", darkBlue);
        tabPrescription.setBounds(30, 90, 500, 110);
        lblTPrescription = (JLabel) tabPrescription.getComponent(1);
        pnlMain.add(tabPrescription);

        tabPatients = createTab("Total Prescriptions", "0", mediumBlue);
        tabPatients.setBounds(550, 90, 500, 110);
        lblTPatients = (JLabel) tabPatients.getComponent(1);
        pnlMain.add(tabPatients);

        pnlForm = new JPanel(null);
        pnlForm.setBounds(30, 220, 850, 110);
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlForm);

        lblFreq = new JLabel("New Frequency:");
        lblFreq.setFont(new Font("Calibri", Font.BOLD, 18));
        lblFreq.setBounds(20, 40, 160, 30);
        pnlForm.add(lblFreq);

        txtFreq = new JTextField();
        txtFreq.setBounds(185, 40, 150, 30);
        txtFreq.setFont(new Font("Calibri", Font.PLAIN, 16));
        pnlForm.add(txtFreq);

        lblDose = new JLabel("New Dosage:");
        lblDose.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDose.setBounds(360, 40, 130, 30);
        pnlForm.add(lblDose);

        cbDose = new JComboBox<>(new String[]{"Select", "5mg", "10mg", "50mg", "100mg", "400mg", "500mg"});
        cbDose.setBounds(495, 40, 150, 30);
        cbDose.setFont(new Font("Calibri", Font.PLAIN, 16));
        pnlForm.add(cbDose);

        btnSave = new JButton("Save Update");
        btnSave.setBounds(680, 38, 140, 35);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(this);
        pnlForm.add(btnSave);

        String[] cols = {"Patient ID", "Patient Name", "Drug", "Dosage", "Frequency", "Duration"};
        tblModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
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
            if (!e.getValueIsAdjusting() && isEditing) {
                int row = tblPres.getSelectedRow();
                if (row >= 0) {
                    txtFreq.setText(tblModel.getValueAt(row, 4).toString().replace("x/day", "").trim());
                    cbDose.setSelectedItem(tblModel.getValueAt(row, 3).toString());
                    editingRow = row;
                    editingPrescriptionId = dbIds.get(row);
                }
            }
        });

        pnlBottom = new JPanel(null);
        pnlBottom.setBounds(30, 850, 1560, 50);
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlBottom);

        btnEdit = new JButton("Edit Selected");
        btnEdit.setBounds(20, 10, 140, 30);
        btnEdit.setBackground(mediumBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(e -> {
            int row = tblPres.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select a prescription row first!", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            isEditing = true;
            editingRow = row;
            editingPrescriptionId = dbIds.get(row);
            txtFreq.setText(tblModel.getValueAt(row, 4).toString().replace("x/day", "").trim());
            cbDose.setSelectedItem(tblModel.getValueAt(row, 3).toString());
            JOptionPane.showMessageDialog(this, "Edit Frequency and Dosage, then click Save Update.", "Edit Mode", JOptionPane.INFORMATION_MESSAGE);
        });
        pnlBottom.add(btnEdit);

        loadDataFromDatabase();
    }

    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel(null);
        tabUpdate.setBackground(color);

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 20, 300, 30);
        tabUpdate.add(lblTitle);

        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 32));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 55, 200, 40);
        tabUpdate.add(lblValue);

        return tabUpdate;
    }

    private void loadDataFromDatabase() {
        tblModel.setRowCount(0);
        dbIds.clear();
        patientIds.clear();

        String loggedInNurse = UserManagementSQL.currentEmployee.getName() + " (Nurse)";

        HashSet<String> assignedPatientIds = new HashSet<>();
        List<Patient> allPatients = PatientManagementSQL.getAllPatients();
        for (Patient p : allPatients) {
            if (p.getNurse() != null && p.getNurse().equalsIgnoreCase(loggedInNurse)) {
                assignedPatientIds.add(p.getId().toUpperCase());
            }
        }

        List<Prescription> allPrescriptions = PrescriptionSQL.getAllPrescriptions();
        for (Prescription p : allPrescriptions) {
            if (assignedPatientIds.contains(p.getPatientId().toUpperCase())) {
                dbIds.add(p.getPrescriptionId());
                patientIds.add(p.getPatientId());
                tblModel.addRow(new Object[]{p.getPatientId(), p.getPatientName(), p.getDrugName(), p.getDosage(), p.getFrequency() + "x/day", p.getDuration() + " day(s)"
                });
            }
        }

        updateSummary();
    }

    private void savePrescriptionUpdate() {
        if (!isEditing) {
            JOptionPane.showMessageDialog(this, "Click 'Edit Selected' on a row first!", "Not in Edit Mode", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (editingRow < 0 || editingPrescriptionId < 0) {
            JOptionPane.showMessageDialog(this, "No prescription selected!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String freqText = txtFreq.getText().trim();
        if (freqText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a frequency!", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cbDose.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a valid dosage!", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int freqVal;
        try {
            freqVal = Integer.parseInt(freqText);
            if (freqVal <= 0) {
                JOptionPane.showMessageDialog(this, "Frequency must be a positive number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Frequency must be a valid number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int durVal = Integer.parseInt(
            tblModel.getValueAt(editingRow, 5).toString().replace("day(s)", "").trim()
        );

        Prescription updated = new Prescription(
            editingPrescriptionId,
            tblModel.getValueAt(editingRow, 0).toString(),
            tblModel.getValueAt(editingRow, 1).toString(),
            tblModel.getValueAt(editingRow, 2).toString(),
            cbDose.getSelectedItem().toString(),  
            freqVal,                                
            durVal,
            "Pending"
        );

        if (PrescriptionSQL.updatePrescription(updated)) {
            isEditing = false;
            editingRow = -1;
            editingPrescriptionId = -1;
            clearFields();
            loadDataFromDatabase();
            JOptionPane.showMessageDialog(this, "Prescription updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update prescription in the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSummary() {
        int totalPrescriptions = tblModel.getRowCount();
        HashSet<String> uniquePatients = new HashSet<>(patientIds);
        lblTPatients.setText(String.valueOf(totalPrescriptions));
        lblTPrescription.setText(String.valueOf(uniquePatients.size()));
    }

    private void clearFields() {
        txtFreq.setText("");
        cbDose.setSelectedIndex(0);
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSave) {
            savePrescriptionUpdate();
        }
    }
}