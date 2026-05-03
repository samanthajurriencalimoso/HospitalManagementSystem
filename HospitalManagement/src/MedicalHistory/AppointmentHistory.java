/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MedicalHistory;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Zaphkiel
 */
public class AppointmentHistory extends JPanel {

    public AppointmentHistory() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1620, 920);
        mainPanel.setOpaque(false);
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        add(mainPanel);

        JLabel lblTitle = new JLabel("Appointment history");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(40, 30, 400, 40);
        mainPanel.add(lblTitle);

        String[] statuses = {"Upcoming", "Completed", "Cancelled"};
        Color[] colors = {new Color(0x5AC8FA), new Color(0x4CD964), new Color(0xFF3B30)};
        
        int startY = 100;
        int gap = 150;

        for (int i = 0; i < statuses.length; i++) {
            int currentY = startY + (i * gap);
            mainPanel.add(createAppointmentCard(statuses[i], colors[i], currentY));
        }

        setVisible(true);
    }

    private JPanel createAppointmentCard(String status, Color statusColor, int yPos) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(40, yPos, 1540, 120);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JLabel lblStatus = new JLabel(status);
        lblStatus.setOpaque(true);
        lblStatus.setBackground(statusColor);
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setBounds(20, 15, 100, 25);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblStatus);

        JLabel type = new JLabel("Type of Appointment");
        type.setBounds(20, 50, 200, 20);
        type.setForeground(Color.GRAY);
        type.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(type);

        JLabel typeVal = new JLabel("Wellness checkup");
        typeVal.setBounds(20, 75, 200, 25);
        typeVal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        card.add(typeVal);

        JLabel complaint = new JLabel("Chief Complaint");
        complaint.setBounds(350, 50, 200, 20);
        complaint.setForeground(Color.GRAY);
        complaint.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(complaint);

        JLabel complaintVal = new JLabel("Specific symptoms or illness");
        complaintVal.setBounds(350, 75, 400, 25);
        complaintVal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        card.add(complaintVal);

        JLabel symptoms = new JLabel("Symptoms");
        symptoms.setBounds(850, 50, 150, 20);
        symptoms.setForeground(Color.GRAY);
        symptoms.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(symptoms);

        JLabel symptomsVal = new JLabel("Coughing");
        symptomsVal.setBounds(850, 75, 150, 25);
        symptomsVal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        card.add(symptomsVal);

        JButton btn = new JButton("Show details");
        btn.setBounds(1370, 40, 150, 35);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200)));
        card.add(btn);

        return card;
    }
}