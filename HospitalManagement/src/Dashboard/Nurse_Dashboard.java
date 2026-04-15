package Dashboard;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;
import javax.swing.*;

public class Nurse_Dashboard extends JPanel{
    
    private JPanel pnlMain;
    private JLabel lblView;
    
    public Nurse_Dashboard() {
        setLayout(null);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JLabel lblMainPlaceholder = new JLabel("NURSE Dashboard");
        lblMainPlaceholder.setBounds(50, 45, 300, 30);
        lblMainPlaceholder.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlMain.add(lblMainPlaceholder);
        
        JPanel pnlPatients = new JPanel();
        pnlPatients.setLayout(null);
        pnlPatients.setBounds(70, 100, 280, 120);
        pnlPatients.setBackground(LightRed);
        pnlMain.add(pnlPatients);
        
        JLabel lblPatients = new JLabel("Patients Assigned");
        lblPatients.setBounds(15, 15, 200, 20);
        lblPatients.setFont(new Font("Calibri", Font.BOLD, 22));
        lblPatients.setForeground(Color.WHITE);
        pnlPatients.add(lblPatients);
        
        JLabel lblPCount = new JLabel("24");
        lblPCount.setBounds(190, 65, 200, 40);
        lblPCount.setForeground(Color.WHITE);
        lblPCount.setFont(new Font("Calibri", Font.BOLD, 32));
        pnlPatients.add(lblPCount);
        
        JPanel pnlAppointment = new JPanel();
        pnlAppointment.setLayout(null);
        pnlAppointment.setBounds(370, 100, 280, 120);
        pnlAppointment.setBackground(Blue);
        pnlMain.add(pnlAppointment);

        JLabel lblATitle = new JLabel("Today's Tasks");
        lblATitle.setBounds(15, 15, 200, 20);
        lblATitle.setForeground(Color.WHITE);
        lblATitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlAppointment.add(lblATitle);

        JLabel lblACount = new JLabel("12");
        lblACount.setBounds(190,65, 200, 40);
        lblACount.setForeground(Color.WHITE);
        lblACount.setFont(new Font("Calibri", Font.BOLD, 32));
        pnlAppointment.add(lblACount);
        
        JPanel pnlPrescription = new JPanel();
        pnlPrescription.setLayout(null);
        pnlPrescription.setBounds(670, 100, 280, 120);
        pnlPrescription.setBackground(Yellow);
        pnlMain.add(pnlPrescription);

        JLabel lblPrTitle = new JLabel("Medication to Administer");
        lblPrTitle.setBounds(15, 15, 250, 20);
        lblPrTitle.setForeground(Color.WHITE);
        lblPrTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlPrescription.add(lblPrTitle);

        JLabel lblPrCount = new JLabel("8");
        lblPrCount.setBounds(190,65, 200, 40);
        lblPrCount.setForeground(Color.WHITE);
        lblPrCount.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlPrescription.add(lblPrCount);
        
        JPanel pnlCritical = new JPanel();
        pnlCritical.setLayout(null);
        pnlCritical.setBounds(970, 100, 280, 120);
        pnlCritical.setBackground(Green);
        pnlMain.add(pnlCritical);

        JLabel lblCTitle = new JLabel("Critical Cases");
        lblCTitle.setBounds(15, 15, 200, 20);
        lblCTitle.setForeground(Color.WHITE);
        lblCTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        pnlCritical.add(lblCTitle);

        JLabel lblCCount = new JLabel("3");
        lblCCount.setBounds(190, 65, 200, 40);
        lblCCount.setForeground(Color.WHITE);
        lblCCount.setFont(new Font("Calibri", Font.BOLD, 28));
        pnlCritical.add(lblCCount);
    }
}
