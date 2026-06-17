package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.InventorySQL;
import Database.MedicalRequestSQL;
import Database.UserManagementSQL;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestPanel_Nurse extends JPanel {

    private JPanel pnlMain, tabItem, tabLows, tabValue, pnlSelection, pnlBot, tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtQty;
    private JLabel lblTItem, lblLStock, lblTValue, lbltitle, lblDT, lblCate, lblItem, lblQty, lblTitle, lblValue;
    private JTable tblInve;
    private JButton btnAdd, btnRefresh;
    private JComboBox<String> cmbCate;
    private JComboBox<InventoryItemOption> cmbItem;
    private JScrollPane srcInve;
    private String currentUserId;

    private class InventoryItemOption {
        int id;
        String name;
        int stock;
        String expiry;
        
        InventoryItemOption(int id, String name, int stock, String expiry) {
            this.id = id;
            this.name = name;
            this.stock = stock;
            this.expiry = expiry;
        }
        
        @Override
        public String toString() {
            return name + " (Exp: " + expiry + ", Stock: " + stock + ")";
        }
    }

    public RequestPanel_Nurse() {
        if (UserManagementSQL.currentEmployee != null) {
            currentUserId = UserManagementSQL.currentEmployee.getId();
        } else {
            currentUserId = "NRS001";
        }

        setLayout(null);
        setBounds(0, 0, 1620, 930);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        lbltitle = new JLabel("Medical Supply Request - Nurse");
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

        tabItem = createTab("Total Requests", "0", darkBlue);
        tabItem.setBounds(30, 80, 500, 100);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);

        tabLows = createTab("Received", "0", Yellow);
        tabLows.setBounds(550, 80, 500, 100);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);

        tabValue = createTab("Pending", "0", mediumBlue);
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

        cmbCate = new JComboBox<>(new String[]{"Medicine", "Equipment", "Supplies"});
        cmbCate.setBounds(100, 26, 180, 28);
        cmbCate.addActionListener(ae -> refreshItemComboBox());
        pnlSelection.add(cmbCate);

        lblItem = new JLabel("Item:");
        lblItem.setBounds(300, 28, 50, 25);
        lblItem.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblItem);

        cmbItem = new JComboBox<>();
        cmbItem.setBounds(355, 26, 220, 28);
        pnlSelection.add(cmbItem);

        lblQty = new JLabel("Qty:");
        lblQty.setBounds(595, 28, 50, 25);
        lblQty.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(645, 26, 120, 28);
        pnlSelection.add(txtQty);

        btnAdd = new JButton("Request Item");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(785, 22, 100, 35);
        btnAdd.addActionListener(e -> addItem());
        pnlSelection.add(btnAdd);

        String[] clm = {"#", "Category", "Item", "Quantity", "Amount", "Status"};
        tblModel = new DefaultTableModel(clm, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblInve = new JTable(tblModel);
        tblInve.setRowHeight(35);
        tblInve.setAutoCreateRowSorter(true);
        tblInve.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblInve.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblInve.getTableHeader().setBackground(lightBlue);
        tblInve.getColumnModel().getColumn(0).setMaxWidth(50);

        srcInve = new JScrollPane(tblInve);
        srcInve.setBounds(30, 310, 1560, 510);
        pnlMain.add(srcInve);

        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(20, 10, 120, 30);
        btnRefresh.setBackground(mediumBlue);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(e -> loadRequests());
        pnlBot.add(btnRefresh);

        refreshItemComboBox();
        loadRequests();
    }

    private void refreshItemComboBox() {
        cmbItem.removeAllItems();
        String category = cmbCate.getSelectedItem().toString();
        
        String sql = "SELECT id, item, quantity, expiry FROM inventory WHERE category = ? AND quantity > 0 ORDER BY expiry ASC";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement", "root", "");
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("item");
                int stock = rs.getInt("quantity");
                String expiry = rs.getString("expiry");
                cmbItem.addItem(new InventoryItemOption(id, name, stock, expiry));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (cmbItem.getItemCount() == 0) {
            cmbItem.addItem(new InventoryItemOption(-1, "No items available", 0, ""));
        }
    }

    private void loadRequests() {
        tblModel.setRowCount(0);
        
        if (currentUserId == null) {
            lblTItem.setText("0");
            lblLStock.setText("0");
            lblTValue.setText("0");
            return;
        }
        
        List<Object[]> requests = MedicalRequestSQL.getUserRequests(currentUserId);
        int total = 0, received = 0, pending = 0;
        for (Object[] req : requests) {
            tblModel.addRow(req);
            total++;
            String status = (String) req[6];
            if (status.equalsIgnoreCase("Received")) received++;
            if (status.equalsIgnoreCase("Pending")) pending++;
        }
        lblTItem.setText(String.valueOf(total));
        lblLStock.setText(String.valueOf(received));
        lblTValue.setText(String.valueOf(pending));
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

    private double getPriceFromInventory(int itemId) {
        String sql = "SELECT price FROM inventory WHERE id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement", "root", "");
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private boolean decreaseStockById(int itemId, int requestedQty) {
        String selectSql = "SELECT quantity FROM inventory WHERE id = ?";
        String updateSql = "UPDATE inventory SET quantity = ? WHERE id = ?";
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement", "root", "")) {
            con.setAutoCommit(false);
            
            int currentQty = 0;
            try (PreparedStatement stmt = con.prepareStatement(selectSql)) {
                stmt.setInt(1, itemId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    currentQty = rs.getInt("quantity");
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found in inventory!");
                    return false;
                }
            }
            
            if (currentQty < requestedQty) {
                JOptionPane.showMessageDialog(null, "Insufficient stock! Available: " + currentQty + ", Requested: " + requestedQty);
                return false;
            }
            
            int newQty = currentQty - requestedQty;
            String newStatus = newQty == 0 ? "Out of Stock" : (newQty < 50 ? "Low Stock" : "Good");
            
            try (PreparedStatement stmt = con.prepareStatement(updateSql)) {
                stmt.setInt(1, newQty);
                stmt.setInt(2, itemId);
                stmt.executeUpdate();
            }
            
            con.commit();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addItem() {
        InventoryItemOption selectedItem = (InventoryItemOption) cmbItem.getSelectedItem();
        if (selectedItem == null || selectedItem.id == -1) {
            JOptionPane.showMessageDialog(this, "Please select a valid item!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String category = cmbCate.getSelectedItem().toString();
        String itemName = selectedItem.name;
        int itemId = selectedItem.id;
        int currentStock = selectedItem.stock;
        String qtyText = txtQty.getText().trim();

        if (qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity!", "Missing Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qtyText);
            if (qty <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero!", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be a valid number!", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (currentStock < qty) {
            JOptionPane.showMessageDialog(this, "Insufficient stock!\nAvailable: " + currentStock + "\nRequested: " + qty, "Stock Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double pricePerUnit = getPriceFromInventory(itemId);
        double billingAmount = pricePerUnit * qty;

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Request Details:\nItem: " + itemName + "\nExpiry: " + selectedItem.expiry + "\nQuantity: " + qty + 
            "\nUnit Price: ₱" + pricePerUnit + "\nTotal: ₱" + billingAmount + 
            "\n\nConfirm request?", "Confirm Request", JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (decreaseStockById(itemId, qty)) {
            String status = "Pending";
            
            if (MedicalRequestSQL.addRequestWithBilling(category, itemName, qty, status, currentUserId, billingAmount)) {
                loadRequests();
                txtQty.setText("");
                refreshItemComboBox();
                JOptionPane.showMessageDialog(this, "Request submitted! Stock updated. Remaining: " + (currentStock - qty));
            } else {
                JOptionPane.showMessageDialog(this, "Failed to record request in database!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update inventory stock!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}