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
        
        // For screen size
        setBackground(Color.WHITE);
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
        
        //======================Medications Panel==============
        MedicationsPanel();
        
        //======================Diagnostics Panel==============
        DiagnosticsPanel();
        
        //======================Diet Panel==============
        DietPanel();
        
        JScrollPane mainScroll = new JScrollPane(centerPanel);
        AppPane.setComponentAt(0, centerPanel);

    
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
            btn.setBounds(x, y, 90, 30);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
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
            lblDate.setBounds(15, 5, 100, 20);
            lblDate.setFont(new Font("Segoe UI", Font.BOLD, 14));
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
            circle.setBounds(90, 5, 10, 10);
            row.add(circle);
            
            JPanel verticalLine = new JPanel();
            verticalLine.setBackground(Color.GRAY);
            verticalLine.setBounds(94, 18, 2, 30);
            row.add(verticalLine);

            JLabel lblIllness = new JLabel(illness);
            lblIllness.setBounds(115, 15, 300, 20);
            lblIllness.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
        pnlpatient.setBounds(20,20,1180, 200);
        pnlpatient.setBackground(Color.WHITE);
        centerPanel.add(pnlpatient);
    
        JButton btnEditPatient = createEditButton(1070, 15);
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
        
        JPanel pnlImageFrame = createSmallPanel(pnlpatient, 20, 25, 150, 150);
        JLabel lblImage = createLabel(pnlImageFrame, "", 0, 0, 180, 180);
    
        JButton btnUpload = new JButton("Upload");
        btnUpload.setBounds(45, 90, 100, 30);
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
        
        JLabel lblpatient = createLabel(pnlpatient, "Patient's Name", 200, 25, 300, 24);
        applyFont (lblpatient, "Segoe UI", Font.BOLD, 20);
        JTextField txtpatient = createHiddenField(pnlpatient, "Patient's Name", 200, 25, 300, 24);
        configureEdit(btnEditPatient, lblpatient, txtpatient);
        setupEditableComponent(pnlpatient, lblpatient, txtpatient);
        pnlpatient.add(createLine(200,55,500,1,Color.GRAY));
        
        JLabel lbldiagnosis = createLabel(pnlpatient, " Diagnosis", 1030, 70, 160, 24);
        applyFont (lbldiagnosis, "Segoe UI", Font.BOLD, 15);
        
        JLabel lblbarrier = createLabel(pnlpatient, "Health Barriers", 1030, 125, 150, 24);
        applyFont (lblbarrier, "Segoe UI", Font.BOLD, 15);
        
        JPanel pnldiagnosis = createSmallPanel(pnlpatient, 1030, 100, 80, 20);
        JLabel lbldiag = createLabel(pnldiagnosis, "Leukemia", 10, 2, 100, 15);
        pnldiagnosis.setBackground(Color.WHITE);
        
        String[][] patientData = {{"BMI", "22.4"},{"Weight", "70 kg"},{"Height", "175 cm"},{"Pressure", "120/80"}};
        int startx = 200;
        int gap = 170;
        int panelWidth = 140;
        int numberofPanels = 4;
        
        for (int i = 0; i < patientData.length; i++){
            int x = startx + (i*gap);
            JPanel pnl  = createSmallPanel(pnlpatient, x, 100, panelWidth,75);      
            JLabel lbl = createLabel(pnl, patientData[i][0], 10, 40, 120, 25);
            applyFont (lbl, "Segoe UI", Font.BOLD, 18);
            lbl.setForeground(Color.GRAY);
            
            JLabel lblvalues = createLabel(pnl, patientData[i][1], 10, 10, 90, 25);
            applyFont (lblvalues, "Segoe UI", Font.BOLD, 20);
            JTextField txtvalues = createHiddenField(pnl, patientData[i][1], 10, 10, 90, 25);
            setupEditableComponent(pnl, lblvalues, txtvalues);

        }
            btnEditPatient.addActionListener(e -> {
                editMode = !editMode;
                btnEditPatient.setText(editMode ? "Save" : "Edit");
            });
    }
    
    public void TimeLinePanel(){
        JPanel pnltimeline = new JPanel();
        pnltimeline.setLayout(null);
        pnltimeline.setBounds(745,240,460,250);
        pnltimeline.setBackground(Color.WHITE);
        centerPanel.add(pnltimeline);
        
        JLabel lbltimeline = new JLabel("Medical Timeline");
        lbltimeline.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lbltimeline.setBounds(60, 22, 300, 20);
        pnltimeline.add(lbltimeline);
        
        JButton btnEditTimeline = createEditButton(350, 15);
        pnltimeline.add(btnEditTimeline);
        
        pnltimeline.add(createLine(30,60,400,1, Color.GRAY));
        
        pnltimeline.add(createHistoryRow("Apr 2022", "Pre-Diabetes", 75 ));
        pnltimeline.add(createHistoryRow("May 2024", "Diabetic", 130 ));
        pnltimeline.add(createHistoryRow("Sept 2025", "Leukemia", 185));
        
    }
              
    public void HistoryPanel(){
        JPanel pnlhistory = new JPanel();
        pnlhistory.setBounds(20, 240, 700, 230);
//      pnlhistory.setBounds(20, 180, 620, 230);
        pnlhistory.setLayout(null);
        pnlhistory.setBackground(Color.WHITE);
        centerPanel.add(pnlhistory);
        
        JLabel lblhistory = createLabel(pnlhistory, "Medical History",  60, 22, 300, 20);
        lblhistory.setFont(new Font("Segoe UI", Font.BOLD, 17));
        pnlhistory.add(createLine(30,60,640,1, Color.GRAY));  
        JButton btnEditHistory = createEditButton(590, 15);
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
        int startX = 15;
        int startY = 75;
        
        int gapX = 340; 
        int gapY = 80; 

        for (int i = 0; i < panelTitles.length; i++) {

            int row = i / 2; 
            int col = i % 2; 

            int x = startX + (col * gapX);
            int y = startY + (row * gapY);

            JPanel pnl = createSmallPanel(pnlhistory, x, y, 320, 60);
            JLabel lblTitle = createLabel(pnl, panelTitles[i], 10, 5, 200, 22);
            applyFont (lblTitle, "Segoe UI", Font.BOLD, 16);
            lblTitle.setForeground(Color.GRAY);
            JLabel lblvalues = createLabel (pnl, historyValues[i],10, 32, 200, 25);
            applyFont (lblvalues, "Segoe UI", Font.BOLD, 16);
            JTextField txtvalues = createHiddenField(pnl, historyValues[i],10, 32, 200, 25);
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
        pnldiagnostics.setBounds(20, 490, 700, 370);
        pnldiagnostics.setBackground(Color.WHITE);
        centerPanel.add(pnldiagnostics);
        
        
        JLabel lbldiagnostics = createLabel(pnldiagnostics, "Diagnostic Results",  60, 22, 300, 20);
        applyFont(lbldiagnostics, "Segoe UI", Font.BOLD, 17);
        pnldiagnostics.add(createLine(30,60,640,1, Color.GRAY));
         
        JPanel pnlResultsContainer = new JPanel();
        pnlResultsContainer.setLayout(null);
        pnlResultsContainer.setPreferredSize(new Dimension(500, 700));
        pnlResultsContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(pnlResultsContainer);
        scrollPane.setBounds(10, 62, 680, 290);
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
        
        int starty = 10 ;
        int gap = 170;
        int numberofPanels = 4;
        
        for (int i = 0; i < numberofPanels; i++){
            int y = starty + (i*gap);
            JPanel pnl  = createSmallPanel(pnlResultsContainer, 20, y, 630,150);
            pnl.setBackground(new Color(0xA3, 0xCA, 0xE9));
            JPanel pnlcenter = createSmallPanel (pnl, 0, 50, 630, 100);
            JLabel lbl = createLabel(pnl, " Results", 10, 20, 300, 15);
             applyFont(lbl, "Segoe UI", Font.BOLD, 16);
        }
    }
    
    public void DietPanel(){
        JPanel pnldiet = new JPanel();
        pnldiet.setLayout(null);
        pnldiet.setBounds(1230, 20, 360, 470);
        pnldiet.setBackground(Color.WHITE);
        centerPanel.add(pnldiet);
        
        JLabel lbldiagnostics = createLabel(pnldiet, "Diet",  60, 22, 300, 20);
        applyFont(lbldiagnostics, "Segoe UI", Font.BOLD, 17);
        pnldiet.add(createLine(30,60,300,1, Color.GRAY));
        JButton btnEditHistory = createEditButton(250, 15);
        pnldiet.add(btnEditHistory);
        
        int starty = 80 ;
        int gap = 90;
        int numberofPanels = 4;
        String[] lblcontent = {"8 cups", "Table Sugar, Daily Avg 3/6", "Lactose, Beans", "8h Sleeping"};
        
        for (int i = 0; i < numberofPanels; i++){
            int y = starty + (i*gap);
            JPanel pnl  = createSmallPanel(pnldiet, 30, y, 300,75);
            pnl.setBackground(Color.WHITE);
            JLabel lbl = createLabel(pnl, lblcontent[i], 40, 20, 300, 22);
            applyFont(lbl, "Segoe UI", Font.BOLD, 16);
            JTextField txtvalues = createHiddenField(pnl, lblcontent[i],40, 20, 200, 22);
            setupEditableComponent(pnl, lbl, txtvalues);
        }
            btnEditHistory.addActionListener(e -> {
                editMode = !editMode;
                btnEditHistory.setText(editMode ? "Save" : "Edit");
            });
        
    }

    public void MedicationsPanel(){
        JPanel pnlmedications = new JPanel();
        pnlmedications.setLayout(null);
        pnlmedications.setBounds(750, 510, 840, 350);
        pnlmedications.setBackground(Color.WHITE);
        centerPanel.add(pnlmedications);
    
        JLabel lblTitle = createLabel(pnlmedications,"Medications",  60, 22, 300, 20);
        applyFont(lblTitle, "Segoe UI", Font.BOLD, 17);
        
        
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
        tblMedications.setRowHeight(55);
        tblMedications.setFont(new Font("Sego UI", Font.PLAIN, 16));
        tblMedications.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tblMedications.setGridColor(Color.LIGHT_GRAY);
        tblMedications.setBackground(Color.WHITE);
        tblMedications.setShowGrid(false);
        tblMedications.setSelectionBackground(new Color(220, 240, 255));
        
        JTableHeader TblHApp = tblMedications.getTableHeader();
        TblHApp.setBackground(new Color(0xA3, 0xCA, 0xE9));
        TblHApp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        TblHApp.setForeground(Color.DARK_GRAY);
        
        JScrollPane srcMedications = new JScrollPane(tblMedications);
        srcMedications.setBounds(0, 60, 840, 290);
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
            lblStatus.setFont(new Font("Seoge UI", Font.PLAIN, 16));
            
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
        
        JButton btnEditMedications = createEditButton(730, 15);
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