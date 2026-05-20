/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prescriptions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Karl
 */
public class admin_Prescription extends JPanel implements ActionListener {
    
    admin_Prescription(){
        
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        JPanel pnlPrescriptionTable = new JPanel(null);
        pnlPrescriptionTable.setBounds(70, 400, 1060, 500);
        pnlPrescriptionTable.setBackground(Color.WHITE);
        add(pnlPrescriptionTable);
        
        JLabel lblPrescription = new JLabel("Prescription Management");
        lblPrescription.setBounds(20, 20, 400, 30);
        lblPrescription.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlPrescriptionTable.add(lblPrescription);

        String[] col = {"Patient", "Drug", "Dosage", "Frequency", "Duration"};
        DefaultTableModel model = new DefaultTableModel(col, 0);

        model.addRow(new String[]{"John Doe", "Paracetamol", "500mg", "2x/day", "5 days"});

        JTable table = new JTable(model);
        table.setRowHeight(30);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 70, 1020, 300);
        pnlPrescriptionTable.add(sp);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(450, 400, 150, 40);
        btnDelete.setBackground(new Color(0x6F,0xA8,0xDC));
        btnDelete.setForeground(Color.WHITE);
        pnlPrescriptionTable.add(btnDelete);

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(null, "Select a row first!");
            }
        });
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
