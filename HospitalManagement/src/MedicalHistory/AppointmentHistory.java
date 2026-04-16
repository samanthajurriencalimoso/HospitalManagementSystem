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
public class AppointmentHistory extends JPanel{
    
    public AppointmentHistory() {
            // For screen size
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(1000, 1000));
        setLayout(null);
        
         // ===================== MAIN CONTENT =====================
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1620, 920);
        mainPanel.setBackground(new Color(0xEAF3F3));
        add(mainPanel);

        // Title
        JLabel lblTitle = new JLabel("Appointment history");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(40, 30, 400, 40);
        mainPanel.add(lblTitle);

        // Cards
        mainPanel.add(createAppointmentCard("Upcoming", new Color(0x5AC8FA), 100));
        mainPanel.add(createAppointmentCard("Completed", new Color(0x4CD964), 250));
        mainPanel.add(createAppointmentCard("Cancelled", new Color(0xFF3B30), 400));
        setVisible(true);

    }
    
        private JPanel createAppointmentCard(String status, Color statusColor, int yPos) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(40, yPos, 1400, 120);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

    // Status label
        JLabel lblStatus = new JLabel(status);
        lblStatus.setOpaque(true);
        lblStatus.setBackground(statusColor);
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setBounds(20, 15, 100, 25);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblStatus);

    // Type
        JLabel type = new JLabel("Type of Appointment");
        type.setBounds(20, 50, 200, 20);
        type.setForeground(Color.GRAY);
        card.add(type);

        JLabel typeVal = new JLabel("Wellness checkup");
        typeVal.setBounds(20, 70, 200, 20);
        card.add(typeVal);

        JLabel complaint = new JLabel("Chief Complaint");
        complaint.setBounds(250, 50, 200, 20);
        complaint.setForeground(Color.GRAY);
        card.add(complaint);

        JLabel complaintVal = new JLabel("Specific symptoms or illness");
        complaintVal.setBounds(250, 70, 250, 20);
        card.add(complaintVal);

    
        JLabel symptoms = new JLabel("Symptoms");
        symptoms.setBounds(550, 50, 150, 20);
        symptoms.setForeground(Color.GRAY);
        card.add(symptoms);

        JLabel symptomsVal = new JLabel("Coughing");
        symptomsVal.setBounds(550, 70, 150, 20);
        card.add(symptomsVal);

        
        JButton btn = new JButton("Show details");
        btn.setBounds(1200, 40, 150, 35);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(100,150,200)));
        card.add(btn);

        return card;

    }
}
