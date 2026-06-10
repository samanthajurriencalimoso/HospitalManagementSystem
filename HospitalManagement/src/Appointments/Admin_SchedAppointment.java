package Appointments;

import static Color_Palette.ColorPalette.*;
import Database.AppointmentSQL;
import Database.PatientManagementSQL;
import Database.UserManagementSQL;
import Models.Appointment;
import Models.Patient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Admin_SchedAppointment extends JPanel implements ActionListener {

    private JPanel pnlMain, tabPatients, tabAdmitted, tabCompleted, tabPending, tabUr, pnlSelection, pnlBot, tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtTreatment, txtDate;
    private JComboBox<String> cmbPatient, cmbDoc;
    private JTable tblAppoint;
    private JLabel lblTitle, lblDT, lblPatient, lblDoc, lblTreatment, lblDate, lblTPatient, lblUCases, lblPAppointments, 
                    lblTitle2, lblValue, lblAdmitted, lblCompleted;
    private JButton btnAdd, btnEdit, btnRev, btnChangeStatus;
    private JScrollPane srcApp;
    
    private String selectedAppointmentId = null;
    
    public Admin_SchedAppointment() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lblTitle = new JLabel("Appointment Management");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lblTitle);
        
        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1320, 20, 400, 40);
        pnlMain.add(lblDT);
        
        startClockTimer();
        
        tabPatients = createTab("Today's Patients", "0", Blue);
        tabPatients.setBounds(30, 80, 300, 100);
        pnlMain.add(tabPatients);
        lblTPatient = (JLabel) tabPatients.getComponent(1);

        tabPending = createTab("Pending Appointments", "0", Yellow);
        tabPending.setBounds(350, 80, 300, 100);
        pnlMain.add(tabPending);
        lblPAppointments = (JLabel) tabPending.getComponent(1);
        
        tabAdmitted = createTab("Admitted Appointments", "0", darkBlue);
        tabAdmitted.setBounds(670, 80, 300, 100);
        pnlMain.add(tabAdmitted);
        lblAdmitted = (JLabel) tabAdmitted.getComponent(1);

        tabUr = createTab("Urgent Cases", "0", LightRed);
        tabUr.setBounds(990, 80, 300, 100);
        pnlMain.add(tabUr);
        lblUCases = (JLabel) tabUr.getComponent(1);
        
        tabCompleted = createTab("Completed Appointments", "0", Green);
        tabCompleted.setBounds(1310, 80, 280, 100);
        pnlMain.add(tabCompleted);
        lblCompleted = (JLabel) tabCompleted.getComponent(1);
        
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 200, 1560, 95);
        pnlMain.add(pnlSelection);
        
        lblPatient = new JLabel("Patient: ");
        lblPatient.setBounds(15, 15, 80, 25);
        lblPatient.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblPatient);
        
        cmbPatient = new JComboBox<>();
        cmbPatient.setBounds(85, 13, 200, 28);
        pnlSelection.add(cmbPatient);
        
        lblDoc = new JLabel("Doctor: ");
        lblDoc.setBounds(310, 15, 80, 25);
        lblDoc.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblDoc);
        
        cmbDoc = new JComboBox<>();
        cmbDoc.setBounds(380, 13, 200, 28);
        pnlSelection.add(cmbDoc);
        
        lblTreatment = new JLabel("Treatment: ");
        lblTreatment.setBounds(610, 15, 100, 25);
        lblTreatment.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblTreatment);
        
        txtTreatment = new JTextField();
        txtTreatment.setBounds(705, 13, 180, 28);
        pnlSelection.add(txtTreatment);

        lblDate = new JLabel("Date (yyyy-MM-dd):");
        lblDate.setBounds(15, 55, 150, 25);
        lblDate.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(165, 53, 120, 28);
        pnlSelection.add(txtDate);
        
        txtDate.setText(java.time.LocalDate.now().toString());

        btnAdd = new JButton("Save Appointment");
        btnAdd.setBounds(920, 10, 160, 35);
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdd.addActionListener(e -> saveAppointment());
        pnlSelection.add(btnAdd);
        
        btnEdit = new JButton("Edit");
        btnEdit.setBounds(1090, 10, 100, 35);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(e -> editAppointment());
        pnlSelection.add(btnEdit);
        
        String[] clm = {"Appointment ID", "Patient", "Doctor", "Treatment", "Status", "Appointment Date"};
        tblModel = new DefaultTableModel(clm, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblAppoint = new JTable(tblModel);
        tblAppoint.setRowHeight(35);
        tblAppoint.setAutoCreateRowSorter(true);
        tblAppoint.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblAppoint.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblAppoint.getTableHeader().setBackground(lightBlue);
        
        srcApp = new JScrollPane(tblAppoint);
        srcApp.setBounds(30, 310, 1560, 530);
        pnlMain.add(srcApp);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlBot);
        
        btnChangeStatus = new JButton("Change Status");
        btnChangeStatus.setBounds(20, 10, 150, 30);
        btnChangeStatus.setBackground(orange);
        btnChangeStatus.setForeground(Color.WHITE);
        btnChangeStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        btnChangeStatus.setFocusPainted(false);
        btnChangeStatus.addActionListener(e -> changeStatus());
        pnlBot.add(btnChangeStatus);
        
        btnRev = new JButton("Remove");
        btnRev.setBounds(190, 10, 120, 30);
        btnRev.setBackground(LightRed);
        btnRev.setForeground(Color.WHITE);
        btnRev.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRev.setFocusPainted(false);
        btnRev.addActionListener(ee -> removeAppointment());
        pnlBot.add(btnRev);
        
        populateDropdownContextData();
        refreshTableData();
    }
    
    private void populateDropdownContextData() {
        cmbPatient.removeAllItems();
        cmbDoc.removeAllItems();

        List<Patient> activePatients = PatientManagementSQL.getAllPatients();
        for (Patient p : activePatients) {
            cmbPatient.addItem(p.getName());
        }
 
        List<String> activeDoctors = UserManagementSQL.getActiveEmployeeNamesByRole("Doctor");
        for (String docName : activeDoctors) {
            cmbDoc.addItem("Dr. " + docName);
        }

        if (cmbPatient.getItemCount() == 0) cmbPatient.addItem("No Registered Patients");
        if (cmbDoc.getItemCount() == 0) cmbDoc.addItem("No Active Doctors");
    }

    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel();
        tabUpdate.setLayout(null);
        tabUpdate.setBackground(color);
       
        lblTitle2 = new JLabel(title);
        lblTitle2.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle2.setForeground(Color.WHITE);
        lblTitle2.setBounds(20, 20, 250, 25);
        tabUpdate.add(lblTitle2);
        
        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 50, 200, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }
    
    private void refreshTableData() {
        tblModel.setRowCount(0);
        List<Appointment> list = AppointmentSQL.getAllAppointments();
        
        int total = list.size();
        int pending = 0, admitted = 0, urgent = 0, completed = 0;

        for (Appointment app : list) {
            tblModel.addRow(new Object[]{
                app.getAppointmentId(),
                app.getPatientName(),
                app.getDoctorName(),
                app.getTreatment(),
                app.getStatus(),
                app.getAppointmentDate()
            });
            
            switch (app.getStatus()) {
                case "Pending": pending++; break;
                case "Admitted": admitted++; break;
                case "Urgent": urgent++; break;
                case "Complete": completed++; break;
            }
        }

        lblTPatient.setText(String.valueOf(total));
        lblPAppointments.setText(String.valueOf(pending));
        lblAdmitted.setText(String.valueOf(admitted));
        lblUCases.setText(String.valueOf(urgent));
        lblCompleted.setText(String.valueOf(completed));
    }
    
    private void saveAppointment() {
        if (cmbPatient.getSelectedItem() == null || cmbDoc.getSelectedItem() == null ||
            cmbPatient.getSelectedItem().toString().startsWith("No Registered") ||
            cmbDoc.getSelectedItem().toString().startsWith("No Active")) {
            JOptionPane.showMessageDialog(this, "Valid Patient and Doctor entity selections are mandatory!");
            return;
        }

        String patient = cmbPatient.getSelectedItem().toString();
        String doctor = cmbDoc.getSelectedItem().toString();
        String treatment = txtTreatment.getText().trim();
        String appDate = txtDate.getText().trim();

        if (treatment.isEmpty() || appDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Treatment and Target Appointment Date entry boxes must not be left empty!");
            return;
        }

        if (selectedAppointmentId != null) {
            Appointment updatedApp = new Appointment(selectedAppointmentId, patient, doctor, treatment, "Pending", appDate);
            if (AppointmentSQL.updateAppointment(updatedApp)) {
                JOptionPane.showMessageDialog(this, "Appointment data updated successfully!");
                selectedAppointmentId = null;
                btnAdd.setText("Save Appointment");
            } else {
                JOptionPane.showMessageDialog(this, "SQL Database layer update transactional error.");
            }
        } else {
            int nextSeq = AppointmentSQL.getLastSequenceNum() + 1;
            String generatedID = "AP-" + nextSeq;
            
            Appointment newApp = new Appointment(generatedID, patient, doctor, treatment, "Pending", appDate);
            if (AppointmentSQL.insertAppointment(newApp)) {
                JOptionPane.showMessageDialog(this, "Appointment scheduled! Tracking ID: " + generatedID);
            } else {
                JOptionPane.showMessageDialog(this, "Database storage layer execution trace insertion failure.");
            }
        }

        txtTreatment.setText("");
        txtDate.setText(java.time.LocalDate.now().toString());
        refreshTableData();
    }
    
    private void editAppointment() {
        int row = tblAppoint.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblAppoint.convertRowIndexToModel(row);
            
            selectedAppointmentId = tblModel.getValueAt(modelRow, 0).toString();
            cmbPatient.setSelectedItem(tblModel.getValueAt(modelRow, 1).toString());
            cmbDoc.setSelectedItem(tblModel.getValueAt(modelRow, 2).toString());
            txtTreatment.setText(tblModel.getValueAt(modelRow, 3).toString());
            txtDate.setText(tblModel.getValueAt(modelRow, 5).toString());

            btnAdd.setText("Update Appointment");
            JOptionPane.showMessageDialog(this, "Modify dropdown configurations or structural texts, then press Update to apply modifications.");
        } else {
            JOptionPane.showMessageDialog(this, "Select an appointment from the log data grid mapping coordinate array first!");
        }
    }

    private void removeAppointment() {
        int row = tblAppoint.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblAppoint.convertRowIndexToModel(row);
            String id = tblModel.getValueAt(modelRow, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to permanently delete appointment " + id + "?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                if (AppointmentSQL.deleteAppointment(id)) {
                    JOptionPane.showMessageDialog(this, "Appointment record purged cleanly.");
                    txtTreatment.setText("");
                    txtDate.setText(java.time.LocalDate.now().toString());
                    selectedAppointmentId = null;
                    btnAdd.setText("Save Appointment");
                    refreshTableData();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an active target row grid coordinates execution path to delete.");
        }
    }
    
    private void changeStatus() {
        int row = tblAppoint.getSelectedRow();
        if (row >= 0) {
            int modelRow = tblAppoint.convertRowIndexToModel(row);
            String id = tblModel.getValueAt(modelRow, 0).toString();
            String currentStatus = tblModel.getValueAt(modelRow, 4).toString();
            
            String[] options = {"Pending", "Admitted", "Complete", "Urgent"};
            String newStatus = (String) JOptionPane.showInputDialog(this, "Change appointment status selection:", "Update Status Context",
                JOptionPane.PLAIN_MESSAGE, null, options, currentStatus);

            if (newStatus != null) {
                if (AppointmentSQL.updateStatus(id, newStatus)) {
                    JOptionPane.showMessageDialog(this, "Status successfully updated to: " + newStatus);
                    refreshTableData();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an appointment row matrix element from the grid first!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}