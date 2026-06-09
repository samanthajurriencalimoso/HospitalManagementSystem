package Dashboard;

import Appointments.Doctor_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Inventory.RequestPanel_Doctor;
import Patient_Information.PatientInfo_Doctor;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;

public class Doctor_Dashboard extends JPanel{

    private JPanel pnlMain, pnlPat, tabUpdate, tabItem, tabLows, tabMed, tabSup, pnlRecent, pnlSchedule, pnlSummary, pnlSelection;
    private JLabel lblView, lblDashboard, lblDT, lblTItem, lblLStock, lblMed, lblSup, lblACount, lblPrCount, lblPrTitle, lblCTitle, lblCCount,
                   lblPatientIcon, lblPatientName, lblPatientID, lblSchedule, lblStatusAct, lblSummary, lblTasks;
    private JTable tblActivities, tblSummary, tblItems;
    private JTableHeader HActivities, summaryHeader, tblHdr;
    private DefaultTableCellRenderer centerRenderer, center;
    private JScrollPane scrActivities, scrSummary, scrItems;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton btnV, btnN;
    
    public Doctor_Dashboard() {
        setLayout(null);
        
        //Main Panel
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
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        tabItem = createTab("Total Patients", "0", darkBlue);
        tabItem.setBounds(30, 80, 370, 120);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);
        
        tabLows = createTab("Today's Appointment", "0", Yellow);
        tabLows.setBounds(420, 80, 370, 120);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);
        
        tabMed = createTab("Total Prescriptions", "0", Blue);
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
        imgPat = imgPatient.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblPatientIcon = new JLabel(new ImageIcon(imgPat));
        lblPatientIcon.setBounds(130, 30, 150, 150);
        pnlPat.add(lblPatientIcon);
        
        lblPatientName = new JLabel("Patient Name", SwingConstants.CENTER);
        lblPatientName.setFont(new Font("Calibri", Font.BOLD, 30));
        lblPatientName.setBounds(70, 200, 260, 30);
        pnlPat.add(lblPatientName);
        
        // DB should show the Patient ID
        lblPatientID = new JLabel("Patient ID: P ", SwingConstants.CENTER);
        lblPatientID.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblPatientID.setBounds(70, 240, 260, 25);
        pnlPat.add(lblPatientID); 
        
        btnV = new JButton("View Profile");
        btnV.setBounds(60, 300, 150, 40);
        btnV.setFont(new Font("Calibri", Font.BOLD, 14));
        btnV.setFocusPainted(false);
        pnlPat.add(btnV);

        btnN = new JButton("View Notes");
        btnN.setBounds(220, 300, 150, 40);
        btnN.setFont(new Font("Calibri", Font.BOLD, 14));
        btnN.setFocusPainted(false);
        pnlPat.add(btnN);
        
        pnlSchedule = new JPanel();
        pnlSchedule.setLayout(null);
        pnlSchedule.setBounds(480, 220, 1090, 380);
        pnlSchedule.setBackground(Color.WHITE);
        pnlMain.add(pnlSchedule);
        
        lblSchedule = new JLabel("Today's Schedule");
        lblSchedule.setBounds(20, 20, 300, 30);
        lblSchedule.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSchedule.add(lblSchedule);
        
        String[] clmSchedule = {"Time", "Patient Name", "Room", "Reason", "Last Visit", "Status"};
        
        String[][] RwSchedule = {
            {"09:00 AM", "Joshua Garcia", "Room 101", "Routine Checkup", "03/05/2026", "Completed"},
            {"10:30 AM", "Maria Santos", "Room 102", "Follow-up Consultation", "02/20/2026", "Pending"},
            {"01:00 PM", "Daniel Cruz", "Room 103", "Lab Results Review", "01/15/2026", "In Progress"},
            {"03:15 PM", "Angela Reyes", "Room 104", "General Consultation", "04/01/2026", "Scheduled"},
            {"04:45 PM", "Michael Tan", "Room 105", "Prescription Renewal", "03/28/2026", "Completed"},
            {"08:30 AM", "Sophia Lim", "Room 106", "Annual Physical Exam", "04/10/2026", "Scheduled"},
            {"11:15 AM", "Carlos Dela Cruz", "Room 107", "Blood Pressure Check", "03/22/2026", "Completed"},
            {"02:30 PM", "Isabella Ramos", "Room 108", "Diabetes Follow-up", "02/18/2026", "Pending"},
            {"05:00 PM", "John Bautista", "Room 109", "Post-Surgery Check", "03/30/2026", "In Progress"},
            {"06:15 PM", "Emily Flores", "Room 110", "Vaccination", "04/05/2026", "Completed"}
            };
        
        tblActivities = new JTable(RwSchedule, clmSchedule);
        tblActivities.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblActivities.setRowHeight(30);
        tblActivities.setGridColor(Color.LIGHT_GRAY);
        tblActivities.setBackground(Color.WHITE);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblActivities.getColumnCount() - 1; i++) {
            tblActivities.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tblActivities.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
        lblStatusAct = new JLabel(value.toString(), SwingConstants.CENTER);
        lblStatusAct.setOpaque(true);
        lblStatusAct.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        switch (value.toString()) {
        case "In Progress":
            lblStatusAct.setBackground(lightBlue);
            break;
        case "Scheduled":
            lblStatusAct.setBackground(MintGreen);
            break;
        case "Pending":
            lblStatusAct.setBackground(PaleYellow);
            break;
        case "Not Confirmed":
            lblStatusAct.setBackground(Lavender);
            break;
        case "Completed":
            lblStatusAct.setBackground(Teal);
            break;
        case "Urgent":
            lblStatusAct.setBackground(SoftPink);
            lblStatusAct.setFont(new Font("Calibri", Font.BOLD, 16));
            break;
        default:
            lblStatusAct.setBackground(Color.WHITE);
            break;
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
        
        lblView = new JLabel("View All");
        lblView.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblView.setForeground(Color.BLUE);
        lblView.setBounds(1000, 20, 80, 30);
        lblView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblView.addMouseListener(new java.awt.event.MouseAdapter() {
        
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMain.removeAll();
                pnlMain.add(new PatientInfo_Doctor());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSchedule.add(lblView);
        
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

        String[] clmSummary = {"Room Number", "Doctor", "Nurse", "Equipment"};

        String[][] RwSummary = {
            {"Room 101", "Dr. Santos", "Nurse Cruz", "ECG Monitor"},
            {"Room 102", "Dr. Reyes", "Nurse Lopez", "Oxygen Tank"},
            {"Room 103", "Dr. Garcia", "Nurse Mendoza", "Infusion Pump"},
            {"Room 104", "Dr. Ramos", "Nurse Villanueva", "Wheelchair"}
        };

        tblSummary = new JTable(RwSummary, clmSummary);
        tblSummary.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblSummary.setRowHeight(35);
        tblSummary.setGridColor(Color.LIGHT_GRAY);
        tblSummary.setBackground(Color.WHITE);

        summaryHeader = tblSummary.getTableHeader();
        summaryHeader.setBackground(lightBlue);
        summaryHeader.setFont(new Font("Calibri", Font.BOLD, 14));

        center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
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

        String[] clmItems = {"Category", "Item", "Status"};

        String[][] RwItems = {
            {"Medicine", "Paracetamol", "Requested"},
            {"Supplies", "Surgical Masks", "Pending"},
            {"Equipment", "IV Stand", "Delivered"},
            {"Medicine", "Antibiotics", "Pending"},
            {"Supplies", "Gloves", "Requested"}
        };

        tblItems = new JTable(RwItems, clmItems);
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
        
        for (int i = 0; i < 3; i++) {
            tblItems.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        scrItems = new JScrollPane(tblItems);
        scrItems.setBounds(20, 50, 580, 200);
        pnlSelection.add(scrItems);

        setVisible(true);
    }
    
    private JPanel createTab(String title, String value, Color color) {
        tabUpdate = new JPanel();
        tabUpdate.setLayout(null);
        tabUpdate.setBackground(color);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 20, 250, 25);
        tabUpdate.add(lblTitle);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 50, 150, 40);
        tabUpdate.add(lblValue);

        return tabUpdate;
    }
}