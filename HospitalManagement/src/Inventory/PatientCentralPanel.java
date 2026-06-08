package Inventory;

import static Color_Palette.ColorPalette.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientCentralPanel extends JPanel {
    
    private JPanel pnlMain,tabPatient,tabAdmission, pnlSelection,pnlBot,tabUpdate, tabDis, tabCri;
    private DefaultTableModel tblModel;
    private JTextField txtName, txtRoom;
    private JLabel lblTPatient, lblAct,lbltitle,lblDT,lblName,lblRoom, lblTitle, lblValue, lblDoc, lblNur, lblStatus, lblCri, lblDis;
    private JTable tblPC;
    private JButton btnAdd, btnAdmit, btnDischarge, btnRemove, btnCStatus;
    private JScrollPane srcRoom;
    private JComboBox<String> cmbDoc, cmbNur, cmbStatus;
    private int PNum = 10000;
    
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
        
        tabAdmission = createTab("Active Admissions", "0", Yellow);
        tabAdmission.setBounds(350, 80, 300, 100);
        pnlMain.add(tabAdmission);
        lblAct = (JLabel) tabAdmission.getComponent(1);
        
        tabDis = createTab("Discharged Patients", "0", Blue);
        tabDis.setBounds(670, 80, 300, 100);
        pnlMain.add(tabDis);
        lblDis = (JLabel) tabDis.getComponent(1);
        
        tabCri = createTab("Critical Patients", "0", LightRed);
        tabCri.setBounds(990, 80, 300, 100);
        pnlMain.add(tabCri);
        lblCri = (JLabel) tabCri.getComponent(1);
     
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 1200, 120);
        pnlMain.add(pnlSelection);
        
        lblName = new JLabel("Name:");
        lblName.setBounds(15, 28, 100, 25);
        lblName.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(120, 26, 200, 28);
        pnlSelection.add(txtName);
        
        lblRoom = new JLabel("Room:");
        lblRoom.setBounds(340, 28, 100, 25);
        lblRoom.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblRoom);
        
        txtRoom = new JTextField();
        txtRoom.setBounds(445, 26, 200, 28);
        pnlSelection.add(txtRoom);
        
        lblDoc = new JLabel("Doctor:");
        lblDoc.setBounds(665, 28, 100, 25);
        lblDoc.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblDoc);
        
        cmbDoc = new JComboBox<>(new String[]{"Dr. Lee", "Dr. Cruz", "Dr. Santos"});
        cmbDoc.setBounds(770, 26, 200, 28);
        pnlSelection.add(cmbDoc);
        
        lblNur = new JLabel("Nurse:");
        lblNur.setBounds(340, 70, 100, 25);
        lblNur.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblNur);
        
        cmbNur = new JComboBox<>(new String[]{"Nurse Watson", "Nurse Reyes", "Nurse Tan"});
        cmbNur.setBounds(445, 70, 200, 28);
        pnlSelection.add(cmbNur);
        
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(15, 70, 100, 25);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblStatus);

        cmbStatus = new JComboBox<>(new String[]{"Active", "Admitted", "Discharged", "Under Observation", "Critical"});
        cmbStatus.setBounds(120, 68, 200, 28);
        pnlSelection.add(cmbStatus);
        
        btnAdd = new JButton("Add Patient");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(1010, 25, 150, 35);
        btnAdd.addActionListener(e -> addPatient());
        pnlSelection.add(btnAdd);
       
        String[] clm = {"Patient ID", "Name", "Room", "Doctor", "Nurse", "Status"};
        tblModel = new DefaultTableModel(clm, 0);
        tblPC = new JTable(tblModel);
        tblPC.setRowHeight(35);
        tblPC.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblPC.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblPC.getTableHeader().setBackground(lightBlue);
        
        srcRoom = new JScrollPane(tblPC);
        srcRoom.setBounds(30, 350, 1560, 500);
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
        
        JButton btnViewProfile = new JButton("View Profile");
        btnViewProfile.setBackground(mediumBlue);
        btnViewProfile.setForeground(Color.WHITE);
        btnViewProfile.setFont(new Font("Calibri", Font.BOLD, 14));
        btnViewProfile.setFocusPainted(false);
        btnViewProfile.setBounds(510, 10, 150, 30);
        btnViewProfile.addActionListener(e -> viewProfile());
        pnlBot.add(btnViewProfile);
        
        btnCStatus = new JButton("Change Status");
        btnCStatus.setBackground(orange);
        btnCStatus.setForeground(Color.WHITE);
        btnCStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCStatus.setFocusPainted(false);
        btnCStatus.setBounds(680, 10, 150, 30);
        btnCStatus.addActionListener(e -> changeStatus());
        pnlBot.add(btnCStatus);

        
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
        String doc = cmbDoc.getSelectedItem().toString();
        String nur = cmbNur.getSelectedItem().toString();
        String status = cmbStatus.getSelectedItem().toString();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name!");
            return;
        }
        
        String PatID = "P+" + (++PNum);
        tblModel.addRow(new Object[]{PatID, name, room, doc, nur, status});
        txtName.setText("");
        txtRoom.setText("");
        cmbDoc.setSelectedIndex(0);
        cmbNur.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        
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
    
    private void viewProfile() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String profile =
                "Patient Profile:\n\n" +
                "Patient ID: " + tblModel.getValueAt(row, 0) + "\n" +
                "Name: " + tblModel.getValueAt(row, 1) + "\n" +
                "Room: " + tblModel.getValueAt(row, 2) + "\n" +
                "Doctor: " + tblModel.getValueAt(row, 3) + "\n" +
                "Nurse: " + tblModel.getValueAt(row, 4) + "\n" +
                "Status: " + tblModel.getValueAt(row, 5);

            JOptionPane.showMessageDialog(this, profile, "Patient Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient to view profile!");
        }
    }
    
    private void changeStatus() {
        int row = tblPC.getSelectedRow();
            if (row >= 0) {
                String currentStatus = tblModel.getValueAt(row, 5).toString();
                String[] options = {"Active", "Admitted", "Discharged", "Under Observation"};
                String newStatus = (String) JOptionPane.showInputDialog(this,"Change status:","Update Patient Status",JOptionPane.PLAIN_MESSAGE,null,options,currentStatus);
                
        if (newStatus != null) {
            tblModel.setValueAt(newStatus, row, 5);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Status updated to " + newStatus);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select an patient to change status!");
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
        int admitted = 0, discharged = 0, critical = 0;
        
        for (int i = 0; i < total; i++) {
            String status = tblModel.getValueAt(i, 5).toString();
            if (status.equals("Admitted")) admitted++;
            if (status.equals("Discharged")) discharged++;
            if (status.equals("Critical")) critical++;
        }
        lblTPatient.setText(String.valueOf(total));
        lblAct.setText(String.valueOf(admitted));
        lblDis.setText(String.valueOf(discharged));
        lblCri.setText(String.valueOf(critical));
    }
    
    private void addSampData() {
        tblModel.addRow(new Object[]{"P-10001", "Jane Smith", "102", "Dr. Lee", "Nurse Watson", "Admitted"});
        tblModel.addRow(new Object[]{"P-10002", "Bob Johnson", "103", "Dr. Cruz", "Nurse Reyes", "Discharged"});
        tblModel.addRow(new Object[]{"P-10003", "Alice Brown", "104", "Dr. Santos", "Nurse Tan", "Under Observation"});
        tblModel.addRow(new Object[]{"P-10004", "Mark Davis", "105", "Dr. Lee", "Nurse Watson", "Critical"});

        updateSummary();
    }
}