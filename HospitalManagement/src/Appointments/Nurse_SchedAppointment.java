package Appointments;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Nurse_SchedAppointment extends JPanel{
    
    JPanel pnlMain, pnlPatients, pnlAvail, pnlUrgent, pnlApp, pnlRecent, dvdType, dvdStatus, pnlNote,
                   pnlSched, dvdCal, dvdTable;
    private JLabel lbltitle, lblDT, lblPatients, lblAvail, lblUrgent, lblApp, lblPatientIcon, lblPatient, lblPatID,
                   lblComp, lblType, lblTime, lblRoom, lblStatus, lblStaff, lblDoctor, lblNurse, lblNotes, lblCalendar,
                   lblMonth, lblTDAp;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton btnRecord, btnNote, btnEdit, btnPrev, btnNext, btnToday, btnDay, btnWeek, btnMonth, btnNew, btnEdit2,
                    btnDone, btnView;
    private JTextArea txtNote;
    private JScrollPane scrNote, scrCalendar, srcAppointments;
    private JTable tblCalendar, tblApp;
    
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
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        pnlPatients = new JPanel();
        pnlPatients.setLayout(null);
        pnlPatients.setBounds(300, 30, 250, 40);
        pnlPatients.setBackground(Blue);
        pnlMain.add(pnlPatients);
        
        lblPatients = new JLabel("18 Today's Patients", SwingConstants.CENTER);
        lblPatients.setBounds(0, 0, 250, 40);
        lblPatients.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        
        pnlAvail = new JPanel();
        pnlAvail.setLayout(null);
        pnlAvail.setBounds(560, 30, 250, 40);
        pnlAvail.setBackground(Green);
        pnlMain.add(pnlAvail);
        
        lblAvail = new JLabel("7 Medications Due", SwingConstants.CENTER);
        lblAvail.setBounds(0, 0, 250, 40);
        lblAvail.setFont(new Font("Calibri", Font.BOLD, 18));
        lblAvail.setForeground(Color.WHITE);
        pnlAvail.add(lblAvail);
        
        pnlUrgent = new JPanel();
        pnlUrgent.setLayout(null);
        pnlUrgent.setBounds(820, 30, 250, 40);
        pnlUrgent.setBackground(LightRed);
        pnlMain.add(pnlUrgent);
        
        lblUrgent = new JLabel("4 Critical Patients", SwingConstants.CENTER);
        lblUrgent.setBounds(0, 0, 250, 40);
        lblUrgent.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUrgent.setForeground(Color.WHITE);
        pnlUrgent.add(lblUrgent);
        
        pnlApp = new JPanel();
        pnlApp.setLayout(null);
        pnlApp.setBounds(1080, 30, 250, 40);
        pnlApp.setBackground(Yellow);
        pnlMain.add(pnlApp);
        
        lblApp = new JLabel("9 Care Tasks Pending", SwingConstants.CENTER);
        lblApp.setBounds(0, 0, 250, 40);
        lblApp.setFont(new Font("Calibri", Font.BOLD, 18));
        lblApp.setForeground(Color.WHITE);
        pnlApp.add(lblApp);
        
        pnlRecent = new JPanel();
        pnlRecent.setLayout(null);
        pnlRecent.setBounds(70, 100, 450, 450);
        pnlRecent.setBackground(Color.WHITE);
        pnlMain.add(pnlRecent);
        
        imgPatient = new ImageIcon(getClass().getResource("/resources/PATIENT.PHOTO.SQUARE.png"));
        imgPat = imgPatient.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        lblPatientIcon = new JLabel(new ImageIcon(imgPat));
        lblPatientIcon.setBounds(20, 20, 150, 150);
        pnlRecent.add(lblPatientIcon);
        
        lblPatient = new JLabel("Patient Name: Joshua Garcia");
        lblPatient.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPatient.setBounds(180, 15, 250, 30);
        pnlRecent.add(lblPatient);
        
        lblPatID = new JLabel("Patient ID: P-10234");
        lblPatID.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPatID.setBounds(180, 45, 250, 30);
        pnlRecent.add(lblPatID);
        
        lblComp = new JLabel("Care Need: Chest Pain");
        lblComp.setFont(new Font("Calibri", Font.BOLD, 20));
        lblComp.setBounds(180, 75, 250, 30);
        pnlRecent.add(lblComp);
        
        lblType = new JLabel("Task: Follow-up");
        lblType.setFont(new Font("Calibri", Font.BOLD, 20));
        lblType.setBounds(180, 105, 250, 25);
        pnlRecent.add(lblType);
       
        dvdType = new JPanel();
        dvdType.setBounds(180, 135, 250, 2);
        dvdType.setBackground(neutralGray);
        pnlRecent.add(dvdType);

        lblTime = new JLabel("Time: 09:00 AM");
        lblTime.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblTime.setBounds(30, 150, 250, 25);
        pnlRecent.add(lblTime);
        
        lblRoom = new JLabel("Room: 101");
        lblRoom.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblRoom.setBounds(30, 180, 250, 25);
        pnlRecent.add(lblRoom);
        
        lblStatus = new JLabel("Status: Needs Monitoring");
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
        
        lblDoctor = new JLabel("Doctor: Isabella Ramos");
        lblDoctor.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblDoctor.setBounds(60, 290, 250, 25);
        pnlRecent.add(lblDoctor);
        
        lblNurse = new JLabel("Nurse: Angela Cruz");
        lblNurse.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblNurse.setBounds(60, 320, 250, 25);
        pnlRecent.add(lblNurse);
        
        btnRecord = new JButton("View Charts");
        btnRecord.setFont(new Font("Calibri", Font.BOLD, 16));
        btnRecord.setBounds(60, 370, 150, 35);
        pnlRecent.add(btnRecord);

        btnNote = new JButton("View Care Notes");
        btnNote.setFont(new Font("Calibri", Font.BOLD, 16));
        btnNote.setBounds(250, 370, 150, 35);
        pnlRecent.add(btnNote);
        
        pnlNote = new JPanel();
        pnlNote.setLayout(null);
        pnlNote.setBounds(70, 570, 450, 330);
        pnlNote.setBackground(lightBlue);
        pnlMain.add(pnlNote);
        
        lblNotes = new JLabel("Nursing Notes");
        lblNotes.setBounds(20, 20, 150, 30);
        lblNotes.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlNote.add(lblNotes);
        
        btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEdit.setBounds(335, 15, 90, 35);
        pnlNote.add(btnEdit);
        
        txtNote = new JTextArea();
        txtNote.setText("Patient is experiencing mild chest pain.\nMonitor vitals every 2 hours.");
        txtNote.setFont(new Font("Calibri", Font.PLAIN, 20));
        txtNote.setEditable(false);
        txtNote.setLineWrap(true);
        txtNote.setWrapStyleWord(true);

        scrNote = new JScrollPane(txtNote);
        scrNote.setBounds(25, 65, 400, 240);
        pnlNote.add(scrNote);
        
        pnlSched = new JPanel();
        pnlSched.setLayout(null);
        pnlSched.setBounds(540, 100, 1020, 800);
        pnlSched.setBackground(Color.WHITE);
        pnlMain.add(pnlSched);
        
        lblCalendar = new JLabel("Calendar");
        lblCalendar.setBounds(20, 20, 150, 30);
        lblCalendar.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSched.add(lblCalendar);
        
        dvdCal = new JPanel();
        dvdCal.setBounds(20, 55, 980, 2);
        dvdCal.setBackground(HighBlue);
        pnlSched.add(dvdCal);
        
        btnPrev = new JButton("<");
        btnPrev.setBounds(30, 70, 50, 35);
        btnPrev.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnPrev.setBackground(darkBlue);
        btnPrev.setForeground(Color.WHITE);
        btnPrev.setFocusPainted(false);
        pnlSched.add(btnPrev);
        
        btnNext = new JButton(">");
        btnNext.setBounds(85, 70, 50, 35);
        btnNext.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnNext.setBackground(darkBlue);
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusPainted(false);
        pnlSched.add(btnNext);
        
        btnToday = new JButton("Today");
        btnToday.setBounds(180, 70, 100, 35);
        btnToday.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnToday.setBackground(darkBlue);
        btnToday.setForeground(Color.WHITE);
        btnToday.setFocusPainted(false);
        pnlSched.add(btnToday);
        
        lblMonth = new JLabel("April 2026");
        lblMonth.setBounds(400, 70, 200, 35);
        lblMonth.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSched.add(lblMonth);
        
        btnDay = new JButton("Day");
        btnDay.setBounds(650, 70, 100, 35);
        btnDay.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnDay.setBackground(darkBlue);
        btnDay.setForeground(Color.WHITE);
        btnDay.setFocusPainted(false);
        pnlSched.add(btnDay);
        
        btnWeek = new JButton("Week");
        btnWeek.setBounds(770, 70, 100, 35);
        btnWeek.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnWeek.setBackground(darkBlue);
        btnWeek.setForeground(Color.WHITE);
        btnWeek.setFocusPainted(false);
        pnlSched.add(btnWeek);
        
        btnMonth = new JButton("Month");
        btnMonth.setBounds(890, 70, 100, 35);
        btnMonth.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnMonth.setBackground(darkBlue);
        btnMonth.setForeground(Color.WHITE);
        btnMonth.setFocusPainted(false);
        pnlSched.add(btnMonth);
        
        String [] days = {"Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"};
        String[][] DateNum = {{"", "", "", "1", "2", "3", "4"},
                              {"5", "6", "7", "8", "9", "10", "11"},
                              {"12", "13", "14", "15", "16", "17", "18"},
                              {"19", "20", "21", "22", "23", "24", "25"},
                              {"26", "27", "28", "29", "30", "", ""}};
        
        tblCalendar = new JTable(DateNum, days);
        tblCalendar.setRowHeight(75);
        tblCalendar.setFont(new Font("Calibri", Font.PLAIN, 18));
        tblCalendar.setGridColor(Color.LIGHT_GRAY);
        tblCalendar.setBackground(Color.WHITE);
        tblCalendar.setSelectionBackground(veryLightBlue);
        tblCalendar.setSelectionForeground(Color.WHITE);
        
        scrCalendar = new JScrollPane(tblCalendar);
        scrCalendar.setBounds(30, 125, 960, 420);
        pnlSched.add(scrCalendar);
        
        dvdTable = new JPanel();
        dvdTable.setBounds(20, 560, 980, 2);
        dvdTable.setBackground(HighBlue);
        pnlSched.add(dvdTable);
        
        lblTDAp = new JLabel("Care Schedule for April 15, 2026");
        lblTDAp.setBounds(30, 580, 400, 30);
        lblTDAp.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTDAp.setForeground(Color.BLACK);
        pnlSched.add(lblTDAp);
        
        String[] clmAppointments = {"Time", "Patient ID", "Patient Name", "Care Task", "Status"};

        String[][] rwAppointments = {{"10:30 AM", "P-10025", "Elena Cruz", "Check Vital Signs", "Pending"},
                                    {"12:30 PM", "P-10026", "Grace Delos Santos", "Administer Medicine", "Scheduled"},
                                    {"01:30 PM", "P-10027", "Macayle Santos", "Change Dressing", "In Progress"}};
        
        tblApp = new JTable(rwAppointments, clmAppointments);
        tblApp.setRowHeight(40);
        tblApp.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        srcAppointments = new JScrollPane(tblApp);
        srcAppointments.setBounds(30, 620, 960, 110);
        pnlSched.add(srcAppointments);
        
        btnNew = new JButton("+ Add Care Task");
        btnNew.setBounds(30, 750, 170, 35);
        btnNew.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnNew.setBackground(darkBlue);
        btnNew.setForeground(Color.WHITE);
        btnNew.setFocusPainted(false);
        pnlSched.add(btnNew);
        
        btnEdit2 = new JButton("Edit Task");
        btnEdit2.setBounds(220, 750, 120, 35);
        btnEdit2.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnEdit2.setBackground(darkBlue);
        btnEdit2.setForeground(Color.WHITE);
        btnEdit2.setFocusPainted(false);
        pnlSched.add(btnEdit2);
        
        btnDone = new JButton("Mark Done");
        btnDone.setBounds(630, 750, 120, 35);
        btnDone.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnDone.setBackground(darkBlue);
        btnDone.setForeground(Color.WHITE);
        btnDone.setFocusPainted(false);
        pnlSched.add(btnDone);
        
        btnView = new JButton("View All Tasks");
        btnView.setBounds(770, 750, 220, 35);
        btnView.setFont(new Font("Calibri", Font.PLAIN, 16));
        btnView.setBackground(darkBlue);
        btnView.setForeground(Color.WHITE);
        btnView.setFocusPainted(false);
        pnlSched.add(btnView);
    }
    
}
