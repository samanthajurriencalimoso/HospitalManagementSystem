package Appointments;

import static Color_Palette.ColorPalette.*;
import Database.AppointmentSQL;
import Database.PatientManagementSQL;
import Database.UserManagementSQL;
import MedicalHistory.MedicalHistory_Nurse;
import Models.Appointment;
import Models.Patient;
import Patient_Information.PatientInfo_Nurse;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;

public class Nurse_SchedAppointment extends JPanel implements ActionListener {
    
    private JPanel pnlMain, tabUpdate, tabItem, tabLows, tabMed, tabSup, pnlRecent, dvdType, dvdStatus, pnlNote,
                    pnlSched, dvdCal, dvdTable, pnlEdit2, pnlNew;
    private JLabel lbltitle, lblDT, lblTitle, lblPatientIcon, lblPatient, lblPatID, lblTItem, lblLStock, lblMed, lblSup,
                   lblComp, lblType, lblTime, lblRoom, lblStatus, lblStaff, lblDoctor, lblNurse, lblNotes, lblCalendar,
                   lblMonth, lblTDAp, lblValue, lblDay, lblMore, lblEv, lblTime2, lblPID, lblPName, lblDocSelect;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton btnRecord, btnNote, btnMedHistory, btnEdit, btnPrev, btnNext, btnNew, btnEdit2, btnCancel;
    private JTextArea txtNote;
    private JTextField PID, txtStatus;
    private JComboBox PName;
    private JScrollPane scrNote, scrCalendar, srcAppointments;
    private JTable tblCalendar, tblApp;
    private JTableHeader tblCHdr;
    private javax.swing.table.DefaultTableModel CModel;
    private YearMonth CMonth = YearMonth.now();
    
    private Map<String, java.util.List<Appointment>> appointmentsMap = new HashMap<>();
    
    private JComboBox<String> cmbTime, cmbType, cmbDoctor;
    private DefaultTableModel appModel;
    private LocalDate currentSelectedDate = null;
    private DateTimeFormatter formatter;
    private LocalDate FMonth, date, SDate, today, selectedDate;
    private Container par;
    private EventType et;
    
    public Nurse_SchedAppointment() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lbltitle = new JLabel("Nurse Appointment");
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
        
        tabItem = createTab("Today's Patients: ", "0", darkBlue);
        tabItem.setBounds(270, 20, 250, 50);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);

        tabLows = createTab("Medications Due: ", "0", Yellow);
        tabLows.setBounds(540, 20, 250, 50);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);

        tabMed = createTab("Critical Patients: ", "0", LightRed);
        tabMed.setBounds(810, 20, 250, 50);
        pnlMain.add(tabMed);
        lblMed = (JLabel) tabMed.getComponent(1);

        tabSup = createTab("Care Task Pending: ", "0", Blue);
        tabSup.setBounds(1080, 20, 250, 50);
        pnlMain.add(tabSup);
        lblSup = (JLabel) tabSup.getComponent(1);
        
        pnlRecent = new JPanel();
        pnlRecent.setLayout(null);
        pnlRecent.setBounds(30, 100, 450, 450);
        pnlRecent.setBackground(Color.WHITE);
        pnlMain.add(pnlRecent);
        
        imgPatient = new ImageIcon(getClass().getResource("/resources/Male_Icon.png"));
        imgPat = imgPatient.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblPatientIcon = new JLabel(new ImageIcon(imgPat));
        lblPatientIcon.setBounds(30, 20, 120, 120);
        pnlRecent.add(lblPatientIcon);
        
        lblPatient = new JLabel("Patient Name:");
        lblPatient.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPatient.setBounds(180, 15, 250, 30);
        pnlRecent.add(lblPatient);
        
        lblPatID = new JLabel("Patient ID: P-");
        lblPatID.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPatID.setBounds(180, 45, 250, 30);
        pnlRecent.add(lblPatID);
        
        lblComp = new JLabel("Care Need: ");
        lblComp.setFont(new Font("Calibri", Font.BOLD, 20));
        lblComp.setBounds(180, 75, 250, 30);
        pnlRecent.add(lblComp);
        
        lblType = new JLabel("Task: ");
        lblType.setFont(new Font("Calibri", Font.BOLD, 20));
        lblType.setBounds(180, 105, 250, 25);
        pnlRecent.add(lblType);
       
        dvdType = new JPanel();
        dvdType.setBounds(180, 135, 250, 2);
        dvdType.setBackground(neutralGray);
        pnlRecent.add(dvdType);

        lblTime = new JLabel("Time: ");
        lblTime.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblTime.setBounds(30, 150, 250, 25);
        pnlRecent.add(lblTime);
        
        lblRoom = new JLabel("Room: ");
        lblRoom.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblRoom.setBounds(30, 180, 250, 25);
        pnlRecent.add(lblRoom);
        
        lblStatus = new JLabel("Status: ");
        lblStatus.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblStatus.setBounds(30, 210, 250, 25);
        pnlRecent.add(lblStatus);
        
        dvdStatus = new JPanel();
        dvdStatus.setBounds(30, 240, 380, 2);
        dvdStatus.setBackground(neutralGray);
        pnlRecent.add(dvdStatus);
        
        lblStaff = new JLabel("Assigned Staff: ");
        lblStaff.setFont(new Font("Calibri", Font.BOLD, 20));
        lblStaff.setBounds(30, 260, 250, 25);
        pnlRecent.add(lblStaff);
        
        lblDoctor = new JLabel("Doctor: ");
        lblDoctor.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblDoctor.setBounds(60, 290, 250, 25);
        pnlRecent.add(lblDoctor);
        
        lblNurse = new JLabel("Nurse: ");
        lblNurse.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblNurse.setBounds(60, 320, 250, 25);
        pnlRecent.add(lblNurse);
        
        btnRecord = new JButton("View Records");
        btnRecord.setFont(new Font("Calibri", Font.BOLD, 16));
        btnRecord.setBounds(40, 370, 150, 35);
        pnlRecent.add(btnRecord);

        btnNote = new JButton("View Prescriptions");
        btnNote.setFont(new Font("Calibri", Font.BOLD, 16));
        btnNote.setBounds(210, 370, 200, 35);
        pnlRecent.add(btnNote);
        
        btnMedHistory = new JButton("Medical History");
        btnMedHistory.setFont(new Font("Calibri", Font.BOLD, 14));
        btnMedHistory.setBounds(40, 415, 370, 35);
        pnlRecent.add(btnMedHistory);
        
        pnlNote = new JPanel();
        pnlNote.setLayout(null);
        pnlNote.setBounds(30, 570, 450, 330);
        pnlNote.setBackground(lightBlue);
        pnlMain.add(pnlNote);
        
        lblNotes = new JLabel("Notes");
        lblNotes.setBounds(20, 20, 150, 30);
        lblNotes.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlNote.add(lblNotes);
        
        btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEdit.setBounds(335, 15, 90, 35);
        pnlNote.add(btnEdit);
        
        txtNote = new JTextArea();
        txtNote.setText("");
        txtNote.setFont(new Font("Calibri", Font.PLAIN, 20));
        txtNote.setEditable(false);
        txtNote.setLineWrap(true);
        txtNote.setWrapStyleWord(true);

        scrNote = new JScrollPane(txtNote);
        scrNote.setBounds(25, 65, 400, 240);
        pnlNote.add(scrNote);
        
        pnlSched = new JPanel();
        pnlSched.setLayout(null);
        pnlSched.setBounds(500, 100, 1060, 800);
        pnlSched.setBackground(Color.WHITE);
        pnlMain.add(pnlSched);
        
        lblCalendar = new JLabel("Calendar");
        lblCalendar.setBounds(20, 20, 150, 30);
        lblCalendar.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSched.add(lblCalendar);
        
        dvdCal = new JPanel();
        dvdCal.setBounds(20, 55, 1020, 2);
        dvdCal.setBackground(HighBlue);
        pnlSched.add(dvdCal);
        
        btnPrev = new JButton("<");
        btnPrev.setBounds(20, 70, 50, 40);
        btnPrev.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPrev.setBackground(darkBlue);
        btnPrev.setForeground(Color.WHITE);
        btnPrev.setFocusPainted(false);
        pnlSched.add(btnPrev);
        
        btnNext = new JButton(">");
        btnNext.setBounds(990, 70, 50, 40);
        btnNext.setFont(new Font("Calibri", Font.BOLD, 22));
        btnNext.setBackground(darkBlue);
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusPainted(false);
        pnlSched.add(btnNext);
        
        lblMonth = new JLabel("May 2026", SwingConstants.CENTER);
        lblMonth.setBounds(0, 70, 1060, 35);
        lblMonth.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlSched.add(lblMonth);
        
        String[] days = {"Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"};

        CModel = new DefaultTableModel(days, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblCalendar = new JTable(CModel);
        tblCalendar.setRowHeight(120);
        tblCalendar.setFont(new Font("Calibri", Font.PLAIN, 18));
        tblCalendar.setGridColor(Color.LIGHT_GRAY);
        tblCalendar.setBackground(Color.WHITE);
        tblCalendar.setShowGrid(true);
        tblCalendar.setGridColor(LightGray);
        tblCalendar.setIntercellSpacing(new Dimension(1, 1));
        tblCalendar.setSelectionBackground(lightBlue);
        tblCalendar.setSelectionForeground(Color.BLACK);
        tblCalendar.setDefaultRenderer(Object.class, new CalendarCellRenderer());
        
        loadAppointmentsFromDatabase();
        renderMonthCalendar();
        
        tblCHdr = tblCalendar.getTableHeader();
        tblCHdr.setBackground(lightBlue);
        tblCHdr.setFont(new Font("Calibri", Font.BOLD, 16));
        tblCHdr.setForeground(Color.BLACK);
        
        tblCalendar.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblCalendar.getSelectedRow();
                int col = tblCalendar.getSelectedColumn();
                if (row != -1 && col != -1) {
                    String cellValue = (String) tblCalendar.getValueAt(row, col);
                    if (cellValue != null && !cellValue.trim().isEmpty()) {
                        String dayText = cellValue.split("\n")[0].trim();
                        if (!dayText.matches("\\d+")) return;

                        selectedDate = CMonth.atDay(Integer.parseInt(dayText));
                        currentSelectedDate = selectedDate;
                        lblTDAp.setText("Appointments for " + selectedDate);

                        appModel.setRowCount(0);
                        String keyDate = selectedDate.toString();
                        if (appointmentsMap.containsKey(keyDate)) {
                            for (Appointment appt : appointmentsMap.get(keyDate)) {
                                String displayTime = "08:00 AM";
                                if (appt.getAppointmentDate().contains(" ")) {
                                    displayTime = appptTimeExtractor(appt.getAppointmentDate());
                                }
                                appModel.addRow(new Object[]{
                                    displayTime, appt.getDoctorName(), appt.getPatientName(), appt.getTreatment(), appt.getStatus()
                                });
                            }
                        }
                    }
                }
            }
        });
        
        scrCalendar = new JScrollPane(tblCalendar);
        scrCalendar.setBounds(20, 125, 1020, 420);
        pnlSched.add(scrCalendar);
        
        dvdTable = new JPanel();
        dvdTable.setBounds(20, 560, 1020, 2);
        dvdTable.setBackground(HighBlue);
        pnlSched.add(dvdTable);
        
        lblTDAp = new JLabel("Appointments for ");
        lblTDAp.setBounds(20, 570, 400, 30);
        lblTDAp.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTDAp.setForeground(Color.BLACK);
        pnlSched.add(lblTDAp);
        
        String[] clmAppointments = {"Time","Doctor","Patient Name","Type","Status"};

        appModel = new DefaultTableModel(clmAppointments, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblApp = new JTable(appModel);
        tblApp.setRowHeight(40);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        tblCHdr = tblApp.getTableHeader();
        tblCHdr.setBackground(lightBlue);
        tblCHdr.setFont(new Font("Calibri", Font.BOLD, 16));
        tblCHdr.setForeground(Color.BLACK);
        
        srcAppointments = new JScrollPane(tblApp);
        srcAppointments.setBounds(20, 610, 1020, 120);
        pnlSched.add(srcAppointments);
        
        tblApp.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblApp.getSelectedRow();

                if (row != -1) {
                    String selectedTime   = (String) appModel.getValueAt(row, 0);
                    String selectedDoctor = (String) appModel.getValueAt(row, 1);
                    String selectedPName  = (String) appModel.getValueAt(row, 2);
                    String selectedType   = (String) appModel.getValueAt(row, 3);
                    String selectedStatus = (String) appModel.getValueAt(row, 4);

                    String patientId = "N/A";
                    String room      = "N/A";
                    String doctor    = selectedDoctor;
                    String nurse     = "N/A";
                    String notes     = "";
                    String condition = "N/A";

                    java.util.List<Patient> patients = PatientManagementSQL.getAllPatients();

                    for (Patient p : patients) {
                        if (p.getName().equalsIgnoreCase(selectedPName)) {

                            patientId = p.getId();

                            if (p.getRoom() != null && !p.getRoom().isEmpty()) {
                                room = p.getRoom();
                            }

                            if (p.getDoctor() != null && !p.getDoctor().isEmpty()) {
                                doctor = p.getDoctor();
                            }

                            if (p.getNurse() != null && !p.getNurse().isEmpty()) {
                                nurse = p.getNurse();
                            }

                            if (p.getNotes() != null) {
                                notes = p.getNotes();
                            }

                            if (p.getConditions() != null && !p.getConditions().isEmpty()) {
                                condition = p.getConditions();
                            }

                            break;
                        }
                    }

                    lblPatient.setText("Patient Name: " + selectedPName);
                    lblPatID.setText("Patient ID: " + patientId);
                    lblComp.setText("Care Need: " + condition);
                    lblType.setText("Task: " + selectedType);

                    lblTime.setText("Time: " + selectedTime);
                    lblRoom.setText("Room: " + room);
                    lblStatus.setText("Status: " + selectedStatus);

                    lblDoctor.setText("Doctor: " + doctor);
                    lblNurse.setText("Nurse: " + nurse);

                    txtNote.setText(notes);

                    pnlRecent.revalidate();
                    pnlRecent.repaint();
                }
            }
        });
        
        btnNew = new JButton("+ New Appointment");
        btnNew.setBounds(20, 745, 170, 35);
        btnNew.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnNew.setBackground(darkBlue);
        btnNew.setForeground(Color.WHITE);
        btnNew.setFocusPainted(false);
        pnlSched.add(btnNew);
        
        btnEdit2 = new JButton("Edit");
        btnEdit2.setBounds(220, 745, 120, 35);
        btnEdit2.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnEdit2.setBackground(lightBlue);
        btnEdit2.setForeground(Color.BLACK);
        btnEdit2.setFocusPainted(false);
        pnlSched.add(btnEdit2);
        
        btnCancel = new JButton("Remove");
        btnCancel.setBounds(920, 745, 120, 35);
        btnCancel.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnCancel.setBackground(LightRed);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        pnlSched.add(btnCancel);
        
        btnRecord.addActionListener(this);
        btnNote.addActionListener(this);
        btnMedHistory.addActionListener(this);
        btnEdit.addActionListener(this);
        btnPrev.addActionListener(this);
        btnNext.addActionListener(this);
        btnNew.addActionListener(this);
        btnEdit2.addActionListener(this);
        btnCancel.addActionListener(this);
    }
    
    private void loadAppointmentsFromDatabase() {
        appointmentsMap.clear();
        java.util.List<Appointment> list = AppointmentSQL.getAllAppointments();
        for (Appointment app : list) {
            String fullDate = app.getAppointmentDate();
            String datePart = fullDate.split(" ")[0]; 
            appointmentsMap.computeIfAbsent(datePart, k -> new ArrayList<>()).add(app);
        }
    }
    
    private void saveNurseCompletionReport(String patientName, String doctorName) {
        String patientId = "N/A";
        java.util.List<Patient> patients = PatientManagementSQL.getAllPatients();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(patientName)) {
                patientId = p.getId();
                break;
            }
        }

        String loggedInNurse = UserManagementSQL.currentEmployee.getName() + " (Nurse)";
        String reportData = "Vitals completed by nurse on " + LocalDate.now().toString();

        Models.NurseAppointment nurseReport = new Models.NurseAppointment(
            0,             
            patientName,
            patientId,
            reportData,
            "Completed",
            loggedInNurse,
            doctorName,
            null
        );

        int savedId = Database.NurseAppointmentSQL.saveReport(nurseReport);
        if (savedId > 0) {
            Database.NurseAppointmentSQL.sendToDoctor(savedId);
        }
    }

    private String appptTimeExtractor(String dateValue) {
        if (dateValue != null && dateValue.contains(" ")) {
            return dateValue.substring(dateValue.indexOf(" ") + 1);
        }
        return "08:00 AM";
    }
    
    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel();
        tabUpdate.setLayout(null);
        tabUpdate.setBackground(color);

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 10, 200, 30);
        tabUpdate.add(lblTitle);

        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 22));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(210, 10, 100, 30);
        tabUpdate.add(lblValue);

        return tabUpdate;
    }

    public enum EventType {
        APPOINTMENT("Appointment", Blue),
        FOLLOWUP   ("Follow-up",   Green),
        SURGERY    ("Surgery",      orange),
        EMERGENCY  ("Emergency",   LightRed);

        public final String label;
        public final Color  color;

        EventType(String label, Color color) { this.label = label; this.color = color; }

        public static EventType fromLabel(String label) {
            for (EventType t : values())
                if (t.label.equals(label)) return t;
            return APPOINTMENT;
        }
    }

    private void renderMonthCalendar() {
        CModel.setRowCount(0);

        formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        lblMonth.setText(CMonth.format(formatter));

        FMonth = CMonth.atDay(1);
        int daysInMonth = CMonth.lengthOfMonth();
        int dayOfWeekOffset = FMonth.getDayOfWeek().getValue() % 7;

        int CurrentD = 1;
        for (int r = 0; r < 6; r++) {
            String[] rowData = new String[7];
            for (int c = 0; c < 7; c++) {
                if (r == 0 && c < dayOfWeekOffset) {
                    rowData[c] = " ";
                } else if (CurrentD > daysInMonth) {
                    rowData[c] = " ";
                } else {
                    rowData[c] = String.valueOf(CurrentD);
                    CurrentD++;
                }
            }
            CModel.addRow(rowData);
            if (CurrentD > daysInMonth) break;
        }
        tblCalendar.revalidate();
        tblCalendar.repaint();
    }

    private void refreshAppointmentsTable() {
        appModel.setRowCount(0);
        lblTDAp.setText("Appointments for ");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRecord) {
            int row = tblApp.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment first.");
                return;
            }
            
            par = this.getParent();
            par.removeAll();
            String patientName = (String) tblApp.getValueAt(row, 2);
            par.add(new PatientInfo_Nurse(patientName));
            par.revalidate();
            par.repaint();
            
        } else if (ae.getSource() == btnMedHistory) {
            int row = tblApp.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment first.");
                return;
            }
            String pname = (String) appModel.getValueAt(row, 2);
            if (pname == null || pname.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selected appointment has incomplete data.");
                return;
            }
            par = this.getParent();
            par.removeAll();
            par.add(new MedicalHistory_Nurse(pname));
            par.revalidate();
            par.repaint();
        } else if (ae.getSource() == btnPrev) {
            CMonth = CMonth.minusMonths(1);
            renderMonthCalendar();
            refreshAppointmentsTable();
            
        } else if (ae.getSource() == btnNext) {
            CMonth = CMonth.plusMonths(1);
            renderMonthCalendar();
            refreshAppointmentsTable();
            
        } else if (ae.getSource() == btnNote) {
            JOptionPane.showMessageDialog(this, "No prescription details found for editing options here.");
        } else if (ae.getSource() == btnEdit) {
            int row = tblApp.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment first.");
                return;
            }
            String selectedPName = (String) appModel.getValueAt(row, 2);
            txtNote.setEditable(true);
            int confirm = JOptionPane.showConfirmDialog(
                this, "Save notes for " + selectedPName + "?", "Save Notes", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String newNotes = txtNote.getText().trim();
                java.util.List<Patient> allPatients = PatientManagementSQL.getAllPatients();
                for (Patient p : allPatients) {
                    if (p.getName().equalsIgnoreCase(selectedPName)) {
                        Patient updated = new Patient(
                            p.getId(), p.getName(), p.getRoom(), p.getDoctor(), p.getNurse(),
                            p.getStatus(), p.getAdmissionDate(), p.getAddress(), p.getContactNumber(),
                            p.getAllergies(), p.getConditions(), p.getMedications(), p.getSurgicalHistory(),
                            p.getFamilyHistory(), p.getLifestyleDiet(), p.getMedicalManagement(),
                            p.getDietBreakfast(), p.getDietLunch(), p.getDietDinner(),
                            p.getEmergencyName(), p.getEmergencyRel(), p.getEmergencyNum(),
                            p.getBloodType(), p.getAge(), newNotes, p.getBp(), p.getHr(),
                            p.getTemp(), p.getSpo2(), p.getGeneralAppearance(), p.getCardiovascular(),
                            p.getRespiratory(), p.getNeurological()
                        );
                        if (PatientManagementSQL.updatePatientProfile(updated)) {
                            JOptionPane.showMessageDialog(this, "Notes saved for " + selectedPName + ".");
                            txtNote.setEditable(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to save notes.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            } else {
                txtNote.setEditable(false);
            }
        } else if (ae.getSource() == btnCancel) {
            int row = tblApp.getSelectedRow();
            if (row != -1) {
                String timeVal = (String) tblApp.getValueAt(row, 0);
                String pname = (String) tblApp.getValueAt(row, 2);

                Appointment targetMatch = null;
                if (currentSelectedDate != null && appointmentsMap.containsKey(currentSelectedDate.toString())) {
                    for (Appointment a : appointmentsMap.get(currentSelectedDate.toString())) {
                        if (a.getPatientName().equalsIgnoreCase(pname) && appptTimeExtractor(a.getAppointmentDate()).equals(timeVal)) {
                            targetMatch = a;
                            break;
                        }
                    }
                }

                if (targetMatch != null && AppointmentSQL.deleteAppointment(targetMatch.getAppointmentId())) {
                    loadAppointmentsFromDatabase();
                    renderMonthCalendar();
                    refreshAppointmentsTable();
                    JOptionPane.showMessageDialog(this, "Appointment removed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an appointment to remove.");
            }
        }
    }

    class CalendarCellRenderer extends JPanel implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

            removeAll();
            setLayout(null);
            setBackground(isSelected ? lightBlue : Color.WHITE);
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, LightGray));

            String raw = value == null ? "" : value.toString().trim();
            if (raw.isEmpty() || !raw.matches("\\d+")) return this;

            lblDay = new JLabel(raw);
            lblDay.setFont(new Font("Calibri", Font.PLAIN, 13));
            lblDay.setForeground(Color.DARK_GRAY);
            lblDay.setBounds(115, 4, 30, 16);
            add(lblDay);

            date = CMonth.atDay(Integer.parseInt(raw));
            java.util.List<Appointment> events = appointmentsMap.getOrDefault(date.toString(), Collections.emptyList());

            int y = 24;
            int shown = 0;
            for (Appointment app : events) {
                if (shown >= 3) {
                    lblMore = new JLabel("  +" + (events.size() - 3) + " more");
                    lblMore.setFont(new Font("Calibri", Font.PLAIN, 11));
                    lblMore.setForeground(Color.DARK_GRAY);
                    lblMore.setBounds(2, y, 142, 14);
                    add(lblMore);
                    break;
                }
                String displayTime = appptTimeExtractor(app.getAppointmentDate());
                lblEv = new JLabel("  " + displayTime + " " + app.getPatientName());
                lblEv.setFont(new Font("Calibri", Font.PLAIN, 11));
                lblEv.setOpaque(true);
                    if ("Urgent".equalsIgnoreCase(app.getStatus())) {
                        lblEv.setBackground(LightRed);
                    } else if ("Completed".equalsIgnoreCase(app.getStatus())
                            || "Complete".equalsIgnoreCase(app.getStatus())) {
                        lblEv.setBackground(Green);
                    } else if ("Cancelled".equalsIgnoreCase(app.getStatus())) {
                        lblEv.setBackground(Color.GRAY);
                    } else {
                        lblEv.setBackground(Blue);
                    }
                lblEv.setForeground(Color.WHITE);
                lblEv.setBounds(2, y, 142, 14);
                add(lblEv);
                y += 18;
                shown++;
            }
            return this;
        }
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}