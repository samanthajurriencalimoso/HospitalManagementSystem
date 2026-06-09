package Dashboard;

import Appointments.Nurse_SchedAppointment;
import static Color_Palette.ColorPalette.*;
import Inventory.RequestPanel_Nurse;
import Patient_Information.PatientInfo_Nurse;
import Prescriptions.Prescription_Nurse;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;

public class Nurse_Dashboard extends JPanel{

    private JPanel pnlMain, pnlPat, tabUpdate, tabItem, tabLows, tabMed, tabSup, pnlRecent, pnlSchedule, pnlSummary, pnlSelection;
    private JLabel lblView, lblDashboard, lblDT, lblTItem, lblLStock, lblMed, lblSup, lblACount, lblPrCount, lblPrTitle, lblCTitle, lblCCount,
                   lblPatientIcon, lblPatientName, lblPatientID, lblSchedule, lblStatusAct, lblSummary, lblTasks;
    private JTable tblActivities, tblSummary, tblItems;
    private JTableHeader HActivities, summaryHeader, tblHdr;
    private DefaultTableCellRenderer centerRenderer, center;
    private DefaultListModel<String> notif, tasks;
    private JScrollPane scrActivities, scrSummary, scrItems;
    private JList<String> lstTasks;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton btnV, btnN;
    
    public Nurse_Dashboard() {
        setLayout(null);
        
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
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        tabItem = createTab("Patients Assigned", "0", darkBlue);
        tabItem.setBounds(30, 80, 370, 120);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);
        
        tabLows = createTab("Today's Tasks", "0", Yellow);
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
        imgPat = imgPatient.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblPatientIcon = new JLabel(new ImageIcon(imgPat));
        lblPatientIcon.setBounds(130, 30, 150, 150);
        pnlPat.add(lblPatientIcon);
        
        lblPatientName = new JLabel("Joshua Garcia", SwingConstants.CENTER);
        lblPatientName.setFont(new Font("Calibri", Font.BOLD, 30));
        lblPatientName.setBounds(70, 200, 260, 30);
        pnlPat.add(lblPatientName);
        
        lblPatientID = new JLabel("Patient ID: P-10234", SwingConstants.CENTER);
        lblPatientID.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblPatientID.setBounds(70, 240, 260, 25);
        pnlPat.add(lblPatientID); 
        
        btnV = new JButton("View Profile");
        btnV.setBounds(60, 300, 150, 40);
        btnV.setFont(new Font("Calibri", Font.BOLD, 14));
        btnV.setFocusPainted(false);
        btnV.addActionListener(e -> {
            pnlMain.removeAll();
            pnlMain.add(new PatientInfo_Nurse());
            pnlMain.revalidate();
            pnlMain.repaint();
        });
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
        
        lblSchedule = new JLabel("Today's Medication Schedule");
        lblSchedule.setBounds(20, 20, 300, 30);
        lblSchedule.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSchedule.add(lblSchedule);
        
        String[] clmSchedule = {"Time", "Patient Name", "Room", "Medicine", "Dosage", "Status"};
        
        String[][] RwSchedule = {
            {"09:00 AM", "Joshua Garcia", "Room 101", "Paracetamol", "500mg", "Pending"},
            {"10:30 AM", "Maria Santos", "Room 102", "Amoxicillin", "250mg", "Given"},
            {"01:00 PM", "Daniel Cruz", "Room 103", "Insulin", "10 units", "Pending"},
            {"03:15 PM", "Angela Reyes", "Room 104", "Losartan", "50mg", "Given"},
            {"04:45 PM", "Michael Tan", "Room 105", "Ibuprofen", "400mg", "Pending"},
            {"08:30 AM", "Sophia Lim", "Room 106", "Vitamin C", "500mg", "Given"},
            {"11:15 AM", "Carlos Dela Cruz", "Room 107", "Metformin", "500mg", "Pending"},
            {"02:30 PM", "Isabella Ramos", "Room 108", "Omeprazole", "20mg", "Given"}
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
        case "Given":
            lblStatusAct.setBackground(MintGreen);
            break;
        case "Pending":
            lblStatusAct.setBackground(PaleYellow);
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
                pnlMain.add(new Prescription_Nurse());
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
                pnlMain.add(new Nurse_SchedAppointment());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSummary.add(lblView);

        String[] clmSummary = {"Room Number", "Patient Name", "Doctor", "Nurse", "Status"};

        String[][] RwSummary = {
            {"Room 101", "Joshua Garcia", "Dr. Santos", "Nurse Cruz", "Occupied"},
            {"Room 102", "Maria Santos", "Dr. Reyes", "Nurse Lopez", "Occupied"},
            {"Room 103", "Daniel Cruz", "Dr. Garcia", "Nurse Mendoza", "Available"},
            {"Room 104", "Angela Reyes", "Dr. Ramos", "Nurse Villanueva", "Occupied"}
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

        for (int i = 0; i < 5; i++) {
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

        String[] clmItems = {"Item", "Quantity", "Status"};

        String[][] RwItems = {
            {"Surgical Masks", "100 boxes", "Delivered"},
            {"Disposable Gloves", "50 boxes", "Pending"},
            {"IV Fluids", "30 bags", "Pending"},
            {"Alcohol", "20 bottles", "Delivered"}
        };

        tblItems = new JTable(RwItems, clmItems);
        tblItems.setFont(new Font("Calibri", Font.PLAIN, 18));
        tblItems.setRowHeight(35);
        tblItems.setGridColor(Color.LIGHT_GRAY);
        tblItems.setBackground(Color.WHITE);

        tblHdr = tblItems.getTableHeader();
        tblItems.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        tblHdr.setBackground(lightBlue);
        tblItems.getTableHeader().setForeground(Color.BLACK);

        scrItems = new JScrollPane(tblItems);
        scrItems.setBounds(20, 60, 580, 200);
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