package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.MedicalRequestSQL;
import Database.UserManagementSQL;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestPanel_Doctor extends JPanel {

    private JPanel pnlMain, tabItem, tabLows, tabValue, pnlSelection, pnlBot, tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtQty;
    private JLabel lblTItem, lblLStock, lblTValue, lbltitle, lblDT, lblCate, lblItem, lblQty, lblTitle, lblValue;
    private JTable tblInve;
    private JButton btnAdd, btnQty, btnRemove;
    private JComboBox<String> cmbCate, cmbItem;
    private JScrollPane srcInve;

    private final String currentUserId;

    private static final String[][] CATEGORY_ITEMS = {
        {"Paracetamol", "Ibuprofen", "Amoxicillin", "Metformin", "Amlodipine",
         "Omeprazole", "Cetirizine", "Azithromycin", "Losartan", "Aspirin"},
        {"Stethoscope", "Blood Pressure Monitor", "Thermometer", "Pulse Oximeter",
         "Otoscope", "Syringe", "IV Drip Stand", "Defibrillator", "Glucometer", "Nebulizer"},
        {"Surgical Gloves", "Face Mask", "Gauze Pad", "Bandage Roll", "Alcohol Wipes",
         "Cotton Balls", "Adhesive Plaster", "Tongue Depressor", "Specimen Cup", "Sterile Drape"}
    };

    public RequestPanel_Doctor() {
        this.currentUserId = UserManagementSQL.currentEmployee.getId();
        MedicalRequestSQL.ensureRequestedByColumn();

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

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

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

        String[] clm = {"#", "Category", "Item", "Quantity", "Billing Amount", "Status"};
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

        btnQty = new JButton("Add Quantity");
        btnQty.setBackground(mediumBlue);
        btnQty.setForeground(Color.WHITE);
        btnQty.setFont(new Font("Calibri", Font.BOLD, 14));
        btnQty.setFocusPainted(false);
        btnQty.setBounds(20, 10, 130, 30);
        btnQty.addActionListener(e -> addQuantity());
        pnlBot.add(btnQty);

        btnRemove = new JButton("Remove");
        btnRemove.setBackground(LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(170, 10, 120, 30);
        btnRemove.addActionListener(e -> removeItem());
        pnlBot.add(btnRemove);

        loadDataFromDatabase();
    }

    private void refreshItemComboBox() {
        int index = cmbCate.getSelectedIndex();
        if (index < 0) return;
        cmbItem.removeAllItems();
        for (String item : CATEGORY_ITEMS[index]) {
            cmbItem.addItem(item);
        }
    }

    private void loadDataFromDatabase() {
        tblModel.setRowCount(0);
        List<Object[]> data = MedicalRequestSQL.getRequestsByUser(currentUserId);
        for (Object[] row : data) {
            tblModel.addRow(row);
        }
        updateSummary();
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

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            if (tblModel.getValueAt(i, 1).toString().equals(category) &&
                tblModel.getValueAt(i, 2).toString().equals(item)) {
                JOptionPane.showMessageDialog(this,
                    item + " already exists under " + category + ".\nUse 'Add Quantity' to update it instead.",
                    "Duplicate Item", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String status = "Pending";

        if (MedicalRequestSQL.addRequest(category, item, qty, status, currentUserId)) {
            loadDataFromDatabase();
            txtQty.setText("");
            cmbCate.setSelectedIndex(0);
            refreshItemComboBox();
            JOptionPane.showMessageDialog(this, "Request submitted to Admin and routed to Logistics pipeline!");
        } else {
            JOptionPane.showMessageDialog(this, "Database entry write transaction failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addQuantity() {
        int viewRow = tblInve.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Select an item first!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = tblInve.convertRowIndexToModel(viewRow);
        int targetDbId      = Integer.parseInt(tblModel.getValueAt(row, 6).toString());
        int current         = Integer.parseInt(tblModel.getValueAt(row, 3).toString());
        String currentStatus = tblModel.getValueAt(row, 5).toString();
        String itemName     = tblModel.getValueAt(row, 2).toString();

        String addStr = JOptionPane.showInputDialog(this,
            "Item: " + itemName + "\nCurrent Qty: " + current + "\nAdd Qty:");
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
        if (currentStatus.equals("Received")) currentStatus = "Pending";

        if (MedicalRequestSQL.updateQuantity(targetDbId, newQty, currentStatus)) {
            loadDataFromDatabase();
            JOptionPane.showMessageDialog(this, "Quantity addition synchronized successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Database modification error tracking update requests.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeItem() {
        int viewRow = tblInve.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Select an item first!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = tblInve.convertRowIndexToModel(viewRow);

        int targetDbId  = Integer.parseInt(tblModel.getValueAt(row, 6).toString());
        String itemName = tblModel.getValueAt(row, 2).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
            "Remove \"" + itemName + "\" from the request list?",
            "Confirm Removal", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            if (MedicalRequestSQL.deleteRequest(targetDbId)) {
                loadDataFromDatabase();
                JOptionPane.showMessageDialog(this, itemName + " deleted from active records tracking system.");
            } else {
                JOptionPane.showMessageDialog(this, "Database delete query execution failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateSummary() {
        int total    = tblModel.getRowCount();
        int received = 0;
        int pending  = 0;

        for (int i = 0; i < total; i++) {
            String status = tblModel.getValueAt(i, 5).toString();
            if (status.equalsIgnoreCase("Received")) received++;
            if (status.equalsIgnoreCase("Pending"))  pending++;
        }

        lblTItem.setText(String.valueOf(total));
        lblLStock.setText(String.valueOf(received));
        lblTValue.setText(String.valueOf(pending));
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}