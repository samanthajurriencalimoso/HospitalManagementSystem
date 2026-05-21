package Dashboard;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;

public class Admin_Dashboard extends JPanel{
    
    private JPanel pnlMain, pnlPatients, pnlAppointment, pnlPrescription, pnlBeds, pnlSupplies, pnlEquipment, pnlStock, pnlActivity, pnlSummary;
    private JLabel lblOverview, lblDT, lblPatients, lblPCount, lblATitle, lblACount, lblPrTitle, lblPrCount, lblBTitle, lblBCount, lblMonth,
                   lblSupplies, lblSuppliesCount, lblEquipment, lblEquipCount, lblStock, lblStockCount, lblActivity, lblFilter, lblSummary;
    private JTable tblCalendar, tblActivities, tblSummary;
    private JTableHeader HActivities;
    private JScrollPane scrCalendar, scrActivities, scrSummary;
    private DefaultTableCellRenderer RwLeft, RwRight;
    JComboBox<String> cmbActivity;
    
    public Admin_Dashboard() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lblOverview = new JLabel("Hospital Overview");
        lblOverview.setBounds(50, 45, 300, 30);
        lblOverview.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlMain.add(lblOverview);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        pnlPatients = new JPanel();
        pnlPatients.setLayout(null);
        pnlPatients.setBounds(70, 100, 280, 120);
        pnlPatients.setBackground(TealGreen);
        pnlMain.add(pnlPatients);
        
        lblPatients = new JLabel("Total Patients");
        lblPatients.setBounds(15, 15, 200, 20);
        lblPatients.setFont(new Font("Calibri", Font.BOLD, 22));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        
        lblPCount = new JLabel("150");
        lblPCount.setBounds(190, 65, 200, 40);
        lblPCount.setForeground(Color.WHITE);
        lblPCount.setFont(new Font("Calibri", Font.BOLD, 32));
        pnlPatients.add(lblPCount);
        
        pnlAppointment = new JPanel();
        pnlAppointment.setLayout(null);
        pnlAppointment.setBounds(370, 100, 280, 120);
        pnlAppointment.setBackground(Blue);
        pnlMain.add(pnlAppointment);

        lblATitle = new JLabel("Today's Appointments");
        lblATitle.setBounds(15, 15, 200, 20);
        lblATitle.setForeground(Color.WHITE);
        lblATitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlAppointment.add(lblATitle);

        lblACount = new JLabel("18");
        lblACount.setBounds(190,65, 200, 40);
        lblACount.setForeground(Color.WHITE);
        lblACount.setFont(new Font("Calibri", Font.BOLD, 32));
        pnlAppointment.add(lblACount);
        
        pnlPrescription = new JPanel();
        pnlPrescription.setLayout(null);
        pnlPrescription.setBounds(670, 100, 220, 120);
        pnlPrescription.setBackground(orange);
        pnlMain.add(pnlPrescription);

        lblPrTitle = new JLabel("Active Prescriptions");
        lblPrTitle.setBounds(15, 15, 200, 20);
        lblPrTitle.setForeground(Color.WHITE);
        lblPrTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        pnlPrescription.add(lblPrTitle);

        lblPrCount = new JLabel("96");
        lblPrCount.setBounds(160,65, 200, 40);
        lblPrCount.setForeground(Color.WHITE);
        lblPrCount.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlPrescription.add(lblPrCount);
        
        pnlBeds = new JPanel();
        pnlBeds.setLayout(null);
        pnlBeds.setBounds(910, 100, 220, 120);
        pnlBeds.setBackground(LightRed);
        pnlMain.add(pnlBeds);

        lblBTitle = new JLabel("Available Beds");
        lblBTitle.setBounds(15, 15, 200, 20);
        lblBTitle.setForeground(Color.WHITE);
        lblBTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        pnlBeds.add(lblBTitle);

        lblBCount = new JLabel("14");
        lblBCount.setBounds(160, 65, 200, 40);
        lblBCount.setForeground(Color.WHITE);
        lblBCount.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlBeds.add(lblBCount);
        
        lblMonth = new JLabel("May 2026", SwingConstants.CENTER);
        lblMonth.setFont(new Font("Calibri", Font.BOLD, 24));
        lblMonth.setBounds(1170, 100, 420, 50);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(darkBlue);
        lblMonth.setForeground(Color.WHITE);
        pnlMain.add(lblMonth);
        
        String [] days = {"Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[][] DateNum = {{"", "", "", "", "1", "2", "3"},
                              {"4", "5", "6", "7", "8", "9", "10"},
                              {"11", "12", "13", "14", "15", "16", "17"},
                              {"18", "19", "20", "21", "22", "23", "24"},
                              {"25", "26", "27", "28", "29", "30", "31"}};
        
        tblCalendar = new JTable(DateNum, days);
        tblCalendar.setRowHeight(60);
        tblCalendar.setFont(new Font("Calibri", Font.PLAIN, 18));
        tblCalendar.setGridColor(Color.LIGHT_GRAY);
        tblCalendar.setBackground(Color.WHITE);
        tblCalendar.setSelectionBackground(SBlue);
        tblCalendar.setSelectionForeground(Color.WHITE);
        
        scrCalendar = new JScrollPane(tblCalendar);
        scrCalendar.setBounds(1170, 150, 420, 320);
        pnlMain.add(scrCalendar);
        
        pnlSupplies = new JPanel();
        pnlSupplies.setLayout(null);
        pnlSupplies.setBounds(70, 250, 350, 130);
        pnlSupplies.setBackground(Color.WHITE);
        pnlSupplies.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlMain.add(pnlSupplies);

        lblSupplies = new JLabel("Medical Supplies");
        lblSupplies.setBounds(20, 20, 200, 25);
        lblSupplies.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlSupplies.add(lblSupplies);

        lblSuppliesCount = new JLabel("120 Items");
        lblSuppliesCount.setBounds(200, 70, 200, 30);
        lblSuppliesCount.setFont(new Font("Calibri", Font.BOLD, 22));
        pnlSupplies.add(lblSuppliesCount);
        
        pnlEquipment = new JPanel();
        pnlEquipment.setLayout(null);
        pnlEquipment.setBounds(440, 250, 320, 130);
        pnlEquipment.setBackground(Color.WHITE);
        pnlEquipment.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlMain.add(pnlEquipment);
        
        lblEquipment = new JLabel("Equipment");
        lblEquipment.setBounds(20, 20, 200, 25);
        lblEquipment.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlEquipment.add(lblEquipment);

        lblEquipCount = new JLabel("45 Units");
        lblEquipCount.setBounds(200, 70, 200, 30);
        lblEquipCount.setFont(new Font("Calibri", Font.BOLD, 22));
        pnlEquipment.add(lblEquipCount);
        
        pnlStock = new JPanel();
        pnlStock.setLayout(null);
        pnlStock.setBounds(780, 250, 350, 130);
        pnlStock.setBackground(Color.WHITE);
        pnlStock.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlMain.add(pnlStock);
        
        lblStock = new JLabel("Equipment");
        lblStock.setBounds(20, 20, 200, 25);
        lblStock.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlStock.add(lblStock);

        lblStockCount = new JLabel("5 Alerts");
        lblStockCount.setBounds(230, 70, 200, 30);
        lblStockCount.setFont(new Font("Calibri", Font.BOLD, 22));
        pnlStock.add(lblStockCount);
        
        pnlActivity = new JPanel();
        pnlActivity.setLayout(null);
        pnlActivity.setBounds(70, 400, 1060, 500);
        pnlActivity.setBackground(Color.WHITE);
        pnlMain.add(pnlActivity);
        
        lblActivity = new JLabel("Recent Activities");
        lblActivity.setBounds(20, 20, 300, 30);
        lblActivity.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlActivity.add(lblActivity);
        
        lblFilter = new JLabel("Activity: ");
        lblFilter.setFont(new Font("Calibri", Font.BOLD, 22));
        lblFilter.setBounds(760, 20, 80, 30);
        pnlActivity.add(lblFilter);
        
        String[] activity = {"All", "System Updates", "Inventory", "Staff Management", "System Activity"};
        cmbActivity = new JComboBox<>(activity);
        cmbActivity.setFont(new Font("Calibri", Font.PLAIN, 16));
        cmbActivity.setBounds(850, 20, 190, 30);
        pnlActivity.add(cmbActivity);
        
        String[] clmActivities = {"Activity", "Date"};
        
        String[][] RwActivities = {
            {"System updated to version 2.2.1", "04/25/2026"},
            {"Security patch installed successfully", "04/25/2026"},
            {"Automated backup completed", "04/25/2026"},
            {"Database optimization executed", "04/24/2026"},
            {"Server restart completed", "04/24/2026"},
            {"Inventory updated: Added 100 surgical masks", "04/25/2026"},
            {"Inventory alert: IV fluids running low", "04/25/2026"},
            {"Removed expired medicines from stock", "04/24/2026"},
            {"Pharmacy stock replenished: Paracetamol", "04/24/2026"},
            {"Inventory audit completed", "04/23/2026"},
            {"New staff added: Nurse Maria Lopez", "04/25/2026"},
            {"Updated role: Dr. Santos promoted to Senior Physician", "04/25/2026"},
            {"Staff schedule updated for Week 17", "04/24/2026"}
            };
        
        tblActivities = new JTable(RwActivities, clmActivities);
        tblActivities.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblActivities.setRowHeight(30);
        tblActivities.setGridColor(Color.LIGHT_GRAY);
        tblActivities.setBackground(Color.WHITE);
        
        HActivities = tblActivities.getTableHeader();
        HActivities.setFont(new Font("Calibri", Font.BOLD, 14));
        HActivities.setForeground(Color.BLACK);
        
        scrActivities = new JScrollPane(tblActivities);
        scrActivities.setBounds(20, 70, 1020, 400);
        pnlActivity.add(scrActivities);
        
        pnlSummary = new JPanel();
        pnlSummary.setLayout(null);
        pnlSummary.setBounds(1170, 500, 420, 400);
        pnlSummary.setBackground(Color.WHITE);
        pnlMain.add(pnlSummary);
        
        lblSummary = new JLabel("Today's Summary");
        lblSummary.setBounds(20, 20, 300, 30);
        lblSummary.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSummary.add(lblSummary);
        
        String[] clmSummary = {"", ""};
        
        String[][] RwSummary = {
            {"Patients Today", "25"},
            {"Doctors on Duty", "7"},
            {"Emergency Cases", "6"},
            {"Appointments", "18"},
            {"Admissions", "6"},
            {"Discharges", "4"},
            {"Waiting Patients", "6"}
            };
        
        tblSummary = new JTable(RwSummary, clmSummary);
        tblSummary.setFont(new Font("Calibri", Font.PLAIN, 18));
        tblSummary.setRowHeight(45);
        tblSummary.setGridColor(Color.LIGHT_GRAY);
        tblSummary.setBackground(Color.WHITE);
        
        tblSummary.setShowGrid(false);
        tblSummary.setIntercellSpacing(new Dimension(0,0));
        tblSummary.setTableHeader(null);
        
        RwLeft = new DefaultTableCellRenderer();
        RwLeft.setHorizontalAlignment(SwingConstants.LEFT);
        
        RwRight = new DefaultTableCellRenderer();
        RwRight.setHorizontalAlignment(SwingConstants.RIGHT);
        RwRight.setFont(new Font("Calibri", Font.BOLD, 22));
        
        tblSummary.getColumnModel().getColumn(0).setCellRenderer(RwLeft);
        tblSummary.getColumnModel().getColumn(1).setCellRenderer(RwRight);
        
        scrSummary = new JScrollPane(tblSummary);
        scrSummary.setBounds(20, 70, 380, 280);
        scrSummary.setBorder(BorderFactory.createEmptyBorder());
        pnlSummary.add(scrSummary);
        
        setVisible(true);
    }
}
