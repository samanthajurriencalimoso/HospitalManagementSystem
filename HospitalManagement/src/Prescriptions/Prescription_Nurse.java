package Prescriptions;

import static Color_Palette.ColorPalette.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class Prescription_Nurse extends JPanel implements ActionListener{

    private JPanel pnlMain, tabPrescription, tabPatients, pnlForm, pnlBottom, tabUpdate;
    private JTable tblPres;
    private JTableHeader hdrtbl;
    private DefaultTableModel tblModel;
    private JTextField txtFreq;
    private JButton btnEdit, btnSave;
    private JLabel lblMainTitle, lblDT, lblInfo, lblTitle, lblStatus, lblFreq, lblValue, lblTPrescription, lblTPatients, lblTMedicine;
    private JScrollPane sp;
    private boolean isEditing = false;
    private JComboBox<String> cbStatus;
    private ArrayList<String[]> prescriptions = new ArrayList<>();
    private int editingRow = -1;

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

        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setBounds(1390, 20, 400, 40);
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.DARK_GRAY);
        pnlMain.add(lblDT);

        tabPrescription = createTab("Total Patients", "0", darkBlue);
        tabPrescription.setBounds(30, 90, 500, 110);
        lblTPrescription = (JLabel) tabPrescription.getComponent(1);
        pnlMain.add(tabPrescription);

        tabPatients = createTab("Total Prescriptions", "0", mediumBlue);
        tabPatients.setBounds(550, 90, 500, 110);
        lblTPatients = (JLabel) tabPatients.getComponent(1);
        pnlMain.add(tabPatients);

        pnlForm = new JPanel(null);
        pnlForm.setBounds(30, 220, 1020, 110);
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlForm);
        
        lblFreq = new JLabel("New Frequency:");
        lblFreq.setFont(new Font("Calibri", Font.BOLD, 18));
        lblFreq.setBounds(20, 40, 150, 30);
        pnlForm.add(lblFreq);
        
        txtFreq = new JTextField();
        txtFreq.setBounds(170, 40, 200, 30);
        txtFreq.setFont(new Font("Calibri", Font.PLAIN, 16));
        pnlForm.add(txtFreq);
        
        lblStatus = new JLabel("New Status:");
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 18));
        lblStatus.setBounds(420, 40, 150, 30);
        pnlForm.add(lblStatus);
        
        cbStatus = new JComboBox<>(new String[]{"Active", "Inactive", "On Hold", "Completed"});
        cbStatus.setBounds(570, 40, 200, 30);
        cbStatus.setFont(new Font("Calibri", Font.PLAIN, 16));
        pnlForm.add(cbStatus);
        
        btnSave = new JButton("Save Update");
        btnSave.setBounds(840, 38, 140, 35);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(this);
        pnlForm.add(btnSave);
        
        String[] cols = {"Patient ID", "Patient Name", "Drug", "Dosage", "Frequency", "Status"};
 
        tblModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
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
                    String freq = tblModel.getValueAt(data, 4).toString().replace("x/day", "").trim();
                    String status = tblModel.getValueAt(data, 5).toString();
                    txtFreq.setText(freq);
                    cbStatus.setSelectedItem(status);
                    editingRow = data;
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
            String freq = tblModel.getValueAt(row, 4).toString().replace("x/day", "").trim();
            String status = tblModel.getValueAt(row, 5).toString();
            txtFreq.setText(freq);
            cbStatus.setSelectedItem(status);
            JOptionPane.showMessageDialog(this, "Edit Frequency and Status, then click Save Update.", "Edit Mode", JOptionPane.INFORMATION_MESSAGE);
        });
        pnlBottom.add(btnEdit);
        
        addSampleData();
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
    
    private void savePrescriptionUpdate() {
        if (!isEditing) {
            JOptionPane.showMessageDialog(this, "Click 'Edit Selected' on a row first!", "Not in Edit Mode", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        if (editingRow < 0) {
            JOptionPane.showMessageDialog(this, "No prescription selected!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        String freq = txtFreq.getText().trim();
        String status = (String) cbStatus.getSelectedItem();
 
        if (freq.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a frequency!", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int freqVal;
        try {
            freqVal = Integer.parseInt(freq);
            if (freqVal <= 0) {
                JOptionPane.showMessageDialog(this, "Frequency must be a positive number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Frequency must be a valid number!", "Invalid Frequency", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        tblModel.setValueAt(freqVal + "x/day", editingRow, 4);
        tblModel.setValueAt(status, editingRow, 5);
 
        String[] row = prescriptions.get(editingRow);
        row[4] = freqVal + "x/day";
        row[5] = status;
 
        isEditing = false;
        editingRow = -1;
        updateSummary();
        clearFields();
        JOptionPane.showMessageDialog(this, "Prescription updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        txtFreq.setText("");
        cbStatus.setSelectedIndex(0);
    }
    
    private void addSampleData() {
        String[] row1 = {"P-10000", "John Doe", "Paracetamol", "500mg", "2x/day", "Active"};
        String[] row2 = {"P-10001", "Jane Smith", "Amoxicillin", "250mg", "3x/day", "Active"};
        String[] row3 = {"P-10003", "Michael Cruz", "Ibuprofen", "400mg", "1x/day", "On Hold"};
 
        prescriptions.add(row1);
        prescriptions.add(row2);
        prescriptions.add(row3);
 
        tblModel.addRow(row1);
        tblModel.addRow(row2);
        tblModel.addRow(row3);
 
        updateSummary();
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSave) {
            savePrescriptionUpdate();
        } 
    }
}