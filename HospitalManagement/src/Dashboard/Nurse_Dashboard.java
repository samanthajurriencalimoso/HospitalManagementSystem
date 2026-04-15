package Dashboard;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;
import javax.swing.*;

public class Nurse_Dashboard extends JPanel{
    
    private JPanel pnlMain;
    private JLabel lblView;
    
    public Nurse_Dashboard() {
        setLayout(null);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JLabel lblMainPlaceholder = new JLabel("NURSE Dashboard");
        lblMainPlaceholder.setBounds(50, 45, 300, 30);
        lblMainPlaceholder.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlMain.add(lblMainPlaceholder);
        
        JPanel pnlPatients = new JPanel();
        pnlPatients.setLayout(null);
        pnlPatients.setBounds(70, 100, 280, 120);
        pnlPatients.setBackground(LightRed);
        pnlMain.add(pnlPatients);
        
        JLabel lblPatients = new JLabel("Patients Assigned");
        lblPatients.setBounds(15, 15, 200, 20);
        lblPatients.setFont(new Font("Calibri", Font.BOLD, 22));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        
        JLabel lblPCount = new JLabel("24");
        lblPCount.setBounds(190, 65, 200, 40);
        lblPCount.setForeground(Color.WHITE);
        lblPCount.setFont(new Font("Calibri", Font.BOLD, 32));
        pnlPatients.add(lblPCount);
        
        JPanel pnlAppointment = new JPanel();
        pnlAppointment.setLayout(null);
        pnlAppointment.setBounds(370, 100, 280, 120);
        pnlAppointment.setBackground(Blue);
        pnlMain.add(pnlAppointment);

        JLabel lblATitle = new JLabel("Today's Tasks");
        lblATitle.setBounds(15, 15, 200, 20);
        lblATitle.setForeground(Color.WHITE);
        lblATitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlAppointment.add(lblATitle);

        JLabel lblACount = new JLabel("12");
        lblACount.setBounds(190,65, 200, 40);
        lblACount.setForeground(Color.WHITE);
        lblACount.setFont(new Font("Calibri", Font.BOLD, 32));
        pnlAppointment.add(lblACount);
        
        JPanel pnlPrescription = new JPanel();
        pnlPrescription.setLayout(null);
        pnlPrescription.setBounds(670, 100, 280, 120);
        pnlPrescription.setBackground(Yellow);
        pnlMain.add(pnlPrescription);

        JLabel lblPrTitle = new JLabel("Medication to Administer");
        lblPrTitle.setBounds(15, 15, 250, 20);
        lblPrTitle.setForeground(Color.WHITE);
        lblPrTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlPrescription.add(lblPrTitle);

        JLabel lblPrCount = new JLabel("8");
        lblPrCount.setBounds(190,65, 200, 40);
        lblPrCount.setForeground(Color.WHITE);
        lblPrCount.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlPrescription.add(lblPrCount);
        
        JPanel pnlCritical = new JPanel();
        pnlCritical.setLayout(null);
        pnlCritical.setBounds(970, 100, 280, 120);
        pnlCritical.setBackground(Green);
        pnlMain.add(pnlCritical);

        JLabel lblCTitle = new JLabel("Critical Cases");
        lblCTitle.setBounds(15, 15, 200, 20);
        lblCTitle.setForeground(Color.WHITE);
        lblCTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlCritical.add(lblCTitle);

        JLabel lblCCount = new JLabel("3");
        lblCCount.setBounds(190, 65, 200, 40);
        lblCCount.setForeground(Color.WHITE);
        lblCCount.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlCritical.add(lblCCount);
        
        JPanel pnlMSched = new JPanel();
        pnlMSched.setLayout(null);
        pnlMSched.setBounds(70, 240, 1080, 400);
        pnlMSched.setBackground(Color.WHITE);
        pnlMain.add(pnlMSched);

        JLabel lblMSched = new JLabel("Patients Vitals | Medication Schedule");
        lblMSched.setBounds(20, 15, 400, 30);
        lblMSched.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlMSched.add(lblMSched);
        
        lblView = new JLabel("View All");
        lblView.setBounds(1000, 15, 100, 30);
        lblView.setFont(new Font("Calibri", Font.BOLD, 18));
        lblView.setForeground(Color.BLUE);
        pnlMSched.add(lblView);

        String[] clmMSched = {"Patient ID", "Patient Name", "Room", "Time", "Heart Rate (BPM)", "Presz. (mmHg)", "Temperature", "Medicine", "Status"};

        String[][] RwMSched = {
            {"P001", "Juan Dela Cruz", "Room 101", "08:00 AM", "78", "120/80", "36.7°C", "Paracetamol 500mg", "Given"},
            {"P002", "Maria Santos", "Room 102", "08:30 AM", "82", "118/79", "36.8°C", "Amoxicillin 250mg", "Given"},
            {"P003", "Pedro Reyes", "Room 103", "09:00 AM", "90", "130/85", "37.2°C", "Insulin", "Pending"},
            {"P004", "Ana Lopez", "Room 104", "09:30 AM", "75", "115/75", "36.6°C", "Losartan 50mg", "Given"},
            {"P005", "Carlos Mendoza", "Room 105", "10:00 AM", "88", "125/82", "37.0°C", "IV Antibiotic", "Ongoing"},
            {"P006", "Liza Bautista", "Room 106", "10:30 AM", "80", "119/78", "36.9°C", "Vitamin C", "Pending"},
            {"P007", "Mark Villanueva", "Room 107", "11:00 AM", "76", "117/76", "36.5°C", "Omeprazole 20mg", "Given"},
            {"P008", "Sofia Ramos", "Room 108", "11:30 AM", "92", "135/88", "37.3°C", "Nebulization", "Ongoing"},
            {"P009", "Daniel Cruz", "Room 109", "12:00 PM", "85", "122/80", "37.1°C", "Paracetamol 500mg", "Pending"},
            {"P010", "Isabella Flores", "Room 110", "12:30 PM", "89", "128/84", "37.0°C", "IV Antibiotic", "Ongoing"},
            {"P011", "John Garcia", "Room 111", "01:00 PM", "72", "110/70", "36.4°C", "Aspirin 80mg", "Given"},
            {"P012", "Carla Fernandez", "Room 112", "01:30 PM", "95", "140/90", "37.5°C", "Metformin 500mg", "Pending"},
            {"P013", "Ethan Aquino", "Room 113", "02:00 PM", "87", "126/82", "37.2°C", "Insulin", "Pending"},
            {"P014", "Nicole Torres", "Room 114", "02:30 PM", "74", "115/75", "36.6°C", "Pain Reliever", "Given"}
        };

        JTable tblMSched = new JTable(RwMSched, clmMSched);
        tblMSched.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblMSched.setRowHeight(35);
        tblMSched.setGridColor(Color.LIGHT_GRAY);
        tblMSched.setBackground(Color.WHITE);

        JTableHeader hdrMSched = tblMSched.getTableHeader();
        hdrMSched.setFont(new Font("Calibri", Font.BOLD, 14));

        DefaultTableCellRenderer centerMSched = new DefaultTableCellRenderer();
        centerMSched.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tblMSched.getColumnModel().getColumn(i).setCellRenderer(centerMSched);
        }

        JScrollPane scrMSched = new JScrollPane(tblMSched);
        scrMSched.setBounds(20, 50, 1040, 340);
        scrMSched.setBorder(BorderFactory.createEmptyBorder());
        pnlMSched.add(scrMSched);
        
        tblMSched.getColumnModel().getColumn(7).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
        JLabel lblStatusAct = new JLabel(value.toString(), SwingConstants.CENTER);
        lblStatusAct.setOpaque(true);
        lblStatusAct.setFont(new Font("Calibri", Font.BOLD, 16));
        
        switch (value.toString()) {
        case "Given":
            lblStatusAct.setBackground(lightBlue);
            break;
        case "Ongoing":
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
        
        // Room Assignment Summary
        JPanel pnlSummary = new JPanel();
        pnlSummary.setLayout(null);
        pnlSummary.setBounds(70, 650, 700, 230);
        pnlSummary.setBackground(Color.WHITE);
        pnlMain.add(pnlSummary);

        JLabel lblSummary = new JLabel("Room Assignment Summary");
        lblSummary.setBounds(20, 15, 400, 30);
        lblSummary.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSummary.add(lblSummary);
        
        lblView = new JLabel("View All");
        lblView.setBounds(610, 15, 100, 30);
        lblView.setFont(new Font("Calibri", Font.BOLD, 18));
        lblView.setForeground(Color.BLUE);
        pnlSummary.add(lblView);

        String[] clmSummary = {"Room Number", "Doctor", "Nurse", "Equipment"};

        String[][] RwSummary = {
            {"Room 101", "Dr. Santos", "Nurse Cruz", "ECG Monitor"},
            {"Room 102", "Dr. Reyes", "Nurse Lopez", "Oxygen Tank"},
            {"Room 103", "Dr. Garcia", "Nurse Mendoza", "Infusion Pump"},
            {"Room 104", "Dr. Ramos", "Nurse Villanueva", "Wheelchair"}
        };

        JTable tblSummary = new JTable(RwSummary, clmSummary);
        tblSummary.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblSummary.setRowHeight(35);
        tblSummary.setGridColor(Color.LIGHT_GRAY);
        tblSummary.setBackground(Color.WHITE);

        JTableHeader summaryHeader = tblSummary.getTableHeader();
        summaryHeader.setFont(new Font("Calibri", Font.BOLD, 14));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tblSummary.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JScrollPane scrSummary = new JScrollPane(tblSummary);
        scrSummary.setBounds(20, 50, 660, 150);
        scrSummary.setBorder(BorderFactory.createEmptyBorder());
        pnlSummary.add(scrSummary);
        
        JPanel pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBounds(800, 650, 350, 230);
        pnlSelection.setBackground(Color.WHITE);
        pnlMain.add(pnlSelection);
        
        JLabel lblQuickA = new JLabel("Quick Actions");
        lblQuickA.setBounds(20, 15, 200, 30);
        lblQuickA.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlSelection.add(lblQuickA);

        JButton btnVitals = new JButton("Take Vitals");
        btnVitals.setBounds(20, 55, 140, 65);
        btnVitals.setFont(new Font("Calibri", Font.BOLD, 18));
        btnVitals.setBackground(Green);
        btnVitals.setForeground(Color.BLACK);
        btnVitals.setFocusPainted(false);
        pnlSelection.add(btnVitals);

        JButton btnSeeAppointment = new JButton("See Appointment");
        btnSeeAppointment.setBounds(20, 140, 140, 65);
        btnSeeAppointment.setFont(new Font("Calibri", Font.BOLD, 14));
        btnSeeAppointment.setBackground(Yellow);
        btnSeeAppointment.setForeground(Color.BLACK);
        btnSeeAppointment.setFocusPainted(false);
        pnlSelection.add(btnSeeAppointment);
        
        JButton btnMChart = new JButton("Medication Chart");
        btnMChart.setBounds(190, 55, 140, 65);
        btnMChart.setFont(new Font("Calibri", Font.BOLD, 14));
        btnMChart.setBackground(Blue);
        btnMChart.setForeground(Color.BLACK);
        btnMChart.setFocusPainted(false);
        pnlSelection.add(btnMChart);

        JButton btnWriteNote = new JButton("Write Note");
        btnWriteNote.setBounds(190, 140, 140, 65);
        btnWriteNote.setFont(new Font("Calibri", Font.BOLD, 18));
        btnWriteNote.setBackground(LightRed);
        btnWriteNote.setForeground(Color.BLACK);
        btnWriteNote.setFocusPainted(false);
        pnlSelection.add(btnWriteNote);
        
        JPanel pnlWard = new JPanel();
        pnlWard.setLayout(null);
        pnlWard.setBounds(1170, 240, 420, 600);
        pnlWard.setBackground(Color.WHITE);
        pnlMain.add(pnlWard);
        
        JLabel lblWard = new JLabel("Ward Activity");
        lblWard.setBounds(20, 20, 300, 30);
        lblWard.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlWard.add(lblWard);
        
        String[] clmWard = {"Patient", "Task", "Time"};
        
        String[][] RwWard = {
            {"Juan Dela Cruz", "Administer antibiotics (IV)", "Now"},
            {"Maria Santos", "Check vital signs", "5 min"},
            {"Pedro Reyes", "Change wound dressing", "15 min"},
            {"Ana Lopez", "Assist with ambulation", "20 min"},
            {"Carlos Mendoza", "Monitor IV fluid level", "30 min"},
            {"Liza Bautista", "Administer insulin", "45 min"},
            {"Mark Villanueva", "Reposition patient", "1 hr"},
            {"Sofia Ramos", "Prepare for discharge", "1 hr"},
            {"Daniel Cruz", "Collect blood sample", "2 hrs"},
            {"Isabella Flores", "Check oxygen saturation", "2 hrs"},
            {"John Garcia", "Assist in feeding", "3 hrs"},
            {"Carla Fernandez", "Record intake/output", "3 hrs"},
            {"Ethan Aquino", "Monitor BP and pulse", "4 hrs"},
            {"Nicole Torres", "Administer pain medication", "4 hrs"}
            };
        
        JTable tblWard = new JTable(RwWard, clmWard);
        tblWard.setFont(new Font("Calibri", Font.PLAIN, 14));
        tblWard.setRowHeight(55);
        tblWard.setGridColor(Color.LIGHT_GRAY);
        tblWard.setBackground(Color.WHITE);
        
        JTableHeader HdrWard = tblWard.getTableHeader();
        HdrWard.setFont(new Font("Calibri", Font.BOLD, 14));

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        for (int i = 0; i < 3; i++) {
            tblWard.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
        }

        tblWard.getColumnModel().getColumn(0).setPreferredWidth(110);
        tblWard.getColumnModel().getColumn(1).setPreferredWidth(190);
        tblWard.getColumnModel().getColumn(2).setPreferredWidth(80);

        JScrollPane scrWard = new JScrollPane(tblWard);
        scrWard.setBounds(20, 70, 380, 450);
        scrWard.setBorder(BorderFactory.createEmptyBorder());
        pnlWard.add(scrWard);
        
        JButton btnViewAll = new JButton("View All Activity");
        btnViewAll.setBounds(125, 535, 180, 50);
        btnViewAll.setFont(new Font("Calibri", Font.BOLD, 18));
        btnViewAll.setBackground(Blue);
        btnViewAll.setForeground(Color.BLACK);
        pnlWard.add(btnViewAll);
    }
}
