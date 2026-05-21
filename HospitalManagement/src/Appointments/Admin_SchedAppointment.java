package Appointments;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Admin_SchedAppointment extends JPanel implements ActionListener{

    private JPanel pnlMain, tabPatients, tabUrgent, tabPending, pnlSelection, pnlBot, tabUpdate;
    private DefaultTableModel tblModel;
    private JTextField txtPatient, txtDoc, txtTreatment;
    private JTable tblAppoint;
    private JLabel lblTitle, lblDT, lblPatient, lblDoc, lblTreatment, lblTPatient, lblUCases, lblPAppointments, lblTitle2, lblValue;
    private JButton btnAdd, btnRef, btnAdmit, btnCom, btnUr, btnRev;
    private JScrollPane srcApp;
    
    public Admin_SchedAppointment() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        lblTitle = new JLabel("Appointment Management");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lblTitle);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        
        tabPatients = createTab("Today's Patients", "0", Blue);
        tabPatients.setBounds(30, 80, 250, 100);
        pnlMain.add(tabPatients);
        lblTPatient = (JLabel) tabPatients.getComponent(1);

        tabUrgent = createTab("Urgent Cases", "0", LightRed);
        tabUrgent.setBounds(300, 80, 250, 100);
        pnlMain.add(tabUrgent);
        lblUCases = (JLabel) tabUrgent.getComponent(1);

        tabPending = createTab("Pending Appointments", "0", Yellow);
        tabPending.setBounds(570, 80, 250, 100);
        pnlMain.add(tabPending);
        lblPAppointments = (JLabel) tabPending.getComponent(1);
        
        pnlSelection = new JPanel();
        pnlSelection.setLayout(null);
        pnlSelection.setBackground(Color.WHITE);
        pnlSelection.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlSelection.setBounds(30, 210, 1130, 80);
        pnlMain.add(pnlSelection);
        
        lblPatient = new JLabel("Patient: ");
        lblPatient.setBounds(15, 28, 80, 25);
        lblPatient.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblPatient);
        
        txtPatient = new JTextField();
        txtPatient.setBounds(80, 26, 150, 28);
        pnlSelection.add(txtPatient);
        
        lblDoc = new JLabel("Doctor: ");
        lblDoc.setBounds(260, 28, 80, 25);
        lblDoc.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblDoc);
        
        txtDoc = new JTextField();
        txtDoc.setBounds(320, 26, 150, 28);
        pnlSelection.add(txtDoc);
        
        lblTreatment = new JLabel("Treatment: ");
        lblTreatment.setBounds(500, 28, 100, 25);
        lblTreatment.setFont(new Font("Calibri", Font.BOLD, 16));
        pnlSelection.add(lblTreatment);
        
        txtTreatment = new JTextField();
        txtTreatment.setBounds(580, 26, 150, 28);
        pnlSelection.add(txtTreatment);
        
        btnAdd = new JButton("Add Appointment");
        btnAdd.setBounds(760, 22, 150, 35);
        btnAdd.setBackground(Green);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdd.addActionListener(e -> addAppointment());
        pnlSelection.add(btnAdd);
        
        btnRef = new JButton("Refresh");
        btnRef.setBounds(940, 20, 150, 35);
        btnRef.setBackground(Blue);
        btnRef.setForeground(Color.WHITE);
        btnRef.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRef.addActionListener(e -> RefreshApp());
        pnlSelection.add(btnRef);
        
        String[] clm = {"Patient", "Doctor", "Treatment", "Status"};
        tblModel = new DefaultTableModel(clm, 0);
        tblAppoint = new JTable(tblModel);
        tblAppoint.setRowHeight(35);
        tblAppoint.setFont(new Font("Calibri", Font.PLAIN, 16));
        tblAppoint.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        tblAppoint.getTableHeader().setBackground(lightBlue);
        
        srcApp = new JScrollPane(tblAppoint);
        srcApp.setBounds(30, 310, 1560, 530);
        pnlMain.add(srcApp);
        
        pnlBot = new JPanel();
        pnlBot.setLayout(null);
        pnlBot.setBounds(30, 860, 1560, 50);
        pnlBot.setBackground(Color.WHITE);
        pnlBot.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlBot);
        
        btnAdmit = new JButton("Admit");
        btnAdmit.setBounds(20, 15, 120, 30);
        btnAdmit.setBackground(darkBlue);
        btnAdmit.setForeground(Color.WHITE);
        btnAdmit.addActionListener(e -> updateStatus("Admitted"));
        pnlBot.add(btnAdmit);
        
        btnCom = new JButton("Complete");
        btnCom.setBounds(160, 15, 120, 30);
        btnCom.setBackground(Blue);
        btnCom.setForeground(Color.WHITE);
        btnCom.addActionListener(e -> updateStatus("Complete"));
        pnlBot.add(btnCom);
        
        btnUr = new JButton("Urgent");
        btnUr.setBounds(300, 15, 120, 30);
        btnUr.setBackground(LightRed);
        btnUr.setForeground(Color.WHITE);
        btnUr.addActionListener(ee -> updateStatus("Urgent"));
        pnlBot.add(btnUr);
        
        btnRev = new JButton("Remove");
        btnRev.setBounds(440, 15, 120, 30);
        btnRev.setBackground(darkBlue);
        btnRev.setForeground(Color.WHITE);
        btnRev.addActionListener(ee -> RemoveApp());
        pnlBot.add(btnRev);
        
        addSampData();
    }
    
    private JPanel createTab(String title, String value, Color color) {
       tabUpdate = new JPanel();
       tabUpdate.setLayout(null);
       tabUpdate.setBackground(color);
       
        lblTitle2 = new JLabel(title);
        lblTitle2.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle2.setForeground(Color.WHITE);
        lblTitle2.setBounds(20, 20, 200, 25);
        tabUpdate.add(lblTitle2);
        
       lblValue = new JLabel(value);
        lblValue.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(20, 50, 200, 40);
        tabUpdate.add(lblValue);
        
        return tabUpdate;
    }
    
    private void addAppointment() {
        String patient = txtPatient.getText().trim();
        String doctor = txtDoc.getText().trim();
        String treatment = txtTreatment.getText().trim();
        
        if (patient.isEmpty() || doctor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Patient and Doctor.");
            return;
        }
        
        tblModel.addRow(new Object[]{patient, doctor, treatment, "Pending"});
        txtPatient.setText("");
        txtDoc.setText("");
        txtTreatment.setText("");
        updateSummary();
    }
    
    private void RefreshApp() {
        tblModel.setRowCount(0);
        addSampData();
        JOptionPane.showMessageDialog(this, "Table refreshed!");
    }
    
    private void updateStatus(String status) {
        int row = tblAppoint.getSelectedRow();
        if (row >= 0) {
            tblModel.setValueAt(status, row, 3);
            updateSummary();
        } else {
            JOptionPane.showMessageDialog(this, "Select an Appointment.");
        }
    }
    
    private void RemoveApp() {
        int row = tblAppoint.getSelectedRow();
        if (row >= 0) {
            tblModel.removeRow(row);
            updateSummary();
        }
    }
    
    private void updateSummary() {
        int total = tblModel.getRowCount();
        int urgent = 0, pending = 0;
        for (int i = 0; i < total; i++) {
            String status = tblModel.getValueAt(i, 3).toString();
            if (status.equals("Urgent")) urgent++;
            if (status.equals("Pending")) pending++;
        }
        lblTPatient.setText(String.valueOf(total));
        lblUCases.setText(String.valueOf(urgent));
        lblPAppointments.setText(String.valueOf(pending));
    }
    
    private void addSampData() {
        tblModel.addRow(new Object[]{"Maria Santos", "Dr. Reyes", "Cardiac", "Pending"});
        tblModel.addRow(new Object[]{"Daniel Cruz", "Dr. Villanueva", "Orthopedia", "Pending"});
        tblModel.addRow(new Object[]{"Angela Ramirez", "Dr. Delgado", "Diabetes", "Pending"});
        tblModel.addRow(new Object[]{"Roberto Garcia", "Dr. Mendoza", "Dental", "Pending"});
        tblModel.addRow(new Object[]{"Melvin Mallon", "Dr. Santiago", "General", "Pending"});
        tblModel.addRow(new Object[]{"Joshua Garcia", "Dr. Santiago", "General", "Pending"});
        
        updateSummary();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
