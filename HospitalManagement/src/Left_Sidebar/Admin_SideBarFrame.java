package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Dashboard.Admin_Dashboard;
import Models.Employee;
import java.awt.*;
import javax.swing.*;

public class Admin_SideBarFrame extends JFrame{
     
    private JPanel window;
    private JPanel hdr;
    private JLabel lblAdminName, lblAdminIcon;
    private ImageIcon imgAdmin;
    private Image imdAd, imgAD;
    
    
     public Admin_SideBarFrame() {
        setTitle("eTriage | Hospital Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
     
        // Sidebar
        Admin_Sidebar sidebar = new Admin_Sidebar(this);
        sidebar.setBounds(0, 0, 300, 1080); 
        add(sidebar);
        
        hdr = new JPanel();
        hdr.setLayout(null);
        hdr.setBounds(300, 0, 1620, 70);
        hdr.setBackground(lightBlue);
        add(hdr);
        
        Employee activeUser = Database.UserManagementSQL.currentEmployee;

        String adminName = (activeUser != null) ? activeUser.getName() : "No Admin Logged In";
        String adminRole = (activeUser != null) ? activeUser.getRole() : "Admin";
        String imgPath = (activeUser != null) ? activeUser.getProfileImage() : null;

        lblAdminName = new JLabel(adminRole + " | " + adminName);
        lblAdminName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblAdminName.setBounds(1320, 20, 300, 35);
        hdr.add(lblAdminName);

        try {
            java.net.URL imgUrl = null;
            if (imgPath != null && !imgPath.isEmpty()) {
                imgUrl = getClass().getResource(imgPath);
            }
            if (imgUrl == null) {
                imgUrl = getClass().getResource("/resources/ADMIN.PHOTO.png");
            }
            imgAdmin = new ImageIcon(imgUrl);
        } catch (Exception e) {
            imgAdmin = new ImageIcon(getClass().getResource("/resources/ADMIN.PHOTO.png"));
        }
        
        imgAdmin = new ImageIcon(getClass().getResource("/resources/ADMIN.PHOTO.png"));
        imdAd = imgAdmin.getImage();
        imgAD= imdAd.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        lblAdminIcon = new JLabel(new ImageIcon(imgAD));
        lblAdminIcon.setBounds(1250, 13, 60, 60);
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