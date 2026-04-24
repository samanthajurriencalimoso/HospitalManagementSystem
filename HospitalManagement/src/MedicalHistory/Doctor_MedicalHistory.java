/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MedicalHistory;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Doctor_MedicalHistory extends JPanel {
    private boolean editMode = false;
    private JPanel selectedRow = null, centerPanel;
    private java.util.List<Runnable> saveTasks = new java.util.ArrayList<>();
    
    public Doctor_MedicalHistory(){
        setLayout(null);
     
        
        JTabbedPane AppPane = new JTabbedPane();
        AppPane.setBounds(0, 0, 1620, 920);
        AppPane.setBackground(new Color(0xA3, 0xCA, 0xE9));
        AppPane.setForeground(Color.BLACK);
        AppPane.setFont(new Font("Segoe UI", Font.BOLD, 16));
        UIManager.put("TabbedPane.selected", new Color(0x5A, 0x8F, 0xC9));
        
        AppPane.addTab("Medical History", new JPanel());
        
        AppointmentHistory doctorAvailabilityPanel = new AppointmentHistory();
        AppPane.addTab("Appointment History", doctorAvailabilityPanel);

        add(AppPane);
        
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setPreferredSize(new Dimension(1600, 1600)); 
        // ==================Patient Panel =================
        PatientPanel();
        
        //======================TimeLine Panel==============
        TimeLinePanel();
        
        //======================History Panel==============
        HistoryPanel();
        
        //======================Diagnostics Panel==============
        DiagnosticsPanel();
        
        //======================Medications Panel==============
        MedicationsPanel();
        
        //======================Diet Panel==============
        DietPanel();
        
        JScrollPane mainScroll = new JScrollPane(centerPanel);
        mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);
        AppPane.setComponentAt(0, mainScroll);
//        AppPane.setComponentAt(0, centerPanel);

    
    }
    
    public static JPanel RoundPanel(String hexColor, Color borderColor, int radius, int thickness) {
    return new JPanel() {
        private Color bgColor = Color.decode(hexColor);
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
            
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        }

        { setOpaque(false); }
    };
}
    
    public JPanel createLine(int x, int y, int width, int height, Color color) {
        JPanel line = new JPanel();
        line.setBounds(x, y, width, height);
        line.setBackground(color);
        return line;
    }
    
    public JButton createEditButton(int x, int y){
       JButton btn = new JButton("Edit");
            btn.setBounds(x, y, 80, 25);

            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

            return btn;
    }
    
    public void makeEditable(JButton btn, JLabel label, JTextField field) {

        field.setVisible(false); // safety default

        btn.addActionListener(e -> {

            if (field.isVisible()) {
                // SAVE MODE
                label.setText(field.getText());
                field.setVisible(false);
                label.setVisible(true);
                btn.setText("Edit");
            } else {
                // EDIT MODE
                field.setVisible(true);
                label.setVisible(false);
                btn.setText("Save");

                field.setText(label.getText()); // sync latest value
            }

            revalidate();
            repaint();
        });
    }
    
    public JPanel createHistoryRow(String date, String illness, int yPosition) {

            JPanel row = new JPanel();
            row.setLayout(null);
            row.setBounds(10, yPosition, 600, 40);
            row.setBackground(Color.WHITE);

            JLabel lblDate = new JLabel(date);
            lblDate.setBounds(10, 10, 100, 20);
            lblDate.setFont(new Font("Seoge UI", Font.BOLD, 12));
            lblDate.setForeground(Color.GRAY);
            row.add(lblDate);

            JPanel circle = new JPanel() {
                @Override
                protected void paintComponent(java.awt.Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval(0, 0, 10, 10);
                }
            };
            circle.setBounds(80, 5, 10, 10);
            row.add(circle);
            
            JPanel verticalLine = new JPanel();
            verticalLine.setBackground(Color.GRAY);
            verticalLine.setBounds(84, 18, 2, 30);
            row.add(verticalLine);

            JLabel lblIllness = new JLabel(illness);
            lblIllness.setBounds(100, 10, 300, 20);
            lblIllness.setFont(new Font("Seoge UI", Font.BOLD, 12));
            row.add(lblIllness);

            return row;
    } 
    
    public void setupEditableComponent(JPanel parentPanel, JLabel label, JTextField field) {

        field.setVisible(false);
        
        Runnable saveLogic = () -> { label.setText(field.getText()); field.setVisible(false); label.setVisible(true); };

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (!editMode) return; 
                field.setText(label.getText());
                label.setVisible(false);
                field.setVisible(true);
                
            }
        });
        field.addActionListener(e -> saveLogic.run());
    }

    public void configureEdit(JButton btn, JLabel label, JTextField field) {
        btn.addActionListener(e -> {
            editMode = !editMode;

            if (editMode) {
                btn.setText("Save");
            } else {
                btn.setText("Edit");
                field.setVisible(false);
                label.setVisible(true);
                label.setText(field.getText());
                selectedRow = null;
            }
            label.getParent().revalidate();
            label.getParent().repaint();
        });
    }
    
    public JPanel createSmallPanel (JPanel parent, int x, int y, int width, int height){
        JPanel panel = new JPanel ();
        panel.setLayout(null);
        panel.setBounds(x,y,width,height);
        parent.add(panel);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        return panel;
    }
    
    public JLabel createLabel (JPanel panel,String text, int x, int y, int width, int height){
        JLabel label = new JLabel(text);
        label.setBounds(x,y,width,height);
        panel.add(label);
        return label;
    }
    
    public void applyFont(javax.swing.JComponent comp, String fontname, int style, int size){
        comp.setFont (new java.awt.Font(fontname, style,size));
    }
    
    public JTextField createHiddenField (JPanel panel, String text, int x, int y, int width, int height){
       JTextField field = new JTextField (text); 
       field.setBounds(x, y, width, height);
       field.setVisible(false);
       panel.add(field);
       return field;
    }
       
    public void PatientPanel(){
        JPanel pnlpatient = new JPanel();
        pnlpatient.setLayout(null);
        pnlpatient.setBounds(30,20,1030, 150);
        pnlpatient.setBackground(Color.WHITE);
        centerPanel.add(pnlpatient);
    
        JButton btnEditPatient = createEditButton(940, 15);
        pnlpatient.add(btnEditPatient);
        
        btnEditPatient.addActionListener(e -> {
            editMode = !editMode;
            btnEditPatient.setText(editMode ? "Save" : "Edit");
            if (!editMode) {
                for (Runnable task : saveTasks) {
                    task.run();
                }
            }
            pnlpatient.revalidate();
            pnlpatient.repaint();
        });
        
        JPanel pnlImageFrame = createSmallPanel(pnlpatient, 20, 15, 135, 120);
        JLabel lblImage = createLabel(pnlImageFrame, "", 0, 0, 180, 180);
    
        JButton btnUpload = new JButton("Upload");
        btnUpload.setBounds(40, 60, 100, 30);
        btnUpload.setBackground(Color.WHITE);
        pnlpatient.add(btnUpload);
        
        btnUpload.addActionListener(e -> {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            java.io.File file = fileChooser.getSelectedFile();

            ImageIcon icon = new ImageIcon(file.getAbsolutePath());

            // resize image to fit frame
            Image img = icon.getImage().getScaledInstance(
                    lblImage.getWidth(),
                    lblImage.getHeight(),
                    Image.SCALE_SMOOTH
            );

            lblImage.setIcon(new ImageIcon(img));
        }
    });
        
        JLabel lblpatient = createLabel(pnlpatient, "Patient's Name", 180, 15, 300, 24);
        applyFont (lblpatient, "Seoge UI", Font.PLAIN, 18);
        JTextField txtpatient = createHiddenField(pnlpatient, "Patient's Name", 180, 15, 300, 24);
        configureEdit(btnEditPatient, lblpatient, txtpatient);
        setupEditableComponent(pnlpatient, lblpatient, txtpatient);
        pnlpatient.add(createLine(180,45,500,1,Color.LIGHT_GRAY));
        
        JLabel lbldiagnosis = createLabel(pnlpatient, " Diagnosis", 940, 50, 100, 24);
        applyFont (lbldiagnosis, "Seoge UI", Font.BOLD, 12);
        
        JLabel lblbarrier = createLabel(pnlpatient, "Health Barriers", 940, 95, 100, 24);
        applyFont (lblbarrier, "Seoge UI", Font.BOLD, 12);
        
        JPanel pnldiagnosis = createSmallPanel(pnlpatient, 940, 75, 80, 20);
        JLabel lbldiag = createLabel(pnldiagnosis, "Leukemia", 10, 2, 100, 15);
        pnldiagnosis.setBackground(Color.WHITE);
        
        String[][] patientData = {{"BMI", "22.4"},{"Weight", "70 kg"},{"Height", "175 cm"},{"Pressure", "120/80"}};
        int startx = 180;
        int gap = 140;
        int panelWidth = 120;
        int numberofPanels = 4;
        
        for (int i = 0; i < patientData.length; i++){
            int x = startx + (i*gap);
            JPanel pnl  = createSmallPanel(pnlpatient, x, 75, panelWidth,60);      
            JLabel lbl = createLabel(pnl, patientData[i][0], 10, 35, 120, 15);
            applyFont (lbl, "Seoge UI", Font.BOLD, 12);
            lbl.setForeground(Color.GRAY);
            
            JLabel lblvalues = createLabel(pnl, patientData[i][1], 10, 5, 70, 24);
            applyFont (lblvalues, "Seoge UI", Font.BOLD, 18);
            JTextField txtvalues = createHiddenField(pnl, patientData[i][1], 10, 5, 50, 24);
            setupEditableComponent(pnl, lblvalues, txtvalues);

        }
            btnEditPatient.addActionListener(e -> {
                editMode = !editMode;
                btnEditPatient.setText(editMode ? "Save" : "Edit");
            });
//        JPanel pnlBMI = createSmallPanel(pnlpatient, 180, 75, 120, 60);
//        JLabel lblbmi = createLabel(pnlBMI, "BMI", 10, 35, 120, 15);
//        applyFont (lblbmi, "Seoge UI", Font.BOLD, 12);
//        lblbmi.setForeground(Color.GRAY);
//        
//        JLabel lblBMIVal = createLabel(pnlBMI, "22.4", 10, 5, 50, 24);
//        applyFont (lblBMIVal, "Seoge UI", Font.BOLD, 18);
//        JTextField txtBMIVal = createHiddenField(pnlBMI, "22.4", 10, 5, 50, 24);
//        setupEditableComponent(pnlBMI, lblBMIVal, txtBMIVal);
//        configureEdit(btnEditPatient, lblBMIVal, txtBMIVal);
//        
//        JPanel pnlWeight = createSmallPanel(pnlpatient, 315, 75, 120, 60);
//        JLabel lblWeight = createLabel(pnlWeight, "Weight", 10, 35, 120, 15);
//        applyFont(lblWeight, "Seoge UI", Font.BOLD, 12);
//        lblWeight.setForeground(Color.GRAY);
//        JLabel lblkg = createLabel(pnlWeight, "kg", 45, 7, 50, 22);
//        applyFont(lblkg, "Seoge UI", Font.BOLD, 16);
//        lblkg.setForeground(Color.GRAY);
//        
//        
//        JLabel lblWeightVal = createLabel(pnlWeight, "70", 10, 5, 50, 24);
//        applyFont(lblWeightVal, "Seoge UI", Font.BOLD, 18);
//        JTextField txtWeightVal = createHiddenField(pnlWeight, "70", 10, 5, 50, 24);
//        setupEditableComponent(pnlWeight, lblWeightVal, txtWeightVal);
//
//        // --- Height Panel ---
//        JPanel pnlHeight = createSmallPanel(pnlpatient, 450, 75, 120, 60);
//        JLabel lblHeight = createLabel(pnlHeight, "Height", 10, 35, 120, 15);
//        applyFont(lblHeight, "Seoge UI", Font.BOLD, 12);
//        lblHeight.setForeground(Color.GRAY);
//        JLabel lblcm = createLabel(pnlHeight, "cm", 45, 7, 50, 22);
//        applyFont(lblcm, "Seoge UI", Font.BOLD, 16);
//        lblcm.setForeground(Color.GRAY);
//
//        JLabel lblHeightVal = createLabel(pnlHeight, "175", 10, 5, 70, 24);
//        applyFont(lblHeightVal, "Seoge UI", Font.BOLD, 18);
//        JTextField txtHeightVal = createHiddenField(pnlHeight, "175", 10, 5, 70, 24);
//        setupEditableComponent(pnlHeight, lblHeightVal, txtHeightVal);
//
//        // --- Pressure Panel ---
//        JPanel pnlPressure = createSmallPanel(pnlpatient, 585, 75, 120, 60);
//        JLabel lblPressure = createLabel(pnlPressure, "Pressure", 10, 35, 120, 15);
//        applyFont(lblPressure, "Seoge UI", Font.BOLD, 12);
//        lblPressure.setForeground(Color.GRAY);
//
//        JLabel lblPressureVal = createLabel(pnlPressure, "120/80", 10, 5, 70, 24);
//        applyFont(lblPressureVal, "Seoge UI", Font.BOLD, 18);
//        JTextField txtPressureVal = createHiddenField(pnlPressure, "120/80", 10, 5, 70, 24);
//        setupEditableComponent(pnlPressure, lblPressureVal, txtPressureVal);
//        
    }
    
    public void TimeLinePanel(){
        JPanel pnltimeline = new JPanel();
        pnltimeline.setLayout(null);
        pnltimeline.setBounds(20,180,400,190);
        pnltimeline.setBackground(Color.WHITE);
        centerPanel.add(pnltimeline);
        
        JLabel lbltimeline = new JLabel("Medical Timeline");
        lbltimeline.setBounds(70, 20, 300, 15);
        pnltimeline.add(lbltimeline);
        
        JButton btnEditTimeline = createEditButton(300, 10);
        pnltimeline.add(btnEditTimeline);
        
        pnltimeline.add(createLine(0,50,400,1, Color.LIGHT_GRAY));
        
        pnltimeline.add(createHistoryRow("Apr 2022", "Pre-Diabetes", 60 ));
        pnltimeline.add(createHistoryRow("May 2024", "Diabetic", 100 ));
        pnltimeline.add(createHistoryRow("Sept 2025", "Leukemia", 140));
        
    }
              
    public void HistoryPanel(){
        JPanel pnlhistory = new JPanel();
        pnlhistory.setBounds(440, 180, 620, 190);
//      pnlhistory.setBounds(20, 180, 620, 230);
        pnlhistory.setLayout(null);
        pnlhistory.setBackground(Color.WHITE);
        centerPanel.add(pnlhistory);
        
        JLabel lblhistory = createLabel(pnlhistory, "Medical History",  70, 20, 300, 15);
        pnlhistory.add(createLine(55,50,500,1, Color.LIGHT_GRAY));  
        JButton btnEditHistory = createEditButton(500, 10);
        pnlhistory.add(btnEditHistory);

        String[] panelTitles = {
            "Allergies", "Chronic Conditions", "Surgical History", "Family History"
        };
        String[] historyValues = { "Pennicilin", "Obesity", "Liposuction", "Obesity"};
        
//        int startx = 20;
//        int gap = 150;
//        int panelWidth = 140;
//        int numberofPanels = 4;
//        
//        for (int i = 0; i < numberofPanels; i++){
//            int x = startx + (i*gap);
//            JPanel pnl  = createSmallPanel(pnlhistory, x, 60, panelWidth,100);      
//            JLabel lblTitles = createLabel(pnl, panelTitles[i], 10, 15, 120,20);
//        }
        int startX = 20;
        int startY = 60;

        int panelWidth = 280;
        int panelHeight = 50;

        int gapX = 290; 
        int gapY = 60; 

        for (int i = 0; i < panelTitles.length; i++) {

            int row = i / 2; 
            int col = i % 2; 

            int x = startX + (col * gapX);
            int y = startY + (row * gapY);

            JPanel pnl = createSmallPanel(pnlhistory, x, y, panelWidth, panelHeight);
            JLabel lblTitle = createLabel(pnl, panelTitles[i], 10, 5, 200, 20);
            applyFont (lblTitle, "Seoge UI", Font.BOLD, 12);
            lblTitle.setForeground(Color.LIGHT_GRAY);
            JLabel lblvalues = createLabel (pnl, historyValues[i],10, 25, 200, 20);
            JTextField txtvalues = createHiddenField(pnl, historyValues[i],10, 25, 200, 20);
            setupEditableComponent(pnl, lblvalues, txtvalues);
        }
            btnEditHistory.addActionListener(e -> {
                editMode = !editMode;
                btnEditHistory.setText(editMode ? "Save" : "Edit");
            });
    }
    
    public void DiagnosticsPanel(){
        JPanel pnldiagnostics = new JPanel();
        pnldiagnostics.setLayout(null);
        pnldiagnostics.setBounds(1080, 20, 700, 400);
        pnldiagnostics.setBackground(Color.WHITE);
        centerPanel.add(pnldiagnostics);
        
        
        JLabel lbldiagnostics = createLabel(pnldiagnostics, "Diagnostic Results",  70, 20, 300, 15);
        
        
        JPanel pnlResultsContainer = new JPanel();
        pnlResultsContainer.setLayout(null);
        pnlResultsContainer.setPreferredSize(new Dimension(500, 800));
        pnlResultsContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(pnlResultsContainer);
        scrollPane.setBounds(10, 51, 630, 230);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
       
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.LIGHT_GRAY;
                this.trackColor = Color.WHITE;      
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setBackground(Color.lightGray);
                return button;
            }
        });
        
        pnldiagnostics.add(scrollPane);
        pnldiagnostics.add(createLine(0,50,620,1,Color.LIGHT_GRAY));
        
        int starty = 10 ;
        int gap = 100;
        int numberofPanels = 7;
        
        for (int i = 0; i < numberofPanels; i++){
            int y = starty + (i*gap);
            JPanel pnl  = createSmallPanel(pnlResultsContainer, 30, y, 530,90);
            pnl.setBackground(new Color(0xA3, 0xCA, 0xE9));
            JPanel pnlcenter = createSmallPanel (pnl, 0, 40, 530, 50);
            JLabel lbl = createLabel(pnl, " Results", 10, 10, 300, 15);
        }
    }
    
    public void DietPanel(){
        JPanel pnldiet = new JPanel();
        pnldiet.setLayout(null);
        pnldiet.setBounds(690, 380, 350, 300);
        pnldiet.setBackground(Color.WHITE);
        centerPanel.add(pnldiet);
        
        JLabel lbldiet= createLabel(pnldiet, "Diet",  50, 20, 300, 15);
        pnldiet.add(createLine(30,50,280,1, Color.LIGHT_GRAY));  
        JButton btnEditHistory = createEditButton(250, 10);
        pnldiet.add(btnEditHistory);
        
        int starty = 50 ;
        int gap = 60;
        int numberofPanels = 4;
        String[] lblcontent = {"8 cups", "Table Sugar, Daily Avg 3/6", "Lactose, Beans", "8h Sleeping"};
        
        for (int i = 0; i < numberofPanels; i++){
            int y = starty + (i*gap);
            JPanel pnl  = createSmallPanel(pnldiet, 30, y, 290,50);
            pnl.setBackground(Color.WHITE);
            JLabel lbl = createLabel(pnl, lblcontent[i], 40, 20, 300, 15);
            
        }
    }

    public void MedicationsPanel(){
        JPanel pnlmedications = new JPanel();
        pnlmedications.setLayout(null);
        pnlmedications.setBounds(20, 380, 650, 240);
        pnlmedications.setBackground(Color.WHITE);
        centerPanel.add(pnlmedications);
    
        JLabel lblTitle = createLabel(pnlmedications,"Medications",50,15,200,30);
        
        
        String[] columnMedications = {"Name", "Status", "Recent","Start Date", "Assign By", "Note"};
        String[][] rowMedications = {
            {"Panadol 1000m", "Somehow Adherent", "04/12/2024", "03/12/2024", "Doctor", "--"},
            {"Amaryl 1mg", " Adherent", "04/12/2024", "03/12/2024", "Nurse", "--"},
            {"Vitacid 1000m", "Not Adherent", "04/12/2024", "03/12/2024", "Patient", "--"},
            {"Amoxicillin 500mg", "Adherent", "10/04/2026", "17/04/2026", "Patient", "--"},
            {"Metformin 850mg", "Not Adherent", "12/04/2026", "15/04/2026", "Patient", "Forgot"},
            {"Losartan 50mg", "Adherent", "01/04/2026", "30/04/2026", "Patient", "--"},
            {"Paracetamol 500mg", "Adherent", "14/04/2026", "15/04/2026", "Patient", "As needed"},
            {"Multivitamins", "Not Adherent", "05/04/2026", "12/04/2026", "Patient", "Ran out"},
        };
        
        JTable tblMedications = new JTable(rowMedications, columnMedications){
            @Override
            public boolean isCellEditable(int row, int column){
            return editMode;
                    }
        };
        tblMedications.setRowHeight(30);
//        tblMedications.setFont(new Font("Seoge UI", Font.PLAIN, 13));
//        tblMedications.getTableHeader().setFont(new Font("Seoge UI", Font.PLAIN, 13));
        tblMedications.setGridColor(Color.LIGHT_GRAY);
        tblMedications.setBackground(Color.WHITE);
        tblMedications.setShowGrid(false);
        tblMedications.setSelectionBackground(new Color(220, 240, 255));
        
        JTableHeader TblHApp = tblMedications.getTableHeader();
        TblHApp.setBackground(new Color(0xA3, 0xCA, 0xE9));
        TblHApp.setFont(new Font("Seoge UI", Font.BOLD, 13));
        TblHApp.setForeground(Color.DARK_GRAY);
        
        JScrollPane srcMedications = new JScrollPane(tblMedications);
        srcMedications.setBounds(0, 50, 650, 190);
        srcMedications.getVerticalScrollBar().setUnitIncrement(16);
       
        JScrollBar vertical = srcMedications.getVerticalScrollBar();
        vertical.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.LIGHT_GRAY;
                this.trackColor = Color.WHITE;      
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setBackground(Color.lightGray);
                return button;
            }
        });
        pnlmedications.add(srcMedications);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer left = new DefaultTableCellRenderer();
        left.setHorizontalAlignment(SwingConstants.LEFT);

        tblMedications.getColumnModel().getColumn(0).setCellRenderer(left); 
        tblMedications.getColumnModel().getColumn(1).setCellRenderer(center); 
        tblMedications.getColumnModel().getColumn(2).setCellRenderer(center); 
        tblMedications.getColumnModel().getColumn(3).setCellRenderer(center); 
        tblMedications.getColumnModel().getColumn(4).setCellRenderer(center); 
        tblMedications.getColumnModel().getColumn(5).setCellRenderer(center);
        
        String[] status = {"", "Adherent", "Somehow Adherent", "Not Adherent"};
        TableColumn statusColumn = tblMedications.getColumnModel().getColumn(1);
        statusColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(status)));
        
        statusColumn.setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JLabel lblStatus = new JLabel(value != null ? value.toString() : "", SwingConstants.CENTER);
            lblStatus.setOpaque(true);
//            lblStatus.setFont(new Font("Seoge UI", Font.PLAIN, 13));
            
            if ("Adherent".equals(value)) {
                lblStatus.setBackground(new Color(204, 255, 204));
            } else if ("Somehow Adherent".equals(value)) {
                lblStatus.setBackground(new Color(255, 229, 204));
            } else if ("Not Adherent".equals(value)) {
                lblStatus.setBackground(new Color(255, 204, 204));
            } else {
                lblStatus.setBackground(Color.WHITE);
            }
            return lblStatus;
                });
        
        JButton btnEditMedications = createEditButton(550, 20);
        btnEditMedications.addActionListener(e -> {
            editMode = !editMode;

            // Stop editing when saving
            if (!editMode && tblMedications.isEditing()) {
                tblMedications.getCellEditor().stopCellEditing();
            }

            btnEditMedications.setText(editMode ? "Save" : "Edit");
            tblMedications.repaint();
        });
        pnlmedications.add(btnEditMedications);
    }
   
    }


    

