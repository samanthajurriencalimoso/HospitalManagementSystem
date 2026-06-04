package Prescriptions;

import static Color_Palette.ColorPalette.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Prescription_Doctor extends JPanel implements ActionListener {

    private JPanel pnlMain, tabPrescription, tabPatients, tabMedicine, tabUpdate, pnlForm, pnlBottom;
    private JLabel lblTitle, lblDate, lblTPrescription, lblTPatients, lblTMedicine, lblPatient, lblDrug, lblDose, lblFreq, lblDur, lblTitle2, 
                   lblValue;
    private JTextField txtPat, txtDrug, txtFreq, txtDur;
    private JComboBox<String> cbDose;
    private JButton btnAdd, btnDelete, btnUpdate;
    private JTable tblPres;
    private JTableHeader hdrtbl;
    private JScrollPane sp;
    private DefaultTableModel tblModel;
    private ArrayList<String[]> prescriptions = new ArrayList<>();

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

        tabPrescription = createTab("Total Prescriptions", "0", darkBlue);
        tabPrescription.setBounds(30, 80, 500, 110);
        pnlMain.add(tabPrescription);

        lblTPrescription = (JLabel) tabPrescription.getComponent(1);

        tabPatients = createTab("Total Patients", "0", mediumBlue);
        tabPatients.setBounds(550, 80, 500, 110);
        pnlMain.add(tabPatients);

        lblTPatients = (JLabel) tabPatients.getComponent(1);
        
        tabMedicine =createTab("Medicine Types", "0", TealGreen);
        tabMedicine.setBounds(1070, 80, 500, 110);
        pnlMain.add(tabMedicine);
        lblTMedicine =
                (JLabel) tabMedicine.getComponent(1);

        pnlForm = new JPanel(null);
        pnlForm.setBounds(30, 220, 1560, 110);
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlForm);
       
        Font lblFont = new Font("Calibri", Font.BOLD, 18);

        lblPatient = new JLabel("Patient:");
        lblPatient.setFont(lblFont);
        lblPatient.setBounds(20, 38, 100, 30);
        pnlForm.add(lblPatient);
        txtPat = new JTextField();
        txtPat.setBounds(100, 38, 180, 30);
        pnlForm.add(txtPat);

        lblDrug = new JLabel("Drug:");
        lblDrug.setFont(lblFont);
        lblDrug.setBounds(310, 38, 80, 30);
        pnlForm.add(lblDrug);
        txtDrug = new JTextField();
        txtDrug.setBounds(370, 38, 180, 30);
        pnlForm.add(txtDrug);

        lblDose = new JLabel("Dosage:");
        lblDose.setFont(lblFont);
        lblDose.setBounds(580, 38, 100, 30);
        pnlForm.add(lblDose);

        cbDose = new JComboBox<>(
                new String[]{"Select", "5mg", "10mg", "50mg","100mg","400mg","500mg"});
        cbDose.setBounds(660, 38, 140, 30);
        pnlForm.add(cbDose);
        
        lblFreq = new JLabel("Frequency:");
        lblFreq.setFont(lblFont);
        lblFreq.setBounds(830, 38, 100, 30);
        pnlForm.add(lblFreq);
        txtFreq = new JTextField();
        txtFreq.setBounds(930, 38, 150, 30);
        pnlForm.add(txtFreq);

        lblDur = new JLabel("Duration:");
        lblDur.setFont(lblFont);
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

        pnlBottom = new JPanel(null);
        pnlBottom.setBounds(30, 850, 1560, 50);
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlBottom);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(20, 10, 120, 30);
        btnUpdate.setBackground(mediumBlue);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Calibri", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        pnlBottom.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(160, 10, 120, 30);
        btnDelete.setBackground(LightRed);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        pnlBottom.add(btnDelete);
        
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        addSampData();
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
        } else if (e.getSource() == btnUpdate) {
            updatePrescription();
        }
    }
    
    private void addPrescription() {

        if (txtPat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Enter patient name!");
            return;
        } else if (txtDrug.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter medicine!");
            return;
        } else if (cbDose.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,"Select dosage!");
            return;
        }
        
        String[] row = {txtPat.getText(), txtDrug.getText(), cbDose.getSelectedItem().toString(), txtFreq.getText(), txtDur.getText()};
        prescriptions.add(row); tblModel.addRow(row);
        
        updateSummary();
        clearFields();
        JOptionPane.showMessageDialog(this,"Prescription added!");
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
    }

    private void updatePrescription() {
        int row = tblPres.getSelectedRow();

        if (row >= 0) {
            tblModel.setValueAt(txtPat.getText(), row, 0);
            tblModel.setValueAt(txtDrug.getText(), row, 1);
            tblModel.setValueAt(cbDose.getSelectedItem(),row,2);
            tblModel.setValueAt(txtFreq.getText(), row, 3);
            tblModel.setValueAt(txtDur.getText(), row, 4);
            updateSummary();
            
            JOptionPane.showMessageDialog(this,"Prescription updated!");
        } else {
            JOptionPane.showMessageDialog(this,"Select row first!");
        }
    }

    private void updateSummary() {
        int totalPrescription = tblModel.getRowCount();
        int totalPatients = tblModel.getRowCount();
        java.util.HashSet<String> meds = new java.util.HashSet<>();
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            meds.add(tblModel.getValueAt(i, 1).toString());
        }
            lblTPrescription.setText(String.valueOf(totalPrescription));
            lblTPatients.setText(String.valueOf(totalPatients));
            lblTMedicine.setText(String.valueOf(meds.size())
        );
    }

    private void clearFields() {
        txtPat.setText("");
        txtDrug.setText("");
        txtFreq.setText("");
        txtDur.setText("");
        cbDose.setSelectedIndex(0);
    }

    private void addSampData() {
        tblModel.addRow(new Object[]{"John Doe", "Paracetamol", "500mg", "2x/day", "5 days"});
        tblModel.addRow(new Object[]{"Maria Santos", "Ibuprofen", "400mg", "3x/day", "7 days"});
        tblModel.addRow(new Object[]{"Mark Cruz", "Amoxicillin", "250mg", "1x/day", "10 days"});
        
        updateSummary();
    }
}