package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class CareTaskSummary extends JPanel {
    
    public CareTaskSummary() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlCareTask = new JPanel();
        pnlCareTask.setLayout(null);
        pnlCareTask.setPreferredSize(new Dimension(1040, 900));
        pnlCareTask.setBackground(Color.WHITE);
        pnlCareTask.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrCareTask = new JScrollPane(pnlCareTask);
        scrCareTask.setBounds(0, 0, 1060, 650);
        scrCareTask.setBorder(BorderFactory.createEmptyBorder());
        scrCareTask.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrCareTask);

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 25, 90, 90);
        pnlCareTask.add(lblLogo);

        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 30, 350, 25);
        pnlCareTask.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblAddress.setBounds(140, 55, 400, 18);
        pnlCareTask.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 12));
        lblContact.setBounds(140, 73, 300, 18);
        pnlCareTask.add(lblContact);

        JLabel lblTitle = new JLabel("CARE TASK SUMMARY");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(680, 35, 400, 30);
        pnlCareTask.add(lblTitle);

        JLabel lblRefNo = new JLabel("Ref No.: CT-2026-001");
        lblRefNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRefNo.setBounds(740, 70, 200, 20);
        pnlCareTask.add(lblRefNo);

        JLabel lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 90, 200, 20);
        pnlCareTask.add(lblDate);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 130, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlCareTask.add(lineTop);

        JPanel pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 150, 980, 80);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlCareTask.add(pnlPatient);

        JLabel lblPName = new JLabel("Patient Name: Joshua Garcia");
        lblPName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPName.setBounds(25, 30, 300, 20);
        pnlPatient.add(lblPName);

        JLabel lblPID = new JLabel("Patient ID: P-10234");
        lblPID.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPID.setBounds(400, 30, 200, 20);
        pnlPatient.add(lblPID);

        JLabel lblRoom = new JLabel("Room: 101");
        lblRoom.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRoom.setBounds(700, 30, 150, 20);
        pnlPatient.add(lblRoom);

        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBounds(30, 250, 980, 280);
        pnlTable.setBorder(BorderFactory.createTitledBorder("Care Tasks"));
        pnlCareTask.add(pnlTable);

        String[] clmTask = {"Time", "Task", "Assigned To", "Status", "Completed At"};
        String[][] rwTask = {
            {"08:00 AM", "Check vital signs", "Nurse Angela", "Completed", "08:15 AM"},
            {"09:00 AM", "Administer medication", "Nurse Angela", "Completed", "09:05 AM"},
            {"10:00 AM", "Assist with bathing", "Nurse Angela", "In Progress", "-"},
            {"12:00 PM", "Monitor food intake", "Nurse Angela", "Pending", "-"},
            {"02:00 PM", "Change wound dressing", "Nurse Angela", "Scheduled", "-"}
        };

        JTable tblTask = new JTable(rwTask, clmTask);
        tblTask.setRowHeight(35);
        tblTask.setFont(new Font("Calibri", Font.PLAIN, 13));
        tblTask.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 13));

        JScrollPane scrTable = new JScrollPane(tblTask);
        scrTable.setBounds(20, 30, 940, 220);
        pnlTable.add(scrTable);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 560, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlCareTask.add(lineFooter);

        JLabel lblFooter = new JLabel("Total Tasks: 5 | Completed: 2 | In Progress: 1 | Pending: 1 | Scheduled: 1");
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 12));
        lblFooter.setBounds(280, 580, 550, 20);
        pnlCareTask.add(lblFooter);
    }
}