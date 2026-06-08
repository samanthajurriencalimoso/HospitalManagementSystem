package Appointments;

import static Color_Palette.ColorPalette.*;
import Patient_Information.PatientInfo_Doctor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;

public class Doctor_SchedAppointment extends JPanel implements ActionListener{
    
    private JPanel pnlMain, tabUpdate, tabItem, tabLows, tabMed, tabSup, pnlRecent, dvdType, dvdStatus, pnlNote,
                   pnlSched, dvdCal, dvdTable, pnlEdit2, pnlNew;
    private JLabel lbltitle, lblDT, lblTitle, lblPatientIcon, lblPatient, lblPatID, lblTItem, lblLStock, lblMed, lblSup,
                   lblComp, lblType, lblTime, lblRoom, lblStatus, lblStaff, lblDoctor, lblNurse, lblNotes, lblCalendar,
                   lblMonth, lblTDAp, lblValue, lblDay, lblMore, lblEv, lblTime2, lblPID, lblPName;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton btnRecord, btnNote, btnEdit, btnPrev, btnNext, btnNew, btnEdit2, btnCancel;
    private JTextArea txtNote;
    private JTextField PID, PName, txtStatus;
    private JScrollPane scrNote, scrCalendar, srcAppointments;
    private JTable tblCalendar, tblApp;
    private JTableHeader tblCHdr;
    private javax.swing.table.DefaultTableModel CModel;
    private YearMonth CMonth = YearMonth.now();
    private Map<LocalDate, java.util.List<Object[]>> appointmentsMap = new HashMap<>();
    private Map<LocalDate, java.util.List<CalendarEvent>> calendarEvents = new HashMap<>();
    private JComboBox<String> cmbTime, cmbType;
    private DefaultTableModel appModel;
    private LocalDate currentSelectedDate = null;
    private DateTimeFormatter formatter;
    private LocalDate FMonth, date, SDate, today, selectedDate;
    private Container par;
    private EventType et;
    private CalendarEvent ev;
    
    public Doctor_SchedAppointment() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lbltitle = new JLabel("Doctor Appointment");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        tabItem = createTab("Today's Patients: ", "0", darkBlue);
        tabItem.setBounds(270, 20, 250, 50);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);

        tabLows = createTab("Review Prescriptions: ", "0", Yellow);
        tabLows.setBounds(540, 20, 250, 50);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);

        tabMed = createTab("Critical Patients: ", "0", LightRed);
        tabMed.setBounds(810, 20, 250, 50);
        pnlMain.add(tabMed);
        lblMed = (JLabel) tabMed.getComponent(1);

        tabSup = createTab("Pending Appointments: ", "0", Blue);
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
        
        lblComp = new JLabel("Diagnosis: ");
        lblComp.setFont(new Font("Calibri", Font.BOLD, 20));
        lblComp.setBounds(180, 75, 250, 30);
        pnlRecent.add(lblComp);
        
        lblType = new JLabel("Type: ");
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
            public boolean isCellEditable(int row, int column) {
                return false;
            }
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
                        if (appointmentsMap.containsKey(selectedDate)) {
                            for (Object[] appt : appointmentsMap.get(selectedDate)) {
                                appModel.addRow(appt);
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
        
        String[] clmAppointments = {"Time","Patient ID","Patient Name","Type","Status"};

        appModel = new DefaultTableModel(clmAppointments, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
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
        btnEdit.addActionListener(this);
        btnPrev.addActionListener(this);
        btnNext.addActionListener(this);
        btnNew.addActionListener(this);
        btnEdit2.addActionListener(this);
        btnCancel.addActionListener(this);
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
        SURGERY    ("Surgery",     orange),
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

    class CalendarEvent {
        String time, patientName, patientId, status;
        EventType type;

        CalendarEvent(String time, String patientId, String patientName, EventType type, String status) {
            this.time = time;
            this.patientId = patientId;
            this.patientName = patientName;
            this.type = type;
            this.status = status;
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
                    date = CMonth.atDay(CurrentD);
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

            String pid   = (String) tblApp.getValueAt(row, 1);
            String pname = (String) tblApp.getValueAt(row, 2);

            if (pid == null || pid.trim().isEmpty() || pname == null || pname.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selected appointment has incomplete patient data.");
                return;
            }
            
            par = this.getParent();
            par.removeAll();
            par.add(new PatientInfo_Doctor());
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
            boolean hasPrescription = false;
            if (hasPrescription) {
                String prescriptionDetails = "Type: ...\nDrug: ...\nDuration: ...\nDosage: ...";
                JOptionPane.showMessageDialog(this, prescriptionDetails,"Patient Prescription", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No prescription found for this patient.");
            }
        } else if (ae.getSource() == btnEdit) {
            txtNote.setEditable(true);
            JOptionPane.showMessageDialog(this, "You can now edit the notes.");
        } else if (ae.getSource() == btnNew) {
            int row = tblCalendar.getSelectedRow();
            int col = tblCalendar.getSelectedColumn();
            if (row == -1 || col == -1) {
                JOptionPane.showMessageDialog(this, "Please select a date first.");
                return;
            }

            String CValue = (String) tblCalendar.getValueAt(row, col);
            if (CValue == null || CValue.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a valid date.");
                return;
            }

            String dayText = CValue.trim();
            if (!dayText.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Please select a valid date.");
                return;
            }

            SDate = CMonth.atDay(Integer.parseInt(dayText));
            today = LocalDate.now();
            if (SDate.isBefore(today)) {
                JOptionPane.showMessageDialog(this, "Cannot create appointment for past dates.");
                return;
            }

            String[] workingHours = {"08:00 AM","09:00 AM","10:00 AM","11:00 AM",
                                     "01:00 PM","02:00 PM","03:00 PM","04:00 PM","05:00 PM"};
            cmbTime  = new JComboBox<>(workingHours);
            PID = new JTextField("P-");
            PName = new JTextField();
            cmbType = new JComboBox<>(new String[]{"Appointment", "Follow-up", "Surgery", "Emergency"});
            txtStatus = new JTextField("Needs Admin Approval");
            txtStatus.setEditable(false);

            pnlNew = new JPanel();
            pnlNew.setLayout(null);
            pnlNew.setPreferredSize(new Dimension(360, 220));

            lblTime2 = new JLabel("Time:");
            lblTime2.setBounds(10, 10, 110, 28);
            cmbTime.setBounds(130, 10, 210, 28);
            pnlNew.add(lblTime2);
            pnlNew.add(cmbTime);

            lblPID = new JLabel("Patient ID:");
            lblPID.setBounds(10, 48, 110, 28);
            PID.setBounds(130, 48, 210, 28);
            pnlNew.add(lblPID);
            pnlNew.add(PID);

            lblPName = new JLabel("Patient Name:");
            lblPName.setBounds(10, 86, 110, 28);
            PName.setBounds(130, 86, 210, 28);
            pnlNew.add(lblPName);
            pnlNew.add(PName);

            lblType = new JLabel("Type:");
            lblType.setBounds(10, 124, 110, 28);
            cmbType.setBounds(130, 124, 210, 28);
            pnlNew.add(lblType);
            pnlNew.add(cmbType);

            JLabel lStatus = new JLabel("Status:");
            lStatus.setBounds(10, 162, 110, 28);
            txtStatus.setBounds(130, 162, 210, 28);
            pnlNew.add(lStatus);
            pnlNew.add(txtStatus);
            
            int option = JOptionPane.showConfirmDialog(this, pnlNew, "New Appointment for " + SDate,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String patientID   = PID.getText().trim();
                String patientName = PName.getText().trim();
                String time        = (String) cmbTime.getSelectedItem();
                String status      = "Needs Admin Approval";

            if (patientID.isEmpty() || patientID.equals("P-") || patientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled up!", "Incomplete Fields", JOptionPane.WARNING_MESSAGE);
                return;
            }
                
            if (SDate.equals(today)) {
                java.time.LocalTime now = java.time.LocalTime.now();
                String selectedTimeStr = (String) cmbTime.getSelectedItem();

                java.time.format.DateTimeFormatter timeFmt = java.time.format.DateTimeFormatter.ofPattern("hh:mm a");
                java.time.LocalTime selectedTime = java.time.LocalTime.parse(selectedTimeStr, timeFmt);

                if (selectedTime.isBefore(now)) {
                    JOptionPane.showMessageDialog(this, "Cannot book " + selectedTimeStr + " — that time has already passed today.");
                    return;
                }
            }
                   
            if (patientID.length() != 7 || !patientID.matches("P-\\d{5}")) {
                JOptionPane.showMessageDialog(this, "Patient ID must be P- followed by exactly 5 numbers.");
                return;
            }
            if (patientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient Name cannot be empty.");
                return;
            }
            if (!patientName.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this, "Patient Name must contain letters only.");
                return;
            }    

                et = EventType.fromLabel((String) cmbType.getSelectedItem());
                ev = new CalendarEvent(time, patientID, patientName, et, status);
                calendarEvents.computeIfAbsent(SDate, k -> new ArrayList<>()).add(ev);

                Object[] newAppt = { time, patientID, patientName, et.label, status };
                appointmentsMap.computeIfAbsent(SDate, k -> new ArrayList<>()).add(newAppt);
                appModel.addRow(newAppt);
                renderMonthCalendar();
            }
        } else if (ae.getSource() == btnEdit2) {
            int row = tblApp.getSelectedRow();
            if (row != -1) {

                String pid   = (String) tblApp.getValueAt(row, 1);
                String pname = (String) tblApp.getValueAt(row, 2);

                String[] workingHours = {"08:00 AM","09:00 AM","10:00 AM","11:00 AM",
                                         "01:00 PM","02:00 PM","03:00 PM","04:00 PM","05:00 PM"};
                cmbTime = new JComboBox<>(workingHours);
                cmbTime.setSelectedItem(tblApp.getValueAt(row, 0));

                PID = new JTextField(pid);
                PID.setEditable(false);
                PID.setBackground(Color.LIGHT_GRAY);

                PName = new JTextField(pname);
                PName.setEditable(false);
                PName.setBackground(Color.LIGHT_GRAY);

                cmbType = new JComboBox<>(new String[]{"Appointment", "Follow-up", "Surgery", "Emergency"});
                cmbType.setSelectedItem(tblApp.getValueAt(row, 3));

                txtStatus = new JTextField((String) tblApp.getValueAt(row, 4));
                txtStatus.setEditable(false);
                txtStatus.setBackground(LightGray);

                pnlEdit2 = new JPanel();
                pnlEdit2.setLayout(null);
                pnlEdit2.setPreferredSize(new Dimension(360, 220));

                lblTime2 = new JLabel("Time:");
                lblTime2.setBounds(10, 10, 110, 28);
                cmbTime.setBounds(130, 10, 210, 28);
                pnlEdit2.add(lblTime2);
                pnlEdit2.add(cmbTime);

                lblPID = new JLabel("Patient ID:");
                lblPID.setBounds(10, 48, 110, 28);
                PID.setBounds(130, 48, 210, 28);
                pnlEdit2.add(lblPID);
                pnlEdit2.add(PID);

                lblPName = new JLabel("Patient Name:");
                lblPName.setBounds(10, 86, 110, 28);
                PName.setBounds(130, 86, 210, 28);
                pnlEdit2.add(lblPName);
                pnlEdit2.add(PName);

                lblType = new JLabel("Type:");
                lblType.setBounds(10, 124, 110, 28);
                cmbType.setBounds(130, 124, 210, 28);
                pnlEdit2.add(lblType);
                pnlEdit2.add(cmbType);

                lblStatus = new JLabel("Status:");
                lblStatus.setBounds(10, 162, 110, 28);
                txtStatus.setBounds(130, 162, 210, 28);
                pnlEdit2.add(lblStatus);
                pnlEdit2.add(txtStatus);

                int option = JOptionPane.showConfirmDialog(this, pnlEdit2,"Edit Appointment", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String selectedTime = (String) cmbTime.getSelectedItem();
                    String selectedType = (String) cmbType.getSelectedItem();

                    if (selectedTime == null || selectedType == null) {
                        JOptionPane.showMessageDialog(this, "All fields must be filled.");
                        return;
                    }

                    tblApp.setValueAt(selectedTime, row, 0);
                    tblApp.setValueAt(selectedType, row, 3);

                    if (currentSelectedDate != null) {
                        java.util.List<Object[]> appts = appointmentsMap.get(currentSelectedDate);
                        if (appts != null && row < appts.size()) {
                            appts.get(row)[0] = selectedTime;
                            appts.get(row)[3] = selectedType;
                        }
                    }

                    if (currentSelectedDate != null) {
                        java.util.List<CalendarEvent> evs = calendarEvents.get(currentSelectedDate);
                        if (evs != null) {
                            for (CalendarEvent ev : evs) {
                                if (ev.patientId.equals(pid) && ev.patientName.equals(pname)) {
                                    ev.time = selectedTime;
                                    ev.type = EventType.fromLabel(selectedType);
                                    break;
                                }
                            }
                        }
                    }

                    renderMonthCalendar();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an appointment to edit.");
            }
        } else if (ae.getSource() == btnCancel) {
            int row = tblApp.getSelectedRow();
            if (row != -1) {
                String pid   = (String) tblApp.getValueAt(row, 1);
                String pname = (String) tblApp.getValueAt(row, 2);

                appModel.removeRow(row);

                if (currentSelectedDate != null) {
                    java.util.List<CalendarEvent> evs = calendarEvents.get(currentSelectedDate);
                    if (evs != null) {
                        evs.removeIf(ev -> ev.patientId.equals(pid) && ev.patientName.equals(pname));
                    }
                    java.util.List<Object[]> appts = appointmentsMap.get(currentSelectedDate);
                    if (appts != null) {
                        appts.removeIf(a -> a[1].equals(pid) && a[2].equals(pname));
                    }
                }
                renderMonthCalendar();
                JOptionPane.showMessageDialog(this, "Appointment removed.");
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
            if (raw.isEmpty()) return this;
            if (!raw.matches("\\d+")) return this;

            lblDay = new JLabel(raw);
            lblDay.setFont(new Font("Calibri", Font.PLAIN, 13));
            lblDay.setForeground(Color.DARK_GRAY);
            lblDay.setBounds(115, 4, 30, 16);
            add(lblDay);

            date = CMonth.atDay(Integer.parseInt(raw));
            java.util.List<CalendarEvent> events = calendarEvents.getOrDefault(date, Collections.emptyList());

            int y = 24;
            int shown = 0;
            for (CalendarEvent ev : events) {
                if (shown >= 3) {
                    lblMore = new JLabel("  +" + (events.size() - 3) + " more");
                    lblMore.setFont(new Font("Calibri", Font.PLAIN, 11));
                    lblMore.setForeground(Color.DARK_GRAY);
                    lblMore.setBounds(2, y, 142, 14);
                    add(lblMore);
                    break;
                }
                lblEv = new JLabel("  " + ev.time + " " + ev.patientName);
                lblEv.setFont(new Font("Calibri", Font.PLAIN, 11));
                lblEv.setOpaque(true);
                lblEv.setBackground(ev.type.color);
                lblEv.setForeground(Color.WHITE);
                lblEv.setBounds(2, y, 142, 14);
                add(lblEv);
                y += 18;
                shown++;
            }
            return this;
        }
    }
}
