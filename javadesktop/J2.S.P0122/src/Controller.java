
import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class Controller {

    private final MainScreen mainScreen;

    public Controller() {
        mainScreen = new MainScreen();
        // setting value of label and panel when first display
        setLabelValue();
        mainScreen.getjSlider_Red().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                // set text for jLabel_Red
                mainScreen.getjLabel_Red().setText("R = " + mainScreen.getjSlider_Red().getValue());
                // set color for panel
                setColorValue(mainScreen.getjSlider_Red().getValue(),
                        mainScreen.getjSlider_Green().getValue(),
                        mainScreen.getjSlider_Blue().getValue());
            }
        });
        mainScreen.getjSlider_Blue().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                // set text for jLabel_Blue
                mainScreen.getjLabel_Blue().setText("B = " + mainScreen.getjSlider_Blue().getValue());
                // set color for panel
                setColorValue(mainScreen.getjSlider_Red().getValue(),
                        mainScreen.getjSlider_Green().getValue(),
                        mainScreen.getjSlider_Blue().getValue());
            }
        });
        mainScreen.getjSlider_Green().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                // set text for jLabel_Green
                mainScreen.getjLabel_Green().setText("G = " + mainScreen.getjSlider_Green().getValue());
                // set color for panel
                setColorValue(mainScreen.getjSlider_Red().getValue(),
                        mainScreen.getjSlider_Green().getValue(),
                        mainScreen.getjSlider_Blue().getValue());
            }
        });
        // set center form
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setVisible(true);
    }

    private void setLabelValue() {
        // set text for jLabel_Red
        mainScreen.getjLabel_Red().setText("R = " + mainScreen.getjSlider_Red().getValue());
        // set text for jLabel_Green
        mainScreen.getjLabel_Green().setText("G = " + mainScreen.getjSlider_Green().getValue());
        // set text for jLabel_Blue
        mainScreen.getjLabel_Blue().setText("B = " + mainScreen.getjSlider_Blue().getValue());
        // set color for jpanel
        setColorValue(mainScreen.getjSlider_Red().getValue(),
                mainScreen.getjSlider_Green().getValue(),
                mainScreen.getjSlider_Blue().getValue());
    }

    private void setColorValue(int red, int green, int blue) {
        Color color = new Color(red, green, blue);
        // set background color for jpanel
        mainScreen.getjPanel_color().setBackground(color);
    }

}
