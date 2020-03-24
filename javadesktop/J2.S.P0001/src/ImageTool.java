
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class ImageTool {

    public static ImageIconGUI imageIconGUI = new ImageIconGUI();
    public static File file = new File("images");

    public ImageTool() {
        createImageIcon();
        // set display center form
        imageIconGUI.setLocationRelativeTo(null);
        // set visible form
        imageIconGUI.setVisible(true);
    }

    public void createImageIcon() {
        int x = 0;
        // for loop all file in the images folder
        for (File f : file.listFiles()) {
            // create ImageIcon object with find found 
            ImageIcon imageIcon = new ImageIcon(f.getAbsolutePath());
            // create new jButton
            JButton jButton = new JButton();
            // find width of button
            int buttonWidth = imageIconGUI.getjToolBar1().getWidth() / (file.listFiles().length);
            // set position height width of button
            jButton.setBounds(x, 0, buttonWidth - 5, imageIconGUI.getjToolBar1().getHeight() - 5);
            // set icon image to button
            jButton.setIcon(new ImageIcon(scaleImageIcon(jButton, imageIcon)));
            // create event button action listener
            jButton.addActionListener(new BtnButtonEvent(imageIcon));
            // add button to jpanel           
            imageIconGUI.getjPanel1().add(jButton);
            // set x position of next button
            x += (buttonWidth - 3);
        }
    }

    public static Image scaleImageIcon(JComponent control, ImageIcon imageIcon) {
        if (control instanceof JLabel) {
            // set image center for JLabel
            ((JLabel) control).setHorizontalAlignment(JLabel.CENTER);
        }
        // find scale width between image and control
        float scalebyWidth = (float) imageIcon.getIconWidth() / (float) ((JComponent) control).getWidth();
        // find scale height between image and control
        float scalebyHeight = (float) imageIcon.getIconHeight() / (float) ((JComponent) control).getHeight();

        if (scalebyHeight <= 1 && scalebyWidth <= 1) {
            Image newimageIcon = imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(),imageIcon.getIconHeight() , java.awt.Image.SCALE_SMOOTH);
            return newimageIcon;
        }
        // check which scale is bigger to scale image
        if (scalebyWidth > scalebyHeight) {
            // find new width of image
            float newWidth = (float) imageIcon.getIconWidth() / scalebyWidth;
            // find new height of image
            float newHeight = (float) imageIcon.getIconHeight() / scalebyWidth;
            // set new size of image scale by bigger scale
            Image newimageIcon = imageIcon.getImage().getScaledInstance((int) newWidth, (int) newHeight, java.awt.Image.SCALE_SMOOTH);
            return newimageIcon;
        } else {
            // find new width of image
            float newWidth = (float) imageIcon.getIconWidth() / scalebyHeight;
            // find new height of image
            float newHeight = (float) imageIcon.getIconHeight() / scalebyHeight;
            // set new size of image by bigger scale
            Image newimageIcon = imageIcon.getImage().getScaledInstance((int) newWidth, (int) newHeight, java.awt.Image.SCALE_SMOOTH);
            return newimageIcon;
        }
    }

}
