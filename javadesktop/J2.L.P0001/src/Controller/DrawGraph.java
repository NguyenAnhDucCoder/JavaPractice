/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ANH DUC
 */
public class DrawGraph extends Canvas {

    private void createBackground(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setBackground(Color.WHITE);
        graphics2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("Draw a rectangle", 100, 100);
        graphics2D.drawRect(100, 200, 50, 50);
    }

    @Override
    public void paint(Graphics g) {
        createBackground(g);
    }
}
