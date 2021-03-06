package org.hexrobot.snescolor;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuItem;

public class MainWindow {
    private JFrame frmSnesColor;
    private ColorButton btnColorPreview;
    private ColorButton[] colorButtons;
    private ColorButton btnSelectedPalette;
    private MainController mainController;
    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;
    private JSpinner spRed;
    private JSpinner spGreen;
    private JSpinner spBlue;
    private JButton btnDeleteColor;
    private JTextArea txtColorsHex;
    private String hexText = "0000";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frmSnesColor.setVisible(true);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainWindow() {
        initialize();
        mainController = new MainController(this);
    }

    private void initialize() {
        frmSnesColor = new JFrame();
        frmSnesColor.setTitle(MainController.PROGRAM_NAME);
        frmSnesColor.setBounds(100, 100, 405, 382);
        frmSnesColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSnesColor.setResizable(false);
        
        JMenuBar menuBar = new JMenuBar();
        frmSnesColor.getContentPane().add(menuBar, BorderLayout.NORTH);
        
        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        
        JMenuItem menuExit = new JMenuItem("Exit");
        menuFile.add(menuExit);
        
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
        
        JMenuItem menuAbout = new JMenuItem("About");
        menuHelp.add(menuAbout);
        
        JPanel panContent = new JPanel();
        frmSnesColor.getContentPane().add(panContent, BorderLayout.CENTER);
        panContent.setLayout(new BoxLayout(panContent, BoxLayout.X_AXIS));
        
        JPanel panLeft = new JPanel();
        panContent.add(panLeft);
        panLeft.setLayout(new BoxLayout(panLeft, BoxLayout.Y_AXIS));
        
        Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
        panLeft.add(rigidArea_1);
        
        btnColorPreview = new ColorButton("");
        btnColorPreview.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnColorPreview.setSize(80, 80);
        btnColorPreview.setEnabled(false);
        btnColorPreview.setMaximumSize(new Dimension(120, 120));
        panLeft.add(btnColorPreview);
        
        Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
        panLeft.add(rigidArea_5);
        
        JPanel panSliders = new JPanel();
        panSliders.setMaximumSize(new Dimension(170, 100));
        panLeft.add(panSliders);
        GridBagLayout gbl_panSliders = new GridBagLayout();
        gbl_panSliders.columnWidths = new int[] {60, 60, 60, 0};
        gbl_panSliders.rowHeights = new int[] {35, 35, 30};
        gbl_panSliders.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        gbl_panSliders.rowWeights = new double[]{0.0, 0.0, 0.0};
        panSliders.setLayout(gbl_panSliders);
        
        sliderRed = new JSlider();
        sliderRed.setMajorTickSpacing(8);
        sliderRed.setPaintTicks(true);
        sliderRed.setValue(0);
        sliderRed.setMaximum(31);
        GridBagConstraints gbc_sliderRed = new GridBagConstraints();
        gbc_sliderRed.gridwidth = 2;
        gbc_sliderRed.fill = GridBagConstraints.BOTH;
        gbc_sliderRed.insets = new Insets(0, 0, 5, 5);
        gbc_sliderRed.gridx = 0;
        gbc_sliderRed.gridy = 0;
        panSliders.add(sliderRed, gbc_sliderRed);
        
        spRed = new JSpinner();
        spRed.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        GridBagConstraints gbc_spRed = new GridBagConstraints();
        gbc_spRed.insets = new Insets(0, 0, 5, 5);
        gbc_spRed.gridx = 2;
        gbc_spRed.gridy = 0;
        panSliders.add(spRed, gbc_spRed);
        
        sliderGreen = new JSlider();
        sliderGreen.setMajorTickSpacing(8);
        sliderGreen.setPaintTicks(true);
        sliderGreen.setValue(0);
        sliderGreen.setMaximum(31);
        GridBagConstraints gbc_sliderGreen = new GridBagConstraints();
        gbc_sliderGreen.gridwidth = 2;
        gbc_sliderGreen.fill = GridBagConstraints.BOTH;
        gbc_sliderGreen.insets = new Insets(0, 0, 5, 5);
        gbc_sliderGreen.gridx = 0;
        gbc_sliderGreen.gridy = 1;
        panSliders.add(sliderGreen, gbc_sliderGreen);
        
        spGreen = new JSpinner();
        spGreen.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        GridBagConstraints gbc_spGreen = new GridBagConstraints();
        gbc_spGreen.insets = new Insets(0, 0, 5, 5);
        gbc_spGreen.gridx = 2;
        gbc_spGreen.gridy = 1;
        panSliders.add(spGreen, gbc_spGreen);
        
        sliderBlue = new JSlider();
        sliderBlue.setMajorTickSpacing(8);
        sliderBlue.setPaintTicks(true);
        sliderBlue.setValue(0);
        sliderBlue.setMaximum(31);
        GridBagConstraints gbc_sliderBlue = new GridBagConstraints();
        gbc_sliderBlue.gridwidth = 2;
        gbc_sliderBlue.fill = GridBagConstraints.BOTH;
        gbc_sliderBlue.insets = new Insets(0, 0, 0, 5);
        gbc_sliderBlue.gridx = 0;
        gbc_sliderBlue.gridy = 2;
        panSliders.add(sliderBlue, gbc_sliderBlue);
        
        spBlue = new JSpinner();
        spBlue.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        GridBagConstraints gbc_spBlue = new GridBagConstraints();
        gbc_spBlue.insets = new Insets(0, 0, 0, 5);
        gbc_spBlue.gridx = 2;
        gbc_spBlue.gridy = 2;
        panSliders.add(spBlue, gbc_spBlue);
        
        JPanel panRight = new JPanel();
        panContent.add(panRight);
        panRight.setLayout(new BoxLayout(panRight, BoxLayout.Y_AXIS));
        
        Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
        panRight.add(rigidArea_2);
        
        JPanel panGrid = new JPanel();
        panGrid.setMaximumSize(new Dimension(200, 200));
        panRight.add(panGrid);
        panGrid.setLayout(new GridLayout(4, 4, 5, 5));
        
        JButton btnPalette1 = new ColorButton("");
        panGrid.add(btnPalette1);
        
        JButton btnPalette2 = new ColorButton("");
        panGrid.add(btnPalette2);
        
        JButton btnPalette3 = new ColorButton("");
        panGrid.add(btnPalette3);
        
        JButton btnPalette4 = new ColorButton("");
        panGrid.add(btnPalette4);
        
        JButton btnPalette5 = new ColorButton("");
        panGrid.add(btnPalette5);
        
        JButton btnPalette6 = new ColorButton("");
        panGrid.add(btnPalette6);
        
        JButton btnPalette7 = new ColorButton("");
        panGrid.add(btnPalette7);
        
        JButton btnPalette8 = new ColorButton("");
        panGrid.add(btnPalette8);
        
        JButton btnPalette9 = new ColorButton("");
        panGrid.add(btnPalette9);
        
        JButton btnPalette10 = new ColorButton("");
        panGrid.add(btnPalette10);
        
        JButton btnPalette11 = new ColorButton("");
        panGrid.add(btnPalette11);
        
        JButton btnPalette12 = new ColorButton("");
        panGrid.add(btnPalette12);
        
        JButton btnPalette13 = new ColorButton("");
        panGrid.add(btnPalette13);
        
        JButton btnPalette14 = new ColorButton("");
        panGrid.add(btnPalette14);
        
        JButton btnPalette15 = new ColorButton("");
        panGrid.add(btnPalette15);
        
        JButton btnPalette16 = new ColorButton("");
        panGrid.add(btnPalette16);
        
        Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
        panRight.add(rigidArea_4);
        
        JButton btnAddColor = new JButton("Add color");
        btnAddColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddColor.setMaximumSize(new Dimension(120, 40));
        panRight.add(btnAddColor);
        
        Component rigidArea = Box.createRigidArea(new Dimension(20, 5));
        panRight.add(rigidArea);
        
        btnDeleteColor = new JButton("Delete color");
        btnDeleteColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeleteColor.setMaximumSize(new Dimension(120, 40));
        btnDeleteColor.setEnabled(false);
        panRight.add(btnDeleteColor);
        
        Component westSpace = Box.createRigidArea(new Dimension(20, 20));
        frmSnesColor.getContentPane().add(westSpace, BorderLayout.WEST);
        
        Component eastSpace = Box.createRigidArea(new Dimension(20, 20));
        frmSnesColor.getContentPane().add(eastSpace, BorderLayout.EAST);
        
        colorButtons = new ColorButton[16];
        colorButtons[0] = (ColorButton) btnPalette1;
        colorButtons[1] = (ColorButton) btnPalette2;
        colorButtons[2] = (ColorButton) btnPalette3;
        colorButtons[3] = (ColorButton) btnPalette4;
        colorButtons[4] = (ColorButton) btnPalette5;
        colorButtons[5] = (ColorButton) btnPalette6;
        colorButtons[6] = (ColorButton) btnPalette7;
        colorButtons[7] = (ColorButton) btnPalette8;
        colorButtons[8] = (ColorButton) btnPalette9;
        colorButtons[9] = (ColorButton) btnPalette10;
        colorButtons[10] = (ColorButton) btnPalette11;
        colorButtons[11] = (ColorButton) btnPalette12;
        colorButtons[12] = (ColorButton) btnPalette13;
        colorButtons[13] = (ColorButton) btnPalette14;
        colorButtons[14] = (ColorButton) btnPalette15;
        colorButtons[15] = (ColorButton) btnPalette16;
        
        JPanel panel = new JPanel();
        frmSnesColor.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 10));
        panel.add(rigidArea_3);
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
        
        Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
        panel_1.add(rigidArea_8);
        
        Font hexFont = new Font("SansSerif", 0, 10);
        
        txtColorsHex = new JTextArea();
        txtColorsHex.setLineWrap(true);
        txtColorsHex.setEditable(false);
        txtColorsHex.setMinimumSize(new Dimension(200, 36));
        txtColorsHex.setText("0000");
        txtColorsHex.setFont(hexFont);
        panel_1.add(txtColorsHex);
        
        Component rigidArea_7 = Box.createRigidArea(new Dimension(10, 20));
        panel_1.add(rigidArea_7);
        
        JButton btnCopy = new JButton("Copy");
        panel_1.add(btnCopy);
        
        Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
        panel_1.add(rigidArea_9);
        
        Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
        panel.add(rigidArea_6);
        
        btnSelectedPalette = colorButtons[0];
        btnSelectedPalette.setSelected(true);
        
        // menu bar events
        
        menuExit.addActionListener((e) -> {
            mainController.exit();
        });
        
        menuAbout.addActionListener((e) -> {
            JDialog dialog = new AboutFrame();
            dialog.setVisible(true);
        });
        
        // Sliders events
        
        sliderRed.addChangeListener((e) -> {
            int redValue = sliderRed.getValue();
            mainController.updateRed(redValue);
            spRed.setValue(redValue);
            updateColorHex();
        });
        
        sliderGreen.addChangeListener((e) -> {
            int greenValue = sliderGreen.getValue();
            mainController.updateGreen(greenValue);
            spGreen.setValue(greenValue);
            updateColorHex();
        });
        
        sliderBlue.addChangeListener((e) -> {
            int blueValue = sliderBlue.getValue();
            mainController.updateBlue(blueValue);
            spBlue.setValue(blueValue);
            updateColorHex();
        });
        
        // Spinners event listeners
        
        spRed.addChangeListener((e) -> {
            int redValue = (int)spRed.getValue();
            mainController.updateRed(redValue);
            sliderRed.setValue(redValue);
        });
        
        spGreen.addChangeListener((e) -> {
            int greenValue = (int)spGreen.getValue();
            mainController.updateGreen(greenValue);
            sliderGreen.setValue(greenValue);
        });
        
        spBlue.addChangeListener((e) -> {
            int blueValue = (int)spBlue.getValue();
            mainController.updateBlue(blueValue);
            sliderBlue.setValue(blueValue);
        });
        
        // palette buttons events
        btnPalette1.addActionListener(new ButtonActionListener(0));
        btnPalette2.addActionListener(new ButtonActionListener(1));
        btnPalette3.addActionListener(new ButtonActionListener(2));
        btnPalette4.addActionListener(new ButtonActionListener(3));
        btnPalette5.addActionListener(new ButtonActionListener(4));
        btnPalette6.addActionListener(new ButtonActionListener(5));
        btnPalette7.addActionListener(new ButtonActionListener(6));
        btnPalette8.addActionListener(new ButtonActionListener(7));
        btnPalette9.addActionListener(new ButtonActionListener(8));
        btnPalette10.addActionListener(new ButtonActionListener(9));
        btnPalette11.addActionListener(new ButtonActionListener(10));
        btnPalette12.addActionListener(new ButtonActionListener(11));
        btnPalette13.addActionListener(new ButtonActionListener(12));
        btnPalette14.addActionListener(new ButtonActionListener(13));
        btnPalette15.addActionListener(new ButtonActionListener(14));
        btnPalette16.addActionListener(new ButtonActionListener(15));
        
        // add color
        btnAddColor.addActionListener((e) -> {
            JDialog dialog = new AddColorFrame(frmSnesColor, mainController);
            dialog.setVisible(true);
        });
        
        // delete color
        btnDeleteColor.addActionListener((e) -> {
            mainController.removeColor();
        });
        
        // copy hex
        btnCopy.addActionListener((e) -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection transferableText = new StringSelection(hexText);
            clipboard.setContents(transferableText, null);
        });
    }
    
    public void updateSelectedColor(Color color) {
        btnColorPreview.setColor(color);
        btnSelectedPalette.setColor(color);
    }
    
    public void updateShownPalette(List<SnesColor> palette)  {
        for(int i = 0; i < 16; i++) {
            if(i < palette.size()) {
                SnesColor currentPalette = palette.get(i);
                ColorButton colButton = colorButtons[i];
                
                colButton.setColor(currentPalette.getRGB24Color());
                colButton.setVisible(true);
            } else {
                colorButtons[i].setVisible(false);
            }
        }
    }
    
    public void updateColors(List<SnesColor> palette) {
        updateShownPalette(palette);
        btnDeleteColor.setEnabled(palette.size() > 1);
        updateColorsHex(palette);
    }
    
    private void updateColorsHex(List<SnesColor> palette) {
        hexText = "";
        
        for(SnesColor color : palette) {
            hexText += color.getBGR15String(true);
        }
        
        txtColorsHex.setText(hexText);
    }
    
    private void updateColorHex() {
        int index = mainController.getSelectedColorIndex();
        SnesColor color = mainController.getPaletteColor(index);
        String colHex = color.getBGR15String(true);
        char[] charArray = hexText.toCharArray();
        int offset = index * 4;
        
        charArray[offset] = colHex.charAt(0);
        charArray[offset + 1] = colHex.charAt(1);
        charArray[offset + 2] = colHex.charAt(2);
        charArray[offset + 3] = colHex.charAt(3);
        
        hexText = new String(charArray);
        txtColorsHex.setText(hexText);
    }
    
    public void updateSelectedButton(int index) {
        btnSelectedPalette.setSelected(false);
        btnSelectedPalette = colorButtons[index];
        btnSelectedPalette.setSelected(true);
        setPreviewColor(mainController.getPaletteColor(index));
    }
    
    private void setPreviewColor(SnesColor color) {        
        sliderRed.setValue(color.getRed());
        sliderGreen.setValue(color.getGreen());
        sliderBlue.setValue(color.getBlue());
        
        //Note: spinner are updated automatically
    }
    
    private class ButtonActionListener implements ActionListener {
        private int buttonIndex;
        
        public ButtonActionListener(int buttonIndex) {
            this.buttonIndex = buttonIndex;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            btnSelectedPalette.setSelected(false);
            btnSelectedPalette = (ColorButton)e.getSource();
            btnSelectedPalette.setSelected(true);
            mainController.setSelectedColor(buttonIndex);
            setPreviewColor(mainController.getPaletteColor(buttonIndex));
        }
    }
}
