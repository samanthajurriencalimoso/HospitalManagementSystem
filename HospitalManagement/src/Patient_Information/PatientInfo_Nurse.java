package Patient_Information;

import static Color_Palette.ColorPalette.*;
import Database.PatientManagementSQL;
import Database.AppointmentSQL;
import Models.Patient;
import Models.Appointment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class PatientInfo_Nurse extends JPanel implements ActionListener {

    private JPanel pnlMain, profileCard, accent, statusBadge, details, detailsLine, doctorNotes, notesLine,
                   vitals, vitalsLine, emergency, emergencyLine, appointment, appLine;
    private JLabel lbltitle, lblDT, lblPat, name, status, info, lblDetails, lblAdd, lblAddre, lblCnum,
                   lblNum, lblAllergy, lblCondition, lblMed, lblNotes, lblVitals, lblVT, lblval, lblENum, lblEmergency,
                   lblApp, lblTime, lblT, lblRoom, lblR, lblType, lblTy, lblEName, lblENameVal, lblERelVal, lblENumVal,
                   lblERel, lblPhoto, lblFullName, lblPatientID, lblPatientAge, lblStatus, lblHRUnitDisplay, lblO2UnitDisplay,
                   lblTempUnitDisplay, lblBPUnitDisplay, lblBlood, lblAddress, lblContact, lblAllergies, lblConditions,
                   lblMedication, lblEEditName, lblEEditRel, lblEEditNum,
                   lblBPValue, lblTempValue, lblSpO2Value, lblHRValue, lblStatusValue, lblDoctor, lblNurse;
    private ImageIcon imgPatient;
    private Image imgPat;
    private JButton edit, vitalsButton, btnSave, btnCancel, btnUploadPhoto, btnVitalsSave, btnVitalsCancel;
    private JTextArea allergyText, conditionText, medText, notesText, lblDate, lblD, txtAllergies, txtConditions,
                      txtMedication, txtAll, txtCon, txtMed;
    private JTextField txtName, txtAddress, txtContactNum, txtPatientID, txtEName, txtAge, txtERel, txtENum,
                       txtBP, txtHR, txtTemp, txtSpO2;
    private JFrame editMenu, vitalsMenu;
    private JComboBox cmbStatus, cmbBlood;
    private JScrollPane scrAller, scrCon, scrMed;
    private DefaultListModel<String> AModel, CModel, MModel;
    private JList<String> Alist, CList, MList;

    private Patient found;

    public PatientInfo_Nurse() {
        buildUI();
    }

    public PatientInfo_Nurse(String patientName) {
        buildUI();
        loadPatientData(patientName);
    }

    private void buildUI() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        lbltitle = new JLabel("Patient Profile");
        lbltitle.setFont(new Font("Calibri", Font.BOLD, 24));
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBounds(30, 20, 400, 40);
        pnlMain.add(lbltitle);

        lblDT = new JLabel();
        lblDT.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDT.setForeground(Color.darkGray);
        lblDT.setBounds(1390, 20, 400, 40);
        pnlMain.add(lblDT);
        startClockTimer();

        profileCard = new JPanel();
        profileCard.setLayout(null);
        profileCard.setBounds(30, 80, 1550, 140);
        profileCard.setBackground(Color.WHITE);
        profileCard.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(profileCard);

        accent = new JPanel();
        accent.setBounds(0, 0, 6, 140);
        accent.setBackground(darkBlue);
        profileCard.add(accent);

        imgPatient = new ImageIcon(getClass().getResource("/resources/Male_Icon.png"));
        imgPat = imgPatient.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblPat = new JLabel(new ImageIcon(imgPat));
        lblPat.setBounds(25, 20, 100, 100);
        profileCard.add(lblPat);

        name = new JLabel(" ");
        name.setFont(new Font("Calibri", Font.BOLD, 22));
        name.setBounds(150, 20, 400, 30);
        profileCard.add(name);

        statusBadge = new JPanel();
        statusBadge.setBackground(neutralGray);
        statusBadge.setBounds(150, 55, 280, 26);
        statusBadge.setLayout(null);
        profileCard.add(statusBadge);

        status = new JLabel("Status:");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Calibri", Font.BOLD, 18));
        status.setBounds(5, 5, 70, 20);
        statusBadge.add(status);

        lblStatusValue = new JLabel("");
        lblStatusValue.setForeground(Color.WHITE);
        lblStatusValue.setFont(new Font("Calibri", Font.BOLD, 18));
        lblStatusValue.setBounds(80, 5, 190, 20);
        statusBadge.add(lblStatusValue);

        info = new JLabel("ID:    |   Room:    |   Admitted: ");
        info.setFont(new Font("Calibri", Font.BOLD, 15));
        info.setForeground(Color.DARK_GRAY);
        info.setBounds(150, 90, 700, 22);
        profileCard.add(info);

        edit = new JButton("Edit");
        edit.setBounds(1420, 30, 100, 35);
        edit.setBackground(darkBlue);
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Calibri", Font.BOLD, 14));
        edit.addActionListener(this);
        profileCard.add(edit);

        vitalsButton = new JButton("Vitals");
        vitalsButton.setBounds(1420, 75, 100, 35);
        vitalsButton.setBackground(lightBlue);
        vitalsButton.setForeground(Color.BLACK);
        vitalsButton.setFont(new Font("Calibri", Font.BOLD, 14));
        vitalsButton.addActionListener(this);
        profileCard.add(vitalsButton);

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

        lblAdd = new JLabel("Address:");
        lblAdd.setFont(new Font("Calibri", Font.BOLD, 22));
        lblAdd.setForeground(darkBlue);
        lblAdd.setBounds(20, 50, 160, 30);
        details.add(lblAdd);

        lblAddre = new JLabel("N/A");
        lblAddre.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblAddre.setForeground(darkBlue);
        lblAddre.setBounds(190, 50, 530, 30);
        details.add(lblAddre);

        lblCnum = new JLabel("Contact:");
        lblCnum.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCnum.setForeground(darkBlue);
        lblCnum.setBounds(20, 85, 160, 30);
        details.add(lblCnum);

        lblNum = new JLabel("N/A");
        lblNum.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNum.setForeground(darkBlue);
        lblNum.setBounds(190, 85, 530, 30);
        details.add(lblNum);

        lblDoctor = new JLabel("Doctor: ");
        lblDoctor.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblDoctor.setForeground(darkBlue);
        lblDoctor.setBounds(20, 120, 350, 25);
        details.add(lblDoctor);

        lblNurse = new JLabel("Nurse: ");
        lblNurse.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblNurse.setForeground(darkBlue);
        lblNurse.setBounds(20, 148, 350, 25);
        details.add(lblNurse);

        lblAllergy = new JLabel("Allergies");
        lblAllergy.setFont(new Font("Calibri", Font.BOLD, 22));
        lblAllergy.setForeground(LightRed);
        lblAllergy.setBounds(20, 185, 150, 22);
        details.add(lblAllergy);

        AModel = new DefaultListModel<>();
        Alist = new JList<>(AModel);
        Alist.setFont(new Font("Calibri", Font.PLAIN, 18));
        scrAller = new JScrollPane(Alist);
        scrAller.setBounds(20, 210, 200, 70);
        details.add(scrAller);

        lblCondition = new JLabel("Conditions");
        lblCondition.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCondition.setForeground(darkBlue);
        lblCondition.setBounds(240, 185, 150, 22);
        details.add(lblCondition);

        CModel = new DefaultListModel<>();
        CList = new JList<>(CModel);
        CList.setFont(new Font("Calibri", Font.PLAIN, 18));
        scrCon = new JScrollPane(CList);
        scrCon.setBounds(240, 210, 200, 70);
        details.add(scrCon);

        lblMed = new JLabel("Medication Intake");
        lblMed.setFont(new Font("Calibri", Font.BOLD, 20));
        lblMed.setForeground(darkBlue);
        lblMed.setBounds(460, 185, 200, 22);
        details.add(lblMed);

        MModel = new DefaultListModel<>();
        MList = new JList<>(MModel);
        MList.setFont(new Font("Calibri", Font.PLAIN, 18));
        scrMed = new JScrollPane(MList);
        scrMed.setBounds(460, 210, 260, 70);
        details.add(scrMed);

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

        lblVT = new JLabel("Blood Pressure:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);
        lblBPValue = new JLabel("—");
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

        lblVT = new JLabel("Heart Rate:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);
        lblHRValue = new JLabel("—");
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

        lblVT = new JLabel("Temperature:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);
        lblTempValue = new JLabel("—");
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

        lblVT = new JLabel("SpO2:");
        lblVT.setFont(new Font("Calibri", Font.BOLD, 20));
        lblVT.setForeground(Color.DARK_GRAY);
        lblVT.setBounds(20, y, 200, 22);
        vitals.add(lblVT);
        lblSpO2Value = new JLabel("—");
        lblSpO2Value.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblSpO2Value.setForeground(darkBlue);
        lblSpO2Value.setBounds(230, y, 100, 22);
        vitals.add(lblSpO2Value);
        lblO2UnitDisplay = new JLabel("%");
        lblO2UnitDisplay.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblO2UnitDisplay.setForeground(Color.GRAY);
        lblO2UnitDisplay.setBounds(340, y, 60, 22);
        vitals.add(lblO2UnitDisplay);

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

        lblEName = new JLabel("Contact Name:");
        lblEName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblEName.setBounds(20, 60, 200, 30);
        emergency.add(lblEName);
        lblENameVal = new JLabel("N/A");
        lblENameVal.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblENameVal.setForeground(darkBlue);
        lblENameVal.setBounds(255, 60, 480, 30);
        emergency.add(lblENameVal);

        lblERel = new JLabel("Relationship:");
        lblERel.setFont(new Font("Calibri", Font.BOLD, 20));
        lblERel.setBounds(20, 120, 200, 30);
        emergency.add(lblERel);
        lblERelVal = new JLabel("N/A");
        lblERelVal.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblERelVal.setForeground(darkBlue);
        lblERelVal.setBounds(255, 120, 480, 30);
        emergency.add(lblERelVal);

        lblENum = new JLabel("Contact Number:");
        lblENum.setFont(new Font("Calibri", Font.BOLD, 20));
        lblENum.setBounds(20, 180, 200, 30);
        emergency.add(lblENum);
        lblENumVal = new JLabel("N/A");
        lblENumVal.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblENumVal.setForeground(darkBlue);
        lblENumVal.setBounds(255, 180, 480, 30);
        emergency.add(lblENumVal);

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
        appLine.setBounds(20, 50, 720, 2);
        appLine.setBackground(lightBlue);
        appointment.add(appLine);

        lblDate = new JTextArea("Date:");
        lblDate.setFont(new Font("Calibri", Font.BOLD, 20));
        lblDate.setForeground(Color.BLACK);
        lblDate.setBounds(20, 70, 80, 30);
        lblDate.setOpaque(false);
        lblDate.setEditable(false);
        appointment.add(lblDate);

        lblD = new JTextArea("—");
        lblD.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblD.setForeground(Color.BLACK);
        lblD.setBounds(105, 70, 230, 30);
        lblD.setOpaque(false);
        lblD.setEditable(false);
        appointment.add(lblD);

        lblTime = new JLabel("Time:");
        lblTime.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTime.setForeground(Color.BLACK);
        lblTime.setBounds(20, 115, 80, 30);
        appointment.add(lblTime);

        lblT = new JLabel("—");
        lblT.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblT.setForeground(Color.BLACK);
        lblT.setBounds(105, 115, 230, 30);
        appointment.add(lblT);

        lblRoom = new JLabel("Room:");
        lblRoom.setFont(new Font("Calibri", Font.BOLD, 20));
        lblRoom.setForeground(Color.BLACK);
        lblRoom.setBounds(370, 70, 80, 30);
        appointment.add(lblRoom);

        lblR = new JLabel("—");
        lblR.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblR.setForeground(Color.BLACK);
        lblR.setBounds(455, 70, 280, 30);
        appointment.add(lblR);

        lblType = new JLabel("Type:");
        lblType.setFont(new Font("Calibri", Font.BOLD, 20));
        lblType.setForeground(Color.BLACK);
        lblType.setBounds(370, 115, 80, 30);
        appointment.add(lblType);

        lblTy = new JLabel("—");
        lblTy.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblTy.setForeground(Color.BLACK);
        lblTy.setBounds(455, 115, 280, 30);
        appointment.add(lblTy);
    }

    private void loadPatientData(String patientName) {
        List<Patient> patients = PatientManagementSQL.getAllPatients();
        found = null;

        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(patientName)) {
                found = p;
                break;
            }
        }

        if (found == null) {
            JOptionPane.showMessageDialog(this,
                "No patient record found for: " + patientName);
            return;
        }

        name.setText(found.getName());
        lblStatusValue.setText(found.getStatus());

        if ("Critical".equalsIgnoreCase(found.getStatus())) {
            statusBadge.setBackground(LightRed);
        } else if ("Discharged".equalsIgnoreCase(found.getStatus())) {
            statusBadge.setBackground(Blue);
        } else if ("Admitted".equalsIgnoreCase(found.getStatus())) {
            statusBadge.setBackground(Green);
        } else {
            statusBadge.setBackground(neutralGray);
        }

        info.setText("ID: " + found.getId()
                + "  |  Room: " + (found.getRoom() == null || found.getRoom().isEmpty() ? "N/A" : found.getRoom())
                + "  |  Admitted: " + (found.getAdmissionDate() == null || found.getAdmissionDate().isEmpty() ? "N/A" : found.getAdmissionDate()));

        lblDoctor.setText("Doctor: " + (found.getDoctor() == null || found.getDoctor().isEmpty() ? "N/A" : found.getDoctor()));
        lblNurse.setText("Nurse: " + (found.getNurse() == null || found.getNurse().isEmpty() ? "N/A" : found.getNurse()));

        lblAddre.setText(found.getAddress() == null || found.getAddress().isEmpty() ? "N/A" : found.getAddress());
        lblNum.setText(found.getContactNumber() == null || found.getContactNumber().isEmpty() ? "N/A" : found.getContactNumber());

        AModel.clear();
        if (found.getAllergies() != null && !found.getAllergies().isEmpty()) {
            for (String s : found.getAllergies().split("[,\\n]"))
                if (!s.trim().isEmpty()) AModel.addElement(s.trim());
        }
        Alist.setModel(AModel);

        CModel.clear();
        if (found.getConditions() != null && !found.getConditions().isEmpty()) {
            for (String s : found.getConditions().split("[,\\n]"))
                if (!s.trim().isEmpty()) CModel.addElement(s.trim());
        }
        CList.setModel(CModel);

        MModel.clear();
        if (found.getMedications() != null && !found.getMedications().isEmpty()) {
            for (String s : found.getMedications().split("[,\\n]"))
                if (!s.trim().isEmpty()) MModel.addElement(s.trim());
        }
        MList.setModel(MModel);

        lblENameVal.setText(found.getEmergencyName() == null || found.getEmergencyName().isEmpty() ? "N/A" : found.getEmergencyName());
        lblERelVal.setText(found.getEmergencyRel() == null || found.getEmergencyRel().isEmpty() ? "N/A" : found.getEmergencyRel());
        lblENumVal.setText(found.getEmergencyNum() == null || found.getEmergencyNum().isEmpty() ? "N/A" : found.getEmergencyNum());

        notesText.setText(found.getNotes() == null ? "" : found.getNotes());

        lblBPValue.setText(found.getBp() == null || found.getBp().isEmpty() ? "—" : found.getBp());
        lblHRValue.setText(found.getHr() == null || found.getHr().isEmpty() ? "—" : found.getHr());
        lblTempValue.setText(found.getTemp() == null || found.getTemp().isEmpty() ? "—" : found.getTemp());
        lblSpO2Value.setText(found.getSpo2() == null || found.getSpo2().isEmpty() ? "—" : found.getSpo2());

        List<Appointment> allAppts = AppointmentSQL.getAllAppointments();
        Appointment nextAppt = null;
        LocalDate today = LocalDate.now();

        for (Appointment a : allAppts) {
            if (a.getPatientName().equalsIgnoreCase(patientName) && a.getAppointmentDate() != null) {
                String datePart = a.getAppointmentDate().contains(" ")
                        ? a.getAppointmentDate().substring(0, a.getAppointmentDate().indexOf(" "))
                        : a.getAppointmentDate();
                try {
                    LocalDate apptDate = LocalDate.parse(datePart);
                    if (!apptDate.isBefore(today)) {
                        if (nextAppt == null) {
                            nextAppt = a;
                        } else {
                            String existingDatePart = nextAppt.getAppointmentDate().contains(" ")
                                    ? nextAppt.getAppointmentDate().substring(0, nextAppt.getAppointmentDate().indexOf(" "))
                                    : nextAppt.getAppointmentDate();
                            if (apptDate.isBefore(LocalDate.parse(existingDatePart))) {
                                nextAppt = a;
                            }
                        }
                    }
                } catch (Exception ex) { 
                
                }
            }
        }

        if (nextAppt != null) {
            String rawDate = nextAppt.getAppointmentDate();
            String apptDatePart = rawDate.contains(" ") ? rawDate.substring(0, rawDate.indexOf(" ")) : rawDate;
            String apptTimePart = rawDate.contains(" ") ? rawDate.substring(rawDate.indexOf(" ") + 1) : "N/A";
            lblD.setText(apptDatePart);
            lblT.setText(apptTimePart);
            lblR.setText(found.getRoom() == null || found.getRoom().isEmpty() ? "N/A" : found.getRoom());
            lblTy.setText(nextAppt.getTreatment());
        } else {
            lblD.setText("No upcoming appointments");
            lblT.setText("");
            lblR.setText("");
            lblTy.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == edit) {
            editMenu = new JFrame();
            editMenu.setTitle("Edit Patient Information");
            editMenu.setSize(900, 580);
            editMenu.setLocationRelativeTo(null);
            editMenu.setLayout(null);
            editMenu.setResizable(false);
            editMenu.getContentPane().setBackground(new Color(240, 240, 240));

            String currentID  = (found != null && found.getId() != null) ? found.getId() : "";
            String currentAge = (found != null) ? String.valueOf(found.getAge()) : "";

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

            txtAge = new JTextField(currentAge.equals("0") ? "" : currentAge);
            txtAge.setBounds(160, 165, 280, 30);
            editMenu.add(txtAge);

            lblStatus = new JLabel("Status:");
            lblStatus.setBounds(30, 210, 120, 25);
            lblStatus.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblStatus);

            String[] statuses = {"Newly Admitted", "Observation", "Admitted", "Discharged", "Critical", "Stable"};
            cmbStatus = new JComboBox<>(statuses);
            cmbStatus.setSelectedItem(lblStatusValue.getText());
            cmbStatus.setBounds(160, 210, 280, 30);
            editMenu.add(cmbStatus);

            lblBlood = new JLabel("Blood Type:");
            lblBlood.setBounds(30, 255, 120, 25);
            lblBlood.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblBlood);

            String[] bloodTypes = {"Pending", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-", "Unknown"};
            cmbBlood = new JComboBox<>(bloodTypes);
            if (found != null && found.getBloodType() != null) {
                cmbBlood.setSelectedItem(found.getBloodType());
            }
            cmbBlood.setBounds(160, 255, 280, 30);
            editMenu.add(cmbBlood);

            lblAddress = new JLabel("Address:");
            lblAddress.setBounds(30, 300, 120, 25);
            lblAddress.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblAddress);

            txtAddress = new JTextField(lblAddre.getText().equals("N/A") ? "" : lblAddre.getText());
            txtAddress.setBounds(160, 300, 280, 30);
            editMenu.add(txtAddress);

            lblContact = new JLabel("Contact Number:");
            lblContact.setBounds(30, 345, 120, 25);
            lblContact.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblContact);

            txtContactNum = new JTextField(lblNum.getText().equals("N/A") ? "" : lblNum.getText());
            txtContactNum.setBounds(160, 345, 280, 30);
            editMenu.add(txtContactNum);

            lblAllergies = new JLabel("Allergies:");
            lblAllergies.setBounds(470, 30, 180, 25);
            lblAllergies.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblAllergies);

            txtAll = new JTextArea(String.join("\n", Collections.list(AModel.elements())));
            txtAll.setLineWrap(true);
            txtAll.setWrapStyleWord(true);
            scrAller = new JScrollPane(txtAll);
            scrAller.setBounds(470, 55, 380, 60);
            editMenu.add(scrAller);

            lblConditions = new JLabel("Conditions:");
            lblConditions.setBounds(470, 125, 180, 25);
            lblConditions.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblConditions);

            txtCon = new JTextArea(String.join("\n", Collections.list(CModel.elements())));
            txtCon.setLineWrap(true);
            txtCon.setWrapStyleWord(true);
            scrCon = new JScrollPane(txtCon);
            scrCon.setBounds(470, 150, 380, 60);
            editMenu.add(scrCon);

            lblMedication = new JLabel("Medications:");
            lblMedication.setBounds(470, 220, 180, 25);
            lblMedication.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblMedication);

            txtMed = new JTextArea(String.join("\n", Collections.list(MModel.elements())));
            txtMed.setLineWrap(true);
            txtMed.setWrapStyleWord(true);
            scrMed = new JScrollPane(txtMed);
            scrMed.setBounds(470, 245, 380, 60);
            editMenu.add(scrMed);

            lblEEditName = new JLabel("Contact Name:");
            lblEEditName.setBounds(470, 315, 150, 25);
            lblEEditName.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblEEditName);

            txtEName = new JTextField(lblENameVal.getText().equals("N/A") ? "" : lblENameVal.getText());
            txtEName.setBounds(630, 315, 220, 30);
            editMenu.add(txtEName);

            lblEEditRel = new JLabel("Relationship:");
            lblEEditRel.setBounds(470, 355, 150, 25);
            lblEEditRel.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblEEditRel);

            txtERel = new JTextField(lblERelVal.getText().equals("N/A") ? "" : lblERelVal.getText());
            txtERel.setBounds(630, 355, 220, 30);
            editMenu.add(txtERel);

            lblEEditNum = new JLabel("Contact Number:");
            lblEEditNum.setBounds(470, 395, 150, 25);
            lblEEditNum.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblEEditNum);

            txtENum = new JTextField(lblENumVal.getText().equals("N/A") ? "" : lblENumVal.getText());
            txtENum.setBounds(630, 395, 220, 30);
            editMenu.add(txtENum);

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

        } else if (e.getSource() == vitalsButton) {
            vitalsMenu = new JFrame();
            vitalsMenu.setTitle("Edit Vitals Snapshot");
            vitalsMenu.setSize(450, 350);
            vitalsMenu.setLocationRelativeTo(null);
            vitalsMenu.setLayout(null);
            vitalsMenu.setResizable(false);
            vitalsMenu.getContentPane().setBackground(new Color(240, 240, 240));

            JLabel lblBPEdit = new JLabel("Blood Pressure (mmHg):");
            lblBPEdit.setBounds(30, 30, 180, 25);
            lblBPEdit.setFont(new Font("Calibri", Font.PLAIN, 14));
            vitalsMenu.add(lblBPEdit);

            txtBP = new JTextField(lblBPValue.getText().equals("—") ? "" : lblBPValue.getText());
            txtBP.setBounds(220, 30, 150, 25);
            vitalsMenu.add(txtBP);

            JLabel lblHREdit = new JLabel("Heart Rate (bpm):");
            lblHREdit.setBounds(30, 75, 180, 25);
            lblHREdit.setFont(new Font("Calibri", Font.PLAIN, 14));
            vitalsMenu.add(lblHREdit);

            txtHR = new JTextField(lblHRValue.getText().equals("—") ? "" : lblHRValue.getText());
            txtHR.setBounds(220, 75, 150, 25);
            vitalsMenu.add(txtHR);

            JLabel lblTempEdit = new JLabel("Temperature (°C):");
            lblTempEdit.setBounds(30, 120, 180, 25);
            lblTempEdit.setFont(new Font("Calibri", Font.PLAIN, 14));
            vitalsMenu.add(lblTempEdit);

            txtTemp = new JTextField(lblTempValue.getText().equals("—") ? "" : lblTempValue.getText());
            txtTemp.setBounds(220, 120, 150, 25);
            vitalsMenu.add(txtTemp);

            JLabel lblSpO2Edit = new JLabel("SpO2 (%):");
            lblSpO2Edit.setBounds(30, 165, 180, 25);
            lblSpO2Edit.setFont(new Font("Calibri", Font.PLAIN, 14));
            vitalsMenu.add(lblSpO2Edit);

            txtSpO2 = new JTextField(lblSpO2Value.getText().equals("—") ? "" : lblSpO2Value.getText());
            txtSpO2.setBounds(220, 165, 150, 25);
            vitalsMenu.add(txtSpO2);

            btnVitalsSave = new JButton("Save");
            btnVitalsSave.setBounds(100, 230, 100, 35);
            btnVitalsSave.setBackground(darkBlue);
            btnVitalsSave.setForeground(Color.WHITE);
            btnVitalsSave.addActionListener(ev -> {
                String bp = txtBP.getText().trim();
                String hr = txtHR.getText().trim();
                String temp = txtTemp.getText().trim();
                String spo2 = txtSpO2.getText().trim();
                
                if ((!bp.isEmpty() && !bp.matches("\\d+/\\d+") && !bp.matches("\\d+"))
                        || (!hr.isEmpty() && !hr.matches("\\d+"))
                        || (!temp.isEmpty() && !temp.matches("\\d+(\\.\\d+)?"))
                        || (!spo2.isEmpty() && !spo2.matches("\\d+"))) {
                    JOptionPane.showMessageDialog(vitalsMenu,
                            "Please enter valid numbers.\nBP format: e.g., 120/80 or 120",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                found.setBp(bp.isEmpty() ? null : bp);
                found.setHr(hr.isEmpty() ? null : hr);
                found.setTemp(temp.isEmpty() ? null : temp);
                found.setSpo2(spo2.isEmpty() ? null : spo2);

                if (PatientManagementSQL.updatePatientVitals(found)) {
                    lblBPValue.setText(bp.isEmpty() ? "—" : bp);
                    lblHRValue.setText(hr.isEmpty() ? "—" : hr);
                    lblTempValue.setText(temp.isEmpty() ? "—" : temp);
                    lblSpO2Value.setText(spo2.isEmpty() ? "—" : spo2);
                    JOptionPane.showMessageDialog(vitalsMenu, "Vitals saved successfully!");
                    vitalsMenu.dispose();
                } else {
                    JOptionPane.showMessageDialog(vitalsMenu, "Failed to save vitals.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            vitalsMenu.add(btnVitalsSave);

            btnVitalsCancel = new JButton("Cancel");
            btnVitalsCancel.setBounds(230, 230, 100, 35);
            btnVitalsCancel.setBackground(Color.GRAY);
            btnVitalsCancel.setForeground(Color.WHITE);
            btnVitalsCancel.addActionListener(ev -> vitalsMenu.dispose());
            vitalsMenu.add(btnVitalsCancel);

            vitalsMenu.setVisible(true);

        } else if (e.getSource() == btnCancel) {
            editMenu.dispose();

        } else if (e.getSource() == btnSave) {
            String errors = "";

            String nameText = txtName.getText().trim();
            if (nameText.isEmpty()) {
                errors += "- Name is required\n";
            } else if (!nameText.matches("[a-zA-Z\\s]+")) {
                errors += "- Name should only contain letters and spaces\n";
            }

            String idText = txtPatientID.getText().trim();
            if (idText.isEmpty()) {
                errors += "- Patient ID is required\n";
            }

            String ageText = txtAge.getText().trim();
            if (!ageText.isEmpty() && !ageText.matches("\\d+")) {
                errors += "- Age should only contain numbers\n";
            } else if (!ageText.isEmpty()) {
                int age = Integer.parseInt(ageText);
                if (age < 0 || age > 120) errors += "- Age must be between 0 and 120\n";
            }

            String contactText = txtContactNum.getText().trim();
            if (!contactText.isEmpty() && !contactText.matches("\\d+")) {
                errors += "- Contact number should only contain digits\n";
            } else if (!contactText.isEmpty() && contactText.length() != 11) {
                errors += "- Contact number must be exactly 11 digits long\n";
            }

            String emergNameText = txtEName.getText().trim();
            if (!emergNameText.isEmpty() && !emergNameText.matches("[a-zA-Z\\s]+")) {
                errors += "- Emergency contact name should only contain letters and spaces\n";
            }

            String emergNumText = txtENum.getText().trim();
            if (!emergNumText.isEmpty() && !emergNumText.matches("\\d+")) {
                errors += "- Emergency contact number should only contain digits\n";
            } else if (!emergNumText.isEmpty() && emergNumText.length() != 11) {
                errors += "- Emergency contact number must be exactly 11 digits long\n";
            }

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(editMenu, "Please fix the following errors:\n\n" + errors,
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int ageVal = ageText.matches("\\d+") ? Integer.parseInt(ageText) : 0;
            String foundRoom    = (found != null && found.getRoom() != null)          ? found.getRoom()          : "";
            String foundDoctor  = (found != null && found.getDoctor() != null)        ? found.getDoctor()        : "";
            String foundNurse   = (found != null && found.getNurse() != null)         ? found.getNurse()         : "";
            String foundAdmDate = (found != null && found.getAdmissionDate() != null) ? found.getAdmissionDate() : "";

            Patient updated = new Patient(
                idText, nameText, foundRoom, foundDoctor, foundNurse,
                cmbStatus.getSelectedItem().toString(), foundAdmDate,
                txtAddress.getText().trim(), contactText,
                txtAll.getText().trim(), txtCon.getText().trim(), txtMed.getText().trim(),
                found != null ? found.getSurgicalHistory()    : "",
                found != null ? found.getFamilyHistory()      : "",
                found != null ? found.getLifestyleDiet()      : "",
                found != null ? found.getMedicalManagement()  : "",
                found != null ? found.getDietBreakfast()      : "",
                found != null ? found.getDietLunch()          : "",
                found != null ? found.getDietDinner()         : "",
                emergNameText, txtERel.getText().trim(), emergNumText,
                cmbBlood.getSelectedItem().toString(), ageVal,
                notesText.getText().trim(),
                found.getBp(), found.getHr(), found.getTemp(), found.getSpo2(),
                found.getGeneralAppearance(), found.getCardiovascular(),
                found.getRespiratory(), found.getNeurological()
            );

            if (PatientManagementSQL.updatePatientProfile(updated)) {
                name.setText(nameText);
                lblStatusValue.setText(updated.getStatus());
                info.setText("ID: " + idText
                        + "  |  Room: " + (foundRoom.isEmpty() ? "N/A" : foundRoom)
                        + "  |  Admitted: " + (foundAdmDate.isEmpty() ? "N/A" : foundAdmDate));

                String newStatus = updated.getStatus();
                if ("Critical".equalsIgnoreCase(newStatus)) {
                    statusBadge.setBackground(LightRed);
                } else if ("Discharged".equalsIgnoreCase(newStatus)) {
                    statusBadge.setBackground(Blue);
                } else if ("Admitted".equalsIgnoreCase(newStatus)) {
                    statusBadge.setBackground(Green);
                } else {
                    statusBadge.setBackground(neutralGray);
                }

                lblAddre.setText(txtAddress.getText().trim().isEmpty() ? "N/A" : txtAddress.getText().trim());
                lblNum.setText(contactText.isEmpty() ? "N/A" : contactText);
                lblENameVal.setText(emergNameText.isEmpty() ? "N/A" : emergNameText);
                lblERelVal.setText(txtERel.getText().trim().isEmpty() ? "N/A" : txtERel.getText().trim());
                lblENumVal.setText(emergNumText.isEmpty() ? "N/A" : emergNumText);

                AModel.clear();
                for (String s : txtAll.getText().split("[\\n,]"))
                    if (!s.trim().isEmpty()) AModel.addElement(s.trim());
                CModel.clear();
                for (String s : txtCon.getText().split("[\\n,]"))
                    if (!s.trim().isEmpty()) CModel.addElement(s.trim());
                MModel.clear();
                for (String s : txtMed.getText().split("[\\n,]"))
                    if (!s.trim().isEmpty()) MModel.addElement(s.trim());

                Alist.setModel(AModel);
                CList.setModel(CModel);
                MList.setModel(MModel);

                found = updated;

                editMenu.dispose();
                JOptionPane.showMessageDialog(this, "Patient profile saved successfully!");
            } else {
                JOptionPane.showMessageDialog(editMenu, "Failed to save changes to database.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}