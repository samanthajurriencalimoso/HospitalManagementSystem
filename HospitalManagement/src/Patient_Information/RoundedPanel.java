package Patient_Information;

import javax.swing.*;
import java.awt.*;
public class RoundedPanel extends JPanel {
    
    private Color bg, bd;
    private int roundEdge, bdthick;
    
    public RoundedPanel(String bgcolor, Color bdcolor, int radius, int thickness){
        bg = Color.decode(bgcolor);
        bd = bdcolor;
        roundEdge = radius;
        bdthick = thickness;       
        setOpaque(false);
        
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, width - 1, height - 1, roundEdge, roundEdge);

        g2.setColor(bd);
        g2.setStroke(new BasicStroke(bdthick));
        g2.drawRoundRect(0, 0, width - 1, height - 1, roundEdge, roundEdge);
    }
    
}
