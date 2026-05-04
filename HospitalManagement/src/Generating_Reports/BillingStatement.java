/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generating_Reports;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Jm's Laptop
 */
public class BillingStatement extends JPanel{
    
    public BillingStatement() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        JPanel pnlBS = new JPanel();
        pnlBS.setLayout(null);
        pnlBS.setPreferredSize(new Dimension(1040, 900));
        pnlBS.setBackground(Color.WHITE);
        pnlBS.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        JScrollPane scrBS = new JScrollPane(pnlBS);
        scrBS.setBounds(0, 0, 1060, 650);
        scrBS.setBorder(BorderFactory.createEmptyBorder());
        scrBS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrBS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrBS.getVerticalScrollBar().setUnitIncrement(16);

        add(scrBS);
        
        ImageIcon imgDlogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image imgbg = imgDlogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imgbg));
        lblLogo.setBounds(30, 20, 90, 90);
        pnlBS.add(lblLogo);
        
        JLabel lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 22));
        lblHospital.setBounds(140, 25, 400, 25);
        pnlBS.add(lblHospital);

        JLabel lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAddress.setBounds(140, 50, 400, 18);
        pnlBS.add(lblAddress);

        JLabel lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblContact.setBounds(140, 70, 300, 18);
        pnlBS.add(lblContact);

        JLabel lblTitle = new JLabel("BILLING STATEMENT");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitle.setBounds(740, 25, 280, 25);
        pnlBS.add(lblTitle);

        JLabel lblStatementNo = new JLabel("Statement No.: BS-2026-001");
        lblStatementNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblStatementNo.setBounds(740, 55, 260, 18);
        pnlBS.add(lblStatementNo);

        JLabel lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(740, 75, 260, 18);
        pnlBS.add(lblDate);

        JPanel lineTop = new JPanel();
        lineTop.setBounds(30, 125, 980, 2);
        lineTop.setBackground(neutralGray);
        pnlBS.add(lineTop);

        JPanel pnlPatientBox = new JPanel();
        pnlPatientBox.setLayout(null);
        pnlPatientBox.setBounds(30, 145, 980, 110);
        pnlPatientBox.setBackground(Color.WHITE);
        pnlPatientBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(neutralGray),
                "Patient Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Calibri", Font.BOLD, 14)
        ));
        pnlBS.add(pnlPatientBox);

        JLabel lblPName = new JLabel("Patient Name:");
        lblPName.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPName.setBounds(25, 30, 120, 20);
        pnlPatientBox.add(lblPName);

        JLabel lblPNameVal = new JLabel("Joshua Garcia");
        lblPNameVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPNameVal.setBounds(150, 30, 250, 20);
        pnlPatientBox.add(lblPNameVal);

        JLabel lblPID = new JLabel("Patient ID:");
        lblPID.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPID.setBounds(530, 30, 120, 20);
        pnlPatientBox.add(lblPID);

        JLabel lblPIDVal = new JLabel("P-10234");
        lblPIDVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPIDVal.setBounds(650, 30, 250, 20);
        pnlPatientBox.add(lblPIDVal);

        JLabel lblAddress2 = new JLabel("Address:");
        lblAddress2.setFont(new Font("Calibri", Font.BOLD, 14));
        lblAddress2.setBounds(25, 60, 120, 20);
        pnlPatientBox.add(lblAddress2);

        JLabel lblAddressVal = new JLabel("45 San Pedro St., Wellness City");
        lblAddressVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAddressVal.setBounds(150, 60, 300, 20);
        pnlPatientBox.add(lblAddressVal);

        JLabel lblContact2 = new JLabel("Contact No.:");
        lblContact2.setFont(new Font("Calibri", Font.BOLD, 14));
        lblContact2.setBounds(530, 60, 120, 20);
        pnlPatientBox.add(lblContact2);

        JLabel lblContactVal = new JLabel("0917-123-4567");
        lblContactVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblContactVal.setBounds(650, 60, 250, 20);
        pnlPatientBox.add(lblContactVal);

        JPanel pnlBillingBox = new JPanel();
        pnlBillingBox.setLayout(null);
        pnlBillingBox.setBounds(30, 270, 980, 110);
        pnlBillingBox.setBackground(Color.WHITE);
        pnlBillingBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(neutralGray),
                "Billing Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Calibri", Font.BOLD, 14)
        ));
        pnlBS.add(pnlBillingBox);

        JLabel lblRoom = new JLabel("Room No.:");
        lblRoom.setFont(new Font("Calibri", Font.BOLD, 14));
        lblRoom.setBounds(25, 30, 120, 20);
        pnlBillingBox.add(lblRoom);

        JLabel lblRoomVal = new JLabel("101");
        lblRoomVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblRoomVal.setBounds(150, 30, 250, 20);
        pnlBillingBox.add(lblRoomVal);

        JLabel lblDoctor = new JLabel("Doctor:");
        lblDoctor.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDoctor.setBounds(530, 30, 120, 20);
        pnlBillingBox.add(lblDoctor);

        JLabel lblDoctorVal = new JLabel("Dr. Isabella Ramos");
        lblDoctorVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDoctorVal.setBounds(650, 30, 250, 20);
        pnlBillingBox.add(lblDoctorVal);

        JLabel lblPayment = new JLabel("Payment Type:");
        lblPayment.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPayment.setBounds(25, 60, 120, 20);
        pnlBillingBox.add(lblPayment);

        JLabel lblPaymentVal = new JLabel("Cash");
        lblPaymentVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPaymentVal.setBounds(150, 60, 250, 20);
        pnlBillingBox.add(lblPaymentVal);

        JLabel lblAdmit = new JLabel("Date Admitted:");
        lblAdmit.setFont(new Font("Calibri", Font.BOLD, 14));
        lblAdmit.setBounds(530, 60, 120, 20);
        pnlBillingBox.add(lblAdmit);

        JLabel lblAdmitVal = new JLabel("April 15, 2026");
        lblAdmitVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAdmitVal.setBounds(650, 60, 250, 20);
        pnlBillingBox.add(lblAdmitVal);

        JPanel pnlChargesBox = new JPanel();
        pnlChargesBox.setLayout(null);
        pnlChargesBox.setBounds(30, 395, 980, 260);
        pnlChargesBox.setBackground(Color.WHITE);
        pnlChargesBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(neutralGray),
                "Charges",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Calibri", Font.BOLD, 14)
        ));
        pnlBS.add(pnlChargesBox);

        String[] clmPrice = {"Date", "Description", "Qty", "Unit Price", "Amount (PHP)"};

        String[][] rwPrice = {
            {"04/15/2026", "Room Charge", "1", "50,500.00", "50,500.00"},
            {"04/15/2026", "Doctor Fee", "1", "25,000.00", "25,000.00"},
            {"04/15/2026", "Lab Test", "2", "1,500.00", "3,000.00"},
            {"04/15/2026", "Medicines", "3", "2,500.00", "7,500.00"},
            {"04/15/2026", "Other Utilities", "2", "5,000.00", "10,000.00"}
        };

        JTable tblPrice = new JTable(rwPrice, clmPrice);
        tblPrice.setRowHeight(28);
        tblPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
        tblPrice.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));

        JScrollPane scrTable = new JScrollPane(tblPrice);
        scrTable.setBounds(20, 30, 940, 180);
        pnlChargesBox.add(scrTable);

        JLabel lblTotal = new JLabel("Subtotal:");
        lblTotal.setFont(new Font("Calibri", Font.BOLD, 14));
        lblTotal.setBounds(650, 215, 150, 20);
        pnlChargesBox.add(lblTotal);

        JLabel lblPrice = new JLabel("96,000.00");
        lblPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrice.setBounds(800, 215, 150, 20);
        pnlChargesBox.add(lblPrice);

        JLabel lblVat = new JLabel("VAT (12%):");
        lblVat.setFont(new Font("Calibri", Font.BOLD, 14));
        lblVat.setBounds(650, 235, 150, 20);
        pnlChargesBox.add(lblVat);

        JLabel lblVPrice = new JLabel("11,520.00");
        lblVPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblVPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVPrice.setBounds(800, 235, 150, 20);
        pnlChargesBox.add(lblVPrice);

        JPanel pnlTotalBox = new JPanel();
        pnlTotalBox.setLayout(null);
        pnlTotalBox.setBounds(650, 670, 360, 70);
        pnlTotalBox.setBackground(Color.WHITE);
        pnlTotalBox.setBorder(BorderFactory.createLineBorder(neutralGray));
        pnlBS.add(pnlTotalBox);

        JLabel lblAmt = new JLabel("Total Amount Due:");
        lblAmt.setFont(new Font("Calibri", Font.BOLD, 16));
        lblAmt.setBounds(20, 20, 180, 25);
        pnlTotalBox.add(lblAmt);

        JLabel lblTAmt = new JLabel("107,520.00");
        lblTAmt.setFont(new Font("Calibri", Font.BOLD, 16));
        lblTAmt.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTAmt.setBounds(190, 20, 150, 25);
        pnlTotalBox.add(lblTAmt);

        JPanel lineFooter = new JPanel();
        lineFooter.setBounds(30, 800, 980, 2);
        lineFooter.setBackground(neutralGray);
        pnlBS.add(lineFooter);

        JLabel lblPrepared = new JLabel("Prepared by: ____________________");
        lblPrepared.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPrepared.setBounds(60, 825, 300, 20);
        pnlBS.add(lblPrepared);

        JLabel lblSignature = new JLabel("Authorized Signature: ____________________");
        lblSignature.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblSignature.setBounds(650, 825, 350, 20);
        pnlBS.add(lblSignature);

        JLabel lblFooter = new JLabel("Thank you for choosing eTriage Hospital.", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Calibri", Font.ITALIC, 14));
        lblFooter.setBounds(320, 865, 400, 20);
        pnlBS.add(lblFooter);
    }
}
