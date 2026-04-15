/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Color_Palette;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jm's Laptop
 */
public class Image_Icons extends JPanel{
    
    private Image imgLNBackground;
    
    public Image_Icons(String path) {
        try {
            imgLNBackground = new ImageIcon(getClass().getResource(path)).getImage();
        } catch (Exception e) {
            System.out.print("The Image is not Found: " + path);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imgLNBackground != null) {
            g.drawImage(imgLNBackground, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
