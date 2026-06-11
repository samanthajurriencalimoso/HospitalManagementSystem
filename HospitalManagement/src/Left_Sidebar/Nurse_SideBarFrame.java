package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Dashboard.Nurse_Dashboard;
import Database.UserManagementSQL;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class Nurse_SideBarFrame extends JFrame{
    
    private JPanel window;
    private JPanel header;
    private JLabel lblNurseName, lblNurseIcon;
    private ImageIcon imgNurse, icon;
    private Image imNur, imgNur;
    
    public Nurse_SideBarFrame() {
        setTitle("eTriage | Hospital Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        
        icon = new ImageIcon(getClass().getResource("/resources/eTriage.Logo.png"));
        setIconImage(icon.getImage());
     
        // Sidebar
        Nurse_SideBar sidebar = new Nurse_SideBar(this);
        sidebar.setBounds(0, 0, 300, 1080); 
        add(sidebar);
        
        header = new JPanel();
        header.setLayout(null);
        header.setBounds(300, 0, 1620, 70);
        header.setBackground(lightBlue);
        add(header);
        
        String nurseName = UserManagementSQL.currentEmployee.getName();
        String nurseRole = UserManagementSQL.currentEmployee.getRole();
        lblNurseName = new JLabel(nurseRole + " | " + nurseName);
        lblNurseName.setFont(new Font("Calibri", Font.BOLD, 18));
        lblNurseName.setBounds(1300, 20, 350, 35);
        header.add(lblNurseName);
        
        String profileImagePath = UserManagementSQL.currentEmployee.getProfileImage();
        
        if (profileImagePath != null && !profileImagePath.isEmpty()) {
            File imgFile = new File(profileImagePath);
            if (imgFile.exists()) {
                imgNurse = new ImageIcon(profileImagePath);
            } else {
                imgNurse = new ImageIcon(getClass().getResource(profileImagePath));
            }
        } else {
            
            imgNurse = new ImageIcon(getClass().getResource("/resources/NURSE.PHOTO.png"));
        }
        imNur = imgNurse.getImage();
        imgNur = imNur.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblNurseIcon = new JLabel(new ImageIcon(imgNur));
        lblNurseIcon.setBounds(1220, 5, 60, 60);
        header.add(lblNurseIcon);

        // Main content area
        window = new JPanel();
        window.setLayout(null);
        window.setBounds(300, 70, 1620, 930);
        window.setBackground(LightGray);
        add(window);

        turnPage(new Nurse_Dashboard());
    
    }

    public void turnPage(JPanel newPage) {
        window.removeAll();
        newPage.setBounds(0, 0, 1620, 930);
        window.add(newPage);
        window.revalidate();
        window.repaint();
    }
}
    