package Generating_Reports_Admin;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentHistoryAdmin extends JPanel implements ActionListener {

    private JPanel pnlMain;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblRefNo, lblDate,
            lblPName, lblPID, lblFooter, lblStatus;
    private JTable tblApp;
    private JScrollPane scrTable;
    private ImageIcon imgLogo;
    private Image imgLG;
    private JButton btnSave, btnCopy, btnEdit, btnAddRow, btnDeleteRow;
    private DefaultTableModel tableModel;

    public AppointmentHistoryAdmin() {
        setLayout(null);
        setBounds(0, 0, 1060, 750);
        setBackground(Color.WHITE);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBounds(0, 0, 1020, 750);
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(pnlMain);

        imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        imgLG = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(imgLG));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlMain.add(lblLogo);

        lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlMain.add(lblHospital);

        lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlMain.add(lblAddress);

        lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlMain.add(lblContact);

        lblRefNo = new JLabel("Ref No.: AP-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlMain.add(lblRefNo);

        lblDate = new JLabel("Generated: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlMain.add(lblDate);
        
        lblStatus = new JLabel("Status: Pending");
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        lblStatus.setForeground(orange);
        lblStatus.setBounds(740, 110, 200, 20);
        pnlMain.add(lblStatus);

        btnSave = new JButton("Save");
        btnSave.setBounds(830, 25, 80, 30);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(this);
        pnlMain.add(btnSave);
        
        btnCopy = new JButton("Copy");
        btnCopy.setBounds(920, 25, 80, 30);
        btnCopy.setBackground(mediumBlue);
        btnCopy.setForeground(Color.WHITE);
        btnCopy.addActionListener(this);
        pnlMain.add(btnCopy);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 940, 2);
        lineTop.setBackground(neutralGray);
        pnlMain.add(lineTop);

        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 940, 80);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlMain.add(pnlPatient);

        lblPName = new JLabel("Patient Name: Joshua Garcia");
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        lblPID = new JLabel("Patient ID: P-10234");
        lblPID.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPID.setBounds(400, 30, 200, 20);
        pnlPatient.add(lblPID);

        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 250, 940, 350);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Appointment Records"));
        pnlMain.add(pnlTable);

        String[] clmApp = {"Date", "Doctor", "Department", "Status", "Remarks"};
        String[][] rwApp = {
            {"04/15/2026", "Dr. Isabella Ramos", "General Medicine", "Completed", "Routine checkup"},
            {"03/28/2026", "Dr. Michael Santos", "Cardiology", "Completed", "BP monitoring"},
            {"03/10/2026", "Dr. Sarah Lopez", "Pediatrics", "Cancelled", "Patient rescheduled"},
            {"02/20/2026", "Dr. Isabella Ramos", "General Medicine", "Completed", "Follow-up"},
            {"01/25/2026", "Dr. John Cruz", "Emergency", "Completed", "Acute Bronchitis"}
        };

        tableModel = new DefaultTableModel(rwApp, clmApp);
        tblApp = new JTable(tableModel);
        tblApp.setRowHeight(35);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblApp.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        scrTable = new JScrollPane(tblApp);
        scrTable.setBounds(20, 30, 900, 220);
        pnlTable.add(scrTable);
        
        btnEdit = new JButton("Edit Selected");
        btnEdit.setBounds(20, 270, 120, 30);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(this);
        pnlTable.add(btnEdit);
        
        btnAddRow = new JButton("Add Row");
        btnAddRow.setBounds(150, 270, 100, 30);
        btnAddRow.setBackground(TealGreen);
        btnAddRow.setForeground(Color.WHITE);
        btnAddRow.addActionListener(this);
        pnlTable.add(btnAddRow);
        
        btnDeleteRow = new JButton("Delete Row");
        btnDeleteRow.setBounds(260, 270, 100, 30);
        btnDeleteRow.setBackground(LightRed);
        btnDeleteRow.setForeground(Color.WHITE);
        btnDeleteRow.addActionListener(this);
        pnlTable.add(btnDeleteRow);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 630, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblFooter = new JLabel("Total Appointments: 5 | Completed: 4 | Cancelled: 1");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(300, 650, 400, 20);
        pnlMain.add(lblFooter);
    }
    
    private void saveReport() {
        int total = tableModel.getRowCount();
        int completed = 0, cancelled = 0;
        for (int i = 0; i < total; i++) {
            String status = tableModel.getValueAt(i, 3).toString();
            if (status.equals("Completed")) completed++;
            if (status.equals("Cancelled")) cancelled++;
        }
        lblFooter.setText("Total Appointments: " + total + " | Completed: " + completed + " | Cancelled: " + cancelled);
        lblStatus.setText("Status: Saved");
        lblStatus.setForeground(Green);
        JOptionPane.showMessageDialog(this, "Report saved successfully!", "Save Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void copyReport() {
        StringBuilder data = new StringBuilder("=== ETRIAGE HOSPITAL APPOINTMENT HISTORY ===\n\n");
        data.append("Patient Name: Joshua Garcia\n");
        data.append("Patient ID: P-10234\n\n");
        data.append("Date\t\tDoctor\t\tDepartment\t\tStatus\t\tRemarks\n");
        data.append("-------------------------------------------------------------------\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                data.append(tableModel.getValueAt(i, j)).append("\t\t");
            }
            data.append("\n");
        }
        StringSelection ss = new StringSelection(data.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        JOptionPane.showMessageDialog(this, "Report copied to clipboard!", "Copy Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void editSelectedRow() {
        int row = tblApp.getSelectedRow();
        if (row >= 0) {
            JTextField txtDate = new JTextField(tableModel.getValueAt(row, 0).toString());
            JTextField txtDoctor = new JTextField(tableModel.getValueAt(row, 1).toString());
            JTextField txtDept = new JTextField(tableModel.getValueAt(row, 2).toString());
            JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled", "Pending"});
            cmbStatus.setSelectedItem(tableModel.getValueAt(row, 3).toString());
            JTextField txtRemarks = new JTextField(tableModel.getValueAt(row, 4).toString());
            
            JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
            panel.add(new JLabel("Date:")); panel.add(txtDate);
            panel.add(new JLabel("Doctor:")); panel.add(txtDoctor);
            panel.add(new JLabel("Department:")); panel.add(txtDept);
            panel.add(new JLabel("Status:")); panel.add(cmbStatus);
            panel.add(new JLabel("Remarks:")); panel.add(txtRemarks);
            
            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Appointment", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                tableModel.setValueAt(txtDate.getText(), row, 0);
                tableModel.setValueAt(txtDoctor.getText(), row, 1);
                tableModel.setValueAt(txtDept.getText(), row, 2);
                tableModel.setValueAt(cmbStatus.getSelectedItem(), row, 3);
                tableModel.setValueAt(txtRemarks.getText(), row, 4);
                JOptionPane.showMessageDialog(this, "Row updated!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first!");
        }
    }
    
    private void addRow() {
        tableModel.addRow(new Object[]{"", "", "", "Pending", ""});
        JOptionPane.showMessageDialog(this, "New row added. Click Edit to fill data.");
    }
    
    private void deleteRow() {
        int row = tblApp.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            JOptionPane.showMessageDialog(this, "Row deleted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first!");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) saveReport();
        else if (e.getSource() == btnCopy) copyReport();
        else if (e.getSource() == btnEdit) editSelectedRow();
        else if (e.getSource() == btnAddRow) addRow();
        else if (e.getSource() == btnDeleteRow) deleteRow();
    }
}