package medical_inventory;

import Color_Palette.ColorPalette;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientCentralPanel extends JPanel {
    
    private JPanel pnlMain;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtRoom;
    private JLabel lblTotalPatients, lblActiveAdmissions;
    private JTable table;
    
    public PatientCentralPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(ColorPalette.veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        int panelWidth = 1620;
        int startX = 30;
        
       
        JLabel title = new JLabel("Patient Central Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(ColorPalette.darkBlue);
        title.setBounds(startX, 20, 400, 40);
        pnlMain.add(title);
        
    
        int tabWidth = (panelWidth - 120) / 4;
        int tabHeight = 100;
        int tabY = 80;
        
        JPanel tab1 = createTab("Total Patients", "0", ColorPalette.darkBlue);
        tab1.setBounds(startX, tabY, tabWidth, tabHeight);
        pnlMain.add(tab1);
        lblTotalPatients = (JLabel) tab1.getComponent(1);
        
        JPanel tab2 = createTab("Active Admissions", "0", ColorPalette.mediumBlue);
        tab2.setBounds(startX + tabWidth + 20, tabY, tabWidth, tabHeight);
        pnlMain.add(tab2);
        lblActiveAdmissions = (JLabel) tab2.getComponent(1);
        
        JPanel tab3 = createTab("Available Beds", "50", ColorPalette.Green);
        tab3.setBounds(startX + (tabWidth + 20) * 2, tabY, tabWidth, tabHeight);
        pnlMain.add(tab3);
        
        JPanel tab4 = createTab("Today's Appointments", "24", ColorPalette.Yellow);
        tab4.setBounds(startX + (tabWidth + 20) * 3, tabY, tabWidth, tabHeight);
        pnlMain.add(tab4);
        
     
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        formPanel.setBounds(startX, 210, 500, 80);
        pnlMain.add(formPanel);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(15, 28, 50, 25);
        formPanel.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(65, 26, 150, 28);
        formPanel.add(txtName);
        
        JLabel lblRoom = new JLabel("Room:");
        lblRoom.setBounds(235, 28, 50, 25);
        formPanel.add(lblRoom);
        
        txtRoom = new JTextField();
        txtRoom.setBounds(285, 26, 80, 28);
        formPanel.add(txtRoom);
        
        JButton btnAdd = new JButton("Add Patient");
        btnAdd.setBackground(ColorPalette.Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(390, 22, 100, 35);
        btnAdd.addActionListener(e -> addPatient());
        formPanel.add(btnAdd);
        
       
        String[] cols = {"Name", "Room", "Status"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(ColorPalette.lightBlue);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(startX, 310, panelWidth - 60, 450);
        pnlMain.add(scroll);
        
       
        JTextField txtSearch = new JTextField("Search...");
        txtSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setBounds(startX, 800, 180, 28);
        txtSearch.addActionListener(e -> searchPatient(txtSearch.getText()));
        pnlMain.add(txtSearch);
        
        JLabel lblInfo = new JLabel("🌡️ 27°C  |  📅 " + LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblInfo.setBounds(1400, 805, 200, 25);
        lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlMain.add(lblInfo);
        
       
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(null);
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        btnPanel.setBounds(startX, 860, panelWidth - 60, 50);
        pnlMain.add(btnPanel);
        
        JButton btnAdmit = new JButton("Admit Selected");
        btnAdmit.setBackground(ColorPalette.darkBlue);
        btnAdmit.setForeground(Color.WHITE);
        btnAdmit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdmit.setFocusPainted(false);
        btnAdmit.setBounds(20, 10, 140, 30);
        btnAdmit.addActionListener(e -> admitPatient());
        btnPanel.add(btnAdmit);
        
        JButton btnDischarge = new JButton("Discharge Selected");
        btnDischarge.setBackground(ColorPalette.Yellow);
        btnDischarge.setForeground(Color.WHITE);
        btnDischarge.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDischarge.setFocusPainted(false);
        btnDischarge.setBounds(180, 10, 150, 30);
        btnDischarge.addActionListener(e -> dischargePatient());
        btnPanel.add(btnDischarge);
        
        JButton btnRemove = new JButton("Remove Selected");
        btnRemove.setBackground(ColorPalette.LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(350, 10, 140, 30);
        btnRemove.addActionListener(e -> removePatient());
        btnPanel.add(btnRemove);
        
        addSampleData();
    }
    
    private JPanel createTab(String title, String value, Color color) {
        JPanel tab = new JPanel();
        tab.setLayout(null);
        tab.setBackground(color);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(15, 20, 150, 20);
        tab.add(lblTitle);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(15, 50, 150, 40);
        tab.add(lblValue);
        
        return tab;
    }
    
    private void addPatient() {
        String name = txtName.getText().trim();
        String room = txtRoom.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name!");
            return;
        }
        tableModel.addRow(new Object[]{name, room, "Active"});
        txtName.setText("");
        txtRoom.setText("");
        updateSummary();
        JOptionPane.showMessageDialog(this, "Patient added!");
    }
    
    private void admitPatient() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.setValueAt("Admitted", row, 2);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Patient admitted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient!");
        }
    }
    
    private void dischargePatient() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.setValueAt("Discharged", row, 2);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Patient discharged!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient!");
        }
    }
    
    private void removePatient() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String name = tableModel.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Remove " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);
                updateSummary();
                JOptionPane.showMessageDialog(this, "Patient removed!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient!");
        }
    }
    
    private void searchPatient(String keyword) {
        if (keyword.isEmpty() || keyword.equals("Search...")) return;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = tableModel.getValueAt(i, 0).toString().toLowerCase();
            if (name.contains(keyword.toLowerCase())) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Patient not found!");
    }
    
    private void updateSummary() {
        int total = tableModel.getRowCount();
        int admitted = 0;
        for (int i = 0; i < total; i++) {
            if (tableModel.getValueAt(i, 2).equals("Admitted")) admitted++;
        }
        lblTotalPatients.setText(String.valueOf(total));
        lblActiveAdmissions.setText(String.valueOf(admitted));
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{"Jane Smith", "102", "Admitted"});
        tableModel.addRow(new Object[]{"Bob Johnson", "103", "Discharged"});
        tableModel.addRow(new Object[]{"Melvin", "321", "Active"});
        updateSummary();
    }
}