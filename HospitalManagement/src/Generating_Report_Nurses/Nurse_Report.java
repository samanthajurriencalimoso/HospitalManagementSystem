package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Nurse_Report extends JPanel implements ActionListener{
    
    private JButton btnMedication, btnVitals, btnCareTask, btnSend, btnEdit, btnPrint;
    private JButton btnMarkComplete, btnMarkIncomplete, btnMarkMissing;
    private JLabel lblType, lblStatus;
    private JPanel pnlMain, pnlPrev;
    private String currentReportType = "";
    
    public Nurse_Report() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JLabel lblNurse = new JLabel("Nurse Reports");
        lblNurse.setBounds(30, 20, 300, 50);
        lblNurse.setFont(new Font("Calibri", Font.BOLD, 28));
        lblNurse.setForeground(Color.BLACK);
        pnlMain.add(lblNurse);
        
        lblType = new JLabel();
        lblType.setBounds(210, 20, 400, 50);
        lblType.setFont(new Font("Calibri", Font.BOLD, 28));
        lblType.setForeground(Color.BLACK);
        pnlMain.add(lblType);
        
        JLabel lbltext = new JLabel("Manage medication, vitals, and care task documents.");
        lbltext.setBounds(30, 60, 500, 30);
        lbltext.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbltext.setForeground(Color.BLACK);
        pnlMain.add(lbltext);
        
        // ===== LEFT PANEL (ONLY Report Type Buttons) =====
        JPanel pnlRType = new JPanel();
        pnlRType.setLayout(null);
        pnlRType.setBounds(30, 110, 450, 800);
        pnlRType.setBackground(Color.WHITE);
        pnlMain.add(pnlRType);
        
        JLabel lblRType = new JLabel("Select Report Type");
        lblRType.setBounds(30, 20, 240, 30);
        lblRType.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlRType.add(lblRType);
        
        btnMedication = new JButton("Medication Administration Log");
        btnMedication.setBounds(20, 70, 400, 80);
        btnMedication.setFont(new Font("Calibri", Font.BOLD, 18));
        btnMedication.setBackground(Color.WHITE);
        btnMedication.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnMedication.addActionListener(this);
        pnlRType.add(btnMedication);
        
        btnVitals = new JButton("Vital Signs Report");
        btnVitals.setBounds(20, 170, 400, 80);
        btnVitals.setFont(new Font("Calibri", Font.BOLD, 20));
        btnVitals.setBackground(Color.WHITE);
        btnVitals.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnVitals.addActionListener(this);
        pnlRType.add(btnVitals);
        
        btnCareTask = new JButton("Care Task Summary");
        btnCareTask.setBounds(20, 270, 400, 80);
        btnCareTask.setFont(new Font("Calibri", Font.BOLD, 20));
        btnCareTask.setBackground(Color.WHITE);
        btnCareTask.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnCareTask.addActionListener(this);
        pnlRType.add(btnCareTask);
        
        // ===== RIGHT PANEL =====
        JPanel pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 800);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);
        
        // TOP ROW: Report Preview on LEFT, Change Status + Status on RIGHT EDGE
        JLabel lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 200, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlB.add(lblPrev);
        
        // CHANGE STATUS Buttons (pushed to right edge)
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
        
        // STATUS LABEL (next to buttons, at right edge)
        lblStatus = new JLabel("Status: No report selected");
        lblStatus.setBounds(990, 25, 200, 28);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 13));
        lblStatus.setForeground(Color.RED);
        pnlB.add(lblStatus);
        
        // Preview Scroll Pane
        pnlPrev = new JPanel();
        pnlPrev.setLayout(null);
        pnlPrev.setBackground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(pnlPrev);
        scroll.setBounds(30, 80, 1040, 610);
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnlB.add(scroll);
        
        // Bottom Buttons
        btnEdit = new JButton("Edit Report");
        btnEdit.setBounds(590, 710, 150, 35);
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(e -> editReport());
        pnlB.add(btnEdit);
        
        btnSend = new JButton("Send to Doctor");
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
        if (ae.getSource() == btnMedication) {
            pnlPrev.removeAll();
            MedicationLog ml = new MedicationLog();
            ml.setBounds(0, 0, 1060, 650);
            pnlPrev.add(ml);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            currentReportType = "Medication Log";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Medication Administration Log");
        } else if (ae.getSource() == btnVitals) {
            pnlPrev.removeAll();
            VitalSignsReport vs = new VitalSignsReport();
            vs.setBounds(0, 0, 1060, 650);
            pnlPrev.add(vs);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            currentReportType = "Vital Signs Report";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Vital Signs Report");
        } else if (ae.getSource() == btnCareTask) {
            pnlPrev.removeAll();
            CareTaskSummary ct = new CareTaskSummary();
            ct.setBounds(0, 0, 1060, 650);
            pnlPrev.add(ct);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            currentReportType = "Care Task Summary";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Care Task Summary");
        }
    }
}