package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Nurses_Report extends JPanel implements ActionListener{
    
    private JButton btnAppointment, btnSend, btnEdit, btnPrint, btnMComplete, btnMIncomplete, btnMMissing;
    private JLabel lblType, lblStatus, lblDT, lblNurse, lbltext, lblRType, lblPrev;
    private JPanel pnlMain, pnlPrev, pnlRType, pnlB;
    private JScrollPane scroll;
    private String currentReportType = "";
    private JButton btnNewReport, btnCopyReport;
    
    public Nurses_Report() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lblNurse = new JLabel("Nurse Reports");
        lblNurse.setBounds(30, 20, 300, 50);
        lblNurse.setFont(new Font("Calibri", Font.BOLD, 28));
        lblNurse.setForeground(Color.BLACK);
        pnlMain.add(lblNurse);
        
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
        
        lbltext = new JLabel("Manage appointment history documents.");
        lbltext.setBounds(30, 60, 500, 30);
        lbltext.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbltext.setForeground(Color.BLACK);
        pnlMain.add(lbltext);
        
        pnlRType = new JPanel();
        pnlRType.setLayout(null);
        pnlRType.setBounds(30, 110, 450, 800);
        pnlRType.setBackground(Color.WHITE);
        pnlMain.add(pnlRType);
        
        lblRType = new JLabel("Select Report Type");
        lblRType.setBounds(30, 20, 240, 30);
        lblRType.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlRType.add(lblRType);
        
        btnAppointment = new JButton("Appointment History");
        btnAppointment.setBounds(20, 70, 400, 80);
        btnAppointment.setFont(new Font("Calibri", Font.BOLD, 18));
        btnAppointment.setBackground(Color.WHITE);
        btnAppointment.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnAppointment);
        
        btnAppointment.addActionListener(this);
        
        pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 800);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);

        lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 200, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
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
        if (ae.getSource() == btnAppointment) {
            pnlPrev.removeAll();
            AppointmentNurse ah = new AppointmentNurse();
            ah.setBounds(0, 0, 1020, 820);
            pnlPrev.setPreferredSize(new Dimension(1020, 820));
            pnlPrev.add(ah);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            lblType.setText(" > Appointment History");
        }
    }
    
    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}