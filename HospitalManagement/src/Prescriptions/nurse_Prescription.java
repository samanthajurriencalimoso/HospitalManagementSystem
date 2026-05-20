package Prescriptions;

import static Color_Palette.ColorPalette.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class nurse_Prescription extends JPanel {

    private JPanel pnlMain;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtFreq;
    private JTextField txtDur;
    private JButton btnUpdate;

    public nurse_Prescription() {

        setLayout(null);
        setBounds(0, 0, 1620, 930);
        setBackground(veryLightBlue);

        pnlMain = new JPanel(null);
        pnlMain.setBounds(0, 0, 1620, 930);
        pnlMain.setBackground(veryLightBlue);
        add(pnlMain);

        JLabel lblMainTitle = new JLabel("Nurse Prescription Dashboard");
        lblMainTitle.setBounds(30, 20, 500, 45);
        lblMainTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        lblMainTitle.setForeground(darkBlue);
        pnlMain.add(lblMainTitle);

        JLabel lblDate = new JLabel("May 21, 2026 | 10:00 AM");
        lblDate.setBounds(1300, 20, 300, 40);
        lblDate.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDate.setForeground(Color.DARK_GRAY);
        pnlMain.add(lblDate);

        JPanel tabPrescription = createTopCard("Total Prescriptions", "3", darkBlue);
        tabPrescription.setBounds(30, 90, 500, 110);
        pnlMain.add(tabPrescription);

        JPanel tabPatients = createTopCard("Patients Assigned", "3", mediumBlue);
        tabPatients.setBounds(550, 90, 500, 110);
        pnlMain.add(tabPatients);

        JPanel tabMedicine = createTopCard("Medicine Schedule", "3", TealGreen);
        tabMedicine.setBounds(1070, 90, 500, 110);
        pnlMain.add(tabMedicine);

        JPanel pnlPrescription = new JPanel(null);
        pnlPrescription.setBounds(30, 240, 1560, 620);
        pnlPrescription.setBackground(Color.WHITE);
        pnlPrescription.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlMain.add(pnlPrescription);

        JLabel lblPrescriptionTitle = new JLabel("Prescription Management");
        lblPrescriptionTitle.setBounds(30, 20, 450, 40);
        lblPrescriptionTitle.setFont(new Font("Calibri", Font.BOLD, 28));
        lblPrescriptionTitle.setForeground(darkBlue);
        pnlPrescription.add(lblPrescriptionTitle);

        String[] col = {
            "Patient",
            "Drug",
            "Dosage",
            "Frequency",
            "Duration"
        };

        model = new DefaultTableModel(col, 0);

        model.addRow(new String[]{
            "John Doe",
            "Paracetamol",
            "500mg",
            "2x/day",
            "5 days"
        });

        model.addRow(new String[]{
            "Jane Smith",
            "Amoxicillin",
            "250mg",
            "3x/day",
            "7 days"
        });

        model.addRow(new String[]{
            "Michael Cruz",
            "Ibuprofen",
            "400mg",
            "1x/day",
            "3 days"
        });

        table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("Calibri", Font.PLAIN, 16));
        table.setSelectionBackground(SBlue);
        JTableHeader header = table.getTableHeader();
        
        header.setFont(new Font("Calibri", Font.BOLD, 18));
        header.setBackground(lightBlue);
        header.setForeground(Color.BLACK);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 80, 1490, 300);
        sp.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlPrescription.add(sp);

        JPanel pnlUpdate = new JPanel(null);
        pnlUpdate.setBounds(180, 420, 1180, 140);
        pnlUpdate.setBackground(veryLightBlue);
        pnlUpdate.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlPrescription.add(pnlUpdate);

        JLabel lblFreq = new JLabel("Frequency");
        lblFreq.setBounds(130, 25, 120, 30);
        lblFreq.setFont(new Font("Calibri", Font.BOLD, 20));
        lblFreq.setForeground(darkBlue);
        pnlUpdate.add(lblFreq);

        txtFreq = new JTextField();
        txtFreq.setBounds(130, 60, 280, 40);
        txtFreq.setFont(new Font("Calibri", Font.PLAIN, 17));
        pnlUpdate.add(txtFreq);

        JLabel lblDur = new JLabel("Duration");
        lblDur.setBounds(500, 25, 120, 30);
        lblDur.setFont(new Font("Calibri", Font.BOLD, 20));
        lblDur.setForeground(darkBlue);
        pnlUpdate.add(lblDur);

        txtDur = new JTextField();
        txtDur.setBounds(500, 60, 280, 40);
        txtDur.setFont(new Font("Calibri", Font.PLAIN, 17));
        pnlUpdate.add(txtDur);

        btnUpdate = new JButton("Update Prescription");
        btnUpdate.setBounds(860, 58, 240, 45);
        btnUpdate.setBackground(Blue);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlUpdate.add(btnUpdate);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();

            if (row >= 0) {
                txtFreq.setText(model.getValueAt(row, 3).toString());
                txtDur.setText(model.getValueAt(row, 4).toString());
            }
        });

        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row >= 0) {
                model.setValueAt(txtFreq.getText(), row, 3);
                model.setValueAt(txtDur.getText(), row, 4);
                JOptionPane.showMessageDialog(null, "Prescription updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a patient first!");
            }
        });
    }

    private JPanel createTopCard(String title, String value, Color bg) {
        JPanel card = new JPanel(null);
        card.setBackground(bg);
        JLabel lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 18, 300, 30);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        card.add(lblTitle);

        JLabel lblValue = new JLabel(value);
        lblValue.setBounds(20, 55, 200, 40);
        lblValue.setForeground(Color.WHITE);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 34));
        card.add(lblValue);

        return card;
    }
}