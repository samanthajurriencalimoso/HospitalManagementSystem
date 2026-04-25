package medical_inventory;

import Color_Palette.ColorPalette;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserManagementPanel extends JPanel {
    
    private JPanel pnlMain;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtID, txtAge;
    private String currentRole = "Doctor";
    private JTable table;
    
    public UserManagementPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(ColorPalette.veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        
        JLabel title = new JLabel("USER MANAGEMENT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(ColorPalette.darkBlue);
        title.setBounds(30, 20, 400, 40);
        pnlMain.add(title);
        
       
        JButton btnDoctor = new JButton("DOCTOR");
        btnDoctor.setBackground(ColorPalette.darkBlue);
        btnDoctor.setForeground(Color.WHITE);
        btnDoctor.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDoctor.setFocusPainted(false);
        btnDoctor.setBounds(30, 80, 120, 35);
        btnDoctor.addActionListener(e -> switchRole("Doctor"));
        pnlMain.add(btnDoctor);
        
        JButton btnNurse = new JButton("NURSE");
        btnNurse.setBackground(ColorPalette.mediumBlue);
        btnNurse.setForeground(Color.WHITE);
        btnNurse.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNurse.setFocusPainted(false);
        btnNurse.setBounds(160, 80, 120, 35);
        btnNurse.addActionListener(e -> switchRole("Nurse"));
        pnlMain.add(btnNurse);
        
        JButton btnAdmin = new JButton("ADMIN");
        btnAdmin.setBackground(ColorPalette.Yellow);
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdmin.setFocusPainted(false);
        btnAdmin.setBounds(290, 80, 120, 35);
        btnAdmin.addActionListener(e -> switchRole("Admin"));
        pnlMain.add(btnAdmin);
        
      
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        formPanel.setBounds(30, 140, 600, 120);
        pnlMain.add(formPanel);
        
        JLabel lblName = new JLabel("NAME:");
        lblName.setBounds(20, 20, 60, 25);
        formPanel.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(80, 18, 180, 28);
        formPanel.add(txtName);
        
        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(280, 20, 40, 25);
        formPanel.add(lblID);
        
        txtID = new JTextField();
        txtID.setBounds(320, 18, 120, 28);
        formPanel.add(txtID);
        
        JLabel lblAge = new JLabel("AGE:");
        lblAge.setBounds(20, 60, 50, 25);
        formPanel.add(lblAge);
        
        txtAge = new JTextField();
        txtAge.setBounds(70, 58, 80, 28);
        formPanel.add(txtAge);
        
        JButton btnSave = new JButton("SAVE");
        btnSave.setBackground(ColorPalette.Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSave.setFocusPainted(false);
        btnSave.setBounds(200, 70, 100, 35);
        btnSave.addActionListener(e -> addUser());
        formPanel.add(btnSave);
        
        JButton btnEdit = new JButton("EDIT");
        btnEdit.setBackground(ColorPalette.darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEdit.setFocusPainted(false);
        btnEdit.setBounds(320, 70, 100, 35);
        btnEdit.addActionListener(e -> editUser());
        formPanel.add(btnEdit);
        
       
        String[] cols = {"NAME", "ID", "AGE", "ROLE", "STATUS"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(ColorPalette.lightBlue);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 280, 1560, 530);
        pnlMain.add(scroll);
        
      
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        bottomPanel.setBounds(30, 860, 1560, 50);
        pnlMain.add(bottomPanel);
        
        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBackground(ColorPalette.LightRed);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDelete.setFocusPainted(false);
        btnDelete.setBounds(20, 10, 120, 30);
        btnDelete.addActionListener(e -> deleteUser());
        bottomPanel.add(btnDelete);
        
        JButton btnRefresh = new JButton("REFRESH");
        btnRefresh.setBackground(ColorPalette.mediumBlue);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBounds(160, 10, 120, 30);
        btnRefresh.addActionListener(e -> refreshTable());
        bottomPanel.add(btnRefresh);
        
        addSampleData();
    }
    
    private void switchRole(String role) {
        currentRole = role;
        JOptionPane.showMessageDialog(this, "Current Role: " + role);
    }
    
    private void addUser() {
        String name = txtName.getText().trim();
        String id = txtID.getText().trim();
        String age = txtAge.getText().trim();
        
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter NAME and ID!");
            return;
        }
        
        tableModel.addRow(new Object[]{name, id, age, currentRole, "Active"});
        
        txtName.setText("");
        txtID.setText("");
        txtAge.setText("");
        
        JOptionPane.showMessageDialog(this, currentRole + " added successfully!");
    }
    
    private void editUser() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtName.setText(tableModel.getValueAt(row, 0).toString());
            txtID.setText(tableModel.getValueAt(row, 1).toString());
            txtAge.setText(tableModel.getValueAt(row, 2).toString());
            
            String role = tableModel.getValueAt(row, 3).toString();
            if (role.equals("Doctor")) currentRole = "Doctor";
            else if (role.equals("Nurse")) currentRole = "Nurse";
            else currentRole = "Admin";
            
            tableModel.removeRow(row);
            JOptionPane.showMessageDialog(this, "Edit the details and click SAVE");
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to edit!");
        }
    }
    
    private void deleteUser() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String name = tableModel.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);
                JOptionPane.showMessageDialog(this, "User deleted!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to delete!");
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        addSampleData();
        JOptionPane.showMessageDialog(this, "Table refreshed!");
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{"Dr. John Smith", "DOC001", "45", "Doctor", "Active"});
        tableModel.addRow(new Object[]{"Dr. Sarah Lee", "DOC002", "38", "Doctor", "Active"});
        tableModel.addRow(new Object[]{"Nurse Emma Watson", "NRS001", "29", "Nurse", "Active"});
        tableModel.addRow(new Object[]{"Nurse Olivia Brown", "NRS002", "32", "Nurse", "Active"});
        tableModel.addRow(new Object[]{"Admin Mike Ross", "ADM001", "41", "Admin", "Active"});
        tableModel.addRow(new Object[]{"Admin Rachel Green", "ADM002", "35", "Admin", "Active"});
    }
}