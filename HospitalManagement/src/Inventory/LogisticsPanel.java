package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.LogisticsSQL;
import Database.InventorySQL;
import Models.LogisticsOrder;
import Models.InventoryItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogisticsPanel extends JPanel {
    
    private JPanel pnlMain, tabPend, tabDel, tabValue, pnlSelection, pnlBot, tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtItem, txtAmount;
    private JLabel lbltitle, lblDT, lblItem, lblAmount, lblPending, lblDelivered, lblTValue, lblTitle, lblValue;
    private JTable tblLogis;
    private JButton btnAdd, btnEdit, btnDeliver, btnRemove, btnAddToInventory;
    private JScrollPane srcLog;
    
    private String selectedOrderId = null;
    
    public LogisticsPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
      
        lbltitle = new JLabel("Logistics Dashboard");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1320, 20, 400, 40);
        pnlMain.add(lblDT);
        
        startClockTimer();
      
        tabPend = createTab("Pending Orders", "0", Yellow);
        tabPend.setBounds(30, 80, 500, 100);
        pnlMain.add(tabPend);
        lblPending = (JLabel) tabPend.getComponent(1);
        
        tabDel = createTab("Delivered", "0", Green);
        tabDel.setBounds(550, 80, 500, 100);
        pnlMain.add(tabDel);
        lblDelivered = (JLabel) tabDel.getComponent(1);
        
        tabValue = createTab("Total Value", "₱0", mediumBlue);
        tabValue.setBounds(1070, 80, 500, 100);
        pnlMain.add(tabValue);
        lblTValue = (JLabel) tabValue.getComponent(1);
        
        ((JLabel) tabValue.getComponent(1)).setBounds(20, 50, 450, 40);
     
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 1000, 80);
        pnlMain.add(pnlSelection);
        
        lblItem = new JLabel("Item:");
        lblItem.setBounds(15, 28, 80, 25);
        lblItem.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblItem);
        
        txtItem = new JTextField();
        txtItem.setBounds(100, 26, 200, 28);
        pnlSelection.add(txtItem);
        
        lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(330, 28, 100, 25);
        lblAmount.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(420, 26, 200, 28);
        pnlSelection.add(txtAmount);
        
        btnAdd = new JButton("Save Order");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(650, 22, 150, 35);
        btnAdd.addActionListener(e -> saveOrder());
        pnlSelection.add(btnAdd);
        
        btnEdit = new JButton("Edit Order");
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEdit.setFocusPainted(false);
        btnEdit.setBounds(820, 22, 150, 35);
        btnEdit.addActionListener(e -> editOrder());
        pnlSelection.add(btnEdit);
       
        String[] clm = {"Order ID", "Item", "Amount", "Status"};
        tblModel = new DefaultTableModel(clm, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblLogis = new JTable(tblModel);
        tblLogis.setRowHeight(35);
        tblLogis.setAutoCreateRowSorter(true);
        tblLogis.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblLogis.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblLogis.getTableHeader().setBackground(lightBlue);
        
        srcLog = new JScrollPane(tblLogis);
        srcLog.setBounds(30, 310, 1560, 510);
        pnlMain.add(srcLog);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);
        
        btnDeliver = new JButton("Mark Delivered");
        btnDeliver.setBackground(darkBlue);
        btnDeliver.setForeground(Color.WHITE);
        btnDeliver.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDeliver.setFocusPainted(false);
        btnDeliver.setBounds(20, 10, 140, 30);
        btnDeliver.addActionListener(e -> markDelivered());
        pnlBot.add(btnDeliver);
        
        btnRemove = new JButton("Remove Order");
        btnRemove.setBackground(LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(180, 10, 130, 30);
        btnRemove.addActionListener(e -> removeOrder());
        pnlBot.add(btnRemove);

        btnAddToInventory = new JButton("Add to Inventory");
        btnAddToInventory.setBackground(Green);
        btnAddToInventory.setForeground(Color.WHITE);
        btnAddToInventory.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAddToInventory.setFocusPainted(false);
        btnAddToInventory.setBounds(330, 10, 160, 30);
        btnAddToInventory.addActionListener(e -> openAddToInventoryModal());
        pnlBot.add(btnAddToInventory);
        
        refreshTableData();
    }
    
    private void openAddToInventoryModal() {
        int row = tblLogis.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select an item line from the logistics tracking table first!");
            return;
        }

        int modelRow = tblLogis.convertRowIndexToModel(row);
        String itemName = tblModel.getValueAt(modelRow, 1).toString();
        String rawAmount = tblModel.getValueAt(modelRow, 2).toString().replace("₱", "").replace(",", "").trim();

        JPanel dialogPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JComboBox<String> cmbCategory = new JComboBox<>(new String[]{"Medicine", "Equipment", "Supplies"});
        JTextField txtItemName = new JTextField(itemName);
        JTextField txtExpiryDate = new JTextField(LocalDate.now().toString());
        JTextField txtPrice = new JTextField(rawAmount);
        JTextField txtQty = new JTextField("1");

        dialogPanel.add(new JLabel("Category:"));
        dialogPanel.add(cmbCategory);
        dialogPanel.add(new JLabel("Item Name:"));
        dialogPanel.add(txtItemName);
        dialogPanel.add(new JLabel("Expiry (YYYY-MM-DD):"));
        dialogPanel.add(txtExpiryDate);
        dialogPanel.add(new JLabel("Unit/Total Price:"));
        dialogPanel.add(txtPrice);
        dialogPanel.add(new JLabel("Quantity:"));
        dialogPanel.add(txtQty);

        int result = JOptionPane.showConfirmDialog(this, dialogPanel, 
                "Inbound Intake: Add Logistics Order to Inventory Records", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String finalCategory = cmbCategory.getSelectedItem().toString();
            String finalItem = txtItemName.getText().trim();
            String finalExpiry = txtExpiryDate.getText().trim();
            String finalPriceStr = txtPrice.getText().trim();
            String finalQtyStr = txtQty.getText().trim();

            if (finalItem.isEmpty() || finalExpiry.isEmpty() || finalPriceStr.isEmpty() || finalQtyStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields inside the registration matrix are mandatory!");
                return;
            }

            try {
                int quantity = Integer.parseInt(finalQtyStr);
                double price = Double.parseDouble(finalPriceStr);
                
                String calculatedStatus = quantity < 50 ? "Low Stock" : "Good";
                try {
                    LocalDate expDate = LocalDate.parse(finalExpiry);
                    if (expDate.isBefore(LocalDate.now().plusDays(30))) {
                        calculatedStatus = "Expiring Soon";
                    }
                } catch (Exception dateEx) {
                    JOptionPane.showMessageDialog(this, "Date syntax evaluation mismatch error! Format: YYYY-MM-DD");
                    return;
                }

                InventoryItem newItem = new InventoryItem(finalCategory, finalItem, quantity, price, calculatedStatus, finalExpiry);
                if (InventorySQL.insertItem(newItem)) {
                    JOptionPane.showMessageDialog(this, "Logistics asset successfully registered to system inventory assets!");
                } else {
                    JOptionPane.showMessageDialog(this, "The inventory database subsystem rejected the input streaming array pipeline command.");
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Price and Quantity inputs must contain valid numeric expressions.");
            }
        }
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
        lblValue.setBounds(20, 50, 200, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }
    
    private void refreshTableData() {
        tblModel.setRowCount(0);
        List<LogisticsOrder> orders = LogisticsSQL.getAllOrders();
        
        double totalValue = 0.0;
        int pendingCount = 0;
        int deliveredCount = 0;

        for (LogisticsOrder order : orders) {
            tblModel.addRow(new Object[]{
                order.getOrderId(),
                order.getItem(),
                "₱" + String.format("%,.2f", order.getAmount()),
                order.getStatus()
            });
            
            if (order.getStatus().equals("Pending")) pendingCount++;
            if (order.getStatus().equals("Delivered")) deliveredCount++;
            totalValue += order.getAmount();
        }
        
        lblPending.setText(String.valueOf(pendingCount));
        lblDelivered.setText(String.valueOf(deliveredCount));
        lblTValue.setText("₱" + String.format("%,.2f", totalValue));
    }
    
    private void saveOrder() {
        String item = txtItem.getText().trim();
        String amtStr = txtAmount.getText().trim();

        if (item.isEmpty() || amtStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter item name and amount!");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amtStr.replace("₱", "").replace(",", "").trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount format!");
            return;
        }

        if (selectedOrderId != null) {
            LogisticsOrder updatedOrder = new LogisticsOrder(selectedOrderId, item, amount, "Pending");
            if (LogisticsSQL.updateOrder(updatedOrder)) {
                JOptionPane.showMessageDialog(this, "Order details successfully modified!");
                selectedOrderId = null;
            } else {
                JOptionPane.showMessageDialog(this, "Error executing query revisions.");
            }
        } else {
            int nextSeq = LogisticsSQL.getLastOrderSequenceNum() + 1;
            String generatedID = "ORD-" + nextSeq;
            
            LogisticsOrder newOrder = new LogisticsOrder(generatedID, item, amount, "Pending");
            if (LogisticsSQL.insertOrder(newOrder)) {
                JOptionPane.showMessageDialog(this, "Order submitted and tracking code generated: " + generatedID);
            } else {
                JOptionPane.showMessageDialog(this, "Database execution layer rejection encountered.");
            }
        }

        txtItem.setText("");
        txtAmount.setText("");
        refreshTableData();
    }
    
    private void editOrder() {
        int row = tblLogis.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblLogis.convertRowIndexToModel(row);
            
            selectedOrderId = tblModel.getValueAt(modelRow, 0).toString();
            txtItem.setText(tblModel.getValueAt(modelRow, 1).toString());
            txtAmount.setText(tblModel.getValueAt(modelRow, 2).toString().replace("₱", "").replace(",", ""));
            
            JOptionPane.showMessageDialog(this, "Modify the entries, then click Save Order to execute structural edits.");
        } else {
            JOptionPane.showMessageDialog(this, "Select an active row matrix element from the grid framework first!");
        }
    }

    private void markDelivered() {
        int row = tblLogis.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblLogis.convertRowIndexToModel(row);
            String orderId = tblModel.getValueAt(modelRow, 0).toString();
            
            if (LogisticsSQL.updateStatus(orderId, "Delivered")) {
                JOptionPane.showMessageDialog(this, "Order status shifted to Delivered!");
                refreshTableData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a shipment row line sequence first!");
        }
    }
    
    private void removeOrder() {
        int row = tblLogis.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblLogis.convertRowIndexToModel(row);
            String orderId = tblModel.getValueAt(modelRow, 0).toString();
            
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete order " + orderId + "?", 
                "Confirm Order Purge", JOptionPane.YES_NO_OPTION);
                
            if (confirmation == JOptionPane.YES_OPTION) {
                if (LogisticsSQL.deleteOrder(orderId)) {
                    JOptionPane.showMessageDialog(this, "Order safely removed from relational tracking lists.");
                    txtItem.setText("");
                    txtAmount.setText("");
                    selectedOrderId = null;
                    refreshTableData();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a target element row first!");
        }
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}