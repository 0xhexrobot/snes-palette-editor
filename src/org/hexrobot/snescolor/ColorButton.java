package org.hexrobot.snescolor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class ColorButton extends JButton {
    private static final long serialVersionUID = 1448487849310352750L;
    private static final int BORDER_WIDTH = 4;
    private BasicStroke borderStroke = new BasicStroke(BORDER_WIDTH);
    private Color color = Color.BLACK;
    private boolean selected = false;
    
    
    public ColorButton(String text) {
        super(text);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(selected) {
            Color border;
            if(color.getRed() > 128) {
                border = Color.white;
            } else {
                border = Color.red;
            }
            
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(border);
            g2.setStroke(borderStroke);
            g2.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - BORDER_WIDTH * 2, getHeight() - BORDER_WIDTH * 2);
        }
   }
    
    public void setColor(Color color ) {
        this.color = color;
        repaint();
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public boolean isSelected() {
        return selected;
    }
}
