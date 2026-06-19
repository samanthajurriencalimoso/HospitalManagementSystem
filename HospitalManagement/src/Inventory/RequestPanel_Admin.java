package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.MedicalRequestSQL;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestPanel_Admin extends JPanel {

    private JPanel pnlMain;
    private JLabel lbltitle, lblDT;
    private JTable tblRequests;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton btnRefresh, btnProcess;

    public RequestPanel_Admin() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        lbltitle = new JLabel("Request Management");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 28));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 300, 50);
        pnlMain.add(lbltitle);

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

        String[] columns = {"#", "Request ID", "Category", "Item", "Quantity", "Requested By", "Amount", "Status", "Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblRequests = new JTable(tableModel);
        tblRequests.setRowHeight(40);
        tblRequests.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblRequests.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblRequests.getTableHeader().setBackground(lightBlue);
        tblRequests.getColumnModel().getColumn(0).setMaxWidth(50);
        tblRequests.getColumnModel().getColumn(1).setMaxWidth(100);

        scrollPane = new JScrollPane(tblRequests);
        scrollPane.setBounds(30, 100, 1560, 700);
        pnlMain.add(scrollPane);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(30, 820, 120, 40);
        btnRefresh.setBackground(darkBlue);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Calibri", Font.BOLD, 16));
        btnRefresh.addActionListener(e -> loadRequests());
        pnlMain.add(btnRefresh);

        btnProcess = new JButton("Mark as Received");
        btnProcess.setBounds(170, 820, 180, 40);
        btnProcess.setBackground(Green);
        btnProcess.setForeground(Color.WHITE);
        btnProcess.setFont(new Font("Calibri", Font.BOLD, 16));
        btnProcess.addActionListener(e -> processRequest());
        pnlMain.add(btnProcess);

        loadRequests();
    }

    private void loadRequests() {
        tableModel.setRowCount(0);
        List<Object[]> requests = MedicalRequestSQL.getAllPendingRequests();
        for (Object[] req : requests) {
            tableModel.addRow(req);
        }
    }

    private void processRequest() {
        int row = tblRequests.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a request first!");
            return;
        }

        int modelRow = tblRequests.convertRowIndexToModel(row);
        int requestId = (int) tableModel.getValueAt(modelRow, 1);
        String itemName = (String) tableModel.getValueAt(modelRow, 3);
        int quantity = (int) tableModel.getValueAt(modelRow, 4);
        String requestedBy = (String) tableModel.getValueAt(modelRow, 5);
        String currentAmount = (String) tableModel.getValueAt(modelRow, 6);
        currentAmount = currentAmount.replace("₱", "").replace(",", "").trim();

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField txtBillingAmount = new JTextField(currentAmount);
        JTextArea txtAdminNotes = new JTextArea(3, 20);
        txtAdminNotes.setLineWrap(true);
        txtAdminNotes.setWrapStyleWord(true);

        panel.add(new JLabel("Billing Amount (₱):"));
        panel.add(txtBillingAmount);
        panel.add(new JLabel("Admin Notes:"));
        panel.add(new JScrollPane(txtAdminNotes));

        int confirm = JOptionPane.showConfirmDialog(this, panel, 
            "Process Request: " + itemName + " (Qty: " + quantity + ")\nRequested by: " + requestedBy,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (confirm == JOptionPane.OK_OPTION) {
            double billingAmount;
            try {
                billingAmount = Double.parseDouble(txtBillingAmount.getText().trim());
                if (billingAmount < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid billing amount!");
                return;
            }

            String adminNotes = txtAdminNotes.getText().trim();
            if (adminNotes.isEmpty()) {
                adminNotes = "Processed by Admin";
            }

            if (MedicalRequestSQL.markRequestAsReceived(requestId, billingAmount, adminNotes)) {
                JOptionPane.showMessageDialog(this, "Request marked as Received!\nBilling Amount: ₱" + billingAmount);
                loadRequests();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to process request!");
            }
        }
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}