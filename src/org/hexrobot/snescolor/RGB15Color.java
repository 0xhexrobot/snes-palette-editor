package org.hexrobot.snescolor;

import java.awt.Color;

public class RGB15Color {
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    
    public RGB15Color() {
    }

    public RGB15Color(String hex, boolean littleEndian) {
        int colorValue = Integer.parseInt(hex, 16);
        
        if(littleEndian) {
            if((colorValue & 0xFF) > 0x7F) {
                throw new IllegalArgumentException(hex + " is not little endian");
            }
            
            colorValue = ((colorValue & 0xFF) << 8) | ((colorValue >> 8) & 0xFF);
            
            red = colorValue & 0x1F;
            green = (colorValue >> 5) & 0x1F;
            blue = (colorValue >> 10) & 0x1F;
        } else {
            if((colorValue >> 8) > 0x7F) {
                throw new IllegalArgumentException(hex + " is not big endian");
            }
            
            red = colorValue & 0x1F;
            green = (colorValue >> 5) & 0x1F;
            blue = (colorValue >> 10) & 0x1F;
        }
    }
    
    public int getRed() {
        return red;
    }
    
    public int getGreen() {
        return green;
    }
    
    public int getBlue() {
        return blue;
    }
    
    public void setRed(int red) {
        if(red < 0 || red > 31) {
            throw new IllegalArgumentException("Invalid range for red: " + red);
        }
        
        System.out.println("RGB15Color update red: " + red);
        this.red = red;
    }
    
    public void setGreen(int green) {
        if(green < 0 || green > 31) {
            throw new IllegalArgumentException("Invalid range for green: " + green);
        }
        
        System.out.println("RGB15Color update green: " + green);
        this.green = green;
    }
    
    public void setBlue(int blue) {
        if(blue < 0 || blue > 31) {
            throw new IllegalArgumentException("Invalid range for blue: " + blue);
        }
        
        System.out.println("RGB15Color update blue: " + blue);
        this.blue = blue;
    }
    
    public Color getRGB24Color() {
        return new Color(red * 8, green * 8, blue * 8);
    }
    
    public String getBGR15String() {
        int bgr = (blue << 10) | (green << 5) | red;
        
        return String.format("%04X", bgr);
    }
    
    @Override
    public String toString() {
        String text = String.format("RGB15Color Red: %d, Green: %d, Blue: %d", red, green, blue);
        
        return text;
    }
}
