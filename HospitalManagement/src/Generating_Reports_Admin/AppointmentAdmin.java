package Generating_Reports_Admin;

import static Color_Palette.ColorPalette.*;
import Database.AdminReportSQL;
import Models.DoctorAppointment;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentAdmin extends JPanel implements ActionListener {

    private JPanel pnlMain, pnlInbox, pnlDetail, pnlTable, pnlApprove;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact;
    private JLabel lblRefNo, lblDate, lblStatus, lblApprovedBy;
    private JLabel lblPName, lblPID, lblFooter;
    private JTable tblInbox, tblRecords;
    private JScrollPane scrInbox, scrRecords, scrMain;
    private DefaultTableModel inboxModel, recordsModel;
    private ImageIcon imgLogo;
    private Image imgLG;
    private JButton btnRefresh, btnLoadReport, btnApprove, btnReject, btnCopy, btnHistory;
    private int selectedReportId = -1;
    private String patientName = "";
    private String patientID = "";
    private boolean isApproved = false;

    public AppointmentAdmin () {
        setLayout(null);
        setBounds(0, 0, 1060, 750);
        setBackground(Color.WHITE);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1020, 980));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrMain = new JScrollPane(pnlMain);
        scrMain.setBounds(0, 0, 1060, 700);
        scrMain.setBorder(BorderFactory.createEmptyBorder());
        add(scrMain);

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

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(600, 25, 100, 30);
        btnRefresh.setBackground(TealGreen);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.addActionListener(this);
        pnlMain.add(btnRefresh);

        btnHistory = new JButton("All History");
        btnHistory.setBounds(710, 25, 110, 30);
        btnHistory.setBackground(mediumBlue);
        btnHistory.setForeground(Color.WHITE);
        btnHistory.addActionListener(this);
        pnlMain.add(btnHistory);

        btnCopy = new JButton("Copy");
        btnCopy.setBounds(830, 25, 80, 30);
        btnCopy.setBackground(darkBlue);
        btnCopy.setForeground(Color.WHITE);
        btnCopy.addActionListener(this);
        pnlMain.add(btnCopy);

        lblRefNo = new JLabel("Ref No.: AD-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 220, 20);
        pnlMain.add(lblRefNo);

        lblDate = new JLabel("Generated: " + java.time.LocalDate.now());
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 220, 20);
        pnlMain.add(lblDate);

        lblStatus = new JLabel("Status: Awaiting Selection");
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        lblStatus.setForeground(orange);
        lblStatus.setBounds(740, 110, 250, 20);
        pnlMain.add(lblStatus);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 940, 2);
        lineTop.setBackground(neutralGray);
        pnlMain.add(lineTop);

        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 145, 940, 80);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlMain.add(pnlPatient);

        lblPName = new JLabel("Patient Name: " + (patientName.isEmpty() ? "________" : patientName));
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        lblPID = new JLabel("Patient ID: " + (patientID.isEmpty() ? "________" : patientID));
        lblPID.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPID.setBounds(400, 30, 200, 20);
        pnlPatient.add(lblPID);

        pnlInbox = new JPanel();
        pnlInbox.setLayout(null);
        pnlInbox.setBounds(30, 235, 940, 200);
        pnlInbox.setBorder(BorderFactory.createTitledBorder("Received from Doctor"));
        pnlMain.add(pnlInbox);

        String[] clmInbox = {"ID", "Patient Name", "Patient ID", "Status", "Sent Date"};
        inboxModel = new DefaultTableModel(clmInbox, 0);
        tblInbox = new JTable(inboxModel);
        tblInbox.setRowHeight(30);
        tblInbox.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblInbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrInbox = new JScrollPane(tblInbox);
        scrInbox.setBounds(20, 25, 900, 140);
        pnlInbox.add(scrInbox);

        btnLoadReport = new JButton("Load Selected Report");
        btnLoadReport.setBounds(760, 170, 160, 25);
        btnLoadReport.setBackground(darkBlue);
        btnLoadReport.setForeground(Color.WHITE);
        btnLoadReport.addActionListener(this);
        pnlInbox.add(btnLoadReport);

        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 450, 940, 250);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Appointment Records (from Doctor)"));
        pnlMain.add(pnlTable);

        String[] clmRecords = {"Date", "Doctor", "Department", "Status", "Remarks"};
        recordsModel = new DefaultTableModel(clmRecords, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblRecords = new JTable(recordsModel);
        tblRecords.setRowHeight(35);
        tblRecords.setFont(new Font("Calibri", Font.PLAIN, 13));
        scrRecords = new JScrollPane(tblRecords);
        scrRecords.setBounds(20, 30, 900, 210);
        pnlTable.add(scrRecords);

        pnlApprove = new JPanel();
        pnlApprove.setLayout(null);
        pnlApprove.setBounds(30, 720, 940, 80);
        pnlApprove.setBackground(Color.WHITE);
        pnlMain.add(pnlApprove);

        btnApprove = new JButton("✔ APPROVE REPORT");
        btnApprove.setBounds(290, 15, 180, 45);
        btnApprove.setBackground(Green);
        btnApprove.setForeground(Color.WHITE);
        btnApprove.setFont(new Font("Calibri", Font.BOLD, 13));
        btnApprove.setEnabled(false);
        btnApprove.addActionListener(this);
        pnlApprove.add(btnApprove);

        btnReject = new JButton("✘ RETURN TO DOCTOR");
        btnReject.setBounds(480, 15, 180, 45);
        btnReject.setBackground(LightRed);
        btnReject.setForeground(Color.WHITE);
        btnReject.setFont(new Font("Calibri", Font.BOLD, 13));
        btnReject.setEnabled(false);
        btnReject.addActionListener(this);
        pnlApprove.add(btnReject);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 820, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblApprovedBy = new JLabel("");
        lblApprovedBy.setFont(new Font("Calibri", Font.BOLD, 14));
        lblApprovedBy.setForeground(Green);
        lblApprovedBy.setBounds(60, 840, 600, 25);
        pnlMain.add(lblApprovedBy);

        lblFooter = new JLabel("Admin Panel — eTriage Hospital Management System");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(330, 870, 400, 20);
        pnlMain.add(lblFooter);

        JLabel spacer = new JLabel();
        spacer.setBounds(0, 920, 10, 50);
        pnlMain.add(spacer);

        loadInbox();
        updateApproveButtonState();
    }

    private void loadInbox() {
        inboxModel.setRowCount(0);
        List<DoctorAppointment> reports = AdminReportSQL.getReportsForAdmin();
        for (DoctorAppointment report : reports) {
            inboxModel.addRow(new Object[]{
                report.getId(),
                report.getPatientName(),
                report.getPatientId(),
                report.getStatus(),
                report.getSentDate() != null ? report.getSentDate().toString().substring(0, 10) : "N/A"
            });
        }
    }

    private void loadAllHistory() {
        inboxModel.setRowCount(0);
        List<DoctorAppointment> reports = AdminReportSQL.getAllReports();
        for (DoctorAppointment report : reports) {
            inboxModel.addRow(new Object[]{
                report.getId(),
                report.getPatientName(),
                report.getPatientId(),
                report.getStatus(),
                report.getSentDate() != null ? report.getSentDate().toString().substring(0, 10) : "N/A"
            });
        }
        pnlInbox.setBorder(BorderFactory.createTitledBorder("All Report History"));
        pnlInbox.revalidate();
    }

    private void loadSelectedReport() {
        int row = tblInbox.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a report from the Doctor list!");
            return;
        }

        selectedReportId = (int) inboxModel.getValueAt(row, 0);
        DoctorAppointment report = AdminReportSQL.getReportById(selectedReportId);

        if (report == null) {
            JOptionPane.showMessageDialog(this, "Could not load report!");
            return;
        }

        patientName = report.getPatientName();
        patientID = report.getPatientId();
        lblPName.setText("Patient Name: " + patientName);
        lblPID.setText("Patient ID: " + patientID);

        recordsModel.setRowCount(0);
        for (String line : report.getReportData().split("\n")) {
            String[] parts = line.split(" \\| ");
            if (parts.length >= 5) recordsModel.addRow(parts);
        }

        String currentStatus = report.getStatus();
        lblStatus.setText("Status: " + currentStatus);
        lblStatus.setForeground(
            currentStatus.equals("Sent to Admin") ? orange
            : currentStatus.equals("Approved by Admin") ? Green
            : LightRed
        );

        boolean pending = currentStatus.equals("Sent to Admin");
        isApproved = currentStatus.equals("Approved by Admin");
        btnApprove.setEnabled(pending);
        btnReject.setEnabled(pending);

        if (isApproved) {
            lblApprovedBy.setText("✓ Already APPROVED by Admin on record.");
            btnApprove.setText("✓ APPROVED");
            btnApprove.setBackground(new Color(0, 150, 0));
        } else {
            lblApprovedBy.setText("");
            btnApprove.setText("✔ APPROVE REPORT");
            btnApprove.setBackground(Green);
        }

        JOptionPane.showMessageDialog(this, "Report loaded from Doctor. You can review and then Approve or Return.");
    }

    private void updateApproveButtonState() {
        if (isApproved) {
            btnApprove.setBackground(new Color(0, 150, 0));
            btnApprove.setText("✓ APPROVED");
            btnApprove.setEnabled(false);
        } else if (selectedReportId == -1) {
            btnApprove.setBackground(Color.GRAY);
            btnApprove.setText("INCOMPLETE");
            btnApprove.setEnabled(false);
        } else {
            btnApprove.setBackground(Green);
            btnApprove.setText("✔ APPROVE REPORT");
            btnApprove.setEnabled(true);
        }
    }

    private void approveReport() {
        if (selectedReportId == -1) {
            JOptionPane.showMessageDialog(this, "No report selected!");
            return;
        }

        JPanel approvePanel = new JPanel(null);
        approvePanel.setPreferredSize(new Dimension(350, 60));

        JLabel lblName = new JLabel("Admin Name:");
        lblName.setBounds(10, 15, 100, 25);
        approvePanel.add(lblName);

        JTextField txtName = new JTextField(15);
        txtName.setBounds(120, 15, 200, 25);
        approvePanel.add(txtName);

        int result = JOptionPane.showConfirmDialog(this, approvePanel, 
            "Final Document Approval", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name!");
            return;
        }

        if (AdminReportSQL.approveReport(selectedReportId, name)) {
            isApproved = true;
            lblStatus.setText("Status: Approved by Admin");
            lblStatus.setForeground(Green);
            lblApprovedBy.setText("✓ FINAL APPROVED by: " + name + " (Admin) on " + java.time.LocalDate.now());
            updateApproveButtonState();
            JOptionPane.showMessageDialog(this, "Report Final Approved!");
            loadInbox();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to approve report!");
        }
    }

    private void rejectReport() {
        if (selectedReportId == -1) {
            JOptionPane.showMessageDialog(this, "No report selected!");
            return;
        }

        JPanel rejectPanel = new JPanel(null);
        rejectPanel.setPreferredSize(new Dimension(350, 60));

        JLabel lblReason = new JLabel("Reason for returning:");
        lblReason.setBounds(10, 15, 150, 25);
        rejectPanel.add(lblReason);

        JTextField txtReason = new JTextField(20);
        txtReason.setBounds(170, 15, 160, 25);
        rejectPanel.add(txtReason);

        int result = JOptionPane.showConfirmDialog(this, rejectPanel, 
            "Return to Doctor", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        String reason = txtReason.getText().trim();
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a reason!");
            return;
        }

        if (AdminReportSQL.rejectReport(selectedReportId, reason)) {
            lblStatus.setText("Status: Returned to Doctor");
            lblStatus.setForeground(LightRed);
            lblApprovedBy.setText("✘ Returned to Doctor. Reason: " + reason);
            btnApprove.setEnabled(false);
            btnReject.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Report returned to Doctor.");
            loadInbox();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to return report!");
        }
    }

    private void copyReport() {
        StringBuilder data = new StringBuilder("=== ETRIAGE HOSPITAL APPOINTMENT HISTORY (ADMIN) ===\n\n");
        data.append("Patient Name: " + (patientName.isEmpty() ? "[EMPTY]" : patientName) + "\n");
        data.append("Patient ID: " + (patientID.isEmpty() ? "[EMPTY]" : patientID) + "\n\n");
        data.append("Date\t\tDoctor\t\tDepartment\t\tStatus\t\tRemarks\n");
        for (int i = 0; i < recordsModel.getRowCount(); i++) {
            for (int j = 0; j < recordsModel.getColumnCount(); j++) {
                data.append(recordsModel.getValueAt(i, j)).append("\t\t");
            }
            data.append("\n");
        }
        data.append("\n").append(lblApprovedBy.getText());
        StringSelection ss = new StringSelection(data.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        JOptionPane.showMessageDialog(this, "Report copied to clipboard!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRefresh) loadInbox();
        else if (e.getSource() == btnHistory) loadAllHistory();
        else if (e.getSource() == btnLoadReport) loadSelectedReport();
        else if (e.getSource() == btnApprove) approveReport();
        else if (e.getSource() == btnReject) rejectReport();
        else if (e.getSource() == btnCopy) copyReport();
    }
}