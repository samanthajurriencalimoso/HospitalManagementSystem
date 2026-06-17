package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.InventorySQL;
import Models.Item;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventoryPanel extends JPanel {
    
    private JPanel pnlMain, tabItem, tabLows, tabValue, pnlSelection, pnlBot, tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtItem, txtQty, txtPrice, txtExpiry;
    private JLabel lbltitle, lblDT, lblTItem, lblLStock, lblTValue, lblItem, lblQty, lblPrice, lblTitle, lblValue, lblCat, lblExpiry;
    private JTable tblInve;
    private JButton btnAdd, btnRestock, btnRemove;
    private JScrollPane srcInve;
    private JComboBox<String> cmbCategory;
    
    public InventoryPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lbltitle = new JLabel("Inventory Management");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        startClockTimer();
   
        tabItem = createTab("Total Items", "0", darkBlue);
        tabItem.setBounds(30, 80, 500, 100);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);
        
        tabLows = createTab("Low Stock", "0", Yellow);
        tabLows.setBounds(550, 80, 500, 100);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);
        
        tabValue = createTab("Total Value", "₱0", mediumBlue);
        tabValue.setBounds(1070, 80, 500, 100);
        pnlMain.add(tabValue);
        lblTValue = (JLabel) tabValue.getComponent(1);
      
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 1350, 120);
        pnlMain.add(pnlSelection);
        
        lblCat = new JLabel("Category:");
        lblCat.setBounds(15, 28, 100, 25);
        lblCat.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblCat);
        
        cmbCategory = new JComboBox<>(new String[]{"Medicine", "Equipment", "Supplies"});
        cmbCategory.setBounds(120, 26, 200, 28);
        pnlSelection.add(cmbCategory);
        
        lblItem = new JLabel("Item:");
        lblItem.setBounds(340, 28, 100, 25);
        lblItem.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblItem);
        
        txtItem = new JTextField();
        txtItem.setBounds(445, 26, 200, 28);
        pnlSelection.add(txtItem);
        
        lblExpiry = new JLabel("Expiry (YYYY-MM-DD):");
        lblExpiry.setBounds(665, 28, 200, 25);
        pnlSelection.add(lblExpiry);

        txtExpiry = new JTextField();
        txtExpiry.setBounds(840, 26, 200, 28);
        pnlSelection.add(txtExpiry);
        
        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(15, 70, 100, 25);
        lblPrice.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblPrice);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(120, 70, 200, 28);
        pnlSelection.add(txtPrice);
        
        lblQty = new JLabel("Qty:");
        lblQty.setBounds(340, 70, 200, 25);
        lblQty.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblQty);
        
        txtQty = new JTextField();
        txtQty.setBounds(445, 70, 200, 28);
        pnlSelection.add(txtQty);
        
        btnAdd = new JButton("Add Item");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(1080, 25, 150, 35);
        btnAdd.addActionListener(e -> saveItem());
        pnlSelection.add(btnAdd);

        String[] clm = {"ID", "Category", "Item", "Quantity", "Price", "Status", "Expiry"};
        tblModel = new DefaultTableModel(clm, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblInve = new JTable(tblModel);
        tblInve.setRowHeight(35);
        tblInve.setAutoCreateRowSorter(true);
        tblInve.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblInve.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblInve.getTableHeader().setBackground(lightBlue);
        
        tblInve.removeColumn(tblInve.getColumnModel().getColumn(0));
        
        srcInve = new JScrollPane(tblInve);
        srcInve.setBounds(30, 350, 1560, 500);
        pnlMain.add(srcInve);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);
        
        btnRestock = new JButton("Add Stock");
        btnRestock.setBackground(mediumBlue);
        btnRestock.setForeground(Color.WHITE);
        btnRestock.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRestock.setFocusPainted(false);
        btnRestock.setBounds(20, 10, 120, 30);
        btnRestock.addActionListener(e -> restockItem());
        pnlBot.add(btnRestock);
        
        btnRemove = new JButton("Remove");
        btnRemove.setBackground(LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(160, 10, 120, 30);
        btnRemove.addActionListener(e -> removeItem());
        pnlBot.add(btnRemove);
        
        refreshTableData();
    }
    
    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel();
        tabUpdate.setLayout(null);
        tabUpdate.setBackground(color);
        
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 20, 250, 25);
        tabUpdate.add(lblTitle);
        
        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 50, 150, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }
    
    private void refreshTableData() {
        tblModel.setRowCount(0);
        List<Item> items = InventorySQL.getAllItems();
        
        int totalItems = 0;
        int lowStockCount = 0;
        double totalValue = 0;
        
        for (Item item : items) {
            tblModel.addRow(new Object[]{
                item.getId(),
                item.getCategory(),
                item.getItem(),
                item.getQuantity(),
                "₱" + String.format("%.2f", item.getPrice()), 
                item.getStatus(),
                item.getExpiry()
            });
            
            totalItems++;
            if (item.getStatus().contains("Low") || item.getStatus().equals("Out of Stock")) {
                lowStockCount++;
            }
            totalValue += (item.getPrice() * item.getQuantity());
        }
        
        lblTItem.setText(String.valueOf(totalItems));
        lblLStock.setText(String.valueOf(lowStockCount));
        
        java.text.NumberFormat phFormat = java.text.NumberFormat.getInstance(java.util.Locale.US);
        phFormat.setMinimumFractionDigits(2);
        phFormat.setMaximumFractionDigits(2);

        lblTValue.setText("₱ " + phFormat.format(totalValue));
    }
    
    private void saveItem() {
        String category = cmbCategory.getSelectedItem().toString();
        String item = txtItem.getText().trim();
        String qtyStr = txtQty.getText().trim();
        String priceStr = txtPrice.getText().trim();
        String expiry = txtExpiry.getText().trim();

        if (item.isEmpty() || qtyStr.isEmpty() || priceStr.isEmpty() || expiry.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        int qty;
        double price;
        try {
            qty = Integer.parseInt(qtyStr);
            price = Double.parseDouble(priceStr.replace("₱", "").replace(",", "").trim());
            if (qty <= 0 || price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Quantity and Price must be valid positive numbers.");
            return;
        }

        String status = qty < 50 ? "Low Stock" : "Good";
        try {
            LocalDate expDate = LocalDate.parse(expiry);
            if (expDate.isBefore(LocalDate.now().plusDays(30))) {
                status = "Expiring Soon";
            }
            if (expDate.isBefore(LocalDate.now())) {
                status = "Expired";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid expiry date format (use YYYY-MM-DD).");
            return;
        }
        
        Item newItem = new Item(category, item, qty, price, status, expiry);
        if (InventorySQL.insertItem(newItem)) {
            JOptionPane.showMessageDialog(this, "Item added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error inserting item into database.");
        }

        clearFields();
        refreshTableData();
    }
    
    private void restockItem() {
        int row = tblInve.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblInve.convertRowIndexToModel(row);
            int id = (Integer) tblModel.getValueAt(modelRow, 0);
            int current = (int) tblModel.getValueAt(modelRow, 3);
            String itemName = tblModel.getValueAt(modelRow, 2).toString();
            
            String addStr = JOptionPane.showInputDialog(this, "Current Stock: " + current + "\nEnter quantity to add:");
            if (addStr != null && !addStr.trim().isEmpty()) {
                try {
                    int add = Integer.parseInt(addStr.trim());
                    if (add <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than zero!");
                        return;
                    }
                    int newQty = current + add;
                    String newStatus = newQty == 0 ? "Out of Stock" : (newQty < 50 ? "Low Stock" : "Good");
                    
                    if (InventorySQL.updateQuantityAndStatus(id, newQty, newStatus)) {
                        JOptionPane.showMessageDialog(this, "Stock added! New Quantity: " + newQty);
                        refreshTableData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update stock!");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Invalid number entered!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an item first!");
        }
    }
    
    private void removeItem() {
        int row = tblInve.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblInve.convertRowIndexToModel(row);
            int id = (Integer) tblModel.getValueAt(modelRow, 0);
            String itemName = tblModel.getValueAt(modelRow, 2).toString();
            
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to permanently remove " + itemName + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                if (InventorySQL.deleteItem(id)) {
                    JOptionPane.showMessageDialog(this, "Item successfully removed!");
                    clearFields();
                    refreshTableData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove item!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an item first!");
        }
    }
    
    private void clearFields() {
        txtItem.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtExpiry.setText("");
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}