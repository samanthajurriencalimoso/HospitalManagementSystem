package Patient_Information;

import static Color_Palette.ColorPalette.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientInfo_Nurse extends JPanel implements ActionListener {

    private JPanel pnlMain, profileCard, accent, statusBadge, details, detailsLine, doctorNotes, notesLine,
                   vitals, vitalsLine, emergency, emergencyLine, appointment, appLine;
    private JLabel lbltitle, lblDT, lblPat, name, status, info, lblDetails, lblAdd, lblAddre, lblCnum,
                   lblNum, lblAllergy, lblCondition, lblMed, lblNotes, lblVitals, lblVT, lblval, lblENum ,lblEmergency,
                   lblApp, lblTime, lblT, lblRoom, lblR, lblType, lblTy, lblEName, lblENameVal, lblERelVal, lblENumVal, 
                   lblERel, lblPhoto, lblFullName, lblPatientID, lblPatientAge, lblStatus, lblHRUnitDisplay, lblO2UnitDisplay, lblTempUnitDisplay, lblBPUnitDisplay,
                   lblBlood, lblAddress, lblContact,lblAllergies, lblConditions, lblMedication, lblEEditName, lblEEditRel, lblEEditNum,
                   lblBPValue, lblTempValue, lblSpO2Value, lblHRValue;
    private ImageIcon imgP;
    private Image ImgPT;
    private JButton edit, notes, btnSave, btnCancel, btnUploadPhoto, btnNotesSave, btnNotesClear;
    private JTextArea allergyText, conditionText, medText, notesText, lblDate, lblD, txtAllergies, txtConditions, txtMedication;
    private JTextField txtName, txtAddress, txtContactNum, txtPatientID, txtEName, txtAge, txtERel, txtENum;
    private JFrame editMenu, notesMenu;
    private JComboBox cmbStatus, cmbBlood;
    
    public PatientInfo_Nurse(){
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        //page title
        lbltitle = new JLabel("Patient Profile");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);
        
        lblDT = new JLabel("May 21, 2026 | 10:00 AM");
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);

       //patient profile card
        profileCard = new JPanel();
        profileCard.setLayout(null);
        profileCard.setBounds(30, 80, 1550, 140);
        profileCard.setBackground(Color.WHITE);
        profileCard.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(profileCard);

        //blue accent on card
        accent = new JPanel();
        accent.setBounds(0, 0, 6, 140);
        accent.setBackground(darkBlue);
        profileCard.add(accent);

        imgP = new ImageIcon(getClass().getResource("/resources/Male_Icon.png"));
        ImgPT = imgP.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblPat = new JLabel(new ImageIcon(ImgPT));
        lblPat.setBounds(25, 20, 100, 100);
        profileCard.add(lblPat);
        
        name = new JLabel(" ");
        name.setFont(new Font("Calibri", Font.BOLD, 22));
        name.setBounds(150, 20, 300, 30);
        profileCard.add(name);

        //status badge
        statusBadge = new JPanel();
        statusBadge.setBackground(LightRed);
        statusBadge.setBounds(150, 55, 190, 26);
        statusBadge.setLayout(null);
        profileCard.add(statusBadge);

        status = new JLabel("Status:");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Calibri", Font.BOLD, 18));
        status.setBounds(5, 5, 180, 20);
        statusBadge.add(status);

        info = new JLabel("ID: P-   |   Age:    |   Blood Type: ");
        info.setFont(new Font("Calibri", Font.BOLD, 15));
        info.setForeground(Color.DARK_GRAY);
        info.setBounds(150, 90, 400, 22);
        profileCard.add(info);

        //edit and notes buttons
        edit = new JButton("Edit");
        edit.setBounds(1420, 30, 100, 35);
        edit.setBackground(darkBlue);
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Calibri", Font.BOLD, 14));
        edit.addActionListener(this);
        profileCard.add(edit);

        notes = new JButton("Notes");
        notes.setBounds(1420, 75, 100, 35);
        notes.setBackground(lightBlue);
        notes.setForeground(Color.BLACK);
        notes.setFont(new Font("Calibri", Font.BOLD, 14));
        notes.addActionListener(this);
        profileCard.add(notes);

        //details panel
        details = new JPanel();
        details.setLayout(null);
        details.setBounds(30, 240, 750, 300);
        details.setBackground(Color.WHITE);
        details.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(details);

        lblDetails = new JLabel("Patient Details");
        lblDetails.setFont(new Font("Calibri", Font.BOLD, 22));
        lblDetails.setForeground(darkBlue);
        lblDetails.setBounds(20, 10, 200, 25);
        details.add(lblDetails);

        detailsLine = new JPanel();
        detailsLine.setBounds(20, 38, 710, 2);
        detailsLine.setBackground(lightBlue);
        details.add(detailsLine);

        lblAdd = new JLabel("Address: ");
        lblAdd.setFont(new Font("Calibri", Font.BOLD, 22));
        lblAdd.setForeground(darkBlue);
        lblAdd.setBounds(20, 50, 200, 30);
        details.add(lblAdd);
        
        lblAddre = new JLabel(" ");
        lblAddre.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblAddre.setForeground(darkBlue);
        lblAddre.setBounds(200, 50, 200, 30);
        details.add(lblAddre);
        
        lblCnum = new JLabel("Contact Number: ");
        lblCnum.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCnum.setForeground(darkBlue);
        lblCnum.setBounds(20, 80, 200, 30);
        details.add(lblCnum);
        
        lblNum = new JLabel(" ");
        lblNum.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNum.setForeground(darkBlue);
        lblNum.setBounds(200, 80, 200, 30);
        details.add(lblNum);

        //patient allergy
        lblAllergy = new JLabel("Allergies");
        lblAllergy.setFont(new Font("Calibri", Font.BOLD, 22));
        lblAllergy.setForeground(LightRed);
        lblAllergy.setBounds(20, 150, 150, 22);
        details.add(lblAllergy);

        allergyText = new JTextArea("• \n• \n• ");
        allergyText.setBounds(20, 185, 200, 80);
        allergyText.setEditable(false);
        allergyText.setOpaque(false);
        allergyText.setFont(new Font("Calibri", Font.PLAIN, 22));
        details.add(allergyText);

        // patient conditions
        lblCondition = new JLabel("Conditions");
        lblCondition.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCondition.setForeground(darkBlue);
        lblCondition.setBounds(250, 150, 150, 22);
        details.add(lblCondition);

        conditionText = new JTextArea("• \n• \n• ");
        conditionText.setBounds(250, 185, 200, 80);
        conditionText.setEditable(false);
        conditionText.setOpaque(false);
        conditionText.setFont(new Font("Calibri", Font.PLAIN, 22));
        details.add(conditionText);

        //patient medication
        lblMed = new JLabel("Medication Intake");
        lblMed.setFont(new Font("Calibri", Font.BOLD, 20));
        lblMed.setForeground(darkBlue);
        lblMed.setBounds(480, 150, 200, 22);
        details.add(lblMed);

        medText = new JTextArea("• \n• \n• ");
        medText.setBounds(480, 185, 230, 80);
        medText.setEditable(false);
        medText.setOpaque(false);
        medText.setFont(new Font("Calibri", Font.PLAIN, 22));
        details.add(medText);

        // doctor notes
        doctorNotes = new JPanel();
        doctorNotes.setLayout(null);
        doctorNotes.setBounds(30, 560, 750, 340);
        doctorNotes.setBackground(Color.WHITE);
        doctorNotes.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(doctorNotes);

        lblNotes = new JLabel("Doctor's Notes");
        lblNotes.setFont(new Font("Calibri", Font.BOLD, 22));
        lblNotes.setForeground(darkBlue);
        lblNotes.setBounds(20, 10, 200, 25);
        doctorNotes.add(lblNotes);

        notesLine = new JPanel();
        notesLine.setBounds(20, 38, 710, 2);
        notesLine.setBackground(lightBlue);
        doctorNotes.add(notesLine);

        notesText = new JTextArea("");
        notesText.setFont(new Font("Calibri", Font.PLAIN, 22));
        notesText.setBounds(20, 50, 700, 260);
        notesText.setEditable(false);
        notesText.setOpaque(false);
        notesText.setLineWrap(true);
        notesText.setWrapStyleWord(true);
        doctorNotes.add(notesText);
        
        // patient vitals
        vitals = new JPanel();
        vitals.setLayout(null);
        vitals.setBounds(820, 240, 760, 220);
        vitals.setBackground(Color.WHITE);
        vitals.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(vitals);

        lblVitals = new JLabel("Vitals Snapshot");
        lblVitals.setFont(new Font("Calibri", Font.BOLD, 22));
        lblVitals.setForeground(darkBlue);
        lblVitals.setBounds(20, 10, 200, 25);
        vitals.add(lblVitals);

        vitalsLine = new JPanel();
        vitalsLine.setBounds(20, 38, 710, 2);
        vitalsLine.setBackground(lightBlue);
        vitals.add(vitalsLine);

        int y = 50;
        // blood press
        lblVT = new JLabel("Blood Pressure:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);

        lblBPValue = new JLabel("");
        lblBPValue.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblBPValue.setForeground(darkBlue);
        lblBPValue.setBounds(230, y, 100, 22);
        vitals.add(lblBPValue);

        lblBPUnitDisplay = new JLabel("mmHg");
        lblBPUnitDisplay.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblBPUnitDisplay.setForeground(Color.GRAY);
        lblBPUnitDisplay.setBounds(340, y, 60, 22);
        vitals.add(lblBPUnitDisplay);
        y += 38;
        

        // heart rate
        lblVT = new JLabel("Heart Rate:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);

        lblHRValue = new JLabel("");
        lblHRValue.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblHRValue.setForeground(darkBlue);
        lblHRValue.setBounds(230, y, 100, 22);
        vitals.add(lblHRValue);

        lblHRUnitDisplay = new JLabel("bpm");
        lblHRUnitDisplay.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblHRUnitDisplay.setForeground(Color.GRAY);
        lblHRUnitDisplay.setBounds(340, y, 60, 22);
        vitals.add(lblHRUnitDisplay);
        y += 38;

        // temperature
        lblVT = new JLabel("Temperature:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);

        lblTempValue = new JLabel("");
        lblTempValue.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblTempValue.setForeground(darkBlue);
        lblTempValue.setBounds(230, y, 100, 22);
        vitals.add(lblTempValue);

        lblTempUnitDisplay = new JLabel("°C");
        lblTempUnitDisplay.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblTempUnitDisplay.setForeground(Color.GRAY);
        lblTempUnitDisplay.setBounds(340, y, 60, 22);
        vitals.add(lblTempUnitDisplay);
        
        y += 38;

        // SpO2
        lblVT = new JLabel("SpO2:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);

        lblSpO2Value = new JLabel("");
        lblSpO2Value.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblSpO2Value.setForeground(darkBlue);
        lblSpO2Value.setBounds(230, y, 100, 22);
        vitals.add(lblSpO2Value);

        lblO2UnitDisplay = new JLabel("%");
        lblO2UnitDisplay.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblO2UnitDisplay.setForeground(Color.GRAY);
        lblO2UnitDisplay.setBounds(340, y, 60, 22);
        vitals.add(lblO2UnitDisplay);


        // emergency contact
        emergency = new JPanel();
        emergency.setLayout(null);
        emergency.setBounds(820, 470, 760, 220);
        emergency.setBackground(Color.WHITE);
        emergency.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(emergency);

        lblEmergency = new JLabel("Emergency Contact");
        lblEmergency.setFont(new Font("Calibri", Font.BOLD, 22));
        lblEmergency.setForeground(darkBlue);
        lblEmergency.setBounds(20, 10, 250, 25);
        emergency.add(lblEmergency);

        emergencyLine = new JPanel();
        emergencyLine.setBounds(20, 38, 710, 2);
        emergencyLine.setBackground(lightBlue);
        emergency.add(emergencyLine);
        
        //contact Name
        lblEName = new JLabel("Contact Name:");
        lblEName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblEName.setBounds(20, 60, 200, 30);
        emergency.add(lblEName);

        lblENameVal = new JLabel("");
        lblENameVal.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblENameVal.setForeground(darkBlue);
        lblENameVal.setBounds(255, 60, 500, 30);
        emergency.add(lblENameVal);

        //relationship
        lblERel = new JLabel("Relationship:");
        lblERel.setFont(new Font("Calibri", Font.BOLD, 20));
        lblERel.setBounds(20, 120, 200, 30);
        emergency.add(lblERel);

        lblERelVal = new JLabel("");
        lblERelVal.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblERelVal.setForeground(darkBlue);
        lblERelVal.setBounds(255, 120, 500, 30);
        emergency.add(lblERelVal);

        //contact Number
        lblENum = new JLabel("Contact Number:");
        lblENum.setFont(new Font("Calibri", Font.BOLD, 20));
        lblENum.setBounds(20, 180, 200, 30);
        emergency.add(lblENum);

        lblENumVal = new JLabel("");
        lblENumVal.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblENumVal.setForeground(darkBlue);
        lblENumVal.setBounds(255, 180, 500, 30);
        emergency.add(lblENumVal);

        //appointment
        appointment = new JPanel();
        appointment.setLayout(null);
        appointment.setBounds(820, 710, 760, 190);
        appointment.setBackground(Color.WHITE);
        appointment.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(appointment);

        lblApp = new JLabel("Next Appointment");
        lblApp.setFont(new Font("Calibri", Font.BOLD, 22));
        lblApp.setForeground(darkBlue);
        lblApp.setBounds(20, 15, 300, 30);
        appointment.add(lblApp);

        appLine = new JPanel();
        appLine.setBounds(20, 50, 1500, 2);
        appLine.setBackground(lightBlue);
        appointment.add(appLine);

        lblDate = new JTextArea("Date:");
        lblDate.setFont(new Font("Calibri", Font.BOLD, 20));
        lblDate.setForeground(Color.BLACK);
        lblDate.setBounds(20, 70, 80, 30);
        appointment.add(lblDate);
        
        lblD = new JTextArea("August 26, 2026");
        lblD.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblD.setForeground(Color.BLACK);
        lblD.setBounds(100, 70, 200, 30);
        appointment.add(lblD);
        
        lblTime = new JLabel("Time:");
        lblTime.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTime.setForeground(Color.BLACK);
        lblTime.setBounds(20, 110, 80, 30);
        appointment.add(lblTime);
        
        lblT = new JLabel("9:00 AM");
        lblT.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblT.setForeground(Color.BLACK);
        lblT.setBounds(100, 110, 200, 30);
        appointment.add(lblT);
        
        lblRoom = new JLabel("Room:");
        lblRoom.setFont(new Font("Calibri", Font.BOLD, 20));
        lblRoom.setForeground(Color.BLACK);
        lblRoom.setBounds(300, 70, 80, 30);
        appointment.add(lblRoom);
        
        lblR = new JLabel("101");
        lblR.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblR.setForeground(Color.BLACK);
        lblR.setBounds(380, 70, 200, 30);
        appointment.add(lblR);

        lblType = new JLabel("Type:");
        lblType.setFont(new Font("Calibri", Font.BOLD, 20));
        lblType.setForeground(Color.BLACK);
        lblType.setBounds(300, 110, 80, 30);
        appointment.add(lblType);
        
        lblTy = new JLabel("General Checkup");
        lblTy.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblTy.setForeground(Color.BLACK);
        lblTy.setBounds(380, 110, 300, 30);
        appointment.add(lblTy);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //edit button
        if (e.getSource() == edit) {
            editMenu = new JFrame();
            editMenu.setTitle("Edit Patient Information");
            editMenu.setSize(900, 580);
            editMenu.setLocationRelativeTo(null);
            editMenu.setLayout(null);
            editMenu.setResizable(false);
            editMenu.getContentPane().setBackground(new Color(240, 240, 240));

            //get current info
            String currentInfo = info.getText();
            String currentID = "";
            String currentAge = "";
            
            //enter info text heree
            if (currentInfo.contains("ID:") && currentInfo.contains("Age:") && currentInfo.contains("Blood:")) {
                String[] parts = currentInfo.split("\\|");
                if (parts.length >= 3) {
                    currentID = parts[0].replace("ID:", "").trim();
                    currentAge = parts[1].replace("Age:", "").trim();
                }
            }
            
            //left side of menu
            lblPhoto = new JLabel("Patient Photo:");
            lblPhoto.setBounds(30, 30, 120, 25);
            lblPhoto.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblPhoto);
            
            btnUploadPhoto = new JButton("Upload Photo");
            btnUploadPhoto.setBounds(160, 30, 130, 25);
            btnUploadPhoto.addActionListener(this);
            editMenu.add(btnUploadPhoto);
           
            lblFullName = new JLabel("Full Name:");
            lblFullName.setBounds(30, 75, 120, 25);
            lblFullName.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblFullName);
            
            txtName = new JTextField(name.getText());
            txtName.setBounds(160, 75, 280, 30);
            editMenu.add(txtName);
            
            lblPatientID = new JLabel("Patient ID:");
            lblPatientID.setBounds(30, 120, 120, 25);
            lblPatientID.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblPatientID);
            
            txtPatientID = new JTextField(currentID);
            txtPatientID.setBounds(160, 120, 280, 30);
            editMenu.add(txtPatientID);
            
            lblPatientAge = new JLabel("Age:");
            lblPatientAge.setBounds(30, 165, 120, 25);
            lblPatientAge.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblPatientAge);
            
            txtAge = new JTextField(currentAge);
            txtAge.setBounds(160, 165, 280, 30);
            editMenu.add(txtAge);
            
            lblStatus = new JLabel("Status:");
            lblStatus.setBounds(30, 210, 120, 25);
            lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblStatus);
            
            String[] statuses = {"Newly Admitted","Observation", "Admitted", "Discharged", "Critical", "Stable"};
            cmbStatus = new JComboBox<>(statuses);
            cmbStatus.setBounds(160, 210, 280, 30);
            editMenu.add(cmbStatus);
            
            lblBlood = new JLabel("Blood Type:");
            lblBlood.setBounds(30, 255, 120, 25);
            lblBlood.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblBlood);
            
            String[] bloodTypes = {"Pending","A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-", "Unknown"};
            cmbBlood = new JComboBox<>(bloodTypes);
            cmbBlood.setBounds(160, 255, 280, 30);
            editMenu.add(cmbBlood);
            
            lblAddress = new JLabel("Address:");
            lblAddress.setBounds(30, 300, 120, 25);
            lblAddress.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblAddress);
            
            txtAddress = new JTextField(lblAddre.getText());
            txtAddress.setBounds(160, 300, 280, 30);
            editMenu.add(txtAddress);
            
            lblContact = new JLabel("Contact Number:");
            lblContact.setBounds(30, 345, 120, 25);
            lblContact.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblContact);
            
            txtContactNum = new JTextField(lblNum.getText());
            txtContactNum.setBounds(160, 345, 280, 30);
            editMenu.add(txtContactNum);
            
            //right side of menu
            lblAllergies = new JLabel("Allergies:");
            lblAllergies.setBounds(470, 30, 180, 25);
            lblAllergies.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblAllergies);
            
            txtAllergies = new JTextArea();
            txtAllergies.setBounds(470, 55, 380, 60);
            txtAllergies.setLineWrap(true);
            txtAllergies.setWrapStyleWord(true);
            txtAllergies.setText(allergyText.getText());
            editMenu.add(txtAllergies);
            
            lblConditions = new JLabel("Conditions:");
            lblConditions.setBounds(470, 125, 180, 25);
            lblConditions.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblConditions);
            
            txtConditions = new JTextArea();
            txtConditions.setBounds(470, 150, 380, 60);
            txtConditions.setLineWrap(true);
            txtConditions.setWrapStyleWord(true);
            txtConditions.setText(conditionText.getText());
            editMenu.add(txtConditions);
            
            lblMedication = new JLabel("Medication:");
            lblMedication.setBounds(470, 220, 180, 25);
            lblMedication.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblMedication);
            
            txtMedication = new JTextArea();
            txtMedication.setBounds(470, 245, 380, 60);
            txtMedication.setLineWrap(true);
            txtMedication.setWrapStyleWord(true);
            txtMedication.setText(medText.getText());
            editMenu.add(txtMedication);
            
            //emergency contact part
            lblEEditName = new JLabel("Contact Name:");
            lblEEditName.setBounds(470, 315, 150, 25);
            lblEEditName.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblEEditName);

            txtEName = new JTextField(lblENameVal.getText());
            txtEName.setBounds(630, 315, 220, 30);
            editMenu.add(txtEName);

            lblEEditRel = new JLabel("Relationship:");
            lblEEditRel.setBounds(470, 355, 150, 25);
            lblEEditRel.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblEEditRel);

            txtERel = new JTextField(lblERelVal.getText());
            txtERel.setBounds(630, 355, 220, 30);
            editMenu.add(txtERel);

            lblEEditNum = new JLabel("Contact Number:");
            lblEEditNum.setBounds(470, 395, 150, 25);
            lblEEditNum.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblEEditNum);

            txtENum = new JTextField(lblENumVal.getText());
            txtENum.setBounds(630, 395, 220, 30);
            editMenu.add(txtENum);
            
            //buttons
            btnSave = new JButton("Save Changes");
            btnSave.setBounds(280, 490, 130, 35);
            btnSave.setBackground(darkBlue);
            btnSave.setForeground(Color.WHITE);
            btnSave.addActionListener(this);
            editMenu.add(btnSave);
            
            btnCancel = new JButton("Cancel");
            btnCancel.setBounds(430, 490, 130, 35);
            btnCancel.setBackground(Color.GRAY);
            btnCancel.setForeground(Color.WHITE);
            btnCancel.addActionListener(this);
            editMenu.add(btnCancel);
            
            editMenu.setVisible(true);
        }
        
        //upload photo button (decorative only)
        else if (e.getSource() == btnUploadPhoto) {
            JOptionPane.showMessageDialog(editMenu, "Photo upload feature coming soon!");
        }
        
        //save button action
        else if (e.getSource() == btnSave) {
            //savename
            name.setText(txtName.getText());
            
            //save status
            status.setText("Status: " + cmbStatus.getSelectedItem());
            
            //save info line (ID, Age, Blood)
            String patientID = txtPatientID.getText();
            String patientAge = txtAge.getText();
            String patientBlood = cmbBlood.getSelectedItem().toString();
            info.setText("ID: " + patientID + "   |   Age: " + patientAge + "   |   Blood Type: " + patientBlood);
            
            //save address and contact
            lblAddre.setText(txtAddress.getText());
            lblNum.setText(txtContactNum.getText());
            
            //save medical info
            allergyText.setText(txtAllergies.getText());
            conditionText.setText(txtConditions.getText());
            medText.setText(txtMedication.getText());
            
            //save emergency contact
            lblENameVal.setText(txtEName.getText());
            lblERelVal.setText(txtERel.getText());
            lblENumVal.setText(txtENum.getText());
            
            editMenu.dispose();
            JOptionPane.showMessageDialog(null, "Patient information updated successfully!");
        }
        
        //cancel button action
        else if (e.getSource() == btnCancel) {
            editMenu.dispose();
        }
        
        //notes button
        else if (e.getSource() == notes) {
            JOptionPane.showMessageDialog(null, "Only doctors can edit medical notes.");
        }
    }
}