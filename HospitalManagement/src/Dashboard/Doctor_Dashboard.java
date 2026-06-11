package Dashboard;

import Appointments.Doctor_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Database.AppointmentSQL;
import Database.MedicalRequestSQL;
import Database.PatientManagementSQL;
import Database.UserManagementSQL;
import Inventory.RequestPanel_Doctor;
import MedicalHistory.MedicalHistory_Doctor;
import Models.Appointment;
import Models.Patient;
import Patient_Information.PatientInfo_Doctor;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class Doctor_Dashboard extends JPanel {

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
    private JButton btnV, btnMH, btnN;

    private String currentSelectedPatientName = null;

    public Doctor_Dashboard() {
        setLayout(null);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        lblDashboard = new JLabel("DOCTOR Dashboard");
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

        // Summary tabs
        tabItem = createTab("Total Patients", "0", darkBlue);
        tabItem.setBounds(30, 80, 370, 120);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);

        tabLows = createTab("Total Appointments", "0", Yellow);
        tabLows.setBounds(420, 80, 370, 120);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);

        tabMed = createTab("Total Requested Items", "0", Blue);
        tabMed.setBounds(810, 80, 370, 120);
        pnlMain.add(tabMed);
        lblMed = (JLabel) tabMed.getComponent(1);

        tabSup = createTab("Critical Patients", "0", LightRed);
        tabSup.setBounds(1200, 80, 370, 120);
        pnlMain.add(tabSup);
        lblSup = (JLabel) tabSup.getComponent(1);

        // Patient panel
        pnlPat = new JPanel();
        pnlPat.setLayout(null);
        pnlPat.setBounds(30, 220, 430, 380);
        pnlPat.setBackground(Color.WHITE);
        pnlMain.add(pnlPat);

        imgPatient = new ImageIcon(getClass().getResource("/resources/Male_Icon.png"));
        imgPat = imgPatient.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblPatientIcon = new JLabel(new ImageIcon(imgPat));
        lblPatientIcon.setBounds(30, 20, 100, 100);
        pnlPat.add(lblPatientIcon);

        lblPatientName = new JLabel("Patient Name:");
        lblPatientName.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPatientName.setBounds(150, 20, 260, 25);
        pnlPat.add(lblPatientName);

        lblPatientID = new JLabel("Patient ID: —");
        lblPatientID.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientID.setBounds(150, 50, 260, 22);
        pnlPat.add(lblPatientID);

        lblPatientStatus = new JLabel("Status: —");
        lblPatientStatus.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientStatus.setBounds(150, 75, 260, 22);
        pnlPat.add(lblPatientStatus);

        JPanel dvd = new JPanel();
        dvd.setBounds(20, 125, 390, 2);
        dvd.setBackground(neutralGray);
        pnlPat.add(dvd);

        lblPatientRoom = new JLabel("Room: —");
        lblPatientRoom.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientRoom.setBounds(30, 140, 360, 22);
        pnlPat.add(lblPatientRoom);

        lblPatientDoctor = new JLabel("Doctor: —");
        lblPatientDoctor.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientDoctor.setBounds(30, 168, 360, 22);
        pnlPat.add(lblPatientDoctor);

        lblPatientNurse = new JLabel("Nurse: —");
        lblPatientNurse.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPatientNurse.setBounds(30, 196, 360, 22);
        pnlPat.add(lblPatientNurse);

        // Buttons: View Profile, Medical History, View Notes
        btnV = new JButton("View Profile");
        btnV.setBounds(40, 310, 110, 35);
        btnV.setFont(new Font("Calibri", Font.BOLD, 14));
        btnV.setFocusPainted(false);
        btnV.setBackground(darkBlue);
        btnV.setForeground(Color.WHITE);
        pnlPat.add(btnV);

        btnMH = new JButton("Medical History");
        btnMH.setBounds(160, 310, 130, 35);
        btnMH.setFont(new Font("Calibri", Font.BOLD, 14));
        btnMH.setFocusPainted(false);
        btnMH.setBackground(mediumBlue);
        btnMH.setForeground(Color.WHITE);
        pnlPat.add(btnMH);

        btnN = new JButton("View Notes");
        btnN.setBounds(300, 310, 110, 35);
        btnN.setFont(new Font("Calibri", Font.BOLD, 14));
        btnN.setFocusPainted(false);
        btnN.setBackground(lightBlue);
        btnN.setForeground(Color.BLACK);
        pnlPat.add(btnN);

        btnV.addActionListener(e -> {
            if (currentSelectedPatientName == null) {
                JOptionPane.showMessageDialog(this, "Please select an appointment first.");
                return;
            }
            PatientManagementSQL.setCurrentPatient(currentSelectedPatientName, "");
            pnlMain.removeAll();
            pnlMain.add(new PatientInfo_Doctor(currentSelectedPatientName));
            pnlMain.revalidate();
            pnlMain.repaint();
        });

        btnMH.addActionListener(e -> {
            if (currentSelectedPatientName == null) {
                JOptionPane.showMessageDialog(this, "Please select an appointment first.");
                return;
            }
            PatientManagementSQL.setCurrentPatient(currentSelectedPatientName, "");
            pnlMain.removeAll();
            pnlMain.add(new MedicalHistory_Doctor(currentSelectedPatientName));
            pnlMain.revalidate();
            pnlMain.repaint();
        });

        btnN.addActionListener(e -> {
            if (currentSelectedPatientName == null) {
                JOptionPane.showMessageDialog(this, "Please select an appointment first.");
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
            notesArea.setFont(new Font("Calibri", Font.PLAIN, 16));
            notesArea.setEditable(false);
            notesArea.setLineWrap(true);
            notesArea.setWrapStyleWord(true);
            JScrollPane sp = new JScrollPane(notesArea);
            sp.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, sp,
                    "Notes for " + currentSelectedPatientName, JOptionPane.PLAIN_MESSAGE);
        });

        // All Appointments panel
        pnlSchedule = new JPanel();
        pnlSchedule.setLayout(null);
        pnlSchedule.setBounds(480, 220, 1090, 380);
        pnlSchedule.setBackground(Color.WHITE);
        pnlMain.add(pnlSchedule);

        lblSchedule = new JLabel("All Appointments");
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
                pnlMain.add(new Doctor_SchedAppointment());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSchedule.add(lblView);

        String[] clmSchedule = {"Date", "Time", "Patient Name", "Room", "Reason", "Status"};
        DefaultTableModel scheduleModel = new DefaultTableModel(clmSchedule, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
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

        // Status renderer
        tblActivities.getColumnModel().getColumn(5).setCellRenderer(
            (table, value, isSelected, hasFocus, row, column) -> {
                lblStatusAct = new JLabel(value == null ? "" : value.toString(), SwingConstants.CENTER);
                lblStatusAct.setOpaque(true);
                lblStatusAct.setFont(new Font("Calibri", Font.PLAIN, 16));
                switch (value == null ? "" : value.toString()) {
                    case "In Progress":   lblStatusAct.setBackground(lightBlue);  break;
                    case "Scheduled":     lblStatusAct.setBackground(MintGreen);  break;
                    case "Pending":       lblStatusAct.setBackground(PaleYellow); break;
                    case "Not Confirmed": lblStatusAct.setBackground(Lavender);   break;
                    case "Completed":     lblStatusAct.setBackground(Teal);       break;
                    case "Urgent":
                        lblStatusAct.setBackground(SoftPink);
                        lblStatusAct.setFont(new Font("Calibri", Font.BOLD, 16));
                        break;
                    default: lblStatusAct.setBackground(Color.WHITE); break;
                }
                if (isSelected) {
                    lblStatusAct.setBackground(HighBlue);
                    lblStatusAct.setForeground(Color.BLACK);
                }
                return lblStatusAct;
            });

        HActivities = tblActivities.getTableHeader();
        HActivities.setBackground(lightBlue);
        HActivities.setFont(new Font("Calibri", Font.BOLD, 14));
        HActivities.setForeground(Color.BLACK);

        scrActivities = new JScrollPane(tblActivities);
        scrActivities.setBounds(20, 70, 1050, 280);
        pnlSchedule.add(scrActivities);

        // Row selection → populate patient panel
        tblActivities.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblActivities.getSelectedRow();
                if (row == -1) return;

                String selPatientName = (String) scheduleModel.getValueAt(row, 2);
                currentSelectedPatientName = selPatientName;

                List<Patient> patients = PatientManagementSQL.getAllPatients();
                Patient found = null;
                for (Patient p : patients) {
                    if (p.getName().equalsIgnoreCase(selPatientName)) {
                        found = p;
                        break;
                    }
                }

                if (found != null) {
                    lblPatientName.setText("Patient: " + found.getName());
                    lblPatientID.setText("ID: " + found.getId());
                    lblPatientStatus.setText("Status: " + found.getStatus());
                    lblPatientRoom.setText("Room: " + (found.getRoom() == null || found.getRoom().isEmpty() ? "N/A" : found.getRoom()));
                    lblPatientDoctor.setText("Doctor: " + found.getDoctor());
                    lblPatientNurse.setText("Nurse: " + found.getNurse());
                    PatientManagementSQL.setCurrentPatient(found.getName(), found.getId());
                } else {
                    lblPatientName.setText("Patient: " + selPatientName);
                    lblPatientID.setText("ID: —");
                    lblPatientStatus.setText("Status: —");
                    lblPatientRoom.setText("Room: —");
                    lblPatientDoctor.setText("Doctor: —");
                    lblPatientNurse.setText("Nurse: —");
                    PatientManagementSQL.setCurrentPatient(selPatientName, "");
                }
                pnlPat.revalidate();
                pnlPat.repaint();
            }
        });

        // Room Assignment Summary
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
                pnlMain.add(new Doctor_SchedAppointment());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSummary.add(lblView);

        String[] clmSummary = {"Room", "Patient Name", "Nurse"};
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

        // Requested Items
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBounds(950, 620, 620, 280);
        pnlSelection.setBackground(Color.WHITE);
        pnlMain.add(pnlSelection);

        lblTasks = new JLabel("Requested Items");
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
                pnlMain.add(new RequestPanel_Doctor());
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
        tblItems.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        tblHdr.setBackground(lightBlue);
        tblItems.getTableHeader().setForeground(Color.BLACK);

        center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < clmItems.length; i++) {
            tblItems.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        scrItems = new JScrollPane(tblItems);
        scrItems.setBounds(20, 50, 580, 200);
        pnlSelection.add(scrItems);

        loadAllAppointments(scheduleModel);
        loadRoomSummary(summaryModel);
        loadRequestedItems(itemsModel);
        loadDashboardMetrics();
        setVisible(true);
    }

    private void loadAllAppointments(DefaultTableModel model) {
        model.setRowCount(0);
        String loggedInDoctor = "Dr. " + UserManagementSQL.currentEmployee.getName();

        List<Appointment> allAppts = AppointmentSQL.getAllAppointments();
        List<Appointment> doctorAppts = new ArrayList<>();

        for (Appointment a : allAppts) {
            if (a.getDoctorName().equalsIgnoreCase(loggedInDoctor)) {
                doctorAppts.add(a);
            }
        }

        doctorAppts.sort((a, b) -> {
            String dA = a.getAppointmentDate() == null ? "" : a.getAppointmentDate();
            String dB = b.getAppointmentDate() == null ? "" : b.getAppointmentDate();
            return dA.compareTo(dB);
        });

        List<Patient> patients = PatientManagementSQL.getAllPatients();

        for (Appointment a : doctorAppts) {
            String date = "—";
            String time = "—";
            if (a.getAppointmentDate() != null) {
                if (a.getAppointmentDate().contains(" ")) {
                    date = a.getAppointmentDate().substring(0, a.getAppointmentDate().indexOf(" "));
                    time = a.getAppointmentDate().substring(a.getAppointmentDate().indexOf(" ") + 1);
                } else {
                    date = a.getAppointmentDate();
                }
            }

            String room = "N/A";
            for (Patient p : patients) {
                if (p.getName().equalsIgnoreCase(a.getPatientName())) {
                    room = (p.getRoom() == null || p.getRoom().isEmpty()) ? "N/A" : p.getRoom();
                    break;
                }
            }

            model.addRow(new Object[]{date, time, a.getPatientName(), room, a.getTreatment(), a.getStatus()});
        }
    }

    private void loadRoomSummary(DefaultTableModel model) {
        model.setRowCount(0);
        String loggedInDoctor = "Dr. " + UserManagementSQL.currentEmployee.getName();

        List<Patient> allPatients = PatientManagementSQL.getAllPatients();
        for (Patient p : allPatients) {
            if (p.getDoctor() == null || !p.getDoctor().equalsIgnoreCase(loggedInDoctor)) continue;
            if (p.getRoom() == null || p.getRoom().trim().isEmpty()) continue;

            String nurse = (p.getNurse() == null || p.getNurse().trim().isEmpty()) ? "N/A" : p.getNurse();
            model.addRow(new Object[]{p.getRoom(), p.getName(), nurse});
        }
    }

    private void loadRequestedItems(DefaultTableModel model) {
        model.setRowCount(0);
        String currentUserId = UserManagementSQL.currentEmployee.getId();
        List<Object[]> requests = MedicalRequestSQL.getRequestsByUser(currentUserId);
        for (Object[] row : requests) {
            if (model.getRowCount() >= 5) break;
            model.addRow(new Object[]{row[1], row[2], row[3], row[5]});
        }
    }

    private void loadDashboardMetrics() {
        String loggedInDoctor = "Dr. " + UserManagementSQL.currentEmployee.getName();

        List<Patient> allPatients = PatientManagementSQL.getAllPatients();
        int totalPatients = 0;
        int critical = 0;
        for (Patient p : allPatients) {
            if (p.getDoctor() != null && p.getDoctor().equalsIgnoreCase(loggedInDoctor)) {
                totalPatients++;
                if ("Critical".equalsIgnoreCase(p.getStatus())) critical++;
            }
        }

        List<Appointment> allAppts = AppointmentSQL.getAllAppointments();
        int totalAppts = 0;
        for (Appointment a : allAppts) {
            if (a.getDoctorName().equalsIgnoreCase(loggedInDoctor)) totalAppts++;
        }

        String currentUserId = UserManagementSQL.currentEmployee.getId();
        List<Object[]> requests = MedicalRequestSQL.getRequestsByUser(currentUserId);
        int totalRequested = requests.size();

        lblTItem.setText(String.valueOf(totalPatients));
        lblLStock.setText(String.valueOf(totalAppts));
        lblMed.setText(String.valueOf(totalRequested));
        lblSup.setText(String.valueOf(critical));
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
}