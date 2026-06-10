package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.PatientManagementSQL;
import Database.UserManagementSQL; 
import Models.Patient;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class PatientCentralPanel extends JPanel {
    
    private JPanel pnlMain, tabPatient, tabAdmission, pnlSelection, pnlBot, tabUpdate, tabDis, tabCri;
    private DefaultTableModel tblModel;
    private JTextField txtName, txtRoom;
    private JLabel lblTPatient, lblAct, lbltitle, lblDT, lblName, lblRoom, lblTitle, lblValue, lblDoc, lblNur, lblStatus, lblCri, lblDis;
    private JTable tblPC;
    private JButton btnAdd, btnAdmit, btnDischarge, btnRemove, btnCStatus, btnEdit;
    private JScrollPane srcRoom;
    private JComboBox<String> cmbDoc, cmbNur, cmbStatus;
    private boolean isEditMode = false;
    private String activeEditingPatientId = "";
    
    public PatientCentralPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
       
        lbltitle = new JLabel("Patient Central Dashboard");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1320, 20, 400, 40);
        pnlMain.add(lblDT);
        
        startClockTimer();
        
        tabPatient = createTab("Total Patients", "0", darkBlue);
        tabPatient.setBounds(30, 80, 300, 100);
        pnlMain.add(tabPatient);
        lblTPatient = (JLabel) tabPatient.getComponent(1);
        
        tabAdmission = createTab("Active Admissions", "0", Yellow);
        tabAdmission.setBounds(350, 80, 300, 100);
        pnlMain.add(tabAdmission);
        lblAct = (JLabel) tabAdmission.getComponent(1);
        
        tabDis = createTab("Discharged Patients", "0", Blue);
        tabDis.setBounds(670, 80, 300, 100);
        pnlMain.add(tabDis);
        lblDis = (JLabel) tabDis.getComponent(1);
        
        tabCri = createTab("Critical Patients", "0", LightRed);
        tabCri.setBounds(990, 80, 300, 100);
        pnlMain.add(tabCri);
        lblCri = (JLabel) tabCri.getComponent(1);
     
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 1350, 120);
        pnlMain.add(pnlSelection);
        
        lblName = new JLabel("Name:");
        lblName.setBounds(15, 28, 100, 25);
        lblName.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(120, 26, 200, 28);
        pnlSelection.add(txtName);
        
        lblRoom = new JLabel("Room:");
        lblRoom.setBounds(340, 28, 100, 25);
        lblRoom.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblRoom);
        
        txtRoom = new JTextField();
        txtRoom.setBounds(445, 26, 200, 28);
        pnlSelection.add(txtRoom);
        
        lblDoc = new JLabel("Doctor:");
        lblDoc.setBounds(665, 28, 100, 25);
        lblDoc.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblDoc);
        
        cmbDoc = new JComboBox<>();
        cmbDoc.setBounds(770, 26, 200, 28);
        pnlSelection.add(cmbDoc);
        
        lblNur = new JLabel("Nurse:");
        lblNur.setBounds(340, 70, 100, 25);
        lblNur.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblNur);
        
        cmbNur = new JComboBox<>();
        cmbNur.setBounds(445, 70, 200, 28);
        pnlSelection.add(cmbNur);
        
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(15, 70, 100, 25);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblStatus);

        cmbStatus = new JComboBox<>(new String[]{"Active", "Admitted", "Discharged", "Under Observation", "Critical"});
        cmbStatus.setBounds(120, 68, 200, 28);
        pnlSelection.add(cmbStatus);
        
        btnAdd = new JButton("Save Patient");
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdd.setFocusPainted(false);
        btnAdd.setBounds(1010, 25, 150, 35);
        btnAdd.addActionListener(e -> addPatient());
        pnlSelection.add(btnAdd);
        
        String[] clm = {"Patient ID", "Name", "Room", "Doctor", "Nurse", "Status", "Date"};
        tblModel = new DefaultTableModel(clm, 0);
        tblPC = new JTable(tblModel);
        tblPC.setRowHeight(35);
        tblPC.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblPC.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblPC.getTableHeader().setBackground(lightBlue);
        
        srcRoom = new JScrollPane(tblPC);
        srcRoom.setBounds(30, 350, 1560, 500);
        pnlMain.add(srcRoom);
       
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);
        
        btnAdmit = new JButton("Admit Selected");
        btnAdmit.setBackground(darkBlue);
        btnAdmit.setForeground(Color.WHITE);
        btnAdmit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdmit.setFocusPainted(false);
        btnAdmit.setBounds(20, 10, 140, 30);
        btnAdmit.addActionListener(e -> admitPatient());
        pnlBot.add(btnAdmit);
        
        btnDischarge = new JButton("Discharge Selected");
        btnDischarge.setBackground(Blue);
        btnDischarge.setForeground(Color.WHITE);
        btnDischarge.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDischarge.setFocusPainted(false);
        btnDischarge.setBounds(180, 10, 150, 30);
        btnDischarge.addActionListener(e -> dischargePatient());
        pnlBot.add(btnDischarge);
        
        btnRemove = new JButton("Remove Selected");
        btnRemove.setBackground(LightRed);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(350, 10, 140, 30);
        btnRemove.addActionListener(e -> removePatient());
        pnlBot.add(btnRemove);
        
        JButton btnViewProfile = new JButton("View Profile");
        btnViewProfile.setBackground(mediumBlue);
        btnViewProfile.setForeground(Color.WHITE);
        btnViewProfile.setFont(new Font("Calibri", Font.BOLD, 14));
        btnViewProfile.setFocusPainted(false);
        btnViewProfile.setBounds(510, 10, 130, 30);
        btnViewProfile.addActionListener(e -> viewProfile());
        pnlBot.add(btnViewProfile);
        
        btnEdit = new JButton("Edit Patient");
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.setBounds(660, 10, 130, 30);
        btnEdit.addActionListener(e -> setupEditFormWorkflow());
        pnlBot.add(btnEdit);

        btnCStatus = new JButton("Change Status");
        btnCStatus.setBackground(orange);
        btnCStatus.setForeground(Color.WHITE);
        btnCStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCStatus.setFocusPainted(false);
        btnCStatus.setBounds(810, 10, 150, 30);
        btnCStatus.addActionListener(e -> changeStatus());
        pnlBot.add(btnCStatus);

        populateActiveStaff();
        loadDataFromDatabase();
    }
    
    private void populateActiveStaff() {
        cmbDoc.removeAllItems();
        cmbNur.removeAllItems();

        List<String> activeDoctors = UserManagementSQL.getActiveEmployeeNamesByRole("Doctor");
        for (String docName : activeDoctors) {
            cmbDoc.addItem("Dr. " + docName);
        }

        List<String> activeNurses = UserManagementSQL.getActiveEmployeeNamesByRole("Nurse");
        for (String nurseName : activeNurses) {
            cmbNur.addItem(nurseName + " (Nurse)");
        }

        if (cmbDoc.getItemCount() == 0) {
            cmbDoc.addItem("No Active Doctors");
        }
        if (cmbNur.getItemCount() == 0) {
            cmbNur.addItem("No Active Nurses");
        }
    }

    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel();
        tabUpdate.setLayout(null);
        tabUpdate.setBackground(color);
        
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(15, 20, 250, 20);
        tabUpdate.add(lblTitle);
        
        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(15, 50, 150, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }

    private void loadDataFromDatabase() {
        tblModel.setRowCount(0);
        List<Patient> patients = PatientManagementSQL.getAllPatients();
        for (Patient p : patients) {
            tblModel.addRow(new Object[]{
                p.getId(), 
                p.getName(), 
                p.getRoom(), 
                p.getDoctor(), 
                p.getNurse(), 
                p.getStatus(),
                p.getAdmissionDate()
            });
        }
        updateSummary();
    }
    
    private void addPatient() {
        String name = txtName.getText().trim();
        String room = txtRoom.getText().trim();
        
        if (cmbDoc.getSelectedItem() == null || cmbNur.getSelectedItem() == null ||
            cmbDoc.getSelectedItem().toString().startsWith("No Active") ||
            cmbNur.getSelectedItem().toString().startsWith("No Active")) {
            JOptionPane.showMessageDialog(this, "Please ensure you have valid active staff members assigned!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty() || room.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required. Please populate both Name and Room number fields!", "Validation Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Pattern.matches("^[a-zA-Z\\s.\\-]+$", name)) {
            JOptionPane.showMessageDialog(this, "Invalid Name structure! Characters must consist of alphabetic text characters only.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches("^[a-zA-Z0-9\\s\\-]+$", room)) {
            JOptionPane.showMessageDialog(this, "Invalid Room structure! Use alphanumeric indicators only (e.g., 'ICU-02' or '304').", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String doc = cmbDoc.getSelectedItem().toString();
        String nur = cmbNur.getSelectedItem().toString();
        String status = cmbStatus.getSelectedItem().toString();
        
        boolean transactionResult;
        
        if (isEditMode) {
            Patient updatedPatient = new Patient(activeEditingPatientId, name, room, doc, nur, status, "");
            transactionResult = PatientManagementSQL.updatePatient(updatedPatient);
        } else {
            String generatedId = PatientManagementSQL.getNextPatientId();
            String currentTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Patient newPatient = new Patient(generatedId, name, room, doc, nur, status, currentTimestamp);
            transactionResult = PatientManagementSQL.insertPatient(newPatient);
        }

        if (transactionResult) {
            clearFormInputFields();
            loadDataFromDatabase();
            JOptionPane.showMessageDialog(this, isEditMode ? "Patient record updated successfully!" : "Patient added successfully!");
            
            isEditMode = false;
            activeEditingPatientId = "";
            btnAdd.setText("Save Patient");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to persist transaction structural updates into the database log layer.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupEditFormWorkflow() {
        int selectedRow = tblPC.getSelectedRow();
        if (selectedRow >= 0) {
            isEditMode = true;
            activeEditingPatientId = tblModel.getValueAt(selectedRow, 0).toString();
            
            txtName.setText(tblModel.getValueAt(selectedRow, 1).toString());
            txtRoom.setText(tblModel.getValueAt(selectedRow, 2).toString());
            
            cmbDoc.setSelectedItem(tblModel.getValueAt(selectedRow, 3).toString());
            cmbNur.setSelectedItem(tblModel.getValueAt(selectedRow, 4).toString());
            cmbStatus.setSelectedItem(tblModel.getValueAt(selectedRow, 5).toString());
            
            btnAdd.setText("Update Patient");
            JOptionPane.showMessageDialog(this, "Modify elements inside text areas and press UPDATE PATIENT to save structural alterations.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an applicable patient entry from table mappings first!", "Selection Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFormInputFields() {
        txtName.setText("");
        txtRoom.setText("");
        if (cmbDoc.getItemCount() > 0) cmbDoc.setSelectedIndex(0);
        if (cmbNur.getItemCount() > 0) cmbNur.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
    }
    
    private void admitPatient() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String patId = tblModel.getValueAt(row, 0).toString();
            String currentRoom = tblModel.getValueAt(row, 2).toString();

            if (currentRoom.isEmpty()) {
                currentRoom = JOptionPane.showInputDialog(this, "Assign Room Number for Admission:", "Room Assignment", JOptionPane.PLAIN_MESSAGE);
                if (currentRoom == null || currentRoom.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Admission cancelled. Room is required.");
                    return;
                }
            }

            if (PatientManagementSQL.updatePatientRoomAndStatus(patId, currentRoom, "Admitted")) {
                loadDataFromDatabase();
                JOptionPane.showMessageDialog(this, "Patient admitted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient row from the table!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void dischargePatient() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String patId = tblModel.getValueAt(row, 0).toString();
            if (PatientManagementSQL.updatePatientRoomAndStatus(patId, "", "Discharged")) {
                loadDataFromDatabase();
                JOptionPane.showMessageDialog(this, "Patient discharged successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient row from the table!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void changeStatus() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String patId = tblModel.getValueAt(row, 0).toString();
            String currentStatus = tblModel.getValueAt(row, 5).toString();
            String[] options = {"Active", "Admitted", "Discharged", "Under Observation", "Critical"};
            
            String newStatus = (String) JOptionPane.showInputDialog(this, "Change status:", "Update Patient Status", 
                    JOptionPane.PLAIN_MESSAGE, null, options, currentStatus);
            
            if (newStatus != null) {
                if (PatientManagementSQL.updatePatientStatus(patId, newStatus)) {
                    loadDataFromDatabase();
                    JOptionPane.showMessageDialog(this, "Status updated to " + newStatus);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient row from the table!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void removePatient() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String patId = tblModel.getValueAt(row, 0).toString();
            String name = tblModel.getValueAt(row, 1).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to completely remove " + name + " (" + patId + ")?", 
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (PatientManagementSQL.deletePatient(patId)) {
                    loadDataFromDatabase();
                    JOptionPane.showMessageDialog(this, "Patient record removed!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient row from the table!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void viewProfile() {
        int row = tblPC.getSelectedRow();
        if (row >= 0) {
            String profile =
                "Patient Profile Details:\n\n" +
                "Patient ID: " + tblModel.getValueAt(row, 0) + "\n" +
                "Name: " + tblModel.getValueAt(row, 1) + "\n" +
                "Room: " + tblModel.getValueAt(row, 2) + "\n" +
                "Doctor: " + tblModel.getValueAt(row, 3) + "\n" +
                "Nurse: " + tblModel.getValueAt(row, 4) + "\n" +
                "Status: " + tblModel.getValueAt(row, 5) + "\n" +
                "Date: " + tblModel.getValueAt(row, 6);

            JOptionPane.showMessageDialog(this, profile, "Patient Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Select a patient row to view their profile details!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void updateSummary() {
        int total = tblModel.getRowCount();
        int admitted = 0, discharged = 0, critical = 0;
        
        for (int i = 0; i < total; i++) {
            String status = tblModel.getValueAt(i, 5).toString();
            if (status.equalsIgnoreCase("Admitted")) admitted++;
            if (status.equalsIgnoreCase("Discharged")) discharged++;
            if (status.equalsIgnoreCase("Critical")) critical++;
        }
        lblTPatient.setText(String.valueOf(total));
        lblAct.setText(String.valueOf(admitted));
        lblDis.setText(String.valueOf(discharged));
        lblCri.setText(String.valueOf(critical));
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}