/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generating_Reports;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Jm's Laptop
 */
public class Admin_Reports extends JPanel implements ActionListener{
    
    private JButton btnBill, btnRt, btnSf;
    private JLabel lblType;
    private JPanel pnlMain;
    
    public Admin_Reports() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);
        
        JLabel lblAdmin = new JLabel("Admin Reports");
        lblAdmin.setBounds(30, 20, 300, 50);
        lblAdmin.setFont(new Font("Calibri", Font.BOLD, 28));
        lblAdmin.setForeground(Color.BLACK);
        pnlMain.add(lblAdmin);
        
        lblType = new JLabel();
        lblType.setBounds(210, 20, 300, 50);
        lblType.setFont(new Font("Calibri", Font.BOLD, 28));
        lblType.setForeground(Color.BLACK);
        pnlMain.add(lblType);
        
        JLabel lbltext = new JLabel("Manage financial, operational, and compliance documents.");
        lbltext.setBounds(30, 60, 500, 50);
        lbltext.setFont(new Font("Calibri", Font.PLAIN, 20));
        lbltext.setForeground(Color.BLACK);
        pnlMain.add(lbltext);
        
        JPanel pnlRType = new JPanel();
        pnlRType.setLayout(null);
        pnlRType.setBounds(30, 120, 450, 780);
        pnlRType.setBackground(Color.WHITE);
        pnlMain.add(pnlRType);
        
        JLabel lblRType = new JLabel("Select Report Type");
        lblRType.setBounds(30, 20, 240, 30);
        lblRType.setFont(new Font("Calibri", Font.BOLD, 24));
        pnlRType.add(lblRType);
        
        btnBill = new JButton("Biling Statement");
        btnBill.setBounds(20, 70, 400, 80);
        btnBill.setFont(new Font("Calibri", Font.BOLD, 20));
        btnBill.setForeground(Color.BLACK);
        btnBill.setBackground(Color.WHITE);
        btnBill.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnBill.setBorderPainted(true);
        btnBill.setFocusPainted(false);
        btnBill.setContentAreaFilled(true);
        btnBill.setHorizontalAlignment(SwingConstants.CENTER);
        btnBill.setMargin(new Insets(0, 10, 0, 0));
        btnBill.setOpaque(true);
        pnlRType.add(btnBill);
        
        btnRt = new JButton("Official Receipt");
        btnRt.setBounds(20, 170, 400, 80);
        btnRt.setFont(new Font("Calibri", Font.BOLD, 20));
        btnRt.setForeground(Color.BLACK);
        btnRt.setBackground(Color.WHITE);
        btnRt.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnRt.setBorderPainted(true);
        btnRt.setFocusPainted(false);
        btnRt.setContentAreaFilled(true);
        btnRt.setHorizontalAlignment(SwingConstants.CENTER);
        btnRt.setMargin(new Insets(0, 10, 0, 0));
        btnRt.setOpaque(true);
        pnlRType.add(btnRt);
        
        btnSf = new JButton("Staff Attendance");
        btnSf.setBounds(20, 270, 400, 80);
        btnSf.setFont(new Font("Calibri", Font.BOLD, 20));
        btnSf.setForeground(Color.BLACK);
        btnSf.setBackground(Color.WHITE);
        btnSf.setBorder(BorderFactory.createLineBorder(borderLBLUE, 2));
        btnSf.setBorderPainted(true);
        btnSf.setFocusPainted(false);
        btnSf.setContentAreaFilled(true);
        btnSf.setHorizontalAlignment(SwingConstants.CENTER);
        btnSf.setMargin(new Insets(0, 10, 0, 0));
        btnSf.setOpaque(true);
        pnlRType.add(btnSf);
        
        JPanel pnlB = new JPanel();
        pnlB.setLayout(null);
        pnlB.setBounds(500, 120, 1100, 780);
        pnlB.setBackground(Color.WHITE);
        pnlMain.add(pnlB);
        
        JLabel lblPrev = new JLabel("Report Preview");
        lblPrev.setBounds(30, 20, 250, 40);
        lblPrev.setFont(new Font("Calibri", Font.BOLD, 24));
        lblPrev.setForeground(Color.BLACK);
        pnlB.add(lblPrev);
        
        JButton btnEdit = new JButton("Edit Report");
        btnEdit.setBounds(590, 730, 150, 35);
        btnEdit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEdit.setBackground(darkBlue);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFocusPainted(false);
        pnlB.add(btnEdit);
        
        JButton btnSend = new JButton("Send to Patient");
        btnSend.setBounds(760, 730, 150, 35);
        btnSend.setFont(new Font("Calibri", Font.BOLD, 16));
        btnSend.setBackground(Green);
        btnSend.setForeground(Color.BLACK);
        btnSend.setFocusPainted(false);
        pnlB.add(btnSend);
        
        JButton btnEx = new JButton("Export (CSV)");
        btnEx.setBounds(760, 730, 150, 35);
        btnEx.setFont(new Font("Calibri", Font.BOLD, 16));
        btnEx.setBackground(Green);
        btnEx.setForeground(Color.BLACK);
        btnEx.setFocusPainted(false);
        btnEx.setVisible(true);
        pnlB.add(btnEx);
        
        JButton btnPrint = new JButton("Print Report");
        btnPrint.setBounds(930, 730, 150, 35);
        btnPrint.setFont(new Font("Calibri", Font.BOLD, 16));
        btnPrint.setBackground(LightGray);
        btnPrint.setForeground(Color.BLACK);
        btnPrint.setFocusPainted(false);
        pnlB.add(btnPrint);
        
        btnBill.addActionListener(this);
        btnRt.addActionListener(this);
        btnSf.addActionListener(this);
        
        JPanel pnlPrev = new JPanel();
        pnlPrev.setLayout(null);
        pnlPrev.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(pnlPrev);
        scroll.setBounds(20, 70, 1060, 650);
        scroll.setBorder(null);
        pnlB.add(scroll);
        
        btnBill.addActionListener(e -> {
           pnlPrev.removeAll();
           
           BillingStatement billingForm = new BillingStatement();
           billingForm.setBounds(0, 0, 1060, 650);
           
           pnlPrev.add(billingForm);
           pnlPrev.revalidate();
           pnlPrev.repaint();
           
           btnSend.setVisible(true);
           btnEx.setVisible(false);
        });
        
        btnRt.addActionListener(e -> {
           pnlPrev.removeAll();
           
           OfficialReceipt officialR = new OfficialReceipt();
           officialR.setBounds(0, 0, 1060, 650);
           
           pnlPrev.add(officialR);
           pnlPrev.revalidate();
           pnlPrev.repaint();
           
           btnSend.setVisible(true);
           btnEx.setVisible(false);
        });
        
        btnSf.addActionListener(e -> {
           pnlPrev.removeAll();
           
           StaffAttendance StaffAtt = new StaffAttendance();
           StaffAtt.setBounds(0, 0, 1060, 900);
           
           pnlPrev.add(StaffAtt);
           pnlPrev.revalidate();
           pnlPrev.repaint();
           
           btnSend.setVisible(false);
           btnEx.setVisible(true);
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnBill) {
            lblType.setText(String.valueOf(" > Billing Statement"));
        } else if (ae.getSource() == btnRt) {
            lblType.setText(String.valueOf(" > Official Receipt"));
        } else if (ae.getSource() == btnSf) {
            lblType.setText(String.valueOf(" > Staff Attendance"));
        }
    }
}
