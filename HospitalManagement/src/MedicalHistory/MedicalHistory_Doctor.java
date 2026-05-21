package MedicalHistory;

import static Color_Palette.ColorPalette.*;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;

public class MedicalHistory_Doctor extends JPanel{
    
    private JPanel pnlMain, profileCard, accent, statusBadge, pnlWard, details, detailsLine, PhyNotes, notesLine,
                   recommend, appLine;
    private JLabel lbltitle, lblDT, lblPat, name, status, info, lblDiet, lblAll, lblP, lblCD, lblDetails, lblObb,
                   lblSH, lblLP, lblNum, lblFH, lblOb, lblPhy, lblGen, lblGS, lblCar, lblRes, lblApp, lblRC, lblN, 
                   lblNC, lblLD, lblLS, lblMM, lblMS;
    private ImageIcon imgP;
    private Image ImgPT;
    private JButton edit, notes, btnViewAll;
    private JTable tblDiet;
    private JTableHeader HdrD;
    private DefaultTableCellRenderer leftRenderer;
    private JScrollPane scrDiet;
    
    public MedicalHistory_Doctor() {
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

        imgP = new ImageIcon(getClass().getResource("/resources/PATIENT.PHOTO.png"));
        ImgPT = imgP.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        lblPat = new JLabel(new ImageIcon(ImgPT));
        lblPat.setBounds(10, 15, 140, 140);
        profileCard.add(lblPat);
        
        name = new JLabel("Joshua Garcia");
        name.setFont(new Font("Calibri", Font.BOLD, 22));
        name.setBounds(150, 20, 300, 30);
        profileCard.add(name);

        //status badge
        statusBadge = new JPanel();
        statusBadge.setBackground(LightRed);
        statusBadge.setBounds(150, 55, 190, 26);
        statusBadge.setLayout(null);
        profileCard.add(statusBadge);

        status = new JLabel("Status: Observation");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Calibri", Font.BOLD, 18));
        status.setBounds(5, 5, 180, 20);
        statusBadge.add(status);

        info = new JLabel("ID: P-10021   |   Age: 29   |   Blood: O+");
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

        notes = new JButton("Notes");
        notes.setBounds(1420, 75, 100, 35);
        notes.setBackground(lightBlue);
        notes.setForeground(Color.BLACK);
        notes.setFont(new Font("Calibri", Font.BOLD, 14));
        profileCard.add(notes);
        
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

        lblAll = new JLabel("Allergies: ");
        lblAll.setFont(new Font("Calibri", Font.BOLD, 22));
        lblAll.setForeground(LightRed);
        lblAll.setBounds(20, 50, 200, 30);
        details.add(lblAll);
        
        lblP = new JLabel("Pennicilin");
        lblP.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblP.setForeground(Color.BLACK);
        lblP.setBounds(20, 80, 200, 30);
        details.add(lblP);
        
        lblCD = new JLabel("Chronic Conditions:");
        lblCD.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCD.setForeground(darkBlue);
        lblCD.setBounds(350, 50, 200, 30);
        details.add(lblCD);
        
        lblObb = new JLabel("Obesity");
        lblObb.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblObb.setForeground(Color.BLACK);
        lblObb.setBounds(350, 80, 200, 30);
        details.add(lblObb);
        
        lblSH = new JLabel("Surgical History: ");
        lblSH.setFont(new Font("Calibri", Font.BOLD, 22));
        lblSH.setForeground(darkBlue);
        lblSH.setBounds(20, 150, 200, 30);
        details.add(lblSH);
        
        lblLP = new JLabel("Liposuction");
        lblLP.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblLP.setForeground(Color.BLACK);
        lblLP.setBounds(20, 180, 200, 30);
        details.add(lblLP);
        
        lblFH = new JLabel("Family History:");
        lblFH.setFont(new Font("Calibri", Font.BOLD, 22));
        lblFH.setForeground(darkBlue);
        lblFH.setBounds(350, 150, 200, 30);
        details.add(lblFH);
        
        lblOb = new JLabel("Obesity");
        lblOb.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblOb.setForeground(Color.BLACK);
        lblOb.setBounds(350, 180, 200, 30);
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

        lblGen = new JLabel("General Appearance: ");
        lblGen.setFont(new Font("Calibri", Font.BOLD, 22));
        lblGen.setForeground(darkBlue);
        lblGen.setBounds(20, 60, 200, 30);
        PhyNotes.add(lblGen);
        
        lblGS = new JLabel("Alert, oriented, no acute distress");
        lblGS.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblGS.setForeground(Color.BLACK);
        lblGS.setBounds(20, 90, 350, 30);
        PhyNotes.add(lblGS);
        
        lblCar = new JLabel("Cardiovascular:");
        lblCar.setFont(new Font("Calibri", Font.BOLD, 22));
        lblCar.setForeground(darkBlue);
        lblCar.setBounds(350, 60, 200, 30);
        PhyNotes.add(lblCar);
        
        lblNum = new JLabel("Regular and normal peripheral pulses");
        lblNum.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblNum.setForeground(Color.BLACK);
        lblNum.setBounds(350, 90, 350, 30);
        PhyNotes.add(lblNum);
        
        lblRes = new JLabel("Respiratory: ");
        lblRes.setFont(new Font("Calibri", Font.BOLD, 22));
        lblRes.setForeground(darkBlue);
        lblRes.setBounds(20, 150, 200, 30);
        PhyNotes.add(lblRes);
        
        lblRC = new JLabel("Clear breath sounds, no wheezes");
        lblRC.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblRC.setForeground(Color.BLACK);
        lblRC.setBounds(20, 180, 350, 30);
        PhyNotes.add(lblRC);
        
        lblN = new JLabel("Neurological:");
        lblN.setFont(new Font("Calibri", Font.BOLD, 22));
        lblN.setForeground(darkBlue);
        lblN.setBounds(350, 150, 200, 30);
        PhyNotes.add(lblN);
        
        lblNC = new JLabel("Cranial nerves intact, reflexes normal");
        lblNC.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblNC.setForeground(Color.BLACK);
        lblNC.setBounds(350, 180, 350, 30);
        PhyNotes.add(lblNC);
        
        pnlWard = new JPanel();
        pnlWard.setLayout(null);
        pnlWard.setBounds(800, 240, 790, 300);
        pnlWard.setBackground(Color.WHITE);
        pnlWard.setBorder(BorderFactory.createLineBorder(borderLBLUE));
        pnlMain.add(pnlWard);
        
        lblDiet = new JLabel("Ward Activity");
        lblDiet.setFont(new Font("Calibri", Font.BOLD, 22));
        lblDiet.setForeground(darkBlue);
        lblDiet.setBounds(20, 20, 300, 30);
        pnlWard.add(lblDiet);
        
        String[] clmWard = {"Food", "Water", "Medication"};
        
        String[][] RwWard = {
            {"Breakfast: Oatmeal with banana + boiled egg", "1 glass (250 ml)", "Multivitamin"},
            {"Lunch: Grilled chicken and mixed vegetables", "2 glasses (500 ml)", "Medication"},
            {"Dinner: Chicken stir-fry and mixed vegetables", "2 glasses (500 ml)", "Medication"}
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

        tblDiet.getColumnModel().getColumn(0).setPreferredWidth(110);
        tblDiet.getColumnModel().getColumn(1).setPreferredWidth(190);
        tblDiet.getColumnModel().getColumn(2).setPreferredWidth(80);

        scrDiet = new JScrollPane(tblDiet);
        scrDiet.setBounds(20, 70, 750, 200);
        scrDiet.setBorder(BorderFactory.createEmptyBorder());
        pnlWard.add(scrDiet);
        
        btnViewAll = new JButton("View All Activity");
        btnViewAll.setBounds(570, 20, 200, 40);
        btnViewAll.setFont(new Font("Calibri", Font.BOLD, 18));
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
        
        lblLS = new JLabel("Limit processed foods, sugary drinks, and high-fat meals.");
        lblLS.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblLS.setForeground(Color.BLACK);
        lblLS.setBounds(20, 110, 600, 30);
        recommend.add(lblLS);
        
        lblMM = new JLabel("Medical Management:");
        lblMM.setFont(new Font("Calibri", Font.BOLD, 20));
        lblMM.setForeground(darkBlue);
        lblMM.setBounds(20, 160, 200, 30);
        recommend.add(lblMM);
        
        lblMS = new JLabel("Continue prescribed medications and multivitamin supplementation.");
        lblMS.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblMS.setForeground(Color.BLACK);
        lblMS.setBounds(20, 200, 600, 30);
        recommend.add(lblMS);
    }
}
