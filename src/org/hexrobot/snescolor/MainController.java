package org.hexrobot.snescolor;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static final int PALETTE_SIZE = 16;
    private List<RGB15Color> palette = new ArrayList<RGB15Color>(List.of(new RGB15Color()));
    private RGB15Color selectedColor = palette.get(0);
    MainWindow mainWindow;
    
    public MainController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        
        mainWindow.updateShownPalette(palette);
    }
    
    public void exit() {
        System.exit(0);
    }
    
    public void setSelectedColor(int index) {
        selectedColor = palette.get(index);
    }
    
    public void addColor() {
        if(palette.size() < PALETTE_SIZE) {
            palette.add(new RGB15Color());
        }
    }
    
    public void removeColor(int index) {
        palette.remove(index);
    }
    
    public void updateRed(int red) {
        selectedColor.setRed(red);
        mainWindow.updateSelectedColor(selectedColor.getRGB24Color());
    }
    
    public void updateGreen(int green) {
        selectedColor.setGreen(green);
        mainWindow.updateSelectedColor(selectedColor.getRGB24Color());
    }
    
    public void updateBlue(int blue) {
        selectedColor.setBlue(blue);
        mainWindow.updateSelectedColor(selectedColor.getRGB24Color());
    }
    
    public void addColors(RGB15Color[] newColors) {
        //TODO add colors
    }
}
