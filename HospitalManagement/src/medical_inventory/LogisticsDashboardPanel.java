package medical_inventory;

import Color_Palette.ColorPalette;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LogisticsDashboardPanel extends JPanel {
    
    private JPanel pnlMain;
    private DefaultTableModel tableModel;
    private JTextField txtItem, txtAmount;
    private JLabel lblPending, lblDelivered, lblTotalValue;
    private JTable table;
    
    public LogisticsDashboardPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(ColorPalette.veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        // TITLE
        JLabel title = new JLabel("Logistics Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(ColorPalette.darkBlue);
        title.setBounds(30, 20, 400, 40);
        pnlMain.add(title);
        
        // SUMMARY CARDS
        JPanel tab1 = createTab("Pending Orders", "0", ColorPalette.Yellow);
        tab1.setBounds(30, 80, 500, 100);
        pnlMain.add(tab1);
        lblPending = (JLabel) tab1.getComponent(1);
        
        JPanel tab2 = createTab("Delivered", "0", ColorPalette.Green);
        tab2.setBounds(550, 80, 500, 100);
        pnlMain.add(tab2);
        lblDelivered = (JLabel) tab2.getComponent(1);
        
        JPanel tab3 = createTab("Total Value", "₱0", ColorPalette.mediumBlue);
        tab3.setBounds(1070, 80, 500, 100);
        pnlMain.add(tab3);
        lblTotalValue = (JLabel) tab3.getComponent(1);
        
        // FORM PANEL
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        formPanel.setBounds(30, 210, 600, 80);
        pnlMain.add(formPanel);
        
        JLabel lblItem = new JLabel("Item:");
        lblItem.setBounds(15, 30, 50, 25);
        formPanel.add(lblItem);
        
        txtItem = new JTextField();
        txtItem.setBounds(60, 28, 180, 28);
        formPanel.add(txtItem);
        
        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(260, 30, 60, 25);
        formPanel.add(lblAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(325, 28, 100, 28);
        formPanel.add(txtAmount);
        
        JButton btnAdd = new JButton("Add Order");
        btnAdd.setBackground(ColorPalette.Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(450, 25, 130, 35);
        btnAdd.addActionListener(e -> addOrder());
        formPanel.add(btnAdd);
        
        // TABLE
        String[] cols = {"Item", "Amount", "Status"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(ColorPalette.lightBlue);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 310, 1560, 510);
        pnlMain.add(scroll);
        
        // BOTTOM BUTTONS
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(null);
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        btnPanel.setBounds(30, 860, 1560, 50);
        pnlMain.add(btnPanel);
        
        JButton btnDeliver = new JButton("Mark Delivered");
        btnDeliver.setBackground(ColorPalette.darkBlue);
        btnDeliver.setForeground(Color.WHITE);
        btnDeliver.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDeliver.setFocusPainted(false);
        btnDeliver.setBounds(20, 10, 140, 30);
        btnDeliver.addActionListener(e -> markDelivered());
        btnPanel.add(btnDeliver);
        
        JButton btnRemove = new JButton("Remove Order");
        btnRemove.setBackground(ColorPalette.LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(180, 10, 130, 30);
        btnRemove.addActionListener(e -> removeOrder());
        btnPanel.add(btnRemove);
        
        addSampleData();
    }
    
    private JPanel createTab(String title, String value, Color color) {
        JPanel tab = new JPanel();
        tab.setLayout(null);
        tab.setBackground(color);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 20, 200, 25);
        tab.add(lblTitle);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 50, 200, 40);
        tab.add(lblValue);
        
        return tab;
    }
    
    private void addOrder() {
        if (txtItem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter item name!");
            return;
        }
        tableModel.addRow(new Object[]{txtItem.getText(), "₱" + txtAmount.getText(), "Pending"});
        txtItem.setText("");
        txtAmount.setText("");
        updateSummary();
        JOptionPane.showMessageDialog(this, "Order added!");
    }
    
    private void markDelivered() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.setValueAt("Delivered", row, 2);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Order delivered!");
        } else {
            JOptionPane.showMessageDialog(this, "Select an order first!");
        }
    }
    
    private void removeOrder() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Order removed!");
        } else {
            JOptionPane.showMessageDialog(this, "Select an order first!");
        }
    }
    
    private void updateSummary() {
        int pending = 0, delivered = 0;
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = tableModel.getValueAt(i, 2).toString();
            if (status.equals("Pending")) pending++;
            if (status.equals("Delivered")) delivered++;
            String amt = tableModel.getValueAt(i, 1).toString().replace("₱", "");
            try { total += Double.parseDouble(amt); } catch (Exception e) {}
        }
        lblPending.setText(String.valueOf(pending));
        lblDelivered.setText(String.valueOf(delivered));
        lblTotalValue.setText("₱" + total);
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{"Surgical Masks", "₱5,000", "Pending"});
        tableModel.addRow(new Object[]{"Disposable Gloves", "₱3,000", "Delivered"});
        tableModel.addRow(new Object[]{"Medical Gauze", "₱5,000", "Pending"});
        updateSummary();
    }
}