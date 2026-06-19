package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import Database.AppointmentSQL;
import Database.NurseAppointmentSQL;
import Database.UserManagementSQL;
import Database.PatientManagementSQL;
import Models.Appointment;
import Models.Patient;
import Models.NurseAppointment;
import Models.Employee;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentNurse extends JPanel implements ActionListener {

    private JPanel pnlMain;
    private JLabel lblLogo, lblHospital, lblAddress, lblContact, lblRefNo, lblDate,
            lblPName, lblPID, lblFooter, lblStatus, lblSentBy;
    private JTable tblApp;
    private JScrollPane scrTable, scrMain;
    private ImageIcon imgLogo;
    private Image imgLG;
    private JButton btnSave, btnCopy, btnEdit, btnAddRow, btnDeleteRow, btnSend, btnNewReport, btnEditPatient, btnEditRow, btnRefresh, btnLoadAppointments;
    private DefaultTableModel tableModel;
    private JPanel pnlAction;
    private boolean isSent = false;
    private String status = "Draft";
    private String patientName = "";
    private String patientID = "";
    private int savedReportId = -1;
    private String loggedInNurse = "";
    private Map<String, String> doctorDepartmentCache = new HashMap<>();

    public AppointmentNurse() {
        setLayout(null);
        setBounds(0, 0, 1060, 750);
        setBackground(Color.WHITE);

        if (UserManagementSQL.currentEmployee != null) {
            loggedInNurse = "Nurse " + UserManagementSQL.currentEmployee.getName();
        } else {
            loggedInNurse = "Nurse";
        }

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setPreferredSize(new Dimension(1020, 850));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrMain = new JScrollPane(pnlMain);
        scrMain.setBounds(0, 0, 1060, 700);
        scrMain.setBorder(BorderFactory.createEmptyBorder());
        scrMain.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrMain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

         btnLoadAppointments = new JButton("Load Appointments");
        btnLoadAppointments.setBounds(430, 25, 150, 30);
        btnLoadAppointments.setBackground(darkBlue);
        btnLoadAppointments.setForeground(Color.WHITE);
        btnLoadAppointments.addActionListener(this);
        pnlMain.add(btnLoadAppointments);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(730, 25, 80, 30);
        btnRefresh.setBackground(mediumBlue);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.addActionListener(this);
        pnlMain.add(btnRefresh);

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
        pnlPatient.setBounds(30, 150, 940, 80);
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

        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 250, 940, 300);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Appointment Records"));
        pnlMain.add(pnlTable);

        String[] clmApp = {"Date", "Doctor", "Department", "Status", "Remarks"};
        tableModel = new DefaultTableModel(clmApp, 0);
        tblApp = new JTable(tableModel);
        tblApp.setRowHeight(35);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblApp.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        scrTable = new JScrollPane(tblApp);
        scrTable.setBounds(20, 30, 900, 200);
        pnlTable.add(scrTable);
        
        btnEditRow = new JButton("Edit Selected");
        btnEditRow.setBounds(20, 250, 120, 30);
        btnEditRow.setBackground(darkBlue);
        btnEditRow.setForeground(Color.WHITE);
        btnEditRow.addActionListener(this);
        pnlTable.add(btnEditRow);
        
        btnAddRow = new JButton("Add Row");
        btnAddRow.setBounds(150, 250, 100, 30);
        btnAddRow.setBackground(TealGreen);
        btnAddRow.setForeground(Color.WHITE);
        btnAddRow.addActionListener(this);
        pnlTable.add(btnAddRow);
        
        btnDeleteRow = new JButton("Delete Row");
        btnDeleteRow.setBounds(260, 250, 100, 30);
        btnDeleteRow.setBackground(LightRed);
        btnDeleteRow.setForeground(Color.WHITE);
        btnDeleteRow.addActionListener(this);
        pnlTable.add(btnDeleteRow);

        pnlAction = new JPanel();
        pnlAction.setLayout(null);
        pnlAction.setBounds(30, 580, 940, 80);
        pnlAction.setBackground(Color.WHITE);
        pnlMain.add(pnlAction);
        
        btnSend = new JButton("SEND TO DOCTOR");
        btnSend.setBounds(370, 15, 200, 45);
        btnSend.setBackground(Green);
        btnSend.setForeground(Color.WHITE);
        btnSend.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSend.addActionListener(this);
        pnlAction.add(btnSend);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 680, 940, 2);
        lineFooter.setBackground(neutralGray);
        pnlMain.add(lineFooter);

        lblSentBy = new JLabel("");
        lblSentBy.setFont(new Font("Calibri", Font.BOLD, 14));
        lblSentBy.setForeground(Green);
        lblSentBy.setBounds(60, 700, 500, 25);
        pnlMain.add(lblSentBy);

        lblFooter = new JLabel("Total Appointments: 0 | Completed: 0 | Cancelled: 0");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(300, 730, 400, 20);
        pnlMain.add(lblFooter);
        
        JLabel spacer = new JLabel();
        spacer.setBounds(0, 800, 10, 50);
        pnlMain.add(spacer);
        
        loadCompletedAppointmentsFromAppointmentsTable();
        updateButtonState();
    }
    
    private String getDoctorDepartment(String doctorName) {
        if (doctorDepartmentCache.containsKey(doctorName)) {
            return doctorDepartmentCache.get(doctorName);
        }
        try {
            List<Employee> employees = UserManagementSQL.getAllEmployees();
            for (Employee emp : employees) {
                String fullName = emp.getName();
                if (doctorName.contains(emp.getName()) || emp.getName().contains(doctorName.replace("Dr. ", ""))) {
                    String dept = emp.getDepartment();
                    if (dept != null && !dept.isEmpty()) {
                        doctorDepartmentCache.put(doctorName, dept);
                        return dept;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        doctorDepartmentCache.put(doctorName, "General Medicine");
        return "General Medicine";
    }
    
    private void loadCompletedAppointmentsFromAppointmentsTable() {
        tableModel.setRowCount(0);
        patientName = "";
        patientID = "";
        savedReportId = -1;
        isSent = false;
        status = "Draft";
        
        List<Appointment> allAppointments = AppointmentSQL.getAllAppointments();
        List<Appointment> completedAppointments = new ArrayList<>();
        
        for (Appointment app : allAppointments) {
            if ("Complete".equalsIgnoreCase(app.getStatus())) {
                completedAppointments.add(app);
            }
        }
        
        if (completedAppointments.isEmpty()) {
            lblPName.setText("Patient Name: ________");
            lblPID.setText("Patient ID: ________");
            lblStatus.setText("Status: No completed appointments found");
            lblStatus.setForeground(Color.GRAY);
            updateFooter();
            updateButtonState();
            return;
        }
        
        Appointment firstApp = completedAppointments.get(0);
        patientName = firstApp.getPatientName();
        patientID = "N/A";
        
        List<Patient> patients = PatientManagementSQL.getAllPatients();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(patientName)) {
                patientID = p.getId();
                break;
            }
        }
        
        savedReportId = -1; 
        status = "Loaded from Appointments";
        
        lblPName.setText("Patient Name: " + patientName);
        lblPID.setText("Patient ID: " + patientID);
        lblStatus.setText("Status: Loaded from Appointments");
        lblStatus.setForeground(orange);
        
        for (Appointment app : completedAppointments) {
            String dept = getDoctorDepartment(app.getDoctorName());
            String displayStatus = app.getStatus();
            String remarks = "Completed by Nurse";
            
            tableModel.addRow(new Object[]{
                app.getAppointmentDate() != null ? app.getAppointmentDate().split(" ")[0] : "N/A",
                app.getDoctorName(),
                dept,
                displayStatus,
                remarks
            });
        }
        
        updateFooter();
        updateButtonState();
        
        System.out.println("✅ Loaded " + completedAppointments.size() + " completed appointments from appointments table");
    }
    
    private void loadReportFromDatabase() {
        List<NurseAppointment> reports = NurseAppointmentSQL.getReportsForNurse(loggedInNurse);
        
        if (reports == null || reports.isEmpty()) {
            return;
        }
        
        for (NurseAppointment report : reports) {
            if ("Sent to Doctor".equalsIgnoreCase(report.getStatus())) {
                continue;
            }
            
            String reportData = report.getReportData();
            if (reportData != null && !reportData.isEmpty()) {
                String[] lines = reportData.split("\n");
                for (String line : lines) {
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 5) {
                        tableModel.addRow(new Object[]{
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim()
                        });
                    }
                }
            }
            
            if (savedReportId == -1 && tableModel.getRowCount() > 0) {
                savedReportId = report.getId();
                patientName = report.getPatientName();
                patientID = report.getPatientId();
                status = report.getStatus();
                lblPName.setText("Patient Name: " + patientName);
                lblPID.setText("Patient ID: " + patientID);
                lblStatus.setText("Status: " + status);
                lblStatus.setForeground(orange);
                
                if ("Sent to Doctor".equalsIgnoreCase(status)) {
                    isSent = true;
                    lblSentBy.setText("✓ Previously SENT to Doctor");
                }
            }
        }
        
        updateFooter();
        updateButtonState();
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
    
    private void updateButtonState() {
        if (isSent) {
            btnSend.setBackground(Color.GRAY);
            btnSend.setText("✓ SENT");
            btnSend.setEnabled(false);
        } else if (!isReportComplete()) {
            btnSend.setBackground(Color.GRAY);
            btnSend.setText("INCOMPLETE");
            btnSend.setEnabled(false);
        } else {
            btnSend.setBackground(Green);
            btnSend.setText("SEND TO DOCTOR");
            btnSend.setEnabled(true);
        }
    }
    
    private void saveReport() {
        if (!isReportComplete()) {
            JOptionPane.showMessageDialog(this, "❌ Cannot save report! Please fill all required fields first.", "Incomplete Report", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String reportData = getReportData();
        
        if (savedReportId == -1) {
            NurseAppointment report = new NurseAppointment(patientName, patientID, reportData, loggedInNurse, "Doctor");
            savedReportId = NurseAppointmentSQL.saveReport(report);
            if (savedReportId > 0) {
                status = "Saved";
                lblStatus.setText("Status: Saved");
                lblStatus.setForeground(Green);
                JOptionPane.showMessageDialog(this, "✓ Report saved to database! ID: " + savedReportId);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save report to database!");
            }
        } else {
            NurseAppointment report = new NurseAppointment(savedReportId, patientName, patientID, reportData, status, loggedInNurse, "Doctor", null, null, null, null);
            if (NurseAppointmentSQL.updateReport(report)) {
                status = "Saved";
                lblStatus.setText("Status: Saved");
                lblStatus.setForeground(Green);
                JOptionPane.showMessageDialog(this, "✓ Report updated in database!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update report!");
            }
        }
        updateButtonState();
    }
    
    private void copyReport() {
        StringBuilder data = new StringBuilder("=== ETRIAGE HOSPITAL APPOINTMENT HISTORY ===\n\n");
        data.append("Patient Name: " + (patientName.isEmpty() ? "[EMPTY]" : patientName) + "\n");
        data.append("Patient ID: " + (patientID.isEmpty() ? "[EMPTY]" : patientID) + "\n\n");
        data.append("Date\t\tDoctor\t\tDepartment\t\tStatus\t\tRemarks\n");
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
    
    private void sendReport() {
        if (!isReportComplete()) {
            JOptionPane.showMessageDialog(this, "Cannot send! Please fill all required fields first.", "Incomplete Report", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (savedReportId == -1) {
            JOptionPane.showMessageDialog(this, "Please save the report before sending!", "Save First", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Send this report to Doctor?", 
            "Confirm Send", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        if (NurseAppointmentSQL.sendToDoctor(savedReportId)) {
            isSent = true;
            status = "Sent to Doctor";
            lblStatus.setText("Status: Sent to Doctor");
            lblStatus.setForeground(Green);
            lblSentBy.setText("✓ SENT to Doctor on " + java.time.LocalDate.now());
            updateButtonState();
            JOptionPane.showMessageDialog(this, "Appointment History sent to Doctor successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send report to Doctor!");
        }
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
            panel.add(new JLabel("Date:*")); panel.add(txtDate);
            panel.add(new JLabel("Doctor:*")); panel.add(txtDoctor);
            panel.add(new JLabel("Department:")); panel.add(txtDept);
            panel.add(new JLabel("Status:")); panel.add(cmbStatus);
            panel.add(new JLabel("Remarks:")); panel.add(txtRemarks);
            
            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Appointment", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (txtDate.getText().trim().isEmpty() || txtDoctor.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Date and Doctor are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                tableModel.setValueAt(txtDate.getText(), row, 0);
                tableModel.setValueAt(txtDoctor.getText(), row, 1);
                tableModel.setValueAt(txtDept.getText(), row, 2);
                tableModel.setValueAt(cmbStatus.getSelectedItem(), row, 3);
                tableModel.setValueAt(txtRemarks.getText(), row, 4);
                updateFooter();
                JOptionPane.showMessageDialog(this, "Row updated!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first!");
        }
    }
    
    private void addRow() {
        tableModel.addRow(new Object[]{"", "", "", "Pending", ""});
        JOptionPane.showMessageDialog(this, "New row added. Click Edit Selected to fill data.");
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
        JTextField txtPatientID = new JTextField(patientID, 20);
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Patient Name:*")); panel.add(txtPatientName);
        panel.add(new JLabel("Patient ID:*")); panel.add(txtPatientID);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Patient Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newName = txtPatientName.getText().trim();
            String newID = txtPatientID.getText().trim();
            if (newName.isEmpty() || newID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient Name and ID are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            patientName = newName;
            patientID = newID;
            lblPName.setText("Patient Name: " + patientName);
            lblPID.setText("Patient ID: " + patientID);
            status = "Draft";
            lblStatus.setText("Status: Draft");
            lblStatus.setForeground(orange);
            isSent = false;
            updateButtonState();
            JOptionPane.showMessageDialog(this, "Patient information updated!");
        }
    }
    
    private void newReport() {
        int confirm = JOptionPane.showConfirmDialog(this, "Create new blank report? Unsaved data will be lost.", "New Report", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.setRowCount(0);
            patientName = "";
            patientID = "";
            savedReportId = -1;
            isSent = false;
            status = "Draft";
            lblPName.setText("Patient Name: ________");
            lblPID.setText("Patient ID: ________");
            lblStatus.setText("Status: Draft");
            lblStatus.setForeground(orange);
            lblSentBy.setText("");
            updateFooter();
            updateButtonState();
            JOptionPane.showMessageDialog(this, "New blank report created. Click 'Edit Patient' to fill in information.", "New Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void refreshReports() {
        loadCompletedAppointmentsFromAppointmentsTable();
        JOptionPane.showMessageDialog(this, "Reports refreshed!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewReport) newReport();
        else if (e.getSource() == btnEditPatient) editPatient();
        else if (e.getSource() == btnSave) saveReport();
        else if (e.getSource() == btnCopy) copyReport();
        else if (e.getSource() == btnSend) sendReport();
        else if (e.getSource() == btnEditRow) editSelectedRow();
        else if (e.getSource() == btnAddRow) addRow();
        else if (e.getSource() == btnDeleteRow) deleteRow();
        else if (e.getSource() == btnRefresh) refreshReports();
        else if (e.getSource() == btnLoadAppointments) {
            loadCompletedAppointmentsFromAppointmentsTable();
            JOptionPane.showMessageDialog(this, "Loaded completed appointments from Appointments table!");
        }
    }
}
