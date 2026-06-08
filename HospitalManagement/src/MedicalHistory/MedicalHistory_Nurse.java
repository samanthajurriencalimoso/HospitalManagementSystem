package MedicalHistory;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;

public class MedicalHistory_Nurse extends JPanel implements ActionListener{
    
    private JPanel pnlMain, profileCard, accent, statusBadge, pnlWard, details, detailsLine, PhyNotes, notesLine,
                   recommend, appLine;
    private JLabel lbltitle, lblDT, lblPat, name, status, info, lblDiet, lblAll, lblP, lblCD, lblDetails, lblObb,
                   lblSH, lblLP, lblNum, lblFH, lblOb, lblPhy, lblGen, lblGS, lblCar, lblRes, lblApp, lblRC, lblN, 
                   lblNC, lblLD, lblLS, lblMM, lblMS, lblUpdateTitle, lblBreakfast, lblLunch, lblDinner,
                   lblAllergies, lblChronicConditions, lblSurgicalHistory, lblFamilyHistory, 
                   lblGeneralAppearance, lblCardiovascular, lblRespiratory, lblNeurological,lblMedHistoryTitle, lblPhyTitle;
    private ImageIcon imgP;
    private Image ImgPT;
    private JButton edit, update, btnViewAll, btnSave, btnCancel, btnUpdateSave, btnUpdateCancel, btnUpdateClear;
    private JTable tblDiet;
    private JTableHeader HdrD;
    private DefaultTableCellRenderer leftRenderer;
    private JScrollPane scrDiet;
    private JFrame editMenu, updateMenu;
    private JTextField txtAllergies, txtChronicConditions, txtSurgicalHistory, txtFamilyHistory, txtBreakfastFood, 
            txtBreakfastWater, txtBreakfastMed, txtLunchFood, txtLunchWater, txtLunchMed, txtDinnerFood, txtDinnerWater, 
            txtDinnerMed, txtGeneralAppearance, txtCardiovascular, txtRespiratory, txtNeurological;

    
    public MedicalHistory_Nurse() {
        setLayout(null);
        setBounds(0, 0, 1620, 930);
        
        //Main Panel
        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(veryLightBlue);
        pnlMain.setBounds(0, 0, 1620, 930);
        add(pnlMain);

        //page title
        lbltitle = new JLabel("Patient Medical History");
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
        profileCard.setBounds(30, 80, 1560, 140);
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
        
        name = new JLabel("");
        name.setFont(new Font("Calibri", Font.BOLD, 22));
        name.setBounds(150, 20, 300, 30);
        profileCard.add(name);

        //status badge
        statusBadge = new JPanel();
        statusBadge.setBackground(LightRed);
        statusBadge.setBounds(150, 55, 190, 26);
        statusBadge.setLayout(null);
        profileCard.add(statusBadge);

        status = new JLabel("Status: ");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Calibri", Font.BOLD, 18));
        status.setBounds(5, 5, 180, 20);
        statusBadge.add(status);

        info = new JLabel("ID: P-  |   Age:   |   Blood Type: ");
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
        profileCard.add(edit);

        update = new JButton("Update");
        update.setBounds(1420, 75, 100, 35);
        update.setBackground(lightBlue);
        update.setForeground(Color.BLACK);
        update.setFont(new Font("Calibri", Font.BOLD, 14));
        profileCard.add(update);
        
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

        lblP = new JLabel("");
        lblP.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblP.setForeground(Color.BLACK);
        lblP.setBounds(20, 85, 300, 30);
        details.add(lblP);

        lblCD = new JLabel("Chronic Conditions:");
        lblCD.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCD.setForeground(darkBlue);
        lblCD.setBounds(350, 50, 200, 30);
        details.add(lblCD);

        lblObb = new JLabel("");
        lblObb.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblObb.setForeground(Color.BLACK);
        lblObb.setBounds(350, 85, 350, 30);
        details.add(lblObb);

        lblSH = new JLabel("Surgical History:");
        lblSH.setFont(new Font("Calibri", Font.BOLD, 22));
        lblSH.setForeground(darkBlue);
        lblSH.setBounds(20, 150, 200, 30);
        details.add(lblSH);

        lblLP = new JLabel("");
        lblLP.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblLP.setForeground(Color.BLACK);
        lblLP.setBounds(20, 185, 300, 30); 
        details.add(lblLP);

        lblFH = new JLabel("Family History:");
        lblFH.setFont(new Font("Calibri", Font.BOLD, 22));
        lblFH.setForeground(darkBlue);
        lblFH.setBounds(350, 150, 200, 30);
        details.add(lblFH);

        lblOb = new JLabel("");
        lblOb.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblOb.setForeground(Color.BLACK);
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
        lblPhy.setBounds(20, 15, 500, 25);
        PhyNotes.add(lblPhy);

        notesLine = new JPanel();
        notesLine.setBounds(20, 50, 710, 2);
        notesLine.setBackground(lightBlue);
        PhyNotes.add(notesLine);

        lblGen = new JLabel("General Appearance:");
        lblGen.setFont(new Font("Calibri", Font.BOLD, 20));
        lblGen.setForeground(darkBlue);
        lblGen.setBounds(20, 70, 200, 30);
        PhyNotes.add(lblGen);

        lblGS = new JLabel("");
        lblGS.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblGS.setForeground(Color.BLACK);
        lblGS.setBounds(20, 105, 320, 30);  // BELOW the label (y=105 instead of 60)
        PhyNotes.add(lblGS);

        lblCar = new JLabel("Cardiovascular:");
        lblCar.setFont(new Font("Calibri", Font.BOLD, 20));
        lblCar.setForeground(darkBlue);
        lblCar.setBounds(350, 70, 160, 30);
        PhyNotes.add(lblCar);

        lblNum = new JLabel("");
        lblNum.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblNum.setForeground(Color.BLACK);
        lblNum.setBounds(350, 105, 350, 30);  // BELOW the label
        PhyNotes.add(lblNum);

        lblRes = new JLabel("Respiratory:");
        lblRes.setFont(new Font("Calibri", Font.BOLD, 20));
        lblRes.setForeground(darkBlue);
        lblRes.setBounds(20, 155, 160, 30);
        PhyNotes.add(lblRes);

        lblRC = new JLabel("");
        lblRC.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblRC.setForeground(Color.BLACK);
        lblRC.setBounds(20, 190, 320, 30);  // BELOW the label
        PhyNotes.add(lblRC);

        lblN = new JLabel("Neurological:");
        lblN.setFont(new Font("Calibri", Font.BOLD, 20));
        lblN.setForeground(darkBlue);
        lblN.setBounds(350, 155, 160, 30);
        PhyNotes.add(lblN);

        lblNC = new JLabel("");
        lblNC.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblNC.setForeground(Color.BLACK);
        lblNC.setBounds(350, 190, 350, 30);  // BELOW the label
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

        String[][] RwWard = {
            {"Breakfast: ", "", ""},
            {"Lunch: ", "", ""},
            {"Dinner: ", "", ""}
        };

        tblDiet = new JTable(RwWard, clmWard);
        tblDiet.setFont(new Font("Calibri", Font.PLAIN, 14));
        tblDiet.setRowHeight(55);
        tblDiet.setGridColor(Color.LIGHT_GRAY);
        tblDiet.setBackground(Color.WHITE);

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
        
        btnViewAll = new JButton("View Diet Plan");
        btnViewAll.setBounds(590, 20, 180, 35);
        btnViewAll.setFont(new Font("Calibri", Font.BOLD, 14));
        btnViewAll.setBackground(Blue);
        btnViewAll.setForeground(Color.WHITE);
        pnlWard.add(btnViewAll);
        
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
        appLine.setBounds(20, 50, 770, 2);
        appLine.setBackground(lightBlue);
        recommend.add(appLine);

        lblLD = new JLabel("Lifestyle & Diet:");
        lblLD.setFont(new Font("Calibri", Font.BOLD, 20));
        lblLD.setForeground(darkBlue);
        lblLD.setBounds(20, 70, 150, 30);
        recommend.add(lblLD);
        
        lblLS = new JLabel("");
        lblLS.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblLS.setForeground(Color.BLACK);
        lblLS.setBounds(20, 110, 600, 30);
        recommend.add(lblLS);
        
        lblMM = new JLabel("Medical Management:");
        lblMM.setFont(new Font("Calibri", Font.BOLD, 20));
        lblMM.setForeground(darkBlue);
        lblMM.setBounds(20, 160, 200, 30);
        recommend.add(lblMM);
        
        lblMS = new JLabel("");
        lblMS.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblMS.setForeground(Color.BLACK);
        lblMS.setBounds(20, 200, 600, 30);
        recommend.add(lblMS);
        
        edit.addActionListener(this);
        update.addActionListener(this);
    }
    
    
    @Override
            public void actionPerformed(ActionEvent e) {
    // edit button
    if (e.getSource() == edit) {
        editMenu = new JFrame();
        editMenu.setTitle("Edit Patient");
        editMenu.setSize(650, 580);
        editMenu.setLocationRelativeTo(null);
        editMenu.setLayout(null);
        editMenu.setResizable(false);
        editMenu.getContentPane().setBackground(new Color(240, 240, 240));

        //med history
        lblMedHistoryTitle = new JLabel("MEDICAL HISTORY");
        lblMedHistoryTitle.setBounds(30, 30, 300, 25);
        lblMedHistoryTitle.setFont(new Font("Calibri", Font.BOLD, 16));
        lblMedHistoryTitle.setForeground(darkBlue);
        editMenu.add(lblMedHistoryTitle);

        lblAllergies = new JLabel("Allergies:");
        lblAllergies.setBounds(30, 70, 100, 25);
        lblAllergies.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblAllergies);

        txtAllergies = new JTextField(lblP.getText());
        txtAllergies.setBounds(190, 70, 410, 30);
        editMenu.add(txtAllergies);

        lblChronicConditions = new JLabel("Chronic Conditions:");
        lblChronicConditions.setBounds(30, 115, 150, 25);
        lblChronicConditions.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblChronicConditions);

        txtChronicConditions = new JTextField(lblObb.getText());
        txtChronicConditions.setBounds(190, 115, 410, 30);
        editMenu.add(txtChronicConditions);

        lblSurgicalHistory = new JLabel("Surgical History:");
        lblSurgicalHistory.setBounds(30, 160, 150, 25);
        lblSurgicalHistory.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblSurgicalHistory);

        txtSurgicalHistory = new JTextField(lblLP.getText());
        txtSurgicalHistory.setBounds(190, 160, 410, 30);
        editMenu.add(txtSurgicalHistory);

        lblFamilyHistory = new JLabel("Family History:");
        lblFamilyHistory.setBounds(30, 205, 150, 25);
        lblFamilyHistory.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblFamilyHistory);

        txtFamilyHistory = new JTextField(lblOb.getText());
        txtFamilyHistory.setBounds(190, 205, 410, 30);
        editMenu.add(txtFamilyHistory);

        //phys examination
        lblPhyTitle = new JLabel("PHYSICAL EXAMINATION");
        lblPhyTitle.setBounds(30, 260, 300, 25);
        lblPhyTitle.setFont(new Font("Calibri", Font.BOLD, 16));
        lblPhyTitle.setForeground(darkBlue);
        editMenu.add(lblPhyTitle);

        lblGeneralAppearance = new JLabel("General Appearance:");
        lblGeneralAppearance.setBounds(30, 300, 150, 25);
        lblGeneralAppearance.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblGeneralAppearance);

        txtGeneralAppearance = new JTextField(lblGS.getText());
        txtGeneralAppearance.setBounds(190, 300, 410, 30);
        editMenu.add(txtGeneralAppearance);

        lblCardiovascular = new JLabel("Cardiovascular:");
        lblCardiovascular.setBounds(30, 345, 150, 25);
        lblCardiovascular.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblCardiovascular);

        txtCardiovascular = new JTextField(lblNum.getText());
        txtCardiovascular.setBounds(190, 345, 410, 30);
        editMenu.add(txtCardiovascular);

        lblRespiratory = new JLabel("Respiratory:");
        lblRespiratory.setBounds(30, 390, 150, 25);
        lblRespiratory.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblRespiratory);

        txtRespiratory = new JTextField(lblRC.getText());
        txtRespiratory.setBounds(190, 390, 410, 30);
        editMenu.add(txtRespiratory);

        lblNeurological = new JLabel("Neurological:");
        lblNeurological.setBounds(30, 435, 150, 25);
        lblNeurological.setFont(new Font("Calibri", Font.BOLD, 14));
        editMenu.add(lblNeurological);

        txtNeurological = new JTextField(lblNC.getText());
        txtNeurological.setBounds(190, 435, 410, 30);
        editMenu.add(txtNeurological);

        //buttons
        btnSave = new JButton("Save Changes");
        btnSave.setBounds(180, 490, 130, 35);
        btnSave.setBackground(darkBlue);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(this);
        editMenu.add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(330, 490, 130, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        editMenu.add(btnCancel);

        editMenu.setVisible(true);
    }
    
    // save (inside edit menu)
    else if (e.getSource() == btnSave) {
        lblP.setText(txtAllergies.getText());
        lblObb.setText(txtChronicConditions.getText());
        lblLP.setText(txtSurgicalHistory.getText());
        lblOb.setText(txtFamilyHistory.getText());

        lblGS.setText(txtGeneralAppearance.getText());
        lblNum.setText(txtCardiovascular.getText());
        lblRC.setText(txtRespiratory.getText());
        lblNC.setText(txtNeurological.getText());

        if (editMenu != null) {
            editMenu.dispose();
        }
        JOptionPane.showMessageDialog(this, "Medical History and Physical Examination updated successfully!");
    }
    
    // cancel (inside edit menu)
    else if (e.getSource() == btnCancel) {
        if (editMenu != null) {
            editMenu.dispose();
        }
    }
    
    // update button
    else if (e.getSource() == update) {
        updateMenu = new JFrame();
        updateMenu.setTitle("Update Diet Plan");
        updateMenu.setSize(750, 400);
        updateMenu.setLocationRelativeTo(null);
        updateMenu.setLayout(null);
        updateMenu.setResizable(false);
        updateMenu.getContentPane().setBackground(new Color(240, 240, 240));

        lblUpdateTitle = new JLabel("Edit Diet Plan");
        lblUpdateTitle.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUpdateTitle.setForeground(darkBlue);
        lblUpdateTitle.setBounds(30, 20, 200, 30);
        updateMenu.add(lblUpdateTitle);

        //diet column headers
        JLabel lblColFood = new JLabel("Food");
        lblColFood.setFont(new Font("Calibri", Font.BOLD, 14));
        lblColFood.setBounds(120, 60, 150, 25);
        updateMenu.add(lblColFood);

        JLabel lblColWater = new JLabel("Water");
        lblColWater.setFont(new Font("Calibri", Font.BOLD, 14));
        lblColWater.setBounds(290, 60, 100, 25);
        updateMenu.add(lblColWater);

        JLabel lblColMed = new JLabel("Medication");
        lblColMed.setFont(new Font("Calibri", Font.BOLD, 14));
        lblColMed.setBounds(410, 60, 150, 25);
        updateMenu.add(lblColMed);

        //breakfast row
        lblBreakfast = new JLabel("Breakfast:");
        lblBreakfast.setFont(new Font("Calibri", Font.BOLD, 14));
        lblBreakfast.setBounds(30, 100, 80, 25);
        updateMenu.add(lblBreakfast);

        txtBreakfastFood = new JTextField();
        txtBreakfastFood.setText((String) tblDiet.getValueAt(0, 1));
        txtBreakfastFood.setBounds(120, 100, 150, 30);
        updateMenu.add(txtBreakfastFood);

        txtBreakfastWater = new JTextField();
        txtBreakfastWater.setText((String) tblDiet.getValueAt(0, 2));
        txtBreakfastWater.setBounds(290, 100, 100, 30);
        updateMenu.add(txtBreakfastWater);

        txtBreakfastMed = new JTextField();
        txtBreakfastMed.setBounds(410, 100, 200, 30);
        updateMenu.add(txtBreakfastMed);

        //lunch row
        lblLunch = new JLabel("Lunch:");
        lblLunch.setFont(new Font("Calibri", Font.BOLD, 14));
        lblLunch.setBounds(30, 160, 80, 25);
        updateMenu.add(lblLunch);

        txtLunchFood = new JTextField();
        txtLunchFood.setText((String) tblDiet.getValueAt(1, 1));
        txtLunchFood.setBounds(120, 160, 150, 30);
        updateMenu.add(txtLunchFood);

        txtLunchWater = new JTextField();
        txtLunchWater.setText((String) tblDiet.getValueAt(1, 2));
        txtLunchWater.setBounds(290, 160, 100, 30);
        updateMenu.add(txtLunchWater);

        txtLunchMed = new JTextField();
        txtLunchMed.setBounds(410, 160, 200, 30);
        updateMenu.add(txtLunchMed);

        //dinner row
        lblDinner = new JLabel("Dinner:");
        lblDinner.setFont(new Font("Calibri", Font.BOLD, 14));
        lblDinner.setBounds(30, 220, 80, 25);
        updateMenu.add(lblDinner);

        txtDinnerFood = new JTextField();
        txtDinnerFood.setText((String) tblDiet.getValueAt(2, 1));
        txtDinnerFood.setBounds(120, 220, 150, 30);
        updateMenu.add(txtDinnerFood);

        txtDinnerWater = new JTextField();
        txtDinnerWater.setText((String) tblDiet.getValueAt(2, 2));
        txtDinnerWater.setBounds(290, 220, 100, 30);
        updateMenu.add(txtDinnerWater);

        txtDinnerMed = new JTextField();
        txtDinnerMed.setBounds(410, 220, 200, 30);
        updateMenu.add(txtDinnerMed);

        //buttons
        btnUpdateSave = new JButton("Save Changes");
        btnUpdateSave.setBounds(150, 290, 130, 35);
        btnUpdateSave.setBackground(darkBlue);
        btnUpdateSave.setForeground(Color.WHITE);
        btnUpdateSave.addActionListener(this);
        updateMenu.add(btnUpdateSave);

        btnUpdateClear = new JButton("Clear");
        btnUpdateClear.setBounds(300, 290, 130, 35);
        btnUpdateClear.setBackground(LightRed);
        btnUpdateClear.setForeground(Color.WHITE);
        btnUpdateClear.addActionListener(this);
        updateMenu.add(btnUpdateClear);

        btnUpdateCancel = new JButton("Cancel");
        btnUpdateCancel.setBounds(450, 290, 130, 35);
        btnUpdateCancel.setBackground(Color.GRAY);
        btnUpdateCancel.setForeground(Color.WHITE);
        btnUpdateCancel.addActionListener(this);
        updateMenu.add(btnUpdateCancel);

        updateMenu.setVisible(true);
    }
    
    // save diet button
    else if (e.getSource() == btnUpdateSave) {
 
        String breakfastText = "Breakfast: " + txtBreakfastFood.getText();
        tblDiet.setValueAt(breakfastText, 0, 0);
        tblDiet.setValueAt(txtBreakfastWater.getText(), 0, 1);

        String lunchText = "Lunch: " + txtLunchFood.getText();
        tblDiet.setValueAt(lunchText, 1, 0);
        tblDiet.setValueAt(txtLunchWater.getText(), 1, 1);

        String dinnerText = "Dinner: " + txtDinnerFood.getText();
        tblDiet.setValueAt(dinnerText, 2, 0);
        tblDiet.setValueAt(txtDinnerWater.getText(), 2, 1);

        if (updateMenu != null) {
            updateMenu.dispose();
        }
        JOptionPane.showMessageDialog(this, "Diet Plan updated successfully!");
    }
    
    // clear diet button
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
    
    // cancel in update menu
    else if (e.getSource() == btnUpdateCancel) {
        if (updateMenu != null) {
            updateMenu.dispose();
        }
    }
            }
}

