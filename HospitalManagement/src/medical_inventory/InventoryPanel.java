package medical_inventory;

import Color_Palette.ColorPalette;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryPanel extends JPanel {
    
    private JPanel pnlMain;
    private DefaultTableModel tableModel;
    private JTextField txtItem, txtQty, txtPrice;
    private JLabel lblTotalItems, lblLowStock, lblTotalValue;
    private JTable table;
    
    public InventoryPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(ColorPalette.veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        // TITLE
        JLabel title = new JLabel("Inventory Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(ColorPalette.darkBlue);
        title.setBounds(30, 20, 400, 40);
        pnlMain.add(title);
        
        // SUMMARY CARDS
        JPanel tab1 = createTab("Total Items", "0", ColorPalette.darkBlue);
        tab1.setBounds(30, 80, 500, 100);
        pnlMain.add(tab1);
        lblTotalItems = (JLabel) tab1.getComponent(1);
        
        JPanel tab2 = createTab("Low Stock", "0", ColorPalette.Yellow);
        tab2.setBounds(550, 80, 500, 100);
        pnlMain.add(tab2);
        lblLowStock = (JLabel) tab2.getComponent(1);
        
        JPanel tab3 = createTab("Total Value", "₱0", ColorPalette.mediumBlue);
        tab3.setBounds(1070, 80, 500, 100);
        pnlMain.add(tab3);
        lblTotalValue = (JLabel) tab3.getComponent(1);
        
        // FORM PANEL
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(ColorPalette.borderLBLUE));
        formPanel.setBounds(30, 210, 750, 80);
        pnlMain.add(formPanel);
        
        JLabel lblItem = new JLabel("Item:");
        lblItem.setBounds(15, 30, 50, 25);
        formPanel.add(lblItem);
        
        txtItem = new JTextField();
        txtItem.setBounds(60, 28, 150, 28);
        formPanel.add(txtItem);
        
        JLabel lblQty = new JLabel("Qty:");
        lblQty.setBounds(230, 30, 40, 25);
        formPanel.add(lblQty);
        
        txtQty = new JTextField();
        txtQty.setBounds(270, 28, 80, 28);
        formPanel.add(txtQty);
        
        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(370, 30, 50, 25);
        formPanel.add(lblPrice);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(420, 28, 100, 28);
        formPanel.add(txtPrice);
        
        JButton btnAdd = new JButton("Add Item");
        btnAdd.setBackground(ColorPalette.Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(550, 25, 120, 35);
        btnAdd.addActionListener(e -> addItem());
        formPanel.add(btnAdd);
        
        // TABLE
        String[] cols = {"Item", "Quantity", "Price", "Status"};
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
        
        JButton btnRestock = new JButton("Restock");
        btnRestock.setBackground(ColorPalette.mediumBlue);
        btnRestock.setForeground(Color.WHITE);
        btnRestock.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRestock.setFocusPainted(false);
        btnRestock.setBounds(20, 10, 120, 30);
        btnRestock.addActionListener(e -> restockItem());
        btnPanel.add(btnRestock);
        
        JButton btnRemove = new JButton("Remove");
        btnRemove.setBackground(ColorPalette.LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(160, 10, 120, 30);
        btnRemove.addActionListener(e -> removeItem());
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
    
    private void addItem() {
        if (txtItem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter item name!");
            return;
        }
        int qty = 0;
        try { qty = Integer.parseInt(txtQty.getText()); } catch (Exception e) {}
        double price = 0;
        try { price = Double.parseDouble(txtPrice.getText()); } catch (Exception e) {}
        
        String status = qty < 50 ? "Low Stock" : "Good";
        tableModel.addRow(new Object[]{txtItem.getText(), qty, "$" + price, status});
        
        txtItem.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        updateSummary();
        JOptionPane.showMessageDialog(this, "Item added!");
    }
    
    private void restockItem() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int current = (int) tableModel.getValueAt(row, 1);
            String addStr = JOptionPane.showInputDialog(this, "Current: " + current + "\nAdd:");
            if (addStr != null) {
                try {
                    int add = Integer.parseInt(addStr);
                    int newQty = current + add;
                    tableModel.setValueAt(newQty, row, 1);
                    tableModel.setValueAt(newQty < 50 ? "Low Stock" : "Good", row, 3);
                    updateSummary();
                    JOptionPane.showMessageDialog(this, "Restocked! New: " + newQty);
                } catch (Exception e) {}
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an item first!");
        }
    }
    
    private void removeItem() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            updateSummary();
            JOptionPane.showMessageDialog(this, "Item removed!");
        } else {
            JOptionPane.showMessageDialog(this, "Select an item first!");
        }
    }
    
    private void updateSummary() {
        int total = tableModel.getRowCount();
        int low = 0;
        double value = 0;
        for (int i = 0; i < total; i++) {
            if (tableModel.getValueAt(i, 3).toString().contains("Low")) low++;
            String price = tableModel.getValueAt(i, 2).toString().replace("₱", "");
            try { value += Double.parseDouble(price); } catch (Exception e) {}
        }
        lblTotalItems.setText(String.valueOf(total));
        lblLowStock.setText(String.valueOf(low));
        lblTotalValue.setText("$" + value);
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{"Paracetamol", 500, "₱2500", "Good"});
        tableModel.addRow(new Object[]{"Amoxicillin", 300, "₱4500", "Good"});
        tableModel.addRow(new Object[]{"Ibuprofen", 45, "₱1500", "Low Stock"});
        updateSummary();
    }
}