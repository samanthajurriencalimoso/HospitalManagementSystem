package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Dashboard.Nurse_Dashboard;
import java.awt.*;
import javax.swing.*;

public class Nurse_SideBarFrame extends JFrame{
    
    private JPanel window;
    
    public Nurse_SideBarFrame() {
        setTitle("eTriage | Hospital Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
     
        // Sidebar
        Nurse_SideBar sidebar = new Nurse_SideBar(this);
        sidebar.setBounds(0, 0, 300, 1080); 
        add(sidebar);
        
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(300, 0, 1620, 70);
        header.setBackground(lightBlue);
        add(header);
        
        JTextField lblSearch = new JTextField("Search...");
        lblSearch.setBounds(970, 18, 260, 35);
        lblSearch.setFont(new Font("Calibri", Font.PLAIN, 18));
        header.add(lblSearch);
        
        JLabel lblNurseName = new JLabel("Nurse | Angela Cruz");
        lblNurseName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblNurseName.setBounds(1320, 20, 300, 35);
        header.add(lblNurseName);
        
        ImageIcon imgNurse = new ImageIcon(getClass().getResource("/resources/NURSE.PHOTO.png"));
        Image imgNur = imgNurse.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel lblNurseIcon = new JLabel(new ImageIcon(imgNur));
        lblNurseIcon.setBounds(1250, 13, 60, 60);
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
    
