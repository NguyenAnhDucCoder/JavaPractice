
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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
public class DrawCircle extends JPanel {

    private final MainScreen mainScreen;

    public DrawCircle(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        // setBackground for JPanel 
        this.setBackground(Color.WHITE);
        // set width height X Y for jpanel
        this.setBounds(mainScreen.getjPanel_Screen().getWidth() / 2 - 25, mainScreen.getjPanel_Screen().getHeight() / 2 - 25, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        // draw circle
        g.drawOval(0, 0, 50, 50);
        // set color for jpanel
        g.setColor(Color.BLUE);
        // fill circle
        g.fillOval(0, 0, 50, 50);
        
    }

}
