package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Dashboard.Doctor_Dashboard;
import Database.UserManagementSQL;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class Doctor_SideBarFrame extends JFrame{
    
    private JPanel window;
    private JPanel header;
    private JLabel lblDoctorName, lblDoctorIcon;
    private ImageIcon imgDoctor, icon;
    private Image imDoc, imgDoc;
    
    public Doctor_SideBarFrame() {
        setTitle("eTriage | Hospital Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        
        icon = new ImageIcon(getClass().getResource("/resources/eTriage.Logo.png"));
        setIconImage(icon.getImage());
     
        // Sidebar
        Doctor_SideBar sidebar = new Doctor_SideBar(this);
        sidebar.setBounds(0, 0, 300, 1080); 
        add(sidebar);
        
        header = new JPanel();
        header.setLayout(null);
        header.setBounds(300, 0, 1620, 70);
        header.setBackground(lightBlue);
        add(header);
        
        String doctorName = UserManagementSQL.currentEmployee.getName();
        String doctorRole = UserManagementSQL.currentEmployee.getRole();
        lblDoctorName = new JLabel(doctorRole + " | " + doctorName);
        lblDoctorName.setFont(new Font("Calibri", Font.BOLD, 18));
        lblDoctorName.setBounds(1300, 20, 350, 35);
        header.add(lblDoctorName);
        
        String profileImagePath = UserManagementSQL.currentEmployee.getProfileImage();
        
        if (profileImagePath != null && !profileImagePath.isEmpty()) {
            File imgFile = new File(profileImagePath);
            if (imgFile.exists()) {
                imgDoctor = new ImageIcon(profileImagePath);
            } else {
                imgDoctor = new ImageIcon(getClass().getResource(profileImagePath));
            }
        } else {
            
            imgDoctor = new ImageIcon(getClass().getResource("/resources/DOCTOR.PHOTO.png"));
        }
        imDoc = imgDoctor.getImage();
        imgDoc = imDoc.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblDoctorIcon = new JLabel(new ImageIcon(imgDoc));
        lblDoctorIcon.setBounds(1220, 5, 60, 60);
        header.add(lblDoctorIcon);

        // Main content area
        window = new JPanel();
        window.setLayout(null);
        window.setBounds(300, 70, 1620, 930);
        window.setBackground(LightGray);
        add(window);

        turnPage(new Doctor_Dashboard());
    
    }

    public void turnPage(JPanel newPage) {
        window.removeAll();
        newPage.setBounds(0, 0, 1620, 930);
        window.add(newPage);
        window.revalidate();
        window.repaint();
    }
}