package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class AppointmentNurse extends JPanel implements ActionListener{
    
    private JButton btnMedication, btnVitals, btnCareTask, btnSend, btnEdit, btnPrint,btnMComplete, btnMIncomplete, btnMMissing;
    private JLabel lblType, lblStatus, lblDT,lblNurse,lbltext,lblRType,lblPrev;
    private JPanel pnlMain, pnlPrev,pnlRType, pnlB;
    private JScrollPane scroll;
    private String currentReportType = "";
    
    public AppointmentNurse() {
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
        
        lbltext = new JLabel("Manage medication, appointment history, and care task documents.");
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
        
        btnMedication = new JButton("Medication Administration Log");
        btnMedication.setBounds(20, 70, 400, 80);
        btnMedication.setFont(new Font("Calibri", Font.BOLD, 18));
        btnMedication.setBackground(Color.WHITE);
        btnMedication.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnMedication);
        
        btnVitals = new JButton("Appointment History");
        btnVitals.setBounds(20, 170, 400, 80);
        btnVitals.setFont(new Font("Calibri", Font.BOLD, 20));
        btnVitals.setBackground(Color.WHITE);
        btnVitals.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnVitals);
        
        btnCareTask = new JButton("Care Task Summary");
        btnCareTask.setBounds(20, 270, 400, 80);
        btnCareTask.setFont(new Font("Calibri", Font.BOLD, 20));
        btnCareTask.setBackground(Color.WHITE);
        btnCareTask.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnCareTask);
        
        btnMedication.addActionListener(this);
        btnVitals.addActionListener(this);
        btnCareTask.addActionListener(this);
        
        pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 800);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);

        lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 200, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlB.add(lblPrev);
        
        btnMComplete = new JButton("Complete");
        btnMComplete.setBounds(350, 25, 100, 30);
        btnMComplete.setBackground(Green);
        btnMComplete.setForeground(Color.WHITE);
        btnMComplete.addActionListener(e -> setStatus("Complete"));
        pnlB.add(btnMComplete);
        
        btnMIncomplete = new JButton("Incomplete");
        btnMIncomplete.setBounds(470, 25, 100, 28);
        btnMIncomplete.setBackground(Yellow);
        btnMIncomplete.setForeground(Color.BLACK);
        btnMIncomplete.addActionListener(e -> setStatus("Incomplete"));
        pnlB.add(btnMIncomplete);
        
        btnMMissing = new JButton("Missing");
        btnMMissing.setBounds(590, 25, 100, 28);
        btnMMissing.setBackground(LightRed);
        btnMMissing.setForeground(Color.WHITE);
        btnMMissing.addActionListener(e -> setStatus("Missing"));
        pnlB.add(btnMMissing);
        
        lblStatus = new JLabel("Status: No report selected");
        lblStatus.setBounds(850, 25, 250, 28);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 20));
        lblStatus.setForeground(Color.RED);
        pnlB.add(lblStatus);
        
        pnlPrev = new JPanel();
        pnlPrev.setLayout(null);
        pnlPrev.setBackground(Color.WHITE);
        pnlPrev.setPreferredSize(new Dimension(1020, 920));
        pnlMain.add(pnlPrev);
        
        scroll = new JScrollPane(pnlPrev);
        scroll.setBounds(30, 70, 1050, 650);
        scroll.setBorder(null);
        scroll.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnlB.add(scroll);
        
        btnEdit = new JButton("Edit Report");
        btnEdit.setBounds(500, 730, 150, 35);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(e -> editReport());
        pnlB.add(btnEdit);
        
        btnSend = new JButton("Send to Doctor");
        btnSend.setBounds(660, 730, 170, 35);
        btnSend.setBackground(Green);
        btnSend.setForeground(Color.BLACK);
        btnSend.addActionListener(e -> sendReport());
        pnlB.add(btnSend);
        
        btnPrint = new JButton("Print Report");
        btnPrint.setBounds(840, 730, 180, 35);
        btnPrint.setBackground(LightGray);
        btnPrint.setForeground(Color.BLACK);
        btnPrint.addActionListener(e -> printReport());
        pnlB.add(btnPrint);
    }
    
    private void setStatus(String status) {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        lblStatus.setText("Status: " + status);
        
        switch (status) {
            case "Complete": lblStatus.setForeground(new Color(0, 150, 0)); break;
            case "Incomplete": lblStatus.setForeground(new Color(255, 140, 0)); break;
            case "Missing": lblStatus.setForeground(Color.RED); break;
            default: lblStatus.setForeground(Color.BLACK);
        }
    }
    
    private void editReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Edit Report: " + currentReportType);
    }
    
    private void sendReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        setStatus("Sent to Doctor");
        JOptionPane.showMessageDialog(this, "Sending " + currentReportType + " to Doctor for review...");
    }
    
    private void printReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Printing " + currentReportType + "...");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == btnVitals) {
            pnlPrev.removeAll();
            AppointmentNurse vs = new AppointmentNurse();
            vs.setBounds(0, 0, 1020, 620);
            pnlPrev.setPreferredSize(new Dimension(1020, 620));
            pnlPrev.add(vs);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Appointment History";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
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

