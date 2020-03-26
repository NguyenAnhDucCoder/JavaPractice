
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sun.java2d.SunGraphics2D;
import sun.security.provider.Sun;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANH DUC
 */
public class MainScreenController {

    public MainScreen mainScreen;
    private int move = 10;

    public MainScreenController() {
        mainScreen = new MainScreen();
        DrawCircle circle = new DrawCircle(mainScreen);
        mainScreen.getjPanel_Screen().add(circle);
        mainScreen.getBtnBottom().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mainScreen.getjPanel_Screen().getHeight()
                        < circle.getY() + circle.getHeight() + move) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                circle.setLocation(circle.getX(), circle.getY() + move);
            }
        });
        mainScreen.getBtnLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (0 > circle.getX() - move) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                circle.setLocation(circle.getX() - move, circle.getY());
            }
        });
        mainScreen.getBtnRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mainScreen.getjPanel_Screen().getWidth()
                        < circle.getX() + move +circle.getWidth()) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
               circle.setLocation(circle.getX() + move, circle.getY());
            }
        });
        mainScreen.getBtnUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (0 > circle.getY() - move) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                circle.setLocation(circle.getX(),circle.getY() - move);
            }
        });
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setVisible(true);
    }
}
