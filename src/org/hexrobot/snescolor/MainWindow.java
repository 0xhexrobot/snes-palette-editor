package org.hexrobot.snescolor;

import java.awt.EventQueue;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuItem;

public class MainWindow {
    private JFrame frmSnesColor;
    private ColorButton btnColorPreview;
    private ColorButton[] colorButtons;
    private ColorButton btnSelectedPalette;
    private MainController mainController;

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
        frmSnesColor.setTitle("SNES Color");
        frmSnesColor.setBounds(100, 100, 423, 343);
        frmSnesColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        
        JSlider sliderRed = new JSlider();
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
        
        JSpinner spRed = new JSpinner();
        spRed.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        GridBagConstraints gbc_spRed = new GridBagConstraints();
        gbc_spRed.insets = new Insets(0, 0, 5, 5);
        gbc_spRed.gridx = 2;
        gbc_spRed.gridy = 0;
        panSliders.add(spRed, gbc_spRed);
        
        JSlider sliderGreen = new JSlider();
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
        
        JSpinner spGreen = new JSpinner();
        spGreen.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        GridBagConstraints gbc_spGreen = new GridBagConstraints();
        gbc_spGreen.insets = new Insets(0, 0, 5, 5);
        gbc_spGreen.gridx = 2;
        gbc_spGreen.gridy = 1;
        panSliders.add(spGreen, gbc_spGreen);
        
        JSlider sliderBlue = new JSlider();
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
        
        JSpinner spBlue = new JSpinner();
        spBlue.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        GridBagConstraints gbc_spBlue = new GridBagConstraints();
        gbc_spBlue.insets = new Insets(0, 0, 0, 5);
        gbc_spBlue.gridx = 2;
        gbc_spBlue.gridy = 2;
        panSliders.add(spBlue, gbc_spBlue);
        
        Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
        panContent.add(rigidArea_3);
        
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
        
        JButton btnDeleteColor = new JButton("Delete color");
        btnDeleteColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeleteColor.setMaximumSize(new Dimension(120, 40));
        panRight.add(btnDeleteColor);
        
        Component rigidArea = Box.createRigidArea(new Dimension(20, 5));
        panRight.add(rigidArea);
        
        JButton btnAddColor = new JButton("Add color");
        btnAddColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddColor.setMaximumSize(new Dimension(120, 40));
        panRight.add(btnAddColor);
        
        Component westSpace = Box.createRigidArea(new Dimension(20, 20));
        frmSnesColor.getContentPane().add(westSpace, BorderLayout.WEST);
        
        Component eastSpace = Box.createRigidArea(new Dimension(20, 20));
        frmSnesColor.getContentPane().add(eastSpace, BorderLayout.EAST);
        
        Component southSpace = Box.createRigidArea(new Dimension(20, 20));
        frmSnesColor.getContentPane().add(southSpace, BorderLayout.SOUTH);
        
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
        
        btnSelectedPalette = colorButtons[0];
        
        // menu bar events
        
        menuExit.addActionListener((e) -> {
            mainController.exit();
        });
        
        // Sliders events
        
        sliderRed.addChangeListener((e) -> {
            int redValue = sliderRed.getValue();
            mainController.updateRed(redValue);
            spRed.setValue(redValue);
        });
        
        sliderGreen.addChangeListener((e) -> {
            int greenValue = sliderGreen.getValue();
            mainController.updateGreen(greenValue);
            spGreen.setValue(greenValue);
        });
        
        sliderBlue.addChangeListener((e) -> {
            int blueValue = sliderBlue.getValue();
            mainController.updateBlue(blueValue);
            spBlue.setValue(blueValue);
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
        
        ButtonActionListener btnActionListener = new ButtonActionListener();
        
        btnPalette1.addActionListener(btnActionListener);
        btnPalette2.addActionListener(btnActionListener);
        btnPalette3.addActionListener(btnActionListener);
        btnPalette4.addActionListener(btnActionListener);
        btnPalette5.addActionListener(btnActionListener);
        btnPalette6.addActionListener(btnActionListener);
        btnPalette7.addActionListener(btnActionListener);
        btnPalette8.addActionListener(btnActionListener);
        btnPalette9.addActionListener(btnActionListener);
        btnPalette10.addActionListener(btnActionListener);
        btnPalette11.addActionListener(btnActionListener);
        btnPalette12.addActionListener(btnActionListener);
        btnPalette13.addActionListener(btnActionListener);
        btnPalette14.addActionListener(btnActionListener);
        btnPalette15.addActionListener(btnActionListener);
        btnPalette16.addActionListener(btnActionListener);
    }
    
    public void updateSelectedColor(Color color) {
        btnColorPreview.setColor(color);
        btnSelectedPalette.setColor(color);
    }
    
    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            btnSelectedPalette = (ColorButton)e.getSource();
        }
    }
}
