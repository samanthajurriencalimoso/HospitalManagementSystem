package Inventory;

import static Color_Palette.ColorPalette.*;
import Database.UserManagementSQL;
import Models.Employee;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class UserManagementPanel extends JPanel {
    
    private JPanel pnlMain, tabAdmin, tabDoc, tabNur, pnlSelection, pnlBot;
    private DefaultTableModel tblModel;
    private JTextField txtName, txtID, txtAge, txtEmail, txtPhone;
    private String currentRole = "None"; 
    private JTable tblUM;
    private JLabel lblAd, lblDoc, lblNur, lbltitle, lblDT, lblName, lblID, lblAge, lblDept, lblEmail, lblPhone, lblStatus;
    private JLabel lblCurrentRole; 
    private JButton btnDoctor, btnNurse, btnAdmin, btnSave, btnEdit, btnDelete, btnRefresh, btnVProfile, btnSCreds, btnCStatus;
    private JButton btnChooseImage; 
    private JScrollPane srcUM;
    private JComboBox<String> cmbStatus, cmbDept;
    
    private String selectedImagePath = "src/profile_images/default.png";
    private File selectedFileReference = null;
    
    public UserManagementPanel() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lbltitle = new JLabel("User Management Dashboard");
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

        lblCurrentRole = new JLabel("Current Role Selected: None");
        lblCurrentRole.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
        lblCurrentRole.setForeground(darkBlue);
        lblCurrentRole.setBounds(30, 55, 400, 25);
        pnlMain.add(lblCurrentRole);
       
        btnAdmin = new JButton("ADMIN");
        btnAdmin.setBackground(darkBlue);
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFont(new Font("Calibri", Font.BOLD, 18));
        btnAdmin.setFocusPainted(false);
        btnAdmin.setBounds(30, 85, 120, 35);
        btnAdmin.addActionListener(e -> switchRole("Admin"));
        pnlMain.add(btnAdmin);
        
        btnDoctor = new JButton("DOCTOR");
        btnDoctor.setBackground(mediumBlue);
        btnDoctor.setForeground(Color.WHITE);
        btnDoctor.setFont(new Font("Calibri", Font.BOLD, 18));
        btnDoctor.setFocusPainted(false);
        btnDoctor.setBounds(160, 85, 120, 35);
        btnDoctor.addActionListener(e -> switchRole("Doctor"));
        pnlMain.add(btnDoctor);
        
        btnNurse = new JButton("NURSE");
        btnNurse.setBackground(lightBlue);
        btnNurse.setForeground(Color.WHITE);
        btnNurse.setFont(new Font("Calibri", Font.BOLD, 18));
        btnNurse.setFocusPainted(false);
        btnNurse.setBounds(290, 85, 120, 35);
        btnNurse.addActionListener(e -> switchRole("Nurse"));
        pnlMain.add(btnNurse);
        
        lblAd = new JLabel("0");
        tabAdmin = createTab("Admin", lblAd, darkBlue);
        tabAdmin.setBounds(30, 135, 250, 60);
        pnlMain.add(tabAdmin);

        lblDoc = new JLabel("0");
        tabDoc = createTab("Doctor", lblDoc, mediumBlue);
        tabDoc.setBounds(300, 135, 250, 60);
        pnlMain.add(tabDoc);

        lblNur = new JLabel("0");
        tabNur = createTab("Nurse", lblNur, lightBlue);
        tabNur.setBounds(570, 135, 250, 60);
        pnlMain.add(tabNur);
        
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 215, 1350, 120);
        pnlMain.add(pnlSelection);

        lblName = new JLabel("Name:");
        lblName.setBounds(15, 25, 100, 25);
        lblName.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(110, 23, 200, 28);
        pnlSelection.add(txtName);
        
        lblDept = new JLabel("Department:");
        lblDept.setBounds(15, 67, 100, 25);
        lblDept.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblDept);

        cmbDept = new JComboBox<>(new String[]{"ER","Pediatrics","Surgery","Pharmacy","Radiology","General"});
        cmbDept.setBounds(110, 65, 200, 28);
        pnlSelection.add(cmbDept);

        lblID = new JLabel("ID:");
        lblID.setBounds(340, 25, 100, 25);
        lblID.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblID);

        txtID = new JTextField();
        txtID.setBounds(410, 23, 200, 28);
        txtID.setEditable(false);
        pnlSelection.add(txtID);
        
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(340, 67, 100, 25);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblStatus);

        cmbStatus = new JComboBox<>(new String[]{"Active","Inactive","On Leave"});
        cmbStatus.setBounds(410, 65, 200, 28);
        pnlSelection.add(cmbStatus);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(640, 25, 100, 25);
        lblAge.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(700, 23, 200, 28);
        pnlSelection.add(txtAge);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(640, 67, 100, 25);
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(700, 65, 200, 28);
        pnlSelection.add(txtEmail);

        lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(930, 25, 100, 25);
        lblPhone.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(1000, 23, 200, 28);
        pnlSelection.add(txtPhone);

        btnChooseImage = new JButton("Choose Profile Image");
        btnChooseImage.setBounds(1000, 63, 200, 28);
        btnChooseImage.setBackground(mediumBlue);
        btnChooseImage.setForeground(Color.WHITE);
        btnChooseImage.setFont(new Font("Calibri", Font.BOLD, 13));
        btnChooseImage.setFocusPainted(false);
        btnChooseImage.addActionListener(e -> selectProfileImage());
        pnlSelection.add(btnChooseImage);

        btnSave = new JButton("Save");
        btnSave.setBounds(1215, 23, 115, 32);
        btnSave.setBackground(Green);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Calibri", Font.BOLD, 14));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> addUser());
        pnlSelection.add(btnSave);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(1215, 63, 115, 32);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 14));
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(e -> editUser());
        pnlSelection.add(btnEdit);
       
        String[] clm = {"Name", "ID", "Age", "Role", "Status", "Department", "Email", "Phone"};
        tblModel = new DefaultTableModel(clm, 0);
        tblUM = new JTable(tblModel);
        tblUM.setRowHeight(40);
        tblUM.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblUM.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblUM.getTableHeader().setBackground(lightBlue);
        
        srcUM = new JScrollPane(tblUM);
        srcUM.setBounds(30, 350, 1560, 500);
        pnlMain.add(srcUM);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlMain.add(pnlBot);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(LightRed);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Calibri", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setBounds(20, 10, 120, 30);
        btnDelete.addActionListener(e -> deleteUser());
        pnlBot.add(btnDelete);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(Blue);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBounds(160, 10, 120, 30);
        btnRefresh.addActionListener(e -> refreshTable());
        pnlBot.add(btnRefresh);

        btnVProfile = new JButton("View Profile");
        btnVProfile.setBackground(mediumBlue);
        btnVProfile.setForeground(Color.WHITE);
        btnVProfile.setFont(new Font("Calibri", Font.BOLD, 14));
        btnVProfile.setFocusPainted(false);
        btnVProfile.setBounds(300, 10, 150, 30);
        btnVProfile.addActionListener(e -> viewProfile());
        pnlBot.add(btnVProfile);

        btnSCreds = new JButton("Send Credentials");
        btnSCreds.setBackground(TealGreen);
        btnSCreds.setForeground(Color.WHITE);
        btnSCreds.setFont(new Font("Calibri", Font.BOLD, 14));
        btnSCreds.setFocusPainted(false);
        btnSCreds.setBounds(470, 10, 180, 30);
        btnSCreds.addActionListener(e -> sendCredentials());
        pnlBot.add(btnSCreds);

        btnCStatus = new JButton("Change Status");
        btnCStatus.setBackground(orange);
        btnCStatus.setForeground(Color.WHITE);
        btnCStatus.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCStatus.setFocusPainted(false);
        btnCStatus.setBounds(670, 10, 150, 30);
        btnCStatus.addActionListener(e -> changeStatus());
        pnlBot.add(btnCStatus);

        loadDataFromDatabase(); 
        switchRole(null);
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
    
    private void disableInputs() {
        txtName.setEnabled(false);
        txtID.setEnabled(false);
        txtAge.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        cmbDept.setEnabled(false);
        cmbStatus.setEnabled(false);
        btnChooseImage.setEnabled(false);
    }

    private void enableInputs() {
        txtName.setEnabled(true);
        txtID.setEnabled(true);
        txtAge.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        cmbDept.setEnabled(true);
        cmbStatus.setEnabled(true);
        btnChooseImage.setEnabled(true);
    }

    private void switchRole(String role) {
        if (role == null || role.equalsIgnoreCase("None")) {
            currentRole = "None";
            lblCurrentRole.setText("Current Role Selected: None");
            txtID.setText("");
            disableInputs();
            return;
        }
        
        currentRole = role;
        lblCurrentRole.setText("Current Role Selected: " + role);
        enableInputs();
        txtID.setEditable(false); 

        String prefix = "";
        if (role.equals("Admin")) prefix = "ADM";
        else if (role.equals("Doctor")) prefix = "DCT";
        else if (role.equals("Nurse")) prefix = "NRS";

        String nextCalculatedId = UserManagementSQL.getNextIdByRole(prefix);
        txtID.setText(nextCalculatedId);
    }

    private void selectProfileImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg", "gif"));
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFileReference = fileChooser.getSelectedFile();
            btnChooseImage.setText(selectedFileReference.getName()); 
        }
    }

    private String saveImageToProjectFolder(String employeeId) {
        if (selectedFileReference == null) {
            return selectedImagePath; 
        }
        try {
            File directory = new File("src/profile_images");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            String extension = ".png";
            String name = selectedFileReference.getName();
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = name.substring(dotIndex);
            }
            
            String relativePath = "src/profile_images/" + employeeId + extension;
            File targetLocation = new File(relativePath);
            
            Files.copy(selectedFileReference.toPath(), targetLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return relativePath;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save the image locally.", "File Error", JOptionPane.ERROR_MESSAGE);
            return "src/profile_images/default.png";
        }
    }
    
    private JPanel createTab(String title, JLabel valueLabel, Color color) {
        JPanel containerTab = new JPanel();
        containerTab.setLayout(null);
        containerTab.setBackground(color);

        JLabel lblTabTitle = new JLabel(title);
        lblTabTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTabTitle.setForeground(Color.WHITE);
        lblTabTitle.setBounds(20, 15, 150, 25);
        containerTab.add(lblTabTitle);

        valueLabel.setFont(new Font("Calibri", Font.BOLD, 28));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setBounds(180, 12, 60, 30);
        containerTab.add(valueLabel);

        return containerTab;
    }
    
    private void loadDataFromDatabase() {
        tblModel.setRowCount(0); 
        List<Employee> employeeList = UserManagementSQL.getAllEmployees();
        
        for (Employee emp : employeeList) {
            tblModel.addRow(new Object[]{
                emp.getName(),
                emp.getId(),
                emp.getAge(),
                emp.getRole(),
                emp.getStatus(),
                emp.getDepartment(),
                emp.getEmail(),
                emp.getPhone()
            });
        }
        updateSummary();
    }
    
    private void addUser() {
        if (currentRole.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select an operational role (ADMIN, DOCTOR, or NURSE) first!", "Action Blocked", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = txtName.getText().trim();
        String id = txtID.getText().trim();
        String ageStr = txtAge.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        
        Object selectedDept = cmbDept.getSelectedItem();
        Object selectedStatus = cmbStatus.getSelectedItem();
        String dept = (selectedDept != null) ? selectedDept.toString() : "General";
        String status = (selectedStatus != null) ? selectedStatus.toString() : "Active";

        if (name.isEmpty() || id.isEmpty() || ageStr.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All form text fields are mandatory. Please complete the form!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Pattern.matches("^[a-zA-Z\\s.\\-]+$", name)) {
            JOptionPane.showMessageDialog(this, "Invalid Name format! Names must contain letters and standard spaces only.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int age = 0;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 18 || age > 100) {
                JOptionPane.showMessageDialog(this, "Age boundary rule violation! Employees must be between 18 and 100 years old.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please clear non-numeric characters out of the Age field!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!Pattern.matches(emailRegex, email)) {
            JOptionPane.showMessageDialog(this, "Invalid Email structure! Please use a standard address layout (e.g., user@domain.com).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches("^\\+?[0-9\\s\\-]{7,15}$", phone)) {
            JOptionPane.showMessageDialog(this, "Invalid Phone format! Use numbers, spaces, or dashes (7-15 digits long).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isEdit = false;
        int selectedRow = tblUM.getSelectedRow();
        if (selectedRow >= 0 && tblModel.getValueAt(selectedRow, 1).toString().equalsIgnoreCase(id)) {
            isEdit = true;
        }

        String defaultUsername;
        String[] nameParts = name.split("\\s+");
        if (nameParts.length > 0) {
            String lastName = nameParts[nameParts.length - 1].toLowerCase();
            defaultUsername = lastName + "." + dept.toLowerCase();
        } else {
            defaultUsername = name.toLowerCase() + "." + dept.toLowerCase();
        }
        
        String defaultPassword = id;
        String finalImagePath = saveImageToProjectFolder(id);

        Employee newEmployee = new Employee(name, id, age, currentRole, status, dept, email, phone, defaultUsername, defaultPassword, finalImagePath);
        boolean dbSuccess;
        
        if (isEdit) {
            dbSuccess = UserManagementSQL.updateEmployee(newEmployee);
        } else {
            dbSuccess = UserManagementSQL.insertEmployee(newEmployee);
        }
        
        if (dbSuccess) {
            loadDataFromDatabase(); 
            
            txtName.setText("");
            txtID.setText("");
            txtAge.setText("");
            cmbDept.setSelectedIndex(0);
            txtEmail.setText("");
            txtPhone.setText("");
            cmbStatus.setSelectedIndex(0);
            btnChooseImage.setText("Choose Profile Image");
            
            selectedImagePath = "src/profile_images/default.png";
            selectedFileReference = null;
            
            switchRole(currentRole); 
            
            JOptionPane.showMessageDialog(this, currentRole + " records processed and written successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "SQL transaction was aborted by database connector layers.");
        }
    }
    
    private void changeStatus() {
        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            String targetId = tblModel.getValueAt(row, 1).toString();
            String currentStatus = tblModel.getValueAt(row, 4).toString();
            String[] options = {"Active", "Inactive", "On Leave"};
            String newStatus = (String) JOptionPane.showInputDialog(
                            this, "Change status:", "Update Employee Status",
                            JOptionPane.PLAIN_MESSAGE, null, options, currentStatus);
            
            if (newStatus != null) {
                if (UserManagementSQL.updateStatus(targetId, newStatus)) {
                    tblModel.setValueAt(newStatus, row, 4);
                    updateSummary();
                    JOptionPane.showMessageDialog(this, "Status updated to " + newStatus);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update status.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to change status!");
        }
    }
    
    private void editUser() {
        if (currentRole.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select an operational role (ADMIN, DOCTOR, or NURSE) first!", "Action Blocked", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            String empId = tblModel.getValueAt(row, 1).toString();
            txtName.setText(tblModel.getValueAt(row, 0).toString());
            txtID.setText(empId);
            txtAge.setText(tblModel.getValueAt(row, 2).toString());
            
            currentRole = tblModel.getValueAt(row, 3).toString();
            lblCurrentRole.setText("Current Role Selected: " + currentRole);
            
            cmbStatus.setSelectedItem(tblModel.getValueAt(row, 4).toString());
            cmbDept.setSelectedItem(tblModel.getValueAt(row, 5).toString());
            txtEmail.setText(tblModel.getValueAt(row, 6).toString());
            txtPhone.setText(tblModel.getValueAt(row, 7).toString());
            
            List<Employee> list = UserManagementSQL.getAllEmployees();
            for (Employee e : list) {
                if (e.getId().equalsIgnoreCase(empId)) {
                    selectedImagePath = e.getProfileImage();
                    File imgFile = new File(selectedImagePath);
                    btnChooseImage.setText(imgFile.exists() ? imgFile.getName() : "Choose Profile Image");
                    break;
                }
            }
            
            enableInputs();
            txtID.setEditable(false); 
            
            JOptionPane.showMessageDialog(this, "Edit the details and click SAVE");
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to edit!");
        }
    }
    
    private void deleteUser() {
        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            String name = tblModel.getValueAt(row, 0).toString();
            String idToDelete = tblModel.getValueAt(row, 1).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (UserManagementSQL.deleteEmployee(idToDelete)) {
                    tblModel.removeRow(row);
                    updateSummary();
                    switchRole(currentRole);
                    JOptionPane.showMessageDialog(this, "User deleted!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user record.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to delete!");
        }
    }
    
    private void updateSummary() {
        int total = tblModel.getRowCount();
        int admin = 0, doctor = 0, nurse = 0;
        for (int i = 0; i < total; i++) {
            String role = tblModel.getValueAt(i, 3).toString();
            if (role.equalsIgnoreCase("Admin")) admin++;
            if (role.equalsIgnoreCase("Doctor")) doctor++;
            if (role.equalsIgnoreCase("Nurse")) nurse++;
        }
        lblAd.setText(String.valueOf(admin));
        lblDoc.setText(String.valueOf(doctor));
        lblNur.setText(String.valueOf(nurse));
    }
    
    private void refreshTable() {
        loadDataFromDatabase();
        txtName.setText("");
        txtAge.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        btnChooseImage.setText("Choose Profile Image");
        selectedImagePath = "src/profile_images/default.png";
        selectedFileReference = null;
        switchRole("None");
        JOptionPane.showMessageDialog(this, "Table synchronized with Database values!");
    }
    
    private void viewProfile() {
        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            String empId = tblModel.getValueAt(row, 1).toString();
            String imgPath = "src/profile_images/default.png";
            
            List<Employee> list = UserManagementSQL.getAllEmployees();
            for (Employee e : list) {
                if (e.getId().equalsIgnoreCase(empId)) {
                    imgPath = e.getProfileImage();
                    break;
                }
            }

            String profile = 
                "Employee Profile:\n\n" +
                "Name: " + tblModel.getValueAt(row, 0) + "\n" +
                "ID: " + tblModel.getValueAt(row, 1) + "\n" +
                "Age: " + tblModel.getValueAt(row, 2) + "\n" +
                "Role: " + tblModel.getValueAt(row, 3) + "\n" +
                "Status: " + tblModel.getValueAt(row, 4) + "\n" +
                "Department: " + tblModel.getValueAt(row, 5) + "\n" +
                "Email: " + tblModel.getValueAt(row, 6) + "\n" +
                "Phone: " + tblModel.getValueAt(row, 7) + "\n" +
                "Image Path: " + imgPath; 

            JOptionPane.showMessageDialog(this, profile, "Profile Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to view profile!");
        }
    }

    private void sendCredentials() {
        int row = tblUM.getSelectedRow();
        if (row >= 0) {
            String name = tblModel.getValueAt(row, 0).toString();
            String id = tblModel.getValueAt(row, 1).toString();

            JOptionPane.showMessageDialog(this,
                "Employee Credentials:\n\n" +
                "Name: " + name + "\n" +
                "ID: " + id,
                "Send Credentials",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to send credentials!");
        }
    }
}