package Inventory;

import static Color_Palette.ColorPalette.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserManagementPanel extends JPanel {
    
    private JPanel pnlMain,tabAdmin,tabDoc,tabNur,pnlSelection,pnlBot,tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtName, txtID, txtAge;
    private String currentRole = "Doctor";
    private JTable tblUM;
    private JLabel lblAd, lblDoc, lblNur,lbltitle,lblDT,lblName,lblID,lblAge,lblTitle,lblValue;
    private JButton btnDoctor, btnNurse, btnAdmin, btnSave, btnEdit, btnDelete, btnRefresh;
    private JScrollPane srcUM;
    
    public UserManagementPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lbltitle = new JLabel("User Management Dashboard");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
       
        btnAdmin = new JButton("ADMIN");
        btnAdmin.setBackground(darkBlue);
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFont(new Font("Calibri", Font.BOLD, 18));
        btnAdmin.setFocusPainted(false);
        btnAdmin.setBounds(30, 80, 120, 35);
        btnAdmin.addActionListener(e -> switchRole("Doctor"));
        pnlMain.add(btnAdmin);
        
        btnDoctor = new JButton("DOCTOR");
        btnDoctor.setBackground(mediumBlue);
        btnDoctor.setForeground(Color.WHITE);
        btnDoctor.setFont(new Font("Calibri", Font.BOLD, 18));
        btnDoctor.setFocusPainted(false);
        btnDoctor.setBounds(160, 80, 120, 35);
        btnDoctor.addActionListener(e -> switchRole("Doctor"));
        pnlMain.add(btnDoctor);
        
        btnNurse = new JButton("NURSE");
        btnNurse.setBackground(lightBlue);
        btnNurse.setForeground(Color.WHITE);
        btnNurse.setFont(new Font("Calibri", Font.BOLD, 18));
        btnNurse.setFocusPainted(false);
        btnNurse.setBounds(290, 80, 120, 35);
        btnNurse.addActionListener(e -> switchRole("Nurse"));
        pnlMain.add(btnNurse);
        
        tabAdmin = createTab("Admin", "0", darkBlue);
        tabAdmin.setBounds(30, 130, 250, 60);
        pnlMain.add(tabAdmin);
        lblAd = (JLabel) tabAdmin.getComponent(1);

        tabDoc = createTab("Doctor", "0", mediumBlue);
        tabDoc.setBounds(300, 130, 250, 60);
        pnlMain.add(tabDoc);
        lblDoc = (JLabel) tabDoc.getComponent(1);

        tabNur = createTab("Nurse", "0", lightBlue);
        tabNur.setBounds(570, 130, 250, 60);
        pnlMain.add(tabNur);
        lblNur = (JLabel) tabNur.getComponent(1);
      
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 1130, 80);
        pnlMain.add(pnlSelection);
        
        lblName = new JLabel("Name:");
        lblName.setBounds(15, 28, 60, 25);
        lblName.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(70, 26, 180, 28);
        pnlSelection.add(txtName);
        
        lblID = new JLabel("ID:");
        lblID.setBounds(270, 28, 80, 25);
        lblID.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblID);
        
        txtID = new JTextField();
        txtID.setBounds(300, 26, 180, 28);
        pnlSelection.add(txtID);
        
        lblAge = new JLabel("Age:");
        lblAge.setBounds(500, 28, 100, 25);
        lblAge.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblAge);
        
        txtAge = new JTextField();
        txtAge.setBounds(540, 26, 180, 28);
        pnlSelection.add(txtAge);
        
        btnSave = new JButton("Save");
        btnSave.setBounds(760, 22, 150, 35);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Calibri", Font.BOLD, 14));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> addUser());
        pnlSelection.add(btnSave);
        
        btnEdit = new JButton("Edit");
        btnEdit.setBounds(940, 22, 150, 35);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(e -> editUser());
        pnlSelection.add(btnEdit);
       
        String[] clm = {"Name", "ID", "Age", "Role", "Status"};
        tblModel = new DefaultTableModel(clm, 0);
        tblUM = new JTable(tblModel);
        tblUM.setRowHeight(40);
        tblUM.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblUM.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblUM.getTableHeader().setBackground(lightBlue);
        
        srcUM = new JScrollPane(tblUM);
        srcUM.setBounds(30, 310, 1560, 530);
        pnlMain.add(srcUM);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBackground(LightRed);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setBounds(20, 10, 120, 30);
        btnDelete.addActionListener(e -> deleteUser());
        pnlBot.add(btnDelete);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(Blue);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBounds(160, 10, 120, 30);
        btnRefresh.addActionListener(e -> refreshTable());
        pnlBot.add(btnRefresh);
        
        //addSampData();
    }
    
    private void switchRole(String role) {
        currentRole = role;
        JOptionPane.showMessageDialog(this, "Current Role: " + role);
    }
    
    private JPanel createTab(String title, String value, Color color) {
       tabUpdate = new JPanel();
       tabUpdate.setLayout(null);
       tabUpdate.setBackground(color);
       
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 20, 200, 25);
        tabUpdate.add(lblTitle);
        
        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(200, 20, 100, 25);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }
    
    private void addUser() {
        String name = txtName.getText().trim();
        String id = txtID.getText().trim();
        String age = txtAge.getText().trim();
        
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter NAME and ID!");
            return;
        }
        
        tblModel.addRow(new Object[]{name, id, age, currentRole, "Active"});
        
        txtName.setText("");
        txtID.setText("");
        txtAge.setText("");
        updateSummary();
        
        JOptionPane.showMessageDialog(this, currentRole + " added successfully!");
    }
    
    private void editUser() {
        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            txtName.setText(tblModel.getValueAt(row, 0).toString());
            txtID.setText(tblModel.getValueAt(row, 1).toString());
            txtAge.setText(tblModel.getValueAt(row, 2).toString());
            
            String role = tblModel.getValueAt(row, 3).toString();
            if (role.equals("Doctor")) currentRole = "Doctor";
            else if (role.equals("Nurse")) currentRole = "Nurse";
            else currentRole = "Admin";
            
            tblModel.removeRow(row);
            updateSummary();
            
            JOptionPane.showMessageDialog(this, "Edit the details and click SAVE");
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to edit!");
        }
    }
    
    private void deleteUser() {
        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            String name = tblModel.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tblModel.removeRow(row);
                updateSummary();
                JOptionPane.showMessageDialog(this, "User deleted!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to delete!");
        }
    }
    
    private void updateSummary() {
        int total = tblModel.getRowCount();
        int admin = 0, doctor = 0, nurse = 0;
        for (int i = 0; i < total; i++) {
            String status = tblModel.getValueAt(i, 3).toString();
            if (status.equals("Admin")) admin++;
            if (status.equals("Doctor")) doctor++;
            if (status.equals("Nurse")) nurse++;
        }
        lblAd.setText(String.valueOf(admin));
        lblDoc.setText(String.valueOf(doctor));
        lblNur.setText(String.valueOf(nurse));
    }
    
    private void refreshTable() {
        tblModel.setRowCount(0);
        //addSampData();
        JOptionPane.showMessageDialog(this, "Table refreshed!");
    }
    
//    private void addSampData() {
//        tblModel.addRow(new Object[]{"Dr. John Smith", "DOC001", "45", "Doctor", "Active"});
//        tblModel.addRow(new Object[]{"Dr. Sarah Lee", "DOC002", "38", "Doctor", "Active"});
//        tblModel.addRow(new Object[]{"Nurse Emma Watson", "NRS001", "29", "Nurse", "Active"});
//        tblModel.addRow(new Object[]{"Nurse Olivia Brown", "NRS002", "32", "Nurse", "Active"});
//        tblModel.addRow(new Object[]{"Admin Mike Ross", "ADM002", "41", "Admin", "Active"});
//        tblModel.addRow(new Object[]{"Admin Rachel Green", "ADM002", "35", "Admin", "Active"});
//    
//        updateSummary();
//    }
}