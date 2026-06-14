package Generating_Report_Nurses;

import static Color_Palette.ColorPalette.*;
import Database.NurseAppointmentSQL;
import Models.NurseAppointment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.*;

public class Nurses_Report extends JPanel implements ActionListener {

    private JButton btnVitals;
    private JButton btnSend, btnEdit, btnPrint;
    private JButton btnMComplete, btnMIncomplete, btnMMissing;
    private JLabel lblType, lblStatus, lblDT, lblNurse, lbltext, lblRType, lblPrev;
    private JPanel pnlMain, pnlPrev, pnlRType, pnlB;
    private JScrollPane scroll;
    private String currentReportType = "";
    private int    currentReportDbId  = -1;
    private final String NURSE_NAME   = "Nurse on Duty";
    private final String DOCTOR_NAME  = "Attending Doctor";

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
        lblType.setBounds(275, 20, 400, 50);
        lblType.setFont(new Font("Calibri", Font.BOLD, 28));
        lblType.setForeground(Color.BLACK);
        pnlMain.add(lblType);

        lbltext = new JLabel("View and manage patient appointment history reports.");
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

        btnVitals = new JButton("Appointment History");
        btnVitals.setBounds(20, 70, 400, 80);
        btnVitals.setFont(new Font("Calibri", Font.BOLD, 18));
        btnVitals.setBackground(Color.WHITE);
        btnVitals.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnVitals.setFocusPainted(false);
        btnVitals.addActionListener(this);
        pnlRType.add(btnVitals);

        pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 110, 1100, 800);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);

        lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 200, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlB.add(lblPrev);

        btnMComplete = new JButton("Complete");
        btnMComplete.setBounds(350, 25, 100, 30);
        btnMComplete.setBackground(Green);
        btnMComplete.setForeground(Color.WHITE);
        btnMComplete.setFont(new Font("Calibri", Font.BOLD, 12));
        btnMComplete.setFocusPainted(false);
        btnMComplete.addActionListener(e -> setStatus("Complete"));
        pnlB.add(btnMComplete);

        btnMIncomplete = new JButton("Incomplete");
        btnMIncomplete.setBounds(470, 25, 100, 30);
        btnMIncomplete.setBackground(Yellow);
        btnMIncomplete.setForeground(Color.BLACK);
        btnMIncomplete.setFont(new Font("Calibri", Font.BOLD, 12));
        btnMIncomplete.setFocusPainted(false);
        btnMIncomplete.addActionListener(e -> setStatus("Incomplete"));
        pnlB.add(btnMIncomplete);

        btnMMissing = new JButton("Missing");
        btnMMissing.setBounds(590, 25, 100, 30);
        btnMMissing.setBackground(LightRed);
        btnMMissing.setForeground(Color.WHITE);
        btnMMissing.setFont(new Font("Calibri", Font.BOLD, 12));
        btnMMissing.setFocusPainted(false);
        btnMMissing.addActionListener(e -> setStatus("Missing"));
        pnlB.add(btnMMissing);

        lblStatus = new JLabel("Status: No report selected");
        lblStatus.setBounds(850, 25, 250, 28);
        lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
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
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 13));
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(e -> editReport());
        pnlB.add(btnEdit);

        btnSend = new JButton("Send to Doctor");
        btnSend.setBounds(660, 730, 170, 35);
        btnSend.setBackground(Green);
        btnSend.setForeground(Color.BLACK);
        btnSend.setFont(new Font("Calibri", Font.BOLD, 13));
        btnSend.setFocusPainted(false);
        btnSend.addActionListener(e -> sendReport());
        pnlB.add(btnSend);

        btnPrint = new JButton("Print Report");
        btnPrint.setBounds(840, 730, 180, 35);
        btnPrint.setBackground(LightGray);
        btnPrint.setForeground(Color.BLACK);
        btnPrint.setFont(new Font("Calibri", Font.BOLD, 13));
        btnPrint.setFocusPainted(false);
        btnPrint.addActionListener(e -> printReport());
        pnlB.add(btnPrint);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        resetTypeButtonStyles();

        if (ae.getSource() == btnVitals) {

            currentReportType = "Appointment History";

            btnVitals.setBackground(lightBlue);

            lblType.setText(" > Appointment History");

            loadOrCreateReport();
        }
    }
    
    private void loadOrCreateReport() {
        NurseAppointment newReport = new NurseAppointment("N/A","N/A","",NURSE_NAME,DOCTOR_NAME);

        int generatedId = NurseAppointmentSQL.saveReport(newReport);

        if (generatedId != -1) {
            currentReportDbId = generatedId;
            renderPreview(newReport);
            applyStatusLabel("Draft");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create report in database.");
            currentReportDbId = -1;
            lblStatus.setText("Status: DB Error");
            lblStatus.setForeground(Color.RED);
        }
    }

    private void renderPreview(NurseAppointment report) {

        pnlPrev.removeAll();

        JLabel lblHeader = new JLabel(currentReportType);
        lblHeader.setBounds(20, 15, 1100, 30);
        lblHeader.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlPrev.add(lblHeader);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 50, 1180, 2);
        pnlPrev.add(sep);

        addInfoRow(pnlPrev, 70, "Report ID:", String.valueOf(currentReportDbId));
        addInfoRow(pnlPrev, 105, "Report Type:", currentReportType);
        addInfoRow(pnlPrev, 140, "Patient Name:", report.getPatientName());
        addInfoRow(pnlPrev, 175, "Patient ID:", report.getPatientId());
        addInfoRow(pnlPrev, 210, "Prepared By:", report.getSentBy());
        addInfoRow(pnlPrev, 245, "Sent To:", report.getSentTo());
        addInfoRow(pnlPrev, 280, "Status:", report.getStatus());

        addInfoRow(
                pnlPrev,
                315,
                "Date:",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a")
                )
        );

        JLabel lblNotes = new JLabel("Notes / Report Data:");
        lblNotes.setBounds(20, 370, 250, 25);
        lblNotes.setFont(new Font("Calibri", Font.BOLD, 14));
        pnlPrev.add(lblNotes);

        JTextArea txtNotes = new JTextArea(
                report.getReportData() == null
                        ? ""
                        : report.getReportData()
        );

        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setEditable(false);
        txtNotes.setFont(new Font("Calibri", Font.PLAIN, 14));

        JScrollPane noteScroll = new JScrollPane(txtNotes);
        noteScroll.setBounds(20, 400, 1180, 250);
        pnlPrev.add(noteScroll);

        pnlPrev.setPreferredSize(new Dimension(1020, 920));

        pnlPrev.revalidate();
        pnlPrev.repaint();

        scroll.getVerticalScrollBar().setValue(0);
    }

    private void addInfoRow(JPanel panel, int y, String labelText, String valueText) {

        JLabel lbl = new JLabel(labelText);
        lbl.setBounds(20, y, 180, 25);
        lbl.setFont(new Font("Calibri", Font.BOLD, 14));
        panel.add(lbl);

        JLabel val = new JLabel(valueText != null ? valueText : "—");
        val.setBounds(220, y, 950, 25);
        val.setFont(new Font("Calibri", Font.PLAIN, 14));
        panel.add(val);
    }

    private void setStatus(String status) {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report type first!");
            return;
        }
        if (currentReportDbId == -1) {
            JOptionPane.showMessageDialog(this, "No report record found. Please reload the report.");
            return;
        }
        applyStatusLabel(status);
    }

    private void applyStatusLabel(String status) {
        lblStatus.setText("Status: " + status);
        switch (status) {
            case "Complete":       lblStatus.setForeground(new Color(0, 150, 0));   break;
            case "Incomplete":     lblStatus.setForeground(new Color(255, 140, 0)); break;
            case "Missing":        lblStatus.setForeground(Color.RED);              break;
            case "Sent to Doctor": lblStatus.setForeground(new Color(0, 100, 200)); break;
            case "Draft":          lblStatus.setForeground(Color.DARK_GRAY);        break;
            default:               lblStatus.setForeground(Color.BLACK);
        }
    }

    private void editReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report type first!");
            return;
        }
        if (currentReportDbId == -1) {
            JOptionPane.showMessageDialog(this, "No report record found. Please reload the report.");
            return;
        }

        NurseAppointment report = NurseAppointmentSQL.getReportById(currentReportDbId);
        if (report == null) {
            JOptionPane.showMessageDialog(this, "Could not load report from database.");
            return;
        }

        JPanel editPanel = new JPanel(null);
        editPanel.setPreferredSize(new Dimension(420, 260));

        JLabel lblPN = new JLabel("Patient Name:");
        lblPN.setBounds(10, 10, 130, 25);
        lblPN.setFont(new Font("Calibri", Font.BOLD, 14));
        editPanel.add(lblPN);

        JTextField txtPN = new JTextField(report.getPatientName());
        txtPN.setBounds(150, 10, 260, 28);
        editPanel.add(txtPN);

        JLabel lblPID = new JLabel("Patient ID:");
        lblPID.setBounds(10, 50, 130, 25);
        lblPID.setFont(new Font("Calibri", Font.BOLD, 14));
        editPanel.add(lblPID);

        JTextField txtPID = new JTextField(report.getPatientId());
        txtPID.setBounds(150, 50, 260, 28);
        editPanel.add(txtPID);

        JLabel lblRD = new JLabel("Report Data:");
        lblRD.setBounds(10, 90, 130, 25);
        lblRD.setFont(new Font("Calibri", Font.BOLD, 14));
        editPanel.add(lblRD);

        JTextArea txtRD = new JTextArea(report.getReportData() != null ? report.getReportData() : "");
        txtRD.setLineWrap(true);
        txtRD.setWrapStyleWord(true);
        JScrollPane rdScroll = new JScrollPane(txtRD);
        rdScroll.setBounds(150, 90, 260, 110);
        editPanel.add(rdScroll);

        int result = JOptionPane.showConfirmDialog(
            this, editPanel,
            "Edit Report — ID: " + currentReportDbId,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            report.setPatientName(txtPN.getText().trim());
            report.setPatientId(txtPID.getText().trim());
            report.setReportData(txtRD.getText().trim());

            if (NurseAppointmentSQL.updateReport(report)) {
                JOptionPane.showMessageDialog(this, "Report updated successfully.");
                renderPreview(report);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update report in database.");
            }
        }
    }

    private void sendReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report type first!");
            return;
        }
        if (currentReportDbId == -1) {
            JOptionPane.showMessageDialog(this, "No report record found. Please reload the report.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,"Send \"" + currentReportType + "\" (ID: " + currentReportDbId + ") to doctor?","Confirm Send",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (NurseAppointmentSQL.sendToDoctor(currentReportDbId)) {
                applyStatusLabel("Sent to Doctor");
                JOptionPane.showMessageDialog(this,
                    "Report sent to " + DOCTOR_NAME + " successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to send report. Please try again.");
            }
        }
    }
    
    private void printReport() {
        if (currentReportType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a report type first!");
            return;
        }
        if (currentReportDbId == -1) {
            JOptionPane.showMessageDialog(this, "No report record found. Please reload the report.");
            return;
        }
        try {
            pnlPrev.printAll(null);
            JOptionPane.showMessageDialog(this, "Report sent to printer successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Print error: " + e.getMessage());
        }
    }
    
    private void resetTypeButtonStyles() {
        btnVitals.setBackground(Color.WHITE);
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}