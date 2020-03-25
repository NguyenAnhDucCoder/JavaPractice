package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import View.FontScreen;
import View.MainScreen;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ANH DUC
 */
public class FontController {

    protected FontScreen fontScreen;

    public FontController() {
        fontScreen = new FontScreen(MainScreenController.mainScreen, true);
        ListFont();
        ListSize();
        setTextSample();
        Event();
        fontScreen.setLocationRelativeTo(null);
        fontScreen.setVisible(true);
    }

    private void Event() {
        fontScreen.getjListFont().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                jListFont_Value();
            }
        });
        fontScreen.getjListFontStyle().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                jListFontStyle_Value();
            }
        });
        fontScreen.getjListSize().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                jListSize_Value();
            }
        });
        fontScreen.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnCancel_JDilog();
            }
        });
        fontScreen.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnOK();
            }
        });
        fontScreen.getTxtSize().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                inputSizeValue();
            }
        });
    }

    private void inputSizeValue() {
        // get cuurent font
        Font current_font = fontScreen.getTxtSample().getFont();
        // get current font family name
        String fontFamilyName = current_font.getFamily();
        // get current font style
        int fontStyle = current_font.getStyle();
        int fontSize = current_font.getSize();
        try {
            // set new fontsize after user input
            fontSize = Integer.parseInt(fontScreen.getTxtSize().getText());
            // set selected list size with fontsize value
            fontScreen.getjListSize().setSelectedValue(fontSize, true);
            // create new font with corresponding values
            Font f = new Font(fontFamilyName, fontStyle, fontSize);
            // set font to text sample
            fontScreen.getTxtSample().setFont(f);
        } catch (NumberFormatException e) {
            // set error mesage
            JOptionPane.showMessageDialog(fontScreen, "Size is incorrect", "Warning", JOptionPane.WARNING_MESSAGE);
            // Set text size again
            fontScreen.getTxtSize().setText(String.valueOf(fontSize));
            // set select jListSize
            fontScreen.getjListSize().setSelectedValue(fontSize, true);
        }
    }

    private void btnOK() {
        // set font notepad text to text sample
        ((MainScreen) fontScreen.getParent()).getjTextArea_Notepad().setFont(fontScreen.getTxtSample().getFont());
        // dispose font screen
        fontScreen.dispose();
    }

    private void btnCancel_JDilog() {
        // dispose dialog
        fontScreen.dispose();
    }

    private void jListSize_Value() {
        // get cuurent font
        Font current_font = fontScreen.getTxtSample().getFont();
        // get current font family name
        String fontName = current_font.getFamily();
        // get current font style
        int fontStyle = current_font.getStyle();
        // set value of selected jListSize
        String a = String.valueOf(fontScreen.getjListSize().getSelectedValue());
        // set text for txtSize
        fontScreen.getTxtSize().setText(a);
        // find current font size
        int fontSize = current_font.getSize();
        try {
            // set fontSize to TxtSize value
            fontSize = Integer.parseInt(fontScreen.getTxtSize().getText());
        } catch (Exception e) {
        }
        // pass new font size
        Font font = new Font(fontName, fontStyle, fontSize);
        // pass new font size to txtSample
        fontScreen.getTxtSample().setFont(font);
    }

    private void jListFont_Value() {
        // get current font
        Font current_font = fontScreen.getTxtSample().getFont();
        // set selected font
        String fontFamilyName = fontScreen.getjListFont().getSelectedValue();
        // set current font style
        int fontStyle = current_font.getStyle();
        // set current font size
        int fontSize = current_font.getSize();
        // set text for font name
        fontScreen.getTxtFont().setText(fontFamilyName);
        // create new font with corresponding values
        Font f = new Font(fontFamilyName, fontStyle, fontSize);
        // set sample to font
        fontScreen.getTxtSample().setFont(f);
    }

    private void jListFontStyle_Value() {
        // set current font
        Font current = fontScreen.getTxtSample().getFont();
        // get selected font style name
        String fontStyleName = fontScreen.getjListFontStyle().getSelectedValue();
        // get selected  index  font style
        int fontStyle = fontScreen.getjListFontStyle().getSelectedIndex();
        // get selected font size
        int fontSize = current.getSize();
        // set text to font style name
        fontScreen.getTxtFontStyle().setText(fontStyleName);
        // set current font name family
        String fontFamilyName = current.getFamily();
        Font f = new Font(fontFamilyName, fontStyle, fontSize);
        // set new font to txtSample
        fontScreen.getTxtSample().setFont(f);
    }

    private void ListSize() {
        DefaultListModel listModel = new DefaultListModel();
        // add even number from 8 to 48 to listModel
        for (int i = 8; i < 49; i += 2) {
            listModel.addElement(i);
        }
        // add list size to jList size
        fontScreen.getjListSize().setModel(listModel);
    }

    private void ListFont() {
        // get all FontFamilyNames to array fontNames
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        DefaultListModel listModel = new DefaultListModel();
        // add all elements in array fontNames to list model
        for (String fontName : fontNames) {
            listModel.addElement(fontName);
        }
        //add list font to Jlist font
        fontScreen.getjListFont().setModel(listModel);
    }

    private void setTextSample() {
        // set font to current font
        Font f = ((MainScreen) fontScreen.getParent()).getjTextArea_Notepad().getFont();
        // set font to text sample
        fontScreen.getTxtSample().setFont(f);

        // set family font value to jListFont()
        fontScreen.getjListFont().setSelectedValue(f.getFamily(), true);
        // set text Family font value to TxtFont()
        fontScreen.getTxtFont().setText(f.getFamily());

        // set fontstyle value to jListFontStyle
        fontScreen.getjListFontStyle().setSelectedIndex(f.getStyle());
        // set fontstyle value to TxtFontStyle
        fontScreen.getTxtFontStyle().setText(fontScreen.getjListFontStyle().getSelectedValue());

        // set font size value to TxtSize
        fontScreen.getTxtSize().setText(Integer.toString(f.getSize()));
        // set font size value to jListSize
        fontScreen.getjListSize().setSelectedValue(f.getSize(), true);

    }

}
