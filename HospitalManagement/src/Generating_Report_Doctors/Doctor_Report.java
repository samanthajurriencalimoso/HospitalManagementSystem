package Generating_Report_Doctors;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Doctor_Report extends JPanel implements ActionListener {

    private JButton btnAppointment, btnSave;
    private JLabel lblType, lblStatus, lblDoctor, lblDT, lbltext, lblRType, lblPrev;
    private JPanel pnlMain, pnlPrev, pnlRType, pnlB;
    private JScrollPane scroll;

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
        pnlMain.add(lblDoctor);

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

        lblType = new JLabel();
        lblType.setBounds(210, 20, 400, 50);
        lblType.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlMain.add(lblType);

        lbltext = new JLabel("Manage appointment history documents.");
        lbltext.setBounds(30, 60, 500, 30);
        lbltext.setFont(new Font("Calibri", Font.PLAIN, 18));
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
        btnAppointment.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        pnlRType.add(btnAppointment);

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
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pnlB.add(scroll);

        btnAppointment.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAppointment) {
            pnlPrev.removeAll();
            AppointmentDoctor ad = new AppointmentDoctor();
            ad.setBounds(0, 0, 1020, 820);
            pnlPrev.setPreferredSize(new Dimension(1020, 820));
            pnlPrev.add(ad);
            pnlPrev.revalidate();
            pnlPrev.repaint();
            scroll.getVerticalScrollBar().setValue(0);

            lblType.setText(" > Appointment History");

            btnSave = new JButton("Save Report");
            btnSave.setBounds(30, 740, 150, 35);
            btnSave.setBackground(Green);
            btnSave.setForeground(Color.WHITE);
            btnSave.addActionListener(e -> ad.saveReport());
            pnlPrev.add(btnSave);
        }
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}
