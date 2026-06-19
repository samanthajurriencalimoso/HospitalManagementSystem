package Generating_Reports_Admin;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Admin_Reports extends JPanel implements ActionListener{
    
    private JButton btnPrescriptionReport, btnEdit, btnSend, btnEx, btnMComplete, btnMIncomplete, btnMMissing;
    private JLabel lblType, lblAdmin, lblDT, lbltext, lblRType, lblPrev, lblStatus;
    private JPanel pnlMain, pnlRType, pnlB, pnlPrev;
    private String currentReportType = "";
    private JScrollPane scroll;
    
    public Admin_Reports() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lblAdmin = new JLabel("Admin Reports");
        lblAdmin.setBounds(30, 20, 300, 50);
        lblAdmin.setFont(new Font("Calibri", Font.BOLD, 28));
        lblAdmin.setForeground(Color.BLACK);
        pnlMain.add(lblAdmin);
        
        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();
        
        lblType = new JLabel();
        lblType.setBounds(210, 20, 400, 50);
        lblType.setFont(new Font("Calibri", Font.BOLD, 28));
        lblType.setForeground(Color.BLACK);
        pnlMain.add(lblType);
        
        lbltext = new JLabel("Manage financial, operational, and compliance documents.");
        lbltext.setBounds(30, 60, 500, 50);
        lbltext.setFont(new Font("Calibri", Font.PLAIN, 20));
        lbltext.setForeground(Color.BLACK);
        pnlMain.add(lbltext);
        
        pnlRType = new JPanel();
        pnlRType.setLayout(null);
        pnlRType.setBounds(30, 110, 450, 750);
        pnlRType.setBackground(Color.WHITE);
        pnlMain.add(pnlRType);
        
        lblRType = new JLabel("Select Report Type");
        lblRType.setBounds(30, 20, 240, 30);
        lblRType.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlRType.add(lblRType);
        
        btnPrescriptionReport = new JButton("Appointment History");
        btnPrescriptionReport.setBounds(20, 70, 400, 70);
        btnPrescriptionReport.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPrescriptionReport.setBackground(Color.WHITE);
        btnPrescriptionReport.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnPrescriptionReport);
        
        btnPrescriptionReport.addActionListener(this);
        
        pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 1000);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);
        
        lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 250, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
        lblPrev.setForeground(Color.BLACK);
        pnlB.add(lblPrev);
        
        pnlPrev = new JPanel();
        pnlPrev.setLayout(null);
        pnlPrev.setBackground(Color.WHITE);
        pnlPrev.setPreferredSize(new Dimension(1020, 920));
        
        scroll = new JScrollPane(pnlPrev);
        scroll.setBounds(30, 70, 1050, 650);
        scroll.setBorder(null);
        scroll.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnlB.add(scroll);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnPrescriptionReport) {
            pnlPrev.removeAll();
            AppointmentAdmin pr = new AppointmentAdmin();
            pr.setBounds(0, 0, 1060, 800);
            pnlPrev.setPreferredSize(new Dimension(1060, 850));
            pnlPrev.add(pr);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            lblType.setText(" > Appointment History");
            btnSend.setVisible(true);
        }
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}