package Left_Sidebar;

import static Color_Palette.ColorPalette.*;
import Dashboard.Doctor_Dashboard;
import java.awt.*;
import javax.swing.*;

public class Doctor_SideBarFrame extends JFrame{
    
    private JPanel window;
    
    public Doctor_SideBarFrame() {
        setTitle("eTriage | Hospital Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
     
        // Sidebar
        Doctor_SideBar sidebar = new Doctor_SideBar(this);
        sidebar.setBounds(0, 0, 300, 1080); 
        add(sidebar);
        
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(300, 0, 1620, 70);
        header.setBackground(lightBlue);
        add(header);
        
        JTextField lblSearch = new JTextField("Search...");
        lblSearch.setBounds(850, 15, 300, 35);
        lblSearch.setFont(new Font("Calibri", Font.PLAIN, 18));
        header.add(lblSearch);
        
        JLabel lblDoctorName = new JLabel("Doctor | Dr. Isabella Ramos");
        lblDoctorName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblDoctorName.setBounds(1170, 15, 350, 40);
        header.add(lblDoctorName);
        
        ImageIcon imgDoctor = new ImageIcon("C:\\Users\\Jm's Laptop\\Documents\\NetBeansProjects\\HospitalManagement\\src\\resources\\DOCTOR.PHOTO.png");
        Image imgDoc = imgDoctor.getImage();
        Image imgDocScaled = imgDoc.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        JLabel lblDoctorIcon = new JLabel(new ImageIcon(imgDocScaled));
        lblDoctorIcon.setBounds(1420, 13, 60, 60);
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
