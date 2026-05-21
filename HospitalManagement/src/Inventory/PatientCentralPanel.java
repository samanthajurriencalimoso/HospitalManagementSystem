package Inventory;

import static Color_Palette.ColorPalette.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientCentralPanel extends JPanel {
    
    private JPanel pnlMain,tabPatient,tabAdmission,tabBed,tabApp,pnlSelection,pnlBot,tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtName, txtRoom;
    private JLabel lblTPatient, lblAct,lbltitle,lblDT,lblName,lblRoom, lblTitle, lblValue;
    private JTable tblPC;
    private JButton btnAdd, btnAdmit, btnDischarge, btnRemove;
    private JScrollPane srcRoom;
    
    public PatientCentralPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
       
        lbltitle = new JLabel("Patient Central Dashboard");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        tabPatient = createTab("Total Patients", "0", darkBlue);
        tabPatient.setBounds(30, 80, 300, 100);
        pnlMain.add(tabPatient);
        lblTPatient = (JLabel) tabPatient.getComponent(1);
        
        tabAdmission = createTab("Active Admissions", "0", mediumBlue);
        tabAdmission.setBounds(350, 80, 300, 100);
        pnlMain.add(tabAdmission);
        lblAct = (JLabel) tabAdmission.getComponent(1);
        
        tabBed = createTab("Available Beds", "50", Green);
        tabBed.setBounds(670, 80, 300,100);
        pnlMain.add(tabBed);
        
        tabApp = createTab("Today's Appointments", "24", Yellow);
        tabApp.setBounds(990, 80, 300, 100);
        pnlMain.add(tabApp);
     
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 650, 80);
        pnlMain.add(pnlSelection);
        
        lblName = new JLabel("Name:");
        lblName.setBounds(15, 28, 80, 25);
        lblName.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(65, 26, 150, 28);
        pnlSelection.add(txtName);
        
        lblRoom = new JLabel("Room:");
        lblRoom.setBounds(230, 28, 50, 25);
        lblRoom.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblRoom);
        
        txtRoom = new JTextField();
        txtRoom.setBounds(280, 26, 150, 28);
        pnlSelection.add(txtRoom);
        
        btnAdd = new JButton("Add Patient");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(460, 22, 150, 35);
        btnAdd.addActionListener(e -> addPatient());
        pnlSelection.add(btnAdd);
       
        String[] clm = {"Name", "Room", "Status"};
        tblModel = new DefaultTableModel(clm, 0);
        tblPC = new JTable(tblModel);
        tblPC.setRowHeight(35);
        tblPC.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblPC.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblPC.getTableHeader().setBackground(lightBlue);
        
        srcRoom = new JScrollPane(tblPC);
        srcRoom.setBounds(30, 310, 1560, 530);
        pnlMain.add(srcRoom);
       
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);
        
        btnAdmit = new JButton("Admit Selected");
        btnAdmit.setBackground(darkBlue);
        btnAdmit.setForeground(Color.WHITE);
        btnAdmit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdmit.setFocusPainted(false);
        btnAdmit.setBounds(20, 10, 140, 30);
        btnAdmit.addActionListener(e -> admitPatient());
        pnlBot.add(btnAdmit);
        
        btnDischarge = new JButton("Discharge Selected");
        btnDischarge.setBackground(Blue);
        btnDischarge.setForeground(Color.WHITE);
        btnDischarge.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDischarge.setFocusPainted(false);
        btnDischarge.setBounds(180, 10, 150, 30);
        btnDischarge.addActionListener(e -> dischargePatient());
        pnlBot.add(btnDischarge);
        
        btnRemove = new JButton("Remove Selected");
        btnRemove.setBackground(LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(350, 10, 140, 30);
        btnRemove.addActionListener(e -> removePatient());
        pnlBot.add(btnRemove);
        
        addSampData();
    }
    
    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel();
        tabUpdate.setLayout(null);
        tabUpdate.setBackground(color);
        
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(15, 20, 250, 20);
        tabUpdate.add(lblTitle);
        
        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(15, 50, 150, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }
    
    private void addPatient() {
        String name = txtName.getText().trim();
        String room = txtRoom.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name!");
            return;
        }
        tblModel.addRow(new Object[]{name, room, "Active"});
        txtName.setText("");
        txtRoom.setText("");
        updateSummary();
        JOptionPane.showMessageDialog(this, "Patient added!");
    }
    
    private void admitPatient() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            tblModel.setValueAt("Admitted", row, 2);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Patient admitted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient!");
        }
    }
    
    private void dischargePatient() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            tblModel.setValueAt("Discharged", row, 2);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Patient discharged!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient!");
        }
    }
    
    private void removePatient() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String name = tblModel.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Remove " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tblModel.removeRow(row);
                updateSummary();
                
                JOptionPane.showMessageDialog(this, "Patient removed!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient!");
        }
    }
    
    private void updateSummary() {
        int total = tblModel.getRowCount();
        int admitted = 0;
        for (int i = 0; i < total; i++) {
            if (tblModel.getValueAt(i, 2).equals("Admitted")) admitted++;
        }
        lblTPatient.setText(String.valueOf(total));
        lblAct.setText(String.valueOf(admitted));
    }
    
    private void addSampData() {
        tblModel.addRow(new Object[]{"Jane Smith", "102", "Admitted"});
        tblModel.addRow(new Object[]{"Bob Johnson", "103", "Discharged"});
        
        updateSummary();
    }
}