package Dashboard;

import Appointments.Nurse_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Database.AppointmentSQL;
import Database.MedicalRequestSQL;
import Database.PatientManagementSQL;
import Database.PrescriptionSQL;
import Database.UserManagementSQL;
import Inventory.RequestPanel_Nurse;
import MedicalHistory.MedicalHistory_Nurse;
import Models.Appointment;
import Models.Patient;
import Models.Prescription;
import Patient_Information.PatientInfo_Nurse;
import Prescriptions.Prescription_Nurse;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class Nurse_Dashboard extends JPanel implements ActionListener{

    private JPanel pnlMain, pnlPat, tabUpdate, tabItem, tabLows, tabMed, tabSup,
                   pnlSchedule, pnlSummary, pnlSelection;
    private JLabel lblView, lblDashboard, lblDT, lblTItem, lblLStock, lblMed, lblSup,
                   lblPatientIcon, lblPatientName, lblPatientID, lblPatientRoom,
                   lblPatientDoctor, lblPatientNurse, lblPatientStatus,
                   lblSchedule, lblStatusAct, lblSummary, lblTasks;
    private JTable tblActivities, tblSummary, tblItems;
    private JTableHeader HActivities, summaryHeader, tblHdr;
    private DefaultTableCellRenderer centerRenderer, center;
    private JScrollPane scrActivities, scrSummary, scrItems;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton btnV, btnN, btnMH;

    private String currentSelectedPatientName = null;
    private DefaultTableModel scheduleModel;
    private List<Prescription> currentPrescriptions = new ArrayList<>();

    public Nurse_Dashboard() {
        setLayout(null);
        buildUI();
        loadData();
    }

    private void buildUI() {
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        lblDashboard = new JLabel("NURSE Dashboard");
        lblDashboard.setBounds(30, 20, 400, 40);
        lblDashboard.setFont(new Font("Calibri", Font.BOLD, 24));
        lblDashboard.setForeground(Color.BLACK);
        pnlMain.add(lblDashboard);

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

        tabItem = createTab("Assigned Patients", "0", darkBlue);
        tabItem.setBounds(30, 80, 370, 120);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);

        tabLows = createTab("Today's Appointments", "0", Yellow);
        tabLows.setBounds(420, 80, 370, 120);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);

        tabMed = createTab("Medications Due", "0", Blue);
        tabMed.setBounds(810, 80, 370, 120);
        pnlMain.add(tabMed);
        lblMed = (JLabel) tabMed.getComponent(1);

        tabSup = createTab("Critical Patients", "0", LightRed);
        tabSup.setBounds(1200, 80, 370, 120);
        pnlMain.add(tabSup);
        lblSup = (JLabel) tabSup.getComponent(1);

        pnlPat = new JPanel();
        pnlPat.setLayout(null);
        pnlPat.setBounds(30, 220, 430, 380);
        pnlPat.setBackground(Color.WHITE);
        pnlMain.add(pnlPat);

        imgPatient = new ImageIcon(getClass().getResource("/resources/Male_Icon.png"));
        imgPat = imgPatient.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblPatientIcon = new JLabel(new ImageIcon(imgPat));
        lblPatientIcon.setBounds(160, 20, 100, 100);
        pnlPat.add(lblPatientIcon);

        lblPatientName = new JLabel("No patient selected");
        lblPatientName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPatientName.setBounds(30, 130, 370, 25);
        lblPatientName.setHorizontalAlignment(SwingConstants.CENTER);
        pnlPat.add(lblPatientName);

        lblPatientID = new JLabel("");
        lblPatientID.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientID.setBounds(30, 160, 370, 22);
        lblPatientID.setHorizontalAlignment(SwingConstants.CENTER);
        pnlPat.add(lblPatientID);

        lblPatientStatus = new JLabel("");
        lblPatientStatus.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientStatus.setBounds(30, 185, 370, 22);
        lblPatientStatus.setHorizontalAlignment(SwingConstants.CENTER);
        pnlPat.add(lblPatientStatus);

        JPanel dvd = new JPanel();
        dvd.setBounds(20, 220, 390, 2);
        dvd.setBackground(neutralGray);
        pnlPat.add(dvd);

        lblPatientRoom = new JLabel("Room: —");
        lblPatientRoom.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientRoom.setBounds(30, 235, 370, 22);
        pnlPat.add(lblPatientRoom);

        lblPatientDoctor = new JLabel("Doctor: —");
        lblPatientDoctor.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientDoctor.setBounds(30, 262, 370, 22);
        pnlPat.add(lblPatientDoctor);

        lblPatientNurse = new JLabel("Nurse: —");
        lblPatientNurse.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientNurse.setBounds(30, 289, 370, 22);
        pnlPat.add(lblPatientNurse);

        btnV = new JButton("View Profile");
        btnV.setBounds(40, 325, 110, 35);
        btnV.setFont(new Font("Calibri", Font.BOLD, 14));
        btnV.setBackground(darkBlue);
        btnV.setForeground(Color.WHITE);
        btnV.setFocusPainted(false);
        pnlPat.add(btnV);

        btnMH = new JButton("Medical History");
        btnMH.setBounds(160, 325, 130, 35);
        btnMH.setFont(new Font("Calibri", Font.BOLD, 14));
        btnMH.setBackground(mediumBlue);
        btnMH.setForeground(Color.WHITE);
        btnMH.setFocusPainted(false);
        pnlPat.add(btnMH);

        btnN = new JButton("View Notes");
        btnN.setBounds(300, 325, 110, 35);
        btnN.setFont(new Font("Calibri", Font.BOLD, 14));
        btnN.setBackground(lightBlue);
        btnN.setForeground(Color.BLACK);
        btnN.setFocusPainted(false);
        pnlPat.add(btnN);

        pnlSchedule = new JPanel();
        pnlSchedule.setLayout(null);
        pnlSchedule.setBounds(480, 220, 1090, 380);
        pnlSchedule.setBackground(Color.WHITE);
        pnlMain.add(pnlSchedule);

        lblSchedule = new JLabel("Today's Medication Schedule");
        lblSchedule.setBounds(20, 20, 300, 30);
        lblSchedule.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSchedule.add(lblSchedule);

        lblView = new JLabel("View All");
        lblView.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblView.setForeground(Color.BLUE);
        lblView.setBounds(1000, 20, 80, 30);
        lblView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblView.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMain.removeAll();
                pnlMain.add(new Prescription_Nurse());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSchedule.add(lblView);

        String[] clmSchedule = {"Time", "Patient Name", "Room", "Medicine", "Dosage", "Status"};
        scheduleModel = new DefaultTableModel(clmSchedule, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        tblActivities = new JTable(scheduleModel);
        tblActivities.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblActivities.setRowHeight(30);
        tblActivities.setGridColor(Color.LIGHT_GRAY);
        tblActivities.setBackground(Color.WHITE);
        tblActivities.setSelectionBackground(HighBlue);
        tblActivities.setSelectionForeground(Color.BLACK);

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < clmSchedule.length - 1; i++) {
            tblActivities.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblActivities.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JLabel lbl = new JLabel(value == null ? "Pending" : value.toString(), SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setFont(new Font("Calibri", Font.PLAIN, 14));
            String v = value == null ? "Pending" : value.toString();
            switch (v) {
                case "Given":    lbl.setBackground(MintGreen); break;
                case "Pending":  lbl.setBackground(PaleYellow); break;
                case "Missed":   lbl.setBackground(SoftPink); break;
                case "Refused":  lbl.setBackground(orange); break;
                default:         lbl.setBackground(Color.WHITE);
            }
            if (isSelected) {
                lbl.setBackground(HighBlue);
                lbl.setForeground(Color.BLACK);
            }
            return lbl;
        });

        tblActivities.getColumnModel().getColumn(5).setCellEditor(
            new DefaultCellEditor(new JComboBox<>(new String[]{"Pending", "Given", "Missed", "Refused"})) {
                @Override
                public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                    JComboBox<String> combo = (JComboBox<String>) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                    combo.setSelectedItem(value == null ? "Pending" : value.toString());
                    return combo;
                }

                @Override
                public boolean stopCellEditing() {
                    String newStatus = (String) getCellEditorValue();
                    int row = tblActivities.getEditingRow();
                    if (row == -1 || row >= currentPrescriptions.size()) {
                        return super.stopCellEditing();
                    }
                    int prescriptionId = currentPrescriptions.get(row).getPrescriptionId();
                    if (PrescriptionSQL.updatePrescriptionStatus(prescriptionId, newStatus)) {
                        scheduleModel.setValueAt(newStatus, row, 5);
                        tblActivities.repaint();
                    } else {
                        JOptionPane.showMessageDialog(tblActivities, "Failed to update status in database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return super.stopCellEditing();
                }
            }
        );

        HActivities = tblActivities.getTableHeader();
        HActivities.setBackground(lightBlue);
        HActivities.setFont(new Font("Calibri", Font.BOLD, 14));
        HActivities.setForeground(Color.BLACK);

        scrActivities = new JScrollPane(tblActivities);
        scrActivities.setBounds(20, 70, 1050, 280);
        pnlSchedule.add(scrActivities);

        tblActivities.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblActivities.getSelectedRow();
                if (row != -1) {
                    String patientName = (String) scheduleModel.getValueAt(row, 1);
                    selectPatient(patientName);
                }
            }
        });

        pnlSummary = new JPanel();
        pnlSummary.setLayout(null);
        pnlSummary.setBounds(30, 620, 900, 280);
        pnlSummary.setBackground(Color.WHITE);
        pnlMain.add(pnlSummary);

        lblSummary = new JLabel("Room Assignment Summary");
        lblSummary.setBounds(20, 15, 400, 30);
        lblSummary.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSummary.add(lblSummary);

        lblView = new JLabel("View All");
        lblView.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblView.setForeground(Color.BLUE);
        lblView.setBounds(820, 15, 80, 30);
        lblView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblView.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMain.removeAll();
                pnlMain.add(new Nurse_SchedAppointment());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSummary.add(lblView);

        String[] clmSummary = {"Room", "Patient Name", "Doctor"};
        DefaultTableModel summaryModel = new DefaultTableModel(clmSummary, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblSummary = new JTable(summaryModel);
        tblSummary.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblSummary.setRowHeight(35);
        tblSummary.setGridColor(Color.LIGHT_GRAY);
        tblSummary.setBackground(Color.WHITE);

        summaryHeader = tblSummary.getTableHeader();
        summaryHeader.setBackground(lightBlue);
        summaryHeader.setFont(new Font("Calibri", Font.BOLD, 14));

        center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < clmSummary.length; i++) {
            tblSummary.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        scrSummary = new JScrollPane(tblSummary);
        scrSummary.setBounds(20, 50, 860, 200);
        scrSummary.setBorder(BorderFactory.createEmptyBorder());
        pnlSummary.add(scrSummary);

        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBounds(950, 620, 620, 280);
        pnlSelection.setBackground(Color.WHITE);
        pnlMain.add(pnlSelection);

        lblTasks = new JLabel("Supply Requests");
        lblTasks.setBounds(20, 15, 300, 30);
        lblTasks.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSelection.add(lblTasks);

        lblView = new JLabel("View All");
        lblView.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblView.setForeground(Color.BLUE);
        lblView.setBounds(540, 15, 80, 30);
        lblView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblView.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMain.removeAll();
                pnlMain.add(new RequestPanel_Nurse());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSelection.add(lblView);

        String[] clmItems = {"Category", "Item", "Qty", "Status"};
        DefaultTableModel itemsModel = new DefaultTableModel(clmItems, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblItems = new JTable(itemsModel);
        tblItems.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblItems.setRowHeight(35);
        tblItems.setGridColor(Color.LIGHT_GRAY);
        tblItems.setBackground(Color.WHITE);

        tblHdr = tblItems.getTableHeader();
        tblHdr.setBackground(lightBlue);
        tblHdr.setFont(new Font("Calibri", Font.BOLD, 14));

        center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < clmItems.length; i++) {
            tblItems.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        scrItems = new JScrollPane(tblItems);
        scrItems.setBounds(20, 50, 580, 200);
        pnlSelection.add(scrItems);

        btnV.addActionListener(e -> {
            if (currentSelectedPatientName == null || currentSelectedPatientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
                return;
            }
            PatientManagementSQL.setCurrentPatient(currentSelectedPatientName, "");
            pnlMain.removeAll();
            pnlMain.add(new PatientInfo_Nurse(currentSelectedPatientName));
            pnlMain.revalidate();
            pnlMain.repaint();
        });

        btnMH.addActionListener(e -> {
            if (currentSelectedPatientName == null || currentSelectedPatientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
                return;
            }
            PatientManagementSQL.setCurrentPatient(currentSelectedPatientName, "");
            pnlMain.removeAll();
            pnlMain.add(new MedicalHistory_Nurse(currentSelectedPatientName));
            pnlMain.revalidate();
            pnlMain.repaint();
        });

        btnN.addActionListener(e -> {
            if (currentSelectedPatientName == null || currentSelectedPatientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
                return;
            }
            List<Patient> patients = PatientManagementSQL.getAllPatients();
            String notes = "";
            for (Patient p : patients) {
                if (p.getName().equalsIgnoreCase(currentSelectedPatientName)) {
                    notes = p.getNotes() == null ? "" : p.getNotes();
                    break;
                }
            }
            JTextArea notesArea = new JTextArea(notes.isEmpty() ? "No notes available." : notes);
            notesArea.setFont(new Font("Calibri", Font.PLAIN, 14));
            notesArea.setEditable(false);
            notesArea.setLineWrap(true);
            notesArea.setWrapStyleWord(true);
            JScrollPane sp = new JScrollPane(notesArea);
            sp.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, sp, "Notes for " + currentSelectedPatientName, JOptionPane.PLAIN_MESSAGE);
        });
    }

    private void loadData() {
        loadMedicationSchedule();
        loadRoomSummary();
        loadSupplyRequests();
        loadDashboardMetrics();
        if (scheduleModel.getRowCount() > 0) {
            selectPatient((String) scheduleModel.getValueAt(0, 1));
        }
    }

    private void loadMedicationSchedule() {
        scheduleModel.setRowCount(0);
        currentPrescriptions.clear();
        String loggedInNurse = UserManagementSQL.currentEmployee.getName() + " (Nurse)";
        List<Patient> myPatients = PatientManagementSQL.getPatientsByNurse(loggedInNurse);

        List<Prescription> allPrescriptions = PrescriptionSQL.getAllPrescriptions();
        for (Prescription p : allPrescriptions) {
            boolean patientAssigned = false;
            String patientRoom = "N/A";
            for (Patient pat : myPatients) {
                if (pat.getName().equalsIgnoreCase(p.getPatientName())) {
                    patientAssigned = true;
                    patientRoom = pat.getRoom() == null ? "N/A" : pat.getRoom();
                    break;
                }
            }
            if (!patientAssigned) continue;

            String time = "As ordered";
            int freq = p.getFrequency();
            if (freq == 1) time = "Morning";
            else if (freq == 2) time = "Morning/Evening";
            else if (freq == 3) time = "Morning/Noon/Evening";
            else if (freq == 4) time = "Every 6 hours";

            scheduleModel.addRow(new Object[]{
                time,
                p.getPatientName(),
                patientRoom,
                p.getDrugName(),
                p.getDosage(),
                p.getStatus() != null ? p.getStatus() : "Pending"
            });
            currentPrescriptions.add(p);
        }
    }

    private void loadRoomSummary() {
        DefaultTableModel model = (DefaultTableModel) tblSummary.getModel();
        model.setRowCount(0);
        String loggedInNurse = UserManagementSQL.currentEmployee.getName() + " (Nurse)";

        List<Patient> allPatients = PatientManagementSQL.getAllPatients();
        for (Patient p : allPatients) {
            if (p.getNurse() == null || !p.getNurse().equalsIgnoreCase(loggedInNurse)) continue;
            if (p.getRoom() == null || p.getRoom().trim().isEmpty()) continue;

            String doctor = (p.getDoctor() == null || p.getDoctor().isEmpty()) ? "N/A" : p.getDoctor();
            model.addRow(new Object[]{p.getRoom(), p.getName(), doctor});
        }
    }

    private void loadSupplyRequests() {
        DefaultTableModel model = (DefaultTableModel) tblItems.getModel();
        model.setRowCount(0);
        String currentUserId = UserManagementSQL.currentEmployee.getId();
        List<Object[]> requests = MedicalRequestSQL.getRequestsByUser(currentUserId);
        for (Object[] row : requests) {
            model.addRow(new Object[]{row[1], row[2], row[3], row[5]});
        }
    }

    private void loadDashboardMetrics() {
        String loggedInNurse = UserManagementSQL.currentEmployee.getName() + " (Nurse)";
        List<Patient> myPatients = PatientManagementSQL.getPatientsByNurse(loggedInNurse);
        int totalPatients = myPatients.size();
        int critical = 0;
        for (Patient p : myPatients) {
            if ("Critical".equalsIgnoreCase(p.getStatus())) critical++;
        }

        int todayAppointments = 0;
        String todayStr = LocalDate.now().toString();
        List<Appointment> allAppts = AppointmentSQL.getAllAppointments();
        for (Appointment a : allAppts) {
            if (myPatients.stream().anyMatch(p -> p.getName().equalsIgnoreCase(a.getPatientName()))
                    && a.getAppointmentDate() != null && a.getAppointmentDate().startsWith(todayStr)) {
                todayAppointments++;
            }
        }

        int medsDue = 0;
        List<Prescription> allPrescriptions = PrescriptionSQL.getAllPrescriptions();
        for (Prescription p : allPrescriptions) {
            if (myPatients.stream().anyMatch(pat -> pat.getName().equalsIgnoreCase(p.getPatientName()))) {
                medsDue++;
            }
        }

        lblTItem.setText(String.valueOf(totalPatients));
        lblLStock.setText(String.valueOf(todayAppointments));
        lblMed.setText(String.valueOf(medsDue));
        lblSup.setText(String.valueOf(critical));
    }

    private void selectPatient(String patientName) {
        currentSelectedPatientName = patientName;
        List<Patient> patients = PatientManagementSQL.getAllPatients();
        Patient found = null;
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(patientName)) {
                found = p;
                break;
            }
        }

        if (found != null) {
            lblPatientName.setText(found.getName());
            lblPatientID.setText("ID: " + found.getId());
            lblPatientStatus.setText("Status: " + found.getStatus());
            lblPatientRoom.setText("Room: " + (found.getRoom() == null || found.getRoom().isEmpty() ? "N/A" : found.getRoom()));
            lblPatientDoctor.setText("Doctor: " + found.getDoctor());
            lblPatientNurse.setText("Nurse: " + found.getNurse());
            PatientManagementSQL.setCurrentPatient(found.getName(), found.getId());
        } else {
            lblPatientName.setText(patientName);
            lblPatientID.setText("ID: —");
            lblPatientStatus.setText("Status: —");
            lblPatientRoom.setText("Room: —");
            lblPatientDoctor.setText("Doctor: —");
            lblPatientNurse.setText("Nurse: —");
            PatientManagementSQL.setCurrentPatient(patientName, "");
        }
        pnlPat.revalidate();
        pnlPat.repaint();
    }

    private JPanel createTab(String title, String value, Color color) {
        JPanel tab = new JPanel();
        tab.setLayout(null);
        tab.setBackground(color);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 20, 250, 25);
        tab.add(lblTitle);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 50, 150, 40);
        tab.add(lblValue);

        return tab;
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}