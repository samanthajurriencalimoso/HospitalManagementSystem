package Dashboard;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Admin_Dashboard extends JPanel {

    private JPanel pnlMain;

    public Admin_Dashboard() {

        setLayout(null);
        setBounds(0, 0, 1620, 930);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(new Color(0xE9, 0xEC, 0xEE));
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        JLabel lblMain = new JLabel("Hospital Overview");
        lblMain.setBounds(50, 45, 300, 30);
        lblMain.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlMain.add(lblMain);

        JPanel pnlPatients = createCard("Total Patients", "150", 60, 100, new Color(0x5DAA9C));
        JPanel pnlAppointment = createCard("Today's Appointments", "18", 340, 100, new Color(0x6FA8DC));
        JPanel pnlPrescription = createCard("Active Prescriptions", "96", 620, 100, new Color(0xE69138));
        JPanel pnlBeds = createCard("Available Beds", "14", 900, 100, new Color(0xE06666));

        pnlMain.add(pnlPatients);
        pnlMain.add(pnlAppointment);
        pnlMain.add(pnlPrescription);
        pnlMain.add(pnlBeds);

        JLabel lblMonth = new JLabel("April 2026", SwingConstants.CENTER);
        lblMonth.setFont(new Font("Calibri", Font.BOLD, 24));
        lblMonth.setBounds(1170, 100, 420, 50);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(new Color(0x39, 0x5A, 0x7F));
        lblMonth.setForeground(Color.WHITE);
        pnlMain.add(lblMonth);

        String[] days = {"Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[][] dates = {
            {"", "", "", "1", "2", "3", "4"},
            {"5", "6", "7", "8", "9", "10", "11"},
            {"12", "13", "14", "15", "16", "17", "18"},
            {"19", "20", "21", "22", "23", "24", "25"},
            {"26", "27", "28", "29", "30", "", ""}
        };

        JTable tblCalendar = new JTable(dates, days);
        tblCalendar.setRowHeight(60);

        JScrollPane scrCalendar = new JScrollPane(tblCalendar);
        scrCalendar.setBounds(1170, 150, 420, 320);
        pnlMain.add(scrCalendar);

        pnlMain.add(createWhiteBox("Medical Supplies", "120 Items", 70, 250));
        pnlMain.add(createWhiteBox("Equipment", "45 Units", 440, 250));
        pnlMain.add(createWhiteBox("Stock Alerts", "5 Alerts", 780, 250));

        JPanel pnlPrescriptionTable = new JPanel(null);
        pnlPrescriptionTable.setBounds(70, 400, 1060, 500);
        pnlPrescriptionTable.setBackground(Color.WHITE);
        pnlMain.add(pnlPrescriptionTable);

        JLabel lblPrescription = new JLabel("Prescription Management");
        lblPrescription.setBounds(20, 20, 400, 30);
        lblPrescription.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlPrescriptionTable.add(lblPrescription);

        String[] col = {"Patient", "Drug", "Dosage", "Frequency", "Duration"};
        DefaultTableModel model = new DefaultTableModel(col, 0);

        model.addRow(new String[]{"John Doe", "Paracetamol", "500mg", "2x/day", "5 days"});

        JTable table = new JTable(model);
        table.setRowHeight(30);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 70, 1020, 300);
        pnlPrescriptionTable.add(sp);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(450, 400, 150, 40);
        btnDelete.setBackground(new Color(0x6F,0xA8,0xDC));
        btnDelete.setForeground(Color.WHITE);
        pnlPrescriptionTable.add(btnDelete);

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(null, "Select a row first!");
            }
        });

        JPanel pnlSummary = new JPanel(null);
        pnlSummary.setBounds(1170, 500, 420, 400);
        pnlSummary.setBackground(Color.WHITE);
        pnlMain.add(pnlSummary);

        JLabel lblSummary = new JLabel("Today's Summary");
        lblSummary.setBounds(20, 20, 300, 30);
        lblSummary.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSummary.add(lblSummary);

        String[][] summaryData = {
            {"Patients Today", "25"},
            {"Doctors on Duty", "7"},
            {"Emergency Cases", "6"},
            {"Appointments", "18"},
            {"Admissions", "6"},
            {"Discharges", "4"},
            {"Waiting Patients", "6"}
        };

        JTable tblSummary = new JTable(summaryData, new String[]{"",""});
        tblSummary.setRowHeight(45);
        tblSummary.setTableHeader(null);

        JScrollPane scrSummary = new JScrollPane(tblSummary);
        scrSummary.setBounds(20, 70, 380, 280);
        pnlSummary.add(scrSummary);
    }

    private JPanel createCard(String title, String value, int x, int y, Color color) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 260, 120);
        panel.setBackground(color);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setBounds(15, 15, 200, 20);
        lblTitle.setForeground(Color.WHITE);

        JLabel lblValue = new JLabel(value);
        lblValue.setBounds(150, 60, 100, 40);
        lblValue.setForeground(Color.WHITE);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));

        panel.add(lblTitle);
        panel.add(lblValue);

        return panel;
    }

    private JPanel createWhiteBox(String title, String value, int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 320, 130);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(0xA3, 0xCA, 0xE9), 2));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 20, 200, 25);

        JLabel lblValue = new JLabel(value);
        lblValue.setBounds(180, 70, 200, 30);

        panel.add(lblTitle);
        panel.add(lblValue);

        return panel;
    }
}