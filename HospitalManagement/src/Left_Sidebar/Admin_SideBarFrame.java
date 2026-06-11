package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Dashboard.Admin_Dashboard;
import Database.UserManagementSQL;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class Admin_SideBarFrame extends JFrame {

    private JPanel window;
    private JPanel hdr;
    private JLabel lblAdminName, lblAdminIcon;
    private ImageIcon imgAdmin, icon;
    private Image imdAd, imgAD;

    public Admin_SideBarFrame() {
        setTitle("eTriage | Hospital Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        
        icon = new ImageIcon(getClass().getResource("/resources/eTriage.Logo.png"));
        setIconImage(icon.getImage());

        // Sidebar
        Admin_Sidebar sidebar = new Admin_Sidebar(this);
        sidebar.setBounds(0, 0, 300, 1080);
        add(sidebar);

        hdr = new JPanel();
        hdr.setLayout(null);
        hdr.setBounds(300, 0, 1620, 70);
        hdr.setBackground(lightBlue);
        add(hdr);

        String adminName = UserManagementSQL.currentEmployee.getName();
        String adminRole = UserManagementSQL.currentEmployee.getRole();
        lblAdminName = new JLabel(adminRole + " | " + adminName);
        lblAdminName.setFont(new Font("Calibri", Font.BOLD, 18));
        lblAdminName.setBounds(1300, 20, 350, 35);
        hdr.add(lblAdminName);

        String profileImagePath = UserManagementSQL.currentEmployee.getProfileImage();
        if (profileImagePath != null && !profileImagePath.isEmpty()) {
            File imgFile = new File(profileImagePath);
            if (imgFile.exists()) {
                imgAdmin = new ImageIcon(profileImagePath);
            } else {
                imgAdmin = new ImageIcon(getClass().getResource(profileImagePath));
            }
        } else {
            
            imgAdmin = new ImageIcon(getClass().getResource("/resources/ADMIN.PHOTO.png"));
        }

        imdAd = imgAdmin.getImage();
        imgAD = imdAd.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblAdminIcon = new JLabel(new ImageIcon(imgAD));
        lblAdminIcon.setBounds(1220, 5, 60, 60);
        hdr.add(lblAdminIcon);

        // Main content area
        window = new JPanel();
        window.setLayout(null);
        window.setBounds(300, 70, 1620, 930);
        window.setBackground(LightGray);
        add(window);

        turnPage(new Admin_Dashboard());
    }

    public void turnPage(JPanel newPage) {
        window.removeAll();
        newPage.setBounds(0, 0, 1620, 930);
        window.add(newPage);
        window.revalidate();
        window.repaint();
    }
}