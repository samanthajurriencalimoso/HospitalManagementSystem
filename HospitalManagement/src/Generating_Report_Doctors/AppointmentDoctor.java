package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import Database.DoctorAppointmentSQL;
import Database.NurseAppointmentSQL;
import Models.DoctorAppointment;
import Models.NurseAppointment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentDoctor extends JPanel implements ActionListener {

    private JPanel pnlMain, pnlTable;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblRefNo, lblDate,
            lblPName, lblPID, lblFooter, lblStatus, lblApprovedBy, lblNotesDisplay;
    private JTable tblApp, tblReceived, tblReturned;
    private JScrollPane scrTable, scrReceived, scrReturned;
    private ImageIcon imgLogo;
    private Image imgLG;
    private JButton btnSave, btnCopy, btnEdit, btnAddRow, btnDeleteRow, btnApprove, btnNewReport, btnEditPatient, btnEditRow, btnLoadFromNurse, btnLoadReturned, btnNotes;
    private DefaultTableModel tableModel, receivedModel, returnedModel;
    private JPanel pnlApprove, pnlReceived, pnlReturned;
    private boolean isApproved = false;
    private String approvedByName, approvedByRole;
    private String status = "Pending";
    private String patientName = "";
    private String patientID = "";
    private String doctorNotes = "";
    private int currentDoctorReportId = -1;
    private int selectedNurseReportId = -1;

    public AppointmentDoctor() {
        setLayout(null);
        setBounds(0, 0, 1060, 900);
        setBackground(Color.WHITE);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1020, 1000));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrMain = new JScrollPane(pnlMain);
        scrMain.setBounds(0, 0, 1060, 750);
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

        btnNewReport = new JButton("New Report");
        btnNewReport.setBounds(600, 25, 100, 30);
        btnNewReport.setBackground(TealGreen);
        btnNewReport.setForeground(Color.WHITE);
        btnNewReport.addActionListener(this);
        pnlMain.add(btnNewReport);

        btnEditPatient = new JButton("Edit Patient");
        btnEditPatient.setBounds(710, 25, 100, 30);
        btnEditPatient.setBackground(darkBlue);
        btnEditPatient.setForeground(Color.WHITE);
        btnEditPatient.addActionListener(this);
        pnlMain.add(btnEditPatient);
        
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

        lblRefNo = new JLabel("Ref No.: AP-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlMain.add(lblRefNo);

        lblDate = new JLabel("Generated: " + java.time.LocalDate.now());
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlMain.add(lblDate);
        
        lblStatus = new JLabel("Status: " + status);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        lblStatus.setForeground(orange);
        lblStatus.setBounds(740, 110, 200, 20);
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

        pnlReceived = new JPanel();
        pnlReceived.setLayout(null);
        pnlReceived.setBounds(30, 235, 940, 150);
        pnlReceived.setBorder(BorderFactory.createTitledBorder("Received from Nurse"));
        pnlMain.add(pnlReceived);

        String[] clmReceived = {"ID", "Patient Name", "Patient ID", "Status", "Sent Date"};
        receivedModel = new DefaultTableModel(clmReceived, 0);
        tblReceived = new JTable(receivedModel);
        tblReceived.setRowHeight(30);
        tblReceived.setFont(new Font("Calibri", Font.PLAIN, 12));
        scrReceived = new JScrollPane(tblReceived);
        scrReceived.setBounds(20, 25, 900, 90);
        pnlReceived.add(scrReceived);

        btnLoadFromNurse = new JButton("Load Selected Report");
        btnLoadFromNurse.setBounds(760, 120, 160, 25);
        btnLoadFromNurse.setBackground(darkBlue);
        btnLoadFromNurse.setForeground(Color.WHITE);
        btnLoadFromNurse.addActionListener(this);
        pnlReceived.add(btnLoadFromNurse);

        // Returned by Admin Panel
        pnlReturned = new JPanel();
        pnlReturned.setLayout(null);
        pnlReturned.setBounds(30, 400, 940, 150);
        pnlReturned.setBorder(BorderFactory.createTitledBorder("Returned by Admin"));
        pnlMain.add(pnlReturned);

        String[] clmReturned = {"ID", "Patient Name", "Patient ID", "Status", "Return Date"};
        returnedModel = new DefaultTableModel(clmReturned, 0);
        tblReturned = new JTable(returnedModel);
        tblReturned.setRowHeight(30);
        tblReturned.setFont(new Font("Calibri", Font.PLAIN, 12));
        scrReturned = new JScrollPane(tblReturned);
        scrReturned.setBounds(20, 25, 900, 90);
        pnlReturned.add(scrReturned);

        btnLoadReturned = new JButton("Load Returned Report");
        btnLoadReturned.setBounds(760, 120, 160, 25);
        btnLoadReturned.setBackground(orange);
        btnLoadReturned.setForeground(Color.WHITE);
        btnLoadReturned.addActionListener(this);
        pnlReturned.add(btnLoadReturned);
        
        pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 565, 940, 220);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Appointment Records"));
        pnlMain.add(pnlTable);

        String[] clmApp = {"Date", "Doctor", "Department", "Status", "Remarks"};
        tableModel = new DefaultTableModel(clmApp, 0);
        tblApp = new JTable(tableModel);
        tblApp.setRowHeight(35);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 13));
        scrTable = new JScrollPane(tblApp);
        scrTable.setBounds(20, 30, 900, 140);
        pnlTable.add(scrTable);
        
        btnEditRow = new JButton("Edit Selected");
        btnEditRow.setBounds(20, 180, 120, 30);
        btnEditRow.setBackground(darkBlue);
        btnEditRow.setForeground(Color.WHITE);
        btnEditRow.addActionListener(this);
        pnlTable.add(btnEditRow);
        
        btnAddRow = new JButton("Add Row");
        btnAddRow.setBounds(150, 180, 100, 30);
        btnAddRow.setBackground(TealGreen);
        btnAddRow.setForeground(Color.WHITE);
        btnAddRow.addActionListener(this);
        pnlTable.add(btnAddRow);
        
        btnDeleteRow = new JButton("Delete Row");
        btnDeleteRow.setBounds(260, 180, 100, 30);
        btnDeleteRow.setBackground(LightRed);
        btnDeleteRow.setForeground(Color.WHITE);
        btnDeleteRow.addActionListener(this);
        pnlTable.add(btnDeleteRow);

        JLabel lblNotesTitle = new JLabel("Doctor's Notes:");
        lblNotesTitle.setBounds(30, 800, 100, 25);
        lblNotesTitle.setFont(new Font("Calibri", Font.BOLD, 14));
        pnlMain.add(lblNotesTitle);

        lblNotesDisplay = new JLabel(doctorNotes.isEmpty() ? "No notes added" : doctorNotes);
        lblNotesDisplay.setBounds(140, 800, 500, 25);
        lblNotesDisplay.setFont(new Font("Calibri", Font.PLAIN, 13));
        lblNotesDisplay.setForeground(Color.DARK_GRAY);
        pnlMain.add(lblNotesDisplay);

        btnNotes = new JButton("Add/Edit Notes");
        btnNotes.setBounds(660, 798, 130, 30);
        btnNotes.setBackground(darkBlue);
        btnNotes.setForeground(Color.WHITE);
        btnNotes.addActionListener(this);
        pnlMain.add(btnNotes);
       
        // Approve Section
        pnlApprove = new JPanel();
        pnlApprove.setLayout(null);
        pnlApprove.setBounds(30, 840, 940, 80);
        pnlApprove.setBackground(Color.WHITE);
        pnlMain.add(pnlApprove);
        
        btnApprove = new JButton("APPROVE & SEND TO ADMIN");
        btnApprove.setBounds(370, 15, 200, 45);
        btnApprove.setBackground(Green);
        btnApprove.setForeground(Color.WHITE);
        btnApprove.addActionListener(this);
        pnlApprove.add(btnApprove);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 940, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblApprovedBy = new JLabel("");
        lblApprovedBy.setFont(new Font("Calibri", Font.BOLD, 14));
        lblApprovedBy.setForeground(Green);
        lblApprovedBy.setBounds(60, 960, 500, 25);
        pnlMain.add(lblApprovedBy);

        lblFooter = new JLabel("Total Appointments: 0 | Completed: 0 | Cancelled: 0");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(300, 990, 400, 20);
        pnlMain.add(lblFooter);
        
        JLabel spacer = new JLabel();
        spacer.setBounds(0, 1030, 10, 50);
        pnlMain.add(spacer);
        
        loadReceivedReports();
        loadReturnedReports();
        updateApproveButtonState();
    }
    
    private void loadReceivedReports() {
        receivedModel.setRowCount(0);
        List<NurseAppointment> reports = NurseAppointmentSQL.getReportsForDoctor();
        for (NurseAppointment report : reports) {
            receivedModel.addRow(new Object[]{
                report.getId(),
                report.getPatientName(),
                report.getPatientId(),
                report.getStatus(),
                report.getSentDate() != null ? report.getSentDate().toString().substring(0, 10) : "N/A"
            });
        }
    }
    
    private void loadReturnedReports() {
        returnedModel.setRowCount(0);
        List<DoctorAppointment> reports = DoctorAppointmentSQL.getReturnedReportsForDoctor();
        for (DoctorAppointment report : reports) {
            returnedModel.addRow(new Object[]{
                report.getId(),
                report.getPatientName(),
                report.getPatientId(),
                report.getStatus(),
                report.getSentDate() != null ? report.getSentDate().toString().substring(0, 10) : "N/A"
            });
        }
    }
    
    private void loadReportFromNurse() {
        int row = tblReceived.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a report from the Nurse list!");
            return;
        }
        
        selectedNurseReportId = (int) receivedModel.getValueAt(row, 0);
        NurseAppointment nurseReport = NurseAppointmentSQL.getReportById(selectedNurseReportId);
        
        if (nurseReport != null) {
            patientName = nurseReport.getPatientName();
            patientID = nurseReport.getPatientId();
            doctorNotes = "";
            lblPName.setText("Patient Name: " + patientName);
            lblPID.setText("Patient ID: " + patientID);
            lblNotesDisplay.setText("No notes added");
            lblNotesDisplay.setForeground(Color.DARK_GRAY);

            String reportData = nurseReport.getReportData();
            tableModel.setRowCount(0);
            if (reportData != null && !reportData.isEmpty()) {
                for (String line : reportData.split("\n")) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 5) {
                        tableModel.addRow(parts);
                    }
                }
            }
            
            currentDoctorReportId = -1;
            status = "Loaded from Nurse";
            lblStatus.setText("Status: Loaded from Nurse");
            lblStatus.setForeground(orange);
            
            JOptionPane.showMessageDialog(this, "Report loaded from Nurse. You can edit and then Approve & Send to Admin.");
        }
    }
    
    private void loadReturnedReport() {
        int row = tblReturned.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a returned report!");
            return;
        }
        
        int returnedId = (int) returnedModel.getValueAt(row, 0);
        DoctorAppointment returnedReport = DoctorAppointmentSQL.getReportById(returnedId);
        
        if (returnedReport != null) {
            patientName = returnedReport.getPatientName();
            patientID = returnedReport.getPatientId();
            doctorNotes = returnedReport.getDoctorNotes() != null ? returnedReport.getDoctorNotes() : "";
            lblPName.setText("Patient Name: " + patientName);
            lblPID.setText("Patient ID: " + patientID);
            
            if (!doctorNotes.isEmpty()) {
                lblNotesDisplay.setText(doctorNotes);
                lblNotesDisplay.setForeground(Color.BLACK);
            } else {
                lblNotesDisplay.setText("No notes added");
                lblNotesDisplay.setForeground(Color.DARK_GRAY);
            }

            String returnReason = DoctorAppointmentSQL.getReturnReason(returnedId);
            if (returnReason != null && !returnReason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Report was returned by Admin.\nReason: " + returnReason, "Returned Report", JOptionPane.WARNING_MESSAGE);
            }
            
            String reportData = returnedReport.getReportData();
            tableModel.setRowCount(0);
            if (reportData != null && !reportData.isEmpty()) {
                for (String line : reportData.split("\n")) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 5) {
                        tableModel.addRow(parts);
                    }
                }
            }
            
            currentDoctorReportId = returnedId;
            selectedNurseReportId = returnedReport.getNurseReportId();
            status = "Returned - Needs Revision";
            lblStatus.setText("Status: Returned by Admin - Needs Revision");
            lblStatus.setForeground(LightRed);
            
            JOptionPane.showMessageDialog(this, "Report loaded. Please revise based on Admin feedback, then Save and Send to Admin again.");
        }
    }
    
    private void editNotes() {
        JTextArea txtNotes = new JTextArea(doctorNotes, 10, 40);
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtNotes);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        
        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Doctor's Notes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            doctorNotes = txtNotes.getText();
            if (doctorNotes.isEmpty()) {
                lblNotesDisplay.setText("No notes added");
                lblNotesDisplay.setForeground(Color.DARK_GRAY);
            } else {
                lblNotesDisplay.setText(doctorNotes);
                lblNotesDisplay.setForeground(Color.BLACK);
            }
            JOptionPane.showMessageDialog(this, "Notes saved! Remember to click Save to store in database.");
        }
    }
    
    private String getReportData() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                data.append(tableModel.getValueAt(i, j)).append(" | ");
            }
            data.append("\n");
        }
        return data.toString();
    }
    
    private void updateFooter() {
        int total = tableModel.getRowCount();
        int completed = 0, cancelled = 0;
        for (int i = 0; i < total; i++) {
            String stat = tableModel.getValueAt(i, 3).toString();
            if (stat.equals("Completed")) completed++;
            if (stat.equals("Cancelled")) cancelled++;
        }
        lblFooter.setText("Total Appointments: " + total + " | Completed: " + completed + " | Cancelled: " + cancelled);
    }
    
    private boolean isReportComplete() {
        if (patientName.isEmpty() || patientID.isEmpty()) {
            return false;
        }
        if (tableModel.getRowCount() == 0) {
            return false;
        }
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String date = tableModel.getValueAt(i, 0).toString();
            String doctor = tableModel.getValueAt(i, 1).toString();
            if (date == null || date.trim().isEmpty() || doctor == null || doctor.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private void updateApproveButtonState() {
        if (isApproved) {
            btnApprove.setBackground(new Color(0, 150, 0));
            btnApprove.setText("✓ APPROVED & SENT");
            btnApprove.setEnabled(false);
        } else if (!isReportComplete()) {
            btnApprove.setBackground(Color.GRAY);
            btnApprove.setText("INCOMPLETE");
            btnApprove.setEnabled(false);
        } else {
            btnApprove.setBackground(Green);
            btnApprove.setText("APPROVE & SEND TO ADMIN");
            btnApprove.setEnabled(true);
        }
    }
    
    public void saveReport() {
        if (!isReportComplete()) {
            JOptionPane.showMessageDialog(this, "❌ Cannot save report! Please fill all required fields first.", "Incomplete Report", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String reportData = getReportData();
        
        if (currentDoctorReportId == -1) {
            DoctorAppointment report = new DoctorAppointment(selectedNurseReportId, patientName, patientID, reportData, "Doctor", "Admin");
            report.setDoctorNotes(doctorNotes);
            currentDoctorReportId = DoctorAppointmentSQL.saveReport(report);
            if (currentDoctorReportId > 0) {
                status = "Saved";
                lblStatus.setText("Status: Saved");
                lblStatus.setForeground(Green);
                JOptionPane.showMessageDialog(this, "✓ Report saved to database! ID: " + currentDoctorReportId);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save report!");
            }
        } else {
            boolean success = DoctorAppointmentSQL.updateReportData(currentDoctorReportId, reportData, doctorNotes);
            if (success) {
                status = "Saved";
                lblStatus.setText("Status: Saved");
                lblStatus.setForeground(Green);
                JOptionPane.showMessageDialog(this, "✓ Report and notes updated in database!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update report!");
            }
        }
        updateApproveButtonState();
    }
    
    private void copyReport() {
        StringBuilder data = new StringBuilder("=== ETRIAGE HOSPITAL APPOINTMENT HISTORY (DOCTOR) ===\n\n");
        data.append("Patient Name: " + (patientName.isEmpty() ? "[EMPTY]" : patientName) + "\n");
        data.append("Patient ID: " + (patientID.isEmpty() ? "[EMPTY]" : patientID) + "\n\n");
        data.append("Doctor's Notes: " + (doctorNotes.isEmpty() ? "None" : doctorNotes) + "\n\n");
        data.append("Date\t\tDoctor\t\tDepartment\t\tStatus\t\tRemarks\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                data.append(tableModel.getValueAt(i, j)).append("\t\t");
            }
            data.append("\n");
        }
        StringSelection ss = new StringSelection(data.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        JOptionPane.showMessageDialog(this, "Report copied to clipboard!");
    }
    
    private void approveDocument() {
        if (!isReportComplete()) {
            JOptionPane.showMessageDialog(this, "Cannot approve! Please fill all required fields first.", 
                "Incomplete Report", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (currentDoctorReportId == -1) {
            JOptionPane.showMessageDialog(this, "Please save the report before approving!");
            return;
        }

        JPanel approvePanel = new JPanel(null);
        approvePanel.setPreferredSize(new Dimension(350, 60));

        JLabel lblName = new JLabel("Doctor Name:");
        lblName.setBounds(10, 15, 100, 25);
        approvePanel.add(lblName);

        JTextField txtName = new JTextField(15);
        txtName.setBounds(120, 15, 200, 25);
        approvePanel.add(txtName);

        int result = JOptionPane.showConfirmDialog(this, approvePanel, 
            "Document Approval", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name!");
                return;
            }

            if (DoctorAppointmentSQL.sendToAdmin(currentDoctorReportId)) {
                isApproved = true;
                approvedByName = name;
                approvedByRole = "Doctor";
                status = "Sent to Admin";
                lblStatus.setText("Status: Sent to Admin");
                lblStatus.setForeground(Green);
                lblApprovedBy.setText("✓ APPROVED by: " + name + " (Doctor) on " + java.time.LocalDate.now());
                updateApproveButtonState();
                JOptionPane.showMessageDialog(this, "Report Approved and Sent to Admin!");

                currentDoctorReportId = -1;
                patientName = "";
                patientID = "";
                doctorNotes = "";
                tableModel.setRowCount(0);
                lblPName.setText("Patient Name: ________");
                lblPID.setText("Patient ID: ________");
                lblNotesDisplay.setText("No notes added");
                
                loadReceivedReports();
                loadReturnedReports();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to send report to Admin!");
            }
        }
    }

    private void editSelectedRow() {
        int row = tblApp.getSelectedRow();
        if (row >= 0) {
            JTextField txtDate    = new JTextField(tableModel.getValueAt(row, 0).toString());
            JTextField txtDoctor  = new JTextField(tableModel.getValueAt(row, 1).toString());
            JTextField txtDept    = new JTextField(tableModel.getValueAt(row, 2).toString());
            JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled", "Pending"});
            cmbStatus.setSelectedItem(tableModel.getValueAt(row, 3).toString());
            JTextField txtRemarks = new JTextField(tableModel.getValueAt(row, 4).toString());

            JPanel panel = new JPanel(null);
            panel.setPreferredSize(new Dimension(370, 185));

            JLabel lblDate = new JLabel("Date:*");
            lblDate.setBounds(10, 10, 100, 25);
            panel.add(lblDate);
            txtDate.setBounds(120, 10, 230, 25);
            panel.add(txtDate);

            JLabel lblDoctor = new JLabel("Doctor:*");
            lblDoctor.setBounds(10, 45, 100, 25);
            panel.add(lblDoctor);
            txtDoctor.setBounds(120, 45, 230, 25);
            panel.add(txtDoctor);

            JLabel lblDept = new JLabel("Department:");
            lblDept.setBounds(10, 80, 100, 25);
            panel.add(lblDept);
            txtDept.setBounds(120, 80, 230, 25);
            panel.add(txtDept);

            JLabel lblStat = new JLabel("Status:");
            lblStat.setBounds(10, 115, 100, 25);
            panel.add(lblStat);
            cmbStatus.setBounds(120, 115, 230, 25);
            panel.add(cmbStatus);

            JLabel lblRemarks = new JLabel("Remarks:");
            lblRemarks.setBounds(10, 150, 100, 25);
            panel.add(lblRemarks);
            txtRemarks.setBounds(120, 150, 230, 25);
            panel.add(txtRemarks);

            int result = JOptionPane.showConfirmDialog(this, panel, 
                "Edit Appointment", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (txtDate.getText().trim().isEmpty() || txtDoctor.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Date and Doctor are required!");
                    return;
                }
                tableModel.setValueAt(txtDate.getText(),           row, 0);
                tableModel.setValueAt(txtDoctor.getText(),         row, 1);
                tableModel.setValueAt(txtDept.getText(),           row, 2);
                tableModel.setValueAt(cmbStatus.getSelectedItem(), row, 3);
                tableModel.setValueAt(txtRemarks.getText(),        row, 4);
                updateFooter();
                JOptionPane.showMessageDialog(this, "Row updated!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first!");
        }
    }

    private void addRow() {
        tableModel.addRow(new Object[]{"", "", "", "Pending", ""});
        JOptionPane.showMessageDialog(this, "New row added.");
    }

    private void deleteRow() {
        int row = tblApp.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            updateFooter();
            JOptionPane.showMessageDialog(this, "Row deleted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first!");
        }
    }

    private void editPatient() {
        JTextField txtPatientName = new JTextField(patientName, 20);
        JTextField txtPatientID   = new JTextField(patientID,   20);

        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(370, 80));

        JLabel lblPN = new JLabel("Patient Name:*");
        lblPN.setBounds(10, 10, 110, 25);
        panel.add(lblPN);
        txtPatientName.setBounds(130, 10, 220, 25);
        panel.add(txtPatientName);

        JLabel lblPID = new JLabel("Patient ID:*");
        lblPID.setBounds(10, 45, 110, 25);
        panel.add(lblPID);
        txtPatientID.setBounds(130, 45, 220, 25);
        panel.add(txtPatientID);

        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Edit Patient Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newName = txtPatientName.getText().trim();
            String newID   = txtPatientID.getText().trim();
            if (newName.isEmpty() || newID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient Name and ID are required!");
                return;
            }
            patientName = newName;
            patientID   = newID;
            lblPName.setText("Patient Name: " + patientName);
            lblPID.setText("Patient ID: "     + patientID);
            isApproved = false;
            status = "Pending";
            lblStatus.setText("Status: Pending");
            lblStatus.setForeground(orange);
            updateApproveButtonState();
            JOptionPane.showMessageDialog(this, "Patient information updated!");
        }
    }
    
    private void newReport() {
        int confirm = JOptionPane.showConfirmDialog(this, "Create new blank report? Unsaved data will be lost.", "New Report", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.setRowCount(0);
            patientName = "";
            patientID = "";
            doctorNotes = "";
            lblNotesDisplay.setText("No notes added");
            currentDoctorReportId = -1;
            selectedNurseReportId = -1;
            isApproved = false;
            status = "Pending";
            lblPName.setText("Patient Name: ________");
            lblPID.setText("Patient ID: ________");
            lblStatus.setText("Status: Pending");
            lblStatus.setForeground(orange);
            lblApprovedBy.setText("");
            updateFooter();
            updateApproveButtonState();
            JOptionPane.showMessageDialog(this, "New blank report created.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewReport) newReport();
        else if (e.getSource() == btnEditPatient) editPatient();
        else if (e.getSource() == btnSave) saveReport();
        else if (e.getSource() == btnCopy) copyReport();
        else if (e.getSource() == btnApprove) approveDocument();
        else if (e.getSource() == btnEditRow) editSelectedRow();
        else if (e.getSource() == btnAddRow) addRow();
        else if (e.getSource() == btnDeleteRow) deleteRow();
        else if (e.getSource() == btnLoadFromNurse) loadReportFromNurse();
        else if (e.getSource() == btnLoadReturned) loadReturnedReport();
        else if (e.getSource() == btnNotes) editNotes();
    }
}
