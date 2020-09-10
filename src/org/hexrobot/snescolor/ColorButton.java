package org.hexrobot.snescolor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class ColorButton extends JButton {
    private static final long serialVersionUID = 1448487849310352750L;
    private Color color = Color.BLACK;
    
    public ColorButton(String text) {
        super(text);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
   }
    
    public void setColor(Color color ) {
        this.color = color;
    }
}
