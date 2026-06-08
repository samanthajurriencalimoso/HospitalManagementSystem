package Inventory;

import static Color_Palette.ColorPalette.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class RequestPanel_Nurse extends JPanel {
    
    private JPanel pnlMain,tabItem,tabLows,tabValue,pnlSelection,pnlBot,tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtQty;
    private JLabel lblTItem, lblLStock, lblTValue,lbltitle,lblDT,lblCate,lblItem,lblQty,lblPrice,lblTitle,lblValue;
    private JTable tblInve;
    private JButton btnAdd, btnQty, btnRemove;
    private JComboBox<String> cmbCate, cmbItem;
    private JScrollPane srcInve;
    
    private static final String[][] CATEGORY_ITEMS = {
        // List of Medication
        {"Paracetamol", "Ibuprofen", "Amoxicillin", "Metformin", "Amlodipine",
         "Omeprazole", "Cetirizine", "Azithromycin", "Losartan", "Aspirin"},
        // List of Equipment
        {"Stethoscope", "Blood Pressure Monitor", "Thermometer", "Pulse Oximeter",
         "Otoscope", "Syringe", "IV Drip Stand", "Defibrillator", "Glucometer", "Nebulizer"},
        // List ofSupplies
        {"Surgical Gloves", "Face Mask", "Gauze Pad", "Bandage Roll", "Alcohol Wipes",
         "Cotton Balls", "Adhesive Plaster", "Tongue Depressor", "Specimen Cup", "Sterile Drape"}
    };
    
    public RequestPanel_Nurse() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lbltitle = new JLabel("Medical Supply Request");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
   
        tabItem = createTab("Total Requested Items", "0", darkBlue);
        tabItem.setBounds(30, 80, 500, 100);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);
        
        tabLows = createTab("Total Received Items", "0", Yellow);
        tabLows.setBounds(550, 80, 500, 100);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);
        
        tabValue = createTab("Total Pending Items", "0", mediumBlue);
        tabValue.setBounds(1070, 80, 500, 100);
        pnlMain.add(tabValue);
        lblTValue = (JLabel) tabValue.getComponent(1);
      
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 900, 80);
        pnlMain.add(pnlSelection);
        
        lblCate = new JLabel("Category:");
        lblCate.setBounds(15, 28, 85, 25);
        lblCate.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblCate);
        
        cmbCate = new JComboBox<>(new String[]{"Medication", "Equipment", "Supplies"});
        cmbCate.setBounds(100, 26, 180, 28);
        cmbCate.addActionListener(ae -> refreshItemComboBox());
        pnlSelection.add(cmbCate);
        
        lblItem = new JLabel("Item:");
        lblItem.setBounds(300, 28, 50, 25);
        lblItem.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblItem);
        
        cmbItem = new JComboBox<>(CATEGORY_ITEMS[0]);
        cmbItem.setBounds(355, 26, 220, 28);
        pnlSelection.add(cmbItem);
        
        lblQty = new JLabel("Qty:");
        lblQty.setBounds(595, 28, 50, 25);
        lblQty.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblQty);
        
        txtQty = new JTextField();
        txtQty.setBounds(645, 26, 120, 28);
        pnlSelection.add(txtQty);
        
        btnAdd = new JButton("Add Item");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(785, 22, 100, 35);
        btnAdd.addActionListener(e -> addItem());
        pnlSelection.add(btnAdd);

        String[] clm = {"Category", "Item", "Quantity", "Status"};
        tblModel = new DefaultTableModel(clm, 0);
        tblInve = new JTable(tblModel);
        tblInve.setRowHeight(35);
        tblInve.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblInve.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblInve.getTableHeader().setBackground(lightBlue);
        
        srcInve = new JScrollPane(tblInve);
        srcInve.setBounds(30, 310, 1560, 510);
        pnlMain.add(srcInve);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);
        
        btnQty = new JButton("Add Quantity");
        btnQty.setBackground(mediumBlue);
        btnQty.setForeground(Color.WHITE);
        btnQty.setFont(new Font("Calibri", Font.BOLD, 14));
        btnQty.setFocusPainted(false);
        btnQty.setBounds(20, 10, 130, 30);
        btnQty.addActionListener(e -> requestedItem());
        pnlBot.add(btnQty);
        
        btnRemove = new JButton("Remove");
        btnRemove.setBackground(LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(170, 10, 120, 30);
        btnRemove.addActionListener(e -> removeItem());
        pnlBot.add(btnRemove);
        
    }
    
    private void refreshItemComboBox() {
        int index = cmbCate.getSelectedIndex();
        cmbItem.removeAllItems();
        for (String item : CATEGORY_ITEMS[index]) {
            cmbItem.addItem(item);
        }
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
    
    private void addItem() {
        String category = cmbCate.getSelectedItem().toString();
        String item     = cmbItem.getSelectedItem().toString();
        String qtyText  = txtQty.getText().trim();
        
        if (qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity!",
                "Missing Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int qty;
            try {
                qty = Integer.parseInt(qtyText);
                if (qty <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be greater than zero!",
                        "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Quantity must be a valid number!",
                    "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (int i = 0; i < tblModel.getRowCount(); i++) {
                if (tblModel.getValueAt(i, 0).toString().equals(category) &&
                    tblModel.getValueAt(i, 1).toString().equals(item)) {
                    JOptionPane.showMessageDialog(this,
                        item + " already exists under " + category + ".\nUse 'Add Quantity' to update it instead.",
                        "Duplicate Item", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            String status = qty < 50 ? "Pending" : "Requested";
            tblModel.addRow(new Object[]{category, item, qty, status});

            txtQty.setText("");
            cmbCate.setSelectedIndex(0);
            refreshItemComboBox();
            updateSummary();
            JOptionPane.showMessageDialog(this, "Item requested successfully!");
        }
    
    private void requestedItem() {
        int row = tblInve.getSelectedRow();
        if (row >= 0) {
            int current = Integer.parseInt(tblModel.getValueAt(row, 2).toString());
            String addStr = JOptionPane.showInputDialog(this, "Current: " + current + "\nAdd:");
            if (addStr != null) {
                try {
                    int add = Integer.parseInt(addStr);
                    int newQty = current + add;
                    tblModel.setValueAt(newQty, row, 2);
                    
                    double UPrice = Double.parseDouble(tblModel.getValueAt(row, 3).toString().replace("₱", ""));
                    double TCost = UPrice * newQty;
                    tblModel.setValueAt("₱" + TCost, row, 4);
                    
                    if (newQty < 50) {
                        tblModel.setValueAt("Requested - Low Stock", row, 5);
                    } else {
                        tblModel.setValueAt("Fulfilled", row, 5);
                    }
                    updateSummary();
                    JOptionPane.showMessageDialog(this, "Restock processed! New Qty: " + newQty);
                } catch (Exception e) {}
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an item first!");
        }
    }
    
    private void addQuantity() {
        int row = tblInve.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an item first!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int current = Integer.parseInt(tblModel.getValueAt(row, 2).toString());
        String addStr = JOptionPane.showInputDialog(this, "Item: " + tblModel.getValueAt(row, 1) + "\nCurrent Qty: " + current + "\nAdd Qty:");

        if (addStr == null) return; 

        addStr = addStr.trim();
        if (addStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity to add!", "Missing Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int add;
        try {
            add = Integer.parseInt(addStr);
            if (add <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity to add must be greater than zero!", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int newQty = current + add;
        tblModel.setValueAt(newQty, row, 2);
        tblModel.setValueAt(newQty < 50 ? "Pending" : "Received", row, 3);

        updateSummary();
        JOptionPane.showMessageDialog(this, "Quantity updated! New Qty: " + newQty);
    }
    
    private void removeItem() {
        int row = tblInve.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an item first!",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String itemName = tblModel.getValueAt(row, 1).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
            "Remove \"" + itemName + "\" from the request list?",
            "Confirm Removal", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            tblModel.removeRow(row);
            updateSummary();
            JOptionPane.showMessageDialog(this, itemName + " removed successfully!");
        }
    }
    
    private void updateSummary() {
        int total    = tblModel.getRowCount();
        int received = 0;
        int pending  = 0;

        for (int i = 0; i < total; i++) {
            String status = tblModel.getValueAt(i, 3).toString();
            if (status.equals("Received")) received++;
            if (status.equals("Pending"))  pending++;
        }

        lblTItem.setText(String.valueOf(total));
        lblLStock.setText(String.valueOf(received));
        lblTValue.setText(String.valueOf(pending));
    }
}