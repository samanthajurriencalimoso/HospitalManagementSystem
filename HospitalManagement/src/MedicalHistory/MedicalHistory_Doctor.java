package MedicalHistory;

import static Color_Palette.ColorPalette.*;
import Database.PatientManagementSQL;
import Database.UserManagementSQL;
import Models.Patient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class MedicalHistory_Doctor extends JPanel implements ActionListener {

    private JPanel pnlMain, profileCard, accent, statusBadge, pnlWard, details, detailsLine, PhyNotes, notesLine,
                   recommend, appLine;
    private JLabel lbltitle, lblDT, lblPat, name, status, info, lblDiet, lblAll, lblP, lblCD, lblDetails, lblObb,
                   lblSH, lblLP, lblNum, lblFH, lblOb, lblPhy, lblGen, lblGS, lblCar, lblRes, lblApp, lblRC, lblN,lblNC,
                   lblStatusValue, lblLD, lblLS, lblMM, lblMS, lblUpdateTitle, lblBreakfast, lblLunch, lblDinner,
                   lblAllergies, lblChronicConditions, lblSurgicalHistory, lblFamilyHistory, lblRecTitle,
                   lblGeneralAppearance, lblCardiovascular, lblRespiratory, lblNeurological, lblPhyTitle;

    private ImageIcon imgP;
    private Image ImgPT;
    private JButton edit, editPhys, editDiet, btnAddRec, btnSave, btnCancel, 
                      btnUpdateSave, btnUpdateClear, btnUpdateCancel,
                      btnPhysSave, btnPhysCancel;
    private JTable tblDiet;
    private DefaultTableModel dietModel;
    private JTableHeader HdrD;
    private DefaultTableCellRenderer leftRenderer;
    private JScrollPane scrDiet;
    private JFrame editMenu, dietMenu, recMenu, physMenu;
    private JTextField txtAllergies, txtChronicConditions, txtSurgicalHistory, txtFamilyHistory,
                       txtBreakfastFood, txtBreakfastWater, txtBreakfastMed,
                       txtLunchFood, txtLunchWater, txtLunchMed,
                       txtDinnerFood, txtDinnerWater, txtDinnerMed,
                       txtGeneralAppearance, txtCardiovascular, txtRespiratory, txtNeurological;
    private JTextArea txtLifestyle, txtMedManagement;

    private Patient foundPatient = null;

    public MedicalHistory_Doctor() {
        buildUI();
    }

    public MedicalHistory_Doctor(String patientName) {
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

        lbltitle = new JLabel("Medical History (Doctor)");
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

        imgP = new ImageIcon(getClass().getResource("/resources/Male_Icon.png"));
        ImgPT = imgP.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblPat = new JLabel(new ImageIcon(ImgPT));
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

        edit = new JButton("Edit History");
        edit.setBounds(1200, 30, 140, 35);
        edit.setBackground(darkBlue);
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Calibri", Font.BOLD, 14));
        edit.addActionListener(this);
        profileCard.add(edit);

        editPhys = new JButton("Edit Physical Exam");
        editPhys.setBounds(1360, 30, 160, 35);
        editPhys.setBackground(mediumBlue);
        editPhys.setForeground(Color.WHITE);
        editPhys.setFont(new Font("Calibri", Font.BOLD, 14));
        editPhys.addActionListener(this);
        profileCard.add(editPhys);

        editDiet = new JButton("Edit Diet Plan");
        editDiet.setBounds(1175, 75, 160, 35);
        editDiet.setBackground(lightBlue);
        editDiet.setForeground(Color.BLACK);
        editDiet.setFont(new Font("Calibri", Font.BOLD, 14));
        editDiet.addActionListener(this);
        profileCard.add(editDiet);

        btnAddRec = new JButton("Edit Recommendations");
        btnAddRec.setBounds(1360, 75, 200, 35);
        btnAddRec.setBackground(darkBlue);
        btnAddRec.setForeground(Color.WHITE);
        btnAddRec.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAddRec.addActionListener(this);
        profileCard.add(btnAddRec);

        details = new JPanel();
        details.setLayout(null);
        details.setBounds(30, 240, 750, 300);
        details.setBackground(Color.WHITE);
        details.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(details);

        lblDetails = new JLabel("Medical History");
        lblDetails.setFont(new Font("Calibri", Font.BOLD, 22));
        lblDetails.setForeground(darkBlue);
        lblDetails.setBounds(20, 10, 200, 25);
        details.add(lblDetails);

        detailsLine = new JPanel();
        detailsLine.setBounds(20, 38, 710, 2);
        detailsLine.setBackground(lightBlue);
        details.add(detailsLine);

        lblAll = new JLabel("Allergies:");
        lblAll.setFont(new Font("Calibri", Font.BOLD, 22));
        lblAll.setForeground(LightRed);
        lblAll.setBounds(20, 50, 200, 30);
        details.add(lblAll);
        lblP = new JLabel("—");
        lblP.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblP.setBounds(20, 85, 300, 30);
        details.add(lblP);

        lblCD = new JLabel("Chronic Conditions:");
        lblCD.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCD.setForeground(darkBlue);
        lblCD.setBounds(350, 50, 250, 30);
        details.add(lblCD);
        lblObb = new JLabel("—");
        lblObb.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblObb.setBounds(350, 85, 350, 30);
        details.add(lblObb);

        lblSH = new JLabel("Surgical History:");
        lblSH.setFont(new Font("Calibri", Font.BOLD, 22));
        lblSH.setForeground(darkBlue);
        lblSH.setBounds(20, 150, 200, 30);
        details.add(lblSH);
        lblLP = new JLabel("—");
        lblLP.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblLP.setBounds(20, 185, 300, 30);
        details.add(lblLP);

        lblFH = new JLabel("Family History:");
        lblFH.setFont(new Font("Calibri", Font.BOLD, 22));
        lblFH.setForeground(darkBlue);
        lblFH.setBounds(350, 150, 200, 30);
        details.add(lblFH);
        lblOb = new JLabel("—");
        lblOb.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblOb.setBounds(350, 185, 350, 30);
        details.add(lblOb);

        PhyNotes = new JPanel();
        PhyNotes.setLayout(null);
        PhyNotes.setBounds(30, 560, 750, 340);
        PhyNotes.setBackground(Color.WHITE);
        PhyNotes.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(PhyNotes);

        lblPhy = new JLabel("Physical Examination Findings");
        lblPhy.setFont(new Font("Calibri", Font.BOLD, 22));
        lblPhy.setForeground(darkBlue);
        lblPhy.setBounds(20, 15, 400, 25);
        PhyNotes.add(lblPhy);

        notesLine = new JPanel();
        notesLine.setBounds(20, 50, 710, 2);
        notesLine.setBackground(lightBlue);
        PhyNotes.add(notesLine);

        lblGen = new JLabel("General Appearance:");
        lblGen.setFont(new Font("Calibri", Font.BOLD, 20));
        lblGen.setBounds(20, 70, 200, 30);
        PhyNotes.add(lblGen);
        lblGS = new JLabel("—");
        lblGS.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblGS.setBounds(20, 105, 320, 30);
        PhyNotes.add(lblGS);
        
        lblCar = new JLabel("Cardiovascular:");
        lblCar.setFont(new Font("Calibri", Font.BOLD, 20));
        lblCar.setBounds(350, 70, 160, 30);
        PhyNotes.add(lblCar);
        lblNum = new JLabel("—");
        lblNum.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblNum.setBounds(350, 105, 350, 30);
        PhyNotes.add(lblNum);

        lblRes = new JLabel("Respiratory:");
        lblRes.setFont(new Font("Calibri", Font.BOLD, 20));
        lblRes.setBounds(20, 155, 160, 30);
        PhyNotes.add(lblRes);
        lblRC = new JLabel("—");
        lblRC.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblRC.setBounds(20, 190, 320, 30);
        PhyNotes.add(lblRC);

        lblN = new JLabel("Neurological:");
        lblN.setFont(new Font("Calibri", Font.BOLD, 20));
        lblN.setBounds(350, 155, 160, 30);
        PhyNotes.add(lblN);
        lblNC = new JLabel("—");
        lblNC.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblNC.setBounds(350, 190, 350, 30);
        PhyNotes.add(lblNC);

        pnlWard = new JPanel();
        pnlWard.setLayout(null);
        pnlWard.setBounds(800, 240, 790, 300);
        pnlWard.setBackground(Color.WHITE);
        pnlWard.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlWard);

        lblDiet = new JLabel("Diet Plan");
        lblDiet.setFont(new Font("Calibri", Font.BOLD, 22));
        lblDiet.setForeground(darkBlue);
        lblDiet.setBounds(20, 20, 300, 30);
        pnlWard.add(lblDiet);

        String[] clmWard = {"Food", "Water", "Medication"};
        dietModel = new DefaultTableModel(
            new String[][]{
                {"Breakfast: ", "", ""},
                {"Lunch: ",     "", ""},
                {"Dinner: ",    "", ""}
            }, clmWard
        );
        tblDiet = new JTable(dietModel);
        tblDiet.setFont(new Font("Calibri", Font.PLAIN, 14));
        tblDiet.setRowHeight(55);
        tblDiet.setGridColor(Color.LIGHT_GRAY);
        tblDiet.setBackground(Color.WHITE);
        tblDiet.setDefaultEditor(Object.class, null);

        HdrD = tblDiet.getTableHeader();
        HdrD.setFont(new Font("Calibri", Font.BOLD, 14));
        HdrD.setBackground(lightBlue);

        leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        for (int i = 0; i < 3; i++) {
            tblDiet.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
        }
        tblDiet.getColumnModel().getColumn(0).setPreferredWidth(250);
        tblDiet.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblDiet.getColumnModel().getColumn(2).setPreferredWidth(100);

        scrDiet = new JScrollPane(tblDiet);
        scrDiet.setBounds(20, 70, 750, 200);
        scrDiet.setBorder(BorderFactory.createEmptyBorder());
        pnlWard.add(scrDiet);

        recommend = new JPanel();
        recommend.setLayout(null);
        recommend.setBounds(800, 560, 790, 340);
        recommend.setBackground(Color.WHITE);
        recommend.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(recommend);

        lblApp = new JLabel("Recommendations");
        lblApp.setFont(new Font("Calibri", Font.BOLD, 22));
        lblApp.setForeground(darkBlue);
        lblApp.setBounds(20, 15, 300, 30);
        recommend.add(lblApp);

        appLine = new JPanel();
        appLine.setBounds(20, 60, 750, 2);
        appLine.setBackground(lightBlue);
        recommend.add(appLine);

        lblLD = new JLabel("Lifestyle & Diet:");
        lblLD.setFont(new Font("Calibri", Font.BOLD, 20));
        lblLD.setForeground(darkBlue);
        lblLD.setBounds(20, 80, 200, 30);
        recommend.add(lblLD);
        lblLS = new JLabel("—");
        lblLS.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblLS.setBounds(20, 115, 750, 60);
        recommend.add(lblLS);

        lblMM = new JLabel("Medical Management:");
        lblMM.setFont(new Font("Calibri", Font.BOLD, 20));
        lblMM.setForeground(darkBlue);
        lblMM.setBounds(20, 190, 220, 30);
        recommend.add(lblMM);
        lblMS = new JLabel("—");
        lblMS.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblMS.setBounds(20, 225, 750, 60);
        recommend.add(lblMS);
    }

    private void loadPatientData(String patientName) {
        String loggedInDoctor = "Dr. " + UserManagementSQL.currentEmployee.getName();
        List<Patient> patients = PatientManagementSQL.getAllPatients();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(patientName)
                    && p.getDoctor().equalsIgnoreCase(loggedInDoctor)) {
                foundPatient = p;
                break;
            }
        }

        if (foundPatient == null) {
            JOptionPane.showMessageDialog(this,
                "No patient record found for: " + patientName
                + "\nThis patient may not be assigned to you.");
            return;
        }

        name.setText(foundPatient.getName());
        lblStatusValue.setText(foundPatient.getStatus());
        if ("Critical".equalsIgnoreCase(foundPatient.getStatus()))
            statusBadge.setBackground(LightRed);
        else if ("Discharged".equalsIgnoreCase(foundPatient.getStatus()))
            statusBadge.setBackground(Blue);
        else if ("Admitted".equalsIgnoreCase(foundPatient.getStatus()))
            statusBadge.setBackground(Green);
        else
            statusBadge.setBackground(neutralGray);

        info.setText("ID: " + foundPatient.getId()
            + "  |  Room: " + (foundPatient.getRoom() == null || foundPatient.getRoom().isEmpty() ? "N/A" : foundPatient.getRoom())
            + "  |  Admitted: " + (foundPatient.getAdmissionDate() == null || foundPatient.getAdmissionDate().isEmpty() ? "N/A" : foundPatient.getAdmissionDate()));

        lblP.setText(empty(foundPatient.getAllergies()));
        lblObb.setText(empty(foundPatient.getConditions()));
        lblLP.setText(empty(foundPatient.getSurgicalHistory()));
        lblOb.setText(empty(foundPatient.getFamilyHistory()));

        lblGS.setText(empty(foundPatient.getGeneralAppearance()));
        lblNum.setText(empty(foundPatient.getCardiovascular()));
        lblRC.setText(empty(foundPatient.getRespiratory()));
        lblNC.setText(empty(foundPatient.getNeurological()));

        String[] breakfastParts = parseDietString(foundPatient.getDietBreakfast());
        dietModel.setValueAt("Breakfast: " + breakfastParts[0], 0, 0);
        dietModel.setValueAt(breakfastParts[1], 0, 1);
        dietModel.setValueAt(breakfastParts[2], 0, 2);

        String[] lunchParts = parseDietString(foundPatient.getDietLunch());
        dietModel.setValueAt("Lunch: " + lunchParts[0], 1, 0);
        dietModel.setValueAt(lunchParts[1], 1, 1);
        dietModel.setValueAt(lunchParts[2], 1, 2);

        String[] dinnerParts = parseDietString(foundPatient.getDietDinner());
        dietModel.setValueAt("Dinner: " + dinnerParts[0], 2, 0);
        dietModel.setValueAt(dinnerParts[1], 2, 1);
        dietModel.setValueAt(dinnerParts[2], 2, 2);

        lblLS.setText(empty(foundPatient.getLifestyleDiet()));
        lblMS.setText(empty(foundPatient.getMedicalManagement()));
    }

    private String empty(String val) {
        return (val == null || val.trim().isEmpty()) ? "N/A" : val.trim();
    }

    private String[] parseDietString(String combined) {
        if (combined == null || combined.trim().isEmpty() || combined.equals("N/A")) {
            return new String[]{"", "", ""};
        }
        String food = "";
        String water = "";
        String med = "";
        String[] parts = combined.split(" \\| ");
        if (parts.length >= 1) food = parts[0];
        if (parts.length >= 2) {
            String w = parts[1];
            if (w.startsWith("Water: ")) water = w.substring(7);
            else water = w;
        }
        if (parts.length >= 3) {
            String m = parts[2];
            if (m.startsWith("Med: ")) med = m.substring(5);
            else med = m;
        }
        return new String[]{food, water, med};
    }

    private String combineDiet(String food, String water, String med) {
        if (food.isEmpty() && water.isEmpty() && med.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(food);
        if (!water.isEmpty()) sb.append(" | Water: ").append(water);
        if (!med.isEmpty()) sb.append(" | Med: ").append(med);
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == edit) {
            editMenu = new JFrame();
            editMenu.setTitle("Edit Medical History");
            editMenu.setSize(600, 370);
            editMenu.setLocationRelativeTo(null);
            editMenu.setLayout(null);
            editMenu.setResizable(false);
            editMenu.getContentPane().setBackground(new Color(240, 240, 240));

            lblRecTitle = new JLabel("MEDICAL HISTORY");
            lblRecTitle.setBounds(30, 20, 300, 25);
            lblRecTitle.setFont(new Font("Calibri", Font.BOLD, 16));
            lblRecTitle.setForeground(darkBlue);
            editMenu.add(lblRecTitle);

            lblAllergies = new JLabel("Allergies:");
            lblAllergies.setBounds(30, 65, 150, 25);
            lblAllergies.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblAllergies);
            txtAllergies = new JTextField(lblP.getText().equals("N/A") ? "" : lblP.getText());
            txtAllergies.setBounds(200, 65, 360, 30);
            editMenu.add(txtAllergies);

            lblChronicConditions = new JLabel("Chronic Conditions:");
            lblChronicConditions.setBounds(30, 110, 160, 25);
            lblChronicConditions.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblChronicConditions);
            txtChronicConditions = new JTextField(lblObb.getText().equals("N/A") ? "" : lblObb.getText());
            txtChronicConditions.setBounds(200, 110, 360, 30);
            editMenu.add(txtChronicConditions);

            lblSurgicalHistory = new JLabel("Surgical History:");
            lblSurgicalHistory.setBounds(30, 155, 160, 25);
            lblSurgicalHistory.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblSurgicalHistory);
            txtSurgicalHistory = new JTextField(lblLP.getText().equals("N/A") ? "" : lblLP.getText());
            txtSurgicalHistory.setBounds(200, 155, 360, 30);
            editMenu.add(txtSurgicalHistory);

            lblFamilyHistory = new JLabel("Family History:");
            lblFamilyHistory.setBounds(30, 200, 160, 25);
            lblFamilyHistory.setFont(new Font("Calibri", Font.BOLD, 14));
            editMenu.add(lblFamilyHistory);
            txtFamilyHistory = new JTextField(lblOb.getText().equals("N/A") ? "" : lblOb.getText());
            txtFamilyHistory.setBounds(200, 200, 360, 30);
            editMenu.add(txtFamilyHistory);

            btnSave = new JButton("Save Changes");
            btnSave.setBounds(150, 280, 130, 35);
            btnSave.setBackground(darkBlue);
            btnSave.setForeground(Color.WHITE);
            btnSave.addActionListener(this);
            editMenu.add(btnSave);

            btnCancel = new JButton("Cancel");
            btnCancel.setBounds(300, 280, 130, 35);
            btnCancel.setBackground(Color.GRAY);
            btnCancel.setForeground(Color.WHITE);
            btnCancel.addActionListener(this);
            editMenu.add(btnCancel);

            editMenu.setVisible(true);
        }
        else if (e.getSource() == btnSave) {
            if (foundPatient == null) {
                JOptionPane.showMessageDialog(editMenu, "No patient loaded.");
                return;
            }
            String newAllergies  = txtAllergies.getText().trim();
            String newConditions = txtChronicConditions.getText().trim();
            String newSurgical   = txtSurgicalHistory.getText().trim();
            String newFamily     = txtFamilyHistory.getText().trim();

            Patient updated = new Patient(
                foundPatient.getId(), foundPatient.getName(), foundPatient.getRoom(),
                foundPatient.getDoctor(), foundPatient.getNurse(), foundPatient.getStatus(),
                foundPatient.getAdmissionDate(), foundPatient.getAddress(), foundPatient.getContactNumber(),
                newAllergies, newConditions, foundPatient.getMedications(),
                newSurgical, newFamily,
                foundPatient.getLifestyleDiet(), foundPatient.getMedicalManagement(),
                foundPatient.getDietBreakfast(), foundPatient.getDietLunch(), foundPatient.getDietDinner(),
                foundPatient.getEmergencyName(), foundPatient.getEmergencyRel(), foundPatient.getEmergencyNum(),
                foundPatient.getBloodType(), foundPatient.getAge(), foundPatient.getNotes(),
                foundPatient.getBp(), foundPatient.getHr(), foundPatient.getTemp(), foundPatient.getSpo2(),
                foundPatient.getGeneralAppearance(), foundPatient.getCardiovascular(),
                foundPatient.getRespiratory(), foundPatient.getNeurological()
            );

            if (PatientManagementSQL.updatePatientProfile(updated)) {
                foundPatient = updated;
                lblP.setText(empty(newAllergies));
                lblObb.setText(empty(newConditions));
                lblLP.setText(empty(newSurgical));
                lblOb.setText(empty(newFamily));
                editMenu.dispose();
                JOptionPane.showMessageDialog(this, "Medical history saved successfully!");
            } else {
                JOptionPane.showMessageDialog(editMenu, "Failed to save to database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btnCancel) {
            if (editMenu != null) editMenu.dispose();
        }

        else if (e.getSource() == editPhys) {
            if (foundPatient == null) return;

            physMenu = new JFrame();
            physMenu.setTitle("Edit Physical Examination");
            physMenu.setSize(600, 500);
            physMenu.setLocationRelativeTo(null);
            physMenu.setLayout(null);
            physMenu.setResizable(false);
            physMenu.getContentPane().setBackground(new Color(240, 240, 240));

            lblPhyTitle = new JLabel("PHYSICAL EXAMINATION");
            lblPhyTitle.setBounds(30, 20, 300, 25);
            lblPhyTitle.setFont(new Font("Calibri", Font.BOLD, 16));
            lblPhyTitle.setForeground(darkBlue);
            physMenu.add(lblPhyTitle);

            lblGeneralAppearance = new JLabel("General Appearance:");
            lblGeneralAppearance.setBounds(30, 65, 180, 25);
            lblGeneralAppearance.setFont(new Font("Calibri", Font.BOLD, 14));
            physMenu.add(lblGeneralAppearance);
            txtGeneralAppearance = new JTextField(lblGS.getText().equals("N/A") ? "" : lblGS.getText());
            txtGeneralAppearance.setBounds(220, 65, 330, 30);
            physMenu.add(txtGeneralAppearance);

            lblCardiovascular = new JLabel("Cardiovascular:");
            lblCardiovascular.setBounds(30, 115, 180, 25);
            lblCardiovascular.setFont(new Font("Calibri", Font.BOLD, 14));
            physMenu.add(lblCardiovascular);
            txtCardiovascular = new JTextField(lblNum.getText().equals("N/A") ? "" : lblNum.getText());
            txtCardiovascular.setBounds(220, 115, 330, 30);
            physMenu.add(txtCardiovascular);

            lblRespiratory = new JLabel("Respiratory:");
            lblRespiratory.setBounds(30, 165, 180, 25);
            lblRespiratory.setFont(new Font("Calibri", Font.BOLD, 14));
            physMenu.add(lblRespiratory);
            txtRespiratory = new JTextField(lblRC.getText().equals("N/A") ? "" : lblRC.getText());
            txtRespiratory.setBounds(220, 165, 330, 30);
            physMenu.add(txtRespiratory);

            lblNeurological = new JLabel("Neurological:");
            lblNeurological.setBounds(30, 215, 180, 25);
            lblNeurological.setFont(new Font("Calibri", Font.BOLD, 14));
            physMenu.add(lblNeurological);
            txtNeurological = new JTextField(lblNC.getText().equals("N/A") ? "" : lblNC.getText());
            txtNeurological.setBounds(220, 215, 330, 30);
            physMenu.add(txtNeurological);

            btnPhysSave = new JButton("Save Changes");
            btnPhysSave.setBounds(150, 280, 130, 35);
            btnPhysSave.setBackground(darkBlue);
            btnPhysSave.setForeground(Color.WHITE);
            btnPhysSave.addActionListener(this);
            physMenu.add(btnPhysSave);

            btnPhysCancel = new JButton("Cancel");
            btnPhysCancel.setBounds(300, 280, 130, 35);
            btnPhysCancel.setBackground(Color.GRAY);
            btnPhysCancel.setForeground(Color.WHITE);
            btnPhysCancel.addActionListener(this);
            physMenu.add(btnPhysCancel);

            physMenu.setVisible(true);
        }
        else if (e.getSource() == btnPhysSave) {
            if (foundPatient == null) return;

            String ga = txtGeneralAppearance.getText().trim();
            String cv = txtCardiovascular.getText().trim();
            String resp = txtRespiratory.getText().trim();
            String neuro = txtNeurological.getText().trim();

            foundPatient.setGeneralAppearance(ga.isEmpty() ? null : ga);
            foundPatient.setCardiovascular(cv.isEmpty() ? null : cv);
            foundPatient.setRespiratory(resp.isEmpty() ? null : resp);
            foundPatient.setNeurological(neuro.isEmpty() ? null : neuro);

            if (PatientManagementSQL.updatePatientProfile(foundPatient)) {
                lblGS.setText(ga.isEmpty() ? "N/A" : ga);
                lblNum.setText(cv.isEmpty() ? "N/A" : cv);
                lblRC.setText(resp.isEmpty() ? "N/A" : resp);
                lblNC.setText(neuro.isEmpty() ? "N/A" : neuro);
                physMenu.dispose();
                JOptionPane.showMessageDialog(this, "Physical examination updated successfully!");
            } else {
                JOptionPane.showMessageDialog(physMenu, "Failed to save to database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btnPhysCancel) {
            if (physMenu != null) physMenu.dispose();
        }

        else if (e.getSource() == editDiet) {
            if (foundPatient == null) return;

            dietMenu = new JFrame();
            dietMenu.setTitle("Edit Diet Plan");
            dietMenu.setSize(750, 400);
            dietMenu.setLocationRelativeTo(null);
            dietMenu.setLayout(null);
            dietMenu.setResizable(false);
            dietMenu.getContentPane().setBackground(new Color(240, 240, 240));

            lblUpdateTitle = new JLabel("Edit Diet Plan");
            lblUpdateTitle.setFont(new Font("Calibri", Font.BOLD, 18));
            lblUpdateTitle.setForeground(darkBlue);
            lblUpdateTitle.setBounds(30, 20, 200, 30);
            dietMenu.add(lblUpdateTitle);

            JLabel lblColFood = new JLabel("Food");
            lblColFood.setFont(new Font("Calibri", Font.BOLD, 14));
            lblColFood.setBounds(120, 60, 150, 25);
            dietMenu.add(lblColFood);
            JLabel lblColWater = new JLabel("Water");
            lblColWater.setFont(new Font("Calibri", Font.BOLD, 14));
            lblColWater.setBounds(290, 60, 100, 25);
            dietMenu.add(lblColWater);
            JLabel lblColMed = new JLabel("Medication");
            lblColMed.setFont(new Font("Calibri", Font.BOLD, 14));
            lblColMed.setBounds(410, 60, 150, 25);
            dietMenu.add(lblColMed);

            lblBreakfast = new JLabel("Breakfast:");
            lblBreakfast.setBounds(30, 100, 80, 25);
            dietMenu.add(lblBreakfast);
            txtBreakfastFood = new JTextField(extractFoodFromDiet(foundPatient.getDietBreakfast()));
            txtBreakfastFood.setBounds(120, 100, 150, 30);
            dietMenu.add(txtBreakfastFood);
            txtBreakfastWater = new JTextField((String) dietModel.getValueAt(0, 1));
            txtBreakfastWater.setBounds(290, 100, 100, 30);
            dietMenu.add(txtBreakfastWater);
            txtBreakfastMed = new JTextField((String) dietModel.getValueAt(0, 2));
            txtBreakfastMed.setBounds(410, 100, 200, 30);
            dietMenu.add(txtBreakfastMed);

            lblLunch = new JLabel("Lunch:");
            lblLunch.setBounds(30, 160, 80, 25);
            dietMenu.add(lblLunch);
            txtLunchFood = new JTextField(extractFoodFromDiet(foundPatient.getDietLunch()));
            txtLunchFood.setBounds(120, 160, 150, 30);
            dietMenu.add(txtLunchFood);
            txtLunchWater = new JTextField((String) dietModel.getValueAt(1, 1));
            txtLunchWater.setBounds(290, 160, 100, 30);
            dietMenu.add(txtLunchWater);
            txtLunchMed = new JTextField((String) dietModel.getValueAt(1, 2));
            txtLunchMed.setBounds(410, 160, 200, 30);
            dietMenu.add(txtLunchMed);

            lblDinner = new JLabel("Dinner:");
            lblDinner.setBounds(30, 220, 80, 25);
            dietMenu.add(lblDinner);
            txtDinnerFood = new JTextField(extractFoodFromDiet(foundPatient.getDietDinner()));
            txtDinnerFood.setBounds(120, 220, 150, 30);
            dietMenu.add(txtDinnerFood);
            txtDinnerWater = new JTextField((String) dietModel.getValueAt(2, 1));
            txtDinnerWater.setBounds(290, 220, 100, 30);
            dietMenu.add(txtDinnerWater);
            txtDinnerMed = new JTextField((String) dietModel.getValueAt(2, 2));
            txtDinnerMed.setBounds(410, 220, 200, 30);
            dietMenu.add(txtDinnerMed);

            btnUpdateSave = new JButton("Save Changes");
            btnUpdateSave.setBounds(150, 290, 130, 35);
            btnUpdateSave.setBackground(darkBlue);
            btnUpdateSave.setForeground(Color.WHITE);
            btnUpdateSave.addActionListener(this);
            dietMenu.add(btnUpdateSave);

            btnUpdateClear = new JButton("Clear");
            btnUpdateClear.setBounds(300, 290, 130, 35);
            btnUpdateClear.setBackground(LightRed);
            btnUpdateClear.setForeground(Color.WHITE);
            btnUpdateClear.addActionListener(this);
            dietMenu.add(btnUpdateClear);

            btnUpdateCancel = new JButton("Cancel");
            btnUpdateCancel.setBounds(450, 290, 130, 35);
            btnUpdateCancel.setBackground(Color.GRAY);
            btnUpdateCancel.setForeground(Color.WHITE);
            btnUpdateCancel.addActionListener(this);
            dietMenu.add(btnUpdateCancel);

            dietMenu.setVisible(true);
        }
        
        else if (e.getSource() == btnUpdateSave) {
            if (foundPatient == null) return;

            String breakfastFood = txtBreakfastFood.getText().trim();
            String breakfastWater = txtBreakfastWater.getText().trim();
            String breakfastMed = txtBreakfastMed.getText().trim();

            String lunchFood = txtLunchFood.getText().trim();
            String lunchWater = txtLunchWater.getText().trim();
            String lunchMed = txtLunchMed.getText().trim();

            String dinnerFood = txtDinnerFood.getText().trim();
            String dinnerWater = txtDinnerWater.getText().trim();
            String dinnerMed = txtDinnerMed.getText().trim();

            if (breakfastFood.isEmpty() || breakfastWater.isEmpty() || breakfastMed.isEmpty() ||
                lunchFood.isEmpty() || lunchWater.isEmpty() || lunchMed.isEmpty() ||
                dinnerFood.isEmpty() || dinnerWater.isEmpty() || dinnerMed.isEmpty()) {
                JOptionPane.showMessageDialog(dietMenu, "Please fill in all diet fields.");
                return;
            }

            String breakfastFull = combineDiet(breakfastFood, breakfastWater, breakfastMed);
            String lunchFull = combineDiet(lunchFood, lunchWater, lunchMed);
            String dinnerFull = combineDiet(dinnerFood, dinnerWater, dinnerMed);

            foundPatient.setDietBreakfast(breakfastFull);
            foundPatient.setDietLunch(lunchFull);
            foundPatient.setDietDinner(dinnerFull);

            if (PatientManagementSQL.updatePatientProfile(foundPatient)) {
                dietModel.setValueAt("Breakfast: " + breakfastFood, 0, 0);
                dietModel.setValueAt(breakfastWater, 0, 1);
                dietModel.setValueAt(breakfastMed, 0, 2);
                dietModel.setValueAt("Lunch: " + lunchFood, 1, 0);
                dietModel.setValueAt(lunchWater, 1, 1);
                dietModel.setValueAt(lunchMed, 1, 2);
                dietModel.setValueAt("Dinner: " + dinnerFood, 2, 0);
                dietModel.setValueAt(dinnerWater, 2, 1);
                dietModel.setValueAt(dinnerMed, 2, 2);
                dietMenu.dispose();
                JOptionPane.showMessageDialog(this, "Diet plan updated successfully!");
            } else {
                JOptionPane.showMessageDialog(dietMenu, "Failed to save to database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btnUpdateClear) {
            txtBreakfastFood.setText("");
            txtBreakfastWater.setText("");
            txtBreakfastMed.setText("");
            txtLunchFood.setText("");
            txtLunchWater.setText("");
            txtLunchMed.setText("");
            txtDinnerFood.setText("");
            txtDinnerWater.setText("");
            txtDinnerMed.setText("");
        }
        else if (e.getSource() == btnUpdateCancel) {
            if (dietMenu != null) dietMenu.dispose();
        }
        
        else if (e.getSource() == btnAddRec) {
            recMenu = new JFrame();
            recMenu.setTitle("Edit Recommendations");
            recMenu.setSize(600, 400);
            recMenu.setLocationRelativeTo(null);
            recMenu.setLayout(null);
            recMenu.setResizable(false);
            recMenu.getContentPane().setBackground(new Color(240, 240, 240));

            JLabel lblRecTitle2 = new JLabel("RECOMMENDATIONS");
            lblRecTitle2.setBounds(30, 20, 300, 30);
            lblRecTitle2.setFont(new Font("Calibri", Font.BOLD, 16));
            lblRecTitle2.setForeground(darkBlue);
            recMenu.add(lblRecTitle2);

            JLabel lblLifestyleTitle = new JLabel("Lifestyle & Diet:");
            lblLifestyleTitle.setBounds(30, 65, 150, 25);
            lblLifestyleTitle.setFont(new Font("Calibri", Font.BOLD, 14));
            recMenu.add(lblLifestyleTitle);
            txtLifestyle = new JTextArea(lblLS.getText().equals("N/A") ? "" : lblLS.getText());
            txtLifestyle.setLineWrap(true);
            txtLifestyle.setWrapStyleWord(true);
            txtLifestyle.setFont(new Font("Calibri", Font.PLAIN, 14));
            JScrollPane spLS = new JScrollPane(txtLifestyle);
            spLS.setBounds(30, 95, 520, 80);
            recMenu.add(spLS);

            JLabel lblMedTitle = new JLabel("Medical Management:");
            lblMedTitle.setBounds(30, 190, 180, 25);
            lblMedTitle.setFont(new Font("Calibri", Font.BOLD, 14));
            recMenu.add(lblMedTitle);
            txtMedManagement = new JTextArea(lblMS.getText().equals("N/A") ? "" : lblMS.getText());
            txtMedManagement.setLineWrap(true);
            txtMedManagement.setWrapStyleWord(true);
            txtMedManagement.setFont(new Font("Calibri", Font.PLAIN, 14));
            JScrollPane spMM = new JScrollPane(txtMedManagement);
            spMM.setBounds(30, 220, 520, 80);
            recMenu.add(spMM);

            JButton btnRecSave = new JButton("Save");
            btnRecSave.setBounds(180, 317, 100, 35);
            btnRecSave.setBackground(darkBlue);
            btnRecSave.setForeground(Color.WHITE);
            recMenu.add(btnRecSave);

            JButton btnRecCancel = new JButton("Cancel");
            btnRecCancel.setBounds(300, 317, 100, 35);
            btnRecCancel.setBackground(Color.GRAY);
            btnRecCancel.setForeground(Color.WHITE);
            recMenu.add(btnRecCancel);

            btnRecSave.addActionListener(save -> {
                if (foundPatient == null) return;
                String ls = txtLifestyle.getText().trim();
                String mm = txtMedManagement.getText().trim();

                Patient updated = new Patient(
                    foundPatient.getId(), foundPatient.getName(), foundPatient.getRoom(),
                    foundPatient.getDoctor(), foundPatient.getNurse(), foundPatient.getStatus(),
                    foundPatient.getAdmissionDate(), foundPatient.getAddress(),
                    foundPatient.getContactNumber(), foundPatient.getAllergies(),
                    foundPatient.getConditions(), foundPatient.getMedications(),
                    foundPatient.getSurgicalHistory(), foundPatient.getFamilyHistory(),
                    ls, mm,
                    foundPatient.getDietBreakfast(), foundPatient.getDietLunch(),
                    foundPatient.getDietDinner(),
                    foundPatient.getEmergencyName(), foundPatient.getEmergencyRel(),
                    foundPatient.getEmergencyNum(), foundPatient.getBloodType(),
                    foundPatient.getAge(), foundPatient.getNotes(),
                    foundPatient.getBp(), foundPatient.getHr(),
                    foundPatient.getTemp(), foundPatient.getSpo2(),
                    foundPatient.getGeneralAppearance(), foundPatient.getCardiovascular(),
                    foundPatient.getRespiratory(), foundPatient.getNeurological()
                );

                if (PatientManagementSQL.updatePatientProfile(updated)) {
                    foundPatient = updated;
                    lblLS.setText(ls.isEmpty() ? "N/A" : ls);
                    lblMS.setText(mm.isEmpty() ? "N/A" : mm);
                    recMenu.dispose();
                    JOptionPane.showMessageDialog(MedicalHistory_Doctor.this, "Recommendations saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(recMenu, "Failed to save to database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            btnRecCancel.addActionListener(cancel -> recMenu.dispose());

            recMenu.setVisible(true);
        }
    }

    private String extractFoodFromDiet(String dietString) {
        if (dietString == null || dietString.isEmpty()) return "";
        int pipeIndex = dietString.indexOf(" |");
        if (pipeIndex > 0) return dietString.substring(0, pipeIndex);
        return dietString;
    }

    private void startClockTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a");
        lblDT.setText(LocalDateTime.now().format(formatter));
        Timer timer = new Timer(1000, e -> lblDT.setText(LocalDateTime.now().format(formatter)));
        timer.start();
    }
}