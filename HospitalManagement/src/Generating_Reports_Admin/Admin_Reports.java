package Generating_Reports_Admin;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Admin_Reports extends JPanel implements ActionListener{
    
    private JButton btnBill, btnRt, btnSf, btnAppointmentHistory, btnDiagnosisSummary, btnPrescriptionReport, btnEdit, btnSend, btnEx, btnMComplete, btnMIncomplete, btnMMissing;
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
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
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
        
        
        btnBill = new JButton("Billing Statement");
        btnBill.setBounds(20, 70, 400, 70);
        btnBill.setFont(new Font("Calibri", Font.BOLD, 18));
        btnBill.setBackground(Color.WHITE);
        btnBill.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnBill);
        
        
        btnRt = new JButton("Official Receipt");
        btnRt.setBounds(20, 155, 400, 70);
        btnRt.setFont(new Font("Calibri", Font.BOLD, 18));
        btnRt.setBackground(Color.WHITE);
        btnRt.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnRt);
        
        
        btnSf = new JButton("Staff Attendance");
        btnSf.setBounds(20, 240, 400, 70);
        btnSf.setFont(new Font("Calibri", Font.BOLD, 18));
        btnSf.setBackground(Color.WHITE);
        btnSf.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnSf);
        
        
        btnAppointmentHistory = new JButton("Appointment History");
        btnAppointmentHistory.setBounds(20, 325, 400, 70);
        btnAppointmentHistory.setFont(new Font("Calibri", Font.BOLD, 18));
        btnAppointmentHistory.setBackground(Color.WHITE);
        btnAppointmentHistory.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnAppointmentHistory);
        
        
        btnDiagnosisSummary = new JButton("Diagnosis Summary");
        btnDiagnosisSummary.setBounds(20, 410, 400, 70);
        btnDiagnosisSummary.setFont(new Font("Calibri", Font.BOLD, 18));
        btnDiagnosisSummary.setBackground(Color.WHITE);
        btnDiagnosisSummary.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnDiagnosisSummary);
        
        
        btnPrescriptionReport = new JButton("Prescription Report");
        btnPrescriptionReport.setBounds(20, 495, 400, 70);
        btnPrescriptionReport.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPrescriptionReport.setBackground(Color.WHITE);
        btnPrescriptionReport.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnPrescriptionReport);
        
        
        btnBill.addActionListener(this);
        btnRt.addActionListener(this);
        btnSf.addActionListener(this);
        btnAppointmentHistory.addActionListener(this);
        btnDiagnosisSummary.addActionListener(this);
        btnPrescriptionReport.addActionListener(this);
        
        pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 800);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);
        
        lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 250, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
        lblPrev.setForeground(Color.BLACK);
        pnlB.add(lblPrev);
        
        btnMComplete = new JButton("Complete");
        btnMComplete.setBounds(350, 25, 100, 30);
        btnMComplete.setBackground(Green);
        btnMComplete.setForeground(Color.WHITE);
        btnMComplete.addActionListener(e -> setStatus("Complete"));
        pnlB.add(btnMComplete);
        
        btnMIncomplete = new JButton("Incomplete");
        btnMIncomplete.setBounds(470, 25, 100, 30);
        btnMIncomplete.setBackground(Yellow);
        btnMIncomplete.setForeground(Color.BLACK);
        btnMIncomplete.addActionListener(e -> setStatus("Incomplete"));
        pnlB.add(btnMIncomplete);
        
        btnMMissing = new JButton("Missing");
        btnMMissing.setBounds(590, 25, 100, 30);
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
        
        scroll = new JScrollPane(pnlPrev);
        scroll.setBounds(30, 70, 1050, 650);
        scroll.setBorder(null);
        scroll.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnlB.add(scroll);
        
        btnEdit = new JButton("Edit Report");
        btnEdit.setBounds(500, 730, 150, 35);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(e -> editReport());
        pnlB.add(btnEdit);
        
        btnSend = new JButton("Send to Patient");
        btnSend.setBounds(660, 730, 170, 35);
        btnSend.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSend.setBackground(Green);
        btnSend.setForeground(Color.BLACK);
        btnSend.addActionListener(e -> sendReport());
        pnlB.add(btnSend);
        
        btnEx = new JButton("Export (CSV)");
        btnEx.setBounds(840, 730, 180, 35);
        btnEx.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEx.setBackground(LightGray);
        btnEx.setForeground(Color.BLACK);
        btnEx.addActionListener(e -> exportReport());
        pnlB.add(btnEx);
    }

    private void setStatus(String status) {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        lblStatus.setText("Status: " + status);
        
        switch (status) {
            case "Complete": lblStatus.setForeground(Green); break;
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
        } else if (currentReportType.equals("Staff Attendance")) {
            JOptionPane.showMessageDialog(this, "Staff Attendance Report cannot be sent to patients.");
            return;
        }
        setStatus("Sent to Patient");
        JOptionPane.showMessageDialog(this, "Sending " + currentReportType + " to Patient for approval...");
    }
    
    private void exportReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Exporting " + currentReportType + "...");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == btnBill) {
            pnlPrev.removeAll();
            BillingStatement bs = new BillingStatement();
            bs.setBounds(0, 0, 1020, 820);
            pnlPrev.setPreferredSize(new Dimension(1020, 820));
            pnlPrev.add(bs);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Billing Statement";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Billing Statement");
            btnSend.setVisible(true);
            
       
        } else if (ae.getSource() == btnRt) {
            pnlPrev.removeAll();
            OfficialReceipt or = new OfficialReceipt();
            or.setBounds(0, 0, 1020, 620);
            pnlPrev.setPreferredSize(new Dimension(1020, 620));
            pnlPrev.add(or);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Official Receipt";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Official Receipt");
            btnSend.setVisible(true);
            
        
        } else if (ae.getSource() == btnSf) {
            pnlPrev.removeAll();
            StaffAttendance sf = new StaffAttendance();
            sf.setBounds(0, 0, 1020, 680);
            pnlPrev.setPreferredSize(new Dimension(1020, 680));
            pnlPrev.add(sf);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Staff Attendance";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Staff Attendance");
            btnSend.setVisible(false);
            
        
        } else if (ae.getSource() == btnAppointmentHistory) {
            pnlPrev.removeAll();
            AppointmentHistoryAdmin ah = new AppointmentHistoryAdmin();
            ah.setBounds(0, 0, 1020, 680);
            pnlPrev.setPreferredSize(new Dimension(1020, 680));
            pnlPrev.add(ah);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Appointment History";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Appointment History");
            btnSend.setVisible(true);
            
        
        } else if (ae.getSource() == btnDiagnosisSummary) {
            pnlPrev.removeAll();
            //DiagnosisSummaryAdmin ds = new DiagnosisSummaryAdmin();
            //ds.setBounds(0, 0, 1020, 620);
            pnlPrev.setPreferredSize(new Dimension(1020, 620));
            //pnlPrev.add(ds);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Diagnosis Summary";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Diagnosis Summary");
            btnSend.setVisible(true);
            
        
        } else if (ae.getSource() == btnPrescriptionReport) {
            pnlPrev.removeAll();
            //PrescriptionReportAdmin pr = new PrescriptionReportAdmin();
            //pr.setBounds(0, 0, 1020, 820);
            pnlPrev.setPreferredSize(new Dimension(1020, 820));
            //pnlPrev.add(pr);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Prescription Report";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(new Color(255, 140, 0));
            lblType.setText(" > Prescription Report");
            btnSend.setVisible(true);
        }
    }
}