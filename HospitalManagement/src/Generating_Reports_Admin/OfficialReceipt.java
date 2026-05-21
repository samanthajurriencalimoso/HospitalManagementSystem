package Generating_Reports_Admin;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class OfficialReceipt extends JPanel {
    
    private JPanel pnlOR, pnlPatient, pnlPayment, sigLine;
    private JLabel lblReceiptNo, lblDate, lblLogo, lblHospital, lblAddress, lblContact, lblPID, lblPIDVal, lblPName, lblPNameVal, lblMethod, 
                   lblMethodVal, lblTrans, lblTransVal, lblReceived, lblCName, lblCashier, lblSignature, lblSigName;
    private JTable tblReceipt;
    private JScrollPane scrOR, scrReceipt;
    private ImageIcon imgLogo;
    private String[] clmReceipt;
    private String[][] rwReceipt;
    
    public OfficialReceipt() {
        setLayout(null);
        setBounds(0, 0, 1060, 650);
        setBackground(Color.WHITE);
        
        pnlOR = new JPanel();
        pnlOR.setLayout(null);
        pnlOR.setPreferredSize(new Dimension(1040, 900));
        pnlOR.setBackground(Color.WHITE);
        pnlOR.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrOR = new JScrollPane(pnlOR);
        scrOR.setBounds(0, 0, 1060, 650);
        scrOR.setBorder(BorderFactory.createEmptyBorder());
        scrOR.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrOR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrOR);

        lblReceiptNo = new JLabel("Receipt No.: OR-2026-0415-001");
        lblReceiptNo.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblReceiptNo.setBounds(30, 25, 260, 20);
        pnlOR.add(lblReceiptNo);

        lblDate = new JLabel("Date: April 15, 2026");
        lblDate.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblDate.setBounds(30, 50, 260, 20);
        pnlOR.add(lblDate);

        imgLogo = new ImageIcon(getClass().getResource("/resources/eTriage.DOCUMENT.LOGO.png"));
        Image img = imgLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(30, 80, 90, 90);
        pnlOR.add(lblLogo);

        lblHospital = new JLabel("ETRIAGE HOSPITAL");
        lblHospital.setFont(new Font("Calibri", Font.BOLD, 20));
        lblHospital.setForeground(darkBlue);
        lblHospital.setBounds(140, 90, 350, 25);
        pnlOR.add(lblHospital);

        lblAddress = new JLabel("123 Health St., Wellness City, Medical County");
        lblAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblAddress.setBounds(140, 115, 400, 20);
        pnlOR.add(lblAddress);

        lblContact = new JLabel("Tel No.: (02) 8123-4567");
        lblContact.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblContact.setBounds(140, 135, 300, 20);
        pnlOR.add(lblContact);

        pnlPatient = new JPanel();
        pnlPatient.setLayout(null);
        pnlPatient.setBounds(30, 190, 470, 120);
        pnlPatient.setBackground(Color.WHITE);
        pnlPatient.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        pnlOR.add(pnlPatient);

        lblPID = new JLabel("Patient ID:");
        lblPID.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPID.setBounds(25, 35, 120, 20);
        pnlPatient.add(lblPID);

        lblPIDVal = new JLabel("P-10234");
        lblPIDVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPIDVal.setBounds(150, 35, 200, 20);
        pnlPatient.add(lblPIDVal);

        lblPName = new JLabel("Patient Name:");
        lblPName.setFont(new Font("Calibri", Font.BOLD, 14));
        lblPName.setBounds(25, 70, 120, 20);
        pnlPatient.add(lblPName);

        lblPNameVal = new JLabel("Joshua Garcia");
        lblPNameVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblPNameVal.setBounds(150, 70, 200, 20);
        pnlPatient.add(lblPNameVal);

        pnlPayment = new JPanel();
        pnlPayment.setLayout(null);
        pnlPayment.setBounds(520, 190, 490, 120);
        pnlPayment.setBackground(Color.WHITE);
        pnlPayment.setBorder(BorderFactory.createTitledBorder("Payment Details"));
        pnlOR.add(pnlPayment);

        lblMethod = new JLabel("Payment Method:");
        lblMethod.setFont(new Font("Calibri", Font.BOLD, 14));
        lblMethod.setBounds(25, 35, 140, 20);
        pnlPayment.add(lblMethod);

        lblMethodVal = new JLabel("Cash");
        lblMethodVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblMethodVal.setBounds(170, 35, 200, 20);
        pnlPayment.add(lblMethodVal);

        lblTrans = new JLabel("Transaction ID:");
        lblTrans.setFont(new Font("Calibri", Font.BOLD, 14));
        lblTrans.setBounds(25, 70, 140, 20);
        pnlPayment.add(lblTrans);

        lblTransVal = new JLabel("TRX-0002563");
        lblTransVal.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblTransVal.setBounds(170, 70, 200, 20);
        pnlPayment.add(lblTransVal);

        clmReceipt = new String[]{"Description", "Amount (PHP)"};
        rwReceipt = new String[][]{
            {"Consultation Fee", "1,500.00"},
            {"Laboratory Fee", "1,200.00"},
            {"Medicine", "800.00"},
            {"Room Charge", "2,500.00"},
            {"TOTAL PAID:", "PHP 6,000.00"},
            {"Amount in Words:", "Six thousand pesos only."}
        };

        tblReceipt = new JTable(rwReceipt, clmReceipt);
        tblReceipt.setFont(new Font("Calibri", Font.PLAIN, 15));
        tblReceipt.setRowHeight(36);
        tblReceipt.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));

        scrReceipt = new JScrollPane(tblReceipt);
        scrReceipt.setBounds(30, 330, 980, 260);
        pnlOR.add(scrReceipt);

        lblReceived = new JLabel("Received by:");
        lblReceived.setFont(new Font("Calibri", Font.BOLD, 14));
        lblReceived.setBounds(40, 625, 200, 20);
        pnlOR.add(lblReceived);
        
        lblCName = new JLabel("Maria Santos");
        lblCName.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblCName.setBounds(40, 645, 200, 45);
        pnlOR.add(lblCName);
        
        lblCashier = new JLabel("Cashier");
        lblCashier.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblCashier.setBounds(40, 670, 200, 45);
        pnlOR.add(lblCashier);

        lblSignature = new JLabel("Signature:");
        lblSignature.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblSignature.setBounds(520, 635, 100, 20);
        pnlOR.add(lblSignature);

        sigLine = new JPanel();
        sigLine.setBounds(650, 665, 300, 2);
        sigLine.setBackground(neutralGray);
        pnlOR.add(sigLine);

        lblSigName = new JLabel("Maria Santos", SwingConstants.CENTER);
        lblSigName.setFont(new Font("Serif", Font.ITALIC, 26));
        lblSigName.setBounds(650, 625, 300, 40);
        pnlOR.add(lblSigName);
    }
}