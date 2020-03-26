
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
        mainScreen.getjPanel_Circle().add(circle);
        mainScreen.getBtnBottom().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mainScreen.getjPanel_Screen().getHeight()
                        < mainScreen.getjPanel_Circle().getY() + mainScreen.getjPanel_Circle().getHeight() + move) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                mainScreen.getjPanel_Circle().setLocation(mainScreen.getjPanel_Circle().getX(), mainScreen.getjPanel_Circle().getY() + move);
            }
        });
        mainScreen.getBtnLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (0 > mainScreen.getjPanel_Circle().getX() - move) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                mainScreen.getjPanel_Circle().setLocation(mainScreen.getjPanel_Circle().getX() - move, mainScreen.getjPanel_Circle().getY());
            }
        });
        mainScreen.getBtnRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mainScreen.getjPanel_Screen().getWidth()
                        < mainScreen.getjPanel_Circle().getX() + move + mainScreen.getjPanel_Circle().getWidth()) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                mainScreen.getjPanel_Circle().setLocation(mainScreen.getjPanel_Circle().getX() + move, mainScreen.getjPanel_Circle().getY());
            }
        });
        mainScreen.getBtnUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (0 > mainScreen.getjPanel_Circle().getY() - move) {
                    JOptionPane.showMessageDialog(null, "Cannot move anymore !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                mainScreen.getjPanel_Circle().setLocation(mainScreen.getjPanel_Circle().getX(), mainScreen.getjPanel_Circle().getY() - move);
            }
        });
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setVisible(true);
    }
}
