package org.hexrobot.snescolor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JMenuItem;

public class AddColorFrame extends JDialog {
    private static final long serialVersionUID = 5772783691950760601L;
    // "([\\da-fA-F]{2}\\s*[\\da-fA-F]{2}\\s*){1,16}" hex regex
    private static final String LITTLE_ENDIAN_REGEX = "([\\da-fA-F]{2}\\s*[0-7][\\da-fA-F]\\s*){1,16}";
    private JPanel contentPane;
    private JTextField txtInput;
    private JMenuItem menuCopy;
    private JMenuItem menuCut;
    private JMenuItem menuPaste;
    private Clipboard clipboard;
    private Pattern bgr15Pattern;
    
    public AddColorFrame(JFrame parent, MainController mainController) {
        super(parent, "Add color", true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 423, 175);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        txtInput = new JTextField();
        txtInput.setMaximumSize(new Dimension(2147483647, 30));
        panel.add(txtInput);
        txtInput.setColumns(10);
        
        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(txtInput, popupMenu);
        
        menuCopy = new JMenuItem("Copy");
        popupMenu.add(menuCopy);
        
        menuCut = new JMenuItem("Cut");
        popupMenu.add(menuCut);
        
        menuPaste = new JMenuItem("Paste");
        popupMenu.add(menuPaste);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel.add(verticalStrut);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(buttonsPanel);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        
        JButton btnOk = new JButton("Ok");
        btnOk.setEnabled(false);
        buttonsPanel.add(btnOk);
        
        Component rigidArea = Box.createRigidArea(new Dimension(5, 20));
        buttonsPanel.add(rigidArea);
        
        JButton btnCancel = new JButton("Cancel");
        buttonsPanel.add(btnCancel);
        
        Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_1, BorderLayout.NORTH);
        
        Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_2, BorderLayout.SOUTH);
        
        Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_3, BorderLayout.WEST);
        
        Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
        contentPane.add(rigidArea_4, BorderLayout.EAST);
        
        btnOk.addActionListener((e) -> {
            if(isValidBRG15()) {
                mainController.addColors(parseColors());
            }
            
            dispose();
        });
        
        btnCancel.addActionListener((e) -> {
            dispose();
        });
        
        txtInput.addActionListener((e) -> {
            btnOk.setEnabled(isValidBRG15());
        });
        
        txtInput.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                btnOk.setEnabled(isValidBRG15());
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                btnOk.setEnabled(isValidBRG15());
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                btnOk.setEnabled(isValidBRG15());
            }
        });
        
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        menuCopy.addActionListener((e) -> {
            String selectedText = txtInput.getSelectedText();
            
            if(selectedText != null) {
                StringSelection transferableText = new StringSelection(selectedText);
                clipboard.setContents(transferableText, null);
            }
        });

        menuCut.addActionListener((e) -> {
            String selectedText = txtInput.getSelectedText();
            
            if(selectedText != null) {
                StringSelection transferableText = new StringSelection(selectedText);
                clipboard.setContents(transferableText, null);
            }
            
            if(txtInput.getSelectedText() != null) {
                txtInput.replaceSelection("");
            }
        });

        menuPaste.addActionListener((e) -> {
            Transferable t = clipboard.getContents(this);

            if(t != null) {
                try {
                    if(t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String pastedText = (String) t.getTransferData(DataFlavor.stringFlavor);
                        
                        if(txtInput.getSelectedText() != null) {
                            txtInput.replaceSelection("");
                        }
                        
                        String text = txtInput.getText();
                        int caretPosition = txtInput.getCaretPosition();
                        
                        if(caretPosition == 0) {
                            text = pastedText + text;
                            caretPosition = pastedText.length();
                        } else if(caretPosition == text.length()) {
                            text += pastedText;
                            caretPosition = text.length();
                        } else {
                            String leftText = text.substring(0, caretPosition);
                            String rightText = text.substring(caretPosition);
                            
                            text = leftText + pastedText + rightText;
                            caretPosition += pastedText.length();
                        }
                        
                        txtInput.setText(text);
                        txtInput.setCaretPosition(caretPosition);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        bgr15Pattern = Pattern.compile(LITTLE_ENDIAN_REGEX);
    }
        
    private boolean isValidBRG15() {
        String txtColors = txtInput.getText();
        Matcher matcher = bgr15Pattern.matcher(txtColors);
        
        return matcher.matches();
    }
    
    private void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		if (e.isPopupTrigger()) {
        			showMenu(e);
        		}
        	}
        	public void mouseReleased(MouseEvent e) {
        		if (e.isPopupTrigger()) {
        			showMenu(e);
        		}
        	}
        	private void showMenu(MouseEvent e) {
                boolean hasSelectedText = ((JTextField) component).getSelectedText() != null;
                
                menuCopy.setEnabled(hasSelectedText);
                menuCut.setEnabled(hasSelectedText);
        	            	    
                try {
                    Transferable t = clipboard.getContents(null);
                    
                    if(t != null) {
                        if(t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            String clipboardText = (String) t.getTransferData(DataFlavor.stringFlavor);
                            boolean clipboardNotEmpty = clipboardText.length() > 0;
                            
                            menuPaste.setEnabled(clipboardNotEmpty);
                        }
                    }
                } catch(UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
        	    
        		popup.show(e.getComponent(), e.getX(), e.getY());
        	}
        });
    }

    private SnesColor[] parseColors() {
        final int COLOR_CHAR_SIZE = 4;
        
        SnesColor[] colors;
        String txtColors = txtInput.getText();
        
        txtColors = txtColors.replaceAll("\\s", "");
        colors = new SnesColor[txtColors.length() / COLOR_CHAR_SIZE];
        
        for(int i = 0; i < txtColors.length() / COLOR_CHAR_SIZE; i++) {
            String currentColor = txtColors.substring(i * COLOR_CHAR_SIZE, i * COLOR_CHAR_SIZE + COLOR_CHAR_SIZE);
            colors[i] = new SnesColor(currentColor, true);
            System.out.println(colors[i]);
        }
        
        return colors;
    }
}
