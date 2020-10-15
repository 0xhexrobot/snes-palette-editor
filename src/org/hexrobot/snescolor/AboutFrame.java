package org.hexrobot.snescolor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class AboutFrame extends JDialog {
    private static final long serialVersionUID = -1612706205805200488L;
    private JPanel contentPane;

    public AboutFrame() {
        setTitle("About SNES Color");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 394, 140);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea, BorderLayout.NORTH);
        
        Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_1, BorderLayout.SOUTH);
        
        Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_2, BorderLayout.EAST);
        
        Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_3, BorderLayout.WEST);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel lblVersion = new JLabel("SNES Color " + MainController.VERSION);
        lblVersion.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.add(lblVersion);
        
        JLabel lblAuthor = new JLabel("Made by hexrobot");
        lblAuthor.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.add(lblAuthor);
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        
        JButton btnOk = new JButton("Ok");
        panel_1.add(btnOk);
        
        btnOk.addActionListener((e) -> {
            dispose();
        });
    }

}
