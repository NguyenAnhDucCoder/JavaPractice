/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author ANH DUC
 */
public class CommonSub {

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
            Image newimageIcon = imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(), imageIcon.getIconHeight(), java.awt.Image.SCALE_SMOOTH);
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

    public static byte[] ConvertFileToByte(String filename) {
        try {
            // get file from filename
            File file = new File(filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileByte = new byte[(int) file.length()];
            // read file byte from file
            fileInputStream.read(fileByte);
            fileInputStream.close();
            return fileByte;
        } catch (Exception e) {
            return null;
        }
    }
}
