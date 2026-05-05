package Dashboard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Doctor_Dashboard extends JPanel implements ActionListener{
    
    //gitnang bahagi
    ArrayList<String[]> prescriptions = new ArrayList<>();
    DefaultTableModel model;
    JTable table;
    JPanel centerPanel;
    JLabel lblName, lblMed, lblStep1, lblStep2, lblStep3 ;
    JComboBox<String> cbDose;
    JTextField txtPat, txtDrugs, txtFreq, txtDur;
    JButton btnAdd, btnDelete, btnUpdate;
    public Doctor_Dashboard() {
        
        setMinimumSize(new Dimension(1000, 1000));
        setLayout(null);
        setLayout(null);
        setBounds(0,0,1620,930);
        setBackground(Color.WHITE);
     
        //gitnang bahagi
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(400, 150, 650, 700);
        centerPanel.setBackground(Color.WHITE);

        lblName = new JLabel("Patient: ");
        lblName.setBounds(20, 10, 50, 30);
        centerPanel.add(lblName);
        
        txtPat = new JTextField();
        txtPat.setBounds(80, 10, 200, 30);
        centerPanel.add(txtPat);
        
        lblMed = new JLabel("Select a drug");
        lblMed.setBounds(20, 60, 75, 30);
        centerPanel.add(lblMed);
        
        txtDrugs = new JTextField();
        txtDrugs.setBounds(105, 60, 200, 30);
        centerPanel.add(txtDrugs);
        
        lblStep1 = new JLabel("Step 1: Dosage");
        lblStep1.setBounds(20, 100, 150, 20);
        centerPanel.add(lblStep1);

        cbDose = new JComboBox<>(new String[]{"Select dosage","5mg", "10mg", "50mg", "100mg", "400mg", "500mg"});
        cbDose.setBounds(20, 130, 120, 30);
        centerPanel.add(cbDose);

        lblStep2 = new JLabel("Step 2: Frequency");
        lblStep2.setBounds(180, 100, 150, 20);
        centerPanel.add(lblStep2);

        txtFreq = new JTextField();
        txtFreq.setBounds(180, 130, 180, 30);
        centerPanel.add(txtFreq);

        lblStep3 = new JLabel("Step 3: Duration");
        lblStep3.setBounds(380, 100, 150, 20);
        centerPanel.add(lblStep3);

        txtDur = new JTextField();
        txtDur.setBounds(380, 130, 120, 30);
        centerPanel.add(txtDur);


        btnAdd = new JButton("Add to List");
        btnAdd.setBounds(20, 170, 150, 30);
        centerPanel.add(btnAdd);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(180, 170, 100, 30);
        centerPanel.add(btnDelete);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(290, 170, 100, 30);
        centerPanel.add(btnUpdate);
       
        String[] col = {"Patient", "Drug", "Dosage", "Frequency", "Duration"};
        model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 220, 580, 200);
        centerPanel.add(sp);
        
        add(centerPanel);
      
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String patient = txtPat.getText();
        String text = txtDrugs.getText();
        Object dosage = cbDose.getSelectedItem();
        String freq = txtFreq.getText();
        String dur = txtDur.getText();
        if(e.getSource() == btnAdd){
        if(patient == null || patient.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Input a name first!");
                return;
            } else if (text == null || text.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input a drug first!");
                return;
            } else if (dosage == null || dosage.toString().equals("Select dosage")) {
                JOptionPane.showMessageDialog(this, "Select a Dosage first!");
                return;
            } else if (freq == null || freq.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input a Frequency first!");
                return;
            } else if (dur == null || dur.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input a Duration first!");
                return;
            } 
            
            String[] rowData = {
                patient,
                text,
                dosage.toString(),
                freq,
                dur
            }; 
            prescriptions.add(rowData);
            model.addRow(rowData);
            
            txtPat.setText("");
            txtDrugs.setText("");
            cbDose.setSelectedIndex(0);
            txtFreq.setText("");
            txtDur.setText("");
        }
        
        if (e.getSource() == btnDelete){
        int row = table.getSelectedRow();

        if (row >= 0) {
        model.removeRow(row);          
        prescriptions.remove(row);     
        } else {
        JOptionPane.showMessageDialog(this, "Select a prescription first!");
        }
        
        }
        
        if (e.getSource() == btnUpdate ){
            int row = table.getSelectedRow();

    if (row >= 0) {
        // updating the table
        model.setValueAt(txtPat.getText(), row, 0);
        model.setValueAt(txtDrugs.getText(), row, 1);
        model.setValueAt(cbDose.getSelectedItem(), row, 2);
        model.setValueAt(txtFreq.getText(), row, 3);
        model.setValueAt(txtDur.getText(), row, 4);

        // updating the array 
        prescriptions.get(row)[0] = txtPat.getText();
        prescriptions.get(row)[1] = txtDrugs.getText();
        prescriptions.get(row)[2] = cbDose.getSelectedItem().toString();
        prescriptions.get(row)[3] = txtFreq.getText();
        prescriptions.get(row)[4] = txtDur.getText();

    } else {
        JOptionPane.showMessageDialog(this, "Select a prescription to update!");
    }
        }
    }
    
}


