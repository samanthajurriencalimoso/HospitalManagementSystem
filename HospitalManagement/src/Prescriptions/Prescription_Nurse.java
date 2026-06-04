package Prescriptions;

import static Color_Palette.ColorPalette.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

public class Prescription_Nurse extends JPanel implements ActionListener{

    private JPanel pnlMain, tabPrescription, tabPatients, tabMedicine, pnlPrescription, pnlUpdate, tabUpdate;
    private JTable tblPres;
    private JTableHeader hdrPres;
    private DefaultTableModel tblModel;
    private JTextField txtFreq;
    private JTextField txtDur;
    private JButton btnUpdate;
    private JLabel lblMainTitle, lblDT, lblPrescriptionTitle, lblFreq, lblDur, lblTitle, lblValue, lblTPrescription, lblTPatients, lblTMedicine;
    private JScrollPane sp;

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

        tabPrescription = createTopCard("Total Prescriptions", "3", darkBlue);
        tabPrescription.setBounds(30, 90, 500, 110);
        lblTPrescription = (JLabel) tabPrescription.getComponent(1);
        pnlMain.add(tabPrescription);

        tabPatients = createTopCard("Patients Assigned", "3", mediumBlue);
        tabPatients.setBounds(550, 90, 500, 110);
        lblTPatients = (JLabel) tabPatients.getComponent(1);
        pnlMain.add(tabPatients);

        tabMedicine = createTopCard("Medicine Schedule", "3", TealGreen);
        tabMedicine.setBounds(1070, 90, 500, 110);
        lblTMedicine = (JLabel) tabMedicine.getComponent(1);
        pnlMain.add(tabMedicine);

        pnlPrescription = new JPanel(null);
        pnlPrescription.setBounds(30, 240, 1560, 620);
        pnlPrescription.setBackground(Color.WHITE);
        pnlPrescription.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlMain.add(pnlPrescription);

        lblPrescriptionTitle = new JLabel("Prescription Management");
        lblPrescriptionTitle.setBounds(30, 20, 450, 40);
        lblPrescriptionTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        lblPrescriptionTitle.setForeground(darkBlue);
        pnlPrescription.add(lblPrescriptionTitle);

        String[] col = {"Patient", "Drug", "Dosage", "Frequency", "Duration"};

        tblModel = new DefaultTableModel(col, 0);
        tblPres = new JTable(tblModel);
        tblPres.setRowHeight(35);
        tblPres.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblPres.setSelectionBackground(SBlue);
        
        hdrPres = tblPres.getTableHeader();
        hdrPres.setFont(new Font("Calibri", Font.BOLD, 18));
        hdrPres.setBackground(lightBlue);
        hdrPres.setForeground(Color.BLACK);

        sp = new JScrollPane(tblPres);
        sp.setBounds(30, 80, 1490, 300);
        sp.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlPrescription.add(sp);

        pnlUpdate = new JPanel(null);
        pnlUpdate.setBounds(180, 420, 1180, 140);
        pnlUpdate.setBackground(veryLightBlue);
        pnlUpdate.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlPrescription.add(pnlUpdate);

        lblFreq = new JLabel("Frequency");
        lblFreq.setBounds(130, 25, 120, 30);
        lblFreq.setFont(new Font("Calibri", Font.BOLD, 20));
        lblFreq.setForeground(darkBlue);
        pnlUpdate.add(lblFreq);

        txtFreq = new JTextField();
        txtFreq.setBounds(130, 60, 280, 40);
        txtFreq.setFont(new Font("Calibri", Font.PLAIN, 17));
        pnlUpdate.add(txtFreq);

        lblDur = new JLabel("Duration");
        lblDur.setBounds(500, 25, 120, 30);
        lblDur.setFont(new Font("Calibri", Font.BOLD, 20));
        lblDur.setForeground(darkBlue);
        pnlUpdate.add(lblDur);

        txtDur = new JTextField();
        txtDur.setBounds(500, 60, 280, 40);
        txtDur.setFont(new Font("Calibri", Font.PLAIN, 17));
        pnlUpdate.add(txtDur);

        btnUpdate = new JButton("Update Prescription");
        btnUpdate.setBounds(860, 58, 240, 45);
        btnUpdate.setBackground(Blue);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlUpdate.add(btnUpdate);

        addSampData();
    }

    private JPanel createTopCard(String title, String value, Color bg) {
        tabUpdate = new JPanel(null);
        tabUpdate.setBackground(bg);
        
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 18, 300, 30);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        tabUpdate.add(lblTitle);

        lblValue = new JLabel(value);
        lblValue.setBounds(20, 55, 200, 40);
        lblValue.setForeground(Color.WHITE);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 34));
        tabUpdate.add(lblValue);

        return tabUpdate;
    }
    
    private void updatePrescription() {
        int row = tblPres.getSelectedRow();

        if (row >= 0) {
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
    
    private void addSampData() {
        tblModel.addRow(new String[]{"John Doe", "Paracetamol", "500mg", "2x/day", "5 days"});
        tblModel.addRow(new String[]{"Jane Smith", "Amoxicillin", "250mg", "3x/day", "7 days"});
        tblModel.addRow(new String[]{"Michael Cruz", "Ibuprofen", "400mg", "1x/day", "3 days"});
        
        updateSummary();
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnUpdate) {
            updatePrescription();
        } 
    }
}