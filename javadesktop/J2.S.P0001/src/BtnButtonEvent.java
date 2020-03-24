
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class BtnButtonEvent implements ActionListener {

    private ImageIcon imageIcon;

    public BtnButtonEvent(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ImageTool.imageIconGUI.getjLabel1().setIcon(new ImageIcon(ImageTool.scaleImageIcon(ImageTool.imageIconGUI.getjLabel1(), imageIcon)));
    }
}
