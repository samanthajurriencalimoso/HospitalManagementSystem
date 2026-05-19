package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Doctor_Report extends JPanel implements ActionListener{
    
    private JButton btnPrescription, btnDiagnosis, btnAppointment, btnSend, btnEdit, btnPrint;
    private JButton btnMarkComplete, btnMarkIncomplete, btnMarkMissing;
    private JLabel lblType, lblStatus;
    private JPanel pnlMain, pnlPrev;
    private String currentReportType = "";
    
    public Doctor_Report() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JLabel lblDoctor = new JLabel("Doctor Reports");
        lblDoctor.setBounds(30, 20, 300, 50);
        lblDoctor.setFont(new Font("Calibri", Font.BOLD, 28));
        lblDoctor.setForeground(Color.BLACK);
        pnlMain.add(lblDoctor);
        
        lblType = new JLabel();
        lblType.setBounds(210, 20, 400, 50);
        lblType.setFont(new Font("Calibri", Font.BOLD, 28));
        lblType.setForeground(Color.BLACK);
        pnlMain.add(lblType);
        
        JLabel lbltext = new JLabel("Manage prescription, diagnosis, and appointment documents.");
        lbltext.setBounds(30, 60, 500, 30);
        lbltext.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbltext.setForeground(Color.BLACK);
        pnlMain.add(lbltext);
        
       
        JPanel pnlRType = new JPanel();
        pnlRType.setLayout(null);
        pnlRType.setBounds(30, 110, 450, 800);
        pnlRType.setBackground(Color.WHITE);
        pnlMain.add(pnlRType);
        
        JLabel lblRType = new JLabel("Select Report Type");
        lblRType.setBounds(30, 20, 240, 30);
        lblRType.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlRType.add(lblRType);
        
        btnPrescription = new JButton("Prescription Report");
        btnPrescription.setBounds(20, 70, 400, 80);
        btnPrescription.setFont(new Font("Calibri", Font.BOLD, 20));
        btnPrescription.setBackground(Color.WHITE);
        btnPrescription.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnPrescription.addActionListener(this);
        pnlRType.add(btnPrescription);
        
        btnDiagnosis = new JButton("Patient Diagnosis Summary");
        btnDiagnosis.setBounds(20, 170, 400, 80);
        btnDiagnosis.setFont(new Font("Calibri", Font.BOLD, 20));
        btnDiagnosis.setBackground(Color.WHITE);
        btnDiagnosis.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnDiagnosis.addActionListener(this);
        pnlRType.add(btnDiagnosis);
        
        btnAppointment = new JButton("Appointment History Report");
        btnAppointment.setBounds(20, 270, 400, 80);
        btnAppointment.setFont(new Font("Calibri", Font.BOLD, 20));
        btnAppointment.setBackground(Color.WHITE);
        btnAppointment.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnAppointment.addActionListener(this);
        pnlRType.add(btnAppointment);
        
       
        JPanel pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 800);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);
        
       
        JLabel lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 200, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlB.add(lblPrev);
        
      
        btnMarkComplete = new JButton("Complete");
        btnMarkComplete.setBounds(650, 25, 100, 28);
        btnMarkComplete.setBackground(Green);
        btnMarkComplete.setForeground(Color.WHITE);
        btnMarkComplete.addActionListener(e -> setStatus("Complete"));
        pnlB.add(btnMarkComplete);
        
        btnMarkIncomplete = new JButton("Incomplete");
        btnMarkIncomplete.setBounds(760, 25, 100, 28);
        btnMarkIncomplete.setBackground(Yellow);
        btnMarkIncomplete.setForeground(Color.BLACK);
        btnMarkIncomplete.addActionListener(e -> setStatus("Incomplete"));
        pnlB.add(btnMarkIncomplete);
        
        btnMarkMissing = new JButton("Missing");
        btnMarkMissing.setBounds(870, 25, 100, 28);
        btnMarkMissing.setBackground(LightRed);
        btnMarkMissing.setForeground(Color.WHITE);
        btnMarkMissing.addActionListener(e -> setStatus("Missing"));
        pnlB.add(btnMarkMissing);
        
      
        lblStatus = new JLabel("Status: No report selected");
        lblStatus.setBounds(990, 25, 200, 28);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 13));
        lblStatus.setForeground(Color.RED);
        pnlB.add(lblStatus);
        
       
        pnlPrev = new JPanel();
        pnlPrev.setLayout(null);
        pnlPrev.setBackground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(pnlPrev);
        scroll.setBounds(30, 80, 1040, 610);
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnlB.add(scroll);
        
       
        btnEdit = new JButton("Edit Report");
        btnEdit.setBounds(590, 710, 150, 35);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(e -> editReport());
        pnlB.add(btnEdit);
        
        btnSend = new JButton("Send to Admin");
        btnSend.setBounds(760, 710, 180, 35);
        btnSend.setBackground(Green);
        btnSend.setForeground(Color.BLACK);
        btnSend.addActionListener(e -> sendReport());
        pnlB.add(btnSend);
        
        btnPrint = new JButton("Print Report");
        btnPrint.setBounds(960, 710, 120, 35);
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
        setStatus("Sent to Admin");
        JOptionPane.showMessageDialog(this, "Sending " + currentReportType + " to Admin for approval...");
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
        if (ae.getSource() == btnPrescription) {
            pnlPrev.removeAll();
            PrescriptionReport pr = new PrescriptionReport();
            pr.setBounds(0, 0, 1060, 650);
            pnlPrev.add(pr);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            currentReportType = "Prescription Report";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Prescription Report");
        } else if (ae.getSource() == btnDiagnosis) {
            pnlPrev.removeAll();
            DiagnosisSummary ds = new DiagnosisSummary();
            ds.setBounds(0, 0, 1060, 650);
            pnlPrev.add(ds);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            currentReportType = "Diagnosis Summary";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Patient Diagnosis Summary");
        } else if (ae.getSource() == btnAppointment) {
            pnlPrev.removeAll();
            AppointmentHistory ah = new AppointmentHistory();
            ah.setBounds(0, 0, 1060, 650);
            pnlPrev.add(ah);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            currentReportType = "Appointment History";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Appointment History Report");
        }
    }
}