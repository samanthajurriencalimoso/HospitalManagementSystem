package Dashboard;

import static Color_Palette.ColorPalette.*;
import Database.UserManagementSQL;
import Database.PatientManagementSQL;
import Database.AppointmentSQL;
import Database.LogisticsSQL;
import Models.Employee;
import Models.Patient;
import Models.Appointment;
import Models.LogisticsOrder;
import Inventory.InventoryPanel;
import Inventory.UserManagementPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class Admin_Dashboard extends JPanel {
    
    private JPanel pnlMain, tabItem, pnlActivity, pnlSummary, tabLows, tabValue, tabSup, tabEq, tabMed;
    private JLabel lblOverview, lblDT, lblMonth, lblActivity, lblSummary, lblTItem, lblLStock, lblMed, lblSup, lblEq, lblActive;
    private JTable tblCalendar, tblActivities, tblSummary;
    private JTableHeader HActivities, tblCHdr;
    private JScrollPane scrCalendar, scrActivities, scrSummary;
    private JButton btnPrev, btnNext;
    private DefaultTableModel CModel, activitiesModel, staffModel;
    private YearMonth CMonth = YearMonth.now();
    
    public Admin_Dashboard() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lblOverview = new JLabel("Hospital Overview");
        lblOverview.setBounds(30, 20, 400, 40);
        lblOverview.setFont(new Font("Calibri", Font.BOLD, 24));
        lblOverview.setForeground(Color.BLACK);
        pnlMain.add(lblOverview);
        
        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();
        
        // Metrics Badges
        tabItem = createTab("Total Patients", "0", darkBlue);
        tabItem.setBounds(30, 80, 350, 120);
        pnlMain.add(tabItem);
        lblTItem = (JLabel) tabItem.getComponent(1);
        
        tabLows = createTab("Today's Appointments", "0", Yellow);
        tabLows.setBounds(400, 80, 350, 120);
        pnlMain.add(tabLows);
        lblLStock = (JLabel) tabLows.getComponent(1);
        
        tabMed = createTab("Critical Patients", "0", LightRed);
        tabMed.setBounds(770, 80, 350, 120);
        pnlMain.add(tabMed);
        lblMed = (JLabel) tabMed.getComponent(1);
        
        tabSup = createTab("Total number of Doctors", "0", Blue);
        tabSup.setBounds(30, 220, 350, 120);
        pnlMain.add(tabSup);
        lblSup = (JLabel) tabSup.getComponent(1);
        
        tabEq = createTab("Total number of Nurses", "0", orange);
        tabEq.setBounds(400, 220, 350, 120);
        pnlMain.add(tabEq);
        lblEq = (JLabel) tabEq.getComponent(1);
        
        tabValue = createTab("Pending Deliveries", "0", Green);
        tabValue.setBounds(770, 220, 350, 120);
        pnlMain.add(tabValue);
        lblActive = (JLabel) tabValue.getComponent(1);
        
        btnPrev = new JButton("<");
        btnPrev.setBounds(1140, 80, 50, 50);
        btnPrev.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPrev.setBackground(darkBlue);
        btnPrev.setForeground(Color.WHITE);
        btnPrev.setFocusPainted(false);
        btnPrev.addActionListener(e -> {
            CMonth = CMonth.minusMonths(1);
            refreshCalendar();
        });
        pnlMain.add(btnPrev);
                
        lblMonth = new JLabel("", SwingConstants.CENTER);
        lblMonth.setFont(new Font("Calibri", Font.BOLD, 24));
        lblMonth.setBounds(1190, 80, 350, 50);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(darkBlue);
        lblMonth.setForeground(Color.WHITE);
        pnlMain.add(lblMonth);
        
        btnNext = new JButton(">");
        btnNext.setBounds(1540, 80, 50, 50);
        btnNext.setFont(new Font("Calibri", Font.BOLD, 18));
        btnNext.setBackground(darkBlue);
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusPainted(false);
        btnNext.addActionListener(e -> {
            CMonth = CMonth.plusMonths(1);
            refreshCalendar();
        });
        pnlMain.add(btnNext);
        
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        CModel = new DefaultTableModel(days, 0) {
             @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        
        tblCalendar = new JTable(CModel);
        tblCalendar.setRowHeight(41);
        tblCalendar.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblCalendar.setGridColor(Color.LIGHT_GRAY);
        tblCalendar.setBackground(Color.WHITE);
        tblCalendar.setCellSelectionEnabled(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tblCalendar.setFillsViewportHeight(true);
        
        tblCalendar.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel lblC = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                lblC.setHorizontalAlignment(SwingConstants.CENTER);
                lblC.setBackground(Color.WHITE);
                lblC.setForeground(Color.BLACK);

                if (value != null && !value.toString().trim().isEmpty()) {
                    try {
                        int day = Integer.parseInt(value.toString().trim());
                        LocalDate date = CMonth.atDay(day);
                        if (date.equals(LocalDate.now())) {
                            lblC.setBackground(darkBlue);
                            lblC.setForeground(Color.WHITE);
                            lblC.setFont(new Font("Calibri", Font.BOLD, 16));
                        }
                    } catch (Exception ignored) {}
                }
                if (isSelected) {
                    lblC.setBackground(SBlue);
                    lblC.setForeground(Color.WHITE);
                }
                return lblC;
            }
        });
        
        tblCalendar.getTableHeader().setBackground(lightBlue);
        tblCalendar.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tblCalendar.getTableHeader().setForeground(Color.BLACK);
        
        tblCalendar.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int r = tblCalendar.getSelectedRow();
                int c = tblCalendar.getSelectedColumn();
                if (r != -1 && c != -1) {
                    Object val = tblCalendar.getValueAt(r, c);
                    if (val != null && !val.toString().isEmpty()) {
                        filterDashboardByDate(val.toString());
                    }
                }
            }
        });
        
        scrCalendar = new JScrollPane(tblCalendar);
        scrCalendar.setBounds(1140, 130, 450, 300);
        
        scrCalendar.getViewport().setBackground(Color.WHITE);
        scrCalendar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pnlMain.add(scrCalendar);
        refreshCalendar();
        
        pnlActivity = new JPanel();
        pnlActivity.setLayout(null);
        pnlActivity.setBounds(30, 360, 1090, 550);
        pnlActivity.setBackground(Color.WHITE);
        pnlMain.add(pnlActivity);
        
        lblActivity = new JLabel("Live Logistics Tracking");
        lblActivity.setBounds(20, 20, 300, 30);
        lblActivity.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlActivity.add(lblActivity);
        
        String[] clmActivities = {"Order ID", "Item Asset", "Cost Amount", "Status"};
        
        activitiesModel = new DefaultTableModel(clmActivities, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblActivities = new JTable(activitiesModel);
        tblActivities.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblActivities.setRowHeight(35);
        tblActivities.setGridColor(Color.LIGHT_GRAY);
        tblActivities.setBackground(Color.WHITE);
        
        HActivities = tblActivities.getTableHeader();
        HActivities.setBackground(lightBlue);
        HActivities.setFont(new Font("Calibri", Font.BOLD, 14));
        HActivities.setForeground(Color.BLACK);

        tblActivities.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int r, int c) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, r, c);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setFont(new Font("Calibri", Font.BOLD, 15));
                if (value != null) {
                    String state = value.toString();
                    if (state.equalsIgnoreCase("Delivered")) {
                        lbl.setForeground(SeaGreen);
                    } else if (state.equalsIgnoreCase("Pending")) {
                        lbl.setForeground(Color.ORANGE);
                    } else {
                        lbl.setForeground(Color.DARK_GRAY);
                    }
                }
                return lbl;
            }
        });
        
        scrActivities = new JScrollPane(tblActivities);
        scrActivities.setBounds(20, 70, 1050, 460);
        pnlActivity.add(scrActivities);
        
        JLabel lblViewInventory = new JLabel("View All");
        lblViewInventory.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblViewInventory.setForeground(Color.BLUE);
        lblViewInventory.setBounds(1000, 20, 80, 30);
        lblViewInventory.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblViewInventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                pnlMain.removeAll();
                pnlMain.add(new InventoryPanel());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlActivity.add(lblViewInventory);
        
        // Staff Section
        pnlSummary = new JPanel();
        pnlSummary.setLayout(null);
        pnlSummary.setBounds(1140, 450, 450, 460);
        pnlSummary.setBackground(Color.WHITE);
        pnlMain.add(pnlSummary);

        lblSummary = new JLabel("Staff Status");
        lblSummary.setBounds(20, 20, 300, 30);
        lblSummary.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSummary.add(lblSummary);
        
        JLabel lblViewStaff = new JLabel("View All");
        lblViewStaff.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblViewStaff.setForeground(Color.BLUE);
        lblViewStaff.setBounds(360, 20, 80, 30);
        lblViewStaff.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblViewStaff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                pnlMain.removeAll();
                pnlMain.add(new UserManagementPanel());
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });
        pnlSummary.add(lblViewStaff);

        String[] clmStaff = {"Role", "Name", "Status"};

        staffModel = new DefaultTableModel(clmStaff, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblSummary = new JTable(staffModel);
        tblSummary.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblSummary.setRowHeight(40);
        tblSummary.setGridColor(Color.LIGHT_GRAY);
        tblSummary.setBackground(Color.WHITE);
        
        tblCHdr = tblSummary.getTableHeader();
        tblCHdr.setBackground(lightBlue);
        tblCHdr.setFont(new Font("Calibri", Font.BOLD, 16));
        tblCHdr.setForeground(Color.BLACK);

        DefaultTableCellRenderer rwLeft = new DefaultTableCellRenderer();
        rwLeft.setHorizontalAlignment(SwingConstants.LEFT);
        tblSummary.getColumnModel().getColumn(0).setCellRenderer(rwLeft);
        tblSummary.getColumnModel().getColumn(1).setCellRenderer(rwLeft);

        tblSummary.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setFont(new Font("Calibri", Font.BOLD, 16));
                
                if (value != null) {
                    String val = value.toString();
                    if (val.equalsIgnoreCase("Active")) {
                        cell.setForeground(new Color(0x2E8B57));
                    } else if (val.equalsIgnoreCase("Inactive")) {
                        cell.setForeground(Color.RED);
                    } else if (val.equalsIgnoreCase("On Leave")) {
                        cell.setForeground(Color.ORANGE);
                    } else {
                        cell.setForeground(Color.GRAY);
                    }
                }
                return cell;
            }
        });

        scrSummary = new JScrollPane(tblSummary);
        scrSummary.setBounds(20, 70, 410, 370);
        scrSummary.setBorder(BorderFactory.createEmptyBorder());
        pnlSummary.add(scrSummary);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                loadDashboardStats();
            }
        });

        loadDashboardStats();
        setVisible(true);
    }
    
    private JPanel createTab(String title, String value, Color color) {
        JPanel tabContainer = new JPanel();
        tabContainer.setLayout(null);
        tabContainer.setBackground(color);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 20, 310, 25);
        tabContainer.add(titleLabel);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Calibri", Font.BOLD, 32));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setBounds(20, 55, 310, 40);
        tabContainer.add(valueLabel);
        
        return tabContainer;
    }
    
    private void loadDashboardStats() {
        int docCount = 0;
        int nurseCount = 0;
        int totalPatients = 0;
        int criticalPatients = 0;
        int todayAppointments = 0;
        int pendingDeliveries = 0;

        staffModel.setRowCount(0);
        activitiesModel.setRowCount(0);

        try {
            List<Employee> liveEmployees = UserManagementSQL.getAllEmployees();
            for (Employee emp : liveEmployees) {
                String role = emp.getRole();
                if (role != null) {
                    if (role.equalsIgnoreCase("Doctor")) docCount++;
                    else if (role.equalsIgnoreCase("Nurse")) nurseCount++;
                }

                String[] staffRow = {
                    emp.getRole() != null ? emp.getRole() : "Unknown",
                    emp.getName() != null ? emp.getName() : "Unnamed",
                    emp.getStatus() != null ? emp.getStatus() : "Inactive"
                };
                staffModel.addRow(staffRow);
            }
        } catch (Exception ex) {
            System.err.println("Staff summary breakdown failed: " + ex.getMessage());
        }

        try {
            List<Patient> livePatients = PatientManagementSQL.getAllPatients();
            totalPatients = livePatients.size();
            for (Patient p : livePatients) {
                if (p.getStatus() != null && p.getStatus().equalsIgnoreCase("Critical")) {
                    criticalPatients++;
                }
            }
        } catch (Exception ex) {
            System.err.println("Patient summary breakdown failed: " + ex.getMessage());
        }

        try {
            List<Appointment> liveAppointments = AppointmentSQL.getAllAppointments();
            String todayPrefix = LocalDate.now().toString();

            for (Appointment app : liveAppointments) {
                String appDate = app.getAppointmentDate();
                if (appDate != null && appDate.startsWith(todayPrefix)) {
                    todayAppointments++;
                }
            }
        } catch (Exception ex) {
            System.err.println("Appointment summary breakdown failed: " + ex.getMessage());
        }

        try {
            List<LogisticsOrder> activeOrders = LogisticsSQL.getAllOrders();
            for (LogisticsOrder order : activeOrders) {
                if (order.getStatus() != null && order.getStatus().equalsIgnoreCase("Pending")) {
                    pendingDeliveries++;
                }
                
                activitiesModel.addRow(new Object[]{
                    order.getOrderId(),
                    order.getItem(),
                    "₱" + String.format("%,.2f", order.getAmount()),
                    order.getStatus()
                });
            }
        } catch (Exception ex) {
            System.err.println("Logistics logs data link failed: " + ex.getMessage());
        }

        lblTItem.setText(String.valueOf(totalPatients));
        lblLStock.setText(String.valueOf(todayAppointments));
        lblMed.setText(String.valueOf(criticalPatients));
        lblSup.setText(String.valueOf(docCount));
        lblEq.setText(String.valueOf(nurseCount));
        lblActive.setText(String.valueOf(pendingDeliveries));
    }

    private void filterDashboardByDate(String day) {
        try {
            int selectedDay = Integer.parseInt(day);
            String dateTargetPrefix = CMonth.atDay(selectedDay).toString();
            int selectedDayAppointments = 0;

            List<Appointment> liveAppointments = AppointmentSQL.getAllAppointments();
            for (Appointment app : liveAppointments) {
                String appDate = app.getAppointmentDate();
                if (appDate != null && appDate.startsWith(dateTargetPrefix)) {
                    selectedDayAppointments++;
                }
            }
            lblLStock.setText(String.valueOf(selectedDayAppointments));
        } catch (Exception ignored) {}
    }
    
    private void refreshCalendar() {
        CModel.setRowCount(0);
        lblMonth.setText(CMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));

        LocalDate first = CMonth.atDay(1);
        int offset = first.getDayOfWeek().getValue() - 1;
        int dim = CMonth.lengthOfMonth();
        int day = 1;

        for (int r = 0; r < 6; r++) {
            String[] row = new String[7];
            for (int c = 0; c < 7; c++) {
                if ((r == 0 && c < offset) || day > dim) {
                    row[c] = "";
                } else {
                    row[c] = String.valueOf(day++);
                }
            }
            CModel.addRow(row);
            if (day > dim) break;
        }
        tblCalendar.revalidate();
        tblCalendar.repaint();
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}