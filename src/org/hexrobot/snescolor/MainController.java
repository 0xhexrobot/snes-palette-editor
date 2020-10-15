package org.hexrobot.snescolor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {
    private static final int PALETTE_SIZE = 16;
    private List<RGB15Color> palette = new ArrayList<RGB15Color>(List.of(new RGB15Color()));
    private int selectedColorIndex = 0;
    MainWindow mainWindow;
    
    public MainController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        
        mainWindow.updateShownPalette(palette);
    }
    
    public void exit() {
        System.exit(0);
    }
    
    public int getSelectedColorIndex() {
        return selectedColorIndex;
    }
    
    public void setSelectedColor(int index) {
        selectedColorIndex = index;
    }
    
    public void addColor() {
        if(palette.size() < PALETTE_SIZE) {
            palette.add(new RGB15Color());
        }
    }
    
    public void removeColor() {
        palette.remove(selectedColorIndex);
        mainWindow.updateColors(palette);
        
        if(selectedColorIndex == palette.size()) {
            selectedColorIndex--;
        }
        
        mainWindow.updateSelectedButton(selectedColorIndex);
    }
    
    public void updateRed(int red) {
        RGB15Color selectedColor = palette.get(selectedColorIndex);
        selectedColor.setRed(red);
        mainWindow.updateSelectedColor(selectedColor.getRGB24Color());
    }
    
    public void updateGreen(int green) {
        RGB15Color selectedColor = palette.get(selectedColorIndex);
        selectedColor.setGreen(green);
        mainWindow.updateSelectedColor(selectedColor.getRGB24Color());
    }
    
    public void updateBlue(int blue) {
        RGB15Color selectedColor = palette.get(selectedColorIndex);
        selectedColor.setBlue(blue);
        mainWindow.updateSelectedColor(selectedColor.getRGB24Color());
    }
    
    public void addColors(RGB15Color[] newColors) {
        if(palette.size() + newColors.length > PALETTE_SIZE) {
            int itemsToRemove = palette.size() + newColors.length - PALETTE_SIZE;
            palette.subList(0, itemsToRemove).clear();
        }
        
        palette.addAll(Arrays.asList(newColors));
        mainWindow.updateColors(palette);
    }
    
    public RGB15Color getPaletteColor(int index) {
        return palette.get(index);
    }
}
