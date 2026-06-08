package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Doctor_Report extends JPanel implements ActionListener {

    private JButton btnPrescription, btnDiagnosis, btnAppointment, btnSend, btnEdit, btnPrint;
    private JButton btnMComplete, btnMIncomplete, btnMMissing;
    private JLabel lblType, lblStatus, lblDoctor, lblDT, lbltext, lblRType, lblPrev;
    private JPanel pnlMain, pnlPrev, pnlRType, pnlB;
    private String currentReportType = "";
    private JScrollPane scroll;
    private JButton btnSaveReport, btnCopyReport;

    public Doctor_Report() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        lblDoctor = new JLabel("Doctor Reports");
        lblDoctor.setBounds(30, 20, 300, 50);
        lblDoctor.setFont(new Font("Calibri", Font.BOLD, 28));
        lblDoctor.setForeground(Color.BLACK);
        pnlMain.add(lblDoctor);

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

        lbltext = new JLabel("Manage prescription, diagnosis, and appointment documents.");
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

        btnPrescription = new JButton("Prescription Report");
        btnPrescription.setBounds(20, 70, 400, 80);
        btnPrescription.setFont(new Font("Calibri", Font.BOLD, 18));
        btnPrescription.setBackground(Color.WHITE);
        btnPrescription.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnPrescription);

        btnDiagnosis = new JButton("Patient Diagnosis Summary");
        btnDiagnosis.setBounds(20, 170, 400, 80);
        btnDiagnosis.setFont(new Font("Calibri", Font.BOLD, 18));
        btnDiagnosis.setBackground(Color.WHITE);
        btnDiagnosis.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnDiagnosis);

        btnAppointment = new JButton("Appointment History Report");
        btnAppointment.setBounds(20, 270, 400, 80);
        btnAppointment.setFont(new Font("Calibri", Font.BOLD, 18));
        btnAppointment.setBackground(Color.WHITE);
        btnAppointment.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnAppointment);

        btnPrescription.addActionListener(this);
        btnDiagnosis.addActionListener(this);
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

        btnSend = new JButton("Send to Admin");
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
        
        btnSaveReport = new JButton("Save Report");
        btnSaveReport.setBounds(500, 730, 150, 35);
        btnSaveReport.setBackground(Green);
        btnSaveReport.setForeground(Color.WHITE);
        btnSaveReport.addActionListener(e -> saveCurrentReport());
        pnlB.add(btnSaveReport);
        
        
        btnCopyReport = new JButton("Copy Report");
        btnCopyReport.setBounds(660, 730, 150, 35);
        btnCopyReport.setBackground(mediumBlue);
        btnCopyReport.setForeground(Color.WHITE);
        btnCopyReport.addActionListener(e -> copyCurrentReport());
        pnlB.add(btnCopyReport);
        
        
    }
    
    private void saveCurrentReport() {
            if (currentReportType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a report first!");
                return;
            }
            setStatus("Saved");
            JOptionPane.showMessageDialog(this, currentReportType + " saved successfully!");
        }
    
    private void copyCurrentReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        String data = "=== ETRIAGE HOSPITAL " + currentReportType.toUpperCase() + " ===\n";
        data += "Date: " + java.time.LocalDate.now() + "\n";
        data += "Status: " + lblStatus.getText() + "\n";
        data += "This document is an official report from eTriage Hospital System.";

        StringSelection ss = new StringSelection(data);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        JOptionPane.showMessageDialog(this, currentReportType + " copied to clipboard!");
    }

    private void setStatus(String status) {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report first!");
            return;
        }
        lblStatus.setText("Status: " + status);
        switch (status) {
            case "Complete": lblStatus.setForeground(Green); break;
            case "Incomplete": lblStatus.setForeground(Color.YELLOW); break;
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
            pr.setBounds(0, 0, 1020, 820);
            pnlPrev.setPreferredSize(new Dimension(1020, 820));
            pnlPrev.add(pr);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Prescription Report";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(orange);
            lblType.setText(" > Prescription Report");
        } else if (ae.getSource() == btnDiagnosis) {
            pnlPrev.removeAll();
            DiagnosisSummary ds = new DiagnosisSummary();
            ds.setBounds(0, 0, 1020, 620);
            pnlPrev.setPreferredSize(new Dimension(1020, 620));
            pnlPrev.add(ds);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Diagnosis Summary";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(orange);
            lblType.setText(" > Patient Diagnosis Summary");
        } else if (ae.getSource() == btnAppointment) {
            pnlPrev.removeAll();
            AppointmentHistory ah = new AppointmentHistory();
            ah.setBounds(0, 0, 1020, 680);
            pnlPrev.setPreferredSize(new Dimension(1020, 680));
            pnlPrev.add(ah);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);
            currentReportType = "Appointment History";
            lblStatus.setText("Status: Incomplete");
            lblStatus.setForeground(orange);
            lblType.setText(" > Appointment History Report");
        }
    }
}